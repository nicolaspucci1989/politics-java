package com.nicolas.politics.domain;

import com.nicolas.politics.errorHandling.UserException;

import java.util.ArrayList;
import java.util.List;

public class Candidato {

    String nombre;
    Partido partido;
    Integer votos = 0;
    List<Promesa> promesas = new ArrayList<>();
    List<String> opiniones = new ArrayList<>();

    public void validar() {
        if (nombre == null) {
            throw new UserException("Debe ingresar descripcion");
        }
        if (partido == null) {
            throw new UserException("El candidato debe estar participando en un partido politico");
        }
    }

    @Override
    public String toString() {
        return "Candidato{" +
                "nombre='" + nombre + '\'' +
                '}';
    }

    public void agregarPromesa(String nuevaPromesa) {
        promesas.add(new Promesa(nuevaPromesa));
    }

    public boolean tienePromesas() {
        return !promesas.isEmpty();
    }

    public void agregarOpinioin(String opinion) {
        opiniones.add(opinion);
    }

    public boolean tieneVotos() {
       return votos > 0;
    }

    public void votar() {
        votos++;
    }

    public void reset() {
        promesas = new ArrayList<>();
        opiniones = new ArrayList<>();
    }
}
