package cn.mnu.paper.action;

import com.opensymphony.xwork2.ModelDriven;

import cn.mnu.paper.action.base.BaseDepartmentAction;
import cn.mnu.paper.domain.Department;

public class ChangeDepartmentAction extends BaseDepartmentAction
	implements ModelDriven<Department> {
	private Department model = new Department();
	private String data;
	
	public String addDepartment() throws Exception {
		
		if (model != null) {
			
			if (departmentmgr.addDepartment(model) != 0) {
				data = "{success:true}";
			} else {
				data = "{success:false,info:'Ìí¼ÓÊ§°Ü£¡'}";
			}
				
			
		} else {
			data = "{success:false,info:'Ìí¼ÓÊ§°Ü£¡'}";
		} 
		return SUCCESS;
	}
	
	public String changeDepartment() throws Exception {
		if (model != null) {
			if (departmentmgr.changeDepartment(model) != 0) {
				data = "{success:true}";
			} else {
				data = "{success:false,info:'ÐÞ¸ÄÊ§°Ü£¡'}";
			}
			
		} else {
			data = "{success:false,info:'ÐÞ¸ÄÊ§°Ü£¡'}";
		} 
		return SUCCESS;
	}
	
	public String deleteDepartment() throws Exception {
		if (model.getId() != 0 && departmentmgr.removeDepartment(model.getId())) {
			data = "{success:true}";
		} else {
			data = "{success:false}";
		}
		return SUCCESS;
	}
	
	@Override
	public Department getModel() {
		// TODO Auto-generated method stub
		return model;
	}


	public String getData() {
		return data;
	}


	public void setData(String data) {
		this.data = data;
	}

}
