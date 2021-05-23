package de.spielerei_eichstaett.katalog.spiel;

import de.spielerei_eichstaett.katalog.security.Sudo;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class SpielInit implements CommandLineRunner {
    SpielRepository repo;

    @Override
    public void run(String... args) {
        var utopia = new Spiel()
                .withName("Utopia")
                .withBeschreibung("""                                          
                                          Der König von Utopia hat die Fürsten der größten antiken Kulturen eingeladen, die Vielfalt der Architektur ihrer weit entfernten Länder auf der Inselgruppe Utopias entstehen zu lassen. Die Spieler sind Minister des Monarchen und ihre Aufgabe ist es, die Gäste in der Stadt zu empfangen und sie auf ihrem Weg über die einzelnen Inseln zu begleiten. Jeder Schritt des Stadtausbaus wird ihr Ansehen, ihr Prestige vergrößern.

                                          Das Ziel des Spieles ist es, derjenige zu sein, der mindestens 50 Prestige-Punkte gesammelt hat. Außerdem darf natürlich gebaut, Ränke geschmiedet und vor allem begeistert gespielt werden.
                                          """)
                .withErscheinugsjahr("2007")
                .withSpieldauerMinutenMin(60)
                .withSpieleranzahlMin(2)
                .withSpieleranzahlMax(5)
                .withAltersempfehlung(10)
                .withLeihpreis(4)
//                .withKategorie(Spiel.Kategorie.Strategie)
                .withId(1);

        var glasStrasse = new Spiel()
                .withName("Die Glasstraße")
                .withBeschreibung("""
                                          Glasstraße entführt die Spieler in den Bayrischen Wald Mitte des 18. Jahrhunderts. Mit Hilfe von zwei raffinierten Produktionsrädern erzeugst du Glas und Ziegel und verwaltest deine Rohstoffe für die Herstellung dieser Güter. Auf deinem Spielertableau gestaltest du die Landschaft zu deinem Vorteil und baust die verschiedensten Gebäude, um daraus im Spiel Nutzen zu ziehen.

                                          Zentrales Spielelement sind 15 Personenkarten, die jeder Spieler zur Verfügung hat, und fünf davon in jeder Bauperiode auswählt. Doch Vorsicht: Haben deine Mitspieler die gleiche Karte auf der Hand, so schränkt das deine Aktionsmöglichkeiten ein. Flexibel auf diese Unwägbarkeit reagieren zu können, ist der Schlüssel zum Erfolg!

                                          Ob alleine, zu zweit oder zu mehreren: Die Glasstraße ist immer ein Vergnügen!
                                          """)
                .withErscheinugsjahr("2009")
                .withSpieldauerMinutenMin(20)
                .withSpieldauerTyp(Spiel.SpieldauerTyp.ProSpieler)
                .withSpieleranzahlMin(1)
                .withSpieleranzahlMax(4)
                .withAltersempfehlung(12)
                .withLeihpreis(3)
//                .withKategorie(Spiel.Kategorie.Strategie)
                .withId(2);

        try (Sudo sudo = new Sudo()) {
//            repo.save(utopia);
//            repo.save(glasStrasse);
            repo.findAll().forEach(System.out::println);
        }
    }
}
