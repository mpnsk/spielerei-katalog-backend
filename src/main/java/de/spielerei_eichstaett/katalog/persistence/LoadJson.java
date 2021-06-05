package de.spielerei_eichstaett.katalog.persistence;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.spielerei_eichstaett.katalog.security.Sudo;
import de.spielerei_eichstaett.katalog.spiel.Spiel;
import de.spielerei_eichstaett.katalog.spiel.SpielKategorieRepository;
import de.spielerei_eichstaett.katalog.spiel.SpielRepositoryPermissions;
import de.spielerei_eichstaett.trello.dto.TrelloBoard;
import de.spielerei_eichstaett.trello.dto.TrelloList;
import de.spielerei_eichstaett.trello.dto.card.TrelloCard;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.example.TrelloCardToSpielConverter;
import org.example.TrelloListToSpielKategorieConverter;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//@Component
@AllArgsConstructor
public class LoadJson {
    SpielRepositoryPermissions spielRepository;
    SpielKategorieRepository spielKategorieRepository;


    @SneakyThrows
    @PostConstruct
    public void postConstruct() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        final var converter = new TrelloCardToSpielConverter();

        final var trelloBoard = objectMapper.readValue(LoadJson.class.getClassLoader().getResource("spielerei.json"), TrelloBoard.class);

        final var listToKategorieConverter = new TrelloListToSpielKategorieConverter();
        Map<String, TrelloList> listMap = new HashMap<>();
        for (TrelloList list : trelloBoard.getLists()) {
            if (!list.isClosed())
                listMap.putIfAbsent(list.getId(), list);
        }
        Map<String, Spiel.Kategorie> kategorieMap = new HashMap<>();
        listMap.forEach((s, trelloList) -> {
            kategorieMap.put(s, listToKategorieConverter.apply(trelloList));
        });


        final var cards = trelloBoard.getCards();
        cards.removeIf(TrelloCard::getClosed);

        final var spiele = new ArrayList<Spiel>();

        for (TrelloCard card : cards) {
            try {
                final var spiel = converter.apply(card);
                spiel.setKategorie(kategorieMap.get(card.getIdList()));
                spiele.add(spiel);
            } catch (Exception e) {
                System.out.println("card.getName() = " + card.getName());
                e.printStackTrace();
            }
        }
        try (Sudo sudo = new Sudo()) {
            spielKategorieRepository.saveAll(kategorieMap.values());
            spielRepository.saveAll(spiele);
        }
//        System.out.println("spiele = " + spiele);

    }
}
