package DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.IOrderDetailDAO;
import main.DataSource;
import model.OrderDetailVO;
import model.ProductVO;

public class OrderDetailDAO implements IOrderDetailDAO {

	@Override
	public ArrayList<OrderDetailVO> getAllOrderDetailList() {
		ArrayList<OrderDetailVO> odlist = new ArrayList<OrderDetailVO>();
		OrderDAO oDAO = new OrderDAO();
		String sql = "o.order_id, od.order_detail_id, p.product_id, od.product_cnt, od.options "
				+ "FROM order_details od "
				+"JOIN orders o ON o.order_id = od.order_id "
				+"JOIN product p ON p.product_id = od.product_id";
		Connection con = null;
		try {
			con = DataSource.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs =  stmt.executeQuery();
			while(rs.next()) {
				ProductVO pVO = new ProductVO();
				OrderDetailVO odVO = new OrderDetailVO();
				odVO.setOrderId(rs.getInt("order_id"));
				odVO.setOrderDetailId(rs.getInt("order_detail_id"));
				odVO.setProductId(rs.getInt("product_id"));
				odVO.setProductCnt(rs.getInt("product_cnt"));
				odVO.setOptions(rs.getString("options"));
				odlist.add(odVO);
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			DataSource.closeConnection(con);
		}
		return odlist;
	}

	@Override
	public ArrayList<OrderDetailVO> getAllUserOrderDeatilList(int orderDetailId) {
		ArrayList<OrderDetailVO> odlist = new ArrayList<OrderDetailVO>();
		String sql = "SELECT o.order_id, od.order_detail_id, p.product_id, od.product_cnt, od.options "
				+"FROM order_details od "
				+"JOIN orders o ON o.order_id = od.order_id "
				+"JOIN product p ON p.product_id = od.product_id "
				+"WHERE od.order_details_id = ?";
		Connection con = null;
		try {
			con = DataSource.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, orderDetailId);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				OrderDetailVO odVO = new OrderDetailVO();
				odVO.setOrderId(rs.getInt("order_id"));
				odVO.setOrderDetailId(rs.getInt("order_detail_id"));
				odVO.setProductId(rs.getInt("product_id"));
				odVO.setProductCnt(rs.getInt("product_cnt"));
				odVO.setOptions(rs.getString("options"));
				odlist.add(odVO);
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			DataSource.closeConnection(con);
		}
		return odlist;
	}

	@Override
	public int insertOrderDetail(OrderDetailVO vo) {
		int count = 0;
		String sql = "INSERT INTO order_details VALUES(order_details_seq.NEXTVAL,?,?,?,?)";
		Connection con = null;
		try {
			con = DataSource.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, vo.getOrderId());
			stmt.setInt(2, vo.getProductId());
			stmt.setInt(3, vo.getProductCnt());
			stmt.setString(4, vo.getOptions());
			count = stmt.executeUpdate();
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);

		}finally {
			DataSource.closeConnection(con);
		}
		return count;
	}
}

//	@Override
//	public int deleteOrderDetail(OrderDetailVO vo) {
//		int deleteRow = 0;
//		String sql = "DELETE FROM order_details WHERE order_detail_id =?";
//		Connection con = null;
//		try {
//			con = DataSource.getConnection();
//			PreparedStatement stmt = con.prepareStatement(sql);
//			stmt.setInt(1, vo.getOrderDetailId());
//			deleteRow = stmt.executeUpdate();
//		}catch(SQLException e) {
//			throw new RuntimeException(e);
//		}finally {
//			DataSource.closeConnection(con);
//		}
//		return deleteRow;
//	}
//
//}
