package com.nicolas.politics.domain;

import com.nicolas.politics.errorHandling.UserException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Promesa {

    LocalDate fecha;

    String accionPrometida;

    private Long id;

    public Promesa() {
    }

    public Promesa(String accionPrometida) {
        fecha = LocalDate.now();
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

    @Column
    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Column
    public String getAccionPrometida() {
        return accionPrometida;
    }

    public void setAccionPrometida(String accionPrometida) {
        this.accionPrometida = accionPrometida;
    }
}
