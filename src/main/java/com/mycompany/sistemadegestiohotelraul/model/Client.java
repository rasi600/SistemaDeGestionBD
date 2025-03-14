package com.mycompany.sistemadegestiohotelraul.model;

import java.sql.Date;

public class Client extends Persona {
    private Date data_registre;  // java.sql.Date para fecha de registro
    private String tipus_client;
    private int targeta_credit;

    // Constructor con java.sql.Date
    public Client(int id, String nom, String cognom, String adreça, String dni, Date dataNaixement, String telefon, String email,
                  Date data_registre, String tipus_client, int targeta_credit) {
        super(id, nom, cognom, adreça, dni, dataNaixement, telefon, email);  // Llamada al constructor de la clase Persona
        this.data_registre = data_registre;
        this.tipus_client = tipus_client;
        this.targeta_credit = targeta_credit;
    }

    // Getter y setter para data_registre usando java.sql.Date
    public Date getData_registre() {
        return data_registre;
    }

    public void setData_registre(Date data_registre) {
        this.data_registre = data_registre;
    }

    // Getter y setter para tipus_client
    public String getTipus_client() {
        return tipus_client;
    }

    public void setTipus_client(String tipus_client) {
        this.tipus_client = tipus_client;
    }

    // Getter y setter para targeta_credit
    public int getTargeta_credit() {
        return targeta_credit;
    }

    public void setTargeta_credit(int targeta_credit) {
        this.targeta_credit = targeta_credit;
    }
}
