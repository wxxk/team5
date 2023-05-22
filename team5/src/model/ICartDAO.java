package model;

import java.util.ArrayList;

public interface ICartDAO {
	public ArrayList<CartVO> getAllCart(String userId);
	public ArrayList<CartVO> getCart(String cartId);
	public int insertCart(CartVO vo); //상품등록
	public int updateCart(CartVO vo); //수량만 수정
	public int allDeleteCart(CartVO vo); //카트 전체삭제
	public int deleteCart(CartVO vo); //카트 부분삭제
}
