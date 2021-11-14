package com.nicolas.politics;

import com.nicolas.politics.dao.CandidatoRepository;
import com.nicolas.politics.dao.PartidoRepository;
import com.nicolas.politics.dao.ZonaRepository;
import com.nicolas.politics.domain.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class PoliticsBootstrap implements InitializingBean {
    @Autowired
    ZonaRepository repoZonas;

    @Autowired
    PartidoRepository repoPartidos;

    @Autowired
    CandidatoRepository repoCandidatos;

    Partido frejuli;
    Partido perone;
    Partido prime;

    Candidato sosa;
    Candidato benitez;
    Candidato yapura;
    Candidato ramos;
    Candidato monti;
    Candidato rota;
    Candidato cafrune;

    Zona nacional;
    Zona springfield;

    private void initPartidos() {
        frejuli = new Peronista("FREJULI", 6000, true);
        frejuli = new Peronista("Perone", 5000, false);
        frejuli = new Preservativo("PRIME", 1200, LocalDate.parse("2009-06-16"));

        this.crearPartidos(frejuli);
        this.crearPartidos(perone);
        this.crearPartidos(prime);
    }

    private void  crearPartidos(Partido partido) {
        var partidoEnRepo = repoPartidos.findByNombre(partido.getNombre());
        if (partidoEnRepo == null) {
            repoPartidos.save(partido);
        } else {
            partido.id = partidoEnRepo.id;
            repoPartidos.save(partido);
        }
    }

    public void initCandidatos() {
        sosa = new Candidato("Julio sosa", frejuli, new ArrayList<>(Arrays.asList(new Promesa("Terminar con la inseguridad"), new Promesa("Aborto para unos, banderitas para otros"))));
        benitez = new Candidato("Myriam Benitez", frejuli, new ArrayList<>(List.of(new Promesa("Girar y girar hacia la libertad"))));
        yapura = new Candidato("Marcelo Yapura", frejuli, new ArrayList<>(Arrays.asList(new Promesa("Terminar con la pobreza"), new Promesa("Que todos los docentes de la UTN cobren en euros"))));
        ramos = new Candidato("Manuel Ramos", perone, new ArrayList<>(Arrays.asList(new Promesa("Terminar con la inseguridad"), new Promesa("Recuperar la confianza de los argentinos"))));
        monti = new Candidato("Yaco Monti", perone, new ArrayList<>(Arrays.asList(new Promesa("Terminar con la inseguridad"), new Promesa("Recuperar la dignidad"))));
        rota = new Candidato("Nino Rota", prime, new ArrayList<>(Arrays.asList(new Promesa("Ganarle a la pobreza"), new Promesa("Sacar el cepo a la moneda extranjera"), new Promesa("Eliminar el impuesto a las ganancias"))));
        cafrune = new Candidato("Yamila Cafrune", prime, new ArrayList<>(Arrays.asList(new Promesa("Terminar con Futbol para Todos"),new Promesa("Privatizar las empresas del estado"),new Promesa("Dolarizar la economia"))));

        this.crearCandidato(sosa);
        this.crearCandidato(benitez);
        this.crearCandidato(yapura);
        this.crearCandidato(ramos);
        this.crearCandidato(monti);
        this.crearCandidato(rota);
        this.crearCandidato(cafrune);
    }

    private void crearCandidato(Candidato candidato) {
        var candidatoEnElRepo = repoCandidatos.findByNombre(candidato.getNombre());
        if (candidatoEnElRepo != null) {
            candidato.id = candidatoEnElRepo.id;
        }
        repoCandidatos.save(candidato);
    }

    private void initZonas() {
        nacional = new Zona("Elecciones nacionales", new HashSet<>(Arrays.asList(sosa, benitez, ramos, rota)));
        nacional = new Zona("Springfield", new HashSet<>(Arrays.asList(yapura, monti, cafrune)));

        this.crearZona(nacional);
        this.crearZona(springfield);
    }

    private void crearZona(Zona zona) {
        var listaZonas = repoZonas.findByDescripcion(zona.getDescripcion());
        if (listaZonas.isEmpty()) {
            repoZonas.save(zona);
        } else {
            var zonaBD = listaZonas.get(0);
            zona.id = zonaBD.id;
            repoZonas.save(zona);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        initPartidos();
        initCandidatos();
        initZonas();
    }
}
