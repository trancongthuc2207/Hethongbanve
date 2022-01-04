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
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("Kiem tra nhap trung ghe")
    public void testNhapVeXeTrung() throws SQLException{  // NHAP 2 VE TRUNG GHE
        beforeAll();
        refreshMa();
        chuyendi cd = sv_cd.getMaToChuyen(7); 
        khachhang kh = sv_kh.getMaToKH(1);     // TRUYEN : MA KHACH HANG
        xe xe = sv_xe.getMaToXE(7, 7); //1 maxe 2 machuyen        // TRUYEN : MA XE
        nhanvien nv = sv_nv.getNhanVienFromMa(1);       // TRUYEN : MA NHAN VIEN
         // TRUUYEN : MA CHUYEN
        List<String> GheTrong = sv_xe.getGheTrong(7);  // 7 la maXE  
        Date tgBD = tgHT;
        Timestamp tgbd = new Timestamp(tgBD.getTime());
        Date tgIn = tgHT;
        tgIn.setTime(tgHT.getTime()-61*60*1000);
        Timestamp tgin = new Timestamp(tgIn.getTime());
        
        vexe vx1 = new vexe(sv_ve.getMaVeCurrent()+1, tgbd, GheTrong.get(0), cd.getMaChuyen(), kh.getMaKH(), nv.getMaNV(), xe.getMaXE(), tgin, 1);      
        vexe vx2 = new vexe(sv_ve.getMaVeCurrent()+2, tgbd, GheTrong.get(0), cd.getMaChuyen(), kh.getMaKH(), nv.getMaNV(), xe.getMaXE(), tgin, 1);
        
       
        System.out.println("TEST TRUNG GHE");
        boolean koTrung = ckOP.checkGheTrung(vx1);
        sv_ve.addVeXe(vx1);
        boolean Trung = ckOP.checkGheTrung(vx2);
        
        Assertions.assertFalse(koTrung,"VE 1 KHONG TRUNG");
        Assertions.assertTrue(Trung,"VE 2 TRUNG");

        System.out.println("\n");
    }
    
    @Test
    @DisplayName("Kiem tra thoi gian dat ve < 1h truoc khi bat dau")
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
        
        boolean datDuoc = ckOP.checkTimeDatVe(vx2);
        boolean koDatDuoc = ckOP.checkTimeDatVe(vx1);
        
        Assertions.assertTrue(datDuoc,"VE 2 DAT DUOC");
        Assertions.assertFalse(koDatDuoc,"VE 1 KHONG DAT DUOC");
       
        System.out.println("\n");
    }
    
    @Test
    @DisplayName("Kiem tra thoi gian mua ve < 5phut truoc khi bat dau")
    public void testThoigianmuave() throws SQLException{
        beforeAll();
        refreshMa();
        chuyendi cd = sv_cd.getMaToChuyen(7);
        khachhang kh = sv_kh.getMaToKH(1);     // TRUYEN : MA KHACH HANG
        xe xe = sv_xe.getMaToXE(7, 7); //1 maxe 2 machuyen        // TRUYEN : MA XE
        nhanvien nv = sv_nv.getNhanVienFromMa(1);       // TRUYEN : MA NHAN VIEN
               // THOI GIAN BAT DAU // TRUUYEN : MA CHUYEN
             
        List<String> GheTrong = sv_xe.getGheTrong(7);  // 7 la maXE  
        
        Date tgBD = Date.from(Instant.now());
        Timestamp tgbd = new Timestamp(tgBD.getTime());
        
        Date tgIn = Date.from(Instant.now());
        tgIn.setTime(tgIn.getTime()-6*60*1000);
        Timestamp tgin = new Timestamp(tgIn.getTime());
        
        Date tgInFail = Date.from(Instant.now());
        tgInFail.setTime(tgInFail.getTime()-4*60*1000);
        Timestamp tginfail = new Timestamp(tgInFail.getTime());
        
        vexe vx1 = new vexe(sv_ve.getMaVeCurrent()+1, tgbd, GheTrong.get(3), cd.getMaChuyen(), kh.getMaKH(), nv.getMaNV(), xe.getMaXE(), tginfail, 2);
        vexe vx2 = new vexe(sv_ve.getMaVeCurrent()+2, tgbd, GheTrong.get(4), cd.getMaChuyen(), kh.getMaKH(), nv.getMaNV(), xe.getMaXE(), tgin, 2);
        
        System.out.println("TEST MUA TRUOC 5p: ");
        boolean muaDuoc = ckOP.checkTimeMuaVe(vx2);
        boolean koMuaDuoc = ckOP.checkTimeMuaVe(vx1);
        
        Assertions.assertTrue(muaDuoc,"VE 2 MUA DUOC");
        Assertions.assertFalse(koMuaDuoc,"VE 1 KHONG MUA DUOC");
       
        System.out.println("\n");
    }
    
    @Test
    @DisplayName("Kiem tra ve co the hoan tra khong (<1h truoc khi bat dau)")
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
        vexe vx3 = new vexe(sv_ve.getMaVeCurrent()+3, tgbd, GheTrong.get(7), cd.getMaChuyen(), kh.getMaKH(), nv.getMaNV(), xe.getMaXE(), tgIN2ve, 0);
        vexe vx4 = new vexe(sv_ve.getMaVeCurrent()+3, tgbd, GheTrong.get(8), cd.getMaChuyen(), kh.getMaKH(), nv.getMaNV(), xe.getMaXE(), tgIN2ve, 2);
        System.out.println("TEST VE DUOC HOAN LAI TRC 1h: ");
        
        boolean veHoan1 = ckOP.isVeCanTransfer(vx1); // VE 1 h > 1tieng truoc khi bat dau
        boolean veHoan2 = ckOP.isVeCanTransfer(vx2); // VE 2 h < 1tieng truoc khi bat dau
        boolean veHoan3 = ckOP.isVeCanTransfer(vx3); // Ve 3 la ve da thu hoi
        boolean veHoan4 = ckOP.isVeCanTransfer(vx4); // Ve 4 la ve da nhan
        Assertions.assertTrue(veHoan1,"Ve 1 hoan duoc");
        Assertions.assertFalse(veHoan2,"Ve 2 ko hoan duoc");
        Assertions.assertFalse(veHoan3,"Ve 3 ko hoan duoc (Da thu hoi)");
        Assertions.assertFalse(veHoan4,"Ve 4 ko hoan duoc (Da nhan)");
        System.out.println("\n");
    }
    
    @Test
    @DisplayName("Hieu luc cua ve duoc cap nhat trc 30p truoc khi bat dau")
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
        
        vexe vx1 = new vexe(sv_ve.getMaVeCurrent()+1, tgbd1, GheTrong.get(9), cd.getMaChuyen(), kh.getMaKH(), nv.getMaNV(), xe.getMaXE(), tgin1, 1);        
        vexe vx2 = new vexe(sv_ve.getMaVeCurrent()+2, tgbd2, GheTrong.get(10), cd.getMaChuyen(), kh.getMaKH(), nv.getMaNV(), xe.getMaXE(), tgin2, 1);
        System.out.println("TEST VE DUOC HOAN LAI TRC 1h: ");
        sv_ve.addVeXe(vx1); // Sau 30p truoc khi bat dau
        sv_ve.addVeXe(vx2); // Truoc 30p truoc khi bat dau
        sv_ve.capNhatVe();
        vexe vx1_capnhat = sv_ve.getVeXe(vx1.getMaVE()); // Ve 1 cap nhat lai trang thai thu hoi
        vexe vx2_capnhat = sv_ve.getVeXe(vx2.getMaVE()); // ve 2 khong cap nhat
        boolean ve1 = ckOP.isVeThuHoi(vx1_capnhat);
        boolean ve2 = ckOP.isVeThuHoi(vx2_capnhat);
        
        Assertions.assertNotEquals(vx1.getTrangthai(),vx1_capnhat.getTrangthai());
        Assertions.assertEquals(vx2.getTrangthai(),vx2_capnhat.getTrangthai());
        Assertions.assertTrue(ve1);
        Assertions.assertFalse(ve2);      
        System.out.println("\n");
    }
    
    @Test
    @DisplayName("Kiem tra ve hoa: + Ve cu thi thu hoi + Ve moi duoc them vao")
    public void testHoanVeMoi() throws SQLException{
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
       
        Date tgINChung = Date.from(Instant.now());
        Timestamp tgIN = new Timestamp(tgINChung.getTime());
        
        vexe vx1 = new vexe(sv_ve.getMaVeCurrent()+1, tgbd, GheTrong.get(11), cd.getMaChuyen(), kh.getMaKH(), nv.getMaNV(), xe.getMaXE(), tgIN, 1);
        vexe vxmoi = new vexe();
        vxmoi.setMaVE(sv_ve.getMaVeCurrent()+2);
        vxmoi.setSoghe(GheTrong.get(14));
        vxmoi.setThoigianbatdau(tgbd);
        vxmoi.setMaChuyen(7);
        vxmoi.setMaKH(1);
        vxmoi.setMaNV(1);
        vxmoi.setMaXE(7);
        vxmoi.setNgayin(tgIN);
        vxmoi.setTrangthai(1);
       
        System.out.println("TEST VE CAP NHAT SAU KHI HOAN VE VA TAO VE MOI: ");
        sv_ve.addVeXe(vx1); // Ve nay duoc them tu trc
        boolean veHoan1 = ckOP.isVeCanTransfer(vx1); // VE 1 h > 1tieng truoc khi bat dau
        Assertions.assertTrue(veHoan1,"Ve 1 hoan duoc");  // CHECK VE CO THE HOAN
    
        boolean trungGhe = ckOP.checkGheTrung(vxmoi);
        Assertions.assertFalse(trungGhe, "Ghe dang trong");
        
        sv_ve.hoanVeMoi(vx1, vxmoi);
        Assertions.assertNotEquals(vx1.getTrangthai(),sv_ve.getVeXe(vx1.getMaVE()).getTrangthai()); // BAN DAU la 1, luc sau se thanh 0
        
        System.out.println("\n");
    }
}
