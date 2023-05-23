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
		return "    " + categoryId + "\t\t" + 
				categoryName;
	}

	public String [] toArray() {
		return new String []{
				categoryId + "" + categoryName
		};
	}
}
