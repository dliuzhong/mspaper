package cn.mnu.paper.action;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import cn.mnu.paper.action.base.BaseNewsAction;
import cn.mnu.paper.domain.*;
import cn.mnu.paper.tools.MyTools;

public class ViewNewsAction extends BaseNewsAction {
	private Integer layoutid;
	private String pic;
	private int layout_no;
	private List<News> newss;
	
	
	
	
	// json
	private String url;
	
	private String work;
	private String query;
	
	private String paperid;
	private String subjectid;
	private String year;
	private String author;
	
	private int start;
	private int limit;
	private String data;
	private String callback;
	
	
	private InputStream excelStream;
	
	
	public String execute() throws Exception {
		Map session = ActionContext.getContext().getSession();
		Integer paperid = (Integer) session.get("paperid");
		newss = newsmgr.getNewsByPaperAndLayout(paperid, layoutid);
		
		return SUCCESS;
		
	}

	
	public String searchNewsQueryJson() throws Exception {
		int totalCount;
		List<News> nl;
		if (work.trim().equals("all")) {
			totalCount= newsmgr.findAllNewsSum();
			nl = newsmgr.findNewsByWorkAndQurey(work, query, start, limit);
		} else if(work.trim().equals("find")) {
			// 中文编码转换
			if (author != null) {
				//author = MyTools.toChinese(author);
			}
			if (paperid == null || paperid.trim().equals("") || paperid.trim().equals("null")) {
				paperid = "0";
			}
			if (subjectid == null || subjectid.trim().equals("") || subjectid.trim().equals("null")) {
				subjectid = "0";
			}
			totalCount = newsmgr.findNewsSumFind(Integer.parseInt(paperid), Integer.parseInt(subjectid), year, author);
			nl = newsmgr.findNewsFind(Integer.parseInt(paperid), Integer.parseInt(subjectid), year, author, start, limit);
			
		} else {
			// 中文编码转换
			// query = MyTools.toChinese(query);
			totalCount = newsmgr.findNewsSumByWorkAndQuerySum(work, query);
			nl = newsmgr.findNewsByWorkAndQurey(work, query, start, limit);
		}
		
		
		News n;
		// json
		data = "({\"totalCount\":" + totalCount + ",\"results\":[";
		for (int i = 0; i < nl.size(); i++) {
			n = nl.get(i);
			data += "{\"id\":" + (i + 1) + ",\"title\":\"" + n.getTitle() + 
					"\",\"paper\":\"" + n.getPaper().getPaper() + "\",\"layout_no\":\"" + 
					n.getLayout().getLayout_no() + "\",\"author\":\"" + n.getAuthor() + 
					"\",\"subject\":\"" + n.getSubject().getName() + "\",\"cbtime\":\"" + 
					MyTools.changeDate(n.getPaper().getCbtime()) + "\",\"lasteditdatetime\":\"" 
					+ MyTools.changeTime(n.getTime()) + "\",\"see\":\"" + "<a href='../../../paper/" + 
					n.getPaper().getPaper() + "/e" + n.getLayout().getLayout_no() + "/" + n.getFile_path() + 
					"' target='_blank'>查看</a>"
					+ "\"}";
			if (i != (nl.size() - 1))
				data += ",";
		}
		data += "]})";
		ServletActionContext.getRequest().setAttribute("data", data);

		return SUCCESS;
	}
	
	public String downloadReportExcel() throws Exception {
		List<News> nl;
		if (work != null && !work.trim().equals("")) {
			if (work.equals("find")) {
				// 中文编码转换
				/*
				if (author != null) {
					author = MyTools.toChinese(author);
				}
				*/
				if (paperid == null || paperid.trim().equals("") || paperid.trim().equals("null")) {
					paperid = "0";
				}
				if (subjectid == null || subjectid.trim().equals("") || subjectid.trim().equals("null")) {
					subjectid = "0";
				}
				
				nl = newsmgr.findNewsFind(Integer.parseInt(paperid), Integer.parseInt(subjectid), year, author);
			} else {
				// 中文编码转换
				//query = MyTools.toChinese(query);
				nl = newsmgr.findNewsByWorkAndQurey(work, query);
			}
			excelStream = newsmgr.findInputStreamNews(nl);
			
			return SUCCESS;
			
		} else {
			return "failure";
		}
	}
	
	public String adminDoSearch() throws Exception {
		url = "json/seachQuery.action?work=" + work + "&query=" + query;
		return SUCCESS;
	}
	public String adminDoFind() throws Exception {
		work = "find";
		url = "json/seachQuery.action?work=find&paperid=" + paperid + "&subjectid=" + subjectid + 
				"&year=" + year + "&author=" + author;
		return SUCCESS;
	}
	
	public Integer getLayoutid() {
		return layoutid;
	}

	public void setLayoutid(Integer layoutid) {
		this.layoutid = layoutid;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public int getLayout_no() {
		return layout_no;
	}

	public void setLayout_no(int layout_no) {
		this.layout_no = layout_no;
	}

	public List<News> getNewss() {
		return newss;
	}

	public void setNewss(List<News> newss) {
		this.newss = newss;
	}

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
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


	public String getQuery() {
		return query;
	}


	public void setQuery(String query) {
		this.query = query;
	}


	public String getData() {
		return data;
	}


	public void setData(String data) {
		this.data = data;
	}


	public String getCallback() {
		return callback;
	}


	public void setCallback(String callback) {
		this.callback = callback;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}



	public String getYear() {
		return year;
	}


	public void setYear(String year) {
		this.year = year;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getPaperid() {
		return paperid;
	}


	public void setPaperid(String paperid) {
		this.paperid = paperid;
	}


	public String getSubjectid() {
		return subjectid;
	}


	public void setSubjectid(String subjectid) {
		this.subjectid = subjectid;
	}


	public InputStream getExcelStream() {
		return excelStream;
	}


	public void setExcelStream(InputStream excelStream) {
		this.excelStream = excelStream;
	}

}
