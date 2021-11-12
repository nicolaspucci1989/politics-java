package com.nicolas.politics.domain;

import com.nicolas.politics.errorHandling.UserException;

public class Partido {
    String nombre;
    int afiliados;

    public void validar() {

        if (nombre == null) {
            throw new UserException("Debe ingresar nombre");
        }
        if (afiliados < 1000) {
            throw new UserException("El partido no tiene suficientes afiliados");
        }
    }

    @Override
    public String toString() {
        return "Partido{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
