package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.DataSource;

public class CategoryDAO implements ICategoryDAO{

	//전체 카테고리 조회
	@Override
	public ArrayList<CategoryVO> getAllCategories() {
		ArrayList<CategoryVO> categoryList = new ArrayList<CategoryVO>();
		String sql = "select * from category";
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
			if(stmt!=null) try{stmt.close();} catch(Exception e){};
		}
		return categoryList;
	}
	
	//카테고리별 조회
	@Override
	public CategoryVO getCategory(String categoryId) {
		CategoryVO vo = null;
		String sql = "select c.category_id, c.category_name, p.product_id, p.product_name, p.product_price, p.product_img "
				+ "from product p, category c"
				+ "where p.category_id = c.category_id and c.category_id = ?";
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DataSource.getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setString(1, categoryId);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				vo = new CategoryVO();
				vo.setCategoryId(rs.getInt("category_id"));
				vo.setCategoryName(rs.getString("category_name"));
				vo.setProductId(rs.getInt("product_id"));
				vo.setProductName(rs.getString("product_name"));
				vo.setProductPrice(rs.getInt("product_price"));
				vo.setProductImg(rs.getString("product_img"));
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if(stmt!=null)try {stmt.close();}catch(Exception e) {}
			if(con!=null)try {con.close();}catch(Exception e) {}
		}
		return vo;
	}
	
	//카테고리 등록
	@Override
	public int insertCategory(CategoryVO vo) {
		int count = 0;
		String sql = "INSERT INTO category (category_id, category_name)"+
					 " VALUES (?, ?)";
		Connection con = null;
		try {
			con = DataSource.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, vo.getCategoryId());
			stmt.setString(2, vo.getCategoryName());
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
		String sql = "UPDATE category SET category_name = ?"
				+ "WHERE category_id= ?";
		Connection con = null;
		try {
			con = DataSource.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, vo.getCategoryName());
			stmt.setInt(2, vo.getCategoryId());
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
