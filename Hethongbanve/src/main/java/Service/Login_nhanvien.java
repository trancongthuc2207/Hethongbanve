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
import pojo.nhanvien;
import pojo.nhanvien_taikhoan;

/**
 *
 * @author Admin
 */
public class Login_nhanvien {
    
    public List<nhanvien> getNhanvien() throws SQLException {
        Connection conn = jdbcUtils.getConn();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("Select * from nhanvien");
        List<nhanvien> result = new ArrayList<>();
        while(rs.next()){
            nhanvien nv = new nhanvien(rs.getInt("MaNV"), rs.getString("TenNV"),rs.getString("CMND"),rs.getString("SDT"));
            result.add(nv);
        }
        return result;
    }
    
    public List<nhanvien_taikhoan> getNhanvien_TaiKhoan() throws SQLException {
        Connection conn = jdbcUtils.getConn();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("Select * from nhanvien_taikhoan");
        List<nhanvien_taikhoan> result = new ArrayList<>();
        while(rs.next()){
            nhanvien_taikhoan nv_tk = new nhanvien_taikhoan(rs.getInt("MaNV"), rs.getString("Taikhoan"),rs.getString("Matkhau"),rs.getInt("Chucvu"));
            result.add(nv_tk);
        }
        return result;
    }
    
    public String TenNhanVienCurrent(String s) throws SQLException{ //s la ten tai khoan
        String tenNV = "";
        Connection conn = jdbcUtils.getConn();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("Select nv.* from nhanvien_taikhoan tk_nv, nhanvien nv where tk_nv.Taikhoan = "+ "\"" + s + "\" and tk_nv.MaNV = nv.MaNV");
        List<nhanvien> result = new ArrayList<>();
        while(rs.next()){
            nhanvien nvt = new nhanvien(rs.getInt("MaNV"), rs.getString("TenNV"), rs.getString("CMND"), rs.getString("SDT"));
            result.add(nvt);
        }
        if(result.size() == 1){
            tenNV = result.get(0).getTenNV();
        }
        return tenNV;
    }
    
    public int ChucVuNhanVienCurrent(String s) throws SQLException{
        int maCV = 0;
        Connection conn = jdbcUtils.getConn();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("Select * from nhanvien_taikhoan where Taikhoan = "+ "\"" + s + "\"");
        List<nhanvien_taikhoan> result = new ArrayList<>();
        while(rs.next()){
            nhanvien_taikhoan nvt = new nhanvien_taikhoan(rs.getInt("MaNV"), rs.getString("Taikhoan"), rs.getString("Matkhau"), rs.getInt("Chucvu"));
            result.add(nvt);
        }
        if(result.size() == 1){
            maCV = result.get(0).getChucvu();
        }
        return maCV;
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
