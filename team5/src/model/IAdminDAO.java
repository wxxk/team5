package model;

import java.util.ArrayList;

public interface IAdminDAO {
	public ArrayList<AdminVO> getAlladmin();
	public AdminVO getAdmin(String adminId);
	public int updateAdmin (AdminVO vo);
}
