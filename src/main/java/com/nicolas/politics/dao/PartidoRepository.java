package com.nicolas.politics.dao;

import com.nicolas.politics.domain.Partido;
import org.springframework.data.repository.CrudRepository;

public interface PartidoRepository extends CrudRepository<Partido, Long> {
    Partido findByNombre(String nombre);
}
