package model;

import java.util.ArrayList;

public interface ICategoryDAO {
	public ArrayList<CategoryVO> getAllCategories();
	public CategoryVO getCategory(String categoryId);
	public int insertCategory(CategoryVO vo);
	public int updateCategory(CategoryVO vo);
	public int deleteCategory(CategoryVO vo);
}
