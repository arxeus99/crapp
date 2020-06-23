package com.valentelmadafaka.crapp.models;

import java.io.Serializable;

public class RegistroTemporal implements Serializable {

    private int bristol;
    private int bristolTable;
    private String color;
    private int puntosDolor;
    private int puntosGases;
    private int puntosFlota;



    public int getBristolTable() {
        return bristolTable;
    }

    public void setBristolTable(int bristolTable) {
        this.bristolTable = bristolTable;
    }

    public int getPuntosDolor() {
        return puntosDolor;
    }

    public void setPuntosDolor(int puntosDolor) {
        this.puntosDolor = puntosDolor;
    }

    public int getPuntosGases() {
        return puntosGases;
    }

    public void setPuntosGases(int puntosGases) {
        this.puntosGases = puntosGases;
    }

    public int getPuntosFlota() {
        return puntosFlota;
    }

    public void setPuntosFlota(int puntosFlota) {
        this.puntosFlota = puntosFlota;
    }

    public int getBristol() {
        return bristol;
    }

    public void setBristol(int bristol) {
        this.bristol = bristol;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
