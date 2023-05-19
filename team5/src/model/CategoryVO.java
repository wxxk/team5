package model;

import lombok.Data;

@Data
public class CategoryVO {
	private int categoryId;
	private String categoryName;
	private int productId;
	private String productName;
	private int productPrice;
	private String productImg;
	
	@Override
	public String toString() {
		return "카테고리 ID = " + categoryId + "\n" + "카테고리 이름 = " + categoryName + "\n";
	}
	
	public String [] toArray() {
		return new String []{
				categoryId + "" + categoryName
		};
	}
}
