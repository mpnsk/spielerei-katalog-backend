package de.spielerei_eichstaett.katalog;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class InfoController {
    @RequestMapping("/me")
    public Map<String, Object> home(Principal principal) {
        Map<String, Object> model = new HashMap<>();
        model.put("id", UUID.randomUUID().toString());
        model.put("content", "Hello " + principal);
        return model;
    }
}
