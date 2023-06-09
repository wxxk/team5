package DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.ICategoryDAO;
import main.DataSource;
import model.CategoryVO;

public class CategoryDAO implements ICategoryDAO{

	//전체 카테고리 조회
	@Override
	public ArrayList<CategoryVO> getAllCategories() {
		ArrayList<CategoryVO> categoryList = new ArrayList<CategoryVO>();
		String sql = "SELECT * FROM category";
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DataSource.getConnection();
			stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				CategoryVO category = new CategoryVO();
				category.setCategoryId(rs.getInt("category_id"));
				category.setCategoryName(rs.getString("category_name"));
				categoryList.add(category);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DataSource.closeConnection(con);
		}
		return categoryList;
	}
	
	//카테고리별 상품 조회
	@Override
	public ArrayList<CategoryVO> getCategory(String categoryId) {
		ArrayList<CategoryVO> categoryList = new ArrayList<CategoryVO>();
		String sql = "SELECT c.category_id, c.category_name, p.product_id, p.product_name, p.product_price, p.product_img "
				+ "FROM product p, category c "
				+ "WHERE p.category_id = c.category_id and c.category_id = ?";
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DataSource.getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setString(1, categoryId);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				CategoryVO category = new CategoryVO();
				category.setCategoryId(rs.getInt("category_id"));
				category.setCategoryName(rs.getString("category_name"));
				category.setProductId(rs.getInt("product_id"));
				category.setProductName(rs.getString("product_name"));
				category.setProductPrice(rs.getInt("product_price"));
				category.setProductImg(rs.getString("product_img"));
				categoryList.add(category);
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DataSource.closeConnection(con);
		}
		return categoryList;
	}

	//카테고리 등록
	@Override
	public int insertCategory(CategoryVO vo) {
		int count = 0;
		String sql = "INSERT INTO category (category_seq.NEXTVAL(), category_name)"+
				" VALUES (?, ?)";
		Connection con = null;
		try {
			con = DataSource.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, vo.getCategoryId());
			stmt.setString(2, vo.getCategoryName());
			count = stmt.executeUpdate();
		} catch(Exception e) {
			throw new RuntimeException(e);
		} finally {
			DataSource.closeConnection(con);
		}
		return count;
	}

	//카테고리 수정
	@Override
	public int updateCategory(CategoryVO vo) {
		int count = 0;
		String sql = "UPDATE category SET category_name = ? "
				+ "WHERE category_id= ?";
		Connection con = null;
		try {
			con = DataSource.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, vo.getCategoryName());
			stmt.setInt(2, vo.getCategoryId());
			count = stmt.executeUpdate();
		} catch(Exception e) {
			throw new RuntimeException(e);
		} finally {
			DataSource.closeConnection(con);
		}
		return count;
	}

	//카테고리 삭제
	@Override
	public int deleteCategory(CategoryVO vo) {
		int deleteRow = 0;
		String sql = "DELETE FROM category WHERE category_id = ?";
		Connection con = null;
		try {
			con=DataSource.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, vo.getCategoryId());
			deleteRow = stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			DataSource.closeConnection(con);
		}
		return deleteRow;
	}

}
