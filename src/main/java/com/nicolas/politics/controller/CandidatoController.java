package com.nicolas.politics.controller;

import com.nicolas.politics.domain.Candidato;
import com.nicolas.politics.service.CandiatoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
public class CandidatoController {

    @Autowired
    CandiatoService candiatoService;

    @GetMapping("/candidatos/{id}")
    @ApiOperation("Permite conocer los datos de una persona candidata en base al identificador.")
    public Candidato getCandidato(@PathVariable Long id) {
        return candiatoService.buscarPorId(id);
    }

    @PutMapping("/candidatos/{id}")
    @ApiOperation("Permite actualizar las promesas o los votos de una persona candidata.")
    public HttpEntity<String> actualizarCandidate(@RequestBody Candidato candidateNuevo, @PathVariable Long id) {
        candiatoService.actualizar(candidateNuevo, id);
        return ResponseEntity.ok().body("La persona candidata fue actualizada correctamente");
    }
}
