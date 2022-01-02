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
       
        //THOI GIAN HIEN TAI - GIO IN
        Date dateCur = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        ///DEFAULT
        String date = sdf.format(dateCur);
        
        Timestamp tgHT = Timestamp.valueOf(date); //THOI GIAN HIEN TAI 
        ///
        
        chuyendi cd = sv_cd.getMaToChuyen(7); 
        khachhang kh = sv_kh.getMaToKH(1);     // TRUYEN : MA KHACH HANG
        xe xe = sv_xe.getMaToXE(7, 7); //1 maxe 2 machuyen        // TRUYEN : MA XE
        nhanvien nv = sv_nv.getNhanVienFromMa(1);       // TRUYEN : MA NHAN VIEN
               // THOI GIAN BAT DAU // TRUUYEN : MA CHUYEN
         
        List<String> GheTrong = sv_xe.getGheTrong(7);  // 7 la maXE  
        
        vexe vx1 = new vexe(sv_ve.getMaVeCurrent()+1, cd.getThoiGianBatDau(), GheTrong.get(1), cd.getMaChuyen(), kh.getMaKH(), nv.getMaNV(), xe.getMaChuyen(), tgHT, 1);
        vexe vx2 = new vexe(sv_ve.getMaVeCurrent()+2, cd.getThoiGianBatDau(), GheTrong.get(1), cd.getMaChuyen(), kh.getMaKH(), nv.getMaNV(), xe.getMaChuyen(), tgHT, 1);
        
        List<vexe> kq = sv_ve.getVeXe();
        
        sv_ve.addVeXe(vx1);
        sv_ve.addVeXe(vx2);   // ---> SE BI FAIL DO TRUNG GHE
        
        Set<vexe> kq2 = new HashSet<>(kq);
        Assertions.assertEquals(kq.size(), kq2.size());
        
    }
}
