package cn.mnu.paper.action.base;

import cn.mnu.paper.service.AdminManager;
import cn.mnu.paper.service.PaperManager;

import com.opensymphony.xwork2.ActionSupport;
/***
 * ����PaperAction
 * @author mdl
 * @version 1.0
 * @date 2014-07-12
 */
public class BasePaperAction extends ActionSupport {
	// ҵ���߼����
	protected PaperManager papermgr;


	public String execute() throws Exception {
		return SUCCESS;
	}


	public void setPapermgr(PaperManager papermgr) {
		this.papermgr = papermgr;
	}
}
