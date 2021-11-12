package com.nicolas.politics.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Peronista extends Partido{

    @Column
    boolean populista;
}
