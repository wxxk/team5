package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.DataSource;

public class CartDAO implements ICartDAO{
	//카트 전체 조회
	public ArrayList<CartVO> getAllCart(String userId){
		ArrayList<CartVO> cartList = new ArrayList<CartVO>();
		String sql = "SELECT c.cart_id, p.product_id, p.product_name, ct.category_name, c.options, c.cart_cnt, p.product_price "+		
				"FROM users u, cart c, product p, category ct "+
				"WHERE u.user_id = c.user_id "+
				"AND c.product_id = p.product_id "+
				"AND p.category_id = ct.category_id "+
				"AND u.user_id = ? "+
				"ORDER BY cart_id";

		Connection con = null;
		try {
			con = DataSource.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString (1, userId );
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				CartVO ct = new CartVO();
				ct.setCartId(rs.getInt("cart_id"));
				ct.setProductId(rs.getInt("product_id"));
				ct.setProductName(rs.getString("product_name"));
				ct.setCategoryName(rs.getString("category_name"));
				ct.setOptions(rs.getString("options"));
				ct.setCartCnt(rs.getInt("cart_cnt"));
				ct.setProductPrice(rs.getInt("product_price"));
				cartList.add(ct);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DataSource.closeConnection(con);
		}
		return cartList;
	}
	
	
	//cart_id별로 조회
	public ArrayList<CartVO> getCart(String cartId){
		ArrayList<CartVO> cartList = new ArrayList<CartVO>();
		String sql = "SELECT c.cart_id, p.product_img, p.product_name, ct.category_name,"
				+ "pd.options, c.cart_cnt, p.product_price "
				+ "FROM users u, cart c, product p, category ct, product_detail pd "
				+ "WHERE u.user_id = c.user_id"
				+ "AND c.product_id = p.product_id"
				+ "AND p.category_id = ct.category_id"
				+ "AND pd.product_id = p.product_id"
				+ "AND cart_id = ?" ;

		Connection con = null;
		try {
			con = DataSource.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString (1, cartId);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				CartVO ct = new CartVO();
				ct.setCartId(rs.getInt("cart_id"));
				ct.setProductImg(rs.getString("product_img"));
				ct.setProductName(rs.getString("product_name"));
				ct.setCategoryName(rs.getString("category_name"));
				ct.setOptions(rs.getString("options"));
				ct.setCartCnt(rs.getInt("cart_cnt"));
				ct.setProductPrice(rs.getInt("product_price"));
				cartList.add(ct);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DataSource.closeConnection(con);
		}
		return cartList;
		
	}

	//카트에 담긴 상품 전체삭제
	public int allDeleteCart(CartVO vo) {
		int deleteRow = 0;
		String sql = "DELETE FROM cart WHERE cart_id = ?";
		Connection con = null;
		try {
			con = DataSource.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, vo.getCartId());
			deleteRow = stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			DataSource.closeConnection(con);
		}
		return deleteRow;
	}

	//카트에 담긴 상품 부분삭제
	public int deleteCart(CartVO vo) {
		int deleteRow = 0;
		String sql = "DELETE FROM cart WHERE product_id = ?";
		Connection con = null;
		try {
			con = DataSource.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, vo.getProductId());
			deleteRow = stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			DataSource.closeConnection(con);
		}
		return deleteRow;
	}


	
	//상품 등록
	public int insertCart(CartVO vo) {
		int count = 0;
		String sql = "INSERT INTO cart (cart_id, user_id, product_id, cart_cnt, options) "
		+ "VALUES(cart_seq.NEXTVAL,?,?,?,?)";
		Connection con = null;
		try {
			con = DataSource.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, vo.getUserId());
			stmt.setInt(2, vo.getProductId());
			stmt.setInt(3, vo.getCartCnt());
			stmt.setString(4, vo.getOptions());
			count = stmt.executeUpdate();
		} catch(Exception e) {
			throw new RuntimeException(e);
		} finally {
			DataSource.closeConnection(con);
		}
		return count;
	}
		
	//장바구니 수량 변경
	public int updateCart(CartVO vo) {
		int count = 0;
		String sql = "UPDATE cart SET cart_cnt = ? WHERE cart_id = ?";
		Connection con = null;
		try {
			con = DataSource.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, vo.getCartCnt());
			stmt.setInt(2, vo.getCartId());
			count = stmt.executeUpdate();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DataSource.closeConnection(con);
		}
		return count;
	}
		

	
	
	
		
	}
	
	
	
	
	
	





