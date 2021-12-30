/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import config.jdbcUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pojo.chuyendi;

/**
 *
 * @author Admin
 */
public class Sv_chuyendi {
    
    private static int MaCDCurrent = 0;
    
    public static int getMaCDCurrent(){
        return MaCDCurrent;
    }

    public List<chuyendi> getChuyendi() throws SQLException{
        List<chuyendi> dscd = new ArrayList<>();
        Connection conn = jdbcUtils.getConn();

        Statement stm = conn.createStatement();
        String sql = "SELECT * FROM chuyendi";
        ResultSet rs = stm.executeQuery(sql);
      
        while(rs.next()){
            chuyendi cd = new chuyendi(rs.getInt("MaChuyen"), rs.getString("TenChuyen"), rs.getDouble("Gia"), rs.getTimestamp("ThoiGianBatDau"), rs.getTimestamp("ThoiGianKetThuc"));
            dscd.add(cd);
            MaCDCurrent = rs.getInt("MaChuyen");
        }
        conn.close();
        return dscd;
    }
    
    public chuyendi getMaToChuyen(int maCD) throws SQLException{
        
        Connection conn = jdbcUtils.getConn();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("Select * from chuyendi where MaChuyen = " + maCD);
        chuyendi cd = new chuyendi();
        while(rs.next()){
            cd = new chuyendi(rs.getInt("MaChuyen"), rs.getString("TenChuyen"), rs.getDouble("Gia"), rs.getTimestamp("ThoiGianBatDau"), rs.getTimestamp("ThoiGianKetThuc"));
        }
        conn.close();
        return cd;
    }
    
    public List<chuyendi> getChuyendi(String kw) throws SQLException{
        List<chuyendi> dscd = new ArrayList<>();
        Connection conn = jdbcUtils.getConn();
        String sql = "Select * from chuyendi";
        if (kw != null && !kw.isEmpty()) {
            sql += " Where TenChuyen like '%" + kw +"%'";
        }
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);

        while (rs.next()) {
            chuyendi cd = new chuyendi(rs.getInt("MaChuyen"), rs.getString("TenChuyen"), rs.getDouble("Gia"), rs.getTimestamp("ThoiGianBatDau"), rs.getTimestamp("ThoiGianKetThuc"));
            dscd.add(cd);
        }
        conn.close();
        return dscd;
    }

    //THEM CHUYEN DI
    public void themChuyenDi(chuyendi cd) throws SQLException{
        try(Connection conn = jdbcUtils.getConn()){
            conn.setAutoCommit(false);
            PreparedStatement stm2 = conn.prepareStatement("INSERT INTO chuyendi(ThoiGianKetThuc,ThoiGianBatDau,Gia,TenChuyen,MaChuyen) VALUES (?,?,?,?,?)");
            stm2.setTimestamp(1, cd.getThoiGianKetThuc());
            stm2.setTimestamp(2, cd.getThoiGianBatDau());
            stm2.setDouble(3, cd.getGia());
            stm2.setString(4, cd.getTenChuyen());
            stm2.setInt(5, cd.getMaChuyen());
            stm2.executeUpdate();
            conn.commit();
        }
    }
    
    //SUA CHUYEN DI
    /**
     *
     * @param cd
     * @throws java.sql.SQLException
     */
    public void suaChuyenDi(chuyendi cd) throws SQLException {
        try(Connection conn = jdbcUtils.getConn()){
            conn.setAutoCommit(false);
            PreparedStatement stm3 = conn.prepareStatement("UPDATE chuyendi SET ThoiGianKetThuc=?, ThoiGianBatDau=?, Gia=?, TenChuyen=? WHERE MaChuyen = " + cd.getMaChuyen());
            stm3.setTimestamp(1, cd.getThoiGianKetThuc());
            stm3.setTimestamp(2, cd.getThoiGianBatDau());
            stm3.setDouble(3, cd.getGia());
            stm3.setString(4, cd.getTenChuyen());
            stm3.executeUpdate();
            conn.commit();
        }
    }
    
    //------------ CHUA HOAN THANH
    public void xoaChuyenDi(chuyendi cd) throws SQLException {
        try(Connection conn = jdbcUtils.getConn()){
            conn.setAutoCommit(false);
            PreparedStatement stm4 = conn.prepareStatement("DELETE FROM chuyendi WHERE MaChuyen = " + cd.getMaChuyen());
            stm4.executeUpdate();
            conn.commit();
        }
    }
}
