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
import pojo.vexe;

/**
 *
 * @author Admin
 */
public class Sv_vexe {
    private static int MaVeCurrent = 0;
    
    public static int getMaVeCurrent(){
        return MaVeCurrent;
    }
    
    public List<vexe> getVeXe() throws SQLException{
        List<vexe> dsVe = new ArrayList<>();
        Connection conn = jdbcUtils.getConn();
        
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("Select * from vexe");
        
        while(rs.next()){
            vexe ve = new vexe(rs.getInt("MaVE"), rs.getTimestamp("Thoigianbatdau"),rs.getString("Soghe"), rs.getInt("MaChuyen"), rs.getInt("MaKH"), rs.getInt("MaNV"), rs.getInt("MaXE"), rs.getDate("Ngayin"));
            dsVe.add(ve);
        }
        this.MaVeCurrent = dsVe.size();
        return dsVe;
    } 
}
