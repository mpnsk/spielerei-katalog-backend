package de.spielerei_eichstaett.katalog.spiel;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
public class SpielController {
    final SpielRepository spielRepository;
    final SpielKategorieRepository kategorieRepository;

    @GetMapping
    @RequestMapping("kategorien")
    ArrayList<Spiel.Kategorie> kategorien() {
        final ArrayList<Spiel.Kategorie> list = new ArrayList<>();
        kategorieRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @GetMapping
    @RequestMapping("spiel/{kategorie}/{id}")
    Spiel byKategorieUndId(@PathVariable String kategorie, @PathVariable String id) {
        final var spiel = new Spiel();
        spiel.setName("bsp spiel");
        return spiel;
    }

    @GetMapping
    @RequestMapping("kategorie")
    Map<String, List<Spiel>> byKategorieMap() {
        Map<String, List<Spiel>> map = new HashMap<>();
        kategorieRepository.findAll().forEach(kategorie -> map.put(kategorie.getName(), new ArrayList<>()));
        List.of("Ankündigungen", "In Reparatur", "Allgemeine Informationen", "Entliehen", "eingelagert")
                .forEach(map::remove);
        spielRepository.findAll().forEach(spiel -> {
            final var kategorie = spiel.getKategorie().getName();
            if (map.containsKey(kategorie)) {
                map.get(kategorie).add(spiel);
            }
        });
        return map;
    }
}
