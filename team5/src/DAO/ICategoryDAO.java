package DAO;

import java.util.ArrayList;

import model.CategoryVO;

public interface ICategoryDAO {
	public ArrayList<CategoryVO> getAllCategories();
	public ArrayList<CategoryVO> getCategory(String categoryId);
	public int insertCategory(CategoryVO vo);
	public int updateCategory(CategoryVO vo);
	public int deleteCategory(CategoryVO vo);
}
