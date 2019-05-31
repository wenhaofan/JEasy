package live.autu.简单框架.核心;

import com.jfinal.template.Engine;

/**
 * @author autu
 *
 */
public class 配置 {

	private static Engine 模板引擎=new Engine("简单框架");
	
	private static 动作处理器 _动作处理器=new 动作处理器();

	private static 动作映射 _动作映射;
	
	private static 路由列表 _路由列表=new 路由列表() {
		@Override
		public void 配置() {
		 
		}
	};
	
	
 public static 动作映射 get_动作映射() {
		return _动作映射;
	}

	public static void set_动作映射(动作映射 _动作映射) {
		配置._动作映射 = _动作映射;
	}

public static 动作处理器 get_动作处理器() {
		return _动作处理器;
	}

	public static void set_动作处理器(动作处理器 _动作处理器) {
		配置._动作处理器 = _动作处理器;
	}

	public static 路由列表 get_路由列表() {
		return _路由列表;
	}

	public static void set_路由列表(路由列表 _路由列表) {
		配置._路由列表 = _路由列表;
	}

	public static Engine get模板引擎() {
		return 模板引擎;
	}

	public static void set模板引擎(Engine 模板引擎) {
		配置.模板引擎 = 模板引擎;
	}
 
	
}
