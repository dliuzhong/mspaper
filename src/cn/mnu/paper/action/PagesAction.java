package cn.mnu.paper.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import cn.mnu.paper.action.base.BasePagesAction;
import cn.mnu.paper.domain.Layout;
import cn.mnu.paper.domain.News;
import cn.mnu.paper.domain.Paper;
import cn.mnu.paper.tools.NewFiles;

public class PagesAction extends BasePagesAction {
	private Integer paperid;
	private String data;
	private String tip;
	
	public String refreshPages() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		NewFiles nf = new NewFiles();
		Paper p = papermgr.getPaperById(paperid);
		
		// 更新index
		if (p != null && nf.newIndex(p, request)) {
			List<Layout> ls = layoutmgr.getLayoutByPaper(paperid);
			for (int i = 0;i < ls.size(); i++) {
				Layout l = ls.get(i);
				List<News> ns = newsmgr.getNewsByPaperAndLayout(paperid, l.getId());
				// 更新page和main
				if (nf.newPageHtml(l, ns, request) && nf.newMainHtml(l, ns, ls, request)) {
					
					data = "{success:true}";
				}
					
			}
		}
		
		return SUCCESS;
	}

	public String addPages() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		NewFiles nf = new NewFiles();
		Paper p = papermgr.getPaperById(paperid);
		// 更新index
		if (p != null && nf.newIndex(p, request)) {
			List<Layout> ls = layoutmgr.getLayoutByPaper(paperid);
			for (int i = 0;i < ls.size(); i++) {
				Layout l = ls.get(i);
				System.out.println(l.getPic());
				List<News> ns = newsmgr.getNewsByPaperAndLayout(paperid, l.getId());
				// 更新page和main
				if (nf.newPageHtml(l, ns, request) && nf.newMainHtml(l, ns, ls, request)) {
					p.setPaper_url(p.getPaper());
					papermgr.changePaper(p, request);
					if (p != null) {
						Map session = ActionContext.getContext().getSession();
						tip = "第" + p.getPaper() + "期添加成功！";
						session.put("paperid", null);
						session.put("newpaper", null);
						session.put("laysum", null);
					}
					
					
				}
					
			}
		}
		
		return SUCCESS;
	}
	
	
	public Integer getPaperid() {
		return paperid;
	}

	public void setPaperid(Integer paperid) {
		this.paperid = paperid;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}
}
