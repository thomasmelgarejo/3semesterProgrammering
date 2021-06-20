package com.jpa.thom.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Sogn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int sogneKode;
    private String sogneNavn;
    private double smittetryk;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date nedlukDato;
    private boolean checked;

    @ManyToOne()
    @JsonBackReference
    private Kommune kommune;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSogneKode() {
        return sogneKode;
    }

    public void setSogneKode(int sogneKode) {
        this.sogneKode = sogneKode;
    }

    public String getSogneNavn() {
        return sogneNavn;
    }

    public void setSogneNavn(String sogneNavn) {
        this.sogneNavn = sogneNavn;
    }

    public double getSmittetryk() {
        return smittetryk;
    }

    public void setSmittetryk(double smittetryk) {
        this.smittetryk = smittetryk;
    }

    public Date getNedlukDato() {
        return nedlukDato;
    }

    public void setNedlukDato(Date nedlukDato) {
        this.nedlukDato = nedlukDato;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Kommune getKommune() {
        return kommune;
    }

    public void setKommune(Kommune kommune) {
        this.kommune = kommune;
    }
}
