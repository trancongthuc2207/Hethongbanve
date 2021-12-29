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
import pojo.vexe;

/**
 *
 * @author Admin
 */
public class Sv_Update_TrangThaiVe {
    public void UpdateTrangThaiVeToNull(vexe vx) throws SQLException{
            try(Connection conn = jdbcUtils.getConn()){
                conn.setAutoCommit(false);
                PreparedStatement stm = conn.prepareStatement("UPDATE vexe Set Trangthai = null where MaVE = ?");
                stm.setInt(1, vx.getMaVE());
                stm.executeUpdate();
                conn.commit();
            }
    }
}
