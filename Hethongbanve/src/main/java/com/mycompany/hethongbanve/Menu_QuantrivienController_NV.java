/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hethongbanve;

import Service.Sv_chuyendi;
import config.Utils;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import pojo.chuyendi;
import pojo.nhanvien;

/**
 *
 * @author huqedgar_user
 */
public class Menu_QuantrivienController_NV {
//    @FXML private TableView<nhanvien> tbNhanVien;
    
     /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {    
//        this.loadTableViewNV();
//        try {
//            this.loadTableDataNV();
//        } catch (SQLException ex) {
//            Logger.getLogger(Menu_QuantrivienController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        this.initTextFieldNV(); //KHOI TAO TEXT FIELD
//}
//////////////////////////////////////////////////////////////////////NHAN VIEN////////////////////////////////////////////////////////////////////
//    public void loadTableViewNV(){
//        TableColumn colMaNV = new TableColumn("Mã");
//        colMaNV.setCellValueFactory(new PropertyValueFactory("MaNV"));
//        colMaNV.setPrefWidth(100);
//        
//        TableColumn colTenNV = new TableColumn("Tên Nhân Viên");
//        colTenNV.setCellValueFactory(new PropertyValueFactory("TenNV"));
//        colTenNV.setPrefWidth(220);
//        
//        TableColumn colCMND = new TableColumn("CMND/CCCD");
//        colCMND.setCellValueFactory(new PropertyValueFactory("CMND"));
//        colCMND.setPrefWidth(160);
//        
//        TableColumn colSDT = new TableColumn("Số Điện Thoại");
//        colSDT.setCellValueFactory(new PropertyValueFactory("SDT"));
//        colSDT.setPrefWidth(160);
//        
//        this.tbNhanVien.getColumns().addAll(colMaNV,colTenNV,colCMND,colSDT);
//    }
//    
//    public void loadTableDataNV() throws SQLException{
//        Login_nhanvien lnv = new Login_nhanvien();
//        this.tbNhanVien.setItems(FXCollections.observableList(lnv.getNhanvien()));
//    }
//
}
