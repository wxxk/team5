package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.DataSource;

public class AdminDAO implements IAdminDAO {

	@Override
	public ArrayList<AdminVO> getAlladmin() {
		ArrayList<AdminVO> adminList = new ArrayList<AdminVO>();
		String sql = "SELECT * FROM admin";
		Connection con = null;
		try {
			con = DataSource.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				AdminVO admin = new AdminVO();
				admin.setUserId(rs.getString("admin_id"));
				adminList.add(admin);
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			DataSource.closeConnection(con);
		}
		return adminList;
	}

	@Override
	public AdminVO getAdmin(String adminId) {
		AdminVO vo = null;
		String sql = "SELECT admin_id, admin_password FROM admin WHERE admin_id=?";
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DataSource.getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setString(1, adminId);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				vo = new AdminVO();
				vo.setAdminId(rs.getString("admin_id"));
				vo.setAdminPassword(rs.getString("admin_password"));
				System.out.println("타써용");
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			DataSource.closeConnection(con);
		}
		return vo;
	}


	@Override
	public int updateAdmin(AdminVO vo) {
		int count=0;
		String sql = "UPDATE admin SET admin_password FROM admin WHERE admin_id=?";
		Connection con = null;
		try {
			con = DataSource.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, vo.getAdminPassword());
			stmt.setString(2, vo.getAdminId());
			count = stmt.executeUpdate();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DataSource.closeConnection(con);
		}
		return count;
	}
}

