package cn.mnu.paper.action;


import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import cn.mnu.paper.action.base.BaseSubjectAction;
import cn.mnu.paper.domain.Subject;
import cn.mnu.paper.tools.MyTools;

public class ViewSubjectAction extends BaseSubjectAction {

	private String callback;
	private String query;
	private int start;
	private int limit;
	private String data;
	
	private String sort;
	private String dir;
	
	public String subjectsQueryJson() throws Exception {
		// 中文编码转换
		query = MyTools.toChinese(query);
		if (query.startsWith(" ") || query.equals("无")) {
			query = "";
		}
		int totalCount = subjectmgr.getQuerySubjectSum(query.trim());
		List<Subject> sl = subjectmgr.findActiveSubjectsByNameQuery(query, start, limit);
		Subject s = null;
		data = callback + "({\"totalCount\":" + totalCount + ",\"subjects\":[";
		for (int i = 0;i < sl.size(); i++) {
			s = sl.get(i);
			data += "{\"id\":" + s.getId() + ",\"name\":\"" + s.getName() + "\"}";
			if (i != (totalCount - 1))
				data += ",";
		}
		data += "]})";
		ServletActionContext.getRequest().setAttribute("data", data);
		
		return SUCCESS;
		
	}
	
	public String getSubjectsJson() throws Exception {
		int totalCount = subjectmgr.getAllSubjectSum();
		List<Subject> sl = subjectmgr.findAllSubjects(start, limit, sort, dir);
		Subject s = null;
		int sum;
		// json
		data = "({\"totalCount\":" + totalCount + ",\"subjects\":[";
		for (int i = 0;i < sl.size(); i++) {
			s = sl.get(i);
			sum = newsmgr.findNewsSumBySubject(s.getId());
			data += "{\"id\":" + s.getId() + ",\"name\":\"" + s.getName() + "\",\"desc\":\"" + 
					s.getDe() + "\",\"status\":" + s.getSt() + ",\"sum\":" + sum + ",\"datetime\":\"" + 
					MyTools.changeDate(s.getTime()) + "\"}";
			if (i != (totalCount - 1))
				data += ",";
		}
		data += "]})";
		return SUCCESS;
	}

	public String getCallback() {
		return callback;
	}

	public void setCallback(String callback) {
		this.callback = callback;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
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
