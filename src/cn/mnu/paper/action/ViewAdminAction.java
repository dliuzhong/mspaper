package cn.mnu.paper.action;

import java.util.List;

import cn.mnu.paper.action.base.BaseAdminAction;
import cn.mnu.paper.domain.Admin;
import cn.mnu.paper.tools.MyTools;

public class ViewAdminAction extends BaseAdminAction {
	private int start;
	private int limit;
	private String data;
	
	private String sort;
	private String dir;
	
	public String getAdminsJson() throws Exception {
		int totalCount = adminmgr.findAllAdminSum();
		List<Admin> al = adminmgr.findAllAdmin(start, limit, sort, dir);
		Admin a;
		// json
		data = "({\"totalCount\":" + totalCount + ",\"admins\":[";
		for (int i = 0;i < al.size(); i++) {
			a = al.get(i);
			
			data += "{\"id\":" + a.getId() + ",\"username\":\"" + a.getUsername() + "\",\"password\":\"" + 
					a.getPassword() + "\",\"name\":\"" + a.getName() + "\",\"grade\":" + a.getGrade() + 
					",\"other\":\"" + a.getOther() + "\",\"datetime\":\"" + MyTools.changeTime(a.getDatetime()) + 
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
