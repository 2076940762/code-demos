package doa;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import domain.Product;

public interface ProductDoa {

	List<Product> getNewProduct() throws SQLException;

	List<Product> getHotProduct() throws Exception;

	Product geProductById(String pid) throws Exception;

	int getTotalRecodes(String cid) throws SQLException;

	List<Product> findByPageid(String cid, int currPage, Integer pageSize) throws Exception;

	void deleteCid(Connection connection, String cid) throws Exception;

	List<Product> findAll()throws Exception;

}
