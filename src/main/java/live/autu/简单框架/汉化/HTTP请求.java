package live.autu.简单框架.汉化;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class HTTP请求 extends HttpServletRequestWrapper {

	public HTTP请求(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
	}

	public void 添加属性(String key,Object val) {
		this.setAttribute(key, val);
	}
	
}
