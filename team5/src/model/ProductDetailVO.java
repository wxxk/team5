package model;

import lombok.Data;

@Data
public class ProductDetailVO {
	private int productDetailId;
	private int productId;
	private String options;
	private int cnt;
	
	@Override
	public String toString() {
		return "상품 상세 ID = " + productDetailId + "\n" + "상품 ID = " + productId + "\n" +
				"옵션 = " + options + "\n" + "수량 = " + cnt + "\n";
	}
	
	public String [] toArray() {
		return new String []{
				productDetailId + "" + productId + "" + options + "" + cnt
		};
	}
}
