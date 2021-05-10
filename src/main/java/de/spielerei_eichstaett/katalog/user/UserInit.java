package de.spielerei_eichstaett.katalog.user;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

@Configuration
@AllArgsConstructor
//@Transactional
public class UserInit implements CommandLineRunner {
    UserRepo userRepo;

    @Override
    public void run(String... args) {
        final var delegatingPasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        var u1 = new User()
                .withEmail("email2")
                .withAccountNonExpired(true)
                .withEnabled(true)
                .withAccountNonLocked(true)
                .withCredentialsNonExpired(true)
                .withPassword(delegatingPasswordEncoder.encode("user"))
                .withUsername("user")
//                .withUserRoles(List.of(UserRole.USER));
                ;
        userRepo.save(u1);
        userRepo.findAll().forEach(System.out::println);
    }
}
