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
        Login_nhanvien sv_nv = new  Login_nhanvien();
        Sv_vexe sv_ve = new Sv_vexe();
        Sv_khachhang sv_kh = new Sv_khachhang();
        Sv_xe sv_xe = new Sv_xe();
        Sv_CheckOption ckOP = new Sv_CheckOption();
        Sv_chuyendi sv_cd = new Sv_chuyendi();
       
        
        chuyendi cd = sv_cd.getMaToChuyen(7); 
      
        khachhang kh = sv_kh.getMaToKH(1);     // TRUYEN : MA KHACH HANG
        xe xe = sv_xe.getMaToXE(7, 7); //1 maxe 2 machuyen        // TRUYEN : MA XE
        nhanvien nv = sv_nv.getNhanVienFromMa(1);       // TRUYEN : MA NHAN VIEN
               // THOI GIAN BAT DAU // TRUUYEN : MA CHUYEN
         
        List<String> GheTrong = sv_xe.getGheTrong(7);  // 7 la maXE  
        
        vexe vx1 = new vexe(sv_ve.getMaVeCurrent()+1, Timestamp.valueOf("2021-01-02 21:30:00"), GheTrong.get(0), cd.getMaChuyen(), kh.getMaKH(), nv.getMaNV(), xe.getMaXE(), Timestamp.valueOf("2021-01-02 20:30:00"), 1);
        vexe vx2 = new vexe(sv_ve.getMaVeCurrent()+2, Timestamp.valueOf("2021-01-02 21:30:00"), GheTrong.get(0), cd.getMaChuyen(), kh.getMaKH(), nv.getMaNV(), xe.getMaXE(), Timestamp.valueOf("2021-01-02 20:30:00"), 1);
        
        System.out.println("TEST TRUNG GHE");
        if(ckOP.checkGheTrung(vx1) != true){
            sv_ve.addVeXe(vx1);
            System.out.println("==> VE 1 THANH CONG");
        }
        List<vexe> kq = sv_ve.getVeXe();
        if(ckOP.checkGheTrung(vx2) != true)
            sv_ve.addVeXe(vx2);   // ---> SE BI FAIL DO TRUNG GHE
        else
            System.out.println("==> VE 2 TRÙNG GHẾ : TRUE");
        
        Set<vexe> kq2 = new HashSet<>(kq);
        Assertions.assertEquals(kq.size(), kq2.size());
        System.out.println("\n");
    }
    
    @Test
    public void testThoigiandatve() throws SQLException{
        beforeAll();
        Login_nhanvien sv_nv = new  Login_nhanvien();
        Sv_vexe sv_ve = new Sv_vexe();
        Sv_khachhang sv_kh = new Sv_khachhang();
        Sv_xe sv_xe = new Sv_xe();
        Sv_CheckOption ckOP = new Sv_CheckOption();
        Sv_chuyendi sv_cd = new Sv_chuyendi();
       
        chuyendi cd = sv_cd.getMaToChuyen(7); 

        khachhang kh = sv_kh.getMaToKH(1);     // TRUYEN : MA KHACH HANG
        xe xe = sv_xe.getMaToXE(7, 7); //1 maxe 2 machuyen        // TRUYEN : MA XE
        nhanvien nv = sv_nv.getNhanVienFromMa(1);       // TRUYEN : MA NHAN VIEN
               // THOI GIAN BAT DAU // TRUUYEN : MA CHUYEN
        
        List<String> GheTrong = sv_xe.getGheTrong(7);  // 7 la maXE  
        
        vexe vx1 = new vexe(sv_ve.getMaVeCurrent()+1, Timestamp.valueOf("2021-01-02 21:30:00"), GheTrong.get(1), cd.getMaChuyen(), kh.getMaKH(), nv.getMaNV(), xe.getMaXE(), Timestamp.valueOf("2021-01-02 20:31:00"), 1);
        vexe vx2 = new vexe(sv_ve.getMaVeCurrent()+2, Timestamp.valueOf("2021-01-02 21:30:00"), GheTrong.get(2), cd.getMaChuyen(), kh.getMaKH(), nv.getMaNV(), xe.getMaXE(), Timestamp.valueOf("2021-01-02 20:30:00"), 1);
        
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
    
    @Test
    public void testThoigianmuave() throws SQLException{
        beforeAll();
        Login_nhanvien sv_nv = new  Login_nhanvien();
        Sv_vexe sv_ve = new Sv_vexe();
        Sv_khachhang sv_kh = new Sv_khachhang();
        Sv_xe sv_xe = new Sv_xe();
        Sv_CheckOption ckOP = new Sv_CheckOption();
        Sv_chuyendi sv_cd = new Sv_chuyendi();
       
        chuyendi cd = sv_cd.getMaToChuyen(7);
        khachhang kh = sv_kh.getMaToKH(1);     // TRUYEN : MA KHACH HANG
        xe xe = sv_xe.getMaToXE(7, 7); //1 maxe 2 machuyen        // TRUYEN : MA XE
        nhanvien nv = sv_nv.getNhanVienFromMa(1);       // TRUYEN : MA NHAN VIEN
               // THOI GIAN BAT DAU // TRUUYEN : MA CHUYEN
             
        List<String> GheTrong = sv_xe.getGheTrong(7);  // 7 la maXE  
        
        vexe vx1 = new vexe(sv_ve.getMaVeCurrent()+1, Timestamp.valueOf("2021-01-02 21:30:00"), GheTrong.get(3), cd.getMaChuyen(), kh.getMaKH(), nv.getMaNV(), xe.getMaXE(), Timestamp.valueOf("2021-01-02 21:26:00"), 2);
        vexe vx2 = new vexe(sv_ve.getMaVeCurrent()+2, Timestamp.valueOf("2021-01-02 21:30:00"), GheTrong.get(4), cd.getMaChuyen(), kh.getMaKH(), nv.getMaNV(), xe.getMaXE(), Timestamp.valueOf("2021-01-02 21:24:00"), 2);
        
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
        Login_nhanvien sv_nv = new  Login_nhanvien();
        Sv_vexe sv_ve = new Sv_vexe();
        Sv_khachhang sv_kh = new Sv_khachhang();
        Sv_xe sv_xe = new Sv_xe();
        Sv_CheckOption ckOP = new Sv_CheckOption();
        Sv_chuyendi sv_cd = new Sv_chuyendi();
       
        chuyendi cd = sv_cd.getMaToChuyen(7); 

        khachhang kh = sv_kh.getMaToKH(1);     // TRUYEN : MA KHACH HANG
        xe xe = sv_xe.getMaToXE(7, 7); //1 maxe 2 machuyen        // TRUYEN : MA XE
        nhanvien nv = sv_nv.getNhanVienFromMa(1);       // TRUYEN : MA NHAN VIEN
               // THOI GIAN BAT DAU // TRUUYEN : MA CHUYEN
        
        List<String> GheTrong = sv_xe.getGheTrong(7);  // 7 la maXE  
        
        vexe vx1 = new vexe(sv_ve.getMaVeCurrent()+1, Timestamp.valueOf("2021-01-03 00:30:00"), GheTrong.get(5), cd.getMaChuyen(), kh.getMaKH(), nv.getMaNV(), xe.getMaXE(), Timestamp.valueOf("2021-01-02 21:30:00"), 1);
        vexe vx2 = new vexe(sv_ve.getMaVeCurrent()+2, Timestamp.valueOf("2021-01-02 21:30:00"), GheTrong.get(6), cd.getMaChuyen(), kh.getMaKH(), nv.getMaNV(), xe.getMaXE(), Timestamp.valueOf("2021-01-02 21:30:00"), 1);
        
        Date dateCur = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        ///DEFAULT
        String date = sdf.format(dateCur); // Lay thoi gian hien tai
        Timestamp tgHT = Timestamp.valueOf(date);
        
        System.out.println("TEST VE DUOC HOAN LAI TRC 1h: ");
        System.out.println("Thoi gian hien tai la: " + tgHT);
        if(ckOP.isVeCanTransfer(vx1))
        {
            sv_ve.addVeXe(vx1);
            System.out.println("==> VE 1 THANH CONG");
            System.out.println("==> TIME VE 1: " + vx1.getThoigianbatdau());
        }
        List<vexe> kq = sv_ve.getVeXe();
        if(ckOP.isVeCanTransfer(vx2))
            sv_ve.addVeXe(vx2);
        else{
            System.out.println("==> VE 2 FAIL GIO HOAN < 1h ");
            System.out.println("==> TIME VE 2: " + vx2.getThoigianbatdau());
        }
        Set<vexe> kq2 = new HashSet<>(kq);
        Assertions.assertEquals(kq.size(), kq2.size());
        System.out.println("\n");
    }
}
