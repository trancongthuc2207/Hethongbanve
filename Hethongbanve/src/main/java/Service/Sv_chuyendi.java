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
    
    public List<chuyendi> getChuyendi() throws SQLException{
        List<chuyendi> dscd = new ArrayList<>();
        Connection conn = jdbcUtils.getConn();
        
        Statement stm = conn.createStatement();
        String sql = "Select * from chuyendi";
        ResultSet rs = stm.executeQuery(sql);
        
        while(rs.next()){
            chuyendi cd = new chuyendi(rs.getInt("MaChuyen"), rs.getString("TenChuyen"), rs.getDouble("Gia"), rs.getTimestamp("ThoiGianBatDau"), rs.getTimestamp("ThoiGianKetThuc"));
            dscd.add(cd);
        }
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
        return cd;
    }
    
    public List<chuyendi> getChuyendi(String kw) throws SQLException{
        List<chuyendi> dscd = new ArrayList<>();
        Connection conn = jdbcUtils.getConn();
        String sql = "Select * from chuyendi";
        if (kw != null && !kw.isEmpty()) {
            sql += " Where TenChuyen like \'%" + kw +"%\'";
        }
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);

        while (rs.next()) {
            chuyendi cd = new chuyendi(rs.getInt("MaChuyen"), rs.getString("TenChuyen"), rs.getDouble("Gia"), rs.getTimestamp("ThoiGianBatDau"), rs.getTimestamp("ThoiGianKetThuc"));
            dscd.add(cd);
        }            
        return dscd;
    }
    
}
