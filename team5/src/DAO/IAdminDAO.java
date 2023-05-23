package DAO;

import java.util.ArrayList;

import model.AdminVO;

public interface IAdminDAO {
	public ArrayList<AdminVO> getAlladmin();
	public AdminVO getAdmin(String adminId);
	public int updateAdmin (AdminVO vo);
}
