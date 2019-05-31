package live.autu.简单框架.核心;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import live.autu.简单框架.汉化.HTTP响应;
import live.autu.简单框架.汉化.HTTP请求;

public class 入口拦截器 implements Filter  {
	
	private 处理器 处理器;
	private String 编码="UTF-8";
	private 简单框架配置 _简单框架配置;
 
 
	private int 内容路径长度;
	
	public 入口拦截器() {
		this._简单框架配置 = null;
	}
	
	/**
	 * 支持 web 项目无需 web.xml 配置文件，便于嵌入式整合 jetty、undertow
	 */
	public 入口拦截器(简单框架配置 框架配置) {
		this._简单框架配置 = 框架配置;
	}
	
	public void init(FilterConfig 拦截器配置) throws ServletException {
		if (_简单框架配置 == null) {
			创建简单框架配置(拦截器配置.getInitParameter("configClass"));
		}
	 
		_简单框架配置.configRoute(配置.get_路由列表());
		
		配置.set_动作映射(new 动作映射(配置.get_路由列表()));
		配置.get_动作映射().构建动作映射();
		配置.get_动作处理器().init(配置.get_动作映射());
		String contextPath = 拦截器配置.getServletContext().getContextPath();
		内容路径长度 = (contextPath == null || "/".equals(contextPath) ? 0 : contextPath.length());
	 
		简单框架.实例().init(_简单框架配置, 拦截器配置.getServletContext());
		
		处理器 = 简单框架.实例().get处理器();	// 开始接受请求
	}
	
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		request.setCharacterEncoding(编码);
		
		String 请求路径 =java.net.URLDecoder.decode(new String(request.getRequestURI().getBytes("ISO-8859-1"), "UTF-8"), "UTF-8"); 
		if (内容路径长度 != 0) {
			请求路径 = 请求路径.substring(内容路径长度);
		}
	 
		try {
			处理器.处理(请求路径,new HTTP请求(request) , new HTTP响应(response));
		}
		catch (Exception e) {
		 e.printStackTrace();
		}
		
 
	}
	
	public void destroy() {
 
	}
	
	protected void 创建简单框架配置(String 配置类) {
		if (配置类 == null) {
			throw new RuntimeException("Please set configClass parameter of JFinalFilter in web.xml");
		}
		
		Object 临时类 = null;
		try {
			临时类 = Class.forName(配置类).newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Can not create instance of class: " + 配置类, e);
		}
		
		if (临时类 instanceof 简单框架配置) {
			_简单框架配置 = (简单框架配置)临时类;
		} else {
			throw new RuntimeException("Can not create instance of class: " + 配置类 + ". Please check the config in web.xml");
		}
	}
	
 
}
