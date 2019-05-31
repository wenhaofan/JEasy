package live.autu.简单框架.渲染;

import javax.servlet.ServletContext;

import com.jfinal.template.Engine;

public class 渲染工厂 implements 渲染工厂接口 {

	protected Engine 模板引擎;

	protected ServletContext servletContext;
	protected 默认渲染工厂 _默认渲染工厂;

	// private static final 渲染Factory me = new 渲染Factory();
	// private 渲染Factory() {}

	// public static 渲染Factory me() {
	// return me;
	// }
	@Override
	public void init(Engine 模板引擎, ServletContext servletContext) {
		this.模板引擎 = 模板引擎;

		this.servletContext = servletContext;

		_默认渲染工厂 = new 默认渲染工厂();

	}

	@Override
	public 渲染抽象 get渲染(String view) {
		return _默认渲染工厂.get渲染(view);
	}

	@Override
	public 渲染抽象 get错误渲染(int errorCode, String view) {
		return new 错误渲染(errorCode, view);
	}

	@Override
	public 渲染抽象 get错误渲染(int errorCode) {
		return new 错误渲染(errorCode, null);
	}

	private static class 默认渲染工厂 {
		public 渲染抽象 get渲染(String view) {
			return new 默认渲染(view);
		}
	}
 

}
