package com.mycompany.sistemadegestiohotelraul.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexio {
    private static final String URL = "jdbc:mysql://localhost:3306/BD_SistemaGestioHotel_Raul"; 
    private static final String USER = "root"; 
    private static final String PASSWORD = ""; 

    public static Connection connectar() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Conexión exitosa.");
        } catch (SQLException e) {
            System.err.println("❌ Error de conexión: " + e.getMessage());
        }
        return conn;
    }

    public static void tancarConnexio(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
                System.out.println("🔌 Connexió tancada.");
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al cerrar la conexión: " + e.getMessage());
        }
    }
}
