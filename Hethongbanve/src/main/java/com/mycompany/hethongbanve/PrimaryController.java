package com.mycompany.hethongbanve;

import java.text.MessageFormat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class PrimaryController {
    @FXML private TextField txtTaikhoan;
    @FXML private TextField txtMatkhau;
    
    
    
    public void btn_dangnhap(ActionEvent event){
        String tk = txtTaikhoan.getText();
        String mk = txtMatkhau.getText();
      
    }
}
