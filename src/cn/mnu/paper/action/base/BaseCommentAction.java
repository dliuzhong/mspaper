package cn.mnu.paper.action.base;

import cn.mnu.paper.service.CommentManager;

import com.opensymphony.xwork2.ActionSupport;

/***
 * 基础CommentAction
 * @author mdl
 * @version 1.0
 * @date 2014-08-10
 */
public class BaseCommentAction extends ActionSupport {
	// 业务逻辑组件
	protected CommentManager commentmgr;
	
	public String execute() throws Exception {
		return SUCCESS;
	}

	public void setCommentmgr(CommentManager commentmgr) {
		this.commentmgr = commentmgr;
	}
}
