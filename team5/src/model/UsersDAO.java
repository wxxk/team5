package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.DataSource;


public class UsersDAO implements IUsersDAO {

	@Override
	public ArrayList<UsersVO> getAlluers() {
		ArrayList<UsersVO> userList = new ArrayList<UsersVO>();
		String sql = "SELECT * FROM users";
		Connection con = null;
		try {
			con = DataSource.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				UsersVO user = new UsersVO();
				user.setUserId(rs.getString("user_id"));
				user.setUserPassword(rs.getString("user_password"));
				user.setUserName(rs.getString("user_name"));
				user.setUserBirth(rs.getString("user_birth"));
				user.setUserPhoneNumber(rs.getString("user_phone_number"));
				user.setUserAddress(rs.getString("user_address"));
				userList.add(user);
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			DataSource.closeConnection(con);
		}
		return userList;
	}

	@Override
	public UsersVO getUser(String userId) {
		UsersVO vo = null;
		String sql = "SELECT * FROM users WHERE user_id=?";
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DataSource.getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setString(1, userId);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				vo = new UsersVO();
				vo.setUserId(rs.getString("user_id"));
				vo.setUserPassword(rs.getString("user_password"));
				vo.setUserName(rs.getString("user_name"));
				vo.setUserBirth(rs.getString("user_birth"));
				vo.setUserPhoneNumber(rs.getString("user_phone_number"));
				vo.setUserAddress(rs.getString("user_address"));
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			DataSource.closeConnection(con);
		}
		return vo;
	}

	@Override
	public int insertUser(UsersVO vo) {
		int count=0;
		String sql = "INSERT INTO users (user_id, user_password, user_name, user_birth, user_phone_number, user_address)" +
				" values (?, ?, ?, ?, ?, ?)";
		Connection con = null;
		try {
			con = DataSource.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, vo.getUserId());
			stmt.setString(2, vo.getUserPassword());
			stmt.setString(3, vo.getUserName());
			stmt.setString(4, vo.getUserBirth());
			stmt.setString(5, vo.getUserPhoneNumber());
			stmt.setString(6, vo.getUserAddress());

			count = stmt.executeUpdate();
		} catch(Exception e) {
			throw new RuntimeException(e);
		} finally {
			DataSource.closeConnection(con);
		}
		return count;
	}


	@Override
	public int updateUser(UsersVO vo) {
		int count=0;
		String sql = "UPDATE users SET user_name=?, user_phone_number=? , user_address=? WHERE user_id=?";
		Connection con = null;
		try {
			con = DataSource.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, vo.getUserName());
			stmt.setString(2, vo.getUserPhoneNumber());
			stmt.setString(3, vo.getUserAddress());
			stmt.setString(4, vo.getUserId());
			count = stmt.executeUpdate();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DataSource.closeConnection(con);
		}
		return count;
	}

	@Override
	public int deleteUser(UsersVO vo) {
		int deleteRow = 0;

		String sql = "DELETE FROM users WHERE user_id=? ";

		Connection con = null;
		try {
			con=DataSource.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, vo.getUserId());
			deleteRow = stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			DataSource.closeConnection(con);
		}
		return deleteRow;
	}
}


