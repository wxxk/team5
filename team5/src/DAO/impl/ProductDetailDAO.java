package DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.IProductDetailDAO;
import main.DataSource;
import model.ProductDetailVO;

public class ProductDetailDAO implements IProductDetailDAO {

	//상품 디테일 조회
	@Override
	public ArrayList<ProductDetailVO> getProductDetail(int productId) {
		ArrayList<ProductDetailVO> productDetailList = new ArrayList<ProductDetailVO>();
		ProductDetailVO vo = null;
		String sql = "SELECT pd.product_detail_id, p.product_id, pd.options, pd.cnt "
				+ "FROM product p, product_detail pd "
				+ "WHERE p.product_id = pd.product_id AND pd.product_id = ?";
		
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DataSource.getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, productId);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				vo = new ProductDetailVO();
				vo.setProductDetailId(rs.getInt("product_detail_id"));
				vo.setProductId(rs.getInt("product_id"));
				vo.setOptions(rs.getString("options"));
				vo.setCnt(rs.getInt("cnt"));
				productDetailList.add(vo);
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if(stmt!=null)try {stmt.close();}catch(Exception e) {}
			if(con!=null)try {con.close();}catch(Exception e) {}
		}
		return productDetailList;
	}
	
	public ProductDetailVO getProductD(int productId, String options) {
		ProductDetailVO vo = null;
		String sql = "SELECT pd.product_detail_id, p.product_id, pd.options, pd.cnt "
				+ "FROM product p, product_detail pd "
				+ "WHERE p.product_id = pd.product_id "
				+ "AND pd.product_id = ? "
				+ "AND pd.options = ?";
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DataSource.getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, productId);
			stmt.setString(2, options);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				vo = new ProductDetailVO();
				vo.setProductDetailId(rs.getInt("product_detail_id"));
				vo.setProductId(rs.getInt("product_id"));
				vo.setOptions(rs.getString("options"));
				vo.setCnt(rs.getInt("cnt"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if(stmt!=null)try {stmt.close();}catch(Exception e) {}
			if(con!=null)try {con.close();}catch(Exception e) {}
		}
		return vo;
	}
	

	//상품 디테일 등록
	@Override
	public int insertProductDetail(ProductDetailVO vo) {
	    int count = 0;
	    String sql = "INSERT INTO product_detail (product_detail_id, product_id, options, cnt)"+
	        " VALUES (product_detail_seq.NEXTVAL, ?, ?, ?)";
	    Connection con = null;
	    try {
	        con = DataSource.getConnection();
	        PreparedStatement stmt = con.prepareStatement(sql);
	        stmt.setInt(1, vo.getProductId());
	        stmt.setString(2, vo.getOptions());
	        stmt.setInt(3, vo.getCnt());
	        count = stmt.executeUpdate();
	    } catch(Exception e) {
	        throw new RuntimeException(e);
	    } finally {
	        DataSource.closeConnection(con);
	    }
	    return count;
	}

	//상품 디테일 수정
	@Override
	public int updateProductDetail(ProductDetailVO vo) {
		int count = 0;
		String sql = "UPDATE product_detail SET product_id = ?, options = ?, cnt = ? "
				+ "WHERE product_detail_id = ?";
		Connection con = null;
		try {
			con = DataSource.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, vo.getProductId());
			stmt.setString(2, vo.getOptions());
			stmt.setInt(3, vo.getCnt());
			stmt.setInt(4, vo.getProductDetailId());
			count = stmt.executeUpdate();
		} catch(Exception e) {
			throw new RuntimeException(e);
		} finally {
			DataSource.closeConnection(con);
		}
		return count;
	}

	//상품 디테일 삭제
	@Override
	public int deleteProductDetail(ProductDetailVO vo) {
		int deleteRow = 0;
		String sql = "DELETE FROM product_detail WHERE product_detail_id = ?";
		Connection con = null;
		try {
			con=DataSource.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, vo.getCnt());
			deleteRow = stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			DataSource.closeConnection(con);
		}
		return deleteRow;
	}

	@Override
	public int updateStock(int productDetailId, int cnt, String options) {
		int count = 0;
		String sql = "UPDATE product_detail SET cnt = ? WHERE product_id = ? AND options = ?";
		Connection con = null;
		try {
			con = DataSource.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setInt(1, cnt);
			stmt.setInt(2, productDetailId);
			stmt.setString(3, options);
			
			count = stmt.executeUpdate();
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			DataSource.closeConnection(con);
		}
		return count;
	}

}
