package cn.mnu.paper.action.base;

import cn.mnu.paper.service.NewsManager;
import cn.mnu.paper.service.SubjectManager;

import com.opensymphony.xwork2.ActionSupport;

/***
 * 基础SucjectAction
 * @author mdl
 * @version 1.0
 * @date 2014-07-20
 */
public class BaseSubjectAction extends ActionSupport {
	// 业务逻辑组件
	protected SubjectManager subjectmgr;
	protected NewsManager newsmgr;
	
	public void setSubjectmgr(SubjectManager subjectmgr) {
		this.subjectmgr = subjectmgr;
	}
	public String execute() throws Exception
	{
		return SUCCESS;
	}
	public void setNewsmgr(NewsManager newsmgr) {
		this.newsmgr = newsmgr;
	}

	
}
