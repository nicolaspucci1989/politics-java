package com.nicolas.politics.domain;

import com.nicolas.politics.errorHandling.UserException;

import java.util.HashSet;
import java.util.Set;

public class Zona {
    String descripcion;
    Set<Candidato> candidatos = new HashSet<>();

    public void validar() {
        if (descripcion == null) {
            throw new UserException("Debe ingresar descripcion");
        }
        if (candidatos.isEmpty()) {
            throw new UserException("Debe haber al menos un candidato en la zona");
        }
    }

    @Override
    public String toString() {
        return "Zona{" +
                "descripcion='" + descripcion + '\'' +
                '}';
    }
}
