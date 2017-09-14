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
     * ?��?�� 고객?�� Database customr table?��?�� ?��?��?��?��.<br>
     * ?���? ?��?�� 곳으�? �??�� ?��?��?�� 게시물의 id (PK)�? 받아 Database?��?�� ?��?��?��?��.
     * 
     * Query : delete
     * 
     * 1. Connection ?��?��
     * 2. PreparedStatement ?��?��
     * 3. 쿼리 ?��?��
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
	 * ?��?�� 고객?�� password?? email ?��보�?? Database customr table?��?�� 갱신?��?��.<br>
	 * 
	 * Query : update
	 * 
	 * 1. Connection ?��?�� 2. PreparedStatement ?��?�� 3. 쿼리 ?��?�� 4. close()
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
	 * 모든 고객?�� ?��보�?? Database customr table?��?�� �??��?��?��.<br>
	 * 
	 * Query : select
	 * 
	 * 1. Connection ?��?�� 2. PreparedStatement ?��?�� 3. 쿼리 ?��?�� 4. close()
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