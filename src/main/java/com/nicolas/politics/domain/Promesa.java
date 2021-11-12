package com.nicolas.politics.domain;

import com.nicolas.politics.errorHandling.UserException;

import java.time.LocalDate;

public class Promesa {
    LocalDate fecha;
    String accionPrometida;

    public Promesa() {
    }

    public Promesa(String accionPrometida) {
        this.accionPrometida = accionPrometida;
    }

    public void validar() {
        if (fecha == null) {
            throw new UserException("Debe ingresar fecha");
        }
        if (accionPrometida == null) {
            throw new UserException("Debe ingresar una acción en la promesa");
        }
    }
}
