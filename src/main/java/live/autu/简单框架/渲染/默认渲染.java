package live.autu.简单框架.渲染;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import com.jfinal.template.Engine;

import live.autu.简单框架.工具.路径工具;

public class 默认渲染 extends 渲染抽象 {

	protected static Engine 模板引擎;

	private static final String 响应类型 = "text/html; charset=" + get编码();

	static void 初始化(Engine 模板引擎) {
		if (模板引擎 == null) {
			throw new IllegalArgumentException("engine can not be null");
		}
		默认渲染.模板引擎 = 模板引擎;
	}

	public 默认渲染(String 视图) {
		this.视图 = 视图;
	}

	public String get响应类型() {
		return 响应类型;
	}

	public void 渲染() {
		响应.setContentType(get响应类型());

		Map<Object, Object> data = new HashMap<Object, Object>();
		for (Enumeration<String> attrs = 请求.getAttributeNames(); attrs.hasMoreElements();) {
			String attrName = attrs.nextElement();
			data.put(attrName, 请求.getAttribute(attrName));
		}

		try {
			OutputStream os = 响应.getOutputStream();
			模板引擎.getTemplate(路径工具.getWEB根路径()+get视图()).render(data, os);
		} catch (RuntimeException e) { // 捕获 ByteWriter.close() 抛出的 RuntimeException
			Throwable cause = e.getCause();
			if (cause instanceof IOException) { // ClientAbortException、EofException 直接或间接继承自 IOException
				String name = cause.getClass().getSimpleName();
				if ("ClientAbortException".equals(name) || "EofException".equals(name)) {
					return;
				}
			}

			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			// throw new RenderException(e);
		}
	}

}
