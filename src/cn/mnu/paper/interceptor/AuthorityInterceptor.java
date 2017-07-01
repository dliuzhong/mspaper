package cn.mnu.paper.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthorityInterceptor extends AbstractInterceptor {

	// 拦截用户请求
	public String intercept(ActionInvocation invocation) throws Exception {
		
		// 取得跟踪用户的HTTP session
		Map<String, Object> session = ActionContext.getContext().getSession();
		HttpServletRequest request=ServletActionContext.getRequest();
		String path = request.getRequestURI().toUpperCase();
		Object msuserid = session.get("msuserid");
		Object adminid = session.get("adminid");
		Object adminGrade = session.get("adminGrade");
		//System.out.println("AuthorityInterceptor");
		// 检查URL
		// 投稿用户
		//System.out.println("path" +  path);
		if (path.indexOf("MSUSER/MAIN") != -1) {
			//System.out.println("msuser/main" + path);
			if (msuserid == null) {
				return "msuser_login";
			} else {
				return invocation.invoke();
			}
		// 管理员用户
		} else if (path.indexOf("ADMIN/MAIN") != -1) {
			//System.out.println("admin/main" + path);
			if (adminid == null) {
				return "admin_login";
			} else {
				// 3，2
				if (path.indexOf("EDIT") != -1) {
					if (adminGrade != null && Integer.parseInt(adminGrade.toString()) == 1) {
						return "admin_login";
					} else {
						return invocation.invoke();
					}
					// 3
				} else if (path.indexOf("SYS")!= -1) {
					if (adminGrade != null && (Integer.parseInt(adminGrade.toString()) == 1 || Integer.parseInt(adminGrade.toString()) == 2)) {
						return "admin_login";
					} else {
						return invocation.invoke();
					}
					// 2,3
				} else if (path.indexOf("ARTICLE") != -1) {
					if (adminGrade != null && (Integer.parseInt(adminGrade.toString()) == 1)) {
						return "admin_login";
					} else {
						return invocation.invoke();
					} 
				} else {
					return invocation.invoke();
				}
			}
		} else {
			return invocation.invoke();
		}

	}

}
