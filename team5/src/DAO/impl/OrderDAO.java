package DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.IOrderDAO;
import main.DataSource;
import model.CartVO;
import model.OrderDetailVO;
import model.OrderVO;
import model.ProductDetailVO;
import model.ProductVO;

public class OrderDAO implements IOrderDAO {

	@Override
	public ArrayList<OrderVO> getAllOrderList(){
		ArrayList<OrderVO> orderList = new ArrayList<OrderVO>();
		//total_price넣어야 함
		String sql = 
				"SELECT o.order_id, u.user_name, u.user_phone_number, u.user_address,"
						+ "p.product_img, p.product_name, pd.options, c.cart_cnt, p.product_price "	
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
				//				od.setCartCnt(rs.getInt("cart_cnt"));	
				od.setProductPrice(rs.getInt("product_price"));
				od.setTotalPrice(rs.getInt("total_Price"));
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
				"SELECT o.order_id, p.product_name, p.product_price, u.user_name, u.user_address, u.user_phone_number, o.order_total_price, od.options "
				+ "FROM users u "
				+ "JOIN orders o ON u.user_id = o.user_id "
				+ "JOIN product p ON o.product_id = p.product_id "
				+ "JOIN order_details od on o.order_id = od.order_id "
				+ "WHERE u.user_id = ?";

		Connection con = null;
		try {
			con = DataSource.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, userId);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				OrderVO od = new OrderVO();
				od.setUserId(userId);
				od.setOrderId(rs.getInt("order_id"));
				od.setProductName(rs.getString("product_name"));
				//				od.setCartCnt(rs.getInt("cart_cnt"));	
				od.setProductPrice(rs.getInt("product_price"));
				od.setUserName(rs.getString("user_name"));
				od.setUserAddress(rs.getString("user_address"));
				od.setUserPhoneNumber(rs.getString("user_phone_number"));
				od.setTotalPrice(rs.getInt("order_total_Price"));
				od.setOptions(rs.getString("options"));
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

//	   @Override
//	   public int insertCartOrder(String userId, int productId, int cartId, List<CartVO> cl, String options); {
//		   int count = 0;
//		   int totalprice = 0;
//		   
//		   ProductDAO pDAO = new ProductDAO();
//		   OrderDetailDAO odDAO = new OrderDetailDAO();
//		   List<OrderDetailVO> oVoList= new ArrayList<OrderDetailVO>();
//		   ProductDetailDAO pdDAO = new ProductDetailDAO();
//		   ProductDetailVO pdVO = new ProductDetailVO();
//		   CartDAO cDAO = new CartDAO();
//		   
//		   int orderPk = 0;
//		   
//		   ResultSet rs = null;
//		   
//		   PreparedStatement stmt = null;
//		   PreparedStatement stmt2 = null;
//		   
//		   String sql = "INSERT INTO orders (order_id, user_id, product_id, cart_id,order_total_price )"+
//				   " VALUES (?,?,?,?,?)";
//		   String sql2="SELECT orders_seq.NEXTVAL AS oseq FROM dual";
//		   
//		   Connection con = null;
//		   
//		   try {
//			   con = DataSource.getConnection();
//			   
//			   stmt2 = con.prepareStatement(sql2);
//			   rs = stmt2.executeQuery();
//			   
//			   if(rs.next()) {
//				   orderPk=rs.getInt("oseq");      
//			   }
//		   
//			   for(CartVO cart : cl) {
//				   totalprice = cart.getCartCnt()*cart.getProductPrice();
//			   }
//			  
//			   stmt = con.prepareStatement(sql);
//			   stmt.setInt(1, orderPk);
//			   stmt.setString(2, userId);
//			   stmt.setInt(3, productId);
//			   stmt.setInt(4, cartId);
//			   stmt.setInt(5, totalprice);
//			   count = stmt.executeUpdate(); 
//			   OrderDetailVO odVO=null;
//			   
//			   for(CartVO cart : cl) {
//				   odVO = new OrderDetailVO();
//				   odVO.setProductId(productId);
//				   odVO.setProductCnt(cart.getCartCnt());
//				   odVO.setOrderId(orderPk);
//				   odVO.setOptions(cart.getOptions());
//				   oVoList.add(odVO);
//				   odDAO.insertOrderDetail(odVO);
////				   pdDAO.updateStock(productId, pdVO.getCnt()-odVO.getProductCnt());
//				   cDAO.deleteCart(cart.getCartId());
//			   }
//			   
//			   
//		   } catch(Exception e) {
//			   throw new RuntimeException(e);
//		   } finally {
//			   DataSource.closeConnection(con);
//		   }
//		   return count;
//	   }

	@Override
	public int insertAllCartOrder(OrderVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	@Override
	public int insertCartOrder(String userId, int productId, int cartId , List<CartVO> cl,String options) {
		int count = 0;
		int totalprice = 0;

		ProductDAO pDAO = new ProductDAO();
		
		CartVO cartVO = new CartVO();
		CartDAO cartDAO = new CartDAO();

		OrderDetailDAO odDAO = new OrderDetailDAO();

		List<OrderDetailVO> oVoList= new ArrayList<OrderDetailVO>();

		ProductDetailDAO pdDAO = new ProductDetailDAO();

		ProductDetailVO pdVO = new ProductDetailVO();
		pdVO = pdDAO.getProductD(productId, options);

		int orderPk = 0;

		ResultSet rs = null;

		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;

		String sql = "INSERT INTO orders (order_id, user_id, product_id, cart_id, order_total_price )"+
				" VALUES (?,?,?,?,?)";
		String sql2="SELECT orders_seq.NEXTVAL AS oseq FROM dual";

		Connection con = null;
		System.out.println("test2");
		try {
			con = DataSource.getConnection();

			pdVO = pdDAO.getProductD(productId,options);

			stmt2 = con.prepareStatement(sql2);
			rs = stmt2.executeQuery();

			if(rs.next()) {
				orderPk=rs.getInt("oseq");      
			}

			for(CartVO cart : cl) {
				totalprice = cart.getCartCnt()*cart.getProductPrice();
			}

			stmt = con.prepareStatement(sql);
			stmt.setInt(1, orderPk);
			stmt.setString(2, userId);
			stmt.setInt(3, productId);
			stmt.setInt(4, cartId);
			stmt.setInt(5, totalprice);
			count = stmt.executeUpdate(); 
			for(CartVO cart : cl) {
				OrderDetailVO odVO = new OrderDetailVO();

				odVO.setProductId(productId);
				odVO.setProductCnt(cart.getCartCnt());
				odVO.setOrderId(orderPk);
				odVO.setOptions(cart.getOptions());

				oVoList.add(odVO);

				odDAO.insertOrderDetail(odVO);
				
				pdDAO.updateStock(productId , pdVO.getCnt()-cart.getCartCnt());
			}


		} catch(Exception e) {
			throw new RuntimeException(e);
		} finally {
			DataSource.closeConnection(con);
		}
		return count;
	}



	@Override
	public int insertProductOrder(OrderVO vo, int cnt, String orderDetailOptions) {
		int count = 0;
		int orderPk = 0;
		OrderDetailDAO odDAO = new OrderDetailDAO();
		String sql = "INSERT INTO orders (order_id, user_id, product_id, order_total_price)"
				+ " VALUES (?, ?, ?, ?)";

		String sql2="SELECT orders_seq.NEXTVAL AS oseq FROM dual";
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DataSource.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			PreparedStatement stmt2 = con.prepareStatement(sql2);

			stmt2 = con.prepareStatement(sql2);
			rs = stmt2.executeQuery();

			if(rs.next()) {
				orderPk = rs.getInt("oseq");      
			}

			stmt.setInt(1,  orderPk);
			stmt.setString(2, vo.getUserId());
			stmt.setInt(3, vo.getProductId());
			stmt.setInt(4, vo.getTotalPrice());
			count = stmt.executeUpdate();

			OrderDetailVO odVO=null;

			odVO = new OrderDetailVO();
			odVO.setProductId(vo.getProductId());
			odVO.setProductCnt(cnt);
			odVO.setOrderId(orderPk);
			odVO.setOptions(orderDetailOptions);
			odDAO.insertOrderDetail(odVO);
		} catch(Exception e) {
			throw new RuntimeException(e);
		} finally {
			DataSource.closeConnection(con);
		}
		return count;
	}








}