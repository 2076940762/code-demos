package com.itheima.web.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.itheima.domain.Customer;
import com.itheima.domain.Dict;
import com.itheima.domain.PageBean;
import com.itheima.service.CustomerService;
import com.itheima.utils.UploadUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
import com.sun.corba.se.impl.orbutil.closure.Constant;

/**
 * 客户的控制层
 * 
 * @author Administrator
 */
public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {

	private static final long serialVersionUID = 113695314694166436L;

	private static String PATH = "D:\\opt\\apache-tomcat-8.5.46-windows-x64\\webapps\\upload\\";

	// 不要忘记手动new
	private Customer customer = new Customer();

	// model 是CustomerAction类的属性
	public Customer getModel() {
		return customer;
	}

	// 提供service的成员属性，提供set方法
	private CustomerService customerService;

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	// 属性驱动的方式
	// 当前页，默认值就是1
	private Integer pageCode = 1;

	public void setPageCode(Integer pageCode) {
		if (pageCode == null) {
			pageCode = 1;
		}
		this.pageCode = pageCode;
	}

	// 每页显示的数据的条数
	private Integer pageSize = 2;

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 分页的查询方法
	 * 
	 * @return
	 */
	public String findByPage() {
		// 调用service业务层
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		// 拼接查询的条件
		String cust_name = customer.getCust_name();
		if (cust_name != null && !cust_name.trim().isEmpty()) {
			// 说明，客户的名称输入值了
			criteria.add(Restrictions.like("cust_name", "%" + cust_name + "%"));
		}

		// 拼接客户的级别
		Dict level = customer.getLevel();
		if (level != null && !level.getDict_id().trim().isEmpty()) {
			// 说明，客户的级别肯定选择了一个级别
			criteria.add(Restrictions.eq("level.dict_id", level.getDict_id()));
		}

		// 客户的来源
		Dict source = customer.getSource();
		if (source != null && !source.getDict_id().trim().isEmpty()) {
			// 说明，客户的级别肯定选择了一个级别
			criteria.add(Restrictions.eq("source.dict_id", source.getDict_id()));
		}

		// 查询
		PageBean<Customer> page = customerService.findByPage(pageCode, pageSize, criteria);
		// 压栈
		ValueStack vs = ActionContext.getContext().getValueStack();
		// 栈顶是map<"page",page对象>
		vs.set("page", page);
		return "page";
	}

	/**
	 * 初始化到添加的页面
	 * 
	 * @return
	 */
	public String initAddUI() {
		return "initAddUI";
	}

	/**
	 * 文件的上传，需要在CustomerAction类中定义成员的属性，命名是有规则的！！ private File upload; // 表示要上传的文件
	 * private String uploadFileName; 表示是上传文件的名称（没有中文乱码） private String
	 * uploadContentType; 表示上传文件的MIME类型 提供set方法，拦截器就注入值了
	 */

	// 要上传的文件
	private File upload;

	// 文件的名称
	private String uploadFileName;

	// 文件的MIME的类型
	private String uploadContentType;

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	/**
	 * 保存客户的方法
	 * 
	 * @return
	 * @throws IOException
	 */
	public String save() throws IOException {
		// 做文件的上传，说明用户选择了上传的文件了
		if (uploadFileName != null) {

			// 打印
			System.out.println("文件类型：" + uploadContentType);

			// 把文件的名称处理一下
			String uuidname = UploadUtils.getUUIDName(uploadFileName);

			// 把文件上传到D:\\apache-tomcat-7.0.52\\webapps\\upload
			String path = "D:\\opt\\apache-tomcat-8.5.46-windows-x64\\webapps\\upload\\";

			// 创建file对象
			File file = new File(path + uuidname);

			// 简单方式
			FileUtils.copyFile(upload, file);

			// 把上传的文件的路径，保存到客户表中
			customer.setFilepath(path + uuidname);
		}

		// 保存客户成功了
		customerService.save(customer);
		return "save";
	}

	/**
	 * 删除客户
	 * 
	 * @return
	 */
	public String delete() {
		// 删除客户，获取客户的信息获取到，上传文件的路径
		customer = customerService.findById(customer.getCust_id());

		// 获取上传文件的路径
		String filepath = customer.getFilepath();

		// 删除客户
		customerService.delete(customer);

		// 再删除文件
		File file = new File(filepath);
		if (file.exists()) {
			file.delete();
		}

		return "delete";
	}

	/**
	 * 跳转到初始化修改的页面
	 * 
	 * @return
	 */
	public String initUpdate() {
		// 默认customer压栈的了，Action默认压栈，model是Action类的书写 getModel(返回customer对象)
		customer = customerService.findById(customer.getCust_id());
		return "initUpdate";
	}

	/**
	 * 修改客户数据
	 * 
	 * @return
	 * @throws IOException
	 */
	public String update() throws IOException {
		// 如果客户上传了新的文件，则删除旧文件并上传新文件

		if (uploadFileName != null && !uploadFileName.trim().isEmpty()) {
			String oldPathString = customer.getFilepath();
			if (oldPathString != null && !oldPathString.trim().isEmpty()) {
				File file = new File(oldPathString);
				if (file.exists()) {
					file.delete();
				}
			}

			// 上传新的文件
			String uuidName = UploadUtils.getUUIDName(uploadFileName);
			File file = new File(PATH + uuidName);
			FileUtils.copyFile(upload, file);
		}

		customerService.update(customer);

		return "update";
	}

	/**
	 * 查询所有的客户
	 * 
	 * @return
	 * @throws IOException 
	 */
	public String findAll() throws IOException {
		List<Customer> list = customerService.findAll();
		
		String jsonString = JSON.toJSONString(list,SerializerFeature.DisableCircularReferenceDetect);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("txt/html;charset=utf-8");
		PrintWriter writer = response.getWriter();
		writer.println(jsonString);
		
		return NONE;

	}
}
