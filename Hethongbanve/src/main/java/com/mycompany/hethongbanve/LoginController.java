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
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class LoginController {
    public static String tenNVCurrent = "";     //// TEN TAI KHOAN CURRENT
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
        if(!(txtTaikhoan).getText().isEmpty() || !(txtMatkhau).getText().isEmpty()){
            if(check){
                isLogin = true;
                switchMenuChucNang();
                Utils.getBox("ĐĂNG NHẬP THÀNH CÔNG!!", Alert.AlertType.INFORMATION).show();
            }
            else 
                Utils.getBox("SAI TÀI KHOẢN HOẶC MẬT KHẨU!!", Alert.AlertType.WARNING).show();
        }
        else
            Utils.getBox("BẠN CHƯA NHẬP ĐẦY ĐỦ TÀI KHOẢN HOẶC MẬT KHẨU", Alert.AlertType.WARNING).show();
    }
    
    @FXML
    private void switchMenuChucNang() throws IOException {
        //App.setRoot("MenuChucNang");
        
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("MenuChucNang.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("MENU HỆ THỐNG BÁN VÉ XE");
        stage.show();
        
    }
    
}
