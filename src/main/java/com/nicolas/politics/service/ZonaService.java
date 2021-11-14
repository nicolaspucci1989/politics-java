package com.nicolas.politics.service;

import com.nicolas.politics.dao.ZonaRepository;
import com.nicolas.politics.domain.Zona;
import com.nicolas.politics.errorHandling.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZonaService {

    @Autowired
    ZonaRepository zonaRepository;

    public Zona buscarPorId(Long id) {
        return zonaRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("La zona con identificador " + id + " no existe"));
    }
}
