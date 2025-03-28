package com.mycompany.sistemadegestiohotelraul.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Model {

    
    // ================================================AÑADIR================================================
    
    // Método para agregar un cliente
    public boolean afegirClient(Persona persona, String dataRegistre, String tipusClient, String targeta) {
    // 1. SQL para insertar en Persona
    String sqlPersona = "INSERT INTO Persona (id_persona, nom, cognoms, adreça, dni, data_naixement, telefon, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    // 2. SQL para insertar en Client
    String sqlClient = "INSERT INTO Client (id_persona, data_registre, tipus_client, targeta) VALUES (?, ?, ?, ?)";

    try (Connection conn = Connexio.connectar();  // 3. Conexión a la base de datos
         PreparedStatement stmtPersona = conn.prepareStatement(sqlPersona);  // 4. Preparar sentencia Persona
         PreparedStatement stmtClient = conn.prepareStatement(sqlClient)) {  // 5. Preparar sentencia Client

        // 6. Set parámetros para Persona
        stmtPersona.setInt(1, persona.getId());  
        stmtPersona.setString(2, persona.getNom());  
        stmtPersona.setString(3, persona.getCognom());  
        stmtPersona.setString(4, persona.getAdreça());  
        stmtPersona.setString(5, persona.getDni());  
        stmtPersona.setDate(6, new java.sql.Date(persona.getDataNaixement().getTime()));  
        stmtPersona.setString(7, persona.getTelefon());  
        stmtPersona.setString(8, persona.getEmail());  

        // 7. Set parámetros para Client
        stmtClient.setInt(1, persona.getId());  
        stmtClient.setString(2, dataRegistre);  
        stmtClient.setString(3, tipusClient);  
        stmtClient.setString(4, targeta);  

        // 8. Ejecutar inserción Persona
        int filasPersona = stmtPersona.executeUpdate();

        // 9. Ejecutar inserción Client
        int filasClient = stmtClient.executeUpdate();

        // 10. Si ambas inserciones son exitosas, return true
        return filasPersona > 0 && filasClient > 0;  
    } catch (SQLException e) {
        // 11. Si error, mostrar mensaje
        System.err.println("Error al agregar cliente: " + e.getMessage());
    }

    // 12. Si falla, return false
    return false;
    }

    // Método para agregar un empleado
    public boolean afegirEmpleat(Persona persona, String llocFeina, String dataContratacio, double salariBrut, String estatLaboral) {
        String sqlPersona = "INSERT INTO Persona (id_persona, nom, cognoms, adreça, dni, data_naixement, telefon, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        String sqlEmpleat = "INSERT INTO Empleat (id_persona, lloc_feina, data_contratacio, salari_brut, estat_laboral) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Connexio.connectar();
             PreparedStatement stmtPersona = conn.prepareStatement(sqlPersona);
             PreparedStatement stmtEmpleat = conn.prepareStatement(sqlEmpleat)) {

            stmtPersona.setInt(1, persona.getId());
            stmtPersona.setString(2, persona.getNom());
            stmtPersona.setString(3, persona.getCognom());
            stmtPersona.setString(4, persona.getAdreça());
            stmtPersona.setString(5, persona.getDni());
            stmtPersona.setDate(6, new java.sql.Date(persona.getDataNaixement().getTime()));
            stmtPersona.setString(7, persona.getTelefon());
            stmtPersona.setString(8, persona.getEmail());

            stmtEmpleat.setInt(1, persona.getId());
            stmtEmpleat.setString(2, llocFeina);
            stmtEmpleat.setString(3, dataContratacio);
            stmtEmpleat.setDouble(4, salariBrut);
            stmtEmpleat.setString(5, estatLaboral);

            int filasPersona = stmtPersona.executeUpdate();
            int filasEmpleat = stmtEmpleat.executeUpdate();

            return filasPersona > 0 && filasEmpleat > 0;
        } catch (SQLException e) {
            System.err.println("Error al agregar empleado: " + e.getMessage());
        }
        return false;
    }
    
    // Método para agregar una tarea
    public void afegirTasca(Tasca tasca) throws SQLException {
        String query = "INSERT INTO Tasca (descripcio, data_creacio, data_execucio, estat) VALUES (?, ?, ?, ?)";

        try (Connection conn = Connexio.connectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, tasca.getDescripcio());
            stmt.setDate(2, tasca.getData_creacio());
            stmt.setDate(3, tasca.getData_execucio());
            stmt.setString(4, tasca.getEstat());

            stmt.executeUpdate();
        }
    }


    public boolean crearReserva(Reserva reserva) {
        String sql = "INSERT INTO reserva (id_reserva, id_client, id_habitacio, data_reserva, data_inici, data_fi, tipus_reserva, tipus_IVA, preu_total_reserva) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection con = Connexio.connectar();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, reserva.getId_reserva());
            stmt.setInt(2, reserva.getId_client());
            stmt.setInt(3, reserva.getId_habitacio());
            stmt.setDate(4, java.sql.Date.valueOf(reserva.getData_reserva())); 
            stmt.setDate(5, java.sql.Date.valueOf(reserva.getData_inici()));  
            stmt.setDate(6, java.sql.Date.valueOf(reserva.getData_fi()));     
            stmt.setString(7, reserva.getTipus_reserva());
            stmt.setDouble(8, reserva.getTipus_IVA());
            stmt.setDouble(9, reserva.getPreu_total_reserva());
            
            int filasInsertadas = stmt.executeUpdate();
            return filasInsertadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<String> obtenerClientes() {
        List<String> clientes = new ArrayList<>();
        String sql = "SELECT Client.id_client, Persona.nom FROM Client JOIN Persona ON Client.id_persona = Persona.id_persona";
        
        try (Connection con = Connexio.connectar();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                int idClient = rs.getInt("id_client");
                String nombre = rs.getString("nom");
                clientes.add(idClient + " - " + nombre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return clientes;
    }

    public List<String> obtenerNombresEmpleados() throws SQLException {
        List<String> nombres = new ArrayList<>();
        String query = "SELECT nom FROM Persona p " +
                       "INNER JOIN Empleat e ON p.id_persona = e.id_persona";

        try (Connection con = Connexio.connectar();
             PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                nombres.add(rs.getString("nom")); // Solo obtiene el nombre
            }
        }

        if (nombres.isEmpty()) {
            System.out.println("No hay empleados en la BD.");
        }

        return nombres;
    }



     public boolean crearFactura(Factura factura, int id_reserva, int id_factura) {
        boolean resultado = false;
        String sql = "INSERT INTO factura (id_factura, id_reserva, data_emissio, metode_pagament, base_imposable, iva, total) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Connexio.connectar();  // Usamos la clase Database para obtener la conexión
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // Asignamos los valores de la factura
            ps.setInt(1, id_factura);  // Asignamos el id_factura que se pasa como parámetro
            ps.setInt(2, id_reserva);  // Asignamos el id_reserva (clave foránea de la reserva)

            // Rellenamos el resto de los campos de la factura
            ps.setDate(3, java.sql.Date.valueOf(factura.getData_emissioAsLocalDate()));  // Convertimos LocalDate a SQL Date
            ps.setString(4, factura.getMetode_pagament());
            ps.setDouble(5, factura.getBase_imposable());
            ps.setDouble(6, factura.getIva());
            ps.setDouble(7, factura.getTotal());

            // Ejecutamos la inserción
            int filasAfectadas = ps.executeUpdate();  // Ejecutamos la inserción

            if (filasAfectadas > 0) {
                resultado = true;  // Si la inserción fue exitosa, cambiamos resultado a true
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultado;  // Retornamos si la inserción fue exitosa
    }

    //===============================================OBTENER===============================================
    
    public int obtenerIdEmpleadoPorNombre(String nombre) throws SQLException {
        String query = "SELECT id_persona FROM Persona WHERE nom = ?";
        try (Connection conn = Connexio.connectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id_persona");
            }
        }
        return -1; // Si no se encuentra, retorna -1
    }


     
    // Método para obtener una persona por su ID
    public Persona obtenerPersona(int id) throws SQLException {
        // 1. Definir la consulta SQL
        String query = "SELECT * FROM Persona WHERE id_persona = ?";

        try (Connection conn = Connexio.connectar();  // 2. Conectar a la base de datos
             PreparedStatement stmt = conn.prepareStatement(query)) {  // 3. Preparar la sentencia

            // 4. Establecer el parámetro `id` en la consulta
            stmt.setInt(1, id);

            // 5. Ejecutar la consulta y obtener el resultado
            ResultSet rs = stmt.executeQuery();

            // 6. Si existe la persona con ese ID, devolverla
            if (rs.next()) {
                return new Persona(
                    rs.getInt("id_persona"),
                    rs.getString("nom"),
                    rs.getString("cognoms"),
                    rs.getString("adreça"),
                    rs.getString("dni"),
                    rs.getDate("data_naixement"),
                    rs.getString("telefon"),
                    rs.getString("email")
                );
            } else {
                // 7. Si no se encuentra la persona, devolver null
                return null;
            }
        }
    }


    public Habitacio obtenerHabitacio(int id_habitacio) throws SQLException {
        String query = "SELECT * FROM Habitacio WHERE id_habitacio = ?";
        try (Connection conn = Connexio.connectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id_habitacio);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Habitacio(
                    rs.getInt("id_habitacio"),
                    rs.getString("numero_habitacio"),
                    rs.getString("tipus"),
                    rs.getInt("capacitat"),
                    rs.getDouble("preu_nit_AD"),
                    rs.getDouble("preu_nit_MP"),
                    rs.getString("estat"),
                    rs.getString("descripcio")
                );
            }
        }
        return null;  // Si no se encuentra la habitación
    }
    
       

 // 1. Obtener todos los IDs de las tareas desde la base de datos
    public List<Integer> obtenerIdsTareas() throws SQLException {
        List<Integer> idsTareas = new ArrayList<>();
        String sql = "SELECT id_tasca FROM Tasca";
        
        try (Connection conn = Connexio.connectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                idsTareas.add(rs.getInt("id_tasca"));
            }
        }
        return idsTareas;
    }

    // 2. Obtener los detalles de una tarea por su ID
    public Tasca obtenerTascaPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Tasca WHERE id_tasca = ?";
        Tasca tasca = null;

        try (Connection conn = Connexio.connectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                tasca = new Tasca(
                    rs.getInt("id_tasca"),
                    rs.getString("descripcio"),
                    rs.getDate("data_creacio"),  // Usando java.sql.Date directamente
                    rs.getDate("data_execucio"), // Usando java.sql.Date directamente
                    rs.getString("estat")
                );
            }
        }
        return tasca;
    }
    
    // Método para obtener una persona por su ID
    public Persona obtenirPersonaPerId(int idPersona) {
        // 1. Definir las consultas SQL para Persona, Cliente y Empleat
        String consultaPersona = "SELECT * FROM Persona WHERE id_persona = ?";
        String consultaCliente = "SELECT * FROM Client WHERE id_persona = ?";
        String consultaEmpleado = "SELECT * FROM Empleat WHERE id_persona = ?";

        try (Connection connexio = Connexio.connectar(); // 2. Conectar a la base de datos
             PreparedStatement stmtPersona = connexio.prepareStatement(consultaPersona);
             PreparedStatement stmtCliente = connexio.prepareStatement(consultaCliente);
             PreparedStatement stmtEmpleado = connexio.prepareStatement(consultaEmpleado)) {

            // 3. Establecer el parámetro `idPersona` para la consulta de Persona
            stmtPersona.setInt(1, idPersona);
            ResultSet rsPersona = stmtPersona.executeQuery();

            if (rsPersona.next()) {  // 4. Si la persona existe, obtener los datos
                int id = rsPersona.getInt("id_persona");
                String nom = rsPersona.getString("nom");
                String cognoms = rsPersona.getString("cognoms");
                String adreça = rsPersona.getString("adreça");
                String dni = rsPersona.getString("dni");
                java.sql.Date dataNaixementSQL = rsPersona.getDate("data_naixement");
                String telefon = rsPersona.getString("telefon");
                String email = rsPersona.getString("email");

                // 5. Verificar si la persona es un Cliente
                stmtCliente.setInt(1, idPersona);
                try (ResultSet rsCliente = stmtCliente.executeQuery()) {
                    if (rsCliente.next()) {  // Si es un Cliente, devolver la instancia de Cliente
                        return new Client(
                            id, nom, cognoms, adreça, dni, dataNaixementSQL, telefon, email, 
                            rsCliente.getDate("data_registre"),  // Obtener fecha de registro
                            rsCliente.getString("tipus_client"),
                            rsCliente.getString("targeta")
                        );
                    }
                }

                // 6. Verificar si la persona es un Empleat
                stmtEmpleado.setInt(1, idPersona);
                try (ResultSet rsEmpleado = stmtEmpleado.executeQuery()) {
                    if (rsEmpleado.next()) {  // Si es un Empleat, devolver la instancia de Empleat
                        java.sql.Date dataContratacioSQL = rsEmpleado.getDate("data_contratacio");  // Obtener fecha de contratación

                        return new Empleat(
                            id, nom, cognoms, adreça, dni, dataNaixementSQL, telefon, email,
                            rsEmpleado.getString("lloc_feina"),
                            dataContratacioSQL,  // Fecha de contratación
                            rsEmpleado.getDouble("salari_brut"),
                            rsEmpleado.getString("estat_laboral")
                        );
                    }
                }

                // 7. Si no es ni Cliente ni Empleat, devolver solo Persona
                return new Persona(id, nom, cognoms, adreça, dni, dataNaixementSQL, telefon, email);
            }
        } catch (SQLException e) {  // 8. Manejo de excepciones
            System.err.println("Error al obtener la persona: " + e.getMessage());
        }
        return null;  // 9. Si no se encuentra la persona, devolver null
    }
    
    // Método para obtener todas las reservas
public List<String> obtenerReservas() {
    List<String> reservas = new ArrayList<>();
    String sql = "SELECT id_reserva, id_client FROM Reserva";  // Modificar según la estructura de la tabla de reservas

    try (Connection con = Connexio.connectar();
         Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        // Iterar sobre el conjunto de resultados y agregar las reservas a la lista
        while (rs.next()) {
            int idReserva = rs.getInt("id_reserva");
            int idClient = rs.getInt("id_client");
            reservas.add(idReserva + " - Cliente ID: " + idClient);  // Formato de salida "id_reserva - Cliente ID"
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return reservas;
}

// Método para obtener todas las facturas
public List<String> obtenerFacturas() {
    List<String> facturas = new ArrayList<>();
    String sql = "SELECT id_factura, id_reserva FROM Factura";  // Modificar según la estructura de la tabla de facturas

    try (Connection con = Connexio.connectar();
         Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        // Iterar sobre el conjunto de resultados y agregar las facturas a la lista
        while (rs.next()) {
            int idFactura = rs.getInt("id_factura");
            int idReserva = rs.getInt("id_reserva");
            facturas.add(idFactura + " - Reserva ID: " + idReserva);  // Formato de salida "id_factura - Reserva ID"
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return facturas;
}

public Reserva obtenerReservaPorId(int idReserva) {
    String sql = "SELECT * FROM Reserva WHERE id_reserva = ?";
    try (Connection con = Connexio.connectar();
         PreparedStatement stmt = con.prepareStatement(sql)) {

        stmt.setInt(1, idReserva);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
        return new Reserva(
            rs.getInt("id_reserva"),
            rs.getInt("id_client"),
            rs.getInt("id_habitacio"),
            // Si la columna data_reserva es un Date, primero obtenemos el java.sql.Date
            // Luego lo convertimos a LocalDate usando toLocalDate()
            rs.getDate("data_reserva") != null ? rs.getDate("data_reserva").toLocalDate() : null, 

            // Repetir para las demás fechas
            rs.getDate("data_inici") != null ? rs.getDate("data_inici").toLocalDate() : null,
            rs.getDate("data_fi") != null ? rs.getDate("data_fi").toLocalDate() : null,

            rs.getString("tipus_reserva"),
            rs.getDouble("tipus_IVA"),
            rs.getDouble("preu_total_reserva")
        );

        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}

    public Factura obtenerFacturaPorId(int idFactura) {
        Factura factura = null;
        String sql = "SELECT * FROM Factura WHERE id_factura = ?";

        try (Connection con = Connexio.connectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, idFactura);  // Establecer el parámetro de la consulta

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int idReserva = rs.getInt("id_reserva");

                    // Convertir java.sql.Date a LocalDate
                    java.sql.Date sqlDate = rs.getDate("data_emissio");
                    LocalDate dataEmissio = sqlDate.toLocalDate();  // Conversión a LocalDate

                    String metodePagament = rs.getString("metode_pagament");
                    double baseImposable = rs.getDouble("base_imposable");
                    double iva = rs.getDouble("iva");
                    double total = rs.getDouble("total");

                    // Crear factura
                    factura = new Factura(idFactura, idReserva, dataEmissio, metodePagament, baseImposable, iva, total);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return factura;
    }
    

    
    //===============================================ELIMINAR===============================================
    // Método para eliminar un cliente
    public boolean eliminarCliente(int idPersona) {
        // 1. Definir la consulta SQL para eliminar el cliente por su id_persona
        String sql = "DELETE FROM Client WHERE id_persona = ?";

        try (Connection conn = Connexio.connectar();  // 2. Establecer la conexión a la base de datos
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPersona);  // 3. Establecer el valor del parámetro `idPersona`

            // 4. Ejecutar la consulta y devolver true si se elimina al menos un cliente
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {  // 5. Manejo de excepciones
            System.err.println("Error al eliminar el cliente: " + e.getMessage());
        }

        // 6. Si ocurre un error, devolver false
        return false;
    }


    // Método para eliminar un empleado
    public boolean eliminarEmpleado(int idPersona) {
        String sql = "DELETE FROM Empleat WHERE id_persona = ?";
        try (Connection conn = Connexio.connectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPersona);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar el empleado: " + e.getMessage());
        }
        return false;
    }

    // Método para eliminar una persona de la base de datos
    public boolean eliminarPersona(int idPersona) {
        String sql = "DELETE FROM Persona WHERE id_persona = ?";
        try (Connection conn = Connexio.connectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPersona);
            return stmt.executeUpdate() > 0; // Devuelve true si se eliminó correctamente
        } catch (SQLException e) {
            System.err.println("Error al eliminar la persona: " + e.getMessage());
        }
        return false;
    }
    
    // Método para eliminar una reserva
public boolean eliminarReserva(int idReserva) {
    String sql = "DELETE FROM Reserva WHERE id_reserva = ?";  // Consulta DELETE para eliminar la reserva

    try (Connection con = Connexio.connectar();
         PreparedStatement stmt = con.prepareStatement(sql)) {

        // Establecer el parámetro de la consulta
        stmt.setInt(1, idReserva);

        // Ejecutar la consulta
        int filasEliminadas = stmt.executeUpdate();

        return filasEliminadas > 0;  // Si se eliminó alguna fila, la reserva fue eliminada con éxito
    } catch (SQLException e) {
        e.printStackTrace();
        return false;  // Si ocurre un error, devolver false
    }
}

// Método para eliminar una factura
public boolean eliminarFactura(int idFactura) {
    String sql = "DELETE FROM Factura WHERE id_factura = ?";  // Consulta DELETE para eliminar la factura

    try (Connection con = Connexio.connectar();
         PreparedStatement stmt = con.prepareStatement(sql)) {

        // Establecer el parámetro de la consulta
        stmt.setInt(1, idFactura);

        // Ejecutar la consulta
        int filasEliminadas = stmt.executeUpdate();

        return filasEliminadas > 0;  // Si se eliminó alguna fila, la factura fue eliminada con éxito
    } catch (SQLException e) {
        e.printStackTrace();
        return false;  // Si ocurre un error, devolver false
    }
}

    //===============================================VERIFICAR===============================================
    
    // Método para verificar si una persona es cliente
    public boolean esCliente(int idPersona) {
        // 1. Consulta SQL para verificar si la persona es cliente
        String consulta = "SELECT COUNT(*) FROM Client WHERE id_persona = ?";

        try (Connection connexio = Connexio.connectar();  // 2. Establecer la conexión con la base de datos
             PreparedStatement stmt = connexio.prepareStatement(consulta)) {

            stmt.setInt(1, idPersona);  // 3. Establecer el valor del parámetro `idPersona`

            // 4. Ejecutar la consulta y verificar si hay al menos un cliente con el `idPersona`
            ResultSet rs = stmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;  // Si se encuentra al menos una coincidencia, devuelve true
        } catch (SQLException e) {  // 5. Manejo de excepciones
            System.err.println("Error al verificar si la persona es cliente: " + e.getMessage());
        }

        // 6. Si ocurre un error o no es cliente, devolver false
        return false;
    }


    // Método para verificar si una persona es empleado
    public boolean esEmpleado(int idPersona) {
        String consulta = "SELECT COUNT(*) FROM Empleat WHERE id_persona = ?";
        try (Connection connexio = Connexio.connectar();
             PreparedStatement stmt = connexio.prepareStatement(consulta)) {
            stmt.setInt(1, idPersona);
            ResultSet rs = stmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException e) {
            System.err.println("Error al verificar si la persona es empleado: " + e.getMessage());
        }
        return false;
    }
    
    
    
}





