package cn.mnu.paper.action;

import java.util.List;

import cn.mnu.paper.action.base.BaseDepartmentAction;
import cn.mnu.paper.domain.Department;
import cn.mnu.paper.domain.Msuser;
import cn.mnu.paper.tools.MyTools;

public class ViewDepartmentAction extends BaseDepartmentAction {
	private int start;
	private int limit;
	private String data;
	
	private String sort;
	private String dir;
	
	public String getDepartmentsJson() throws Exception {
		int totalCount = departmentmgr.findAllDepartmentSum();
		
		List<Department> dl = departmentmgr.findAllDepartment(start, limit, sort, dir);
		Department d;
		int sum;
		// json
		data = "({\"totalCount\":" + totalCount + ",\"departments\":[";
		for (int i = 0;i < dl.size(); i++) {
			d = dl.get(i);
			sum = msusermgr.findMsuserSumByDepartment(d.getId());
			data += "{\"id\":" + d.getId() + ",\"name\":\"" + d.getName() + "\",\"de\":\"" + 
					d.getDe() + "\",\"type\":" + d.getType() + ",\"sum\":\"" + sum + "\"}";
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
