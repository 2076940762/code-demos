package com.itheima.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.itheima.domain.PageBean;

public interface BaseDao<T> {

	// 保存
	public void save(T t);

	//更新
	public void update(T t);

	//删除
	public void delete(T t);
	
   //查询指定id的用户
	public T findById(Long id);
	
	//查询所有
	public List<T> findAll();

	/**
	//分页查询
	 * @param currPage当前页页码
	 * @param pageSize 页面大小
	 * @param criteria     离线查询条件
	 * @return
	 */
	public PageBean<T> findByPage(Integer currPage,Integer pageSize,DetachedCriteria criteria);
}
