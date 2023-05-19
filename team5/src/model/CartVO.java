package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


public class CartVO {
	private int cartId;
	private String userId;
	private int productId;
	private String productImg;
	private String productName;
	private String categoryName;
	private String options;
	private int cartCnt;
	private int productPrice;
	private int totalPrice;


	public String toString() {
		return "cartId = " + cartId + "\n" + 
				"productImg = " + productImg + "\n" +
				"productName = " + productName +  "\n" + 
				"categoryName = " + categoryName + "\n" + 
				"options = " + options + "\n" +
				"cartCnt = " + cartCnt + "\n" +
				"productPrice = " + productPrice + "\n" +
				"totalPrice = " + totalPrice + "\n";	
	}

	public String [] toArray() {
		return new String []{
				cartId + "" + productImg + "" + productName + "" + categoryName + "" 
						+ options + "" + cartCnt + "" + productPrice + "" + totalPrice
		};


	}
}
