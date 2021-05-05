package de.spielerei_eichstaett.katalog.db;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@With
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Spiel {
    @Id
    @GeneratedValue
    int id;
    String name;
    @ToString.Exclude
    @Column(length = 2048)
    String beschreibung;
    SpieldauerTyp spieldauerTyp = SpieldauerTyp.Standard;
    int spieldauerMinuten;
    String erscheinugsjahr;
    int spieleranzahlMin;
    int spieleranzahlMax;
    int altersempfehlung;
    int leihpreis;
    Kategorie kategorie;

    public enum Kategorie {
        Strategie,
        Knobel,
        Quiz,
        Klassiker,
        Familie,
        Party,
        ZweiPersonen,
        GamersGame,
        Wuerfel
    }

    public enum SpieldauerTyp {
        Standard,
        ProSpieler
    }
}