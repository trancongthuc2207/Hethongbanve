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
        String sql = "Select * from chuyendi";
        ResultSet rs = stm.executeQuery(sql);
        
        while(rs.next()){
            chuyendi cd = new chuyendi(rs.getInt("MaChuyen"), rs.getString("TenChuyen"), rs.getDouble("Gia"), rs.getTimestamp("ThoiGianBatDau"), rs.getTimestamp("ThoiGianKetThuc"));
            MaCDCurrent = rs.getInt("MaChuyen");
            dscd.add(cd);
        }
        return dscd;
    }
    
    public void themChuyenDi(chuyendi cd) throws SQLException{
        try(Connection conn = jdbcUtils.getConn()){
            conn.setAutoCommit(false);
            PreparedStatement stm2 = conn.prepareStatement("INSERT INTO chuyendi(MaChuyen,TenChuyen,Gia,ThoiGianBatDau,ThoiGianKetThuc) VALUES (?,?,?,?,?)");
            stm2.setInt(1, cd.getMaChuyen());
            stm2.setString(2, cd.getTenChuyen());
            stm2.setDouble(3, cd.getGia());
            stm2.setTimestamp(4, cd.getThoiGianBatDau());
            stm2.setTimestamp(5, cd.getThoiGianKetThuc());
            stm2.executeUpdate();
            conn.commit();
        }
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

    
}
