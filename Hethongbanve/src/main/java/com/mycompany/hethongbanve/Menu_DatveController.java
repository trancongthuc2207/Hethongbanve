package com.mycompany.hethongbanve;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Service.Login_nhanvien;
import Service.Sv_CheckOption;
import Service.Sv_Update_CD_XeGhe;
import Service.Sv_chuyendi;
import Service.Sv_khachhang;
import Service.Sv_vexe;
import Service.Sv_xe;
import config.PrintBill;
import config.Utils;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
    @FXML private TableView<vexe> tbVeXE;
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
    @FXML private Label txtTrangthaiUser;
    ////BANG THONG TIN XE
    @FXML private TextField txtMaXE;
    @FXML private TextField txtTenXe;
    @FXML private TextField txtTrangthai;
    ////BANG THONG TIN NGAY GIO HE THONG
    @FXML private TextField txtGioIn;
    
    ////// THONG TIN CHI TIET VE HIEN TAI
    @FXML private Label lblMaVe;    @FXML private Label lblTgbt;
    @FXML private Label lblSoghe;
    @FXML private Label lblMaChuyen;
    @FXML private Label lblMaKH;
    @FXML private Label lblMaNV;
    @FXML private Label lblMaXE;
    
    
    /////CHECK NHAP
   private boolean ClickloadCD = false;
   private boolean ClickloadKH = false;
   private boolean ClickloadXE = false;
   private boolean ClickloadGHE = false;
   
   private static int maCDtam = 0;
   private static int maKHtam = 0;
   private static int maXEtam = 0;
   private static String gheNgoitam = null;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.initInputFullOp();
        ////////init Ma~
        this.txtMaCD.setText(null);
        this.txtMaKH.setText(null);
        this.txtMaXE.setText(null);
        ////////Init Thong tin ve label
        this.initThongTinLabel();
        try {
            this.refeshCD();
        } catch (SQLException ex) {
            Logger.getLogger(Menu_DatveController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ///Gio in hien tai
        Date dateCur = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        ///DEFAULT
        this.txtGioIn.setText(sdf.format(dateCur));
        
        
        /////////TABLE VIEW
        ///CHUYEN DI
        this.loadTableViewCD();
        try {
            this.loadTableDataCDKeyword(null);
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
        //VE
        this.loadTableViewVeXE();
        try {
            this.loadTableDataVeXE();
        } catch (SQLException ex) {
            Logger.getLogger(Menu_DatveController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Sv_vexe vx = new Sv_vexe();
        this.SLMV = vx.getMaVeCurrent() + 1;
        this.lblMaVe.setText(String.valueOf(SLMV));
        
        
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
        this.lblMaNV.setText(txtMaNV.getText());
        try {
            if(lg.ChucVuNhanVienCurrent(LoginController.tenNVCurrent) == 1 || lg.ChucVuNhanVienCurrent(LoginController.tenNVCurrent) == 2)
                this.txtTrangthaiUser.setText("Bình thường");
            else
                this.txtTrangthaiUser.setText("Bị cấm");
        } catch (SQLException ex) {
            Logger.getLogger(Menu_DatveController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ////
        this.txtTenCD.textProperty().addListener((evt) -> {
            try {
                this.loadTableDataCDKeyword(txtTenCD.getText());
            } catch (SQLException ex) {
                Logger.getLogger(Menu_DatveController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        this.txtTenKH.textProperty().addListener((evt) -> {
            try {
                this.loadTableDataKHKeyword(this.txtTenKH.getText());
            } catch (SQLException ex) {
                Logger.getLogger(Menu_DatveController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        this.txtTenXe.textProperty().addListener((evt) -> {
            try {
                this.loadTableDataXEKeyword(this.txtTenXe.getText());
            } catch (SQLException ex) {
                Logger.getLogger(Menu_DatveController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        ///HOAN VE
        if(Menu_XuLyVeController.clickHoanVe){
            try {
                this.loadVeHienTaiHoanVe();
            } catch (SQLException ex) {
                Logger.getLogger(Menu_DatveController.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.maCDtam = Integer.valueOf(this.txtMaCD.getText());
            this.maXEtam = Integer.valueOf(this.txtMaXE.getText());
            this.maKHtam = Integer.valueOf(this.txtMaKH.getText());
            this.gheNgoitam = this.lblSoghe.getText();
        }
    }  
    ///////////////LOAD CAC TABLE
    /////////CHUYEN DI
    public void loadTableViewCD(){
        TableColumn colMaCD = new TableColumn("MaChuyen");
        colMaCD.setCellValueFactory(new PropertyValueFactory("MaChuyen"));
        colMaCD.setPrefWidth(50);
        
        TableColumn colTenCD = new TableColumn("TenChuyen");
        colTenCD.setCellValueFactory(new PropertyValueFactory("TenChuyen"));
        colTenCD.setPrefWidth(120);
        
        TableColumn colGia = new TableColumn("Gia");
        colGia.setCellValueFactory(new PropertyValueFactory("Gia"));
        colGia.setPrefWidth(60);
        
        TableColumn colThoiGianBatDau = new TableColumn("ThoiGianBatDau");
        colThoiGianBatDau.setCellValueFactory(new PropertyValueFactory("ThoiGianBatDau"));
        colThoiGianBatDau.setPrefWidth(120);
        
        TableColumn colThoiGianKetThuc = new TableColumn("ThoiGianKetThuc");
        colThoiGianKetThuc.setCellValueFactory(new PropertyValueFactory("ThoiGianKetThuc"));
        colThoiGianKetThuc.setPrefWidth(120);
        
        this.tbChuyendi.getColumns().addAll(colMaCD,colTenCD,colGia,colThoiGianBatDau,colThoiGianKetThuc);
    }
    public void loadTableDataCD() throws SQLException{
        Sv_chuyendi listCD = new Sv_chuyendi();
        this.tbChuyendi.setItems(FXCollections.observableList(listCD.getChuyendi()));
    }
    public void loadTableDataCDKeyword(String kw) throws SQLException{
        Sv_chuyendi listCD = new Sv_chuyendi();
        this.tbChuyendi.setItems(FXCollections.observableList(listCD.getChuyendi(kw)));
    }
    ////////KHACH HANG
    public void loadTableViewKH(){
        TableColumn colMaKH = new TableColumn("MaKH");
        colMaKH.setCellValueFactory(new PropertyValueFactory("MaKH"));
        colMaKH.setPrefWidth(70);
        
        TableColumn colTenKH = new TableColumn("TenKH");
        colTenKH.setCellValueFactory(new PropertyValueFactory("TenKH"));
        colTenKH.setPrefWidth(100);
        
        TableColumn colCMND = new TableColumn("CMND");
        colCMND.setCellValueFactory(new PropertyValueFactory("CMND"));
        colCMND.setPrefWidth(80);
        
        TableColumn colSDT = new TableColumn("SDT");
        colSDT.setCellValueFactory(new PropertyValueFactory("SDT"));
        colSDT.setPrefWidth(70);
        
        this.tbKhachhang.getColumns().addAll(colMaKH,colTenKH,colCMND,colSDT);
    }
    public void loadTableDataKH() throws SQLException{
        Sv_khachhang listkh = new Sv_khachhang();
        this.tbKhachhang.setItems(FXCollections.observableList(listkh.getKhachHang()));
    }
    public void loadTableDataKHKeyword(String kw) throws SQLException{
        Sv_khachhang listkh = new Sv_khachhang();
        this.tbKhachhang.setItems(FXCollections.observableList(listkh.getKhachhang(kw)));
    }
    ////////XE
    public void loadTableViewXE(){
        TableColumn colMaXE = new TableColumn("MaXE");
        colMaXE.setCellValueFactory(new PropertyValueFactory("MaXE"));
        colMaXE.setPrefWidth(70);
        
        TableColumn colTenXe = new TableColumn("TenXe");
        colTenXe.setCellValueFactory(new PropertyValueFactory("TenXe"));
        colTenXe.setPrefWidth(120);
        
        TableColumn colBienso = new TableColumn("Bienso");
        colBienso.setCellValueFactory(new PropertyValueFactory("Bienso"));
        colBienso.setPrefWidth(70);
        
        TableColumn colTrangthai = new TableColumn("Trangthai");
        colTrangthai.setCellValueFactory(new PropertyValueFactory("Trangthai"));
        colTrangthai.setPrefWidth(70);
        
        TableColumn colMaChuyen = new TableColumn("MaChuyen");
        colMaChuyen.setCellValueFactory(new PropertyValueFactory("MaChuyen"));
        colMaChuyen.setPrefWidth(80);
        
        this.tbXe.getColumns().addAll(colMaXE,colTenXe,colBienso,colTrangthai,colMaChuyen);
    }
    public void loadTableDataXE() throws SQLException{
        Sv_xe listXe = new Sv_xe();
        listXe.capNhatTrangThaiXe();
        this.tbXe.setItems(FXCollections.observableList(listXe.getXe()));
    }
    public void loadTableDataXEKeyword(String kw) throws SQLException{
        Sv_xe listXe = new Sv_xe();
        this.tbXe.setItems(FXCollections.observableList(listXe.getXe(kw)));
    }
    ////////VE
    public void loadTableViewVeXE(){
        TableColumn colMaVE = new TableColumn("MaVE");
        colMaVE.setCellValueFactory(new PropertyValueFactory("MaVE"));
        colMaVE.setPrefWidth(60);
        
        TableColumn colThoigianbatdau = new TableColumn("Thoigianbatdau");
        colThoigianbatdau.setCellValueFactory(new PropertyValueFactory("Thoigianbatdau"));
        colThoigianbatdau.setPrefWidth(120);
        
        TableColumn colSoghe = new TableColumn("Soghe");
        colSoghe.setCellValueFactory(new PropertyValueFactory("Soghe"));
        colSoghe.setPrefWidth(50);
        
        TableColumn colMaChuyen = new TableColumn("MaChuyen");
        colMaChuyen.setCellValueFactory(new PropertyValueFactory("MaChuyen"));
        colMaChuyen.setPrefWidth(60);
        
        TableColumn colMaKH = new TableColumn("MaKH");
        colMaKH.setCellValueFactory(new PropertyValueFactory("MaKH"));
        colMaKH.setPrefWidth(60);
        
        TableColumn colMaNV = new TableColumn("MaNV");
        colMaNV.setCellValueFactory(new PropertyValueFactory("MaNV"));
        colMaNV.setPrefWidth(60);
        
        TableColumn colMaXE = new TableColumn("MaXE");
        colMaXE.setCellValueFactory(new PropertyValueFactory("MaXE"));
        colMaXE.setPrefWidth(60);
        
        TableColumn colNgayin = new TableColumn("Ngayin");
        colNgayin.setCellValueFactory(new PropertyValueFactory("Ngayin"));
        colNgayin.setPrefWidth(120);
        
        TableColumn colTrangthai = new TableColumn("Trangthai");
        colTrangthai.setCellValueFactory(new PropertyValueFactory("Trangthai"));
        colTrangthai.setPrefWidth(60);
        
        this.tbVeXE.getColumns().addAll(colMaVE,colThoigianbatdau,colSoghe,colMaChuyen,colMaKH,colMaNV,colMaXE,colNgayin,colTrangthai);
    }
    public void loadTableDataVeXE() throws SQLException{
        Sv_vexe listVe = new Sv_vexe();
        this.tbVeXE.setItems(FXCollections.observableList(listVe.getVeXe()));
    }
    
    ////// CAC BUTTON LOAD DU LIEU THONG TIN THEO YEU CAU
    //LOAD BUTTON CHUYEN DI
    public void loadMaToCD(ActionEvent event) throws SQLException{
        this.ClickloadCD = true;
        Sv_chuyendi loadcd = new Sv_chuyendi();
        chuyendi cd = new chuyendi();
        if(this.txtMaCD.getText() != null && txtMaCD.getText() != "" && !txtMaCD.getText().isEmpty()){
            if(Integer.parseInt(txtMaCD.getText()) > 0 && Integer.parseInt(txtMaCD.getText()) <= loadcd.getMaCDCurrent()){
                cd = loadcd.getMaToChuyen(Integer.parseInt(txtMaCD.getText()));
                if(cd != null){
                    txtTenCD.setText(cd.getTenChuyen());
                    txtGia.setText(String.valueOf(cd.getGia()));
                    txtThoiGianBatDau.setText(cd.getThoiGianBatDau().toString());
                    
                    this.refreshXE();
                    Sv_xe listXe = new Sv_xe();
                    this.tbXe.setItems(FXCollections.observableList(listXe.getXeFromMaCD(Integer.parseInt(txtMaCD.getText()))));
                    this.lblMaChuyen.setText(txtMaCD.getText());
                    this.lblTgbt.setText(txtThoiGianBatDau.getText());
                }
                else{
                    this.refeshCD();
                    this.refreshXE();
                    Utils.getBox("SỐ MÃ CHUYẾN KHÔNG HỢP LỆ", Alert.AlertType.WARNING).show();
                    this.ClickloadCD = false;
                }        
            }
            else{
                this.refeshCD();
                this.refreshXE();
                Utils.getBox("SỐ MÃ CHUYẾN KHÔNG HỢP LỆ", Alert.AlertType.WARNING).show();
                this.ClickloadCD = false;
            }
        }
        else{
            Utils.getBox("MỜI BẠN NHẬP MÃ CHUYẾN", Alert.AlertType.WARNING).show();
            this.ClickloadCD = false;
        }
    }
    //LOAD BUTTON KHACH HANG
    public void loadMaToKH(ActionEvent event) throws SQLException{
        this.ClickloadKH = true;
        Sv_khachhang loadKH = new Sv_khachhang();
        khachhang kh = new khachhang();
        if(txtMaKH.getText() != null && txtMaKH.getText() != "" && !txtMaKH.getText().isEmpty()){
            if(Integer.parseInt(txtMaKH.getText()) > 0 && Integer.parseInt(txtMaKH.getText()) <= loadKH.getMaKHCurrent()){
                kh = loadKH.getMaToKH(Integer.parseInt(txtMaKH.getText()));
                if(kh != null){
                    txtTenKH.setText(kh.getTenKH());
                    this.lblMaKH.setText(txtMaKH.getText());
                }
                else{
                    Utils.getBox("SỐ MÃ KHÁCH HÀNG KHÔNG HỢP LỆ", Alert.AlertType.WARNING).show();
                    this.ClickloadKH = false;
                }
            }
            else{
                Utils.getBox("SỐ MÃ KHÁCH HÀNG KHÔNG HỢP LỆ", Alert.AlertType.WARNING).show();
                this.ClickloadKH = false;
            }
        }
        else{
            Utils.getBox("MỜI BẠN NHẬP MÃ KHÁCH HÀNG", Alert.AlertType.WARNING).show();
            this.ClickloadKH = false;
        }
    }
    //LOAD BUTTON XE
    public void loadMaToXE(ActionEvent event) throws SQLException{
        this.ClickloadXE = true;
        Sv_xe loadXe = new Sv_xe();
        xe xe = new xe();
        if(txtMaXE.getText() != null && txtMaXE.getText() != "" && !txtMaXE.getText().isEmpty()){
            if(Integer.parseInt(txtMaXE.getText()) > 0 && Integer.parseInt(txtMaXE.getText()) <= loadXe.getMaXeCurrent()){
                if(txtMaCD.getText() != null){
                    try {
                        xe = loadXe.getMaToXE(Integer.parseInt(txtMaXE.getText()), Integer.parseInt(txtMaCD.getText()));
                    } catch (NumberFormatException numberFormatException) {
                    } catch (SQLException sQLException) {
                        Utils.getBox("KHÔNG CÓ XE PHÙ HỢP VỚI CHUYẾN ĐÃ CHỌN", Alert.AlertType.WARNING).show();
                    }
                    this.cbXe_ghetrong.setItems(FXCollections.observableList(loadXe.getGheTrong(Integer.parseInt(txtMaXE.getText()))));
                    
                    if(xe.getMaXE() != 0){
                        txtTenXe.setText(xe.getTenXe());
                        this.lblMaXE.setText(txtMaXE.getText());
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
                        this.lblSoghe.setText(null);
                        this.lblMaXE.setText(null);
                        Utils.getBox("KHÔNG CÓ XE PHÙ HỢP VỚI CHUYẾN ĐÃ CHỌN", Alert.AlertType.WARNING).show();
                        this.ClickloadXE = false;
                    }
                }
                else{
                    this.refreshXE();
                    Utils.getBox("YÊU CẦU NHẬP MÃ CHUYẾN TRƯỚC", Alert.AlertType.WARNING).show();
                    this.ClickloadXE = false;
                }
            }
            else{
                Utils.getBox("SỐ MÃ XE KHÔNG HỢP LỆ", Alert.AlertType.WARNING).show();
                this.ClickloadXE = false;
            }
        }
        else{
            Utils.getBox("MỜI BẠN NHẬP MÃ XE", Alert.AlertType.WARNING).show();
            this.ClickloadXE = false;
        }
    }
    
    ///////REFRESH
    public void refeshCD() throws SQLException{
        this.txtMaCD.setText(null);
        this.txtTenCD.setText(null);
        this.txtGia.setText(null);
        this.txtThoiGianBatDau.setText(null);
        this.lblMaChuyen.setText(null);
        this.lblTgbt.setText(null);
        this.loadTableDataCD();
        this.loadTableDataXE();
        this.ClickloadKH = false;
    }
    public void refreshKH() throws SQLException{
        this.txtMaKH.setText(null);
        this.txtTenKH.setText(null);
        this.lblMaKH.setText(null);
        this.loadTableDataKH();
        this.ClickloadKH = false;
    }
    public void refreshXE() throws SQLException{
        this.txtMaXE.setText(null);
        this.txtTenXe.setText(null);
        this.txtTrangthai.setText(null);
        this.cbXe_ghetrong.setItems(null);
        this.lblMaXE.setText(null);
        this.lblSoghe.setText(null);
        this.ClickloadGHE = false;
        this.ClickloadXE = false;
    }
    
    /////XU LY VE CURRENT FOR UPDATE TO DATABASE
    public vexe veCurrent() throws ParseException{
        Sv_vexe vx = new Sv_vexe();
        this.SLMV = vx.getMaVeCurrent();
        if(isInputFullOp()){
            this.veCur.setMaVE(SLMV+1);
            this.veCur.setThoigianbatdau(Timestamp.valueOf(txtThoiGianBatDau.getText()));
            this.veCur.setSoghe(this.cbXe_ghetrong.getValue().substring(0, 3));
            this.veCur.setMaChuyen(Integer.parseInt(txtMaCD.getText()));
            this.veCur.setMaKH(Integer.parseInt(txtMaKH.getText()));
            this.veCur.setMaNV(Integer.parseInt(txtMaNV.getText()));
            this.veCur.setMaXE(Integer.parseInt(txtMaXE.getText()));
            this.veCur.setNgayin(Timestamp.valueOf(txtGioIn.getText()));
            this.veCur.setTrangthai(1);
        }
        else{
            Utils.getBox("CÓ THỂ BẠN CHƯA NHẬP ĐỦ THÔNG TIN CỦA VÉ", Alert.AlertType.WARNING).show();
        }
        return this.veCur;
    }
    public void datVeButton(ActionEvent event) throws ParseException, SQLException{
        if(this.ISTRANGTHAIHOANVE() == false){
            vexe vx = this.veCurrent();
            Sv_vexe service = new Sv_vexe();
            Sv_chuyendi sv_cd = new Sv_chuyendi();
            Sv_CheckOption CkOP = new Sv_CheckOption();
                if(isInputFullOp() == true){
                    if(CkOP.isOutOfTimeToMove(sv_cd.getMaToChuyen(vx.getMaChuyen())) != true){
                        if(CkOP.checkTimeDatVe(vx) == true){
                            if(CkOP.checkGheTrung(vx) != true){
                                    service.addVeXe(vx);
                                    Utils.getBox("THÊM VÉ THÀNH CÔNG", Alert.AlertType.INFORMATION).show();
                                    initInputFullOp();
                                    this.loadTableDataVeXE();
                                    this.initThongTinTextField();
                            }
                            else
                                Utils.getBox("GHẾ ĐÃ TRÙNG", Alert.AlertType.WARNING).show();
                        }
                        else
                            Utils.getBox("VÉ ĐẶT PHẢI TRƯỚC 1 TIẾNG!!", Alert.AlertType.WARNING).show();
                    }
                    else
                        Utils.getBox("XE ĐÃ DI CHUYỂN", Alert.AlertType.WARNING).show();
                }
                else
                    Utils.getBox("YÊU CẦU BẠN NHẬP ĐẦY ĐỦ (LOAD ĐỦ THÔNG TIN TRƯỚC KHI NHẤN NÚT ĐẶT VÉ)", Alert.AlertType.WARNING).show();
        }
        else
            Utils.getBox("ĐANG TRONG TRẠNG THÁI HOÀN VÉ, KHÔNG THỂ ĐẶT VÉ NÀY", Alert.AlertType.WARNING).show();
    }
    
    public void muaVeButton(ActionEvent event) throws ParseException, SQLException, IOException{
        if(this.ISTRANGTHAIHOANVE() != true){
            vexe vx = this.veCurrent();
            Sv_vexe service = new Sv_vexe();
            Sv_chuyendi sv_cd = new Sv_chuyendi();
            Sv_CheckOption CkOP = new Sv_CheckOption();
                if(isInputFullOp() == true){
                    vx.setTrangthai(2);
                    if(CkOP.isOutOfTimeToMove(sv_cd.getMaToChuyen(vx.getMaChuyen())) != true){
                        if(CkOP.checkTimeMuaVe(vx) == true){
                            if(CkOP.checkGheTrung(vx) != true){
                                    service.addMuaVeXe(vx);
                                    Utils.getBox("THÊM VÉ THÀNH CÔNG", Alert.AlertType.INFORMATION).show();
                                    PrintBill inve = new PrintBill();
                                    inve.PrintBill(vx);
                                    initInputFullOp();
                                    this.loadTableDataVeXE();
                                    this.initThongTinTextField();
                            }
                            else
                                Utils.getBox("GHẾ ĐÃ TRÙNG", Alert.AlertType.WARNING).show();
                        }
                        else{
                            Utils.getBox("HẾT HẠN MUA VÉ", Alert.AlertType.WARNING).show();
                        }
                    }
                    else
                        Utils.getBox("XE ĐÃ DI CHUYỂN", Alert.AlertType.WARNING).show();
                }
                else
                        Utils.getBox("YÊU CẦU BẠN NHẬP ĐẦY ĐỦ (LOAD ĐỦ THÔNG TIN TRƯỚC KHI NHẤN NÚT ĐẶT VÉ)", Alert.AlertType.WARNING).show();
          }
        else
            Utils.getBox("ĐANG TRONG TRẠNG THÁI HOÀN VÉ, KHÔNG THỂ MUA VÉ NÀY", Alert.AlertType.WARNING).show();
    }
    //////PHAN KHOI TAO FORM
    public void initThongTinTextField() throws SQLException{
        this.refeshCD();
        this.refreshKH();
        this.refreshXE();
        this.RESETTRANGTHAI_MUA_DAT();
        //khoi tao lai gio in moi
        Date dateCur = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        ///DEFAULT
        this.txtGioIn.setText(sdf.format(dateCur));
        //khoi tao lai ve moi
        this.loadTableDataVeXE();
        Sv_vexe vx = new Sv_vexe();
        this.SLMV = vx.getMaVeCurrent() + 1;
        this.lblMaVe.setText(String.valueOf(SLMV));
        this.initInputFullOp();
    }
    public void initThongTinLabel(){
        this.lblMaVe.setText(null);
        this.lblTgbt.setText(null);
        this.lblSoghe.setText(null);
        this.lblMaChuyen.setText(null);
        this.lblMaKH.setText(null);
        this.lblMaNV.setText(null);
        this.lblMaXE.setText(null);
    }
    public boolean isInputFullOp(){
        if(this.ClickloadCD && this.ClickloadGHE && this.ClickloadKH && this.ClickloadXE)
            return true;
        return false;
    }
    public void initInputFullOp(){
        this.ClickloadCD = false;    
        this.ClickloadGHE = false; 
        this.ClickloadKH  = false;
        this.ClickloadXE = false;
    }
    public void CbBoxToLabel(ActionEvent evt){
        this.lblSoghe.setText(this.cbXe_ghetrong.getValue());
        this.ClickloadGHE = true;
    }
    

    ////XU LY VE --->
    public void loadVeHienTaiHoanVe() throws SQLException{
        this.txtMaKH.setEditable(false);
        this.txtTenKH.setEditable(false);
        this.ClickloadKH = true;
        vexe vx = new vexe();
        vx = Menu_XuLyVeController.getVeCurr();
        //MAVE
        this.lblMaVe.setText(String.valueOf(vx.getMaVE()));
        //CD
        Sv_chuyendi loadcd = new Sv_chuyendi();
        this.txtMaCD.setText(String.valueOf(vx.getMaChuyen()));
        this.txtTenCD.setText(loadcd.getMaToChuyen(vx.getMaChuyen()).getTenChuyen());
        this.txtGia.setText(String.valueOf(loadcd.getMaToChuyen(vx.getMaChuyen()).getGia()));
        this.txtThoiGianBatDau.setText(String.valueOf(loadcd.getMaToChuyen(vx.getMaChuyen()).getThoiGianBatDau()));
        this.lblMaChuyen.setText(this.txtMaCD.getText());
        this.lblTgbt.setText(this.txtThoiGianBatDau.getText());
        //KH
        Sv_khachhang loadKH = new Sv_khachhang();
        this.txtMaKH.setText(String.valueOf(vx.getMaKH()));
        txtTenKH.setText(String.valueOf(loadKH.getMaToKH(vx.getMaKH()).getTenKH()));
        this.lblMaKH.setText(txtMaKH.getText());
        //XE
        Sv_xe loadXE = new Sv_xe();
        this.txtMaXE.setText(String.valueOf(vx.getMaXE()));
        txtTenXe.setText(String.valueOf(loadXE.getMaToXE(vx.getMaXE(), vx.getMaChuyen()).getTenXe()));
        txtTrangthai.setText(String.valueOf(loadXE.getMaToXE(vx.getMaXE(), vx.getMaChuyen()).getTrangthai()));
        this.lblMaXE.setText(txtMaXE.getText());
        this.lblSoghe.setText(vx.getSoghe());
        this.txtGioIn.setText(vx.getNgayin().toString());
        Menu_XuLyVeController.clickHoanVe = false;
    }
    public void hoanVeButton(ActionEvent event) throws SQLException{
        if(this.ISTRANGTHAIHOANVE() == true){
            Sv_vexe sv_ve = new Sv_vexe();
            Sv_xe loadXe = new Sv_xe();
            Sv_CheckOption ckOp = new Sv_CheckOption();
            Date dateCur = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
            xe xe = new xe();
            //vexe veOld = sv_ve.getVeXe(Menu_XuLyVeController.getVeCurr().getMaVE());
            this.veCur = sv_ve.getVeXe(Menu_XuLyVeController.getVeCurr().getMaVE());
            if(ckOp.isVeCanTransfer(veCur)){
                    if(Integer.valueOf(this.txtMaKH.getText()) == veCur.getMaKH()){
                        this.SLMV = sv_ve.getMaVeCurrent();
                        this.veCur.setMaVE(SLMV+1);
                        if(Integer.valueOf(this.txtMaCD.getText()) == this.maCDtam){
                            xe = loadXe.getMaToXE(veCur.getMaXE(), veCur.getMaChuyen());
                            this.cbXe_ghetrong.setItems(FXCollections.observableList(loadXe.getGheTrong(veCur.getMaXE())));
                            this.veCur.setSoghe(this.cbXe_ghetrong.getValue().substring(0, 3));
                            String Ngayinmoi = sdf.format(dateCur);
                            this.veCur.setNgayin(Timestamp.valueOf(Ngayinmoi));
                            this.veCur.setTrangthai(1);
                            if(ckOp.checkGheTrung(veCur) != true){
                                sv_ve.hoanVeMoi(Menu_XuLyVeController.getVeCurr(), veCur);
                                Utils.getBox("HOÀN VÉ THÀNH CÔNG", Alert.AlertType.INFORMATION).show();
                                this.loadTableDataVeXE();
                                initThongTinTextField();
                                this.txtMaKH.setEditable(true);
                                this.txtTenKH.setEditable(true);
                                initInputFullOp();
                            }
                            else
                                Utils.getBox("TRÙNG GHẾ", Alert.AlertType.INFORMATION).show();
                        }
                        else{       // NẾU CHUYẾN ĐI THAY ĐỔI
                            if(ClickloadCD  && ClickloadXE && ClickloadGHE){
                                ///DEFAULT
                                String Ngayinmoi = sdf.format(dateCur);
                                this.veCur.setMaChuyen(Integer.parseInt(txtMaCD.getText()));
                                this.veCur.setMaXE(Integer.parseInt(txtMaXE.getText()));
                                this.veCur.setSoghe(this.cbXe_ghetrong.getValue().substring(0, 3));
                                this.veCur.setNgayin(Timestamp.valueOf(Ngayinmoi));
                                this.veCur.setTrangthai(1);
                               if(ckOp.checkGheTrung(veCur) != true){
                                    sv_ve.hoanVeMoi(Menu_XuLyVeController.getVeCurr(), veCur);
                                    Utils.getBox("HOÀN VÉ THÀNH CÔNG", Alert.AlertType.INFORMATION).show();
                                    this.loadTableDataVeXE();
                                    initThongTinTextField();
                                    this.txtMaKH.setEditable(true);
                                    this.txtTenKH.setEditable(true);
                                    initInputFullOp();
                                }
                                else
                                    Utils.getBox("TRÙNG GHẾ", Alert.AlertType.INFORMATION).show();
                            }
                            else
                                Utils.getBox("CHƯA ĐẦY ĐỦ THÔNG TIN", Alert.AlertType.INFORMATION).show();
                        }
                    }
                    else
                        Utils.getBox("MÃ KHÁCH HÀNG HOÀN VÉ KHÔNG ĐƯỢC THAY ĐỔI", Alert.AlertType.WARNING).show();
            }
        }
        else
            Utils.getBox("ĐANG TRONG TRẠNG THÁI ĐẶT VÀ MUA VÉ, KHÔNG THỂ HOÀN VÉ NÀO NGAY LÚC NÀY", Alert.AlertType.WARNING).show();
    }
    
    public boolean ISTRANGTHAIHOANVE(){
        boolean check = false;
        if(maCDtam != 0 && maKHtam != 0 && maXEtam != 0 && gheNgoitam != null)
            return check = true;
        return check;
    }
    
    public void RESETTRANGTHAI_MUA_DAT(){
        this.maCDtam = 0;
        this.maKHtam = 0;
        this.maXEtam = 0;
        this.gheNgoitam = null;
    }
}
