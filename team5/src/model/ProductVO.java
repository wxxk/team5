package model;

import lombok.Data;

@Data
public class ProductVO {
	private int productId;
	private int categoryId;
	private String productName;
	private int productPrice;
	private String productImg;

	@Override
	public String toString() {
		return  
				"  " + categoryId + "\t" + "     " + productId + "\t" + "         " + productName + "\t" +  " " + productPrice + "\t" +  "    "+ productImg +"\n"
				+ "----------------------------------------------------------------------";
	}
//	public String toString() {
//		return "상품 ID = " + productId + "\n" + "카테고리 ID = " + categoryId + "\n" +
//				"상품 이름 = " + productName + "\n" + "상품 가격 = " + productPrice + "\n" + 
//				"상품 이미지 = " + productImg + "\n";
//	}

	public String [] toArray() {
		return new String []{
				productId + "" + categoryId + "" + productName + "" + productPrice + "" 
						+ productImg
		};
	}
}
