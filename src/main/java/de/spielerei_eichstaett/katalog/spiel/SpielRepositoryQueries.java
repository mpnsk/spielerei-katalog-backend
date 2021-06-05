package de.spielerei_eichstaett.katalog.spiel;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpielRepositoryQueries extends PagingAndSortingRepository<Spiel, Integer> {
    List<Spiel> findByName(@Param("name") String name);
}
