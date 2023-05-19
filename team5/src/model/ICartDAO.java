package model;

import java.util.ArrayList;

public interface ICartDAO {
	ArrayList<CartVO> getAllCart(String userId);
	int insertCart(CartVO vo); //상품등록
	int updateCart(CartVO vo); //수량만 수정
	int allDeleteCart(CartVO vo); //카트 전체삭제
	int deleteCart(CartVO vo); //카트 부분삭제
}
