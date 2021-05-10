package de.spielerei_eichstaett.katalog.registration;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping(path = "register")
class RegistrationController {
    RegistrationService service;

    @PostMapping
    public String register(@Valid @RequestBody RegistrationRequest request) {
        return service.process(request);
    }

    @GetMapping
    public String confirm(@RequestParam String token) {
        try {
            service.confirmEmail(token);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "failure";
        }
    }
}
