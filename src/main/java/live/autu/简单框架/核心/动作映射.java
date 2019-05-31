package live.autu.简单框架.核心;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import live.autu.简单框架.注解.不是动作;

public class 动作映射 {

	protected static final String SLASH = "/";
	
	protected 路由列表 _路由列表;
	protected Map<String, 动作> 映射 = new HashMap<String, 动作>(2048, 0.5F);
	
	public 动作映射(路由列表 _路由列表) {
		this._路由列表 = _路由列表;
	}
	
	protected Set<String> 构建排除方法名() {
		Set<String> 排除方法名集合 = new HashSet<String>();
		Method[] 方法数组 = 控制器.class.getMethods();
		for (Method 方法 : 方法数组) {
			// if (m.getParameterTypes().length == 0)
			排除方法名集合.add(方法.getName());
		}
		return 排除方法名集合;
	}
	
	protected List<路由列表> get路由列表集合() {
		List<路由列表> 路由列表集合 = 路由列表.get路由列表集合();
		List<路由列表> 返回值 = new ArrayList<路由列表>(路由列表集合.size() + 1);
		返回值.add(_路由列表);
		返回值.addAll(路由列表集合);
		return 返回值;
	}
	
	protected void 构建动作映射() {
		映射.clear();
		Set<String> 排除方法名集合 = 构建排除方法名();
	
		List<路由列表> _路由列表=get路由列表集合();
		 
		for (路由列表 临时路由集合 : _路由列表) {
		for (路由 临时路由 : 临时路由集合.get路由集合()) {
			Class<? extends 控制器> 控制器类 = 临时路由.get控制器类();
		 
			boolean 是否为控制器子类 = (控制器类.getSuperclass() == 控制器.class);
			Method[] 控制器方法数组 = (是否为控制器子类 ? 控制器类.getDeclaredMethods() : 控制器类.getMethods());
			for (Method 临时控制器方法 : 控制器方法数组) {
				String 方法名 = 临时控制器方法.getName();
				if (排除方法名集合.contains(方法名) /* || method.getParameterTypes().length != 0 */)
					continue ;
				if (是否为控制器子类 && !Modifier.isPublic(临时控制器方法.getModifiers()))
					continue ;
				if (临时控制器方法.getAnnotation(不是动作.class) != null)
					continue ;
				
				//Interceptor[] actionInters = interMan.buildControllerActionInterceptor(routes.getInterceptors(), controllerInters, controllerClass, method);
				String 控制器关键字 = 临时路由.get控制器关键字();
				
				 
				String 动作关键字;
				if (方法名.equals("默认")) {
					动作关键字 = 控制器关键字;
				}
				else {
					动作关键字 = 控制器关键字.endsWith(SLASH) ? 控制器关键字 + 方法名 : 控制器关键字 + SLASH + 方法名;
				}
				
				动作 _动作 = new 动作(控制器类, 动作关键字, 临时控制器方法, 方法名,临时路由.get视图路径(),null);
				if (映射.put(动作关键字, _动作) != null) {
					throw new RuntimeException();
				}
			}
			}
		}
		
		_路由列表.clear();
		
		// support url = controllerKey + urlParas with "/" of controllerKey
		动作 action = 映射.get("/");
		if (action != null) {
			映射.put("", action);
		}
	}
 
	 
	public 动作 获取动作(String 请求路径, String[] 路径参数数组) {
		动作 _动作 = 映射.get(请求路径);
		if (_动作 != null) {
			return _动作;
		}
		
		// --------
		int i = 请求路径.lastIndexOf('/');
		if (i != -1) {
			_动作 = 映射.get(请求路径.substring(0, i));
			路径参数数组[0] = 请求路径.substring(i + 1);
		}
		
		return _动作;
	}
	
	public List<String> get全部动作关键字() {
		List<String> 全部动作关键字 = new ArrayList<String>(映射.keySet());
		Collections.sort(全部动作关键字);
		return 全部动作关键字;
	}
}
