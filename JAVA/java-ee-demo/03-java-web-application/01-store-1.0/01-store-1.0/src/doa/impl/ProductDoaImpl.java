package doa.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import doa.ProductDoa;
import domain.Category;
import domain.Product;

public class ProductDoaImpl implements ProductDoa {

	@Override
	public List<Product> getNewProduct() throws SQLException {
		QueryRunner runner = new QueryRunner(new ComboPooledDataSource());
		String sql = "SELECT * FROM product	ORDER BY pdate ASC LIMIT 9;	";
		return runner.query(sql, new BeanListHandler<Product>(Product.class));
	}

	@Override
	public List<Product> getHotProduct() throws Exception {
		QueryRunner runner = new QueryRunner(new ComboPooledDataSource());
		String sql = "SELECT * FROM product WHERE is_hot=1	ORDER BY pdate ASC LIMIT 9;		";
		return runner.query(sql, new BeanListHandler<Product>(Product.class));
	}

	@Override
	public Product geProductById(String pid) throws Exception {
		QueryRunner runner = new QueryRunner(new ComboPooledDataSource());

		String sql = "SELECT * FROM product 	WHERE pid=?;";

		return runner.query(sql, new BeanHandler<Product>(Product.class), pid);
	}

	@Override
	public List<Product> findByPageid(String cid, int currPage, Integer pageSize) throws Exception {
		QueryRunner runner = new QueryRunner(new ComboPooledDataSource());
		String sql = "SELECT * FROM product	WHERE cid=? LIMIT ?, ?  ";
		return runner.query(sql, new BeanListHandler<Product>(Product.class), cid, (currPage - 1) * pageSize, pageSize);
	}

	@Override
	public int getTotalRecodes(String cid) throws SQLException {
		QueryRunner runner = new QueryRunner(new ComboPooledDataSource());
		String sql = "select count(*) from product where cid= ? ";
		return ((Long) runner.query(sql, new ScalarHandler(), cid)).intValue();
	}

	/**
	 * 删除分类信息前，先将该类的商品分类置为空
	 */
	@Override
	public void deleteCid(Connection connection, String cid) throws Exception {
		QueryRunner runner = new QueryRunner();
		String sql = "UPDATE product SET cid = NULL WHERE cid = ? ;";
		runner.update(connection, sql, cid);
	}

	/**
	 * 查询所有的商品信息
	 */
	@Override
	public List<Product> findAll() throws Exception {
		List<Product> pList = new LinkedList<Product>();

		// 查询
		QueryRunner runner = new QueryRunner(new ComboPooledDataSource());
		String sql = "SELECT * FROM product A JOIN category B ON A.cid=B.cid ; ";
		List<Map<String, Object>> maplist = runner.query(sql, new MapListHandler());

		// 封装结果
		for (Map<String, Object> map : maplist) {
			Product product = new Product();
			BeanUtils.populate(product, map);

			Category category = new Category();
			BeanUtils.populate(category, map);

			product.setCategory(category);

			pList.add(product);
		}
		return pList;
	}

}
