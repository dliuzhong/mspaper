package cn.mnu.paper.action.base;

import cn.mnu.paper.service.LayoutManager;
import cn.mnu.paper.service.NewsManager;
import cn.mnu.paper.service.PaperManager;

import com.opensymphony.xwork2.ActionSupport;

/***
 * 基础LayoutAction
 * @author mdl
 * @version 1.0
 * @date 2014-07-12
 */
public class BaseLayoutAction extends ActionSupport {
	// 业务逻辑组件
	protected LayoutManager layoutmgr;
	protected PaperManager papermgr;
	protected NewsManager newsmgr;
	
	public void setLayoutmgr(LayoutManager layoutmgr) {
		this.layoutmgr = layoutmgr;
	}

	public String execute() throws Exception {
		return SUCCESS;
	}

	public void setPapermgr(PaperManager papermgr) {
		this.papermgr = papermgr;
	}

	public void setNewsmgr(NewsManager newsmgr) {
		this.newsmgr = newsmgr;
	}

	
}
