package model;

import java.util.ArrayList;

public interface IProductDAO {
	public ArrayList<ProductVO> getAllProducts();
	public ArrayList<ProductVO> getProductBy(int categoryId);
	public ProductVO getProduct(int productId);
	public int insertProduct (ProductVO vo);
	public int updateProduct (ProductVO vo);
	public int deleteProduct (ProductVO vo);
}
