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

	@Override
	public String toString() {
		return "상품 ID = " + productId + "\n" + "카테고리 ID = " + categoryId + "\n" +
				"상품 이름 = " + productName + "\n" + "상품 가격 = " + productPrice + "\n" + 
				"상품 이미지 = " + productImg + "\n" + "수량 = " + cnt + "\n";
	}

	public String [] toArray() {
		return new String []{
				productId + "" + categoryId + "" + productName + "" + productPrice + "" 
						+ productImg + "" + cnt
		};
	}
}
