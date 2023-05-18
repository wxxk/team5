package model;

import lombok.Data;

@Data
public class ProductDetailVO {
	private int productDetailId;
	private int productId;
	private String options;
	private int cnt;
}
