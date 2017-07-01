package cn.mnu.paper.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import cn.mnu.paper.action.base.BaseCommentAction;
import cn.mnu.paper.domain.Comment;
import cn.mnu.paper.tools.FileDo;
import cn.mnu.paper.tools.MyTools;
import cn.mnu.paper.tools.XmlParser;

import com.opensymphony.xwork2.ModelDriven;

public class CommentAction extends BaseCommentAction 
	implements ModelDriven<Comment> {
	private Comment model = new Comment();
	private List<Comment> comments;
	private int newsid;
	
	
	public String getComment() throws Exception  {
		// 获取配置文件
		String isShow;
		FileDo filedo = new FileDo(ServletActionContext.getRequest());
		String xmlName = filedo.getDir() + "WEB-INF\\config.xml";
		XmlParser xml = new XmlParser(xmlName);
		isShow = xml.getXMLRootNodeValue("comment", "isShow");
		
		if (isShow.equals("true")) {
			comments = commentmgr.getNewsComment(newsid);
			
			return SUCCESS;
		} else {
			return "failure";
		}
	}
	
	public String addComment() throws Exception {
		model.setIp(MyTools.getIpAddr(ServletActionContext.getRequest()));
		if (commentmgr.addComment(model, newsid)) {
			return SUCCESS;
		} else {
			return "failure";
		}
	}

	@Override
	public Comment getModel() {
		// TODO Auto-generated method stub
		return model;
	}

	public int getNewsid() {
		return newsid;
	}

	public void setNewsid(int newsid) {
		this.newsid = newsid;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

}
