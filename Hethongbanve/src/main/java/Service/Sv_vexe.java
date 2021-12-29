/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import static com.mycompany.hethongbanve.Menu_DatveController.DATE_FORMAT_NOW;
import config.jdbcUtils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.binding.Bindings;
import pojo.vexe;

/**
 *
 * @author Admin
 */
public class Sv_vexe {
    public static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
    public static final SimpleDateFormat DateFormat = new SimpleDateFormat(DATE_FORMAT_NOW);
    private static int MaVeCurrent = 0;
    
    public static int getMaVeCurrent(){
        return MaVeCurrent;
    }
    
    public List<vexe> getVeXe() throws SQLException{
        List<vexe> dsVe = new ArrayList<>();
        Connection conn = jdbcUtils.getConn();
        
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("Select * from vexe");
        
        while(rs.next()){
            vexe ve = new vexe(rs.getInt("Mave"), rs.getTimestamp("ThoiGianBatDau"),rs.getString("Soghe"), rs.getInt("MaChuyen"), rs.getInt("MaKH"), rs.getInt("MaNV"), rs.getInt("MaXE"), rs.getTimestamp("Ngayin"),rs.getInt("Trangthai"));
            this.MaVeCurrent = rs.getInt("Mave");
            dsVe.add(ve);
            
        }
        return dsVe;
    }
    
    public vexe getVeXe(int MaVe) throws SQLException{
        vexe ve = new vexe();
        Connection conn = jdbcUtils.getConn();
        
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("Select * from vexe where MaVE = " + MaVe);
        
        while(rs.next()){
            ve = new vexe(rs.getInt("Mave"), rs.getTimestamp("ThoiGianBatDau"),rs.getString("Soghe"), rs.getInt("MaChuyen"), rs.getInt("MaKH"), rs.getInt("MaNV"), rs.getInt("MaXE"), rs.getTimestamp("Ngayin"),rs.getInt("Trangthai"));
        }
        return ve;
    }
    
    
    public void addVeXe(vexe vx) throws SQLException{ // DAT VE
        //Sv_chuyendi svCD = new Sv_chuyendi();
        Sv_CheckOption ckOP = new Sv_CheckOption();
        Sv_Update_CD_XeGhe upGhe = new Sv_Update_CD_XeGhe();
        if(ckOP.checkTimeDatVe(vx) == true){
            if(ckOP.checkGheTrung(vx) != true){ // true là trùng
                try(Connection conn = jdbcUtils.getConn()){
                    conn.setAutoCommit(false);
                    PreparedStatement stm1 = conn.prepareStatement("INSERT INTO vexe(MaVE,Thoigianbatdau,Soghe,MaChuyen,MaKH,MaNV,MaXE,Ngayin,Trangthai) VALUES (?,?,?,?,?,?,?,?,?)");
                    stm1.setInt(1, vx.getMaVE());
                    stm1.setTimestamp(2, (Timestamp) vx.getThoigianbatdau());
                    stm1.setString(3, vx.getSoghe());
                    stm1.setInt(4, vx.getMaChuyen());
                    stm1.setInt(5, vx.getMaKH());
                    stm1.setInt(6, vx.getMaNV());
                    stm1.setInt(7, vx.getMaXE());
                    stm1.setTimestamp(8, (Timestamp) vx.getNgayin());
                    stm1.setInt(9, vx.getTrangthai());
                    stm1.executeUpdate();
                    conn.commit();
                }
                upGhe.UpdateGheForXe(vx);
            }
            else
                return;
        }
        else{
            return;
        }
    }
    
    public void addMuaVeXe(vexe vx) throws SQLException{ // MUA VE
        //Sv_chuyendi svCD = new Sv_chuyendi();
        Sv_CheckOption ckOP = new Sv_CheckOption();
        Sv_Update_CD_XeGhe upGhe = new Sv_Update_CD_XeGhe();
        if(ckOP.checkTimeMuaVe(vx) == true){
            if(ckOP.checkGheTrung(vx) != true){ // true là trùng
                try(Connection conn = jdbcUtils.getConn()){
                    conn.setAutoCommit(false);
                    PreparedStatement stm1 = conn.prepareStatement("INSERT INTO vexe(MaVE,Thoigianbatdau,Soghe,MaChuyen,MaKH,MaNV,MaXE,Ngayin,Trangthai) VALUES (?,?,?,?,?,?,?,?,?)");
                    stm1.setInt(1, vx.getMaVE());
                    stm1.setTimestamp(2, (Timestamp) vx.getThoigianbatdau());
                    stm1.setString(3, vx.getSoghe());
                    stm1.setInt(4, vx.getMaChuyen());
                    stm1.setInt(5, vx.getMaKH());
                    stm1.setInt(6, vx.getMaNV());
                    stm1.setInt(7, vx.getMaXE());
                    stm1.setTimestamp(8, (Timestamp) vx.getNgayin());
                    stm1.setInt(9, vx.getTrangthai());
                    stm1.executeUpdate();
                    conn.commit();
                }
                upGhe.UpdateGheForXe(vx);
            }
            else
                return;
        }
        else{
            return;
        }
    }
    
    ///CAP NHAT SAU KHOANG THOI GIAN NHAT DE CHECK HIEU LUC VE
    
    public void capNhatVe() throws SQLException{
        Sv_CheckOption CkStatus = new Sv_CheckOption();
        Sv_Update_TrangThaiVe upDateStatus = new Sv_Update_TrangThaiVe();
        List<vexe> list = new ArrayList<>();
        list = this.getVeXe();
        
        for(vexe v : list){
            if(CkStatus.hieuLucVeDat(v) == true){
                upDateStatus.UpdateTrangThaiVeToNull(v);
            }
            System.out.println(v.getTrangthai());
        }
        
    }
    
}
