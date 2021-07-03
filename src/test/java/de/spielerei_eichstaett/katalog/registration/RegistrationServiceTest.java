package de.spielerei_eichstaett.katalog.registration;

import de.spielerei_eichstaett.katalog.user.User;
import de.spielerei_eichstaett.katalog.user.UserRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceTest {
    @InjectMocks
    RegistrationService registrationService;
    @Mock
    UserRepo userRepo;
    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @BeforeAll
    static void beforeAll() {
//        MockitoAnnotations.initMocks(RegistrationServiceTest.class);
//        MockitoAnnotations.openMocks(RegistrationServiceTest.class);
    }

    @BeforeEach
    void setUp() {
//        registrationService = new RegistrationService(userRepo, NoOpPasswordEncoder.getInstance());
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void loadUserByUsername() {
        final var value = new User();
        when(userRepo.findByUsername("lol")).thenReturn(Optional.of(value));
        final var lol = registrationService.loadUserByUsername("lol");
        System.out.println("registrationService.passwordEncoder = " + registrationService.passwordEncoder);
        System.out.println("lol = " + lol);
        assertEquals(value, lol);
    }
}