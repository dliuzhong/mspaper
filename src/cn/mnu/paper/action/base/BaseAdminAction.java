package cn.mnu.paper.action.base;

import cn.mnu.paper.service.AdminManager;

import com.opensymphony.xwork2.ActionSupport;

/***
 * ����AdminAction
 * @author mdl
 * @version 1.0
 * @date 2014-07-12
 */
public class BaseAdminAction extends ActionSupport {
	// ҵ���߼����
	protected AdminManager adminmgr;

	public void setAdminmgr(AdminManager adminmgr) {
		this.adminmgr = adminmgr;
	}

	public String execute() throws Exception
	{
		return SUCCESS;
	}
}
