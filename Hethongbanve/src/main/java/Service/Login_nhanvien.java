/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import config.HashMK;
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
        conn.close();
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
        conn.close();
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
        conn.close();
        return tenNV;
    }
    
    public nhanvien getNhanVienFromMa(int maNV) throws SQLException{ //s la ten tai khoan
        nhanvien nv = new nhanvien();
        Connection conn = jdbcUtils.getConn();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("Select * from nhanvien where MaNV = " + maNV);
        List<nhanvien> result = new ArrayList<>();
        while(rs.next()){
            nhanvien nvt = new nhanvien(rs.getInt("MaNV"), rs.getString("TenNV"), rs.getString("CMND"), rs.getString("SDT"));
            result.add(nvt);
        }
        if(result.size() == 1){
            nv = result.get(0);
        }
        conn.close();
        return nv;
    }
    
    
    public int MaNhanVienCurrent(String s) throws SQLException{ //s la ten tai khoan
        int maNV = 0;
        Connection conn = jdbcUtils.getConn();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("Select nv.* from nhanvien_taikhoan tk_nv, nhanvien nv where tk_nv.Taikhoan = "+ "\"" + s + "\" and tk_nv.MaNV = nv.MaNV");
        List<nhanvien> result = new ArrayList<>();
        while(rs.next()){
            nhanvien nvt = new nhanvien(rs.getInt("MaNV"), rs.getString("TenNV"), rs.getString("CMND"), rs.getString("SDT"));
            result.add(nvt);
        }
        if(result.size() == 1){
            maNV = result.get(0).getMaNV();
        }
        conn.close();
        return maNV;
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
        conn.close();
        return maCV;
    }
    
    
    public boolean CheckLogin(String tk,String mkHash) throws SQLException{
        boolean b = false;
        Connection conn = jdbcUtils.getConn();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("Select * from nhanvien_taikhoan where Taikhoan = \'"+ tk + "\'");
        String mkData_hash = "";
        while(rs.next()){
            mkData_hash = rs.getString("Matkhau");
        }
        if(mkData_hash.equals(mkHash))
            b = true;
        conn.close();
        return b;
    }
    
    public nhanvien_taikhoan getTaiKhoanNVFromTK(String kw) throws SQLException{
        nhanvien_taikhoan tk = new nhanvien_taikhoan();
        
        Connection conn = jdbcUtils.getConn();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("Select * from nhanvien_taikhoan where Taikhoan = "+ "\"" + kw + "\"");
        
        while(rs.next()){
            tk = new nhanvien_taikhoan(rs.getInt("MaNV"), rs.getString("Taikhoan"), rs.getString("Matkhau"), rs.getInt("Chucvu"));
        }
        conn.close();
        return tk;
    }
    
}
