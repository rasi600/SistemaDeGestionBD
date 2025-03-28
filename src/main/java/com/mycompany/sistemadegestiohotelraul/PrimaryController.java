package com.mycompany.sistemadegestiohotelraul;

import com.mycompany.sistemadegestiohotelraul.model.Client;
import com.mycompany.sistemadegestiohotelraul.model.Empleat;
import com.mycompany.sistemadegestiohotelraul.model.Model;
import com.mycompany.sistemadegestiohotelraul.model.Persona;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class PrimaryController {

    Model model = new Model(); // Instancia del modelo para interactuar con la base de datos

    @FXML ComboBox<Persona> lista;
    @FXML TextField id_persona, nom, cognoms, adreça, dni, telefon, email, dniconsultar;
    @FXML TextField Data_Regis, Tipus_Client, Targeta, Lloc_Feina, Data_Contratacio, Salari_Brut, Estat_Laboral;
    @FXML DatePicker data_naixement_bo, Data_Regist_bo, Data_Contratacio_bo;
    @FXML Button boto_afegir_client, boto_afegir_empleat, boto_eliminar, boto_crearReserva;

    @FXML
    public void initialize() { 
        try {
            System.out.println("Inicializando...");
        } catch (Exception e) {
            alerta("Error al cargar los usuarios: " + e.getMessage());
        }
    }
    
    @FXML
    private void VentanaSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    public void consultarusuari() {
        try {
            int id = Integer.parseInt(dniconsultar.getText());
            Persona persona = model.obtenirPersonaPerId(id);

            if (persona != null) {
                id_persona.setText(String.valueOf(persona.getId()));
                nom.setText(persona.getNom());
                cognoms.setText(persona.getCognom());
                adreça.setText(persona.getAdreça());
                dni.setText(persona.getDni());
                telefon.setText(persona.getTelefon());
                email.setText(persona.getEmail());
                
                if (persona instanceof Client) {
                    Data_Regist_bo.setValue(((Client) persona).getData_registre().toLocalDate());
                    Tipus_Client.setText(((Client) persona).getTipus_client());
                    Targeta.setText(String.valueOf((((Client) persona).getTargeta_credit())));
                }
                
                if (persona instanceof Empleat) {
                    Lloc_Feina.setText(((Empleat) persona).getLlocFeina());
                    Data_Contratacio_bo.setValue(((Empleat) persona).getDataContratacio().toLocalDate());
                    Salari_Brut.setText(String.valueOf((((Empleat) persona).getSalariBrut())));
                    Estat_Laboral.setText(((Empleat) persona).getEstatLaboral());
                }

                if (persona.getDataNaixement() != null) {
                    data_naixement_bo.setValue(persona.getDataNaixement().toLocalDate());
                } else {
                    data_naixement_bo.setValue(null);
                }
            } else {
                alerta("⚠️ Usuario no encontrado.");
            }
        } catch (NumberFormatException e) {
            alerta("⚠️ La ID debe ser un número válido.");
        }
    }



    @FXML
    public void afegirClient() throws SQLException, FileNotFoundException, IOException {
        LocalDate data = data_naixement_bo.getValue(); 
        LocalDate dataReg = Data_Regist_bo.getValue();

        if (data != null && dataReg != null) {
            String dataRegString = dataReg.toString();
            boolean ok = model.afegirClient(
                new Persona(
                    Integer.parseInt(id_persona.getText()), 
                    nom.getText(), 
                    cognoms.getText(), 
                    adreça.getText(), 
                    dni.getText(), 
                    java.sql.Date.valueOf(data), 
                    telefon.getText(), 
                    email.getText()
                ),
                dataRegString, 
                Tipus_Client.getText(), 
                Targeta.getText()
            );

            if (ok) {
                alerta("Cliente añadido correctamente.");
            } else {
                alerta("No se pudo añadir el cliente.");
            }
        } else {
            alerta("⚠️ Las fechas son obligatorias.");
        }
    }

    @FXML
    public void afegirEmpleat() throws SQLException, FileNotFoundException, IOException {
        LocalDate dataNaixement = data_naixement_bo.getValue();
        LocalDate dataContractacio = Data_Contratacio_bo.getValue();

        if (dataNaixement != null && dataContractacio != null) {
            try {
                boolean ok = model.afegirEmpleat(
                    new Persona(
                        Integer.parseInt(id_persona.getText()),
                        nom.getText(),
                        cognoms.getText(),
                        adreça.getText(),
                        dni.getText(),
                        java.sql.Date.valueOf(dataNaixement),
                        telefon.getText(),
                        email.getText()
                    ),
                    Lloc_Feina.getText(),
                    dataContractacio.toString(),
                    Double.parseDouble(Salari_Brut.getText()),
                    Estat_Laboral.getText()
                );

                if (ok) {
                    alerta("Empleado añadido correctamente.");
                } else {
                    alerta("No se pudo añadir el empleado.");
                }
            } catch (Exception e) {
                alerta("⚠️ Error al añadir el empleado: " + e.getMessage());
            }
        } else {
            alerta("⚠️ Las fechas son obligatorias.");
        }
    }


    @FXML
    public void eliminarPersona() {
        try {
            if (id_persona.getText().trim().isEmpty()) {
                alerta("⚠️ Introduce un ID válido.");
                return;
            }

            int id = Integer.parseInt(id_persona.getText().trim());

            boolean esCliente = model.esCliente(id);
            boolean esEmpleado = model.esEmpleado(id);

            boolean eliminado = false;

            // Si es cliente, eliminar de la tabla Client
            if (esCliente) {
                eliminado = model.eliminarCliente(id);
            }

            // Si es empleado, eliminar de la tabla Empleat
            if (esEmpleado) {
                eliminado = model.eliminarEmpleado(id);
            }

            // Eliminar de la tabla Persona si es cliente o empleado
            if (esCliente || esEmpleado) {
                eliminado = model.eliminarPersona(id);
            } else {
                // Si no es ni cliente ni empleado, eliminar solo de la tabla Persona
                eliminado = model.eliminarPersona(id);
            }

            // Si la eliminación fue exitosa, limpiar los campos y mostrar mensaje
            if (eliminado) {
                limpiarCampos();
                alerta("✅ Persona eliminada correctamente.");
            } else {
                alerta("❌ No se pudo eliminar la persona.");
            }
        } catch (NumberFormatException e) {
            alerta("⚠️ El ID debe ser un número válido.");
        } catch (Exception e) {
            alerta("⚠️ Error al eliminar la persona.");
        }
    }

    private void alerta(String text) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setHeaderText(null);
        alerta.setTitle("Información");
        alerta.setContentText(text);
        alerta.show();
    }

    public void limpiarCampos() {
        id_persona.clear();
        nom.clear();
        cognoms.clear();
        adreça.clear();
        dni.clear();
        telefon.clear();
        email.clear();
        dniconsultar.clear();
        Tipus_Client.clear();
        Targeta.clear();
        Lloc_Feina.clear();
        data_naixement_bo.setValue(null);
        Data_Contratacio_bo.setValue(null);
        Data_Regist_bo.setValue(null);
        Salari_Brut.clear();
        Estat_Laboral.clear();
        

    }
}
