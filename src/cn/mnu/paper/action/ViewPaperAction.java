package cn.mnu.paper.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import cn.mnu.paper.domain.*;
import cn.mnu.paper.tools.MyTools;
import cn.mnu.paper.action.base.BasePaperAction;

public class ViewPaperAction extends BasePaperAction {
	private List<Paper> papers;
	private String year;
	private String paperno;
	// json
	private String callback;
	private String query;
	private int start;
	private int limit;
	private String data;
	
	
	public String searchPaper() throws Exception {
		if (year == null || year.trim().equals("")) {
			this.setYear("");
		}
		if (paperno == null || paperno.trim().equals("")) {
			this.setPaperno("");
		}
		System.out.println(year +","+ paperno);
		papers = papermgr.getPaperByPaperAndYear(paperno, year);
		return SUCCESS;
	}
	
	public String papersQueryJson() throws Exception {
		// ÖÐÎÄ±àÂë×ª»»
		query = MyTools.toChinese(query);
		if (query.startsWith(" ")) {
			query = "";
		}
		int totalCount = papermgr.findQueryPaperSum(query.trim());
		List<Paper> pl = papermgr.findPaperByPaperQuery(query.trim(), start, limit);
		Paper p = null;
		data = callback + "({\"totalCount\":" + totalCount + ",\"papers\":[";
		for (int i = 0; i < pl.size(); i++) {
			p = pl.get(i);
			data += "{\"id\":" + p.getId() + ",\"paper\":\"" + p.getPaper()
					+ "\"}";
			if (i != (totalCount - 1))
				data += ",";
		}
		data += "]})";
		ServletActionContext.getRequest().setAttribute("data", data);

		return SUCCESS;

	}


	public List<Paper> getPapers() {
		return papers;
	}


	public void setPapers(List<Paper> papers) {
		this.papers = papers;
	}


	public String getYear() {
		return year;
	}


	public void setYear(String year) {
		this.year = year;
	}


	public String getPaperno() {
		return paperno;
	}


	public void setPaperno(String paperno) {
		this.paperno = paperno;
	}

	public String getCallback() {
		return callback;
	}

	public void setCallback(String callback) {
		this.callback = callback;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
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
	
	
	
}
