package com.mycompany.sistemadegestiohotelraul;

import com.mycompany.sistemadegestiohotelraul.model.Model;
import com.mycompany.sistemadegestiohotelraul.model.Tasca;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TascaController {

    private final Model model = new Model();

    @FXML
    private TextField estat;
    @FXML
    private TextArea descripcio;
    @FXML
    private DatePicker data_creacio, data_execucio;
    @FXML
    private ComboBox<Integer> comboBoxTareas; 
    @FXML
    private ComboBox<String> comboBoxEmpleados;  // Nuevo ComboBox para empleados

    @FXML
    private void VentanaSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private void initialize() {
        try {
            // Cargar lista de empleados en el ComboBox
            cargarEmpleados();

            // Cargar IDs de tareas
            comboBoxTareas.setItems(FXCollections.observableList(model.obtenerIdsTareas()));

            // Manejar selección de tarea
            comboBoxTareas.setOnAction(e -> mostrarDetallesTasca(comboBoxTareas.getValue()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void cargarEmpleados() {
        try {
            List<String> empleados = model.obtenerNombresEmpleados();
            if (empleados.isEmpty()) {
                System.out.println("No se encontraron empleados en la base de datos.");
            }
            comboBoxEmpleados.setItems(FXCollections.observableArrayList(empleados));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    private void mostrarDetallesTasca(Integer tascaId) {
        try {
            Tasca tasca = model.obtenerTascaPorId(tascaId);
            if (tasca != null) {
                descripcio.setText(tasca.getDescripcio());
                estat.setText(tasca.getEstat());
                data_creacio.setValue(tasca.getData_creacio().toLocalDate());
                data_execucio.setValue(tasca.getData_execucio().toLocalDate());
                //comboBoxEmpleados.setItems();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

@FXML
private void CrearTasca() {
    try {
        String estat_tasca = estat.getText();
        String descripcio_tasca = descripcio.getText();
        java.sql.Date fechaCreacio = java.sql.Date.valueOf(data_creacio.getValue());
        java.sql.Date fechaExecucio = java.sql.Date.valueOf(data_execucio.getValue());

        // Obtener nombre del empleado seleccionado
        String nomEmpleado = comboBoxEmpleados.getValue();
        if (nomEmpleado == null) {
            System.out.println("Debe seleccionar un empleado.");
            return;
        }

        // Obtener ID del empleado
        int idEmpleado = model.obtenerIdEmpleadoPorNombre(nomEmpleado);
        if (idEmpleado == -1) {
            System.out.println("Error: No se encontró el empleado.");
            return;
        }

        // Crear tarea con el ID del empleado
        Tasca tasca = new Tasca(0, descripcio_tasca, fechaCreacio, fechaExecucio, estat_tasca);
        model.afegirTasca(tasca);

        // Actualizar ComboBox de tareas
        comboBoxTareas.setItems(FXCollections.observableList(model.obtenerIdsTareas()));
    } catch (Exception e) {
        e.printStackTrace();
    }
}

@FXML
public void LimpiarCampos(){
    descripcio.clear();
    estat.clear();
    data_creacio.setValue(null);
    data_execucio.setValue(null);
    comboBoxEmpleados.setValue(null);
    comboBoxTareas.setValue(null);
}

}
