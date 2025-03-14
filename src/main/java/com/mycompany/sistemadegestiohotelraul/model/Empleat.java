package com.mycompany.sistemadegestiohotelraul.model;

import java.sql.Date;  // Usamos java.sql.Date para las fechas

public class Empleat extends Persona {
    private String llocFeina;
    private Date dataContratacio;  // Cambiado a java.sql.Date
    private double salariBrut;
    private String estatLaboral;

    // Constructor con java.sql.Date
    public Empleat(int id, String nom, String cognom, String adreça, String dni, Date dataNaixement, String telefon, String email,
                   String llocFeina, Date dataContratacio, double salariBrut, String estatLaboral) {
        super(id, nom, cognom, adreça, dni, dataNaixement, telefon, email);  // Llamamos al constructor de Persona
        this.llocFeina = llocFeina;
        this.dataContratacio = dataContratacio;
        this.salariBrut = salariBrut;
        this.estatLaboral = estatLaboral;
    }

    // Getter y setter para llocFeina
    public String getLlocFeina() {
        return llocFeina;
    }

    public void setLlocFeina(String llocFeina) {
        this.llocFeina = llocFeina;
    }

    // Getter y setter para dataContratacio
    public Date getDataContratacio() {
        return dataContratacio;
    }

    public void setDataContratacio(Date dataContratacio) {
        this.dataContratacio = dataContratacio;
    }

    // Getter y setter para salariBrut
    public double getSalariBrut() {
        return salariBrut;
    }

    public void setSalariBrut(double salariBrut) {
        this.salariBrut = salariBrut;
    }

    // Getter y setter para estatLaboral
    public String getEstatLaboral() {
        return estatLaboral;
    }

    public void setEstatLaboral(String estatLaboral) {
        this.estatLaboral = estatLaboral;
    }
}
