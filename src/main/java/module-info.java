module com.mycompany.sistemadegestiohotelraul {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql; // Esta línea es necesaria para trabajar con JDBC

    opens com.mycompany.sistemadegestiohotelraul to javafx.fxml;
    exports com.mycompany.sistemadegestiohotelraul;
}
