package doa;

import java.sql.Connection;
import java.util.List;

import domain.Category;

public interface CategoryDoa {

	List<Category> findAllCategories() throws Exception;

	void add(Category category) throws Exception;

	Category getByCid(String cid) throws Exception;

	void update(Category category)  throws Exception;

	void delete(Connection connection, String cid) throws Exception;

}
