/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vexe;

import Service.Login_nhanvien;
import Service.Sv_CheckOption;
import Service.Sv_chuyendi;
import Service.Sv_khachhang;
import Service.Sv_vexe;
import Service.Sv_xe;
import static com.mycompany.hethongbanve.Menu_DatveController.DATE_FORMAT_NOW;
import config.jdbcUtils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pojo.chuyendi;
import pojo.khachhang;
import pojo.nhanvien;
import pojo.vexe;
import pojo.xe;
import pojo.xe_ghe;

/**
 *
 * @author Admin
 */
public class veTester {
    private static Connection conn;
    Login_nhanvien sv_nv = new  Login_nhanvien();
    Sv_vexe sv_ve = new Sv_vexe();
    Sv_khachhang sv_kh = new Sv_khachhang();
    Sv_xe sv_xe = new Sv_xe();
    Sv_CheckOption ckOP = new Sv_CheckOption();
    Sv_chuyendi sv_cd = new Sv_chuyendi();
    Date dateCur = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
    ///DEFAULT
    String date = sdf.format(dateCur); 
    // Time ve
    Date tgHT = Date.from(Instant.now());
   
    public void refreshMa() throws SQLException{
        List<vexe> lst = sv_ve.getVeXe();
    }
    
    @BeforeAll
    public static void beforeAll() throws SQLException{
        conn = jdbcUtils.getConn();
    }
    public static void afterAll() throws SQLException{
        if(conn != null)
        {
            conn.close();
        }
    }
    
    @Test
    public void testNhapVeXeTrung() throws SQLException{  // NHAP 2 VE TRUNG GHE
        beforeAll();
        refreshMa();
        chuyendi cd = sv_cd.getMaToChuyen(7); 
        khachhang kh = sv_kh.getMaToKH(1);     // TRUYEN : MA KHACH HANG
        xe xe = sv_xe.getMaToXE(7, 7); //1 maxe 2 machuyen        // TRUYEN : MA XE
        nhanvien nv = sv_nv.getNhanVienFromMa(1);       // TRUYEN : MA NHAN VIEN
               // THOI GIAN BAT DAU // TRUUYEN : MA CHUYEN
         
        List<String> GheTrong = sv_xe.getGheTrong(7);  // 7 la maXE  
        
        Date tgBD = tgHT;
        Timestamp tgbd = new Timestamp(tgBD.getTime());
        Date tgIn = tgHT;
        tgIn.setTime(tgHT.getTime()-61*60*1000);
        Timestamp tgin = new Timestamp(tgIn.getTime());
        
        vexe vx1 = new vexe(sv_ve.getMaVeCurrent()+1, tgbd, GheTrong.get(0), cd.getMaChuyen(), kh.getMaKH(), nv.getMaNV(), xe.getMaXE(), tgin, 1);      
        vexe vx2 = new vexe(sv_ve.getMaVeCurrent()+2, tgbd, GheTrong.get(0), cd.getMaChuyen(), kh.getMaKH(), nv.getMaNV(), xe.getMaXE(), tgin, 1);
        
        System.out.println("TEST TRUNG GHE");
        if(ckOP.checkGheTrung(vx1) != true){
            sv_ve.addVeXe(vx1);
            System.out.println("==> VE 1 THEM THANH CONG");
        }
        List<vexe> kq = sv_ve.getVeXe();
        if(ckOP.checkGheTrung(vx2) != true)
            sv_ve.addVeXe(vx2);   // ---> SE BI FAIL DO TRUNG GHE
        else
            System.out.println("==> VE 2 TRÙNG GHẾ : KHONG THE THEM VAO");
        
        Set<vexe> kq2 = new HashSet<>(kq);
        Assertions.assertEquals(kq.size(), kq2.size());
        System.out.println("\n");
    }
    
    @Test
    public void testThoigiandatve() throws SQLException{
        beforeAll();
        refreshMa();
        chuyendi cd = sv_cd.getMaToChuyen(7); 

        khachhang kh = sv_kh.getMaToKH(1);     // TRUYEN : MA KHACH HANG
        xe xe = sv_xe.getMaToXE(7, 7); //1 maxe 2 machuyen        // TRUYEN : MA XE
        nhanvien nv = sv_nv.getNhanVienFromMa(1);       // TRUYEN : MA NHAN VIEN
               // THOI GIAN BAT DAU // TRUUYEN : MA CHUYEN
        
        List<String> GheTrong = sv_xe.getGheTrong(7);  // 7 la maXE  
        
        Date tgBD = tgHT;
        Timestamp tgbd = new Timestamp(tgBD.getTime());
        //
        Date tgIn = tgHT;
        tgIn.setTime(tgHT.getTime()-61*60*1000);
        Timestamp tgin = new Timestamp(tgIn.getTime());
        //
        Date tgInFail = tgIn;
        tgInFail.setTime(tgIn.getTime()+2*60*1000);
        Timestamp tginfail = new Timestamp(tgInFail.getTime());
        
        vexe vx1 = new vexe(sv_ve.getMaVeCurrent()+1, tgbd, GheTrong.get(1), cd.getMaChuyen(), kh.getMaKH(), nv.getMaNV(), xe.getMaXE(), tginfail, 1);      
        vexe vx2 = new vexe(sv_ve.getMaVeCurrent()+2, tgbd, GheTrong.get(2), cd.getMaChuyen(), kh.getMaKH(), nv.getMaNV(), xe.getMaXE(), tgin, 1);
        
        System.out.println("TEST DAT TRUOC 1h: ");
        if(ckOP.checkTimeDatVe(vx2))
        {
            sv_ve.addVeXe(vx2);
            System.out.println("==> VE 2 THANH CONG");
        }
        List<vexe> kq = sv_ve.getVeXe();
        if(ckOP.checkTimeDatVe(vx1))
            sv_ve.addVeXe(vx1);
        else
            System.out.println("==> VE 1 FAIL GIỜ IN < 1h ");
        Set<vexe> kq2 = new HashSet<>(kq);
        Assertions.assertEquals(kq.size(), kq2.size());
        System.out.println("\n");
    }
//    
    @Test
    public void testThoigianmuave() throws SQLException{
        beforeAll();
        refreshMa();
        chuyendi cd = sv_cd.getMaToChuyen(7);
        khachhang kh = sv_kh.getMaToKH(1);     // TRUYEN : MA KHACH HANG
        xe xe = sv_xe.getMaToXE(7, 7); //1 maxe 2 machuyen        // TRUYEN : MA XE
        nhanvien nv = sv_nv.getNhanVienFromMa(1);       // TRUYEN : MA NHAN VIEN
               // THOI GIAN BAT DAU // TRUUYEN : MA CHUYEN
             
        List<String> GheTrong = sv_xe.getGheTrong(7);  // 7 la maXE  
        
        Date tgBD = tgHT;
        Timestamp tgbd = new Timestamp(tgBD.getTime());
        //
        Date tgIn = tgHT;
        tgIn.setTime(tgHT.getTime()-6*60*1000);
        Timestamp tgin = new Timestamp(tgIn.getTime());
        //
        Date tgInFail = tgIn;
        tgInFail.setTime(tgIn.getTime()+2*60*1000);
        Timestamp tginfail = new Timestamp(tgInFail.getTime());
        
        
        vexe vx1 = new vexe(sv_ve.getMaVeCurrent()+1, tgbd, GheTrong.get(3), cd.getMaChuyen(), kh.getMaKH(), nv.getMaNV(), xe.getMaXE(), tginfail, 2);
        vexe vx2 = new vexe(sv_ve.getMaVeCurrent()+2, tgbd, GheTrong.get(4), cd.getMaChuyen(), kh.getMaKH(), nv.getMaNV(), xe.getMaXE(), tgin, 2);
        
        System.out.println("TEST MUA TRUOC 5p: ");
        if(ckOP.checkTimeMuaVe(vx2)){
            sv_ve.addMuaVeXe(vx2);
            System.out.println("==> VE 2 THANH CONG");
        }
        List<vexe> kq = sv_ve.getVeXe();
        if(ckOP.checkTimeMuaVe(vx1))
            sv_ve.addMuaVeXe(vx1);
        else
            System.out.println("==> VE 1 FAIL GIỜ IN < 5p");
        
        Set<vexe> kq2 = new HashSet<>(kq);
        Assertions.assertEquals(kq.size(), kq2.size());
        System.out.println("\n");
    }
    
    @Test
    public void testVeCanTransfer() throws SQLException{
        beforeAll();
        refreshMa();
        chuyendi cd = sv_cd.getMaToChuyen(7); 
        khachhang kh = sv_kh.getMaToKH(1);     // TRUYEN : MA KHACH HANG
        xe xe = sv_xe.getMaToXE(7, 7); //1 maxe 2 machuyen        // TRUYEN : MA XE
        nhanvien nv = sv_nv.getNhanVienFromMa(1);       // TRUYEN : MA NHAN VIEN
                // TRUUYEN : MA CHUYEN
        List<String> GheTrong = sv_xe.getGheTrong(7);  // 7 la maXE  
        
        //
        Date tgBD = Date.from(Instant.now());
        tgBD.setTime(tgBD.getTime() + 61*60*1000);
        Timestamp tgbd = new Timestamp(tgBD.getTime());
        //
        Date tgBDFail = Date.from(Instant.now());
        tgBDFail.setTime(tgBDFail.getTime() + 59*60*1000);
        Timestamp tgbdfail = new Timestamp(tgBDFail.getTime());
        //
        Date tgINChung = Date.from(Instant.now());
        Timestamp tgIN2ve = new Timestamp(tgINChung.getTime());
        
        
        vexe vx1 = new vexe(sv_ve.getMaVeCurrent()+1, tgbd, GheTrong.get(5), cd.getMaChuyen(), kh.getMaKH(), nv.getMaNV(), xe.getMaXE(), tgIN2ve, 1);
        vexe vx2 = new vexe(sv_ve.getMaVeCurrent()+2, tgbdfail, GheTrong.get(6), cd.getMaChuyen(), kh.getMaKH(), nv.getMaNV(), xe.getMaXE(), tgIN2ve, 1);
        
        System.out.println("TEST VE DUOC HOAN LAI TRC 1h: ");
        System.out.println("Thoi gian hien tai la: " + Date.from(Instant.now()));
        sv_ve.addVeXe(vx1);
        sv_ve.addVeXe(vx2);
        if(ckOP.isVeCanTransfer(vx1))
        {
            System.out.println("<==> VE 1 THANH CONG");
            System.out.println("<==> TIME VE 1: " + vx1.getThoigianbatdau());
        }
        else{
            System.out.println("==> VE 1 FAIL GIO HOAN < 1h ");
            System.out.println("==> TIME VE 1: " + vx2.getThoigianbatdau());
        }
        if(ckOP.isVeCanTransfer(vx2)){
            System.out.println("==> VE 2 THANH CONG");
            System.out.println("==> TIME VE 1: " + vx1.getThoigianbatdau());
        }
        else{
            System.out.println("==> VE 2 FAIL GIO HOAN < 1h ");
            System.out.println("==> TIME VE 2: " + vx2.getThoigianbatdau());
        }
        
        System.out.println("\n");
    }
    
    @Test
    public void testHieuLucVeSau30pTruocBatDau() throws SQLException{
        beforeAll();
        refreshMa();
        chuyendi cd = sv_cd.getMaToChuyen(7); 
        khachhang kh = sv_kh.getMaToKH(1);     // TRUYEN : MA KHACH HANG
        xe xe = sv_xe.getMaToXE(7, 7); //1 maxe 2 machuyen        // TRUYEN : MA XE
        nhanvien nv = sv_nv.getNhanVienFromMa(1);       // TRUYEN : MA NHAN VIEN
                // TRUUYEN : MA CHUYEN
        List<String> GheTrong = sv_xe.getGheTrong(7);  // 7 la maXE  
        
        Date tgBD = Date.from(Instant.now());
        tgBD.setTime(tgBD.getTime() + 29*60*1000);
        Date tgIn = Date.from(Instant.now());
        tgIn.setTime(tgIn.getTime() - 60*60*1000);
        
        Date tgBD2 = Date.from(Instant.now());
        tgBD2.setTime(tgBD.getTime() + 31*60*1000);
        Date tgIn2 = Date.from(Instant.now());
        tgIn2.setTime(tgIn.getTime() - 60*60*1000);
        
        Timestamp tgbd1 = new Timestamp(tgBD.getTime());
        Timestamp tgin1 = new Timestamp(tgIn.getTime());
        Timestamp tgbd2 = new Timestamp(tgBD2.getTime());
        Timestamp tgin2 = new Timestamp(tgIn2.getTime());
        
        vexe vx1 = new vexe(sv_ve.getMaVeCurrent()+1, tgbd1, GheTrong.get(7), cd.getMaChuyen(), kh.getMaKH(), nv.getMaNV(), xe.getMaXE(), tgin1, 1);        
        vexe vx2 = new vexe(sv_ve.getMaVeCurrent()+2, tgbd2, GheTrong.get(8), cd.getMaChuyen(), kh.getMaKH(), nv.getMaNV(), xe.getMaXE(), tgin2, 1);
        System.out.println("TEST VE DUOC HOAN LAI TRC 1h: ");
        System.out.println("Thoi gian hien tai la: " + Date.from(Instant.now()));
        sv_ve.addVeXe(vx1);
        if(ckOP.hieuLucVeDat(vx1))
        {  
            System.out.println("- VE 1 BAT DAU: " + vx1.getThoigianbatdau());
            System.out.println("==> VE 1 CHUA DC NHAN TRC 30P BAT DAU");
            System.out.println("- Trang thai luc dau cua ve 1 la: "+ vx1.getTrangthai());
            sv_ve.capNhatVe();
            System.out.println("- Trang thai luc sau la: "+ sv_ve.getVeXe(vx1.getMaVE()).getTrangthai());
            if(ckOP.isVeThuHoi(sv_ve.getVeXe(vx1.getMaVE())))
                System.out.println("==> VE 1 DA DUOC THU HOI");
        }
        else{
            System.out.println("==> VE 1 CON HIEU LUC");
            System.out.println("- VE 1 BAT DAU: " + vx1.getThoigianbatdau());
        }
        
        sv_ve.addVeXe(vx2);
        if(ckOP.hieuLucVeDat(vx2))
        {  
            System.out.println("- VE 2 BAT DAU: " + vx2.getThoigianbatdau());
            System.out.println("==> VE 2 CHUA DC NHAN TRC 30P BAT DAU");
            System.out.println("- Trang thai luc dau cua ve 2 la: "+ vx2.getTrangthai());
            sv_ve.capNhatVe();
            System.out.println("- Trang thai luc sau la: "+ sv_ve.getVeXe(vx2.getMaVE()).getTrangthai());
            if(ckOP.isVeThuHoi(sv_ve.getVeXe(vx2.getMaVE())))
                System.out.println("==> VE 2 DA DUOC THU HOI");
        }
        else{
            System.out.println("<==> VE 2 CON HIEU LUC");
            System.out.println("- VE 2 BAT DAU: " + vx2.getThoigianbatdau());
        }
        
        System.out.println("\n");
    }
}
