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
import java.sql.Date;
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
    private TextField id_reserva, id_persona, id_habitacio, preu_total, base_imposable, iva, total;
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

 // Método para crear la reserva
@FXML
private void CrearReserva() {
    try {
        // Obtener los valores de los campos del formulario
        String idReservaText = id_reserva.getText();
        String idPersonaText = id_persona.getText();
        String idHabitacioText = id_habitacio.getText();
        String preuTotalText = preu_total.getText();
        String tipusIvaText = tipus_iva.getValue();

        // Mostrar los valores para depuración
        System.out.println("id_reserva: " + idReservaText);
        System.out.println("id_persona: " + idPersonaText);
        System.out.println("id_habitacio: " + idHabitacioText);
        System.out.println("preu_total: " + preuTotalText);
        System.out.println("tipus_iva: " + tipusIvaText);

        // Comprobar si los campos no están vacíos y son válidos
        if (idReservaText.isEmpty() || idPersonaText.isEmpty() || idHabitacioText.isEmpty() || preuTotalText.isEmpty() || tipusIvaText == null) {
            System.out.println("Error: Algunos campos están vacíos.");
            return;  // Salir del método si hay campos vacíos
        }

        // Convertir los campos a los tipos correspondientes
        int idReserva = Integer.parseInt(idReservaText);  // ID de la reserva
        int idPersona = Integer.parseInt(idPersonaText);  // ID de la persona
        int idHabitacio = Integer.parseInt(idHabitacioText);  // ID de la habitación
        LocalDate dataReserva = data_reserva.getValue();  // Fecha de reserva
        LocalDate dataInici = data_inici.getValue();  // Fecha de inicio
        LocalDate dataFi = data_fi.getValue();  // Fecha de fin
        String tipusReserva = tipus_reserva.getValue();  // Tipo de reserva
        double tipusIVA = Double.parseDouble(tipusIvaText);  // Tipo de IVA
        double preuTotal = Double.parseDouble(preuTotalText);  // Precio total

        // Convertir LocalDate a java.sql.Date
        Date sqlDataReserva = Date.valueOf(dataReserva);  // Conversión de LocalDate a SQL Date
        Date sqlDataInici = Date.valueOf(dataInici);  // Conversión de LocalDate a SQL Date
        Date sqlDataFi = Date.valueOf(dataFi);  // Conversión de LocalDate a SQL Date

        // Crear el objeto Reserva con los valores convertidos
        Reserva reserva = new Reserva(idReserva, idPersona, idHabitacio, sqlDataReserva, sqlDataInici, sqlDataFi, tipusReserva, tipusIVA, preuTotal);

        // Imprimir para ver los datos
        System.out.println("Reserva creada: " + reserva.toString());

        // Aquí puedes llamar al modelo para guardar la reserva en la base de datos.
        // Ejemplo: modeloReserva.crearReserva(reserva);
        model.crearReserva(reserva, idPersona, idHabitacio, idReserva);

        System.out.println("Reserva creada con éxito!");

    } catch (NumberFormatException e) {
        // Si hay un error con la conversión de datos numéricos
        System.out.println("Error al convertir los datos numéricos. Verifica que los campos contengan solo números.");
    } catch (Exception e) {
        // Para cualquier otro error
        e.printStackTrace();
    }
}


    
    // Método para crear la factura asociada a la reserva
     @FXML
    private void CrearFactura() {
        try {
            // Obtener los valores de los campos
            int idReserva = Integer.parseInt(id_reserva.getText());  // ID de la reserva existente
            LocalDate dataEmissio = data_emissio.getValue();  // Fecha de emisión
            String metodePagament = metode_pagament.getValue();  // Método de pago
            double baseImposable = Double.parseDouble(base_imposable.getText());  // Base imponible
            double ivaValor = Double.parseDouble(iva.getText());  // IVA
            double totalFactura = Double.parseDouble(total.getText());  // Total de la factura

            // Crear una nueva factura con los datos proporcionados
            Factura factura = new Factura(idReserva, dataEmissio, metodePagament, baseImposable, ivaValor, totalFactura);

            // Insertar la factura en la base de datos (suponiendo que el modelo lo maneja)
            boolean resultado = model.crearFactura(factura, idReserva, idReserva);

            if (resultado) {
                // Mostrar mensaje de éxito
                mostrarMensaje("Factura creada con éxito", "Éxito", Alert.AlertType.INFORMATION);
            } else {
                // Mostrar mensaje de error
                mostrarMensaje("Error al crear la factura", "Error", Alert.AlertType.ERROR);
            }
        } catch (Exception e) {
            mostrarMensaje("Error en los datos ingresados", "Error", Alert.AlertType.ERROR);
        }
    }


    @FXML
    public void volverAtras() {
        Stage stage = (Stage) anrereButton.getScene().getWindow();
        stage.close(); // Cierra la ventana actual para volver a la anterior
    }

    private void mostrarMensaje(String mensaje, String titulo, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
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