package web.servlet.admin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import domain.Category;
import domain.Product;
import service.ProductService;
import service.impl.ProductServiceImpl;

public class AddProductServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// 存放商品属性的map
			HashMap<String, Object> map = new HashMap<String, Object>();

			// 创建磁盘文件项
			DiskFileItemFactory factory = new DiskFileItemFactory();

			// 创建文件上传对象
			ServletFileUpload upload = new ServletFileUpload(factory);

			// 解析request
			List<FileItem> list = upload.parseRequest(request);

			for (FileItem item : list) {
				if (item.isFormField()) {
					// 普通上传组件
					map.put(item.getFieldName(), item.getString("utf-8"));

				} else {
					// 文件上传组件
					String name = item.getName();
					System.out.println("name" + name);

					// 获取文件真实名称 abc.jpg
					name = name.substring(name.lastIndexOf("\\") + 1);

					// 获取图片存放的路径
					// Returns a reference to the ServletContext in which this servletis running.
					// Returns a String containing the real path for a givenvirtual path.
					String path = this.getServletContext().getRealPath("/products/1");
					System.out.println("path" + path);

					// 输出流
					String uuidName = UUID.randomUUID().toString().replace("-", "").substring(0, 32).toLowerCase()
							+ name;
					File outfile = new File(path, uuidName);
					FileOutputStream outstrem = new FileOutputStream(outfile);
					InputStream inputStream = item.getInputStream();

					// 缓冲区
					byte[] buffer = new byte[1024 * 4];

					int len = -1;
					// 复制
					while ((len = inputStream.read(buffer)) != -1) {
						outstrem.write(buffer, 0, len);
					}

					inputStream.close();
					outstrem.close();

					// 路径存入map
					map.put("pimage", "/products/1/" + uuidName);

					// 删除零时文件
					item.delete();
				}
			} // for

			// 封装数据
			Product product = new Product();
			BeanUtils.populate(product, map);

			// pid
			product.setPid(UUID.randomUUID().toString().replace("-", "").substring(0, 32).toUpperCase());

			// pdate
			product.setPdate(new Date());

			// category
			Category category = new Category();
			category.setCid((String) map.get("cid"));

			product.setCategory(category);

			// 调用service完成数据入库
			ProductService service = new ProductServiceImpl();
			service.add(product);

			resp.sendRedirect(request.getContextPath()+"/adminProduct?method=findAll");
			return;
		} catch (Exception e) {
			request.setAttribute("msg", "添加商品失败");
			request.getRequestDispatcher("/jsp/msg.jsp").forward(request, resp);
			e.printStackTrace();
			return;
		} // try
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
