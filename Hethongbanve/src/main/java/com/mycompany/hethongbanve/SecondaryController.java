package com.mycompany.hethongbanve;

import Service.Sv_khachhang;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pojo.khachhang;

public class SecondaryController {
//    @FXML
//    private void switchToPrimary() throws IOException {
//        App.setRoot("primary");
//    }
    @FXML private TableView<khachhang> tbKhachhang;
    
    
    
   
            
            
    public void loadTableView(){
        TableColumn colMaKH = new TableColumn("Ma KH");
        colMaKH.setCellValueFactory(new PropertyValueFactory("MaKH"));
        colMaKH.setPrefWidth(100);
        
        TableColumn colTenKH = new TableColumn("Ten KH");
        colMaKH.setCellValueFactory(new PropertyValueFactory("TenKH"));
        colMaKH.setPrefWidth(200);
        
        TableColumn colCMND = new TableColumn("CMND");
        colMaKH.setCellValueFactory(new PropertyValueFactory("CMND"));
        colMaKH.setPrefWidth(100);
        
        TableColumn colSDT = new TableColumn("SDT");
        colMaKH.setCellValueFactory(new PropertyValueFactory("SDT"));
        colMaKH.setPrefWidth(100);
        
        this.tbKhachhang.getColumns().addAll(colMaKH,colTenKH,colCMND,colSDT);
    }
    
    public void loadTableData() throws SQLException{
        Sv_khachhang listkh = new Sv_khachhang();
        this.tbKhachhang.setItems(FXCollections.observableArrayList(listkh.getKhachHang()));
    }
}