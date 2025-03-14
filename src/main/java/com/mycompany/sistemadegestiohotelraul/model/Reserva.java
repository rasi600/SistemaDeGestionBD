/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemadegestiohotelraul.model;

import java.util.Date;

/**
 *
 * @author alumne
 */
public class Reserva {
    private int id_reserva;
    private Date data_reserva;
    private Date data_inici;
    private Date data_fi;
    private String tipus_reserva;
    private double tipus_IVA;
    private double preu_total_reserva;

    // Constructor SIN id_reserva (se genera automáticamente)
    public Reserva(Date data_reserva, Date data_inici, Date data_fi, String tipus_reserva, double tipus_IVA, double preu_total_reserva) {
        this.data_reserva = data_reserva;
        this.data_inici = data_inici;
        this.data_fi = data_fi;
        this.tipus_reserva = tipus_reserva;
        this.tipus_IVA = tipus_IVA;
        this.preu_total_reserva = preu_total_reserva;
    }

    public int getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(int id_reserva) {
        this.id_reserva = id_reserva;
    }

    public Date getData_reserva() {
        return data_reserva;
    }

    public void setData_reserva(Date data_reserva) {
        this.data_reserva = data_reserva;
    }

    public Date getData_inici() {
        return data_inici;
    }

    public void setData_inici(Date data_inici) {
        this.data_inici = data_inici;
    }

    public Date getData_fi() {
        return data_fi;
    }

    public void setData_fi(Date data_fi) {
        this.data_fi = data_fi;
    }

    public String getTipus_reserva() {
        return tipus_reserva;
    }

    public void setTipus_reserva(String tipus_reserva) {
        this.tipus_reserva = tipus_reserva;
    }

    public double getTipus_IVA() {
        return tipus_IVA;
    }

    public void setTipus_IVA(double tipus_IVA) {
        this.tipus_IVA = tipus_IVA;
    }

    public double getPreu_total_reserva() {
        return preu_total_reserva;
    }

    public void setPreu_total_reserva(double preu_total_reserva) {
        this.preu_total_reserva = preu_total_reserva;
    }

    
}

