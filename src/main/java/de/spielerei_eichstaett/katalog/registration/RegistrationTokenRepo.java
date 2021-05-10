package de.spielerei_eichstaett.katalog.registration;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RegistrationTokenRepo extends CrudRepository<RegistrationToken, Long> {
    Optional<RegistrationToken> findByValue(String value);
}
