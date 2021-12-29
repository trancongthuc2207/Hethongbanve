package com.mycompany.hethongbanve;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Service.Login_nhanvien;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pojo.chuyendi;
import pojo.nhanvien;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class Menu_QuantrivienController implements Initializable {
    @FXML private TableView<chuyendi> tbChuyendi;
    @FXML private TableView<nhanvien> tbNhanvien;
    @FXML private TextField txtMaChuyen;
    @FXML private TextField txtTenChuyen;
    @FXML private TextField txtGia;
    @FXML private TextField txtTGBD;
    @FXML private TextField txtTGKT;
    Sv_chuyendi maTam = new Sv_chuyendi();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.initTextField();
        this.loadTableViewCD();
        try {
            this.loadTableDataCD();
        } catch (SQLException ex) {
            Logger.getLogger(MenuChucNangController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //SET VALUE TEXTFIELD MA CHUYEN
        txtMaChuyen.setText(String.valueOf(maTam.getMaCDCurrent() + 1));
        this.loadTableViewNV();
        try {
            this.loadTableDataNV();
        } catch (SQLException ex) {
            Logger.getLogger(Menu_QuantrivienController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   

    // INIT TEXT FIELD
    public void initTextField(){
        txtMaChuyen.setText(null);
        txtTenChuyen.setText(null);
        txtGia.setText(null);
        txtTGBD.setText(null);
        txtTGKT.setText(null);
    }

    ////////CHUYEN DI
    public void loadTableViewCD(){
        TableColumn colMaCD = new TableColumn("MaChuyen");
        colMaCD.setCellValueFactory(new PropertyValueFactory("MaChuyen"));
        colMaCD.setPrefWidth(100);
        
        TableColumn colTenCD = new TableColumn("TenChuyen");
        colTenCD.setCellValueFactory(new PropertyValueFactory("TenChuyen"));
        colTenCD.setPrefWidth(220);
        
        TableColumn colGia = new TableColumn("Gia");
        colGia.setCellValueFactory(new PropertyValueFactory("Gia"));
        colGia.setPrefWidth(160);
        
        TableColumn colThoiGianBatDau = new TableColumn("ThoiGianBatDau");
        colThoiGianBatDau.setCellValueFactory(new PropertyValueFactory("ThoiGianBatDau"));
        colThoiGianBatDau.setPrefWidth(160);
        
        TableColumn colThoiGianKetThuc = new TableColumn("ThoiGianKetThuc");
        colThoiGianKetThuc.setCellValueFactory(new PropertyValueFactory("ThoiGianKetThuc"));
        colThoiGianKetThuc.setPrefWidth(160);
        
        this.tbChuyendi.getColumns().addAll(colMaCD,colTenCD,colGia,colThoiGianBatDau,colThoiGianKetThuc);
    }
    public void loadTableDataCD() throws SQLException{
        Sv_chuyendi listCD = new Sv_chuyendi();
        this.tbChuyendi.setItems(FXCollections.observableList(listCD.getChuyendi()));
    }
    
    ///////NHANVIEN
    public void loadTableViewNV(){
        TableColumn colMaNV = new TableColumn("MaNV");
        colMaNV.setCellValueFactory(new PropertyValueFactory("MaNV"));
        colMaNV.setPrefWidth(100);
        
        TableColumn colTenNV = new TableColumn("TenNV");
        colTenNV.setCellValueFactory(new PropertyValueFactory("TenNV"));
        colTenNV.setPrefWidth(220);
        
        TableColumn colCMND = new TableColumn("CMND");
        colCMND.setCellValueFactory(new PropertyValueFactory("CMND"));
        colCMND.setPrefWidth(160);
        
        TableColumn colSDT = new TableColumn("SDT");
        colSDT.setCellValueFactory(new PropertyValueFactory("SDT"));
        colSDT.setPrefWidth(160);
        
        this.tbNhanvien.getColumns().addAll(colMaNV,colTenNV,colCMND,colSDT);
    }
    public void loadTableDataNV() throws SQLException{
        Login_nhanvien lnv = new Login_nhanvien();
        this.tbNhanvien.setItems(FXCollections.observableList(lnv.getNhanvien()));
    }

    ///////THEM CHUYEN DI
    public void themChuyenDi(ActionEvent Event) throws SQLException{
        if(this.txtTenChuyen.getText() != null && this.txtGia.getText() != null && this.txtTGBD.getText() != null && this.txtTGKT.getText() != null)
        {
            chuyendi cd = new chuyendi(Integer.valueOf(this.txtMaChuyen.getText()), this.txtTenChuyen.getText(), Double.valueOf(this.txtGia.getText()), Timestamp.valueOf(this.txtTGBD.getText()), Timestamp.valueOf(this.txtTGKT.getText()));
            System.out.println(cd.getMaChuyen());
            Sv_chuyendi tcd = new Sv_chuyendi();
            try {
                tcd.themChuyenDi(cd);
                Utils.getBox("THEM THANH CONG", Alert.AlertType.INFORMATION).show();
                this.loadTableDataCD();
            } catch (SQLException sQLException) {
                Utils.getBox("THEM THAT BAI", Alert.AlertType.WARNING).show();
            }
        }
        else
            Utils.getBox("HAY NHAP DAY DU THONG TIN!!!", Alert.AlertType.WARNING).show();
    }

    //REFRESH TEXTFIELD
    public void refreshTextField(){
        this.loadTableDataCD();
        //SET VALUE TEXTFIELD MA CHUYEN
        txtMaChuyen.setText(String.valueOf(maTam.getMaCDCurrent() + 1));
        txtTenChuyen.setText(null);
        txtGia.setText(null);
        txtTGBD.setText(null);
        txtTGKT.setText(null);
    }
}
