package com.mycompany.sistemadegestiohotelraul.model;
import java.sql.Date;

public class Tasca {
    private int id_tasca;
    private String descripcio;
    private Date data_creacio;  // java.sql.Date
    private Date data_execucio; // java.sql.Date
    private String estat;

    // Constructor
    public Tasca(int id_tasca, String descripcio, Date data_creacio, Date data_execucio, String estat) {
        this.id_tasca = id_tasca;
        this.descripcio = descripcio;
        this.data_creacio = data_creacio;
        this.data_execucio = data_execucio;
        this.estat = estat;
    }

    // Getters
    public int getId_tasca() {
        return id_tasca;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public Date getData_creacio() {
        return data_creacio;
    }

    public Date getData_execucio() {
        return data_execucio;
    }

    public String getEstat() {
        return estat;
    }
}
