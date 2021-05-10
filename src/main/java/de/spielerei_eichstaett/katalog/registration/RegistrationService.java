package de.spielerei_eichstaett.katalog.registration;

import de.spielerei_eichstaett.katalog.user.User;
import de.spielerei_eichstaett.katalog.user.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class RegistrationService implements UserDetailsService {

    UserRepo userRepo;
    RegistrationTokenRepo tokenRepo;
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("lol srry, no " + username));
    }

    public String process(RegistrationRequest req) {
        checkUsernameAlreadyTaken(req);

        final var user = createUser(req);
        final var token = RegistrationToken.create24HourTokenFor(user);
        userRepo.save(user);
        tokenRepo.save(token);

        // TODO send email with uuid
        return token.getValue();
    }

    private User createUser(RegistrationRequest req) {
        return new User()
                .withEnabled(false)
                .withPassword(passwordEncoder.encode(req.password()))
                .withUsername(req.username())
                .withEmail(req.email());
    }

    private void checkUsernameAlreadyTaken(RegistrationRequest req) {
        var byUsername = userRepo.findByUsername(req.username());
        if (byUsername.isPresent()) throw new IllegalArgumentException("username already taken");
    }

    public boolean confirmEmail(String uuid) {
        final var token = tokenRepo.findByValue(uuid).orElseThrow(
                () -> new IllegalArgumentException("uuid is invalid: " + uuid));
        final var now = LocalDateTime.now();
        if (now.isAfter(token.expiresAt))
            throw new IllegalArgumentException("token is already expired: " + token + System.lineSeparator() +
                                                       now);
        if (now.isBefore(token.createdAt))
            throw new IllegalArgumentException("token is already expired: " + token + System.lineSeparator() +
                                                       now);
        if (token.confirmedAt != null)
            throw new IllegalStateException("token already consumed");


        final var user = token.getUser();
        user.setEnabled(true);
        userRepo.save(user);
        token.setConfirmedAt(now);
        tokenRepo.save(token);
        return true;
    }
}
