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
public class Factura {
    private int id_factura;
    private Date data_emissio;
    private String metode_pagament;
    private Double base_imposable;
    private Double iva;
    private Double total;

    public Factura(int id_factura, Date data_emissio, String metode_pagament, Double base_imposable, Double iva, Double total) {
        this.id_factura = id_factura;
        this.data_emissio = data_emissio;
        this.metode_pagament = metode_pagament;
        this.base_imposable = base_imposable;
        this.iva = iva;
        this.total = total;
    }

    public int getId_factura() {
        return id_factura;
    }

    public void setId_factura(int id_factura) {
        this.id_factura = id_factura;
    }

    public Date getData_emissio() {
        return data_emissio;
    }

    public void setData_emissio(Date data_emissio) {
        this.data_emissio = data_emissio;
    }

    public String getMetode_pagament() {
        return metode_pagament;
    }

    public void setMetode_pagament(String metode_pagament) {
        this.metode_pagament = metode_pagament;
    }

    public Double getBase_imposable() {
        return base_imposable;
    }

    public void setBase_imposable(Double base_imposable) {
        this.base_imposable = base_imposable;
    }

    public Double getIva() {
        return iva;
    }

    public void setIva(Double iva) {
        this.iva = iva;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Factura{" + "id_factura=" + id_factura + ", data_emissio=" + data_emissio + ", metode_pagament=" + metode_pagament + ", base_imposable=" + base_imposable + ", iva=" + iva + ", total=" + total + '}';
    }

    
    
    
}