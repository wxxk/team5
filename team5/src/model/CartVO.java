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


	@Override
	public String toString() {
		return  "   " +cartId + "\t" + "      " +
				 productImg + "\t  " +
				 productName +  "\t     " + 
				 categoryName + "\t   " + 
				 options + "           " +
				 cartCnt + "\t    " +
				productPrice  +"\n"+
				"-----------------------------------------------------------------------------------------------------";
	
	
	}

	public String [] toArray() {
		return new String []{
				cartId + "" + productImg + "" + productName + "" + categoryName + "" 
						+ options + "" + cartCnt + "" + productPrice + "" 
		};


	}
}
