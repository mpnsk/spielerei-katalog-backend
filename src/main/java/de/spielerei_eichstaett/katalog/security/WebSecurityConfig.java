package de.spielerei_eichstaett.katalog.security;

import de.spielerei_eichstaett.katalog.registration.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    RegistrationService registrationService;
    PasswordEncoder passwordEncoder;

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withDefaultPasswordEncoder()
                                   .username("user")
                                   .password("user")
                                   .roles("USER")
                                   .build());
        manager.createUser(User.withDefaultPasswordEncoder()
                                   .username("admin")
                                   .password("admin")
                                   .roles("USER", "ADMIN")
                                   .build());
        return manager;
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(registrationService);
        return provider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .oauth2Login(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .csrf().disable()
                .authorizeRequests(req -> req
                        .antMatchers("/public", "/public/**").permitAll()
                        .antMatchers("/private", "/private/**").authenticated()
                )
        ;
    }
}