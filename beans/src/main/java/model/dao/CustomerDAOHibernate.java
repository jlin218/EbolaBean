package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.CustomerBean;
import model.CustomerDAO;

public class CustomerDAOHibernate implements CustomerDAO {
	public static final String URL="jdbc:sqlserver://localhost:1433;databaseName=java";
	public static final String PASSWORD="passw0rd";
	public static final String USER="sa";
	
	public static final String SELECT_BY_ID="select *from customer where custid=?";
	public static final String UPDATE="update customer set password=?, email=?, birth=? where custid=?";

	public static void main(String[]args){
		CustomerDAO dao = new CustomerDAOHibernate();
		CustomerBean person = dao.select("Alex");
		System.out.println(person.toString());
	}
	/* (non-Javadoc)
	 * @see modeldao.CustomerDAO#select(java.lang.String)
	 */
	@Override
	public CustomerBean select(String id){
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		CustomerBean cb = new CustomerBean();
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(SELECT_BY_ID);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				cb.setCustid(rs.getString("custid"));
				cb.setPassword(rs.getBytes("password"));
				cb.setEmail(rs.getString("email"));
				cb.setBirth(rs.getDate("birth"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (conn != null){
				try {
					conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null){
				try {
					pstmt.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if (rs != null){
				try {
					rs.close();
				} catch(SQLException e) {
					e.printStackTrace();
			
				}
			}	
		}
		return cb;
	}
	/* (non-Javadoc)
	 * @see modeldao.CustomerDAO#update(byte[], java.lang.String, java.util.Date, java.lang.String)
	 */
	@Override
	public boolean update(byte[] password,String email, java.util.Date birth,String custid){
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(UPDATE);
			pstmt.setBytes(1, password);
			pstmt.setString(2, email);
			pstmt.setDate(3, new java.sql.Date(birth.getTime()));
			pstmt.setString(4, custid);
			int num = pstmt.executeUpdate();
			System.out.println(num);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (conn != null){
				try {
					conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null){
				try {
					pstmt.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}	
		return false;
	}
	
}
