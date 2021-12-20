module com.mycompany.hethongbanve {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    
    opens com.mycompany.hethongbanve to javafx.fxml;
    exports com.mycompany.hethongbanve;
}
