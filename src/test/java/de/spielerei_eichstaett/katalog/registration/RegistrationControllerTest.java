package de.spielerei_eichstaett.katalog.registration;

import de.spielerei_eichstaett.katalog.user.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith({MockitoExtension.class})
class RegistrationControllerTest {
    @Mock
    UserRepo userRepo;
    @Mock
    PasswordEncoder passwordEncoder;
    @InjectMocks
    RegistrationService registrationService;
    RegistrationController registrationController;
    //    @InjectMocks

    @BeforeEach
    void setUp() {

        registrationController = new RegistrationController(registrationService);
    }

    //    @Test
    void register() {
//        when(userRepo.findByUsername("u")).thenReturn(Optional.of(new User()));
        final var request = new RegistrationRequest("u", "e", "p");

//        when(registrationService.process(request))
        final var register = registrationController.register(request);
        assertEquals("success", register);
    }
}