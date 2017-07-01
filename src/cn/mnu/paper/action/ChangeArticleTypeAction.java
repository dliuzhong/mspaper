package cn.mnu.paper.action;

import com.opensymphony.xwork2.ModelDriven;

import cn.mnu.paper.action.base.BaseArticleTypeAction;
import cn.mnu.paper.domain.ArticleType;

public class ChangeArticleTypeAction extends BaseArticleTypeAction 
	implements ModelDriven<ArticleType> {
	private ArticleType model = new ArticleType();
	private String data;
	
	public String addArticleType() throws Exception {
		
		if (model != null) {
			
			if (articleTypemgr.addArticleType(model) != 0) {
				data = "{success:true}";
			} else {
				data = "{success:false,info:'Ìí¼ÓÊ§°Ü£¡'}";
			}
				
			
		} else {
			data = "{success:false,info:'Ìí¼ÓÊ§°Ü£¡'}";
		} 
		return SUCCESS;
	}
	
	public String changeArticleType() throws Exception {
		if (model != null) {
			if (articleTypemgr.changeArticleType(model) != 0) {
				data = "{success:true}";
			} else {
				data = "{success:false,info:'ÐÞ¸ÄÊ§°Ü£¡'}";
			}
			
		} else {
			data = "{success:false,info:'ÐÞ¸ÄÊ§°Ü£¡'}";
		} 
		return SUCCESS;
	}
	
	public String deleteArticleType() throws Exception {
		if (model.getId() != 0 && articleTypemgr.removeArticleType(model.getId())) {
			data = "{success:true}";
		} else {
			data = "{success:false}";
		}
		return SUCCESS;
	}
	
	@Override
	public ArticleType getModel() {
		// TODO Auto-generated method stub
		return model;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
