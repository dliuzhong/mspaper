package cn.mnu.paper.action.base;

import cn.mnu.paper.service.AdminManager;

import com.opensymphony.xwork2.ActionSupport;

/***
 * 基础AdminAction
 * @author mdl
 * @version 1.0
 * @date 2014-07-12
 */
public class BaseAdminAction extends ActionSupport {
	// 业务逻辑组件
	protected AdminManager adminmgr;

	public void setAdminmgr(AdminManager adminmgr) {
		this.adminmgr = adminmgr;
	}

	public String execute() throws Exception
	{
		return SUCCESS;
	}
}
