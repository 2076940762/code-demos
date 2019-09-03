package download;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

public class downloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取文件名
		String name = request.getParameter("name");
		
		//转换文件名编码
		String filename =new String(name.getBytes("iso-8859-1"),"utf-8");

		// 获取文件mime类型
		ServletContext context = this.getServletContext();
		String mimeType = context.getMimeType(filename);

		// 设置文件类型
		response.setContentType(mimeType);
		response.setHeader("content-disposition", "attachment;filename=" + filename);//常见浏览器支持utf-8编码

		ServletOutputStream os = response.getOutputStream();
		InputStream is = context.getResourceAsStream("/downloadRes/" + name);
		byte[] buffer = new byte[1024 * 4];
		int len = -1;

//		while ((len = is.read(buffer)) != -1) {
//			os.write(buffer, 0, len);
//		}
		
		//使用commons.io复制数据
		IOUtils.copy(is, os);
		
		is.close();
		os.close();

	}

}
