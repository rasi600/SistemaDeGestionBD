package com.mycompany.sistemadegestiohotelraul.model;

import java.time.LocalDate;

public class Factura {
    private int id_factura; 
    private int id_reserva;
    private LocalDate data_emissio;
    private String metode_pagament;
    private double base_imposable;
    private double iva;
    private double total;

    // Constructor
    public Factura(int id_reserva, LocalDate data_emissio, String metode_pagament, double base_imposable, double iva, double total) {
        this.id_reserva = id_reserva;
        this.data_emissio = data_emissio;
        this.metode_pagament = metode_pagament;
        this.base_imposable = base_imposable;
        this.iva = iva;
        this.total = total;
    }

    // Getters y setters
    public int getId_factura() {
        return id_factura;
    }

    public void setId_factura(int id_factura) {
        this.id_factura = id_factura;
    }

    public int getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(int id_reserva) {
        this.id_reserva = id_reserva;
    }

    public LocalDate getData_emissio() {
        return data_emissio;
    }

    public void setData_emissio(LocalDate data_emissio) {
        this.data_emissio = data_emissio;
    }

    public String getMetode_pagament() {
        return metode_pagament;
    }

    public void setMetode_pagament(String metode_pagament) {
        this.metode_pagament = metode_pagament;
    }

    public double getBase_imposable() {
        return base_imposable;
    }

    public void setBase_imposable(double base_imposable) {
        this.base_imposable = base_imposable;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Factura{" +
                "id_factura=" + id_factura +
                ", id_reserva=" + id_reserva +
                ", data_emissio=" + data_emissio +
                ", metode_pagament='" + metode_pagament + '\'' +
                ", base_imposable=" + base_imposable +
                ", iva=" + iva +
                ", total=" + total +
                '}';
    }
}
