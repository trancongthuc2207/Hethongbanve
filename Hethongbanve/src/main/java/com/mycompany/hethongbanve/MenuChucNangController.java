/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hethongbanve;

import Service.Login_nhanvien;
import Service.Sv_chuyendi;
import Service.Sv_khachhang;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pojo.chuyendi;
import pojo.khachhang;
import pojo.nhanvien;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class MenuChucNangController implements Initializable {
//    private String tenNV = LoginController.tenNVCurrent;
    private static int SLM = 0;
    @FXML private TableView<chuyendi> tbChuyendi;
    @FXML private TableView<khachhang> tbKhachhang;
    @FXML private TextField txtTenKH;
    @FXML private TextField txtCMND;
    @FXML private TextField txtSDT;
    @FXML private TextField txtChucVu;
    @FXML private TextField txtNhanVienTruc;
    @FXML private TextField txtChucVuNhanVienTruc;
            
    @Override
   public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.txtTenKH.setText(null);
        this.txtCMND.setText(null);
        this.txtSDT.setText(null);
        //////////THONG TIN NHAN VIEN TRUC
        try {
            this.loadThongTinNhanVienTruc();
        } catch (SQLException ex) {
            Logger.getLogger(MenuChucNangController.class.getName()).log(Level.SEVERE, null, ex);
        }
        /////////THONG TIN KHACH HANG
        this.loadTableViewKH();
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
        SLM = listkh.getMaKHCurrent();
        this.tbKhachhang.setItems(FXCollections.observableList(listkh.getKhachHang()));
    }
    public void loadThongTinNhanVienTruc() throws SQLException{
        Login_nhanvien dsnv = new Login_nhanvien();
        if(dsnv.ChucVuNhanVienCurrent(LoginController.tenNVCurrent) == 1)
            txtChucVuNhanVienTruc.setText("NHÂN VIÊN");
        else
            txtChucVuNhanVienTruc.setText("QUẢN TRỊ VIÊN");
        txtNhanVienTruc.setText(dsnv.TenNhanVienCurrent(LoginController.tenNVCurrent));
    }  
    public void addKhachhang(ActionEvent Event) throws SQLException{
        this.loadTableDataKH();
        if(this.txtTenKH.getText() != null && this.txtCMND.getText() != null && this.txtSDT.getText() != null)
        {
            System.out.println(SLM+1);
            khachhang kh = new khachhang(SLM+1,this.txtTenKH.getText(), this.txtCMND.getText(), this.txtSDT.getText());
            
            System.out.println(kh.getMaKH());
            Sv_khachhang nKH = new Sv_khachhang();
            try {
                nKH.addKhachhang(kh);
                Utils.getBox("Them Thanh Cong", Alert.AlertType.INFORMATION).show();
            } catch (SQLException sQLException) {
                Utils.getBox("Them That Bai", Alert.AlertType.WARNING).show();
            }
        }
        else
            Utils.getBox("Moi nhap du thong tin", Alert.AlertType.WARNING).show();
        this.loadTableDataKH();
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
}
