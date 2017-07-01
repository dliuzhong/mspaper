package cn.mnu.paper.action;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ModelDriven;

import cn.mnu.paper.action.base.BaseMsuserAction;
import cn.mnu.paper.domain.Department;
import cn.mnu.paper.domain.Msuser;
import cn.mnu.paper.tools.MyTools;

public class ViewMsuserAction extends BaseMsuserAction 
	implements ModelDriven<Msuser> {
	private Msuser model = new Msuser();
	private List<Department> departmentList;
	
	private int start;
	private int limit;
	private String data;
	
	private String sort;
	private String dir;
	
	
	public String getMsusersJson() throws Exception {
		int totalCount = msusermgr.findAllMsuserSum();
				
		List<Msuser> ul = msusermgr.findAllMsuser(start, limit, sort, dir);
		Msuser u;
		// json
		data = "({\"totalCount\":" + totalCount + ",\"users\":[";
		for (int i = 0;i < ul.size(); i++) {
			u = ul.get(i);
			
			data += "{\"id\":" + u.getId() + ",\"username\":\"" + u.getUsername() + "\",\"password\":\"" + 
					u.getPassword() + "\",\"name\":\"" + u.getName() + "\",\"email\":\"" + (u.getEmail() == null ? "" : u.getEmail()) + 
					"\",\"telephone\":\"" + u.getTelephone() + 
					"\",\"qq\":\"" + u.getQq() + "\",\"type\":\"" + u.getType() + "\",\"department\":\"" + 
					(u.getDepartment() == null ? "" : u.getDepartment().getName()) +  "\",\"status\":" + u.getStatus() + ",\"datetime\":\"" + 
					MyTools.changeTime(u.getDatetime()) + 
					"\"}";
			if (i != (totalCount - 1))
				data += ",";
		}
		data += "]})";
		return SUCCESS;
	}
	
	public String viewMsuserData() throws Exception {
		if (model != null && model.getId() != 0) {
			System.out.println(model.getId());
			model = msusermgr.findMsuserById(model.getId());
			if (model != null) {
				
 				int deid;
 				if (model.getDepartment() != null) {
 					deid = model.getDepartment().getId();
 				} else {
 					deid = 0;
 				}
				

				if (model.getType().trim().equals("内部用户")) {

					departmentList = departmentmgr.findDepartmentByType(1,
							deid);

				} else {
					departmentList = departmentmgr.findDepartmentByType(2,
							deid);
				}

				return SUCCESS;
			} else {
				return "failure";
			}
		} else {
			return "failure";
		}
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

	@Override
	public Msuser getModel() {
		// TODO Auto-generated method stub
		return model;
	}

	public List<Department> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<Department> departmentList) {
		this.departmentList = departmentList;
	}


	
}
