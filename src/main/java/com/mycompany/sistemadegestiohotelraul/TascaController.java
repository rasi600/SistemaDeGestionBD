package com.mycompany.sistemadegestiohotelraul;

import com.mycompany.sistemadegestiohotelraul.model.Model;
import com.mycompany.sistemadegestiohotelraul.model.Tasca;
import java.io.IOException;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TascaController {

    // Instancia del modelo
    private final Model model = new Model();

    @FXML
    private TextField estat;  // Campo de texto para el estado
    @FXML
    private TextArea descripcio;  // Campo de texto para la descripción
    @FXML
    private DatePicker data_creacio, data_execucio;  // Fecha de creación y fecha de ejecución
    @FXML
    private ComboBox<Integer> comboBoxTareas;  // ComboBox para seleccionar las tareas por su ID

    @FXML
    private void VentanaSecondary() throws IOException {
        App.setRoot("secondary");
    }
    
    @FXML
    private void initialize() {
        try {
            // 1. Cargar IDs de tareas en el ComboBox
            comboBoxTareas.setItems(FXCollections.observableList(model.obtenerIdsTareas()));

            // 2. Al seleccionar una tarea, mostrar sus detalles
            comboBoxTareas.setOnAction(e -> mostrarDetallesTasca(comboBoxTareas.getValue()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 3. Mostrar detalles de la tarea seleccionada
    private void mostrarDetallesTasca(Integer tascaId) {
        try {
            Tasca tasca = model.obtenerTascaPorId(tascaId);
            if (tasca != null) {
                // Mostrar detalles
                descripcio.setText(tasca.getDescripcio());
                estat.setText(tasca.getEstat());
                data_creacio.setValue(tasca.getData_creacio().toLocalDate()); // Convertir a LocalDate
                data_execucio.setValue(tasca.getData_execucio().toLocalDate()); // Convertir a LocalDate
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 4. Crear una nueva tarea
    @FXML
    private void CrearTasca() {
        try {
            String estat_tasca = estat.getText();
            String descripcio_tasca = descripcio.getText();

            // Convertir fechas de DatePicker a java.sql.Date
            java.sql.Date fechaCreacio = java.sql.Date.valueOf(data_creacio.getValue());
            java.sql.Date fechaExecucio = java.sql.Date.valueOf(data_execucio.getValue());

            // Crear nueva tarea
            Tasca tasca = new Tasca(0, descripcio_tasca, fechaCreacio, fechaExecucio, estat_tasca);
            model.afegirTasca(tasca); // Llamar al método para guardar la tarea

            // 1. Actualizar el ComboBox con los nuevos IDs de las tareas
            comboBoxTareas.setItems(FXCollections.observableList(model.obtenerIdsTareas()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

