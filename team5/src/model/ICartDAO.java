package model;

import java.util.ArrayList;

public interface ICartDAO {
	//카트에 담아둔 상품조회
	ArrayList<CartVO> getAllCart();
	int insertCart(CartVO vo);
	int updateCart(CartVO vo);
	int deleteCart(int productId);
}
