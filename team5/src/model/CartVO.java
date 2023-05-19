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

	@Override
	public String toString() {
	    return "cartId = " + cartId + ", " +
	            "productImg = " + productImg + ", " +
	            "productName = " + productName + ", " +
	            "categoryName = " + categoryName + ", " +
	            "options = " + options + ", " +
	            "cartCnt = " + cartCnt + ", " +
	            "productPrice = " + productPrice + ", " +
	            "totalPrice = " + totalPrice;
	}

	public String [] toArray() {
		return new String []{
				cartId + "" + productImg + "" + productName + "" + categoryName + "" 
						+ options + "" + cartCnt + "" + productPrice + "" + totalPrice
		};


	}
}
