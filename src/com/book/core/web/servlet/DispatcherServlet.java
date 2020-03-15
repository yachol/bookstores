package com.book.core.web.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.core.util.PathUtil;

/**
 * 路由分发器 作用：通过前端url来映射类中的方法
 * 
 * @author yy
 * @version 1.0
 * @param <E>
 *
 */
public class DispatcherServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		// 1.获取扫描的包的路径
		String basepath = PathUtil.getBasepath(config);
		// 2.扫描的包下面的类
		List<String> classnames = PathUtil.getClassname(basepath);
		//3.设计一个保存 路由名 ,类实例的map
		PathUtil.getClassUrlAndInstance(classnames);
		//4.设计一个保存类路由和方法路由 方法名
		HashMap<String,HashMap<String,String>> methodMap = PathUtil.methodMap;
		Set<String> keySet = methodMap.keySet();
		for (String string : keySet) {
			System.out.println(string);
			HashMap<String, String> map = methodMap.get(string);
			for (String string2 : map.keySet()) {
				System.out.println("\t"+string2);
				System.out.println("\t"+map.get(string2));
			}
		}
		
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 可以接受任意请求方式
		String servletPath = req.getServletPath();
//		req.getRequestDispatcher("js/bootstrap.js").forward(req, resp);
//		System.out.println(servletPath);
		String[] urls = servletPath.split("/");
		if(urls.length!=3) {
			resp.sendRedirect("error1");
			return;
		}
		//类路由
		String classUrl="/"+urls[1];
		//方法路由
		String methodUrl="/"+urls[2];
		methodUrl = methodUrl.substring(0,methodUrl.lastIndexOf("."));
		//2.匹配类路由
		Map<String, Object> classMap = PathUtil.classMap;
			if(!classMap.containsKey(classUrl)) {//匹配类路由
				//没找到 
				resp.sendRedirect("error2");
				return;
			}
		//3.类路由存在，匹配方法路由
			HashMap<String,HashMap<String,String>> methodMap = PathUtil.methodMap;
			HashMap<String, String> methodmap2 = methodMap.get(classUrl);
//			System.out.println(methodmap2);
			if(!methodmap2.containsKey(methodUrl)) {
				resp.sendRedirect("error3");
				return;
			}
		//4.匹配成功找到方法名
			String methodName = methodmap2.get(methodUrl);
//			System.out.println(methodName);
		//5.执行该方法
			Object obj = classMap.get(classUrl);
			Class<? extends Object> clazz = obj.getClass();
			try {
				Method method = clazz.getDeclaredMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
				Object invoke = method.invoke(obj, req,resp);
				resp.setContentType("text/html;charset=utf-8");
				if(invoke!=null&&invoke instanceof String) {
					String result =(String)invoke;
					String contextPath = req.getContextPath();
					if(result.startsWith("forward:/")) {
						result = result.substring(result.indexOf("/"));
//						System.out.println("../"+result);
						req.getRequestDispatcher("../"+result).forward(req, resp);;
					}else if(result.startsWith("redirect:/")) {
						result = result.substring(result.indexOf("/"));
						resp.sendRedirect(contextPath+result);
					}else {
						req.getRequestDispatcher("../"+result).forward(req, resp);;
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
	}
}
