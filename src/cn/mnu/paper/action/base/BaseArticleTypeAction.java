package cn.mnu.paper.action.base;

import cn.mnu.paper.service.ArticleTypeManager;

import com.opensymphony.xwork2.ActionSupport;

/***
 * ����ArticleTypeAction
 * @author mdl
 * @version 1.0
 * @date 2014-08-13
 */
public class BaseArticleTypeAction extends ActionSupport {
	// ҵ���߼����
	protected ArticleTypeManager articleTypemgr;
	
	public String execute() throws Exception {
		return SUCCESS;
	}

	public void setArticleTypemgr(ArticleTypeManager articleTypemgr) {
		this.articleTypemgr = articleTypemgr;
	}
}
