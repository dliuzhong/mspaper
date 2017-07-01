package cn.mnu.paper.action;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ModelDriven;

import cn.mnu.paper.action.base.BaseArticleAction;
import cn.mnu.paper.domain.Article;
import cn.mnu.paper.domain.ArticleType;

public class ChangeMyArticleAction extends BaseArticleAction
	implements ModelDriven<Article> {
	private Article model = new Article();
	private List<ArticleType> atList;
	private Integer msuid;
	private Integer typeid;
	private String data;
	
	public String addMyArticle() throws Exception {
		if (model != null && 
				articlemgr.addArticle(model, msuid, typeid) != 0) {
			data = "投稿成功！";
			return SUCCESS;
		} else {
			return "failure";
		}
	}
	
	public String deleteMyArticle() throws Exception {
		if (model != null && 
				articlemgr.removeArticleStatus1ById(model.getId())) {
			data = "删除成功！";
			return SUCCESS;
		} else {
			return "failure";
		}
	}
	
	public String viewMyArticle() throws Exception {
		if (model != null) {
			model = articlemgr.findArticleById(model.getId());
			if (model != null) {
				return SUCCESS;
			} else {
				return "failure";
			}
		} else {
			return "failure";
		}
	}
	
	public String getMyArticle() throws Exception {
		if (model != null) {
			
			model = articlemgr.findArticleById(model.getId());
			if (model != null) {
				
				ArticleType atf = articleTypemgr.findArticleTypeById(model.getType().getId());
				//System.out.println(atf.getName());
				atList = new ArrayList<ArticleType>();
				atList.add(atf);
				List<ArticleType> atl = articleTypemgr.findAllArticleType();
				ArticleType att = null;
				for (int i = 0;i < atl.size(); i++) {
					att = atl.get(i);
					if (att.getId() != atf.getId()) {
						atList.add(att);
					}
				}
				
				return SUCCESS;
			} else {
				return "failure";
			}
		} else {
			return "failure";
		}
	}
	 
	public String changeMyArticle() throws Exception {
		if (model != null) {
			if (articlemgr.changeMyArticle(model, typeid) != 0) {
				return SUCCESS;
			} else {
				return "failure";
			}
		} else {
			return "failure";
		}
	}
	
	@Override
	public Article getModel() {
		// TODO Auto-generated method stub
		return model;
	}



	public Integer getMsuid() {
		return msuid;
	}



	public void setMsuid(Integer msuid) {
		this.msuid = msuid;
	}



	public Integer getTypeid() {
		return typeid;
	}



	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public List<ArticleType> getAtList() {
		return atList;
	}

	public void setAtList(List<ArticleType> atList) {
		this.atList = atList;
	}

}
