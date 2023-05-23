package DAO;

import java.util.ArrayList;
import java.util.List;

import model.CartVO;
import model.OrderVO;

public interface IOrderDAO {
	public ArrayList<OrderVO> getAllOrderList(); //admin입장에서 전체주문조회
	public ArrayList<OrderVO> getOrderList(String userId); //user에 따른 주문 목록
	public int insertAllCartOrder(OrderVO vo);
	public int insertCartOrder(String userId, int productId, int cartId, List<CartVO> cl, String options);
	public int insertProductOrder(OrderVO vo, int cnt, String orderDetailOptions);
	public int updateOrder(OrderVO vo); //user_address options
	public int deleteOrder(OrderVO vo);
}
