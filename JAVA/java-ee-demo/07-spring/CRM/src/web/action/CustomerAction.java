package web.action;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionSupport;

import service.CustomerService;

public class CustomerAction extends ActionSupport {

	public String save() {

		System.out.println("action....");

		//获取ServletContext
		ServletContext servletContext = ServletActionContext.getServletContext();
		
		// 需要使用WEB的工厂的方式
		WebApplicationContext factory = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		CustomerService service=(CustomerService) factory.getBean("customerService");
		
		service.save();
		
		
		return NONE;
	}
}
