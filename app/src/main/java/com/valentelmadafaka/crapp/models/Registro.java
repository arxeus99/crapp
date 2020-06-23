package com.valentelmadafaka.crapp.models;


public class Registro {

    private int puntuacionFinal;
    private int bristolPuntuacion;
    private String bristolTable;
    private int colorPuntuacion;
    private String color;
    private String diagnosticoColor;
    private String diagnosticoGases;
    private String diagnosticoDolor;
    private String fecha;
    private String semana;
    private String dia;
    private int mes;
    private int año;

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public String getSemana() {
        return semana;
    }

    public void setSemana(String semana) {
        this.semana = semana;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDiagnosticoGases() {
        return diagnosticoGases;
    }

    public void setDiagnosticoGases(String diagnosticoGases) {
        this.diagnosticoGases = diagnosticoGases;
    }

    public String getDiagnosticoDolor() {
        return diagnosticoDolor;
    }

    public void setDiagnosticoDolor(String diagnosticoDolor) {
        this.diagnosticoDolor = diagnosticoDolor;
    }

    public int getBristolPuntuacion() {
        return bristolPuntuacion;
    }

    public void setBristolPuntuacion(int bristolPuntuacion) {
        this.bristolPuntuacion = bristolPuntuacion;
    }

    public int getColorPuntuacion() {
        return colorPuntuacion;
    }

    public void setColorPuntuacion(int colorPuntuacion) {
        this.colorPuntuacion = colorPuntuacion;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDiagnosticoColor() {
        return diagnosticoColor;
    }

    public void setDiagnosticoColor(String diagnosticoColor) {
        this.diagnosticoColor = diagnosticoColor;
    }

    public int getBristol() {
        return bristolPuntuacion;
    }

    public void setBristol(int bristolPuntuacion) {
        this.bristolPuntuacion = bristolPuntuacion;
    }

    public String getBristolTable() {
        return bristolTable;
    }

    public void setBristolTable(String bristolTable) {
        this.bristolTable = bristolTable;
    }

    public int getPuntuacionFinal(){return this.puntuacionFinal;}

    public void setPuntuacionFinal(int puntuacionFinal) { this.puntuacionFinal = puntuacionFinal; }
}
