package de.spielerei_eichstaett.katalog.registration;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

record RegistrationRequest(
        @NotBlank String username,
        @NotBlank @Email String email,
        @NotBlank @Size(min = 6, message = "pw must be grater than 6 characters") String password) {
}
