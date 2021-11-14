package com.nicolas.politics.domain;

import com.nicolas.politics.errorHandling.UserException;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Preservativo extends Partido{

    @Column
    LocalDate fechaCreacion;

    public Preservativo(String nombre, Integer afiliados, LocalDate fechaCreacion) {
        super(nombre, afiliados);
        this.fechaCreacion = fechaCreacion;
    }

    public Preservativo() {
        super();
    }

    @Override
    public void validar() {
        super.validar();
        if (fechaCreacion == null) {
            throw new UserException("Debe ingresar fecha de creación");
        }
        if (fechaCreacion.compareTo(LocalDate.now()) > 0) {
            throw new UserException("La fecha de creación debe ser anterior a la de hoy");
        }
    }
}
