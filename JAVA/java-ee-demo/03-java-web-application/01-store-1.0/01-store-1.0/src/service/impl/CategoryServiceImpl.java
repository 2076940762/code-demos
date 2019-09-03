package service.impl;

import java.sql.Connection;
import java.util.List;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import doa.CategoryDoa;
import doa.ProductDoa;
import doa.impl.CategoryDoaimpl;
import doa.impl.ProductDoaImpl;
import domain.Category;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import service.CategoryService;

public class CategoryServiceImpl implements CategoryService {

	@Override
	public List<Category> findAllCategories() throws Exception {
		/*
		 * 使用缓存技术改进
		 */

		// 1.创建缓存管理器
		CacheManager manager = CacheManager
				.create(CategoryServiceImpl.class.getClassLoader().getResourceAsStream("ehcache.xml"));

		// 获取缓存
		Cache cache = manager.getCache("categoryCache");

//		获取数据
		Element element = cache.get("clist");

		List<Category> cList = null;

		if (element == null) {
			// 缓存中没有指定数据则去数据库查询
			CategoryDoa doa = new CategoryDoaimpl();
			return doa.findAllCategories();
		} else {
//			如果缓存命中则直接返回
			cList = (List<Category>) element.getObjectValue();
			return cList;
		}
	}

	@Override
	public void add(Category category) throws Exception {
		// 向数据库中添加分类信息
		CategoryDoa doa = new CategoryDoaimpl();
		doa.add(category);

		// 删除缓存
		CacheManager manager = new CacheManager(
				CategoryServiceImpl.class.getClassLoader().getResourceAsStream("ehcache.xml"));
		Cache cache = manager.getCache("categoryCache");
		cache.remove("clist");
	}

	/**
	 * 根据id获取分类信息
	 */
	@Override
	public Category getByCid(String cid) throws Exception {
		CategoryDoa dao = new CategoryDoaimpl();
		return dao.getByCid(cid);
	}

	@Override
	public void update(Category category) throws Exception {
		CategoryDoa dao = new CategoryDoaimpl();
		dao.update(category);
	}

	/**
	 * 删除分类
	 */
	@Override
	public void delete(String cid) throws Exception {
		Connection connection =null;
		try {
			// 获取连接
			connection = new ComboPooledDataSource().getConnection();

			// 开启事务
			connection.setAutoCommit(false);

			// 将相应商品的cid置为nulll
			ProductDoa productDoa = new ProductDoaImpl();
			productDoa.deleteCid(connection, cid);

			// 删除分类
			CategoryDoa dao = new CategoryDoaimpl();
			dao.delete(connection, cid);

			// 提交事务
			connection.commit();
			connection.close();
		} catch (Exception e) {
			connection.rollback();
			connection.close();
			
			throw e;
		}

		// 清空缓存
		CacheManager manager = CacheManager.create(this.getClass().getClassLoader().getResourceAsStream("ehcache.xml"));
		Cache cache = manager.getCache("categoryCache");
		cache.remove("clist");
	}

}
