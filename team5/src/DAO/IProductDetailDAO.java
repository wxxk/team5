package DAO;

import java.util.ArrayList;

import model.ProductDetailVO;

public interface IProductDetailDAO {
	public ArrayList<ProductDetailVO> getProductDetail(int productId);
	public ProductDetailVO getProductD(int productId, String options);
	public int insertProductDetail(ProductDetailVO vo);
	public int updateProductDetail(ProductDetailVO vo);
	public int updateStock	(int productDetailId, int cnt, String options);
	public int deleteProductDetail(ProductDetailVO vo);
}

