package de.spielerei_eichstaett.katalog.registration;

import de.spielerei_eichstaett.katalog.user.User;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@With
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationToken {
    @Id
    @GeneratedValue
    Long id;
    String value;
    LocalDateTime createdAt;
    LocalDateTime expiresAt;
    LocalDateTime confirmedAt;
    @ManyToOne
    User user;

    public static RegistrationToken create24HourTokenFor(User user) {
        return new RegistrationToken()
                .withValue(UUID.randomUUID().toString())
                .withCreatedAt(LocalDateTime.now())
                .withExpiresAt(LocalDateTime.now().plusHours(24))
                .withUser(user);
    }
}
