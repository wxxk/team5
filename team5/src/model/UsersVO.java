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
	
	@Override
	public String toString() {
		return "ID =" + userId + "\n" + "Name =" + userName + "\n" +
				"Birth =" + userBirth +  "\n" + "Phonenumber =" + userPhoneNumber + "\n"+ 
				"Address=" + userAddress;
	}
	
	public String [] toArray() {
		return new String []{
			userId + "" + userPassword + "" + userName + "" + userBirth + "" 
					+ userPhoneNumber + "" + userAddress
		};
	}
}
 