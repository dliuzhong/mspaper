package cn.mnu.paper.action.base;

import cn.mnu.paper.service.DepartmentManager;
import cn.mnu.paper.service.MsuserManager;

import com.opensymphony.xwork2.ActionSupport;

/***
 * ����UserAction
 * @author mdl
 * @version 1.0
 * @date 2014-08-12
 */
public class BaseMsuserAction extends ActionSupport {
	// ҵ���߼����
	protected MsuserManager msusermgr;
	protected DepartmentManager departmentmgr;

	public String execute() throws Exception
	{
		return SUCCESS;
	}


	public void setMsusermgr(MsuserManager msusermgr) {
		this.msusermgr = msusermgr;
	}


	public void setDepartmentmgr(DepartmentManager departmentmgr) {
		this.departmentmgr = departmentmgr;
	}


}
