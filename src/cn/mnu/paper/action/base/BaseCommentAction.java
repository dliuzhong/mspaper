package cn.mnu.paper.action.base;

import cn.mnu.paper.service.CommentManager;

import com.opensymphony.xwork2.ActionSupport;

/***
 * ����CommentAction
 * @author mdl
 * @version 1.0
 * @date 2014-08-10
 */
public class BaseCommentAction extends ActionSupport {
	// ҵ���߼����
	protected CommentManager commentmgr;
	
	public String execute() throws Exception {
		return SUCCESS;
	}

	public void setCommentmgr(CommentManager commentmgr) {
		this.commentmgr = commentmgr;
	}
}
