package live.autu.简单框架.渲染;

import javax.servlet.ServletContext;

import com.jfinal.template.Engine;

public interface 渲染工厂接口 {

	public void init(Engine 模板引擎 , ServletContext servletContext) ;

	public 渲染抽象 get渲染(String view);
	
	public 渲染抽象 get错误渲染(int errorCode, String view);
	
	public 渲染抽象 get错误渲染(int errorCode);
	
}
