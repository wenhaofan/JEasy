package live.autu.简单框架.核心;

import java.lang.reflect.Method;

import live.autu.简单框架.切面.拦截器;

public class 动作 {

	private Class<? extends 控制器> 控制器;
	
	private String 动作关键字;

	private Method 方法;
		
	private String 方法关键字;
	
	private String 视图路径;

	private 拦截器[] 拦截器数组;
	
	public 动作(Class<? extends 控制器> 控制器, String 动作关键字, Method 方法, String 方法关键字, String 视图路径,拦截器[] 拦截器数组) {
		super();
		this.控制器 = 控制器;
		this.动作关键字 = 动作关键字;
		this.方法 = 方法;
		this.方法关键字 = 方法关键字;
		this.视图路径 = 视图路径;
		this.拦截器数组 = 拦截器数组;
	}

	public Class<? extends 控制器> get控制器() {
		return 控制器;
	}

	public void set控制器(Class<? extends 控制器> 控制器) {
		this.控制器 = 控制器;
	}

 
	
	public String get动作关键字() {
		return 动作关键字;
	}

	public void set动作关键字(String 动作关键字) {
		this.动作关键字 = 动作关键字;
	}

	public String get方法关键字() {
		return 方法关键字;
	}

	public void set方法关键字(String 方法关键字) {
		this.方法关键字 = 方法关键字;
	}

	public Method get方法() {
		return 方法;
	}

	public void set方法(Method 方法) {
		this.方法 = 方法;
	}
 
	
	
	public String get视图路径() {
		return 视图路径;
	}

	public void set视图路径(String 视图路径) {
		this.视图路径 = 视图路径;
	}

	public 拦截器[] get拦截器数组() {
		return 拦截器数组;
	}

	public void set拦截器数组(拦截器[] 拦截器数组) {
		this.拦截器数组 = 拦截器数组;
	}
 
}
