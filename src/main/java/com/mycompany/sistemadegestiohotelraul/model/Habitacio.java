package com.mycompany.sistemadegestiohotelraul.model;

public class Habitacio {
    private int id_habitacio;
    private String numero_habitacio;  // Cambié a String porque en la base de datos es VARCHAR
    private String tipus;
    private int capacitat;
    private double preu_nit_AD;
    private double preu_nit_MP;
    private String estat;
    private String descripcio;

    // Constructor corregido
    public Habitacio(int id_habitacio, String numero_habitacio, String tipus, int capacitat, double preu_nit_AD, double preu_nit_MP, String estat, String descripcio) {
        this.id_habitacio = id_habitacio;
        this.numero_habitacio = numero_habitacio;
        this.tipus = tipus;
        this.capacitat = capacitat;
        this.preu_nit_AD = preu_nit_AD;
        this.preu_nit_MP = preu_nit_MP;
        this.estat = estat;
        this.descripcio = descripcio;
    }

    // Getters y setters
    public int getId_habitacio() {
        return id_habitacio;
    }

    public void setId_habitacio(int id_habitacio) {  // Arreglé el nombre del parámetro
        this.id_habitacio = id_habitacio;
    }

    public String getNumero_habitacio() {
        return numero_habitacio;
    }

    public void setNumero_habitacio(String numero_habitacio) {  // Arreglé el tipo a String
        this.numero_habitacio = numero_habitacio;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    public int getCapacitat() {
        return capacitat;
    }

    public void setCapacitat(int capacitat) {
        this.capacitat = capacitat;
    }

    public double getPreu_nit_AD() {
        return preu_nit_AD;
    }

    public void setPreu_nit_AD(double preu_nit_AD) {
        this.preu_nit_AD = preu_nit_AD;
    }

    public double getPreu_nit_MP() {
        return preu_nit_MP;
    }

    public void setPreu_nit_MP(double preu_nit_MP) {
        this.preu_nit_MP = preu_nit_MP;
    }

    public String getEstat() {
        return estat;
    }

    public void setEstat(String estat) {
        this.estat = estat;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    @Override
    public String toString() {
        return "Habitacio{" + 
               "id_habitacio=" + id_habitacio + 
               ", numero_habitacio='" + numero_habitacio + '\'' + 
               ", tipus='" + tipus + '\'' + 
               ", capacitat=" + capacitat + 
               ", preu_nit_AD=" + preu_nit_AD + 
               ", preu_nit_MP=" + preu_nit_MP + 
               ", estat='" + estat + '\'' + 
               ", descripcio='" + descripcio + '\'' + 
               '}';
    }
}
