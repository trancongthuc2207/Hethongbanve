/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hethongbanve;

import config.Utils;
import java.sql.SQLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import Service.Sv_nhanvien;
import pojo.nhanvien;

/**
 * FXML Controller class
 * 
 * @author huqedgar_user
 */
public class Menu_QuantrivienController_NV implements Initializable {
    @FXML private TableView<nhanvien> tbNhanVien;
    @FXML private TextField txtTimKiemNV;
    
     /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {    
        this.loadTableViewNV();
        try {
            this.loadTableDataNV();
        } catch (SQLException ex) {
            Logger.getLogger(Menu_QuantrivienController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //TIM KIEM
        this.txtTimKiemNV.textProperty().addListener((evt)-> {
            try {
                this.loadTableDataNVKw(this.txtTimKiemNV.getText());
            } catch (SQLException ex) {
                Logger.getLogger(Menu_QuantrivienController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
}
////////////////////////////////////////////////////////////////////NHAN VIEN////////////////////////////////////////////////////////////////////
    public void loadTableViewNV(){
        TableColumn colMaNV = new TableColumn("Mã");
        colMaNV.setCellValueFactory(new PropertyValueFactory("MaNV"));
        colMaNV.setPrefWidth(100);
        
        TableColumn colTenNV = new TableColumn("Tên Nhân Viên");
        colTenNV.setCellValueFactory(new PropertyValueFactory("TenNV"));
        colTenNV.setPrefWidth(220);
        
        TableColumn colCMND = new TableColumn("CMND/CCCD");
        colCMND.setCellValueFactory(new PropertyValueFactory("CMND"));
        colCMND.setPrefWidth(160);
        
        TableColumn colSDT = new TableColumn("Số Điện Thoại");
        colSDT.setCellValueFactory(new PropertyValueFactory("SDT"));
        colSDT.setPrefWidth(160);
        
        this.tbNhanVien.getColumns().addAll(colMaNV,colTenNV,colCMND,colSDT);
    }

    public void loadTableDataNV() throws SQLException {
        Sv_nhanvien nv = new Sv_nhanvien();
        this.tbNhanVien.setItems(FXCollections.observableList(nv.getNhanVien()));
    }
    
    public void loadTableDataNVKw(String kw) throws SQLException {
        Sv_nhanvien nv = new Sv_nhanvien();
        this.tbNhanVien.setItems(FXCollections.observableList(nv.getNhanVien(kw)));
    }
    
    public void camNVBtn(ActionEvent event) {
        nhanvien nv = this.tbNhanVien.getSelectionModel().getSelectedItem();
        if (nv != null) {
            Alert xacNhan = Utils.getBox("Xác nhận cấm nhân viên này sử dụng?", Alert.AlertType.CONFIRMATION);
            xacNhan.showAndWait().ifPresent((ButtonType res) -> {
                if (res == ButtonType.OK) {
                    Sv_nhanvien banNV = new Sv_nhanvien();
                    try {
                        banNV.camSuDung(nv);
                        this.loadTableDataNV();
                        Utils.getBox("Cấm thành công !!!", Alert.AlertType.INFORMATION).show();
                    }catch (SQLException ex) {
                        Utils.getBox("Cấm thất bại !!!", Alert.AlertType.ERROR).show();
                    }
                }
            });
        } else
            Utils.getBox("Chưa chọn dòng từ bảng!", Alert.AlertType.WARNING).show();
    }
    
    public void choPhepNVBtn(ActionEvent event) {
        nhanvien nv = this.tbNhanVien.getSelectionModel().getSelectedItem();
        if (nv != null) {
            Alert xacNhan = Utils.getBox("Xác nhận cho phép nhân viên này sử dụng?", Alert.AlertType.CONFIRMATION);
            xacNhan.showAndWait().ifPresent((ButtonType res) -> {
                if (res == ButtonType.OK) {
                    Sv_nhanvien unBanNV = new Sv_nhanvien();
                    try {
                        unBanNV.choPhepSuDung(nv);
                        this.loadTableDataNV();
                        Utils.getBox("Mở khóa thành công !!!", Alert.AlertType.INFORMATION).show();
                    }catch (SQLException ex) {
                        Utils.getBox("Mở khóa thất bại !!!", Alert.AlertType.ERROR).show();
                    }
                }
            });
        } else
            Utils.getBox("Chưa chọn dòng từ bảng!", Alert.AlertType.WARNING).show();
    }
    
}
