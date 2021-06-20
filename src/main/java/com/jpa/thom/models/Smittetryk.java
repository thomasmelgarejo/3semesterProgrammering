package com.jpa.thom.models;

import javax.persistence.Entity;
import javax.persistence.Id;


public class Smittetryk {


    private Long kommuneId;
    private String kommuneNavn;
    private int countKommuner;
    private double sumSmitteTryk;
    private double avgSmitteTryk;

    public Smittetryk(Long kommuneId, String kommuneNavn, int countKommuner, double sumSmitteTryk, double avgSmitteTryk) {
        this.kommuneId = kommuneId;
        this.kommuneNavn = kommuneNavn;
        this.countKommuner = countKommuner;
        this.sumSmitteTryk = sumSmitteTryk;
        this.avgSmitteTryk = avgSmitteTryk;
    }

    public Long getKommuneId() {
        return kommuneId;
    }

    public void setKommuneId(Long kommuneId) {
        this.kommuneId = kommuneId;
    }

    public String getKommuneNavn() {
        return kommuneNavn;
    }

    public void setKommuneNavn(String kommuneNavn) {
        this.kommuneNavn = kommuneNavn;
    }

    public int getCountKommuner() {
        return countKommuner;
    }

    public void setCountKommuner(int countKommuner) {
        this.countKommuner = countKommuner;
    }

    public double getSumSmitteTryk() {
        return sumSmitteTryk;
    }

    public void setSumSmitteTryk(double sumSmitteTryk) {
        this.sumSmitteTryk = sumSmitteTryk;
    }

    public double getAvgSmitteTryk() {
        return avgSmitteTryk;
    }

    public void setAvgSmitteTryk(double avgSmitteTryk) {
        this.avgSmitteTryk = avgSmitteTryk;
    }

    @Override
    public String toString() {
        return "Smittetryk{" +
                "kommuneId=" + kommuneId +
                ", kommuneNavn='" + kommuneNavn + '\'' +
                ", countKommuner=" + countKommuner +
                ", sumSmitteTryk=" + sumSmitteTryk +
                ", avgSmitteTryk=" + avgSmitteTryk +
                '}';
    }
}
