package com.nicolas.politics.dao;

import com.nicolas.politics.domain.Candidato;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CandidatoRepository extends CrudRepository<Candidato, Long> {

    Candidato findByNombre(String nombre);

    @EntityGraph(attributePaths = {"partido", "promesas", "opiniones"})
    @Override
    Optional<Candidato> findById(Long id);
}
