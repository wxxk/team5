package DAO;

import java.util.ArrayList;

import model.OrderVO;

public interface IOrderDAO {
	public ArrayList<OrderVO> getAllOrderList(); //admin입장에서 전체주문조회
	public ArrayList<OrderVO> getOrderList(String userId); //user에 따른 주문 목록
	public int insertCartOrder();
	public int insertProductOrder(String userId, int productDetailId, int cnt);
	public int updateOrder(OrderVO vo); //user_address options
	public int deleteOrder(OrderVO vo);
}
