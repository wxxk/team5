package model;

import lombok.Data;

@Data
public class OrderDetailVO {
	private int orderDetailId;
	private int orderId;
	private int productId;
	private int productCnt;
	private String options;
	@Override
	public String toString() {
		return "OrderDetailVO [orderDetailId=" + orderDetailId + ", orderId=" + orderId + ", productId=" + productId
				+ ", productCnt=" + productCnt + "]";
	}
	
	
	public String[] toArray() {
		return new String[] {
			orderDetailId +""	
		};
	}
}
