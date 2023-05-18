package model;

import java.util.ArrayList;


public interface IUsersDAO {
	public ArrayList<UsersVO> getAlluers();
	public UsersVO getUser(String userId);
	public int insertUser (UsersVO vo);
	public int updateUser (UsersVO vo);
	public int deleteUser (UsersVO vo);
}
