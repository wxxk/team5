package model;

import lombok.Data;

@Data
public class AdminVO {
	private String adminId;
	private String productId;
	private String userId;
	
	@Override
	public String toString() {
		return "adminId=" + adminId + ", productId=" + productId + ", userId=" + userId;
	}
	
	public String [] toArray() {
		return new String[] {
				adminId + "" + productId + "" + userId
		};
	}
}
