package model;

import lombok.Data;

@Data
public class UsersVO {
	private String userId;
	private String userPassword;
	private String userName;
	private String userBirth;
	private String userPhoneNumber;
	private String userAddress;
	private int stated;
	
	@Override
	public String toString() {
		return "아이디 : " + userId + "\n" + "이름 : " + userName + "\n" +
				"생년월일 : " + userBirth +  "\n" + "핸드폰 번호 : " + userPhoneNumber + "\n"+ 
				"주소 : " + userAddress;
	}
	
	public String [] toArray() {
		return new String []{
			userId + "" + userPassword + "" + userName + "" + userBirth + "" 
					+ userPhoneNumber + "" + userAddress
		};
	}
}
 