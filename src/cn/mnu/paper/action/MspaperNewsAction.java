package cn.mnu.paper.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import cn.mnu.paper.action.base.BaseNewsAction;
import cn.mnu.paper.domain.News;
import cn.mnu.paper.tools.MyTools;

public class MspaperNewsAction extends BaseNewsAction {
	private String words;
	private String select;
	private int start;
	private int limit;
	private int allsum;
	private int page;
	
	private List<News> newsl = new ArrayList<News>();
	
	
	public String doSearch() throws Exception {
		
		if (words == null || words.trim().equals("")) {
			allsum = 0;
			return SUCCESS;
		}
		
		// 如果limit为0，就是第一次访问
		if (limit == 0) {
			limit = 10;
		}

		allsum = newsmgr.findNewsBySelectWordsSum(select, words);
		int allPage = allsum % limit == 0 ? allsum / limit : allsum / limit + 1;

		if (page != 0) {
			if (page > allPage) {
				page = 1;
			}

			start = (page - 1) * limit;
		}
		//System.out.println("start:" + start + "limit:" + limit);
		List<News> newss = newsmgr.findNewsBySelectWords(start, limit, select, words);
		
		for (int i = 0;i < newss.size(); i++) {
			News n = newss.get(i);
			if (n.getContent().indexOf("<img") != -1) {
				n.setOther(n.getContent().substring(n.getContent().indexOf("src=\""), n.getContent().length()));
				n.setOther(n.getOther().substring(0, n.getOther().indexOf("\" ") + 2));
			}
			n.setContent(MyTools.replaceHtml(n.getContent()));
			newsl.add(n);
		}
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

	public List<News> getNewsl() {
		return newsl;
	}

	public void setNewsl(List<News> newsl) {
		this.newsl = newsl;
	}

	public String getWords() {
		return words;
	}

	public void setWords(String words) {
		this.words = words;
	}

	public String getSelect() {
		return select;
	}

	public void setSelect(String select) {
		this.select = select;
	}
}
