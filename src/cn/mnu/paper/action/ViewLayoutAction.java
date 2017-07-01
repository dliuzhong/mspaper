package cn.mnu.paper.action;

import java.util.Map;

import cn.mnu.paper.action.base.BaseLayoutAction;
import cn.mnu.paper.domain.Layout;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public class ViewLayoutAction extends BaseLayoutAction 
 	implements ModelDriven<Layout> {
	private Layout model = new Layout();
	private Integer paperid;
	
	public String findLayout() throws Exception {
		Map session = ActionContext.getContext().getSession();
		paperid = (Integer) session.get("paperid");
		int layout_no = model.getLayout_no();
		model = layoutmgr.findLayoutByPaperAndLayno(paperid, model.getLayout_no());
		if (model == null) {
			model = new Layout();
			model.setLayout_no(layout_no);
		}
		//System.out.println(model.getZt()+ layout_no + model.getLayout_no());
		return SUCCESS;
		
	}
	
	public String getLayout() throws Exception {
		model = layoutmgr.findLayoutByID(model.getId());
		return SUCCESS;
	}
	
	public Layout getModel() {
		// TODO Auto-generated method stub
		return model;
	}
	public Integer getPaperid() {
		return paperid;
	}
	public void setPaperid(Integer paperid) {
		this.paperid = paperid;
	}
	
	

}
