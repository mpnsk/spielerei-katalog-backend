package de.spielerei_eichstaett.katalog.web;

import de.spielerei_eichstaett.katalog.spiel.Spiel;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import static org.springframework.data.rest.core.mapping.RepositoryDetectionStrategy.RepositoryDetectionStrategies.ANNOTATED;

@Configuration
public class RepoRestConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        config.setRepositoryDetectionStrategy(ANNOTATED);
//        TODO later we might normalize the json tree and get Spiel.Kategorie only by id
        config.exposeIdsFor(Spiel.Kategorie.class);
        config.exposeIdsFor(Spiel.class);
    }
}
