package com.book.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletConfig;

import com.book.core.annotation.Controller;
import com.book.core.annotation.RequestMapping;


public class PathUtil {
	
	public static final String SRC=PathUtil.class.getClassLoader().getResource("").getPath().substring(1);
	//<类路由，类实例>
	public static Map<String,Object> classMap= new HashMap<>();
	//类路由，《方法路由，方法名》
	public static HashMap<String, HashMap<String,String>> methodMap = new HashMap<>();
	/**
	 * MVC框架下的包扫描路径 ，这个包
	 * @param config
	 * @return
	 */
	public static String getBasepath(ServletConfig config) {
		//1.获取需要扫描的包名web.xml配置的那个配置问价（为了知道扫描哪个包）
			String contextPath = config.getInitParameter("ContextLoadPath");
			
			if(contextPath==null) {//用户未定义配置文件路径
				contextPath=SRC+"servlet.properties";
			}else {//定义了就取出配置稳健的名字
				String[] split = contextPath.split(":");
				if("classpath".equals(split[0])) {//写错了
					contextPath=SRC+split[1];
				}
			}
//			System.out.println(contextPath);
			Properties pro = new Properties();
			try {
				pro.load(new FileInputStream(contextPath));
				return pro.getProperty("auto.scanner.package");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
	}
	/**
	 * 这个包下的所有完整类名
	 * @param basepath
	 * @return
	 */
	public static List<String> getClassname(String basepath) {
		String packpath=PathUtil.SRC+basepath.replace(".", "/");
		File file = new File(packpath);
		List<String> list = new ArrayList<String>();
		if(file.exists()) {
			File[] files = file.listFiles();
			for (File f : files) {
				String className=f.getName();
				className=className.substring(0,className.lastIndexOf("."));
				String classPath=basepath+"."+className;
				list.add(classPath);
			}
		}
		return list.size()>0?list:null;
	}
	/**
	 * 这个包下的所有类路由 类实例 方法路由 方法名
	 * @param classnames
	 */
	public static void getClassUrlAndInstance(List<String> classnames){
		if(classnames==null) {
			return;
		}
		for (String classname : classnames) {
			try {
				Class<?> clazz = Class.forName(classname);
				Object object = clazz.newInstance();
				//找出@controller的 reuestMapping的路由名
				if(clazz.isAnnotationPresent(Controller.class)&&clazz.isAnnotationPresent(RequestMapping.class)) {
					RequestMapping annotation = clazz.getAnnotation(RequestMapping.class);
					String classUrl = annotation.value();
					if(classMap.containsKey(classUrl)) {
						throw new RuntimeException(classUrl+"is no unique");
					}else {
						classMap.put(classUrl, object);
					}
					//找方法路由和方法名
					Method[] methods = clazz.getDeclaredMethods();
					HashMap<String,String> urlName = new HashMap<String,String>();
					for(Method m:methods) {
						if(m.isAnnotationPresent(RequestMapping.class)) {
							RequestMapping annotation2 = m.getAnnotation(RequestMapping.class);
							String methodUrl = annotation2.value();
							String methodName = m.getName();
							urlName.put(methodUrl,methodName);
						}
					}
					methodMap.put(classUrl,urlName);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
