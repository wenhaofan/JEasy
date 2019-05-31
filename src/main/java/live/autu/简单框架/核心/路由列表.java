package live.autu.简单框架.核心;

import java.util.ArrayList;
import java.util.List;

import live.autu.简单框架.切面.拦截器;

public abstract class 路由列表 {

	private static List<路由列表> 路由列表集合 = new ArrayList<>();

	private List<路由> 路由集合 = new ArrayList<>();

	private List<拦截器> 拦截器集合 = new ArrayList<拦截器>();

	private String 基础视图路径 = null;

	public abstract void 配置();

	public 路由列表 添加路由列表(路由列表 _路由列表) {
		路由列表.路由列表集合.add(_路由列表);
		return this;
	}

	public 路由列表 添加路由(String 控制器关键字, Class<? extends 控制器> 控制器类) {
		return 添加路由(控制器关键字, 控制器类, null);
	}

	public 路由列表 添加路由(String 控制器关键字, Class<? extends 控制器> 控制器类, String 视图路径) {
		路由集合.add(new 路由(控制器关键字, 控制器类, 视图路径));
		return this;
	}

	public String get基础视图路径() {
		return 基础视图路径;
	}

	public void set基础视图路径(String 基础视图路径) {
		this.基础视图路径 = 基础视图路径;
	}

	public List<拦截器> get拦截器集合() {
		return 拦截器集合;
	}

	public void set拦截器集合(List<拦截器> 拦截器集合) {
		this.拦截器集合 = 拦截器集合;
	}

	public static List<路由列表> get路由列表集合() {
		return 路由列表集合;
	}

	public static void set路由列表集合(List<路由列表> 路由列表集合) {
		路由列表.路由列表集合 = 路由列表集合;
	}

	public List<路由> get路由集合() {
		return 路由集合;
	}

	public void set路由集合(List<路由> 路由集合) {
		this.路由集合 = 路由集合;
	}

	public void clear() {

		this.路由集合 = null;

		this.拦截器集合 = null;

		this.基础视图路径 = null;
	}

}
