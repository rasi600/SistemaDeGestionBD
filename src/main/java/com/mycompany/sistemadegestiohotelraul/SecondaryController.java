package com.mycompany.sistemadegestiohotelraul;

import com.mycompany.sistemadegestiohotelraul.model.Connexio;
import com.mycompany.sistemadegestiohotelraul.model.Factura;
import com.mycompany.sistemadegestiohotelraul.model.Habitacio;
import com.mycompany.sistemadegestiohotelraul.model.Model;
import com.mycompany.sistemadegestiohotelraul.model.Persona;
import com.mycompany.sistemadegestiohotelraul.model.Reserva;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SecondaryController {
private final Model model = new Model();
    @FXML
    private void VentanaPrimary() throws IOException {
        App.setRoot("primary");
    }
    @FXML
    private void VentanaTasca() throws IOException {
        App.setRoot("tasca");
    }
        
    @FXML
    private TextField id_persona, id_habitacio, preu_total, base_imposable, iva, total;
    @FXML
    private DatePicker data_reserva, data_inici, data_fi, data_emissio;
    @FXML
    private ComboBox<String> tipus_reserva, tipus_iva, metode_pagament;
    @FXML
    private Button crearReservaYFactura, anrereButton;

    @FXML
    public void initialize() {
        // Inicializar ComboBoxes con valores
        tipus_reserva.getItems().addAll("Estandard", "VIP", "Garantida");
        tipus_iva.getItems().addAll("10%", "21%");
        metode_pagament.getItems().addAll("Efectiu", "Targeta");
    }

    @FXML
    private void CrearReservaYFactura() {
        try {
            // Obtener valores de los formularios
            int idPersona = Integer.parseInt(id_persona.getText());
            int idHabitacion = Integer.parseInt(id_habitacio.getText());
            String tipusReserva = tipus_reserva.getValue();
            double tipusIVA = Double.parseDouble(tipus_iva.getValue().replace("%", ""));
            double preuTotal = Double.parseDouble(preu_total.getText());

            // Fechas
            java.sql.Date fechaReserva = java.sql.Date.valueOf(data_reserva.getValue());
            java.sql.Date fechaInicio = java.sql.Date.valueOf(data_inici.getValue());
            java.sql.Date fechaFin = java.sql.Date.valueOf(data_fi.getValue());

            // Factura
            java.sql.Date fechaEmision = java.sql.Date.valueOf(data_emissio.getValue());
            String metodoPago = metode_pagament.getValue();
            double baseImponible = Double.parseDouble(base_imposable.getText());
            double ivaValue = Double.parseDouble(iva.getText());
            double totalValue = Double.parseDouble(total.getText());

            // Crear instancias de objetos
            Persona persona = new Persona(idPersona, "", "", "", "", fechaReserva, "", "");
            Habitacio habitacion = new Habitacio(idHabitacion, "", "", 0, 0, 0, "", "");

            Reserva reserva = new Reserva(fechaReserva, fechaInicio, fechaFin, tipusReserva, tipusIVA, preuTotal);
            Factura factura = new Factura(1, fechaEmision, metodoPago, baseImponible, ivaValue, totalValue);

            // Llamada al modelo
            Model model = new Model();
            model.crearReservaYFactura(reserva, persona, habitacion, factura);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void volverAtras() {
        Stage stage = (Stage) anrereButton.getScene().getWindow();
        stage.close(); // Cierra la ventana actual para volver a la anterior
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    private void limpiarCampos() {
        id_persona.clear();
        id_habitacio.clear();
        data_reserva.setValue(null);
        data_inici.setValue(null);
        data_fi.setValue(null);
        tipus_reserva.setValue(null);
        tipus_iva.setValue(null);
        preu_total.clear();
    }
}