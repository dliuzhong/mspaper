package cn.mnu.paper.action;


import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import cn.mnu.paper.action.base.BaseNewsAction;
import cn.mnu.paper.domain.Layout;
import cn.mnu.paper.domain.News;

public class ChangeNewsAction extends BaseNewsAction
	implements ModelDriven<News> {
	
	private News model = new News();
	private String pic;
	private Integer[] boxes;
	private String data;
	private Integer subjectid;
	private Integer layoutid;
	private int layout_no;
	
	
	public String getNewsBoxexAjax() throws Exception {
		
		if (boxes.length != 0) {
			Map session = ActionContext.getContext().getSession();
			session.put("boxes", boxes);
			data = "{success:true}";
		} else {
			data = "{success:false";
		}
		
		return SUCCESS;
		
	}
	
	
	
	public String getNewsAndPic() throws Exception {
		model = newsmgr.findNewsById(model.getId());
		if (model != null) {
			Layout l = layoutmgr.findLayoutByID(model.getLayout().getId());
			pic = l.getPic();
		}
		return SUCCESS;
	}
	
	
	public String doReditNewsAjax() throws Exception {
		Integer n_id = newsmgr.changeNews(model, subjectid, ServletActionContext.getRequest());
		if (n_id != 0) {
			data = "{success:true}";
		} else {
			data = "{success:false}";
		}
		return SUCCESS;
	}
	// 批量删除
	public String deleteNews() throws Exception {
		
		if (newsmgr.removeNewss(boxes, ServletActionContext.getRequest())) {
			data = "{success:true}";
		} else {
			data = "{success:false}";
		}
		return SUCCESS;
		
	}
	// 单个删除
	public String deleteNewNews() throws Exception {
		
		Integer[] id = new Integer[1];
		id[0] = model.getId();
		
		if (newsmgr.removeNewss(id, ServletActionContext.getRequest())) {
			return SUCCESS;		
		} else {
			return "failure";
		}
		
	}
	
	@Override
	public News getModel() {
		// TODO Auto-generated method stub
		return model;
	}



	

	public String getData() {
		return data;
	}



	public void setData(String data) {
		this.data = data;
	}


	public String getPic() {
		return pic;
	}


	public void setPic(String pic) {
		this.pic = pic;
	}



	public Integer getSubjectid() {
		return subjectid;
	}



	public void setSubjectid(Integer subjectid) {
		this.subjectid = subjectid;
	}



	public Integer[] getBoxes() {
		return boxes;
	}



	public void setBoxes(Integer[] boxes) {
		this.boxes = boxes;
	}



	public int getLayout_no() {
		return layout_no;
	}



	public void setLayout_no(int layout_no) {
		this.layout_no = layout_no;
	}



	public Integer getLayoutid() {
		return layoutid;
	}



	public void setLayoutid(Integer layoutid) {
		this.layoutid = layoutid;
	}



}
