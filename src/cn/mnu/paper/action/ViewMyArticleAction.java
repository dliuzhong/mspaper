package cn.mnu.paper.action;

import java.util.List;

import cn.mnu.paper.action.base.BaseArticleAction;
import cn.mnu.paper.domain.Article;
import cn.mnu.paper.domain.ArticleType;

public class ViewMyArticleAction extends BaseArticleAction {

	private Integer myid;
	private List<Article> al;
	private List<ArticleType> atList;
	
	private int start;
	private int limit;
	private int allsum;
	private int page;

	public String execute() throws Exception {
		atList = articleTypemgr.findAllArticleType();
		// 如果limit为0，就是第一次访问
		if (limit == 0) {
			limit = 8;
		}
		
		allsum = articlemgr.findArticleByMsuserSum(myid);
		int allPage = allsum % limit == 0 ? allsum / limit : allsum / limit + 1;

		if (page != 0) {
			if (page > allPage) {
				page = 1;
			}
			
			start = (page - 1) * limit;
		}
		//System.out.println("start:" + start + "limit:" + limit);	
		al = articlemgr.findArticleByMsuser(myid, start, limit);
		
		return SUCCESS;
		
		
	}
	
	Integer getMyid() {
		return myid;
	}

	public void setMyid(Integer myid) {
		this.myid = myid;
	}

	public List<Article> getAl() {
		return al;
	}

	public void setAl(List<Article> al) {
		this.al = al;
	}

	public List<ArticleType> getAtList() {
		return atList;
	}

	public void setAtList(List<ArticleType> atList) {
		this.atList = atList;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getAllsum() {
		return allsum;
	}

	public void setAllsum(int allsum) {
		this.allsum = allsum;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
}
