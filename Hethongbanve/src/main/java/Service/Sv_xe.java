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
            xe xe = new xe(rs.getInt("MaXE"), rs.getString("TenXe"), rs.getString("Bienso"), rs.getInt("Trangthai"));
            dsxe.add(xe);
        }
        return dsxe;
    }
}
