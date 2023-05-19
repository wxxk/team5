package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.DataSource;

public class ProductDAO implements IProductDAO{

	//전체 상품 조회
	@Override
	public ArrayList<ProductVO> getAllProducts() {
		ArrayList<ProductVO> productList = new ArrayList<ProductVO>();
		String sql = "SELECT p.product_id, c.category_id, p.product_name, p.product_price, p.product_img, p.cnt "
				+ "FROM product p, category c "
				+ "WHERE p.category_id = c.category_id";
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DataSource.getConnection();
			stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				ProductVO product = new ProductVO();
				product.setProductId(rs.getInt("product_id"));
				product.setCategoryId(rs.getInt("category_id"));
				product.setProductName(rs.getString("product_name"));
				product.setProductPrice(rs.getInt("product_price"));
				product.setProductImg(rs.getString("product_img"));
				product.setCnt(rs.getInt("cnt"));
				productList.add(product);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DataSource.closeConnection(con);
			if(stmt!=null) try{stmt.close();} catch(Exception e){};
		}
		return productList;
	}

	//categoryId로 상품 조회
	@Override
	public ArrayList<ProductVO> getProductBy(int categoryId) {
		ArrayList<ProductVO> productList = new ArrayList<ProductVO>();
		String sql = "SELECT * FROM product WHERE category_id = ?";
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DataSource.getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, categoryId);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				ProductVO product = new ProductVO();
				product.setProductId(rs.getInt("product_id"));
				product.setCategoryId(rs.getInt("category_id"));
				product.setProductName(rs.getString("product_name"));
				product.setProductPrice(rs.getInt("product_price"));
				product.setProductImg(rs.getString("product_img"));
				product.setCnt(rs.getInt("cnt"));
				productList.add(product);
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DataSource.closeConnection(con);
		}
		return productList;
	}

	//productName으로 상품 조회
	@Override
	public ProductVO getProduct(String productName) {
		ProductVO vo = null;
		String sql = "SELECT product_id, product_name, product_price From product "
				+ "WHERE product_name = ? ";
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DataSource.getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setString(1, productName);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				vo = new ProductVO();
				vo.setProductId(rs.getInt("product_id"));
				vo.setProductName(rs.getString("product_name"));
				vo.setProductPrice(rs.getInt("product_price"));
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			DataSource.closeConnection(con);
		}
		return vo;
	}

	//상품 등록
	@Override
	public int insertProduct(ProductVO vo) { 
		int count = 0;
		String sql = "INSERT INTO product (product_id, category_id, product_name, product_price, product_img, cnt)"+
				" VALUES (product_seq.NEXTVAL, ?, ?, ?, ?, ?)";
		Connection con = null;
		try {
			con = DataSource.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
//			stmt.setInt(1, vo.getProductId());
			stmt.setInt(1, vo.getCategoryId());
			stmt.setString(2, vo.getProductName());
			stmt.setInt(3, vo.getProductPrice());
			stmt.setString(4, vo.getProductImg());
			stmt.setInt(5, vo.getCnt());
			count = stmt.executeUpdate();
		} catch(Exception e) {
			throw new RuntimeException(e);
		} finally {
			DataSource.closeConnection(con);
		}
		return count;
	}

	//상품 수정
	@Override
	public int updateProduct(ProductVO vo) {
		int count = 0;
		String sql = "UPDATE product SET category_id = ?, product_name = ?, product_price = ?, product_img = ?, cnt = ? "
				+ "WHERE product_id = ?";
		Connection con = null;
		try {
			con = DataSource.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, vo.getCategoryId());
			stmt.setString(2, vo.getProductName());
			stmt.setInt(3, vo.getProductPrice());
			stmt.setString(4, vo.getProductImg());
			stmt.setInt(5, vo.getCnt());
			stmt.setInt(6, vo.getProductId());
			count = stmt.executeUpdate();
		} catch(Exception e) {
			throw new RuntimeException(e);
		} finally {
			DataSource.closeConnection(con);
		}
		return count;
	}

	//상품 삭제
	@Override
	public int deleteProduct(ProductVO vo) {
		int deleteRow = 0;
		String sql = "DELETE FROM product WHERE product_id = ?";
		Connection con = null;
		try {
			con=DataSource.getConnection();
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

}
