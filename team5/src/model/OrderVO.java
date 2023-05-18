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
	private int productPrice;
	private int cartCnt;
	
}
