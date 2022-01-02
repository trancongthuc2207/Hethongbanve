/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hethongbanve;

import Service.Sv_CheckOption;
import Service.Sv_vexe;
import Service.Sv_xe;
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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pojo.vexe;
import pojo.xe;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class Menu_XuLyVeController implements Initializable {
    private static vexe veCurr = new vexe();
    public static boolean clickHoanVe = false;
    @FXML private TableView<vexe> tbVeXE;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.loadTableViewVeXE();
        try {
            this.loadTableDataVeXE();
        } catch (SQLException ex) {
            Logger.getLogger(Menu_DatveController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void loadTableViewVeXE(){
        TableColumn colMaVE = new TableColumn("MaVE");
        colMaVE.setCellValueFactory(new PropertyValueFactory("MaVE"));
        colMaVE.setPrefWidth(100);
        
        TableColumn colThoigianbatdau = new TableColumn("Thoigianbatdau");
        colThoigianbatdau.setCellValueFactory(new PropertyValueFactory("Thoigianbatdau"));
        colThoigianbatdau.setPrefWidth(150);
        
        TableColumn colSoghe = new TableColumn("Soghe");
        colSoghe.setCellValueFactory(new PropertyValueFactory("Soghe"));
        colSoghe.setPrefWidth(100);
        
        TableColumn colMaChuyen = new TableColumn("MaChuyen");
        colMaChuyen.setCellValueFactory(new PropertyValueFactory("MaChuyen"));
        colMaChuyen.setPrefWidth(120);
        
        TableColumn colMaKH = new TableColumn("MaKH");
        colMaKH.setCellValueFactory(new PropertyValueFactory("MaKH"));
        colMaKH.setPrefWidth(120);
        
        TableColumn colMaNV = new TableColumn("MaNV");
        colMaNV.setCellValueFactory(new PropertyValueFactory("MaNV"));
        colMaNV.setPrefWidth(120);
        
        TableColumn colMaXE = new TableColumn("MaXE");
        colMaXE.setCellValueFactory(new PropertyValueFactory("MaXE"));
        colMaXE.setPrefWidth(120);
        
        TableColumn colNgayin = new TableColumn("Ngayin");
        colNgayin.setCellValueFactory(new PropertyValueFactory("Ngayin"));
        colNgayin.setPrefWidth(150);
        
        TableColumn colTrangthai = new TableColumn("Trangthai");
        colTrangthai.setCellValueFactory(new PropertyValueFactory("Trangthai"));
        colTrangthai.setPrefWidth(120);
        
        this.tbVeXE.getColumns().addAll(colMaVE,colThoigianbatdau,colSoghe,colMaChuyen,colMaKH,colMaNV,colMaXE,colNgayin,colTrangthai);
    }
    public void loadTableDataVeXE() throws SQLException{
        Sv_vexe listVe = new Sv_vexe();
        this.tbVeXE.setItems(FXCollections.observableList(listVe.getVeXe()));
    }
    ////////////VE CLICK
    public static vexe getVeCurr(){
        return veCurr;
    }
   
    /////////////
    public void capNhatVeHieuLuc(ActionEvent event) throws SQLException{
        Sv_vexe dvCN = new Sv_vexe();
        dvCN.capNhatVe();
        this.loadTableDataVeXE();
    }
    /////////////
    public void thuHoiVeButton(ActionEvent event) throws SQLException{
        Sv_vexe svVe = new Sv_vexe();
        Sv_CheckOption ckOP = new Sv_CheckOption();
        vexe ve = new vexe();
        ve = this.tbVeXE.getSelectionModel().getSelectedItem();
        if(ckOP.isCanForDeleteVe(ve)){
            svVe.thuHoiVe(ve);
            Utils.getBox("THU HỒI THÀNH CÔNG!", Alert.AlertType.INFORMATION).show();
            this.loadTableDataVeXE();
        }
        else
            Utils.getBox("VÉ ĐÃ NHẬN, THU HỒI THẤT BẠI!", Alert.AlertType.INFORMATION).show();
    }
    
    public void nhanVeButton(ActionEvent event) throws SQLException{
        Sv_vexe svVe = new Sv_vexe();
        Sv_CheckOption ckOP = new Sv_CheckOption();
        vexe ve = new vexe();
        ve = this.tbVeXE.getSelectionModel().getSelectedItem();
        if(ckOP.isVeThuHoi(ve) == false){
            svVe.nhanVe(ve);
            Utils.getBox("VÉ ĐÃ NHẬN THÀNH CÔNG!", Alert.AlertType.INFORMATION).show();
            this.loadTableDataVeXE();
        }
        else
            Utils.getBox("VÉ ĐÃ ĐƯỢC NHẬN HOẶC THU HỒI", Alert.AlertType.INFORMATION).show();
    }
    
    public void hoanVeButton(ActionEvent event) throws SQLException, IOException{
        Sv_CheckOption ckOP = new Sv_CheckOption();
        vexe vx = new vexe();
        vx = this.tbVeXE.getSelectionModel().getSelectedItem();
        this.veCurr = vx;
        if(ckOP.isVeCanTransfer(vx) == true && ckOP.isVeChuaNhan(vx)){
            this.clickHoanVe = true;
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Menu_Datve.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("MENU ĐẶT VÉ");
            stage.show(); 
        }
        else
            Utils.getBox("VÉ ĐÃ ĐƯỢC NHẬN HOẶC THU HỒI", Alert.AlertType.INFORMATION).show();
    }
}
