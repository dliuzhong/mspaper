package cn.mnu.paper.action.base;

import cn.mnu.paper.service.LayoutManager;
import cn.mnu.paper.service.NewsManager;

import com.opensymphony.xwork2.ActionSupport;

/***
 * ����NewsAction
 * @author mdl
 * @version 1.0
 * @date 2014-07-24
 */
public class BaseNewsAction extends ActionSupport {
	// ҵ���߼����
	protected NewsManager newsmgr;
	protected LayoutManager layoutmgr;
	
	public String execute() throws Exception {
		return SUCCESS;
	}

	public void setNewsmgr(NewsManager newsmgr) {
		this.newsmgr = newsmgr;
	}

	public void setLayoutmgr(LayoutManager layoutmgr) {
		this.layoutmgr = layoutmgr;
	}
}
