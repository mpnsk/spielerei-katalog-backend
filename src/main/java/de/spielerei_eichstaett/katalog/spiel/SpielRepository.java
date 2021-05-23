package de.spielerei_eichstaett.katalog.spiel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Optional;

@PreAuthorize("hasRole('USER')")
@RepositoryRestResource(
        collectionResourceRel = "spiele",
        path = "spiele"
)
public interface SpielRepository extends PagingAndSortingRepository<Spiel, Integer> {
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

    @PreAuthorize("permitAll()")
    @Override
    Iterable<Spiel> findAll(Sort sort);

    @PreAuthorize("permitAll()")
    @Override
    Page<Spiel> findAll(Pageable pageable);
}
