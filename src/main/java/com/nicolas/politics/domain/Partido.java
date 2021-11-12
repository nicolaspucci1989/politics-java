package com.nicolas.politics.domain;

import com.nicolas.politics.errorHandling.UserException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Partido {
    @Column(length = 150)
    String nombre;

    @Column
    Integer afiliados;

    private Long id;

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

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }
}
