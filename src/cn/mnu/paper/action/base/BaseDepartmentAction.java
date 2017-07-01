package cn.mnu.paper.action.base;

import cn.mnu.paper.service.DepartmentManager;
import cn.mnu.paper.service.MsuserManager;

import com.opensymphony.xwork2.ActionSupport;
/***
 * 基础DepartmentAction
 * @author mdl
 * @version 1.0
 * @date 2014-08-13
 */
public class BaseDepartmentAction extends ActionSupport {
	// 业务逻辑组件
	protected DepartmentManager departmentmgr;
	protected MsuserManager msusermgr;
	
	public String execute() throws Exception {
		return SUCCESS;
	}

	public void setDepartmentmgr(DepartmentManager departmentmgr) {
		this.departmentmgr = departmentmgr;
	}

	public void setMsusermgr(MsuserManager msusermgr) {
		this.msusermgr = msusermgr;
	}
}
