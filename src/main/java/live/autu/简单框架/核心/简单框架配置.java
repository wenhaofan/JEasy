package live.autu.简单框架.核心;

public abstract class 简单框架配置 {

		
		/**
		 * Config route
		 */
		public abstract void configRoute(路由列表 路由);
		
 
		/**
		 * Call back after JFinal start
		 */
		public void afterStart(){}
		
		/**
		 * Call back before JFinal stop
		 */
		public void beforeStop(){}
		


}
