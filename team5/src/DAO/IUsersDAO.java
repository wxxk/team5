package DAO;

import java.util.ArrayList;

import model.UsersVO;


public interface IUsersDAO {
	public ArrayList<UsersVO> getAlluers(); //admin에서 전체 회원 정보
	public UsersVO getUser(String userId); //회원 정보 불러오기
	public int insertUser (UsersVO vo);
	public int updateUser (UsersVO vo);
	public int deleteUser (UsersVO vo);
}


