package com.neusoft.elmweb.framework;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("请求经过");
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		
		//获取请求路径
		String path = request.getServletPath();
		//截取路径
		String className = path.substring(1,path.lastIndexOf("/"));
		String methodName = path.substring(path.lastIndexOf("/") + 1);
		PrintWriter pw = null;
		//*反射*
		try {
			//获取对应类的类型
			Class<?> clazz = Class.forName("com.neusoft.elmweb.controller." + className);
			//创建对应类的对象
			Object controller = clazz.newInstance();
			//获取controller中的方法
			Method method = clazz.getMethod(methodName, new Class[] {HttpServletRequest.class});
			//调用controller中的方法
			Object result = method.invoke(controller, new Object[] {request});
			//将controller中的结果返回前台
			pw = response.getWriter();	
			ObjectMapper om = new ObjectMapper();
			pw.print(om.writeValueAsString(result));
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(pw != null) {
				pw.close();
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
