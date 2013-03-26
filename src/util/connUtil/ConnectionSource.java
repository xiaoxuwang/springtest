package util.connUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

public class ConnectionSource {

	/**
	 * @param args
	 */
	private static BasicDataSource datasource=null;
	
	public ConnectionSource(){}
	
	public static void init(){
		if(datasource!=null){
			try {
				datasource.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			datasource=null;
		}
		Properties p=new Properties();
		//Oracle param
		//driverClassName:oracle.jdbc.driver.OracleDriver
		//url:jdbc:oracle:thin:@192.168.0.1:1521:testDB
		//Mysql param
		//driverClassName:com.mysql.jdbc.Driver
		//url:jdbc:mysql://127.0.0.1:3306/test_db
		p.setProperty("driverClassName", "com.mysql.jdbc.Driver");
        p.setProperty("url", "jdbc:mysql://127.0.0.1:3306/springtest?characterEncoding=utf-8");
        p.setProperty("password", "root");
        p.setProperty("username", "root");
        p.setProperty("maxActive", "30");
        p.setProperty("maxIdle", "10");
        p.setProperty("maxWait", "1000");
        p.setProperty("removeAbandoned", "false");
        p.setProperty("removeAbandonedTimeout", "120");
        p.setProperty("testOnBorrow", "true");
        p.setProperty("logAbandoned", "true");
        
        try {
			datasource=(BasicDataSource)BasicDataSourceFactory.createDataSource(p);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	public static synchronized Connection getConnection() throws  SQLException {
        if (datasource == null) {
            init();
        }
        Connection conn = null;
        if (datasource != null) {
        	
            conn = datasource.getConnection();
        }
        System.out.println("当前数据库连接个数："+datasource.getNumActive());
        return conn;
    }
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		try {
			System.out.println("Creating connection.");
			conn = ConnectionSource.getConnection(); 
			System.out.println("Creating statement.");
			stmt = conn.createStatement();
			System.out.println("Executing statement.");
			rset = stmt.executeQuery("select * from account");
			System.out.println("Results:");
			int numcols = rset.getMetaData().getColumnCount();
			while (rset.next()) {
				for (int i = 1; i <= numcols; i++) {
					System.out.print("\t" + rset.getString(i));
				}
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
			} catch (Exception e) {
			}
			try {
				stmt.close();
			} catch (Exception e) {
			}
			try {
				conn.close();
			} catch (Exception e) {
			}
		}

	}

}
