package com.itheima.service;

import org.hibernate.criterion.DetachedCriteria;

import com.itheima.dao.LinkmanDao;
import com.itheima.domain.Linkman;
import com.itheima.domain.PageBean;

public class LinkmanServiceImpl implements LinkmanService {

	private LinkmanDao linkmanDao;

	public void setLinkmanDao(LinkmanDao linkmanDao) {
		this.linkmanDao = linkmanDao;
	}

	@Override
	public PageBean<Linkman> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria) {
		return linkmanDao.findByPage(pageCode, pageSize, criteria);
	}

}
