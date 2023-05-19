package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class OrderVO {
	private int orderId;
	private String userId;
	private int productId;
	private String cartId;

	private String userName;
	private String userAddress;
	private String userPhoneNumber;
	private String productName;
	private String productImg;
	private String options;
	private int productPrice;
	private int cartCnt;

	public String toString() {
		return "orderId =" + "orderId" +"\n" + 
				"userId =" + userId + "\n" +
				"userPhoneNumber =" + userPhoneNumber + "\n" +
				"userAddress =" + userAddress + "\n" +
				"productImg =" + productImg + "\n" +
				"productName =" + productName + "\n" +
				"options =" + options + "\n" +
				"cartCnt =" +cartCnt + "\n" +
				"productPrice =" + productPrice;
	}

	public String [] toArray() {
		return new String []{
				orderId + "" + userId + "" + userPhoneNumber + "" + userAddress +
				productImg + "" + productName + "" + options + "" + cartCnt + productPrice	
		};
	}

}
