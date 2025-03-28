package com.mycompany.sistemadegestiohotelraul;

import com.mycompany.sistemadegestiohotelraul.model.Factura;
import com.mycompany.sistemadegestiohotelraul.model.Model;
import com.mycompany.sistemadegestiohotelraul.model.Reserva;
import java.io.IOException;
import java.time.LocalDate;
import java.sql.Date;
import java.util.List;
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
    private TextField id_reserva, id_client, id_habitacio, preu_total, base_imposable, iva, total;
    @FXML
    private DatePicker data_reserva, data_inici, data_fi, data_emissio;
    @FXML
    private ComboBox<String> tipus_reserva, tipus_iva, metode_pagament, id_client_combo, comboReserva, comboFactura;
    @FXML
    private Button crearReservaYFactura, anrereButton;

    @FXML
    public void initialize() {
        tipus_reserva.getItems().addAll("Estandard", "VIP", "Garantida");
        tipus_iva.getItems().addAll("10%", "21%");
        metode_pagament.getItems().addAll("Efectiu","Targeta");
        cargarClientes(); // Llenar ComboBox con clientes
        cargarReservas();
        cargarFacturas();
        mostrarFacturaSeleccionada();
        mostrarReservaSeleccionada();
    }

    private void cargarReservas() {
        try {
            List<String> reservas = model.obtenerReservas(); // Obtener lista de reservas
            comboReserva.getItems().clear();  // Limpiar el ComboBox antes de agregar nuevos elementos
            comboReserva.getItems().addAll(reservas); // Agregar reservas al ComboBox
        } catch (Exception e) {
            System.out.println("Error cargando reservas: " + e.getMessage());
        }
    }

    private void cargarFacturas() {
        try {
            List<String> facturas = model.obtenerFacturas(); // Obtener lista de facturas
            comboFactura.getItems().clear();  // Limpiar el ComboBox antes de agregar nuevos elementos
            comboFactura.getItems().addAll(facturas); // Agregar facturas al ComboBox
        } catch (Exception e) {
            System.out.println("Error cargando facturas: " + e.getMessage());
        }
    }
    
    @FXML
    private void cargarDetallesReserva() {
        try {
            String reservaSeleccionada = comboReserva.getValue();

            if (reservaSeleccionada != null) {
                // Extraer el id_reserva de la selección
                int idReserva = Integer.parseInt(reservaSeleccionada.split(" - ")[0]);

                // Obtener los detalles de la reserva con el id
                Reserva reserva = model.obtenerReservaPorId(idReserva);  // Asegúrate de tener este método en el modelo

                // Llenar los campos con los detalles de la reserva
                id_reserva.setText(String.valueOf(reserva.getId_reserva()));
                id_habitacio.setText(String.valueOf(reserva.getId_habitacio()));

                // Usar los métodos convertidos para obtener LocalDate directamente (ya no es necesario el método 'AsLocalDate')
                if (reserva.getData_reserva() != null) {
                    data_reserva.setValue(reserva.getData_reserva());
                }
                if (reserva.getData_inici() != null) {
                    data_inici.setValue(reserva.getData_inici());
                }
                if (reserva.getData_fi() != null) {
                    data_fi.setValue(reserva.getData_fi());
                }

                // Otros campos
                tipus_reserva.setValue(reserva.getTipus_reserva());
                tipus_iva.setValue(reserva.getTipus_IVA() + "%");
                preu_total.setText(String.valueOf(reserva.getPreu_total_reserva()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void cargarDetallesFactura() {
        try {
            String facturaSeleccionada = comboFactura.getValue();

            if (facturaSeleccionada != null) {
                // Extraer id_factura de la selección
                int idFactura = Integer.parseInt(facturaSeleccionada.split(" - ")[0]);

                // Obtener los detalles de la factura con el id
                Factura factura = model.obtenerFacturaPorId(idFactura);  // Asegúrate de tener este método en el modelo

                // Llenar los campos con los detalles de la factura
                id_reserva.setText(String.valueOf(factura.getId_reserva()));
                data_emissio.setValue(factura.getData_emissioAsLocalDate());
                metode_pagament.setValue(factura.getMetode_pagament());
                base_imposable.setText(String.valueOf(factura.getBase_imposable()));
                iva.setText(String.valueOf(factura.getIva()));
                total.setText(String.valueOf(factura.getTotal()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @FXML
   private void eliminarFactura() {
       try {
           // Obtener la factura seleccionada
           String facturaSeleccionada = comboFactura.getValue();
           if (facturaSeleccionada == null) {
               mostrarMensaje("Seleccione una factura para eliminar", "Error", Alert.AlertType.ERROR);
               return;
           }

           int idFactura = Integer.parseInt(facturaSeleccionada.split(" - ")[0]); // Suponiendo que las facturas tienen el formato "idFactura - detalles"

           // Eliminar la factura
           boolean facturaEliminada = model.eliminarFactura(idFactura);

           if (facturaEliminada) {
               mostrarMensaje("Factura eliminada con éxito", "Éxito", Alert.AlertType.INFORMATION);
           } else {
               mostrarMensaje("Error al eliminar la factura", "Error", Alert.AlertType.ERROR);
           }
       } catch (Exception e) {
           mostrarMensaje("Error al eliminar la factura", "Error", Alert.AlertType.ERROR);
       }
   }



    @FXML
    private void eliminarReserva() {
        try {
            // Obtener la reserva seleccionada
            String reservaSeleccionada = comboReserva.getValue();
            if (reservaSeleccionada == null) {
                mostrarMensaje("Seleccione una reserva para eliminar", "Error", Alert.AlertType.ERROR);
                return;
            }

            int idReserva = Integer.parseInt(reservaSeleccionada.split(" - ")[0]); // Suponiendo que las reservas tienen el formato "idReserva - nombreCliente"

            // Eliminar la reserva
            boolean reservaEliminada = model.eliminarReserva(idReserva);

            if (reservaEliminada) {
                mostrarMensaje("Reserva eliminada con éxito", "Éxito", Alert.AlertType.INFORMATION);
            } else {
                mostrarMensaje("Error al eliminar la reserva", "Error", Alert.AlertType.ERROR);
            }
        } catch (Exception e) {
            mostrarMensaje("Error al eliminar la reserva", "Error", Alert.AlertType.ERROR);
        }
    }


    private void cargarClientes() {
        try {
            List<String> clientes = model.obtenerClientes(); // Obtener lista de clientes
            id_client_combo.getItems().addAll(clientes); // Agregar clientes al ComboBox
        } catch (Exception e) {
            System.out.println("Error cargando clientes: " + e.getMessage());
        }
    }

        @FXML
        private void CrearReserva() {
            try {
            // Obtener valores
            int idReserva = Integer.parseInt(id_reserva.getText());
            int idHabitacio = Integer.parseInt(id_habitacio.getText());
            String clienteSeleccionado = id_client_combo.getValue();

            if (clienteSeleccionado == null) {
                System.out.println("Error: No se ha seleccionado un cliente.");
                return;
            }

            int idClient = Integer.parseInt(clienteSeleccionado.split(" - ")[0]);

            LocalDate dataReserva = data_reserva.getValue();
            LocalDate dataInici = data_inici.getValue();
            LocalDate dataFi = data_fi.getValue();
            String tipusReserva = tipus_reserva.getValue();
            double tipusIVA = Double.parseDouble(tipus_iva.getValue().replace("%", "").trim());
            double preuTotal = Double.parseDouble(preu_total.getText());

            // Convertir a SQL Date
            Date sqlDataReserva = Date.valueOf(dataReserva);
            Date sqlDataInici = Date.valueOf(dataInici);
            Date sqlDataFi = Date.valueOf(dataFi);

            // Crear reserva
            // Si la clase Reserva utiliza LocalDate y no Date, simplemente pasa los valores de LocalDate
            Reserva reserva = new Reserva(idReserva, idClient, idHabitacio, dataReserva, dataInici, dataFi, tipusReserva, tipusIVA, preuTotal);
            model.crearReserva(reserva);
            System.out.println("Reserva creada con éxito!");

        } catch (NumberFormatException e) {
            System.out.println("Error en la conversión de datos. Verifica los valores ingresados.");
        } catch (Exception e) {
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

            // Asignar un valor temporal a id_factura, ya que si es autoincrementable, la base de datos lo manejará
            int idFactura = 0;  // Puedes asignar un valor temporal como 0 o un valor real si es necesario.

            // Crear una nueva factura con los datos proporcionados
            Factura factura = new Factura(idFactura, idReserva, dataEmissio, metodePagament, baseImposable, ivaValor, totalFactura);

            // Llamar al método del modelo para insertar la factura en la base de datos
            boolean resultado = model.crearFactura(factura, idReserva, idFactura);  // Pasar los tres parámetros

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
    private void mostrarFacturaSeleccionada() {
        try {
            String facturaSeleccionada = comboFactura.getValue();
            if (facturaSeleccionada != null) {
                int idFactura = Integer.parseInt(facturaSeleccionada.split(" - ")[0]);  // Extraemos el ID de la factura

                // Obtener la factura del modelo (suponiendo que tienes un método en el modelo para obtener la factura por ID)
                Factura factura = model.obtenerFacturaPorId(idFactura);

                if (factura != null) {
                    // Rellenar los campos con los datos de la factura
                    base_imposable.setText(String.valueOf(factura.getBase_imposable()));
                    iva.setText(String.valueOf(factura.getIva()));
                    total.setText(String.valueOf(factura.getTotal()));
                    data_emissio.setValue(factura.getData_emissioAsLocalDate());
                    metode_pagament.setValue(factura.getMetode_pagament());
                }
            }
        } catch (Exception e) {
            mostrarMensaje("Error al cargar los datos de la factura", "Error", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void mostrarReservaSeleccionada() {
        try {
            String reservaSeleccionada = comboReserva.getValue();
            if (reservaSeleccionada != null) {
                int idReserva = Integer.parseInt(reservaSeleccionada.split(" - ")[0]);  // Extraemos el ID de la reserva

                // Obtener la reserva del modelo (suponiendo que tienes un método en el modelo para obtener la reserva por ID)
                Reserva reserva = model.obtenerReservaPorId(idReserva);

                if (reserva != null) {
                    // Rellenar los campos con los datos de la reserva
                    id_reserva.setText(String.valueOf(reserva.getId_reserva()));
                    id_habitacio.setText(String.valueOf(reserva.getId_habitacio()));
                    id_client.setText(String.valueOf(reserva.getId_client())); // Asumiendo que tienes un ComboBox para clientes
                    data_inici.setValue(reserva.getData_inici());
                    data_fi.setValue(reserva.getData_fi());
                    tipus_reserva.setValue(reserva.getTipus_reserva());
                    tipus_iva.setValue(String.valueOf(reserva.getTipus_IVA()));
                    preu_total.setText(String.valueOf(reserva.getTipus_reserva()));
                }
            }
        } catch (Exception e) {
            mostrarMensaje("Error al cargar los datos de la reserva", "Error", Alert.AlertType.ERROR);
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

    public void limpiarCampos() {
        id_client_combo.setValue(null);
        id_reserva.clear();
        id_habitacio.clear();
        data_reserva.setValue(null);
        data_inici.setValue(null);
        data_fi.setValue(null);
        tipus_reserva.setValue(null);
        tipus_iva.setValue(null);
        preu_total.clear();
        comboFactura.setValue(null);
        comboReserva.setValue(null);
    }
}