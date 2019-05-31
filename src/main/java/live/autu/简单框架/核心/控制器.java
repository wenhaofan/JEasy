package live.autu.简单框架.核心;

import live.autu.简单框架.汉化.HTTP响应;
import live.autu.简单框架.汉化.HTTP请求;
import live.autu.简单框架.渲染.渲染抽象;
import live.autu.简单框架.渲染.渲染管理;

/**
 * 旧时王谢堂前燕，飞入寻常百姓家。
 * @author 范文皓
 */
public class 控制器 {

	private HTTP请求 请求;
	
	private HTTP响应 响应;
	
	private 动作 _动作;
	
	private 渲染抽象 渲染;
	
	private String 路径参数;
	
	private static final 渲染管理 _渲染管理 =渲染管理.实例;
	
	void 初始化(动作 _动作, HTTP请求 请求, HTTP响应 响应,String 路径参数) {
		this._动作 = _动作;
		this.请求 = 请求;
		this.响应 = 响应;
   
		this.路径参数=路径参数;
	}

	public 控制器 设置属性(String 关键字,Object 值) {
		请求.添加属性(关键字, 值);
		return this;
	}
	
	public String 参数(String 关键字) {
		return 请求.getParameter(关键字);
	}
	
	public 渲染抽象 获取渲染() {
		return 渲染;
	}

	public void 设置渲染(渲染抽象 渲染) {
		this.渲染 = 渲染;
	}

	public HTTP请求 请求() {
		return 请求;
	}

	public void 设置请求(HTTP请求 请求) {
		this.请求 = 请求;
	}

	public HTTP响应 响应() {
		return 响应;
	}

	public void 设置响应(HTTP响应 响应) {
		this.响应 = 响应;
	}
	
	public void 渲染(String 视图) {
		渲染 = _渲染管理.get渲染工厂().get渲染(视图);
	}
}
