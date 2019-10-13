package com.itheima.dao;

import static org.hamcrest.CoreMatchers.nullValue;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.itheima.domain.Customer;
import com.itheima.domain.PageBean;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

	// 要查询的类
	private Class clazz;

//	public BaseDaoImpl(Class  clazz){
//		this.clazz=clazz;
//	}

	public BaseDaoImpl() {
		// 在服务器启动时，spring创建子类，此处this实际为子类
		// public class CustomerDaoImpl extends BaseDaoImpl<Customer> 
		Class<? extends BaseDaoImpl> class1 = this.getClass(); // CustomerDaoImpl
		Type superclass = class1.getGenericSuperclass(); // BaseDaoImpl<Customer>
		if (superclass instanceof ParameterizedType) {
			ParameterizedType type = (ParameterizedType) superclass;
			Type[] types = type.getActualTypeArguments(); // Customer
			this.clazz = (Class) types[0];
		}

	}

	public void save(T t) {
		this.getHibernateTemplate().save(t);
	}

	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}

	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}

	/**
	 * 通过主键查询
	 */
	public T findById(Long id) {
		return (T) getHibernateTemplate().get(clazz, id);
	}

	public List<T> findAll() {
		return (List<T>) getHibernateTemplate().find(" from " + clazz.getSimpleName());
	}

	/**
	 * 分页查询
	 */
	public PageBean<T> findByPage(Integer currPage, Integer pageSize, DetachedCriteria criteria) {
		PageBean<T> page = new PageBean<T>();

		// 当前页页码
		page.setPageCode(currPage);

		// 页面大小
		page.setPageSize(pageSize);

		// 总记录书
		criteria.setProjection(Projections.rowCount());
		List<Number> list = (List<Number>) getHibernateTemplate().findByCriteria(criteria);
		if (list != null && list.size() != 0) {
			page.setTotalCount(list.get(0).intValue());
		}

		// 清楚聚合函数
		criteria.setProjection(null);

		// 查询具体数据
		List<T> result = (List<T>) getHibernateTemplate().findByCriteria(criteria, (currPage - 1) * pageSize, pageSize);
		page.setBeanList(result);

		return page;
	}

}
