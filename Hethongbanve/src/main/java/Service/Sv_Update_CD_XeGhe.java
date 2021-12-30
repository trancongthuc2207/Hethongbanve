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
import pojo.vexe;
import pojo.xe_ghe;

/**
 *
 * @author Admin
 */
public class Sv_Update_CD_XeGhe {
    
    public void UpdateGheForXe(vexe vx) throws SQLException{
            try(Connection conn = jdbcUtils.getConn()){
                conn.setAutoCommit(false);
                PreparedStatement stm = conn.prepareStatement("UPDATE xe_ghe Set "+ vx.getSoghe() +" = 1 where MaXE = ?");

                stm.setInt(1, vx.getMaXE());

                stm.executeUpdate();
                conn.commit();
                conn.close();
            }
    }
    //// RESET GHẾ ĐÃ ĐẶT THÀNH GHẾ TRỐNG KHI VÉ BỊ HUỶ
    public void UpdateGheForXeKhiHuyVe(vexe vx) throws SQLException{
            try(Connection conn = jdbcUtils.getConn()){
                conn.setAutoCommit(false);
                PreparedStatement stm = conn.prepareStatement("UPDATE xe_ghe Set "+ vx.getSoghe() +" = 0 where MaXE = ?");

                stm.setInt(1, vx.getMaXE());

                stm.executeUpdate();
                conn.commit();
                conn.close();
            }
    }
    
    
}
