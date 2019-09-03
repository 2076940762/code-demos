package service;

import java.util.List;

import domain.Category;

public interface CategoryService {

	List<Category> findAllCategories() throws Exception;

	void add(Category category) throws Exception;

	Category getByCid(String cid) throws Exception;

	void update(Category category) throws Exception;

	void delete(String cid) throws Exception;

}
