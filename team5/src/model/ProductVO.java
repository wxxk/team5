package model;

import lombok.Data;

@Data
public class ProductVO {
	private int productId;
	private int categoryId;
	private String productName;
	private int productPrice;
	private String productImg;
	private int cnt;
}
