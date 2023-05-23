package model;

import java.util.ArrayList;
import java.util.List;

public interface IOrderDAO {
	public ArrayList<OrderVO> getAllOrderList(); //admin입장에서 전체주문조회
	public ArrayList<OrderVO> getOrderList(String userId); //user에 따른 주문 목록
	public int insertAllCartOrder(String userId, int productId, int cartId, List<CartVO> cl);
	public int insertCartOrder(String userId, int productId, int cartId, List<CartVO> cl);
	public int insertProductOrder(String userId, int productDetailId, int cnt);
	public int updateOrder(OrderVO vo); //user_address options
	public int deleteOrder(OrderVO vo);
}
