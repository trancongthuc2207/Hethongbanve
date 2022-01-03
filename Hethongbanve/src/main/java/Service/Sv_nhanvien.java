/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
import pojo.nhanvien;

/**
 *
 * @author huqedgar_user
 */
public class Sv_nhanvien {
    public List<nhanvien> getNhanVien() throws SQLException {
        Connection conn = jdbcUtils.getConn();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("Select * from nhanvien");
        List<nhanvien> result = new ArrayList<>();
        while(rs.next()){
            nhanvien nv = new nhanvien(rs.getInt("MaNV"), rs.getString("TenNV"),rs.getString("CMND"),rs.getString("SDT"));
            result.add(nv);
        }
        conn.close();
        return result;
    }
    
    public List<nhanvien> getNhanVien(String kw) throws SQLException{
        List<nhanvien> dsnv = new ArrayList<>();
        Connection conn = jdbcUtils.getConn();
        
        Sv_CheckOption check = new Sv_CheckOption();
        String sql = "Select * from nhanvien";
        if (kw.isEmpty())
            sql = "Select * from nhanvien";
        if (check.isNumeric(kw))
            sql = "Select * from nhanvien where MaNV = " + Integer.valueOf(kw);
        if (kw != null && !kw.isEmpty())
            sql = "Select * from nhanvien where TenNV like '%" + kw +"%'";
        
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        
        while (rs.next()) {
            nhanvien nv = new nhanvien(rs.getInt("MaNV"), rs.getString("TenNV"), rs.getString("CMND"), rs.getString("SDT"));
            dsnv.add(nv);
        }
        conn.close();
        return dsnv;
    }
    
    public void camSuDung(nhanvien nv) throws SQLException {
        try(Connection conn = jdbcUtils.getConn()){
            conn.setAutoCommit(false);
            PreparedStatement stm = conn.prepareStatement("UPDATE nhanvien_taikhoan SET Chucvu=0 WHERE MaNV = " + nv.getMaNV());
            stm.executeUpdate();
            conn.commit();
            conn.close();
        }
    }
    
    public void choPhepSuDung(nhanvien nv) throws SQLException {
        try(Connection conn = jdbcUtils.getConn()){
            conn.setAutoCommit(false);
            PreparedStatement stm = conn.prepareStatement("UPDATE nhanvien_taikhoan SET Chucvu=1 WHERE MaNV = " + nv.getMaNV());
            stm.executeUpdate();
            conn.commit();
            conn.close();
        }
    }
}
