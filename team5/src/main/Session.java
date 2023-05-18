package main;

public class Session {
	public static String loginUserId;
	public static String loginAdminId;
	
	public void getLoginAdminId(String adminId) {
		loginAdminId = adminId;
	}
	public void getLoginUserId(String userId) {
		loginUserId = userId;
	}

}
