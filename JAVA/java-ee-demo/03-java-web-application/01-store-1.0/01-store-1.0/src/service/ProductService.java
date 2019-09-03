package service;

import java.sql.SQLException;
import java.util.List;

import domain.ProductPageBean;
import domain.Product;

public interface ProductService {

	List<Product> getNewProduct() throws SQLException;

	List<Product> getHotProduct() throws Exception;

	Product geProductById(String pid) throws Exception;

	ProductPageBean findByPageid(String cid, int currPage, Integer pageSize) throws Exception;

	List<Product> findAll() throws Exception;

	void add(Product product) throws Exception;

}
