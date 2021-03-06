package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import model.vo.CustomerVo;

public class CustomerDao {
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

	public static void insert(CustomerVo cvo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "INSERT INTO customer VALUES(?,?,?,?)";
		try {
			con = source.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, cvo.getId());
			pstmt.setString(2, cvo.getPassword());
			pstmt.setString(3, cvo.getName());
			pstmt.setString(4, cvo.getEmail());
			pstmt.executeUpdate();
		} catch (SQLException s) {
			s.printStackTrace();
			throw s;
		} finally {
			close(con, pstmt);
		}
	}

	/**
     * ?Ή?  κ³ κ°? Database customr table?? ?­? ??€.<br>
     * ?ΈμΆ? ?? κ³³μΌλ‘? λΆ??° ?­? ?  κ²μλ¬Όμ id (PK)λ₯? λ°μ Database?? ?­? ??€.
     * 
     * Query : delete
     * 
     * 1. Connection ??±
     * 2. PreparedStatement ??±
     * 3. μΏΌλ¦¬ ? ?‘
     * 4. close()
     * @param id
     * @throws SQLException
     */
    public static void delete(String id) throws SQLException{
    	Connection con = null;
		PreparedStatement pstmt = null;
		String query = "delete from customer where id=?";
		try {
			con = source.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1,id);
			pstmt.executeUpdate();
		} catch (SQLException s) {
			s.printStackTrace();
			throw s;
		} finally {
			close(con, pstmt);
		}
	}

	/**
	 * ?Ή?  κ³ κ°? password?? email ? λ³΄λ?? Database customr table?? κ°±μ ??€.<br>
	 * 
	 * Query : update
	 * 
	 * 1. Connection ??± 2. PreparedStatement ??± 3. μΏΌλ¦¬ ? ?‘ 4. close()
	 * 
	 * @param id
	 * @throws SQLException
	 */
	public static void update(CustomerVo cvo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "UPDATE customer SET password = ? , email = ? WHERE id = ?";
		try {
			con = source.getConnection();

			pstmt = con.prepareStatement(query);
			pstmt.setString(1, cvo.getPassword());
			pstmt.setString(2, cvo.getEmail());
			pstmt.setString(3, cvo.getId());

			pstmt.executeQuery();

		} catch (SQLException s) {
			s.printStackTrace();
			throw s;
		} finally {
			close(con, pstmt);
		}
	}

	/**
	 * λͺ¨λ  κ³ κ°? ? λ³΄λ?? Database customr table?? κ²????€.<br>
	 * 
	 * Query : select
	 * 
	 * 1. Connection ??± 2. PreparedStatement ??± 3. μΏΌλ¦¬ ? ?‘ 4. close()
	 * 
	 * @param id
	 * @throws SQLException
	 */
	public static ArrayList getCustomers() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList allList = new ArrayList();
		String sql = "select * from books where SUBTITLE is null";
		try {
			conn = source.getConnection();
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				allList.add(new CustomerVo(rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4)));
			}
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