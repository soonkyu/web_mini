package util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;//cp��� Ȱ��

/**
 * driver �ε� Connection ��ü ��ȯ -CP����� �ݿ� �ڿ� ��ȯ ����ȭ �ֻ��� ��� �����
 */
public class DBUtil {
	static DataSource ds;      // DataSource�� �ֱ���â ��������� �ȴ�. 

	static {
		Context initContext;
		try {
			initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/myoracle");
			// Connection conn = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}
	//select
	public static void close(Connection con,Statement stmt,ResultSet rset){ //if���� �����ð��� ���!!
		if(rset!=null){
			try {
				rset.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(stmt!=null){
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}//method of close

	//insert // update //delete
	public static void close(Connection con,Statement stmt){
		if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(stmt!=null){
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	

}//end of class
