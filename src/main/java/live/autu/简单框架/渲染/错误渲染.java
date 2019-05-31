package live.autu.简单框架.渲染;

import java.io.IOException;
import java.io.PrintWriter;

public class 错误渲染 extends 渲染抽象 {

	protected static final String contentType = "text/html; charset=" + get编码();

	protected static final String version = "<center><a href='http://www.jfinal.com?f=ev- ' target='_blank'><b>Powered by JFinal  </b></a></center>";

	protected static final String html404 = "<html><head><title>404 Not Found</title></head><body bgcolor='white'><center><h1>404 Not Found</h1></center><hr>"
			+ version + "</body></html>";
	protected static final String html500 = "<html><head><title>500 Internal Server Error</title></head><body bgcolor='white'><center><h1>500 Internal Server Error</h1></center><hr>"
			+ version + "</body></html>";

	protected static final String html400 = "<html><head><title>400 Bad Request</title></head><body bgcolor='white'><center><h1>400 Bad Request</h1></center><hr>"
			+ version + "</body></html>";
	protected static final String html401 = "<html><head><title>401 Unauthorized</title></head><body bgcolor='white'><center><h1>401 Unauthorized</h1></center><hr>"
			+ version + "</body></html>";
	protected static final String html403 = "<html><head><title>403 Forbidden</title></head><body bgcolor='white'><center><h1>403 Forbidden</h1></center><hr>"
			+ version + "</body></html>";

	protected int 错误码;

	public 错误渲染(int 错误码, String 视图) {
			this.错误码 = 错误码;
			this.视图 = 视图;
		}

	@Override
	public void 渲染() {
		响应.setStatus(get错误码()); // HttpServletResponse.SC_XXX_XXX

		// render with view
		String 视图 = get视图();
		if (视图 != null) {
			渲染管理.me().get渲染工厂().get渲染(视图).set上下文(请求, 响应).渲染();
			return;
		}

		// render with html content
		PrintWriter writer = null;
		try {
			响应.setContentType(contentType);
			writer = 响应.getWriter();
			writer.write(get错误页面());
			writer.flush();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public String get错误页面() {
		int errorCode = get错误码();
		if (errorCode == 404)
			return html404;
		if (errorCode == 500)
			return html500;
		if (errorCode == 400)
			return html400;
		if (errorCode == 401)
			return html401;
		if (errorCode == 403)
			return html403;
		return "<html><head><title>" + errorCode + " Error</title></head><body bgcolor='white'><center><h1>" + errorCode
				+ " Error</h1></center><hr>" + version + "</body></html>";
	}

	public int get错误码() {
		return 错误码;
	}

}
