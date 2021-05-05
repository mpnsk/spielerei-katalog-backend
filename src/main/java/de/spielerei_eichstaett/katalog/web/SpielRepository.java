package de.spielerei_eichstaett.katalog.web;

import de.spielerei_eichstaett.katalog.db.Spiel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "spiele")
public interface SpielRepository extends CrudRepository<Spiel, Integer> {
}
