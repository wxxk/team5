package model;

import java.util.ArrayList;

public interface IProductDetailDAO {
	public ArrayList<ProductDetailVO> getProductDetail(int productId);
	public ProductDetailVO getProductD(int productId, String options);
	public int insertProductDetail(ProductDetailVO vo);
	public int updateProductDetail(ProductDetailVO vo);
	public int deleteProductDetail(ProductDetailVO vo);
}

