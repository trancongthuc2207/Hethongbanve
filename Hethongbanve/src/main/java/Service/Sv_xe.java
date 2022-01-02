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
import pojo.xe;
import pojo.xe_ghe;

/**
 *
 * @author Admin
 */
public class Sv_xe {
    
    private static int MaXeCurrent = 0;
    
    public static int getMaXeCurrent(){
        return MaXeCurrent;
    }
    
    //////TRẢ VỀ DANH SÁCH XE
    public List<xe> getXe() throws SQLException{
        List<xe> dsxe = new ArrayList<>();
        Connection conn = jdbcUtils.getConn();
        
        Statement stm = conn.createStatement();
        String sql = "Select * from xe";
        ResultSet rs = stm.executeQuery(sql);
        
        while(rs.next()){
            xe xe = new xe(rs.getInt("MaXE"), rs.getString("TenXe"), rs.getString("Bienso"), rs.getInt("Trangthai"),rs.getInt("MaChuyen"));
            dsxe.add(xe);
            MaXeCurrent = rs.getInt("MaXE");
        }
        conn.close();
        return dsxe;
    }
    ///// TRẢ VỀ DANH SÁCH GHẾ XE CỦA MÃ XE ĐÓ
    public List<xe_ghe> getGhe(int maXE) throws SQLException{
        List<xe_ghe> lstG = new ArrayList<>();
        Connection conn = jdbcUtils.getConn();
        
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("Select * from MaXE = " + maXE);
        
        while(rs.next()){
            xe_ghe xG = new xe_ghe(rs.getInt("MaXE"), rs.getInt("g1"), rs.getInt("g2"), rs.getInt("g3"), rs.getInt("g4"), rs.getInt("g5"), rs.getInt("g6"), rs.getInt("g7"), rs.getInt("g8"), rs.getInt("g9"), rs.getInt("g10"), rs.getInt("g11"), rs.getInt("g12"), rs.getInt("g13"), rs.getInt("g14"), rs.getInt("g15"), rs.getInt("g16"));
            lstG.add(xG);
        }
        conn.close();
        return lstG;
    }
  
    //TRẢ DANH SÁCH KIỂU STRING CỦA NHỮNG GHẾ TRỐNG
    public List<String> getGheTrong(int maXE) throws SQLException{
        List<String> lstGT = new ArrayList<>();
        Connection conn = jdbcUtils.getConn();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("Select * from xe_ghe where MaXE = " + maXE);
        while(rs.next()){
            for(int i = 1; i <= 16; i++){
                int xGT = rs.getInt("g" + i);
                if(xGT == 0)
                    lstGT.add("g"+i+" ");
                else
                    lstGT.add("g"+i+" (ĐÃ ĐƯỢC ĐẶT)");
            }
        }
        conn.close();
        return lstGT;
    }
    
    ///TÌM XE PHÙ HỢP CHO CHUYẾN ĐI Ở PHẦN ĐẶT VÉ
    public xe getMaToXE(int maXE,int maCD) throws SQLException{
        Connection conn = jdbcUtils.getConn();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("Select * from xe where MaXE = " + maXE + " and MaChuyen = " + maCD);
        xe xe = new xe();
        while(rs.next()){
           xe = new xe(rs.getInt("MaXE"), rs.getString("TenXe"), rs.getString("Bienso"), rs.getInt("Trangthai"),rs.getInt("MaChuyen"));
        }
        conn.close();
        return xe;
    }
    
        public xe getMaToXE(int maXE) throws SQLException{
        Connection conn = jdbcUtils.getConn();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("Select * from xe where MaXE = " + maXE);
        xe xe = new xe();
        while(rs.next()){
           xe = new xe(rs.getInt("MaXE"), rs.getString("TenXe"), rs.getString("Bienso"), rs.getInt("Trangthai"),rs.getInt("MaChuyen"));
        }
        conn.close();
        return xe;
    }
    
    /////TRẢ DANH SÁCH XE ĐƯỢC TÌM KIẾM TỪ MÃ CHUYẾN ĐI
    public List<xe> getXeFromMaCD(int maCD) throws SQLException{
        List<xe> dsxe = new ArrayList<>();
        Connection conn = jdbcUtils.getConn();
        
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("Select * from xe where MaChuyen = " + maCD);
        
        while(rs.next()){
            xe xe = new xe(rs.getInt("MaXE"), rs.getString("TenXe"), rs.getString("Bienso"), rs.getInt("Trangthai"),rs.getInt("MaChuyen"));
            dsxe.add(xe);
        }
        conn.close();
        return dsxe;
    }
    
    /////TRẢ DANH SÁCH XE ĐƯỢC TÌM KIẾM TỪ MÃ CHUYẾN ĐI
    public List<xe> getXeFromMaCD(String kw) throws SQLException{
        List<xe> dsxe = new ArrayList<>();
        Connection conn = jdbcUtils.getConn();
        
        Sv_CheckOption check = new Sv_CheckOption();
        String sql = "Select * from xe";
        if (kw.isEmpty())
            sql = "Select * from xe";
        if ("empty".equalsIgnoreCase(kw) || "0".equals(kw))
            sql = "Select * from xe where MaChuyen is null";
        if (kw != null && !kw.isEmpty() && !"0".equals(kw) && check.isNumeric(kw))
            sql = "Select * from xe where MaChuyen = " + Integer.valueOf(kw);
        
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        
        while (rs.next()) {
            xe xe = new xe(rs.getInt("MaXE"), rs.getString("TenXe"), rs.getString("Bienso"), rs.getInt("Trangthai"),rs.getInt("MaChuyen"));
            dsxe.add(xe);
        }            
        conn.close();
        return dsxe;
    }
    
    //////TRẢ DANH SÁCH XE BĂNG TỪ KHOÁ TÌM KIẾM THEO TÊN
    public List<xe> getXe(String kw) throws SQLException{
        List<xe> dsxe = new ArrayList<>();
        Connection conn = jdbcUtils.getConn();
        
        String sql = "Select * from xe";
        if (kw != null && !kw.isEmpty()) {
            sql += " Where TenXe like \'%" + kw +"%\'";
        }
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);

        while (rs.next()) {
            xe xe = new xe(rs.getInt("MaXE"), rs.getString("TenXe"), rs.getString("Bienso"), rs.getInt("Trangthai"),rs.getInt("MaChuyen"));
            dsxe.add(xe);
        }            
        conn.close();
        return dsxe;
    }
    
    ///// TRẢ VỀ MÃ CHUYẾN ĐƯỢC TÌM BỞI MÃ XE
    public int maChuyenOfMaXE(int maXE) throws SQLException{
        int maChuyen = 0;
        Connection conn = jdbcUtils.getConn();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("Select * from xe where MaXe = " + maXE);
        xe xe = new xe();
        while(rs.next()){
           xe = new xe(rs.getInt("MaXE"), rs.getString("TenXe"), rs.getString("Bienso"), rs.getInt("Trangthai"),rs.getInt("MaChuyen"));
        }
        maChuyen = xe.getMaChuyen();
        conn.close();
        return maChuyen;
    }
    
    //// CẬP NHẬT XE ĐÃ DI CHUYÊN HAY CHƯA
    public void capNhatTrangThaiXe() throws SQLException{
        Sv_CheckOption CkStatus = new Sv_CheckOption();
        Sv_chuyendi sv_cd = new Sv_chuyendi();
        Sv_Update_TrangThaiXe upDateStatus = new Sv_Update_TrangThaiXe();
        List<xe> list = new ArrayList<>();
        list = this.getXe();
        
        for(xe x : list){
            if(CkStatus.isTimeXeToMove(x) == true){
                    upDateStatus.UpdateTrangThaiXeToDiChuyen(x);
            }
        }
    }
    
    //THEM XE
    public void themXe(xe xe) throws SQLException{
        try(Connection conn = jdbcUtils.getConn()){
            conn.setAutoCommit(false);   
            if (xe.getMaChuyen() != 0) {
                PreparedStatement stm = conn.prepareStatement("INSERT INTO xe(MaChuyen,Trangthai,Bienso,TenXe,MaXE) VALUES (?,?,?,?,?)");
                stm.setInt(1,xe.getMaChuyen());
                stm.setInt(2, xe.getTrangthai());
                stm.setString(3, xe.getBienso());
                stm.setString(4, xe.getTenXe());
                stm.setInt(5, xe.getMaXE());
                stm.executeUpdate();
            } else {
                PreparedStatement stm = conn.prepareStatement("INSERT INTO xe(MaChuyen,Trangthai,Bienso,TenXe,MaXE) VALUES (null,?,?,?,?)");
                stm.setInt(1, xe.getTrangthai());
                stm.setString(2, xe.getBienso());
                stm.setString(3, xe.getTenXe());
                stm.setInt(4, xe.getMaXE());
                stm.executeUpdate();
            }
            conn.commit();
        }
    }
    
    //SUA XE
    /**
     *
     * @param xe
     * @throws java.sql.SQLException
     */
    public void suaXe(xe xe) throws SQLException {
        try(Connection conn = jdbcUtils.getConn()){
            conn.setAutoCommit(false);
            PreparedStatement stm = conn.prepareStatement("UPDATE xe SET MaChuyen=?, Trangthai=?, Bienso=?, TenXe=? WHERE MaXE = " + xe.getMaXE());
            stm.setInt(1, xe.getMaChuyen());
            stm.setInt(2, xe.getTrangthai());
            stm.setString(3, xe.getBienso());
            stm.setString(4, xe.getTenXe());
            stm.executeUpdate();
            conn.commit();
        }
    }
    //XOA XE - CHUA HOAN THANH DIEU KIEN (CHECK LAI SV CHUYEN DI)
    public void xoaXe(xe xe) throws SQLException {
        try(Connection conn = jdbcUtils.getConn()){
            conn.setAutoCommit(false);
            PreparedStatement stm = conn.prepareStatement("DELETE FROM xe WHERE MaXE = " + xe.getMaXE());
            stm.executeUpdate();
            conn.commit();
        }
    }
    
}
