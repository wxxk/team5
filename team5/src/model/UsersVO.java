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
		return 
				userId + "\t      " + 
				userName + "\t      " +
				userBirth + "\t     " +
				userPhoneNumber + "\t" +
				userAddress +"\n";
	}

	public String [] toArray() {
		return new String []{
				userId + "" + userPassword + "" + userName + "" + userBirth + "" 
						+ userPhoneNumber + "" + userAddress
		};
	}
}
