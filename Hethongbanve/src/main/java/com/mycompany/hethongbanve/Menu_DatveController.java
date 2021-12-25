package com.mycompany.hethongbanve;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Service.Login_nhanvien;
import Service.Sv_chuyendi;
import Service.Sv_khachhang;
import Service.Sv_xe;
import config.Utils;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import pojo.chuyendi;
import pojo.khachhang;
import pojo.xe;
import pojo.xe_ghe;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class Menu_DatveController implements Initializable {
    @FXML ComboBox<String> cbXe_ghetrong;
    @FXML private TableView<chuyendi> tbChuyendi;
    @FXML private TableView<khachhang> tbKhachhang;
    @FXML private TableView<xe> tbXe;
    ////BANG THONG TIN CHUYEN DI
    @FXML private TextField txtMaCD; 
    @FXML private TextField txtTenCD;
    @FXML private TextField txtGia;
    @FXML private TextField txtThoiGianBatDau;
    ///BANG THONG TIN KHACH HANG
    @FXML private TextField txtMaKH;
    @FXML private TextField txtTenKH;
    ////BANG THONG TIN NHAN VIEN TRUC
    @FXML private TextField txtMaNV;
    @FXML private TextField txtTenNV;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /////////TABLE VIEW
        ///CHUYEN DI
        this.loadTableViewCD();
        try {
            this.loadTableDataCD();
        } catch (SQLException ex) {
            Logger.getLogger(MenuChucNangController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ///KHACH HANG
        this.loadTableViewKH();
        try {
            this.loadTableDataKH();
        } catch (SQLException ex) {
            Logger.getLogger(Menu_DatveController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ///XE
        this.loadTableViewXE();
        try {
            this.loadTableDataXE();
        } catch (SQLException ex) {
            Logger.getLogger(Menu_DatveController.class.getName()).log(Level.SEVERE, null, ex);
        }
        /////////COMBO BOX GHE
        Sv_xe gt = new Sv_xe();
        try {
            // TODO
            this.cbXe_ghetrong.setItems(FXCollections.observableList(gt.getGheTrong(1)));
        } catch (SQLException ex) {
            Logger.getLogger(Menu_DatveController.class.getName()).log(Level.SEVERE, null, ex);
        }
        /////////NHAN VIEN TRUC
        Login_nhanvien lg = new Login_nhanvien();
        try {
            txtTenNV.setText(lg.TenNhanVienCurrent(LoginController.tenNVCurrent));
        } catch (SQLException ex) {
            Logger.getLogger(Menu_DatveController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            txtMaNV.setText(String.valueOf(lg.MaNhanVienCurrent(LoginController.tenNVCurrent)));
        } catch (SQLException ex) {
            Logger.getLogger(Menu_DatveController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    
    /////////CHUYEN DI
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
    ////////KHACH HANG
    public void loadTableViewKH(){
        TableColumn colMaKH = new TableColumn("MaKH");
        colMaKH.setCellValueFactory(new PropertyValueFactory("MaKH"));
        colMaKH.setPrefWidth(100);
        
        TableColumn colTenKH = new TableColumn("TenKH");
        colTenKH.setCellValueFactory(new PropertyValueFactory("TenKH"));
        colTenKH.setPrefWidth(160);
        
        TableColumn colCMND = new TableColumn("CMND");
        colCMND.setCellValueFactory(new PropertyValueFactory("CMND"));
        colCMND.setPrefWidth(100);
        
        TableColumn colSDT = new TableColumn("SDT");
        colSDT.setCellValueFactory(new PropertyValueFactory("SDT"));
        colSDT.setPrefWidth(100);
        
        this.tbKhachhang.getColumns().addAll(colMaKH,colTenKH,colCMND,colSDT);
    }
    public void loadTableDataKH() throws SQLException{
        Sv_khachhang listkh = new Sv_khachhang();
        this.tbKhachhang.setItems(FXCollections.observableList(listkh.getKhachHang()));
    }
    ////////XE
    public void loadTableViewXE(){
        TableColumn colMaXE = new TableColumn("MaXE");
        colMaXE.setCellValueFactory(new PropertyValueFactory("MaXE"));
        colMaXE.setPrefWidth(100);
        
        TableColumn colTenXe = new TableColumn("TenXe");
        colTenXe.setCellValueFactory(new PropertyValueFactory("TenXe"));
        colTenXe.setPrefWidth(220);
        
        TableColumn colBienso = new TableColumn("Bienso");
        colBienso.setCellValueFactory(new PropertyValueFactory("Bienso"));
        colBienso.setPrefWidth(160);
        
        TableColumn colTrangthai = new TableColumn("Trangthai");
        colTrangthai.setCellValueFactory(new PropertyValueFactory("Trangthai"));
        colTrangthai.setPrefWidth(160);
        
    
        this.tbXe.getColumns().addAll(colMaXE,colTenXe,colBienso,colTrangthai);
    }
    public void loadTableDataXE() throws SQLException{
        Sv_xe listXe = new Sv_xe();
        this.tbXe.setItems(FXCollections.observableList(listXe.getXe()));
    }
    
    ////// CAC BUTTON LOAD DU LIEU THONG TIN
    //LOAD CHUYEN DI
    public void loadMaToCD(ActionEvent event) throws SQLException{
        Sv_chuyendi loadcd = new Sv_chuyendi();
        chuyendi cd = new chuyendi();
        if(Integer.parseInt(txtMaCD.getText()) > 0 && Integer.parseInt(txtMaCD.getText()) <= loadcd.getChuyendi().size()){
            cd = loadcd.getMaToChuyen(Integer.parseInt(txtMaCD.getText()));
            if(cd != null){
                txtTenCD.setText(cd.getTenChuyen());
                txtGia.setText(String.valueOf(cd.getGia()));
                txtThoiGianBatDau.setText(cd.getThoiGianBatDau().toString());
            }
        }
        else
            Utils.getBox("SỐ MÃ CHUYẾN KHÔNG HỢP LỆ", Alert.AlertType.WARNING).show();
    }
    //LOAD KHACH HANG
    public void loadMaToKH(ActionEvent event) throws SQLException{
        Sv_khachhang loadKH = new Sv_khachhang();
        khachhang kh = new khachhang();
        if(Integer.parseInt(txtMaCD.getText()) > 0 && Integer.parseInt(txtMaKH.getText()) <= loadKH.getKhachHang().size()){
            kh = loadKH.getMaToKH(Integer.parseInt(txtMaKH.getText()));
            if(kh != null){
                txtTenKH.setText(kh.getTenKH());
            }
        }
        else
            Utils.getBox("SỐ MÃ KHÁCH HÀNG KHÔNG HỢP LỆ", Alert.AlertType.WARNING).show();
    }
}
