package live.autu.简单框架.核心;

import live.autu.简单框架.切面.调用;
import live.autu.简单框架.汉化.HTTP响应;
import live.autu.简单框架.汉化.HTTP请求;
import live.autu.简单框架.渲染.渲染抽象;
import live.autu.简单框架.渲染.渲染管理;

public class 动作处理器 extends 处理器 {
 

	protected boolean 开发模式;
 
	protected 动作映射 _动作映射;
 	protected static final 渲染管理 _渲染管理 = 渲染管理.实例;
	 
	protected void init(动作映射 _动作映射) {
		this._动作映射 = _动作映射;
	}
	
	/**
	 * handle
	 * 1: Action action = actionMapping.getAction(target)
	 * 2: new Invocation(...).invoke()
	 * 3: render(...)
	 */
	@Override
	public void 处理(String 请求路径, HTTP请求 请求, HTTP响应  响应) {
		if (请求路径.indexOf('.') != -1) {
			return ;
		}
	 
		String[] 路径参数数组 = {null};
		动作 _动作 = _动作映射.获取动作(请求路径, 路径参数数组);
		
		if (_动作 == null) {
			_渲染管理.get渲染工厂().get错误渲染(404).set上下文(请求, 响应).渲染();
			return ;
		}
		
		控制器 _控制器 = null;
		try {
			// Controller controller = action.getControllerClass().newInstance();
			_控制器 = _动作.get控制器().newInstance();
		 
			_控制器.初始化(_动作, 请求, 响应, 路径参数数组[0]);
			 
		  
			 new 调用(_动作, _控制器).执行();
			 
			渲染抽象 渲染 = _控制器.获取渲染();
		 
			渲染.set上下文(请求, 响应, _动作.get视图路径()+渲染.get视图()).渲染();;
		}
	 
		catch (Exception e) {
			e.printStackTrace();
			_渲染管理.get渲染工厂().get错误渲染(500).set上下文(请求, 响应).渲染();
		} finally {
			if (_控制器 != null) {
				//_控制器._clear_();
			}
		}
	}
	
	 
}
