package org.acme;

import javax.persistence.Cacheable;
import javax.persistence.Entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;

@Entity
@Cacheable
public class Fruit extends PanacheEntity {

    public String name;

    public Fruit() {}
    public Fruit(String name) {
        this.name = name;
    }
    
}
