package com.mycompany.hethongbanve;

import Service.Login_nhanvien;
import config.Utils;
import java.io.IOException;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;


public class LoginController {
    public static String tenNVCurrent = "";
    public static boolean isLogin = false;
    @FXML private TextField txtTaikhoan;
    @FXML private TextField txtMatkhau;
 
    public void btn_dangnhap(ActionEvent event) throws SQLException, IOException{
        String tk = txtTaikhoan.getText();
        String mk = txtMatkhau.getText();
        String tk_mk = tk + mk;
        tenNVCurrent = txtTaikhoan.getText();
        Login_nhanvien lg = new Login_nhanvien();
        boolean check = false;
        check = lg.CheckLogin(tk_mk);
        if(check){
            isLogin = true;
            Utils.getBox("DANG NHAP THANH CONG", Alert.AlertType.INFORMATION).show();
            switchMenuChucNang();
        }
        else 
            Utils.getBox("DANG NHAP THAT BAI", Alert.AlertType.WARNING).show();
    }
    
    @FXML
    private void switchMenuChucNang() throws IOException {
        App.setRoot("MenuChucNang");
        
    }
    
}
