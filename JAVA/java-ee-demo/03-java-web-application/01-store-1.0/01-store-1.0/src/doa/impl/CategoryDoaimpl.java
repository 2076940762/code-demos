package doa.impl;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import doa.CategoryDoa;
import domain.Category;

public class CategoryDoaimpl implements CategoryDoa {

	@Override
	public List<Category> findAllCategories() throws Exception {
		QueryRunner qr = new QueryRunner(new ComboPooledDataSource());
		String sql = "SELECT * FROM  category	;";
		return qr.query(sql, new BeanListHandler<Category>(Category.class));
	}

	/**
	 * 添加分类信息
	 */
	@Override
	public void add(Category category) throws Exception {
		QueryRunner runner = new QueryRunner(new ComboPooledDataSource());
		String sql = "INSERT INTO category (cid,cname) VALUES (?,?) ; ";
		runner.update(sql, category.getCid(), category.getCname());
	}

	@Override
	public Category getByCid(String cid) throws Exception {
		QueryRunner runner = new QueryRunner(new ComboPooledDataSource());
		String sql = " SELECT * FROM category WHERE cid =? ; ";
		return runner.query(sql, new BeanHandler<Category>(Category.class), cid);
	}

	/**
	 * 更新分类
	 */
	@Override
	public void update(Category category) throws Exception {
		QueryRunner runner = new QueryRunner(new ComboPooledDataSource());
		String sql = "UPDATE category SET cname= ? WHERE cid =? ; ";
		runner.update(sql, category.getCname(), category.getCid());
	}

	/**
	 * 删除分类
	 */
	@Override
	public void delete(Connection connection, String cid) throws Exception {
		QueryRunner runner = new QueryRunner();
		String sql=" DELETE FROM category WHERE cid = ? ; ";
		runner.update(connection, sql, cid);
	}

}
