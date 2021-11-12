package com.nicolas.politics.domain;

import com.fasterxml.jackson.annotation.JsonView;
import com.nicolas.politics.errorHandling.UserException;
import com.nicolas.politics.serializer.View;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Candidato {
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue @JsonView(View.Zona.Grilla.class)
    public Long getId() {
        return id;
    }

    @Column(length = 150) @JsonView(View.Zona.Grilla.class)
    String nombre;

    @ManyToOne @JsonView(View.Zona.Grilla.class)
    Partido partido;


    @JsonView(View.Zona.Grilla.class)
    Integer votos = 0;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OrderColumn
    List<Promesa> promesas = new ArrayList<>();

    @ElementCollection
    @OrderColumn
    List<String> opiniones = new ArrayList<>();

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

}
