package com.jpa.thom.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "kommune")
public class Kommune {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String navn;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kommune")
    private Set<Sogn> sogns;

    public Kommune() {
    }

    public Kommune(String navn) {
        this.navn = navn;
    }

    public Kommune(Long id, String navn) {
        this.id = id;
        this.navn = navn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public Set<Sogn> getSogns() {
        return sogns;
    }

    public void setSogns(Set<Sogn> sogns) {
        this.sogns = sogns;
    }
}
