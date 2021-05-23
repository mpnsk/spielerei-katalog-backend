package de.spielerei_eichstaett.katalog.spiel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;

@Entity
@Getter
@Setter
@With
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Spiel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    //@JsonProperty("n")
    String name;
    @ToString.Exclude
    @Column(length = 2048)
    //@JsonProperty("b")
    String beschreibung;
    //@JsonProperty("dt")
    SpieldauerTyp spieldauerTyp = SpieldauerTyp.Einwert;
    //@JsonProperty("dmin")
    int spieldauerMinutenMin;
    //@JsonProperty("dmax")
    int spieldauerMinutenMax;
    //@JsonProperty("r")
    String erscheinugsjahr;
    //@JsonProperty("smin")
    int spieleranzahlMin;
    //@JsonProperty("smax")
    int spieleranzahlMax;
    //@JsonProperty("a")
    int altersempfehlung;
    //@JsonProperty("amax")
    int altersempfehlungMax;
    //@JsonProperty("p")
    int leihpreis;
    @ManyToOne(fetch = FetchType.EAGER)
    @RestResource
    //@JsonProperty("k")
    Kategorie kategorie;

    public enum SpieldauerTyp {
        Einwert,
        ProSpieler,
        MinMax,
        Beliebig
    }

    @Entity
    @Getter
    @Setter
    public static class Kategorie {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        int id;
        String name;
        @JsonIgnore
        String trelloId;

    }
}
