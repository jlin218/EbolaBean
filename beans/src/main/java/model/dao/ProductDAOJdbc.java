package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.ProductBean;
import model.ProductDAO;

public class ProductDAOJdbc implements ProductDAO {
//	private static final String URL = "jdbc:sqlserver://localhost:1433;database=java";
//	private static final String USERNAME = "sa";
//	private static final String PASSWORD = "passw0rd";
	private DataSource dataSource;
	
	
	public ProductDAOJdbc(){
		try {
			Context cxt = new InitialContext();
			
			dataSource = (DataSource)cxt.lookup("java:comp/env/jdbc/xxx");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static final String SELECT_BY_ID = "select * from product where id=?";
	@Override
	public ProductBean select(int id) {
		Connection conn = null;
		
		ProductBean result = null;
		
		try {
//			conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(SELECT_BY_ID);
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				result = new ProductBean();
				result.setId(rs.getInt("id"));
				result.setName(rs.getString("name"));
				result.setPrice(rs.getDouble("price"));
				result.setMake(rs.getDate("make"));
				result.setExpire(rs.getInt("expire"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	private static final String SELECT_ALL = "select * from product";
	/* (non-Javadoc)
	 * @see modeldao.ProductDAO#select()
	 */
	@Override
	public List<ProductBean> select() {
		List<ProductBean> result = null;
		Connection conn = null;
		try {
//			conn= DriverManager.getConnection(URL,USERNAME,PASSWORD);
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(SELECT_ALL);
			ResultSet rs = ps.executeQuery();
			result = new ArrayList<ProductBean>();
			while(rs.next()){
				ProductBean pd = new ProductBean();
				pd.setId(rs.getInt("id"));
				pd.setName(rs.getString("name"));
				pd.setPrice(rs.getDouble("price"));
				pd.setMake(rs.getDate("make"));
				pd.setExpire(rs.getInt("expire"));
				result.add(pd);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return result;
	}
	private static final String UPDATE = "update product set name=?, price=?, make=?, expire=? where id=?";
	/* (non-Javadoc)
	 * @see modeldao.ProductDAO#update(java.lang.String, double, java.util.Date, int, int)
	 */
	@Override
	public ProductBean update(String name, double price, java.util.Date make, int expire, int id) {
		ProductBean result = null;
		Connection conn = null;
	
		try {
		//	conn=DriverManager.getConnection(URL,USERNAME,PASSWORD);
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(UPDATE);
			result = new ProductBean();
			ps.setString(1,name);
			ps.setDouble(2,price);
			ps.setDate(3,new java.sql.Date(make.getTime()));
			ps.setInt(4,expire);
			ps.setInt(5,id);
			result.setId(id);
			result.setExpire(expire);
			result.setMake(make);
			result.setName(name);
			result.setPrice(price);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return result;
	}
	private static final String INSERT = "insert into product (id, name, price, make, expire) values (?, ?, ?, ?, ?)";
	/* (non-Javadoc)
	 * @see modeldao.ProductDAO#insert(model.ProductBean)
	 */
	@Override
	public ProductBean insert(ProductBean bean) {
		ProductBean result = null;
		Connection conn = null;
		
		try {
			//conn=DriverManager.getConnection(URL,USERNAME,PASSWORD);
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(INSERT);
			result = new ProductBean();
			ps.setInt(1,bean.getId());
			ps.setString(2,bean.getName());
			ps.setDouble(3,bean.getPrice());
			ps.setDate(4,new java.sql.Date(bean.getMake().getTime()));
			ps.setInt(5,bean.getExpire());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return bean;
	}
	private static final String DELETE = "delete from product where id=?";
	/* (non-Javadoc)
	 * @see modeldao.ProductDAO#delete(int)
	 */
	@Override
	public boolean delete(int id) {
		Connection conn=null;
		int n = 0;
		
		try {
		//	conn= DriverManager.getConnection(URL, USERNAME,PASSWORD);
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(DELETE);
			ps.setInt(1, id);
			n = ps.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return n==1;
	}	
}
