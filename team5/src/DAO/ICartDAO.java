package DAO;

import java.util.ArrayList;

import model.CartVO;

public interface ICartDAO {
	public ArrayList<CartVO> getAllCart(String userId); //장바구니 전체 조회
	public int insertCart(CartVO vo); //상품등록
	public int updateCart(int cartCnt, int totalPrice,int cartId); //수량만 수정
	public int allDeleteCart(CartVO vo); //카트 전체삭제
	public int deleteCart(int cartId); //카트 부분삭제
}
//public ArrayList<CartVO> getCart(int cartId); //cart_id별로 장바구니 조회


