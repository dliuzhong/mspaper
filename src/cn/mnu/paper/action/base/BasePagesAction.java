package cn.mnu.paper.action.base;

import com.opensymphony.xwork2.ActionSupport;

import cn.mnu.paper.service.LayoutManager;
import cn.mnu.paper.service.NewsManager;
import cn.mnu.paper.service.PaperManager;

/***
 * 基础PagesAction
 * @author mdl
 * @version 1.0
 * @date 2014-07-24
 */
public class BasePagesAction extends ActionSupport {
	// 业务逻辑组件
	protected PaperManager papermgr;
	protected LayoutManager layoutmgr;
	protected NewsManager newsmgr;

	public String execute() throws Exception {
		return SUCCESS;
	}

	public void setPapermgr(PaperManager papermgr) {
		this.papermgr = papermgr;
	}

	public void setLayoutmgr(LayoutManager layoutmgr) {
		this.layoutmgr = layoutmgr;
	}

	public void setNewsmgr(NewsManager newsmgr) {
		this.newsmgr = newsmgr;
	}
}
