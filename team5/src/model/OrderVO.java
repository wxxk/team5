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
	private int cartId;

	private String userName;
	private String userAddress;
	private String userPhoneNumber;
	private String productName;
	private String productImg;
	private String options;
	private int productPrice;
	private int totalPrice;


	public String toString() {
		return "orderId:" + orderId +"\t" + 
				"productName:" + productName + "\t" +
				"productPrice:" + productPrice + "\t" + 
				"userName:" + userName + "\t" +
				"userAddress:" + userAddress + "\t" +
				"userPhoneNumber:" + userPhoneNumber + "\t" +
				"options:" + options + "\t" +
				"orderTotalPrice:" + totalPrice + "\t";
//				"cartId:" + cartId + "\t" +
//				"userId:" + userId + "\t" +
//				"productId:" + productId + "\t" +
//				"productImg:" + productImg + "\t" +
	}

	public String [] toArray() {
		return new String []{
				orderId + "" + userId + "" + userPhoneNumber + "" + userAddress +
				productImg + "" + productName + "" + options + "" + productPrice	
		};
	}

}
