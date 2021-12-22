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
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import pojo.khachhang;

/**
 *
 * @author Admin
 */
public class Sv_khachhang {
    private static int MaKHCurrent = 0;
    
    public static int getMaKHCurrent(){
        return MaKHCurrent;
    }
    
    public List<khachhang> getKhachHang() throws SQLException
    {
        Connection conn = jdbcUtils.getConn();
        Statement stm = conn.createStatement();
        String sql = "Select * from khachhang";
        ResultSet rs = stm.executeQuery(sql);
        List<khachhang> result = new ArrayList<>();
        while(rs.next()){
            khachhang kh = new khachhang(rs.getInt("MaKH"),rs.getString("TenKH"),rs.getString("CMND"),rs.getString("SDT"));
            result.add(kh);
        }
        MaKHCurrent = result.size();
        return result;
    }
    
    public void addKhachhang(khachhang kh) throws SQLException{
        try(Connection conn = jdbcUtils.getConn()){
            conn.setAutoCommit(false);
            PreparedStatement stm1 = conn.prepareStatement("INSERT INTO khachhang(MaKH,TenKH,CMND,SDT) VALUES (?,?,?,?)");
            stm1.setInt(1, kh.getMaKH());
            stm1.setString(2, kh.getTenKH());
            stm1.setString(3, kh.getCMND());
            stm1.setString(4, kh.getSDT());
            
            stm1.executeUpdate();
            conn.commit();
        }
    }
}
