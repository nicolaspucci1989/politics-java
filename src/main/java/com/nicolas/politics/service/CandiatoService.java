package com.nicolas.politics.service;

import com.nicolas.politics.dao.CandidatoRepository;
import com.nicolas.politics.domain.Candidato;
import com.nicolas.politics.errorHandling.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandiatoService {
    @Autowired
    CandidatoRepository candidatoRepository;

    public void actualizar(Candidato candidatoNuevo, Long id) {
        candidatoRepository
                .findById(id)
                .map(candidato -> {
                    if(!candidatoNuevo.tienePromesas()) {
                        candidato.setPromesas(candidatoNuevo.getPromesas());
                    }
                    if(candidatoNuevo.tieneVotos()) {
                        candidato.setVotos(candidatoNuevo.getVotos());
                    }
                    return candidatoRepository.save(candidato);
                })
                .orElseThrow(() -> new NotFoundException("La persona candidata con identificador " + id + " no existe"));
    }

    public void buscarPorId(Long id) {
        this
                .candidatoRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("La persona candidata con identificador " + id + " no existe"));
    }
}
