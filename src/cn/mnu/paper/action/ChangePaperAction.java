package cn.mnu.paper.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;

import cn.mnu.paper.action.base.BasePaperAction;
import cn.mnu.paper.domain.Paper;

public class ChangePaperAction extends BasePaperAction
	implements ModelDriven<Paper> {
	private Paper model = new Paper();
	private String data;
	
	
	public String getPaper() throws Exception {
		model = papermgr.getPaperById(model.getId());
		if (model != null && model.getId() > 0) {
			return SUCCESS;
		} else {
			return "failure";
		}
	}
	
	
	public String deletePaper() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		if (papermgr.removePaperById(model.getId(), request)) {
			data = "{success:true}";
		} else {
			data = "{success:false}";
		}
		
		return SUCCESS;
	}
	
	public String editPaper() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		if (papermgr.changePaper(model, request) != 0) {
			data = "{success:true}";
		}
		return SUCCESS;
	}
	
	
	public Paper getModel() {
		return model;
	}


	public String getData() {
		return data;
	}


	public void setData(String data) {
		this.data = data;
	}

}
