package live.autu.简单框架.渲染;

import live.autu.简单框架.汉化.HTTP响应;
import live.autu.简单框架.汉化.HTTP请求;
import live.autu.简单框架.配置.配置;

 

public abstract class 渲染抽象 {
	
	protected String 视图;
	protected HTTP请求 请求;
	protected HTTP响应 响应;
	
	private static String 编码 = 配置.默认_编码;
	private static boolean 开发模式 = 配置.默认_开发模式;
 
	public static String get编码() {
		return 编码;
	}

	public static void set编码(String 编码) {
		渲染抽象.编码 = 编码;
	}

	public static boolean is开发模式() {
		return 开发模式;
	}

	public static void set开发模式(boolean 开发模式) {
		渲染抽象.开发模式 = 开发模式;
	}

	public 渲染抽象 set上下文(HTTP请求 请求, HTTP响应 响应) {
		this.请求 = 请求;
		this.响应 = 响应;
		return this;
	}
	
	public 渲染抽象 set上下文(HTTP请求 请求, HTTP响应 响应, String 视图路径) {
		this.请求 = 请求;
		this.响应 = 响应;
		if (视图 != null && 视图.length() > 0 && 视图.charAt(0) != '/') {
			视图 = 视图路径 + 视图;
		}
		return this;
	}
	
	public String get视图() {
		return 视图;
	}
	
	public void set视图(String 视图) {
		this.视图 = 视图;
	}
	
	/**
	 * Render to client
	 */
	public abstract void 渲染();
	
}
