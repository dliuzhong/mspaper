package cn.mnu.paper.action.base;


import cn.mnu.paper.service.ArticleManager;
import cn.mnu.paper.service.ArticleTypeManager;
import cn.mnu.paper.service.DepartmentManager;
import cn.mnu.paper.service.MsuserManager;

import com.opensymphony.xwork2.ActionSupport;

/***
 * 基础ArticleAction
 * @author mdl
 * @version 1.0
 * @date 2014-08-14
 */
public class BaseArticleAction extends ActionSupport {
	// 业务逻辑组件
	protected ArticleManager articlemgr;
	protected ArticleTypeManager articleTypemgr;
	protected MsuserManager msusermgr;
	protected DepartmentManager departmentmgr;

	public String execute() throws Exception {
		return SUCCESS;
	}

	public void setArticlemgr(ArticleManager articlemgr) {
		this.articlemgr = articlemgr;
	}

	public void setArticleTypemgr(ArticleTypeManager articleTypemgr) {
		this.articleTypemgr = articleTypemgr;
	}

	public void setMsusermgr(MsuserManager msusermgr) {
		this.msusermgr = msusermgr;
	}

	public void setDepartmentmgr(DepartmentManager departmentmgr) {
		this.departmentmgr = departmentmgr;
	}

}
