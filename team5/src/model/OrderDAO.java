package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.DataSource;

public class OrderDAO implements IOrderDAO {

	public ArrayList<OrderVO> getOrderList(){
		ArrayList<OrderVO> orderList = new ArrayList<OrderVO>();
		
		String sql = 
		 "SELECT o.order_id, u.user_id, p.product_id, c.cart_id"
		 + "FROM orders o, users u, product p, cart c"
		 + "WHERE o.user_id = u.user_id AND p.product_id = o.product_id AND o.cart_id = c.cart_id";
		Connection con = null;
		try {
			con = DataSource.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				OrderVO od = new OrderVO();
				od.setOrderId(rs.getInt("order_id"));
				od.setUserId(rs.getString("user_id"));
				od.setProductId(rs.getInt("product_id"));
				od.setCartId(rs.getString("cart_id"));	
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DataSource.closeConnection(con);
		}
		return orderList;
	}
}
