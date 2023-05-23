package DAO;

import java.util.ArrayList;

import model.OrderDetailVO;

public interface IOrderDetailDAO {
	public ArrayList<OrderDetailVO> getAllOrderDetailList(); //admin에서 볼때
	public ArrayList<OrderDetailVO> getAllUserOrderDeatilList(int orderDetailId); //user에서 볼때
	public int insertOrderDetail(OrderDetailVO vo); // 상품주문
	public int deleteOrderDetail(OrderDetailVO vo); //admin이 상품취소
}
