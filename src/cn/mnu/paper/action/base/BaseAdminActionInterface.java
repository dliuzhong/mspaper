package cn.mnu.paper.action.base;

import cn.mnu.paper.service.AdminManager;

import com.opensymphony.xwork2.Action;


public class BaseAdminActionInterface implements Action {
	protected AdminManager amdinmgr;

	public void setAmdinmgr(AdminManager amdinmgr)
	{
		this.amdinmgr = amdinmgr;
	}

	public String execute() throws Exception
	{
		return SUCCESS;
	}
}