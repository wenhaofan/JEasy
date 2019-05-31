package live.autu.简单框架.核心;

public class 路由 {

	private String 控制器关键字;
	
	private Class<? extends 控制器 > 控制器类;
 
	private String 视图路径;

	public 路由(String 控制器关键字, Class<? extends 控制器> 控制器类, String 视图路径) {
		super();
		this.控制器关键字 = 控制器关键字;
		this.控制器类 = 控制器类;
		this.视图路径 = 视图路径;
	}

	public String get控制器关键字() {
		return 控制器关键字;
	}

	public void set控制器关键字(String 控制器关键字) {
		this.控制器关键字 = 控制器关键字;
	}

	public Class<? extends 控制器> get控制器类() {
		return 控制器类;
	}

	public void set控制器类(Class<? extends 控制器> 控制器类) {
		this.控制器类 = 控制器类;
	}

	public String get视图路径() {
		return 视图路径;
	}

	public void set视图路径(String 视图路径) {
		this.视图路径 = 视图路径;
	}
 
	
	
}
