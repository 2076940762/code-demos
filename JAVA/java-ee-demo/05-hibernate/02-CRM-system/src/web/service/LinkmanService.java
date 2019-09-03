package web.service;

import dao.linkmanDao;
import domain.Linkman;

public class LinkmanService {

	public void add(Long cust_id, Linkman linkman) {
		new linkmanDao().add(cust_id, linkman);
	}

}
