package com.itheima.web.action;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.itheima.domain.Customer;
import com.itheima.domain.Linkman;
import com.itheima.domain.PageBean;
import com.itheima.service.LinkmanService;
import com.opensymphony.xwork2.ModelDriven;

public class LinkmanAction extends BaseAction implements ModelDriven<Linkman> {

	private Linkman Linkman = new Linkman();

	@Override
	public Linkman getModel() {
		return Linkman;
	}


	private LinkmanService linkmanService;

	public void setLinkmanService(LinkmanService linkmanService) {
		this.linkmanService = linkmanService;
	}

	/**
	 * 分页查找客户数据
	 * 
	 * @return
	 */
	public String findByPage() {
		//离线查询条件
		DetachedCriteria criteria = DetachedCriteria.forClass(Linkman.class);
		
		//联系人姓名
		String lkm_name = Linkman.getLkm_name();
		if (lkm_name!=null && !lkm_name.trim().isEmpty() ) {
			criteria.add(Restrictions.like("lkm_name", Linkman.getLkm_name()));
		}
		
		//联系人客户
		Customer customer = Linkman.getCustomer();
		if (customer != null && customer.getCust_id() != null) {
			criteria.add(Restrictions.eq("customer.cust_id",customer.getCust_id()));
		}
		
		PageBean<Linkman> page = linkmanService.findByPage(getPageCode(), getPageSize(), criteria);

		System.out.println(page);

		return "page";
	}

}
