package DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.ICartDAO;
import main.DataSource;
import model.CartVO;

public class CartDAO implements ICartDAO{
	//장바구니 전체 조회
	public ArrayList<CartVO> getAllCart(String userId){
		ArrayList<CartVO> cartList = new ArrayList<CartVO>();
		String sql = "SELECT c.cart_id, p.product_id, p.product_name, "
				+ "ct.category_name, c.options, c.cart_cnt, c.total_price "+		
				"FROM users u, cart c, product p, category ct "+
				"WHERE u.user_id = c.user_id "+
				"AND c.product_id = p.product_id "+
				"AND p.category_id = ct.category_id "+
				"AND u.user_id = ? "+
				"ORDER BY cart_id";
		Connection con = null;
		try {
			con = DataSource.getConnection(); //getConnection()메서드를 호출하여 데이터베이스 연결
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString (1, userId ); //stmt의 첫 번째 매개변수에 userId 값을 설정
			ResultSet rs = stmt.executeQuery(); //stmt의 SQL문을 실행하여 결과를 ResultSet에 저장
			while(rs.next()) {
				CartVO ct = new CartVO(); //CartVO 객체 생성
				ct.setCartId(rs.getInt("cart_id")); //"cart_id" 열의 값을 가져와서 CartVO 객체의 cartId에 설정
				ct.setProductId(rs.getInt("product_id"));
				ct.setProductName(rs.getString("product_name"));
				ct.setCategoryName(rs.getString("category_name"));
				ct.setOptions(rs.getString("options"));
				ct.setCartCnt(rs.getInt("cart_cnt"));
				ct.setTotalPrice(rs.getInt("total_price"));
				cartList.add(ct);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DataSource.closeConnection(con); //데이터베이스 연결 닫기
		}
		return cartList; //cartList를 반환
	}


	//cart_id별로 장바구니 조회
	public ArrayList<CartVO> getCart(int cartId){
		ArrayList<CartVO> cartList = new ArrayList<CartVO>();
		String sql = "SELECT u.user_id, c.cart_id, p.product_img, p.product_name, ct.category_name,"
				+ "pd.options, c.cart_cnt, c.total_price "
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
			stmt.setInt (1, cartId); //stmt의 첫 번째 매개변수에 cartId 값을 설정
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				CartVO ct = new CartVO();
				ct.setUserId(rs.getString("user_id"));
				ct.setCartId(rs.getInt("cart_id"));
				ct.setProductImg(rs.getString("product_img"));
				ct.setProductName(rs.getString("product_name"));
				ct.setCategoryName(rs.getString("category_name"));
				ct.setOptions(rs.getString("options"));
				ct.setCartCnt(rs.getInt("cart_cnt"));
				ct.setTotalPrice(rs.getInt("total_price"));
				cartList.add(ct);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DataSource.closeConnection(con);
		}
		return cartList;
	}

	//장바구니에 담긴 상품 전체삭제
	public int allDeleteCart(CartVO vo) {
		int deleteRow = 0;
		String sql = "DELETE FROM cart WHERE cart_id = ?";
		Connection con = null;
		try {
			con = DataSource.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, vo.getCartId()); //vo객체의 getCartId()의 값인 cartId를 stmt의 첫 번째 매개변수에 설정
			deleteRow = stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			DataSource.closeConnection(con);
		}
		return deleteRow;
	}

	//장바구니에 담긴 상품 부분삭제
	public int deleteCart(int cartId) {
		int deleteRow = 0;
		String sql = "DELETE FROM cart WHERE cart_id = ?";
		Connection con = null;
		try {
			con = DataSource.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, cartId); //stmt의 첫 번째 매개변수에 cartId 값을 설정
			deleteRow = stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			DataSource.closeConnection(con);
		}
		return deleteRow;
	}

	//장바구니에 상품 등록
	public int insertCart(CartVO vo) {
		int count = 0;
		String sql = "INSERT INTO cart (cart_id, user_id, cart_cnt, product_id, options) "
				+ "VALUES(cart_seq.NEXTVAL, ?, ?, ?, ?)";
		Connection con = null;
		try {
			con = DataSource.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, vo.getUserId());//vo객체의 getUserId()의 값인 userId를 stmt의 첫 번째 매개변수에 설정
			stmt.setInt(2, vo.getProductId());
			stmt.setInt(3, vo.getCartCnt());
			stmt.setInt(4, vo.getTotalPrice());
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
	public int updateCart(int cartCnt, int cartId) {
		int count = 0;
		String sql = "UPDATE cart SET cart_cnt = ? WHERE cart_id = ?";
		Connection con = null;
		try {
			con = DataSource.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, cartCnt); //stmt의 첫 번째 매개변수에 cartCnt 값을 설정
			stmt.setInt(2, cartId);  //stmt의 두 번째 매개변수에 cartId 값을 설정
			count = stmt.executeUpdate();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DataSource.closeConnection(con);
		}
		return count;
	} 

	//장바구니 리스트 중에서 하나 선택
	public CartVO getOrderCart(int cartId) {
		CartVO cVO = null;
		String sql = "SELECT c.user_id, c.product_id, c.cart_cnt, c.options, p.product_price  FROM cart c "
				+ "JOIN product p ON p.product_id = c.product_id "
				+ "WHERE cart_id=? ";
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DataSource.getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, cartId); //stmt의 첫 번째 매개변수에 cartId 값을 설정
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				cVO = new CartVO();
				cVO.setCartId(cartId);
				cVO.setUserId(rs.getString("user_id"));
				cVO.setProductId(rs.getInt("product_id"));
				cVO.setCartCnt(rs.getInt("cart_cnt"));
				cVO.setOptions(rs.getString("options"));
				cVO.setTotalPrice(rs.getInt("total_price"));
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			DataSource.closeConnection(con);
		}
		return cVO;
	}

}











