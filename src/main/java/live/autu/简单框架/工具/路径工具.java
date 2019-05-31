package live.autu.简单框架.工具;

import java.io.File;

public class 路径工具 {

	private static String WEB根路径;
	
	public static void setWEB根路径(String webRootPath) {
		if (webRootPath == null) {
			return ;
		}
		
		if (webRootPath.endsWith(File.separator)) {
			webRootPath = webRootPath.substring(0, webRootPath.length() - 1);
		}
		路径工具.WEB根路径 = webRootPath;
	}
	
	public static String getWEB根路径() {
		return WEB根路径;
	}
	
}
