package cn.mnu.paper.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;

import cn.mnu.paper.action.base.BaseNewsAction;
import cn.mnu.paper.domain.*;


public class NewNewsAction extends BaseNewsAction 
	implements ModelDriven<News> {
	private News model = new News();
	private Integer paperid;
	private Integer layoutid;
	private Integer subjectid;
	private int layout_no;
	private String pic;
	private String data;
	
	public String execute() throws Exception {
		
		if (newsmgr.addNews(model, paperid, layoutid, subjectid, 
				ServletActionContext.getRequest()) != 0) {
			
			return SUCCESS;
		} else {
			return "failure";
		}
	}
	
	public String addNewsAjax() throws Exception {
		
		if (newsmgr.addNewsRefresh(model, paperid, layoutid, subjectid, 
				ServletActionContext.getRequest()) != 0) {
			data = "{success:true}";
		} else {
			data = "{success:false}";
		}
		return SUCCESS;
	}
	
	@Override
	public News getModel() {
		// TODO Auto-generated method stub
		return model;
	}

	public Integer getPaperid() {
		return paperid;
	}

	public void setPaperid(Integer paperid) {
		this.paperid = paperid;
	}

	public Integer getLayoutid() {
		return layoutid;
	}

	public void setLayoutid(Integer layoutid) {
		this.layoutid = layoutid;
	}

	public Integer getSubjectid() {
		return subjectid;
	}

	public void setSubjectid(Integer subjectid) {
		this.subjectid = subjectid;
	}

	public int getLayout_no() {
		return layout_no;
	}

	public void setLayout_no(int layout_no) {
		this.layout_no = layout_no;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
}
