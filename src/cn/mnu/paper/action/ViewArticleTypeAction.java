package cn.mnu.paper.action;

import java.util.List;

import cn.mnu.paper.action.base.BaseArticleTypeAction;
import cn.mnu.paper.domain.ArticleType;

public class ViewArticleTypeAction extends BaseArticleTypeAction {
	private int start;
	private int limit;
	private String data;
	
	private String sort;
	private String dir;
	
	public String getArticleTypesJson() throws Exception {
		int totalCount = articleTypemgr.findAllArticleTypeSum();
		List<ArticleType> al = articleTypemgr.findAllArticleType(start, limit, sort, dir);
		ArticleType a;
		// json
		data = "({\"totalCount\":" + totalCount + ",\"articleTypes\":[";
		for (int i = 0;i < al.size(); i++) {
			a = al.get(i);
			data += "{\"id\":" + a.getId() + ",\"name\":\"" + a.getName() + "\",\"de\":\"" + 
					a.getDe() + "\"}";
			if (i != (totalCount - 1))
				data += ",";
		}
		data += "]})";
		return SUCCESS;
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
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
}
