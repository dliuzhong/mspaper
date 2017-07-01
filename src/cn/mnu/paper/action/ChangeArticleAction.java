package cn.mnu.paper.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import cn.mnu.paper.action.base.BaseArticleAction;
import cn.mnu.paper.domain.Article;

public class ChangeArticleAction extends BaseArticleAction
	implements ModelDriven<Article> {
	private Article model = new Article();
	private String changeinfo;
	private String data;
	private int status_t;
	
	public String changeStatusArticle() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		
		if (model != null && model.getId() != 0) {
			Article a = articlemgr.findArticleById(model.getId());
			if (a != null) {
				if (status_t == 3) {
					a.setChangeinfo(model.getChangeinfo());
				}
				if (status_t == 5) {
					a.setOther(model.getOther());
				}
				if (articlemgr.changeArticleStatus(a, a.getStatus().getId(), 
						status_t, (Integer) session.get("adminid")) != 0) {
					data = "{success:true}";
				} else {
					System.out.println("error");
					data = "{success:false}";
				}
			} else {
				data = "{success:false}";
			}
			
		} else {
			data = "{success:false}";
		}
		return SUCCESS;
	}
	
	public String changeEditArticle() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		if (model != null) {
			if (articlemgr.adminChangeEditArticle(model, (Integer) session.get("adminid")) != 0) {
				data = "{success:true}";
			} else {
				data = "{success:false}";
			}
		} else {
			data = "{success:false}";
		}
		return SUCCESS;
	}
	
	public String deleteArticle() throws Exception {
		if (model != null) {
			if (articlemgr.removeArticleById(model.getId())) {
				data = "{success:true}";
			} else {
				data = "{success:false}";
			}
		} else {
			data = "{success:false}";
		}
		
		return SUCCESS;
	}
	
	public String publishArticle() throws Exception {
		if (model != null && data != null && !data.trim().equals("")) {
			data = "<font color='green'>[ÒÑ·¢±í]</font>£¨" + data + "£©<br>";
			model.setPublish(1);
			if (articlemgr.changeArticleAddDataToOther(data, model) != 0) {
				data = "{success:true}";
			} else {
				data = "{success:false}";
			}
		} else {
			data = "{success:false}";
		}
		return SUCCESS;
	}
	
	@Override
	public Article getModel() {
		// TODO Auto-generated method stub
		return model;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getStatus_t() {
		return status_t;
	}

	public void setStatus_t(int status_t) {
		this.status_t = status_t;
	}

	public String getChangeinfo() {
		return changeinfo;
	}

	public void setChangeinfo(String changeinfo) {
		this.changeinfo = changeinfo;
	}


}
