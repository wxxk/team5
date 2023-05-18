package model;

import java.util.ArrayList;

public interface IProductDetailDAO {
	public ProductDetailVO getProductDetail(String productId);
	public int insertProductDetail(ProductDetailVO vo);
	public int updateProductDetail(ProductDetailVO vo);
	public int deleteProductDetail(ProductDetailVO vo);
}

