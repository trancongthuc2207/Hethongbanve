/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

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
import java.util.List;
import pojo.chuyendi;
import pojo.vexe;
import pojo.xe_ghe;

/**
 *
 * @author Admin
 */
public class Sv_CheckOption {
    
    public boolean checkTimeDatVe(vexe vx) throws SQLException{  // trc 1h
        Sv_chuyendi cd = new Sv_chuyendi();
        boolean check = false;
        if((cd.getMaToChuyen(vx.getMaChuyen()).getThoiGianBatDau().getTime() - vx.getNgayin().getTime()) >= (60*60*1000))
            check = true;
        System.out.println(check);
        return check;
    }
    
    public boolean checkGheTrung(vexe vx) throws SQLException{ //TRUE LÀ TRÙNG
        boolean check = false;
        List<xe_ghe> lstG = new ArrayList<>();
        try {
            Connection conn = jdbcUtils.getConn();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("Select * from xe_ghe where MaXE = " + vx.getMaXE() + 
                    " and " + vx.getSoghe() + " = 1");
            while(rs.next()){
                xe_ghe xG = new xe_ghe(rs.getInt("MaXE"), rs.getInt("g1"), rs.getInt("g2"), rs.getInt("g3"), rs.getInt("g4"), rs.getInt("g5"), rs.getInt("g6"), rs.getInt("g7"), rs.getInt("g8"), rs.getInt("g9"), rs.getInt("g10"), rs.getInt("g11"), rs.getInt("g12"), rs.getInt("g13"), rs.getInt("g14"), rs.getInt("g15"), rs.getInt("g16"));
                lstG.add(xG);
            }
            if(lstG.size() > 0){
                System.out.println(lstG.size());
                check = true;  
            }
        } catch (SQLException sQLException) {
        }
        return check;
    }
    
    public boolean checkTimeMuaVe(vexe vx) throws SQLException{ //trc 5p
        Sv_chuyendi cd = new Sv_chuyendi();
        boolean check = false;
        if((cd.getMaToChuyen(vx.getMaChuyen()).getThoiGianBatDau().getTime() - vx.getNgayin().getTime()) > (5*60*1000))
            check = true;
        System.out.println(check);
        return check;
    }
    
    public boolean hieuLucVeDat(vexe vx) throws SQLException{
        boolean check = false;
        Date dateCur = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        ///DEFAULT
        String date = sdf.format(dateCur);
        
        Timestamp tgHT = Timestamp.valueOf(date); //THOI GIAN HIEN TAI        
        Timestamp tGHetHieuLuc = new Timestamp(vx.getNgayin().getTime() + 30*60*1000); // THOI GIAN IN + 30p

        if((tgHT.getTime() >= tGHetHieuLuc.getTime()) && vx.getTrangthai() != 2)
            check = true;  // HẾT HIỆU LỰC
        System.out.println(check);
        return check;
    }
}
