package model;

import java.util.ArrayList;

public interface IOrderDAO {
	public ArrayList<OrderVO> getAllOrderList();
	public ArrayList<OrderVO> getOrderList(String userId);
	public int insertOrder(String userId, String productName, int cartId );
	public int updateOrder(OrderVO vo); //user_address options
	public int deleteOrder(OrderVO vo);
}
