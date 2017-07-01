package cn.mnu.paper.action;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import cn.mnu.paper.action.base.BaseArticleAction;
import cn.mnu.paper.domain.Article;
import cn.mnu.paper.domain.ArticleType;
import cn.mnu.paper.tools.MyTools;

public class ViewArticleAction extends BaseArticleAction {
	private int start;
	private int limit;
	private String data;
	
	private String sort;
	private String dir;
	
	private int status;
	
	private int id;
	private InputStream wordStream;
	
	public String getstatus1ArticlesJson() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();

		// ×´Ì¬1£ºÎ´ÉóºË
		status = 1;
		int totalCount = articlemgr.findStatusArticleSum(status);
		List<Article> al = articlemgr.findStatusArticle(status, start, limit, sort, dir);
		Article a;
		// json
		data = "({\"totalCount\":" + totalCount + ",\"status1s\":[";
		for (int i = 0;i < al.size(); i++) {
			a = al.get(i);
			// ÐÞ¸Ä×´Ì¬£¬¸ÄÎªÉóºËÖÐ
			//System.out.println((Integer) session.get("adminid"));
			if (a.getStatus().getId() == 1) {
				if (a.getAdmin() != null) {
					articlemgr.changeArticleStatus(a, status, 2, 
							(Integer) session.get("adminid"));
				} else {
					articlemgr.changeArticleStatus(a, status, 2, 0);
				}
				
			}
			data += "{\"id\":" + a.getId() + 
					",\"title\":\"" + a.getTitle() + 
					"\",\"msuser\":\"" + a.getMsuser().getName() + 
					"\",\"keyword\":\"" + a.getKeyword() + 
					"\",\"content\":\"" + a.getContent().replaceAll("\"", "'") + 
					"\",\"type\":\"" + a.getType().getName() + 
					"\",\"time\":\"" + MyTools.changeTime(a.getTime()) + 
					"\",\"other\":\"" + (a.getOther() == null ? "":a.getOther()) +
					"\",\"admin\":\"" + (a.getAdmin() == null ? "":a.getAdmin().getName()) +
					"\",\"changeinfo\":\"" + (a.getChangeinfo() == null ? "":a.getChangeinfo()) +
					"\",\"see\":\"" + "²é¿´" + 
					"\"}";
			if (i != (totalCount - 1))
				data += ",";
		}
		data += "]})";
		return SUCCESS;
	}
	
	public String getstatusArticlesJson() throws Exception {
		// ×´Ì¬3£ºÐÞ¸Ä´ýÉó
		// ×´Ì¬4£ºÍ¨¹ýÉóºË
		// ×´Ì¬5£ºÎ´Í¨¹ýÉóºË
		int totalCount = articlemgr.findStatusArticleSum(status);
		List<Article> al = articlemgr.findStatusArticle(status, start, limit, sort, dir);
		Article a;
		// json
		data = "({\"totalCount\":" + totalCount + ",\"status\":[";
		for (int i = 0;i < al.size(); i++) {
			a = al.get(i);
			
			data += "{\"id\":" + a.getId() + 
					",\"title\":\"" + a.getTitle() + 
					"\",\"msuser\":\"" + a.getMsuser().getName() + 
					"\",\"keyword\":\"" + a.getKeyword() + 
					"\",\"content\":\"" + a.getContent().replaceAll("\"", "'") + 
					"\",\"type\":\"" + a.getType().getName() + 
					"\",\"time\":\"" + MyTools.changeTime(a.getTime()) + 
					"\",\"checktime\":\"" + MyTools.changeTime(a.getChecktime()) +
					"\",\"admin\":\"" + (a.getAdmin() == null ? "":a.getAdmin().getName()) +
					"\",\"changeinfo\":\"" + (a.getChangeinfo() == null ? "":a.getChangeinfo()) +
					"\",\"publish\":\"" + a.getPublish() +
					"\",\"other\":\"" + (a.getOther() == null ? "":a.getOther()) +
					"\",\"see\":\"" + "²é¿´" + 
					"\"}";
			if (i != (totalCount - 1))
				data += ",";
		}
		data += "]})";
		return SUCCESS;
	}

	public String getAllStatusArticlesJson() throws Exception {
		
		int totalCount = articlemgr.findAllArticleSum();
		List<Article> al = articlemgr.findAllArticle(start, limit, sort, dir);
		Article a;
		// json
		data = "({\"totalCount\":" + totalCount + ",\"status\":[";
		for (int i = 0;i < al.size(); i++) {
			a = al.get(i);
			
			data += "{\"id\":" + a.getId() + 
					",\"title\":\"" + a.getTitle() + 
					"\",\"msuser\":\"" + a.getMsuser().getName() + 
					"\",\"keyword\":\"" + a.getKeyword() + 
					"\",\"content\":\"" + a.getContent().replaceAll("\"", "'") + 
					"\",\"type\":\"" + a.getType().getName() + 
					"\",\"time\":\"" + MyTools.changeTime(a.getTime()) + 
					"\",\"status\":\"" + a.getStatus().getName() + 
					"\",\"checktime\":\"" + (a.getChecktime() == null ? "" : MyTools.changeTime(a.getChecktime())) +
					"\",\"other\":\"" + (a.getOther() == null ? "":a.getOther()) +
					"\",\"see\":\"" + "²é¿´" + 
					"\"}";
			if (i != (totalCount - 1))
				data += ",";
		}
		data += "]})";
		return SUCCESS;
	}
	
	public String exportArticle() throws Exception {
		wordStream = articlemgr.findArticleToExport(id);
		
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public InputStream getWordStream() {
		return wordStream;
	}

	public void setWordStream(InputStream wordStream) {
		this.wordStream = wordStream;
	}

	
	
	
	
	
}
