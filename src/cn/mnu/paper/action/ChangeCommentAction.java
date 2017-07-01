package cn.mnu.paper.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;

import cn.mnu.paper.action.base.BaseCommentAction;
import cn.mnu.paper.domain.Comment;
import cn.mnu.paper.tools.FileDo;
import cn.mnu.paper.tools.XmlParser;

public class ChangeCommentAction extends BaseCommentAction 
	implements ModelDriven<Comment> {
	private Comment model = new Comment();
	private String isShow;
	private String data;
	
	public String execute() throws Exception {
		// 获取配置文件
		FileDo filedo = new FileDo(ServletActionContext.getRequest());
		String xmlName = filedo.getDir() + "WEB-INF\\config.xml";
		XmlParser xml = new XmlParser(xmlName);
		isShow = xml.getXMLRootNodeValue("comment", "isShow");
		
		return SUCCESS;
	}
	
	public String changeCommentOpen() throws Exception {
		// 获取配置文件
		FileDo filedo = new FileDo(ServletActionContext.getRequest());
		String xmlName = filedo.getDir() + "WEB-INF\\config.xml";
		XmlParser xml = new XmlParser(xmlName);
		isShow = xml.getXMLRootNodeValue("comment", "isShow");
		if (isShow.equals("true")) {
			isShow = "false";
			if (xml.setXMLRootNodeValue("comment", "isShow", isShow)) {
				data = "{success:true}";
			} else {
				data = "{success:false}";
			}	
		} else {
			isShow = "true";
			if (xml.setXMLRootNodeValue("comment", "isShow", isShow)) {
				data = "{success:true}";
			} else {
				data = "{success:false}";
			}
				
		}
		return SUCCESS;	
	}
	
	public String deleteComment() throws Exception {
		if (model.getId() != 0 && commentmgr.removeComment(model.getId())) {
			data = "{success:true}";
		} else {
			data = "{success:false}";
		}
		return SUCCESS;
	}
	
	public String passComment() throws Exception {
		if (model.getId() != 0 && 
				commentmgr.changeCommentPassTo1(model.getId()) != 0) {
			data = "{success:true}";
		} else {
			data = "{success:false}";
		}
		
		return SUCCESS;
	}
	
	public String unpassComment() throws Exception {
		if (model.getId() != 0 && 
				commentmgr.changeCommentPassTo0(model.getId()) != 0) {
			data = "{success:true}";
		} else {
			data = "{success:false}";
		}
		
		return SUCCESS;
	}
	
	@Override
	public Comment getModel() {
		// TODO Auto-generated method stub
		return model;
	}

	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
