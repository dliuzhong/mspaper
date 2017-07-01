package cn.mnu.paper.action;

import java.util.List;

import com.opensymphony.xwork2.ModelDriven;

import cn.mnu.paper.action.base.BaseLayoutAction;
import cn.mnu.paper.domain.Layout;
import cn.mnu.paper.domain.News;

public class ViewLayoutAndNewsAction extends BaseLayoutAction
	implements ModelDriven<Layout> {

	private Layout model = new Layout();
	private List<News> newss;
	private Integer paperid;
	private int last;
	
	public String execute() throws Exception {
		
		if (model.getLayout_no() != 0) {
			model = layoutmgr.findLayoutByPaperAndLayno(paperid, model.getLayout_no());
			if (model != null) {
				newss = newsmgr.getNewsByPaperAndLayout(paperid, model.getId());
			}
		}
		
		return SUCCESS;
	}
	
	
	@Override
	public Layout getModel() {
		// TODO Auto-generated method stub
		return model;
	}




	public List<News> getNewss() {
		return newss;
	}




	public void setNewss(List<News> newss) {
		this.newss = newss;
	}




	public Integer getPaperid() {
		return paperid;
	}




	public void setPaperid(Integer paperid) {
		this.paperid = paperid;
	}


	public int getLast() {
		return last;
	}


	public void setLast(int last) {
		this.last = last;
	}

	
}
