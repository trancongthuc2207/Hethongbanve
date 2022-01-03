/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hethongbanve;

import Service.Login_nhanvien;
import Service.Sv_chuyendi;
import Service.Sv_khachhang;
import Service.Sv_vexe;
import config.Utils;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pojo.chuyendi;
import pojo.khachhang;
import pojo.nhanvien;
import pojo.vexe;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class MenuChucNangController implements Initializable {
    private static int SLM = 0;
    @FXML private TableView<vexe> tbVeXE;
    @FXML private TableView<khachhang> tbKhachhang;
    @FXML private TextField txtTenKH;
    @FXML private TextField txtCMND;
    @FXML private TextField txtSDT;
    @FXML private Label txtMaKH;
    @FXML private Label txtNhanVienTruc;
    @FXML private Label txtChucVuNhanVienTruc;
    @FXML private Label txtTrangthainhanvien;
    @Override
   public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //////////THONG TIN NHAN VIEN TRUC
        try {
            this.loadThongTinNhanVienTruc();
        } catch (SQLException ex) {
            Logger.getLogger(MenuChucNangController.class.getName()).log(Level.SEVERE, null, ex);
        }
        /////////THONG TIN KHACH HANG
        this.loadTableViewKH();
        try {
            this.loadTableDataKH();
        } catch (SQLException ex) {
            Logger.getLogger(MenuChucNangController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Sv_khachhang sv_kh = new Sv_khachhang();
        this.txtMaKH.setText(String.valueOf(sv_kh.getMaKHCurrent()+1));
        
        this.loadTableViewVeXE();
        try {
            this.loadTableDataVeXE();
        } catch (SQLException ex) {
            Logger.getLogger(MenuChucNangController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    //////////////////////KHACHHANG        
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
    public void loadThongTinNhanVienTruc() throws SQLException{
        Login_nhanvien dsnv = new Login_nhanvien();
        if(dsnv.ChucVuNhanVienCurrent(LoginController.tenNVCurrent) != 2)
            txtChucVuNhanVienTruc.setText("NHÂN VIÊN");
        else
            txtChucVuNhanVienTruc.setText("QUẢN TRỊ VIÊN");
        txtNhanVienTruc.setText(dsnv.TenNhanVienCurrent(LoginController.tenNVCurrent));
        if(dsnv.getTaiKhoanNVFromTK(LoginController.tenNVCurrent).getChucvu() == 0)
            txtTrangthainhanvien.setText("ĐÃ BỊ CẤM SỬ DỤNG");
        else
            txtTrangthainhanvien.setText("BÌNH THƯỜNG");
    }  
    public void addKhachhang(ActionEvent Event) throws SQLException{
        if(!this.txtTenKH.getText().isEmpty() && !this.txtCMND.getText().isEmpty() && !this.txtSDT.getText().isEmpty() && inputFullKH())
        {
            Sv_khachhang nKH = new Sv_khachhang();
            khachhang kh = new khachhang(nKH.getMaKHCurrent() + 1, this.txtTenKH.getText(), this.txtCMND.getText(), this.txtSDT.getText());
            nKH.addKhachhang(kh);
            Utils.getBox("THÊM KHÁCH HÀNG THÀNH CÔNG", Alert.AlertType.INFORMATION).show();
            this.refreshTextFieldKH();
            System.out.println(txtTenKH.getText()+ "\n");
        }
        else
            Utils.getBox("BẠN CHƯA NHẬP ĐẦY ĐỦ THÔNG TIN KHÁCH HÀNG", Alert.AlertType.WARNING).show();
    }
    ////////VE
    public void loadTableViewVeXE(){
        TableColumn colMaVE = new TableColumn("Vé");
        colMaVE.setCellValueFactory(new PropertyValueFactory("MaVE"));
        colMaVE.setPrefWidth(60);
        
        TableColumn colThoigianbatdau = new TableColumn("Bắt đầu");
        colThoigianbatdau.setCellValueFactory(new PropertyValueFactory("Thoigianbatdau"));
        colThoigianbatdau.setPrefWidth(120);
        
        TableColumn colSoghe = new TableColumn("Ghế");
        colSoghe.setCellValueFactory(new PropertyValueFactory("Soghe"));
        colSoghe.setPrefWidth(50);
        
        TableColumn colMaChuyen = new TableColumn("Chuyến");
        colMaChuyen.setCellValueFactory(new PropertyValueFactory("MaChuyen"));
        colMaChuyen.setPrefWidth(50);
        
        TableColumn colMaKH = new TableColumn("KH");
        colMaKH.setCellValueFactory(new PropertyValueFactory("MaKH"));
        colMaKH.setPrefWidth(50);
        
        TableColumn colMaNV = new TableColumn("NV");
        colMaNV.setCellValueFactory(new PropertyValueFactory("MaNV"));
        colMaNV.setPrefWidth(50);
        
        TableColumn colMaXE = new TableColumn("XE");
        colMaXE.setCellValueFactory(new PropertyValueFactory("MaXE"));
        colMaXE.setPrefWidth(50);
        
        TableColumn colNgayin = new TableColumn("Ngàyin");
        colNgayin.setCellValueFactory(new PropertyValueFactory("Ngayin"));
        colNgayin.setPrefWidth(120);
        
        TableColumn colTrangthai = new TableColumn("Trạngthái");
        colTrangthai.setCellValueFactory(new PropertyValueFactory("Trangthai"));
        colTrangthai.setPrefWidth(50);
        
        this.tbVeXE.getColumns().addAll(colMaVE,colThoigianbatdau,colSoghe,colMaChuyen,colMaKH,colMaNV,colMaXE,colNgayin,colTrangthai);
    }
    public void loadTableDataVeXE() throws SQLException{
        Sv_vexe listVe = new Sv_vexe();
        this.tbVeXE.setItems(FXCollections.observableList(listVe.getVeXe()));
    }
    
    ////////BUTTON
    public void ChucNangQuanTriButton(ActionEvent event) throws IOException, SQLException{
       Login_nhanvien dsnv = new Login_nhanvien();

       if(dsnv.ChucVuNhanVienCurrent(LoginController.tenNVCurrent) == 2){
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Menu_Quantrivien.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("MENU QUẢN TRỊ VIÊN");
            stage.show();   
       }
       else
           Utils.getBox("Bạn không đủ quyền để sử dụng!", Alert.AlertType.WARNING).show();
    }
    
    public void ChucNangDatVe(ActionEvent event) throws IOException, SQLException{
       Login_nhanvien dsnv = new Login_nhanvien();

       if(dsnv.ChucVuNhanVienCurrent(LoginController.tenNVCurrent) == 2 || dsnv.ChucVuNhanVienCurrent(LoginController.tenNVCurrent) == 1){
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Menu_Datve.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("MENU ĐẶT VÉ");
            stage.show();   
       }
       else
           Utils.getBox("Bạn không đủ quyền để sử dụng!", Alert.AlertType.WARNING).show();
    }
    
    public void ChucNangXuLyVe(ActionEvent event) throws IOException, SQLException{
       Login_nhanvien dsnv = new Login_nhanvien();

       if(dsnv.ChucVuNhanVienCurrent(LoginController.tenNVCurrent) == 2 || dsnv.ChucVuNhanVienCurrent(LoginController.tenNVCurrent) == 1){
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Menu_XuLyVe.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("MENU XỬ LÝ VÉ");
            stage.show();   
       }
       else
           Utils.getBox("Bạn không đủ quyền để sử dụng!", Alert.AlertType.WARNING).show();
    }
    
    public void refreshTextFieldKH() throws SQLException{
        this.txtTenKH.setText(null);
        this.txtCMND.setText(null);
        this.txtSDT.setText(null);
        Sv_khachhang svkh = new Sv_khachhang();
        this.loadTableDataKH();
        this.txtMaKH.setText(String.valueOf(svkh.getMaKHCurrent()+1));
    }
    public boolean inputFullKH(){
        if(txtTenKH != null && txtCMND != null && txtSDT != null)
            return true;
        return false;
    }
}
