package live.autu.简单框架.渲染;

import javax.servlet.ServletContext;

import com.jfinal.template.Engine;

public class 渲染管理 {

	public static 渲染管理 实例=new 渲染管理();
	
	private Engine 模板引擎;
 
	private ServletContext servletContext;
	private 渲染工厂接口 _渲染工厂 = null;
	
	private static final 渲染管理 me = new 渲染管理();
	private 渲染管理() {}
	
	public static 渲染管理 me() {
		return me;
	}
	
	public 渲染工厂接口 get渲染工厂() {
		return _渲染工厂;
	}
	
	public void setRenderFactory(渲染工厂接口 _渲染工厂) {
		if (_渲染工厂 == null) {
			throw new IllegalArgumentException("renderFactory can not be null");
		}
		this._渲染工厂 = _渲染工厂;
	}
	
	public void 初始化(Engine 模板引擎 , ServletContext servletContext) {
		this.模板引擎 = 模板引擎;
		 
		this.servletContext = servletContext;
 
		initTemplateRender();
 
		// create renderFactory
		if (_渲染工厂 == null) {
			_渲染工厂 = new 渲染工厂();
		}
		_渲染工厂.init(模板引擎, servletContext);
	}
	
	private void initTemplateRender() {
		默认渲染.初始化(模板引擎);
	}
	
 
	public ServletContext getServletContext() {
		return servletContext;
	}
}
