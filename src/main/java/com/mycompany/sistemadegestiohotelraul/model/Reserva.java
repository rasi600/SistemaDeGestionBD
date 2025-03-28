package com.mycompany.sistemadegestiohotelraul.model;

import java.time.LocalDate;

public class Reserva {
    private int id_reserva;
    private int id_client;
    private int id_habitacio;
    private LocalDate data_reserva;  // Cambiar Date por LocalDate
    private LocalDate data_inici;   // Cambiar Date por LocalDate
    private LocalDate data_fi;      // Cambiar Date por LocalDate
    private String tipus_reserva;
    private double tipus_IVA;
    private double preu_total_reserva;

    // Constructor actualizado para usar LocalDate
    public Reserva(int id_reserva, int id_client, int id_habitacio, LocalDate data_reserva, LocalDate data_inici, LocalDate data_fi, String tipus_reserva, double tipus_IVA, double preu_total_reserva) {
        this.id_reserva = id_reserva;
        this.id_client = id_client;
        this.id_habitacio = id_habitacio;
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

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getId_habitacio() {
        return id_habitacio;
    }

    public void setId_habitacio(int id_habitacio) {
        this.id_habitacio = id_habitacio;
    }

    public LocalDate getData_reserva() {
        return data_reserva;
    }

    public void setData_reserva(LocalDate data_reserva) {
        this.data_reserva = data_reserva;
    }

    public LocalDate getData_inici() {
        return data_inici;
    }

    public void setData_inici(LocalDate data_inici) {
        this.data_inici = data_inici;
    }

    public LocalDate getData_fi() {
        return data_fi;
    }

    public void setData_fi(LocalDate data_fi) {
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
