package com.nicolas.politics.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.nicolas.politics.domain.Zona;
import com.nicolas.politics.serializer.View;
import com.nicolas.politics.service.ZonaService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="*", methods={RequestMethod.GET})
public class ZonaController {

    @Autowired
    ZonaService zonaService;

    @GetMapping(value="/zonas")
    @JsonView(value= View.Zona.Plana.class)
    @ApiOperation("Trae la información de las zonas (sin sus relaciones, pensado para una selección inicial).")
    public List<Zona> getZonas() {
        return zonaService.todasLasZonas();
    }

    @GetMapping(value="/zonas/{id}")
    @JsonView(value=View.Zona.Grilla.class)
    @ApiOperation("Permite traer la información de una zona, con las personas candidatas y las intenciones de voto incluidas.")
    public Zona getZona(@PathVariable Long id) {
        return zonaService.buscarPorId(id);
    }
}
