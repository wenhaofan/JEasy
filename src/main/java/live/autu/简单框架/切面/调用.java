package live.autu.简单框架.切面;
 

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import live.autu.简单框架.核心.动作;
import live.autu.简单框架.核心.控制器;
 
/**
 * Invocation is used to invoke the interceptors and the target method
 */
@SuppressWarnings("unchecked")
public class 调用 {
	
	private 动作 动作;
	// private static final Object[] NULL_ARGS = new Object[0];	// Prevent new Object[0] by jvm for paras of 动作 invocation.
 
	private Object 控制器;
	private Method 执行方法;
 
	private 拦截器[] 拦截器数组;
	private Object 返回值;
	
	private int 下标 = 0;
	
	// InvocationWrapper need this constructor
	protected 调用() {
		this.动作 = null;
	}
	
	public 调用(动作 动作, 控制器 控制器) {
		this.动作 = 动作;
		
		拦截器[] 拦截器数组=动作.get拦截器数组();
		if(拦截器数组==null) {
			拦截器数组=new 拦截器[0];
		}
		this.拦截器数组 = 拦截器数组;
		this.控制器 = 控制器;
	 
	}
	
	public 调用(Object 控制器, Method 执行方法, 拦截器[] 拦截器数组) {
		this.动作 = null;
		this.控制器 = 控制器;
		this.执行方法 = 执行方法;
	 
		if(拦截器数组==null) {
			拦截器数组=new 拦截器[0];
		}
		
		this.拦截器数组 = 拦截器数组;
	}
	
	public void 执行() {
		if (下标 < 拦截器数组.length) {
			拦截器数组[下标++].执行(this);
		}
		else if (下标++ == 拦截器数组.length) {	// index++ ensure invoke 动作 only one time
			try {
				// Invoke the 动作
				if (动作 != null) {
					返回值 = 动作.get方法().invoke(控制器);
				}
 
			}
			catch (InvocationTargetException e) {
				Throwable t = e.getTargetException();
				if (t == null) {t = e;}
				throw t instanceof RuntimeException ? (RuntimeException)t : new RuntimeException(t);
			}
			catch (RuntimeException e) {
				throw e;
			}
			catch (Throwable t) {
				throw new RuntimeException(t);
			}
		}
	}
 
	
	/**
	 * Get the target object which be intercepted
	 * <pre>
	 * Example:
	 * OrderService os = getTarget();
	 * </pre>
	 */
	public <T> T get控制器() {
		return (T)控制器;
	}
	
	/**
	 * Return the method of this 动作.
	 * <p>
	 * You can getMethod.getAnnotations() to get annotation on 动作 method to do more things
	 */
	public Method get执行方法方法() {
		if (动作 != null)
			return 动作.get方法();
		return 执行方法;
	}
	
 
	
	/**
	 * Get the return value of the target method
	 */
	public <T> T get返回值() {
		return (T)返回值;
	}
	
	/**
	 * Set the return value of the target method
	 */
	public void set返回值(Object 返回值) {
		this.返回值 = 返回值;
	}
	
	// ---------
	
	/**
	 * Return the controller of this 动作.
	 */
	public 动作 get动作() {
		if (动作 == null)
			throw new RuntimeException("This method can only be used for 动作 interception");
		return 动作;
	}
	
 
	
	/**
	 * Return the controller key.
	 */
	public String get动作关键字() {
		if (动作 == null)
			throw new RuntimeException("This method can only be used for 动作 interception");
		return 动作.get动作关键字();
	}
	
	/**
	 * Return view path of this controller.
	 */
	public String getViewPath() {
		if (动作 == null)
			throw new RuntimeException("This method can only be used for 动作 interception");
		return 动作.get视图路径();
	}
	
	/**
	 * return true if it is 动作 invocation.
	 */
	public boolean is动作调用() {
		return 动作 != null;
	}
}
