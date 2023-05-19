package model;

import java.util.ArrayList;

public interface IOrderDAO {
	public ArrayList<OrderVO> getAllOrderList();
	public OrderVO getOrder(String userId);
	
}
