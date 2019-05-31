
package live.autu.简单框架.核心;

import java.util.List;

import javax.servlet.ServletContext;

import live.autu.简单框架.工具.路径工具;
import live.autu.简单框架.渲染.渲染管理;

/**
 * JFinal
 */
public final class 简单框架 {

	private 动作映射 _动作映射;
	private 处理器 _处理器;
	private ServletContext servletContext;
	private String contextPath = "";

	private static final 简单框架 实例 = new 简单框架();

	private 简单框架() {
	}

	public static 简单框架 实例() {
		return 实例;
	}

	public void init(简单框架配置 _简单框架配置, ServletContext servletContext) {
		this.servletContext = servletContext;
		this.contextPath = servletContext.getContextPath();

		初始化路径工具类();

		初始化动作映射();
		初始化处理器();
		初始化渲染();

	}

	private void 初始化处理器() {
		动作处理器 _动作处理器 = 配置.get_动作处理器();
		if (_动作处理器 == null) {
			_动作处理器 = new 动作处理器();
		}
 
		this._处理器=_动作处理器;
	}

 

	private void 初始化路径工具类() {
		String 路径 = servletContext.getRealPath("/");
	 
		路径工具.setWEB根路径(路径);
	}

	private void 初始化渲染() {
		渲染管理.实例.初始化(配置.get模板引擎(), servletContext);
	}

	private void 初始化动作映射() {
		_动作映射 = new 动作映射(配置.get_路由列表());
		_动作映射.构建动作映射();
	 
	}

 
	public 处理器 get处理器() {
		return _处理器;
	}

 
	public String getContextPath() {
		return contextPath;
	}

	public ServletContext getServletContext() {
		return this.servletContext;
	}

	public 动作 获取动作(String 请求路径, String[] 路径参数数组) {
		return _动作映射.获取动作(请求路径, 路径参数数组);
	}

	public List<String> getAllActionKeys() {
		return _动作映射.get全部动作关键字();
	}

}
