package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.DataSource;

public class ProductDetailDAO implements IProductDetailDAO {

	//상품 디테일 조회
	@Override
	public ProductDetailVO getProductDetail(String productId) {
		ProductDetailVO vo = null;
		String sql = "SELECT pd.product_detail_id, pd.options, pd.cnt "
				+ "FROM product p, product_detail pd "
				+ "WHERE p.product_id = pd.product_id AND pd.product_id = ?";
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DataSource.getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setString(1, productId);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				vo = new ProductDetailVO();
				vo.setProductDetailId(rs.getInt("product_detail_id"));
				vo.setProductId(rs.getInt("product_id"));
				vo.setOptions(rs.getString("options"));
				vo.setCnt(rs.getInt("cnt"));
			}
		}catch(SQLException e) {
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
		String sql = "INSERT INTO product_detail (producct_detail_seq.NEXTVAL(), product_id, options, cnt)"+
				" VALUES (?, ?, ?, ?)";
		Connection con = null;
		try {
			con = DataSource.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, vo.getProductDetailId());
			stmt.setInt(2, vo.getProductId());
			stmt.setString(3, vo.getOptions());
			stmt.setInt(4, vo.getCnt());
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
			stmt.setInt(1, vo.getProductDetailId());
			deleteRow = stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			DataSource.closeConnection(con);
		}
		return deleteRow;
	}

}
