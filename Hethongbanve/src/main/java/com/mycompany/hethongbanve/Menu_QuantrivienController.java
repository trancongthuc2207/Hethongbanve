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
import pojo.chuyendi;
import pojo.nhanvien;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class Menu_QuantrivienController implements Initializable {
    @FXML private TableView<chuyendi> tbChuyenDi;
    @FXML private TableView<nhanvien> tbNhanVien;
    @FXML private TextField txtTimKiem;
    @FXML private TextField txtMaChuyen;
    @FXML private TextField txtTenChuyen;
    @FXML private TextField txtGia;
    @FXML private TextField txtTGBD;
    @FXML private TextField txtTGKT;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
        
        this.loadTableViewNV();
        try {
            this.loadTableDataNV();
        } catch (SQLException ex) {
            Logger.getLogger(Menu_QuantrivienController.class.getName()).log(Level.SEVERE, null, ex);
        }
            //this.initTextFieldNV(); //KHOI TAO TEXT FIELD

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
        this.txtTimKiem.textProperty().addListener((evt)-> {
            try {
                this.loadTableDataCDKw(this.txtTimKiem.getText());
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
        TableColumn colMaCD = new TableColumn("Mã");
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
        
        TableColumn colXoa = new TableColumn();
        colXoa.setCellFactory(p -> {
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

        this.tbChuyenDi.getColumns().addAll(colMaCD,colTenCD,colGia,colThoiGianBatDau,colThoiGianKetThuc, colXoa);
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

////////////////////////////////////////////////////////////////////NHAN VIEN////////////////////////////////////////////////////////////////////
    public void loadTableViewNV(){
        TableColumn colMaNV = new TableColumn("Mã");
        colMaNV.setCellValueFactory(new PropertyValueFactory("MaNV"));
        colMaNV.setPrefWidth(100);
        
        TableColumn colTenNV = new TableColumn("Tên Nhân Viên");
        colTenNV.setCellValueFactory(new PropertyValueFactory("TenNV"));
        colTenNV.setPrefWidth(220);
        
        TableColumn colCMND = new TableColumn("CMND/CCCD");
        colCMND.setCellValueFactory(new PropertyValueFactory("CMND"));
        colCMND.setPrefWidth(160);
        
        TableColumn colSDT = new TableColumn("Số Điện Thoại");
        colSDT.setCellValueFactory(new PropertyValueFactory("SDT"));
        colSDT.setPrefWidth(160);
        
        this.tbNhanVien.getColumns().addAll(colMaNV,colTenNV,colCMND,colSDT);
    }
    
    public void loadTableDataNV() throws SQLException{
        Login_nhanvien lnv = new Login_nhanvien();
        this.tbNhanVien.setItems(FXCollections.observableList(lnv.getNhanvien()));
    }
    
}
