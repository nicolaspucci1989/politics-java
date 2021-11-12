package com.nicolas.politics.domain;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.nicolas.politics.errorHandling.UserException;

import javax.persistence.*;

@Entity
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Peronista.class, name = "PJ"),
        @JsonSubTypes.Type(value = Preservativo.class, name = "PRE")
})
@Inheritance(strategy = InheritanceType.JOINED)
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
