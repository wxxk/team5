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
			"SELECT o.order_id, u.user_name, u.user_phone_number, u.user_address,"
			+ "p.product_img, p.product_name, pd.options, c.cart_cnt, p.product_price"	
			 + "FROM users u "
			 + "JOIN cart c ON u.user_id = c.user_id"
			 + "JOIN orders o ON c.product_id = o.product_id"
			 + "JOIN product p ON o.product_id = p.product_id"
			 + "JOIN product_detail pd ON p.product_id = pd.product_id";
		
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
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DataSource.closeConnection(con);
		}
		return orderList;
	}
}
