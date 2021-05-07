package de.spielerei_eichstaett.katalog.security;

import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

public class Sudo implements AutoCloseable {

    public Sudo() {
        var context = SecurityContextHolder.createEmptyContext();
        var token = new TestingAuthenticationToken("sudo", "sudo", "ROLE_USER", "ROLE_ADMIN");
        context.setAuthentication(token);
        SecurityContextHolder.setContext(context);
    }

    @Override
    public void close() {
        SecurityContextHolder.clearContext();
    }
}
