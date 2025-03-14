package com.mycompany.sistemadegestiohotelraul.model;

import java.sql.Date;

public class Persona {
    private int id;
    private String nom;
    private String cognom;
    private String adreça;
    private String dni;
    private Date dataNaixement;
    private String telefon;
    private String email;

    // Constructor
    public Persona(int id, String nom, String cognom, String adreça, String dni, Date dataNaixement, String telefon, String email) {
        this.id = id;
        this.nom = nom;
        this.cognom = cognom;
        this.adreça = adreça;
        this.dni = dni;
        this.dataNaixement = dataNaixement;
        this.telefon = telefon;
        this.email = email;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognom() {
        return cognom;
    }

    public void setCognom(String cognom) {
        this.cognom = cognom;
    }

    public String getAdreça() {
        return adreça;
    }

    public void setAdreça(String adreça) {
        this.adreça = adreça;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Date getDataNaixement() {
        return dataNaixement;
    }

    public void setDataNaixement(Date dataNaixement) {
        this.dataNaixement = dataNaixement;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
