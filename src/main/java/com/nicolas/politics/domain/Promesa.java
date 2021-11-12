package com.nicolas.politics.domain;

import com.nicolas.politics.errorHandling.UserException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Promesa {

    @Column
    LocalDate fecha;

    @Column
    String accionPrometida;
    private Long id;

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

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }
}
