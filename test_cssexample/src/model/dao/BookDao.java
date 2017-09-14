package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import model.vo.BookVo;

public class BookDao {
	private static DataSource source = null;

	static {
		try {
			Context ctx = new InitialContext();
			source = (DataSource) ctx.lookup("java:comp/env/jdbc/myoracle");
			System.out.println(source);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public static ArrayList getBooks() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList allList = new ArrayList();
		String sql = "select  * from book,IMGSOURCE where book.IMGNO=IMGSOURCE.IMGNO";
		try {
			conn = source.getConnection();
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				allList.add(new BookVo(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getString(4),rset.getString(5),rset.getString(6),rset.getString(7),rset.getInt(8),rset.getString(11)));
			}
			System.out.println(allList);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw sqle;
		} finally {
			close(conn, pstmt, rset);
		}
		return allList;
	}
	private static void close(Connection conn, Statement stmt, ResultSet rset) throws SQLException {
		if (conn != null)
			conn.close();
		if (stmt != null)
			stmt.close();
		if (rset != null)
			rset.close();
	}

	private static void close(Connection conn, Statement stmt) throws SQLException {
		if (conn != null)
			conn.close();
		if (stmt != null)
			stmt.close();
	}
}
