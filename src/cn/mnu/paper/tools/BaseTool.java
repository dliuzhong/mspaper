package cn.mnu.paper.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseTool {
	public static Date getNowDateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date nowDateTime = null;
		try {
			nowDateTime =  sdf.parse(sdf.format(new Date()));
		} catch(Exception e) {
			e.printStackTrace();
		}
		return nowDateTime;
	}
}
