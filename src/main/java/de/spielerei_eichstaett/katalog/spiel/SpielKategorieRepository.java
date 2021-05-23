package de.spielerei_eichstaett.katalog.spiel;

import org.springframework.data.repository.CrudRepository;

//@RepositoryRestResource(
//        collectionResourceRel = "kategorien",
//        path = "kategorien"
//)
public interface SpielKategorieRepository extends CrudRepository<Spiel.Kategorie, Integer> {
}
