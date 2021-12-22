/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hethongbanve;

import Service.Login_nhanvien;
import Service.Sv_khachhang;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import pojo.khachhang;
import pojo.nhanvien;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class MenuChucNangController implements Initializable {
    private String tenNV = LoginController.tenNVCurrent;
    private static int SLM = 0;
    
    @FXML private TableView<khachhang> tbKhachhang;
    @FXML private TextField txtTenKH;
    @FXML private TextField txtCMND;
    @FXML private TextField txtSDT;
    @FXML private TextField txtChucVu;
    @FXML private TextField txtNhanVienTruc;
            
    @Override
   public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.txtTenKH.setText(null);
        this.txtCMND.setText(null);
        this.txtSDT.setText(null);
        try {
            this.loadThongTinNhanVienTruc();
        } catch (SQLException ex) {
            Logger.getLogger(MenuChucNangController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.loadTableView();
        try {
            this.loadTableData();
        } catch (SQLException ex) {
            Logger.getLogger(MenuChucNangController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }  
            
    public void loadTableView(){
        TableColumn colMaKH = new TableColumn("MaKH");
        colMaKH.setCellValueFactory(new PropertyValueFactory("MaKH"));
        colMaKH.setPrefWidth(100);
        
        TableColumn colTenKH = new TableColumn("TenKH");
        colTenKH.setCellValueFactory(new PropertyValueFactory("TenKH"));
        colTenKH.setPrefWidth(250);
        
        TableColumn colCMND = new TableColumn("CMND");
        colCMND.setCellValueFactory(new PropertyValueFactory("CMND"));
        colCMND.setPrefWidth(250);
        
        TableColumn colSDT = new TableColumn("SDT");
        colSDT.setCellValueFactory(new PropertyValueFactory("SDT"));
        colSDT.setPrefWidth(250);
        
        this.tbKhachhang.getColumns().addAll(colMaKH,colTenKH,colCMND,colSDT);
    }
    public void loadTableData() throws SQLException{
        Sv_khachhang listkh = new Sv_khachhang();
        SLM = listkh.getMaKHCurrent();
        this.tbKhachhang.setItems(FXCollections.observableList(listkh.getKhachHang()));
    }
    public void loadThongTinNhanVienTruc() throws SQLException{
        Login_nhanvien dsnv = new Login_nhanvien();
//        for(nhanvien i : dsnv.getNhanvien()){
//            
//        }
        txtNhanVienTruc.setText(tenNV);
    }  
    public void addKhachhang(ActionEvent Event) throws SQLException{
        this.loadTableData();
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
        this.loadTableData();
    }
}
