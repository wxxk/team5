package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.DataSource;

public class OrderDAO implements IOrderDAO {

	@Override
	public ArrayList<OrderVO> getAllOrderList(){
		ArrayList<OrderVO> orderList = new ArrayList<OrderVO>();

		String sql = 
			"SELECT o.order_id, u.user_name, u.user_phone_number, u.user_address,"
			+ "p.product_img, p.product_name, pd.options, c.cart_cnt, p.product_price, c.ordered "	
			 + "FROM users u "
			 + "JOIN cart c ON u.user_id = c.user_id "
			 + "JOIN orders o ON c.product_id = o.product_id "
			 + "JOIN product p ON o.product_id = p.product_id "
			 + "JOIN product_detail pd ON p.product_id = pd.product_id ";

		Connection con = null;
		try {
			con = DataSource.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				OrderVO od = new OrderVO();
				od.setOrderId(rs.getInt("order_id"));
				od.setUserName(rs.getString("user_name"));
				od.setUserPhoneNumber(rs.getString("user_phone_number"));
				od.setUserAddress(rs.getString("user_address"));
				od.setProductImg(rs.getString("product_img"));
				od.setProductName(rs.getString("product_name"));
				od.setOptions(rs.getString("product_name"));
				od.setCartCnt(rs.getInt("cart_cnt"));	
				od.setProductPrice(rs.getInt("product_price"));
				orderList.add(od);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DataSource.closeConnection(con);
		}
		return orderList;
	}



	@Override
	public ArrayList<OrderVO> getOrderList(String userId){
		ArrayList<OrderVO> orderList = new ArrayList<OrderVO>();

		String sql = 
				"SELECT o.order_id,p.product_name, c.cart_cnt, p.product_price, u.user_name, u.user_address, u.user_phone_number "
						+"FROM users u "
						+"JOIN cart c ON u.user_id = c.user_id "
						+"JOIN orders o ON c.product_id = o.product_id "
						+"JOIN product p ON o.product_id = p.product_id "
						+"WHERE u.user_id = ?";
		Connection con = null;
		try {
			con = DataSource.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, userId);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				OrderVO od = new OrderVO();
				od.setOrderId(rs.getInt("order_id"));
				od.setProductName(rs.getString("product_name"));
				od.setCartCnt(rs.getInt("cart_cnt"));	
				od.setProductPrice(rs.getInt("product_price"));
				od.setUserName(rs.getString("user_name"));
				od.setUserAddress(rs.getString("user_address"));
				od.setUserPhoneNumber(rs.getString("user_phone_number"));
				orderList.add(od);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DataSource.closeConnection(con);
		}
		return orderList;
	}



	@Override
	public int insertOrder(OrderVO vo) {
		int count = 0;
		String sql = "INSERT INTO orders (order_id, user_id, product_id, cart_id)"+
				" VALUES (orders_seq.NEXTVAL, ?, ?, ?)";
		Connection con = null;
		try {
			con = DataSource.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, vo.getUserId());
			stmt.setInt(2, vo.getProductId());
			stmt.setInt(3, vo.getCartId());
			count = stmt.executeUpdate();
		} catch(Exception e) {
			throw new RuntimeException(e);
		} finally {
			DataSource.closeConnection(con);
		}
		return count;
	}

	@Override
	public int updateOrder(OrderVO vo) {
		int count = 0;
		String sql = "UPDATE orders SET user_address = ? AND options = ? WHERE order_id = ?";
		Connection con = null;
		try {
			con = DataSource.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, vo.getUserAddress());
			stmt.setString(2, vo.getOptions());
			stmt.setInt(3, vo.getOrderId());
			count = stmt.executeUpdate();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DataSource.closeConnection(con);
		}
		return count;
	}

	@Override
	public int deleteOrder(OrderVO vo) {
		int deleteRow = 0;
		String sql = "DELETE FROM orders WHERE order_id = ?";
		Connection con = null;
		try {
			con=DataSource.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, vo.getOrderId());
			deleteRow = stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			DataSource.closeConnection(con);
		}
		return deleteRow;
	}





}