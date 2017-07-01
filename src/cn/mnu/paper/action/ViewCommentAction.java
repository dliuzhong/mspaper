package cn.mnu.paper.action;

import java.util.List;

import cn.mnu.paper.action.base.BaseCommentAction;
import cn.mnu.paper.domain.Comment;
import cn.mnu.paper.tools.MyTools;

public class ViewCommentAction extends BaseCommentAction {
	private int start;
	private int limit;
	private String data;
	
	private String sort;
	private String dir;
	
	public String getCommentsJson() throws Exception {
		int totalCount = commentmgr.findAllCommentSum();
		List<Comment> cl = commentmgr.findAllComments(start, limit, sort, dir);
		Comment c;
		// json
		data = "({\"totalCount\":" + totalCount + ",\"comments\":[";
		for (int i = 0;i < cl.size(); i++) {
			c = cl.get(i);
			data += "{\"id\":" + c.getId() + ",\"newsid\":" + c.getNews().getId() + ",\"paper\":\"" + 
					c.getNews().getPaper().getPaper() + "\",\"layout_no\":" + c.getNews().getLayout().getLayout_no() + 
					",\"name\":\"" + c.getName() + "\",\"comment\":\"" + c.getComment() + "\",\"time\":\"" + MyTools.changeDate(c.getTime()) + 
					"\",\"ip\":\"" + c.getIp() + "\",\"pass\":\"" + ((c.getPass() == 1) ? "通过" : "未通过") + 
					"\"}";
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
