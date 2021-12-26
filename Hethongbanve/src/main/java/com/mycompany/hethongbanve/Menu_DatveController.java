package com.mycompany.hethongbanve;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Service.Login_nhanvien;
import Service.Sv_chuyendi;
import Service.Sv_khachhang;
import Service.Sv_vexe;
import Service.Sv_xe;
import config.Utils;
import java.net.URL;

import java.util.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import pojo.vexe;
import pojo.xe;
import pojo.xe_ghe;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class Menu_DatveController implements Initializable {
    public static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
    public static final SimpleDateFormat DateFormat = new SimpleDateFormat(DATE_FORMAT_NOW);
    //////VE CUR FOR CHECK UPDATE VE
    private static int SLMV = 0;
    private vexe veCur = new vexe();
    //////
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
    ////BANG THONG TIN XE
    @FXML private TextField txtMaXE;
    @FXML private TextField txtTenXe;
    @FXML private TextField txtTrangthai;
    ////BANG THONG TIN NGAY GIO HE THONG
    @FXML private TextField txtGioIn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /////////Init txtField
        this.refeshCD();
        this.refreshKH();
        this.refreshXE();
        
        Date dateCur = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        
        this.txtGioIn.setText(sdf.format(dateCur));
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
//        /////////COMBO BOX GHE
//        Sv_xe gt = new Sv_xe();
//        try {
//            // TODO
//            this.cbXe_ghetrong.setItems(FXCollections.observableList(gt.getGheTrong(Integer.parseInt(txtMaCD.getText()))));
//        } catch (SQLException ex) {
//            Logger.getLogger(Menu_DatveController.class.getName()).log(Level.SEVERE, null, ex);
//        }
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
        
        TableColumn colMaChuyen = new TableColumn("MaChuyen");
        colMaChuyen.setCellValueFactory(new PropertyValueFactory("MaChuyen"));
        colMaChuyen.setPrefWidth(160);
        
        this.tbXe.getColumns().addAll(colMaXE,colTenXe,colBienso,colTrangthai,colMaChuyen);
    }
    public void loadTableDataXE() throws SQLException{
        Sv_xe listXe = new Sv_xe();
        this.tbXe.setItems(FXCollections.observableList(listXe.getXe()));
    }
    
    ////// CAC BUTTON LOAD DU LIEU THONG TIN
    //LOAD BUTTON CHUYEN DI
    public void loadMaToCD(ActionEvent event) throws SQLException{
        Sv_chuyendi loadcd = new Sv_chuyendi();
        chuyendi cd = new chuyendi();
        if(txtMaCD.getText() != null){
            if(Integer.parseInt(txtMaCD.getText()) > 0 && Integer.parseInt(txtMaCD.getText()) <= loadcd.getChuyendi().size()){
                cd = loadcd.getMaToChuyen(Integer.parseInt(txtMaCD.getText()));
                if(cd != null){
                    txtTenCD.setText(cd.getTenChuyen());
                    txtGia.setText(String.valueOf(cd.getGia()));
                    txtThoiGianBatDau.setText(cd.getThoiGianBatDau().toString());
                    
                    Sv_xe listXe = new Sv_xe();
                    this.tbXe.setItems(FXCollections.observableList(listXe.getXeFromMaCD(Integer.parseInt(txtMaCD.getText()))));
                }
            }
            else{
                this.refeshCD();
                Utils.getBox("SỐ MÃ CHUYẾN KHÔNG HỢP LỆ", Alert.AlertType.WARNING).show();
            }
        }
        else
            Utils.getBox("MỜI BẠN NHẬP MÃ CHUYẾN", Alert.AlertType.WARNING).show();
    }
    //LOAD BUTTON KHACH HANG
    public void loadMaToKH(ActionEvent event) throws SQLException{
        Sv_khachhang loadKH = new Sv_khachhang();
        khachhang kh = new khachhang();
        if(txtMaKH.getText() != null){
            if(Integer.parseInt(txtMaKH.getText()) > 0 && Integer.parseInt(txtMaKH.getText()) <= loadKH.getKhachHang().size()){
                kh = loadKH.getMaToKH(Integer.parseInt(txtMaKH.getText()));
                if(kh != null){
                    txtTenKH.setText(kh.getTenKH());
                }
            }
            else
                Utils.getBox("SỐ MÃ KHÁCH HÀNG KHÔNG HỢP LỆ", Alert.AlertType.WARNING).show();
        }
        else
            Utils.getBox("MỜI BẠN NHẬP MÃ KHÁCH HÀNG", Alert.AlertType.WARNING).show();
    }
    //LOAD BUTTON XE
    public void loadMaToXE(ActionEvent event) throws SQLException{
        Sv_xe loadXe = new Sv_xe();
        xe xe = new xe();
        if(txtMaXE.getText() != null){
            if(Integer.parseInt(txtMaXE.getText()) > 0 && Integer.parseInt(txtMaXE.getText()) <= loadXe.getXe().size()){
                if(txtMaCD.getText() != null){
                    xe = loadXe.getMaToXE(Integer.parseInt(txtMaXE.getText()),Integer.parseInt(txtMaCD.getText()));
                    this.cbXe_ghetrong.setItems(FXCollections.observableList(loadXe.getGheTrong(Integer.parseInt(txtMaXE.getText())))); 
                    if(xe.getMaXE() != 0){
                        txtTenXe.setText(xe.getTenXe());
                        if(xe.getTrangthai() == 0)
                            txtTrangthai.setText("Xe chưa di chuyển.");
                        else
                            if(xe.getTrangthai() == 1)
                                txtTrangthai.setText("Xe đã di chuyển.");
                            else
                                txtTrangthai.setText(null);
                    }
                    else{
                        this.refreshXE();
                        Utils.getBox("KHÔNG CÓ XE PHÙ HỢP VỚI CHUYẾN ĐÃ CHỌN", Alert.AlertType.WARNING).show();
                    }
                }
                else{
                    this.refreshXE();
                    Utils.getBox("YÊU CẦU NHẬP MÃ CHUYẾN TRƯỚC", Alert.AlertType.WARNING).show();
                }
            }
            else
                Utils.getBox("SỐ MÃ XE KHÔNG HỢP LỆ", Alert.AlertType.WARNING).show();
        }
        else
            Utils.getBox("MỜI BẠN NHẬP MÃ XE", Alert.AlertType.WARNING).show();
    }
    
    ///////REFRESH
    public void refeshCD(){
        this.txtMaCD.setText(null);
        this.txtTenCD.setText(null);
        this.txtGia.setText(null);
        this.txtThoiGianBatDau.setText(null);
    }
    public void refreshKH(){
        this.txtMaKH.setText(null);
        this.txtTenKH.setText(null);
    }
    public void refreshXE(){
        this.txtMaXE.setText(null);
        this.txtTenXe.setText(null);
        this.txtTrangthai.setText(null);
    }
    /////GET VE CURRENT FOR CHECK IN ANOTHER FORM
    public vexe veCurrent() throws ParseException{
        Sv_vexe vx = new Sv_vexe();
        this.SLMV = vx.getMaVeCurrent();
        if(txtMaCD.getText() != null && txtMaKH.getText() != null && txtMaXE.getText() != null){
            this.veCur.setMave(SLMV+1);
            this.veCur.setThoiGianBatDau(Timestamp.valueOf(txtThoiGianBatDau.getText()));
            this.veCur.setSoghe(this.cbXe_ghetrong.getValue());
            this.veCur.setMaChuyen(Integer.parseInt(txtMaCD.getText()));
            this.veCur.setMaKH(Integer.parseInt(txtMaKH.getText()));
            this.veCur.setMaNV(Integer.parseInt(txtMaNV.getText()));
            this.veCur.setMaXE(Integer.parseInt(txtMaXE.getText()));
            this.veCur.setNgayin(DateFormat.parse(txtGioIn.getText())); 
            Utils.getBox("ĐẶT VÉ THÀNH CÔNG", Alert.AlertType.INFORMATION).show();
        }
        else
            Utils.getBox("CÓ THỂ BẠN CHƯA NHẬP ĐỦ THÔNG TIN CỦA VÉ", Alert.AlertType.WARNING).show();
        return this.veCur;
    }
    public void datVeButton(ActionEvent event) throws ParseException{
        vexe vx = this.veCurrent();
        System.out.println(vx.getMave());
        System.out.println(vx.getThoiGianBatDau().toString());
        System.out.println(vx.getSoghe());
        System.out.println(vx.getMaChuyen());
        System.out.println(vx.getMaKH());
        System.out.println(vx.getMaNV());
        System.out.println(vx.getMaXE());
        System.out.println(vx.getNgayin().toString());
    }
}
