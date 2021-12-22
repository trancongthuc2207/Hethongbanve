module com.mycompany.hethongbanve {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;
    
    opens com.mycompany.hethongbanve to javafx.fxml;
    exports com.mycompany.hethongbanve;
    exports pojo;
}
