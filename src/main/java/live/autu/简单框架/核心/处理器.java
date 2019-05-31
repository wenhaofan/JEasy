package live.autu.简单框架.核心;

import live.autu.简单框架.汉化.HTTP响应;
import live.autu.简单框架.汉化.HTTP请求;

public abstract class 处理器 {
 
	protected 处理器 下一个处理器;
 
 
	public abstract void 处理(String 请求路径, HTTP请求 _服务请求装饰, HTTP响应 _服务响应装饰);
}
