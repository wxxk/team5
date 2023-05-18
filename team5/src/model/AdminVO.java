package model;

import lombok.Data;

@Data
public class AdminVO {
	private String adminId;
	private String productId;
	private String userId;
	private String adminPassword;
	@Override
	public String toString() {
		return "adminId=" + adminId ;
	}
}