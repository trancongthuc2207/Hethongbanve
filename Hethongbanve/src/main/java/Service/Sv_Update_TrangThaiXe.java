/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import config.jdbcUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import pojo.xe;


/**
 *
 * @author Admin
 */
public class Sv_Update_TrangThaiXe {
    public void UpdateTrangThaiXeToDiChuyen(xe xe) throws SQLException{
            try(Connection conn = jdbcUtils.getConn()){
                conn.setAutoCommit(false);
                PreparedStatement stm = conn.prepareStatement("UPDATE xe Set Trangthai = 1 where MaXE = ?");
                stm.setInt(1, xe.getMaXE());
                stm.executeUpdate();
                conn.commit();
                conn.close();
            }
    }
}
