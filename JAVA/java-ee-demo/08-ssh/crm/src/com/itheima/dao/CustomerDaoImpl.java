package com.itheima.dao;

import com.itheima.domain.Customer;

/**
 * 持久层
 * @author Administrator
 */
public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {

//	public CustomerDaoImpl() {
//		super(Customer.class);
//	}
	
	/**
	 * 保存客户
	 */
//	public void save(Customer customer) {
//		// 把数据，保存到数据库中
//		this.getHibernateTemplate().save(customer);
//	}
	
	/**
	 * 分页的查询
	 */
//	public PageBean<Customer> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria) {
//		PageBean<Customer> page = new PageBean<Customer>();
//		page.setPageCode(pageCode);
//		page.setPageSize(pageSize);
//		
//		// 先查询总记录数	select count(*)
//		criteria.setProjection(Projections.rowCount());
//		List<Number> list = (List<Number>) this.getHibernateTemplate().findByCriteria(criteria);
//		if(list != null && list.size() > 0){
//			int totalCount = list.get(0).intValue();
//			// 总的记录数
//			page.setTotalCount(totalCount);
//		}
//		
//		// 强调：把select count(*) 先清空，变成  select * ...
//		criteria.setProjection(null);
//		
//		// 提供分页的查询
//		List<Customer> beanList = (List<Customer>) this.getHibernateTemplate().findByCriteria(criteria, (pageCode-1)*pageSize, pageSize);
//		// 分页查询数据，每页显示的数据  使用limit
//		page.setBeanList(beanList);
//		
//		return page;
//	}

	/**
	 * 通过主键，查询客户
	 */
//	public Customer findById(Long cust_id) {
//		return this.getHibernateTemplate().get(Customer.class, cust_id);
//	}

	/**
	 * 删除客户
	 */
//	public void delete(Customer customer) {
//		this.getHibernateTemplate().delete(customer);
//	}

//	@Override
//	public void update(Customer customer) {
//		this.getHibernateTemplate().update(customer);
//	}
	
}










