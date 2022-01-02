
package xe;

import config.jdbcUtils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Admin
 */
public class xeTester {
    private static Connection conn;
    @BeforeAll
    public static void beforeAll() throws SQLException{
        conn = jdbcUtils.getConn();
    }
    public static void afterAll() throws SQLException{
        if(conn != null)
        {
            conn.close();
        }
    }
    
    @Test
    public void testXe() throws SQLException{
        beforeAll();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("Select * from xe");
        
        List<String> kq = new ArrayList<>();
        while(rs.next()){
            String name = rs.getString("TenXe");
            System.out.println(name);
            kq.add(name);
        }
        Set<String> kq2 = new HashSet<>(kq);
        Assertions.assertEquals(kq.size(), kq2.size());
    }
}
