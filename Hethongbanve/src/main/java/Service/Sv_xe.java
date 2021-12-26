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
import pojo.chuyendi;
import pojo.xe;
import pojo.xe_ghe;

/**
 *
 * @author Admin
 */
public class Sv_xe {
    
    public List<xe> getXe() throws SQLException{
        List<xe> dsxe = new ArrayList<>();
        Connection conn = jdbcUtils.getConn();
        
        Statement stm = conn.createStatement();
        String sql = "Select * from xe";
        ResultSet rs = stm.executeQuery(sql);
        
        while(rs.next()){
            xe xe = new xe(rs.getInt("MaXE"), rs.getString("TenXe"), rs.getString("Bienso"), rs.getInt("Trangthai"),rs.getInt("MaChuyen"));
            dsxe.add(xe);
        }
        return dsxe;
    }
    
    public List<xe_ghe> getGhe(int maXE) throws SQLException{
        List<xe_ghe> lstG = new ArrayList<>();
        Connection conn = jdbcUtils.getConn();
        
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("Select * from MaXE = " + maXE);
        
        while(rs.next()){
            xe_ghe xG = new xe_ghe(rs.getInt("MaXE"), rs.getInt("g1"), rs.getInt("g2"), rs.getInt("g3"), rs.getInt("g4"), rs.getInt("g5"), rs.getInt("g6"), rs.getInt("g7"), rs.getInt("g8"), rs.getInt("g9"), rs.getInt("g10"), rs.getInt("g11"), rs.getInt("g12"), rs.getInt("g13"), rs.getInt("g14"), rs.getInt("g15"), rs.getInt("g16"));
            lstG.add(xG);
        }
        return lstG;
    }
    
    public List<String> getGheTrong(int maXE) throws SQLException{
        List<String> lstGT = new ArrayList<>();
        Connection conn = jdbcUtils.getConn();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("Select * from xe_ghe where MaXE = " + maXE);
        while(rs.next()){
            for(int i = 1; i <= 16; i++){
                int xGT = rs.getInt("g" + i);
                if(xGT == 0)
                    lstGT.add("g"+i);
                else
                    lstGT.add("g"+i+" (ĐÃ ĐƯỢC ĐẶT)");
            }
        }
        return lstGT;
    }
    
    public xe getMaToXE(int maXE,int maCD) throws SQLException{
        Connection conn = jdbcUtils.getConn();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("Select * from xe where MaXE = " + maXE + " and MaChuyen = " + maCD);
        xe xe = new xe();
        while(rs.next()){
           xe = new xe(rs.getInt("MaXE"), rs.getString("TenXe"), rs.getString("Bienso"), rs.getInt("Trangthai"),rs.getInt("MaChuyen"));
        }
        return xe;
    }
    
    public List<xe> getXeFromMaCD(int maCD) throws SQLException{
        List<xe> dsxe = new ArrayList<>();
        Connection conn = jdbcUtils.getConn();
        
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("Select * from xe where MaChuyen = " + maCD);
        
        while(rs.next()){
            xe xe = new xe(rs.getInt("MaXE"), rs.getString("TenXe"), rs.getString("Bienso"), rs.getInt("Trangthai"),rs.getInt("MaChuyen"));
            dsxe.add(xe);
        }
        return dsxe;
    }
}
