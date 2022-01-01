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
import pojo.xe;
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
            check = true;  // ĐẶT ĐƯỢC
        return check;
    }
    
    public boolean checkGheTrung(vexe vx) throws SQLException{ //TRUE LÀ TRÙNG
        boolean check = false;              //trả về danh sach ghế nếu Ghế đó đã được đặt từ vé khác
        List<xe_ghe> lstG = new ArrayList<>();
        Connection conn = jdbcUtils.getConn();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("Select * from xe_ghe where MaXE = " + vx.getMaXE() + 
                " and " + vx.getSoghe() + " = 1");
        while(rs.next()){
            xe_ghe xG = new xe_ghe(rs.getInt("MaXE"), rs.getInt("g1"), rs.getInt("g2"), rs.getInt("g3"), rs.getInt("g4"), rs.getInt("g5"), rs.getInt("g6"), rs.getInt("g7"), rs.getInt("g8"), rs.getInt("g9"), rs.getInt("g10"), rs.getInt("g11"), rs.getInt("g12"), rs.getInt("g13"), rs.getInt("g14"), rs.getInt("g15"), rs.getInt("g16"));
            lstG.add(xG);
        }
        if(lstG.size() > 0){
            check = true;   /// trùng
        }
        conn.close();
        return check;
    }
    
    public boolean checkTimeMuaVe(vexe vx) throws SQLException{ //trc 5p
        Sv_chuyendi cd = new Sv_chuyendi();
        boolean check = false;
        if((cd.getMaToChuyen(vx.getMaChuyen()).getThoiGianBatDau().getTime() - vx.getNgayin().getTime()) > (5*60*1000))
            check = true;
        return check;
    }
    
    public boolean hieuLucVeDat(vexe vx) throws SQLException{
        boolean check = false;
        Date dateCur = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        ///DEFAULT
        String date = sdf.format(dateCur);
        
        Timestamp tgHT = Timestamp.valueOf(date); //THOI GIAN HIEN TAI 
        Sv_chuyendi cd = new Sv_chuyendi();
        Timestamp tGHetHieuLuc = new Timestamp(cd.getMaToChuyen(vx.getMaChuyen()).getThoiGianBatDau().getTime() - 30*60*1000); // THOI GIAN CHUYEN DI - 30p (Truoc 30p trang thai ve vẫn = 1)
        
        if((tgHT.getTime() >= tGHetHieuLuc.getTime()) && vx.getTrangthai() != 2)
            check = true;  // HẾT HIỆU LỰC
        return check;
    }
    
    public boolean isOutOfTimeToMove(chuyendi cd){  /// XÉT THỜI GIAN HỆ THỐNG > THỜI GIAN BẮT ĐẦU CỦA CHUYẾN ĐI
        boolean check = false;                      /// ĐỂ NGĂN CHẶN VIỆC ĐẶT VÉ
        Date dateCur = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        ///DEFAULT
        String date = sdf.format(dateCur);
        
        Timestamp tgHT = Timestamp.valueOf(date); //THOI GIAN HIEN TAI
        if((tgHT.getTime() >= cd.getThoiGianBatDau().getTime()))  // TRUE LÀ HẾT HẠN
            check = true;
        
        return check;
    }
    
    public boolean isTimeXeToMove(xe xe) throws SQLException{  /// XÉT THỜI GIAN HỆ THỐNG > THỜI GIAN BẮT ĐẦU CỦA CHUYẾN ĐI
        boolean check = false;                      /// ĐỂ NGĂN CHẶN VIỆC ĐẶT 
        Sv_chuyendi cd = new Sv_chuyendi();
        Date dateCur = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        ///DEFAULT
        String date = sdf.format(dateCur);
        
        Timestamp tgHT = Timestamp.valueOf(date); //THOI GIAN HIEN TAI
        if(tgHT.getTime() >= cd.getMaToChuyen(xe.getMaChuyen()).getThoiGianBatDau().getTime())  // TRUE LÀ HẾT HẠN
            check = true;
        
        return check;
    }
    
    public boolean isFullSlotGhe(xe xe) throws SQLException{
        boolean check = true;
        Connection conn = jdbcUtils.getConn();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("Select * from xe_ghe where MaXE = " + xe.getMaXE());
        while(rs.next()){
            for(int i = 1; i <= 16; i++){
                int xGT = rs.getInt("g" + i);
                if(xGT == 0)
                    return check = false;
            }
        }
        conn.close();
        
        return check;
    }
    
    public boolean isCanForDeleteVe(vexe vx){
        boolean check = true;           // true là xoá đc, false là không xoá đc
        if(vx.getTrangthai() == 2 || vx.getTrangthai() == 0)     // 2 là ko thể xoá
            check = false;
        return check;
    }
    
    public boolean isVeThuHoi(vexe vx){
        boolean check = false;           // true là thu hoi, false là không 
        if(vx.getTrangthai() == 0 || vx.getTrangthai() == 2)     
            check = true;
        return check;
    }
    
    public boolean isVeChuaNhan(vexe vx){
        boolean check = false;           // true là thu hoi, false là không 
        if(vx.getTrangthai() == 1)     
            check = true;
        return check;
    }
    
    public boolean isVeCanTransfer(vexe vx) throws SQLException{
        Date dateCur = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        ///DEFAULT
        String date = sdf.format(dateCur); // Lay thoi gian hien tai
        Timestamp tgHT = Timestamp.valueOf(date);
        boolean check = false;
        Sv_chuyendi cd = new Sv_chuyendi();
        if((cd.getMaToChuyen(vx.getMaChuyen()).getThoiGianBatDau().getTime() - tgHT.getTime()) >= (60*60*1000))
            check = true;  // ĐẶT ĐƯỢC
        return check;
    }
   
}
