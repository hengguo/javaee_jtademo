package test.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;


public class IndexTest {
    
    
    public static void mysqlTest(){
        String url = "jdbc:mysql://10.128.11.129:3306/gmhx600";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, "root", "mysql"); 
            conn.setAutoCommit(false);
            Date start = new Date();
            //---------------------------------------------------
            String sql = "SELECT * FROM hx_user_unload AS U , hx_organization_unload AS O "+
            			"where O.id LIKE '1sdf%' LIMIT 1000000";
            stmt = conn.prepareStatement(sql);
            stmt.executeQuery();
            conn.commit();
            //---------------------------------------------------
            System.err.println(new Date().getTime() - start.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	try {
        		stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
    }
    
    public static void testOracle(){
        String url = "jdbc:oracle:thin:@10.128.11.92:1521:testdb52";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(url, "gmscm05", "jl500pwd"); 
            conn.setAutoCommit(false);
            Date start = new Date();
            //---------------------------------------------------
            String sql = "select sysdate from dual";
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            conn.commit();
            while (rs.next()) {
                System.out.println(rs.getString(1));
                
            }
            //---------------------------------------------------
            System.err.println(new Date().getTime() - start.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) {
        testOracle();
    }


}
