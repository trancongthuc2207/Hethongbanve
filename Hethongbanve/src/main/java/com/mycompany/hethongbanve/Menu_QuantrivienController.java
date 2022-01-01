package com.mycompany.hethongbanve;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import Service.Sv_chuyendi;
import pojo.chuyendi;
import Service.Sv_xe;
import pojo.xe;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class Menu_QuantrivienController implements Initializable {
    @FXML private TableView<chuyendi> tbChuyenDi;
    @FXML private TextField txtTimKiemCD;
    @FXML private TextField txtMaChuyen;
    @FXML private TextField txtTenChuyen;
    @FXML private TextField txtGia;
    @FXML private TextField txtTGBD;
    @FXML private TextField txtTGKT;
    
    @FXML private TableView<xe> tbXe;
    @FXML private TextField txtTimKiemXe;
    @FXML private TextField txtMaXe;
    @FXML private TextField txtTenXe;
    @FXML private TextField txtBienSo;
    @FXML private TextField txtTrangThai;
    @FXML private TextField txtMaChuyenKN;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //KHOI DONG - LOAD DU LIEU
        this.loadTableViewCD();
        try {
            this.loadTableDataCD();
        } catch (SQLException ex) {
            Logger.getLogger(MenuChucNangController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.initTextFieldCD();
        } catch (SQLException ex) {
            Logger.getLogger(Menu_QuantrivienController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.loadTableViewXe();
        try {
            this.loadTableDataXe();
        } catch (SQLException ex) {
            Logger.getLogger(Menu_QuantrivienController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            this.initTextFieldXe();
        } catch (SQLException ex) {
            Logger.getLogger(Menu_QuantrivienController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //THEO DOI CHON DONG DU LIEU
        this.tbChuyenDi.setRowFactory(et -> {
            TableRow dong = new TableRow();
            dong.setOnMouseClicked(r -> {
                chuyendi cd = this.tbChuyenDi.getSelectionModel().getSelectedItem();
                this.truyenGiaTriCD(cd);
            });
            return dong;
        });

        //THEO DOI TIM KIEM THEO TEN
        this.txtTimKiemCD.textProperty().addListener((evt)-> {
            try {
                this.loadTableDataCDKw(this.txtTimKiemCD.getText());
            } catch (SQLException ex) {
                Logger.getLogger(Menu_QuantrivienController.class.getName()).log(Level.SEVERE, null, ex);
            } 
        });
    }
    
////////////////////////////////////////////////////////////////////CHUYEN DI////////////////////////////////////////////////////////////////////
    
    //TRUYEN GIA TRI TEXTFIELD DA SUA SANG DONG DANG CHON
    public void truyenGiaTriCD(chuyendi cd)
    {
         this.txtMaChuyen.setText(String.valueOf(cd.getMaChuyen()));
         this.txtTenChuyen.setText(cd.getTenChuyen());
         this.txtGia.setText(String.valueOf(cd.getGia()));
         this.txtTGBD.setText(String.valueOf(cd.getThoiGianBatDau()));
         this.txtTGKT.setText(String.valueOf(cd.getThoiGianKetThuc()));
    }
    
    //KHOI TAO TEXT FIELD
    public void initTextFieldCD() throws SQLException {
         Sv_chuyendi scd = new Sv_chuyendi();
         chuyendi initCD = scd.getMaToChuyen(Sv_chuyendi.getMaCDCurrent());
         this.truyenGiaTriCD(initCD);
    }
    
    //LOAD DU LIEU LEN TABLE
    public void loadTableViewCD(){
        TableColumn colMaCD = new TableColumn("Mã CD");
        colMaCD.setCellValueFactory(new PropertyValueFactory("MaChuyen"));
        colMaCD.setPrefWidth(100);
        
        TableColumn colTenCD = new TableColumn("Tên Chuyến");
        colTenCD.setCellValueFactory(new PropertyValueFactory("TenChuyen"));
        colTenCD.setPrefWidth(220);
        
        TableColumn colGia = new TableColumn("Giá");
        colGia.setCellValueFactory(new PropertyValueFactory("Gia"));
        colGia.setPrefWidth(160);
        
        TableColumn colThoiGianBatDau = new TableColumn("TG Bắt Đầu");
        colThoiGianBatDau.setCellValueFactory(new PropertyValueFactory("ThoiGianBatDau"));
        colThoiGianBatDau.setPrefWidth(160);
        
        TableColumn colThoiGianKetThuc = new TableColumn("TG Kết Thúc");
        colThoiGianKetThuc.setCellValueFactory(new PropertyValueFactory("ThoiGianKetThuc"));
        colThoiGianKetThuc.setPrefWidth(160);
        
        TableColumn colXoaCD = new TableColumn();
        colXoaCD.setCellFactory(p -> {
            Button xoaBtn = new Button("Xóa");
            xoaBtn.setOnAction(et -> {
                TableCell cellGet =  (TableCell)((Button)et.getSource()).getParent();
                chuyendi cd = (chuyendi)cellGet.getTableRow().getItem();
                this.xoaChuyenDi(cd);
            });
            TableCell cell = new TableCell();
            cell.setGraphic(xoaBtn);
            return cell;
        });

        this.tbChuyenDi.getColumns().addAll(colMaCD,colTenCD,colGia,colThoiGianBatDau,colThoiGianKetThuc, colXoaCD);
    }
    
    public void loadTableDataCD() throws SQLException {
        Sv_chuyendi scd = new Sv_chuyendi();
        this.tbChuyenDi.setItems(FXCollections.observableList(scd.getChuyendi()));
    }
    
    public void loadTableDataCDKw(String kw) throws SQLException {
        Sv_chuyendi scd = new Sv_chuyendi();
        this.tbChuyenDi.setItems(FXCollections.observableList(scd.getChuyendi(kw)));
    }

    //THEM CHUYEN DI
    public void themChuyenDiBtn(ActionEvent Event) throws SQLException{
        if (this.txtTenChuyen.getText() != null && this.txtGia.getText() != null && this.txtTGBD.getText() != null && this.txtTGKT.getText() != null)
        {
            chuyendi cd = new chuyendi(Integer.valueOf(this.txtMaChuyen.getText()), this.txtTenChuyen.getText(), Double.valueOf(this.txtGia.getText()), Timestamp.valueOf(this.txtTGBD.getText()), Timestamp.valueOf(this.txtTGKT.getText()));
            //System.out.println(cd.getMaChuyen());
            Sv_chuyendi tcd = new Sv_chuyendi();
            try {
                tcd.themChuyenDi(cd);
                this.loadTableDataCD();
                txtMaChuyen.setText(String.valueOf((Sv_chuyendi.getMaCDCurrent()) + 1)); //CAP NHAT GIA TRI TEXTFIELD MA CHUYEN
                Utils.getBox("Thêm thành công !!!", Alert.AlertType.INFORMATION).show();
            } catch (SQLException ex) {
                Utils.getBox("Thêm thất bại !!!", Alert.AlertType.ERROR).show();
            }
        }
        else
            Utils.getBox("HÃY NHẬP ĐẦY ĐỦ THÔNG TIN!", Alert.AlertType.WARNING).show();
    }

    //LAM MOI CHUYEN DI
    /**
     *
     * @throws SQLException
     */
    public void refreshTextFieldCD() throws SQLException{
        txtMaChuyen.setText(null);
        txtTenChuyen.setText(null);
        txtGia.setText(null);
        txtTGBD.setText(null);
        txtTGKT.setText(null);
        this.loadTableDataCD();
    }
    
    //SUA CHUYEN DI
    public void suaChuyenDiBtn(ActionEvent event) throws SQLException {
        chuyendi cd = this.tbChuyenDi.getSelectionModel().getSelectedItem();
        if (cd != null && Integer.valueOf(txtMaChuyen.getText()) == cd.getMaChuyen()) {
            Alert xacNhan = Utils.getBox("Xác nhận sửa chuyến đi?", Alert.AlertType.CONFIRMATION);
            xacNhan.showAndWait().ifPresent((ButtonType res) -> {
                if (res == ButtonType.OK) {
                    ////////////////////////////////////////////////////////////////////////////// TRUYEN GIA TRI TEXTFIELD DA SUA SANG DONG DANG CHON
                    cd.setTenChuyen(txtTenChuyen.getText());
                    cd.setGia(Double.valueOf(txtGia.getText()));
                    cd.setThoiGianBatDau(Timestamp.valueOf(txtTGBD.getText()));
                    cd.setThoiGianKetThuc(Timestamp.valueOf(txtTGKT.getText()));
                    ////////////////////////////////////////////////////////////////////////////////
                    Sv_chuyendi scd = new Sv_chuyendi();
                    try {
                        scd.suaChuyenDi(cd);
                        Menu_QuantrivienController.this.loadTableDataCD();
                        Utils.getBox("Sửa thành công !!!", Alert.AlertType.INFORMATION).show();
                    }catch (SQLException ex) {
                        Utils.getBox("Sửa thất bại !!!", Alert.AlertType.ERROR).show();
                    }
                }
            });
        } else
            Utils.getBox("MÃ CHUYẾN HOẶC DÒNG ĐANG CHỌN KHÔNG HỢP LỆ!", Alert.AlertType.WARNING).show();
    }

    //XOA CHUYEN DI
    public void xoaChuyenDi(chuyendi cd) {
    Alert xacNhanXoa = Utils.getBox("Xác nhận xóa chuyến đi?", Alert.AlertType.CONFIRMATION);
        xacNhanXoa.showAndWait().ifPresent(res -> {
            if (res == ButtonType.OK) {
                //chuyendi cd = this.tbChuyenDi.getSelectionModel().getSelectedItem();
                Sv_chuyendi xcd = new Sv_chuyendi();
                try {
                    xcd.xoaChuyenDi(cd);
                    this.loadTableDataCD();
                    Utils.getBox("Xóa thành công !!!", Alert.AlertType.INFORMATION).show();
                } catch (SQLException ex) {
                    Utils.getBox("Xóa thất bại !!!", Alert.AlertType.ERROR).show();
                }
            }
        });
    }

//////////////////////////////////////////////////////////////////////XE////////////////////////////////////////////////////////////////////

    //TRUYEN GIA TRI TEXTFIELD DA SUA SANG DONG DANG CHON
    public void truyenGiaTriXe(xe xe)
    {
         this.txtMaXe.setText(String.valueOf(xe.getMaXE()));
         this.txtTenXe.setText(xe.getTenXe());
         this.txtBienSo.setText(xe.getBienso());
         this.txtTrangThai.setText(String.valueOf(xe.getTrangthai()));
         this.txtMaChuyenKN.setText(String.valueOf(xe.getMaChuyen()));
    }
    
    //KHOI TAO TEXT FIELD
    public void initTextFieldXe() throws SQLException {
         Sv_xe sxe = new Sv_xe();
         xe initXe = sxe.getMaToXE(Sv_xe.getMaXeCurrent());
         this.truyenGiaTriXe(initXe);
    }
    
    //LOAD DU LIEU LEN TABLE
    public void loadTableViewXe(){
        TableColumn colMaXE = new TableColumn("Mã Xe");
        colMaXE.setCellValueFactory(new PropertyValueFactory("MaXE"));
        colMaXE.setPrefWidth(100);
        
        TableColumn colTenXe = new TableColumn("Tên Xe");
        colTenXe.setCellValueFactory(new PropertyValueFactory("TenXe"));
        colTenXe.setPrefWidth(100);
        
        TableColumn colBienso = new TableColumn("Biển Số");
        colBienso.setCellValueFactory(new PropertyValueFactory("Bienso"));
        colBienso.setPrefWidth(100);
        
        TableColumn colTrangthai = new TableColumn("Trạng Thái");
        colTrangthai.setCellValueFactory(new PropertyValueFactory("Trangthai"));
        colTrangthai.setPrefWidth(100);
        
        TableColumn colMaChuyen = new TableColumn("Mã CD");
        colMaChuyen.setCellValueFactory(new PropertyValueFactory("MaChuyen"));
        colMaChuyen.setPrefWidth(100);
        
        TableColumn colXoaXe = new TableColumn();
        colXoaXe.setCellFactory(p -> {
            Button xoaBtn = new Button("Xóa");
            xoaBtn.setOnAction(et -> {
            });
            TableCell cell = new TableCell();
            cell.setGraphic(xoaBtn);
            return cell;
        });
        
        this.tbXe.getColumns().addAll(colMaXE, colTenXe, colBienso, colTrangthai, colMaChuyen, colXoaXe);
    }
    
    public void loadTableDataXe() throws SQLException {
        Sv_xe sxe = new Sv_xe();
        this.tbXe.setItems(FXCollections.observableList(sxe.getXe()));
    }
    
    public void loadTableDataXeKw(String kw) throws SQLException {
        Sv_xe sxe = new Sv_xe();
        this.tbXe.setItems(FXCollections.observableList(sxe.getXe(kw)));
    }
    
    
}
