/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import config.jdbcUtils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pojo.nhanvien_taikhoan;

/**
 *
 * @author Admin
 */
public class Login_nhanvien {
    public List<String> getAccountString() throws SQLException {
        
        Connection conn = jdbcUtils.getConn();
        
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("Select * from nhanvien_taikhoan");
        List<String> result = new ArrayList<>();
        while(rs.next()){
            String tk_mk = rs.getString("Taikhoan") + rs.getString("Matkhau");
            result.add(tk_mk);
        }
        List<nhanvien_taikhoan> NV = new ArrayList<>();
        while(rs.next()){
            nhanvien_taikhoan acc = new nhanvien_taikhoan(rs.getInt("MaNV"),rs.getString("Taikhoan"),rs.getString("Taikhoan"),rs.getInt("Chucvu"));
            NV.add(acc);
        }
        return result;
    }
    
    public boolean CheckLogin(String s) throws SQLException{
        boolean b = false;
        Connection conn = jdbcUtils.getConn();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("Select * from nhanvien_taikhoan");
        List<String> result = new ArrayList<>();
        while(rs.next()){
            String tk_mk = rs.getString("Taikhoan") + rs.getString("Matkhau");
            result.add(tk_mk);
        }
        for(String p : result)
        {
            if(p.equals(s))
            {
                b = true;
                break;
            }
        }
        return b;
    }
}
