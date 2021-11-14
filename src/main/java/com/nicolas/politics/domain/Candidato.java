package com.nicolas.politics.domain;

import com.fasterxml.jackson.annotation.JsonView;
import com.nicolas.politics.errorHandling.UserException;
import com.nicolas.politics.serializer.View;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Candidato {

    @Column(length = 150) @JsonView(View.Zona.Grilla.class)
    String nombre;

    public Long id;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue @JsonView(View.Zona.Grilla.class)
    public Long getId() {
        return id;
    }

    Partido partido;

    @JsonView(View.Zona.Grilla.class)
    Integer votos = 0;

    List<Promesa> promesas = new ArrayList<>();

    List<String> opiniones = new ArrayList<>();

    public Candidato(String nombre, Partido partido, List<Promesa> promesas) {
        this.nombre = nombre;
        this.partido = partido;
        this.promesas = promesas;
    }

    public Candidato() {
    }

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

    public Integer getVotos() {
        return votos;
    }

    public void setVotos(Integer votos) {
        this.votos = votos;
    }

    @OrderColumn
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<Promesa> getPromesas() {
        return promesas;
    }

    public void setPromesas(List<Promesa> promesas) {
        this.promesas = promesas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @ElementCollection
    @OrderColumn
    public List<String> getOpiniones() {
        return opiniones;
    }

    public void setOpiniones(List<String> opiniones) {
        this.opiniones = opiniones;
    }

    @ManyToOne @JsonView(View.Zona.Grilla.class)
    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }
}
