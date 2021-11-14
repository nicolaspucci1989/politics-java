package com.nicolas.politics.dao;

import com.nicolas.politics.domain.Zona;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ZonaRepository extends CrudRepository<Zona, Long> {
    List<Zona> findByDescripcion(String descripcion);

    @EntityGraph(attributePaths= {
            "candidatos.promesas",
            "candidatos.partido",
            "candidatos.opiniones"
    })
    @Override
    Optional<Zona> findById(Long aLong);
}
