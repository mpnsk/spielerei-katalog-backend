package de.spielerei_eichstaett.katalog.web;

import de.spielerei_eichstaett.katalog.db.Spiel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Optional;

@PreAuthorize("hasRole('USER')")
@RepositoryRestResource(
        collectionResourceRel = "spiele",
        path = "spiele"
)
public interface SpielRepository extends CrudRepository<Spiel, Integer> {
    @PreAuthorize("permitAll()")
    @Override
    Optional<Spiel> findById(Integer integer);

    @PreAuthorize("permitAll()")
    @Override
    boolean existsById(Integer integer);

    @PreAuthorize("permitAll()")
    @Override
    Iterable<Spiel> findAll();

    @PreAuthorize("permitAll()")
    @Override
    Iterable<Spiel> findAllById(Iterable<Integer> integers);

    @PreAuthorize("permitAll()")
    @Override
    long count();
}
