package com.nicolas.politics.domain;

import com.fasterxml.jackson.annotation.JsonView;
import com.nicolas.politics.errorHandling.UserException;
import com.nicolas.politics.serializer.View;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Zona {

    String descripcion;

    Set<Candidato> candidatos = new HashSet<>();

    public Zona(String descripcion, Set<Candidato> candidatos) {
        this.descripcion = descripcion;
        this.candidatos = candidatos;
    }

    public Long id;

    public Zona() {

    }

    public String getDescripcion() {
        return descripcion;
    }

    @Column(length = 150)
    @JsonView({View.Zona.Plana.class, View.Zona.Grilla.class})
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

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

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    @JsonView({View.Zona.Plana.class, View.Zona.Grilla.class})
    public Long getId() {
        return id;
    }


    @OneToMany(fetch = FetchType.LAZY)
    @JsonView(View.Zona.Grilla.class)
    public Set<Candidato> getCandidatos() {
        return candidatos;
    }

    public void setCandidatos(Set<Candidato> candidatos) {
        this.candidatos = candidatos;
    }
}
