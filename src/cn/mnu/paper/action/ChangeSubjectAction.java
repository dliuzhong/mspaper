package cn.mnu.paper.action;

import com.opensymphony.xwork2.ModelDriven;

import cn.mnu.paper.action.base.BaseSubjectAction;
import cn.mnu.paper.domain.Subject;
import cn.mnu.paper.tools.MyTools;

public class ChangeSubjectAction extends BaseSubjectAction 
	implements ModelDriven<Subject> {
	
	private Subject model = new Subject();
	private String data;
	
	public String addSubject() throws Exception {
		
		if (model != null) {
			if (subjectmgr.addSubject(model) != 0) {
				data = "{success:true}";
			} else {
				data = "{success:false}";
			}
		} else {
			data = "{success:false}";
		}
		
		return SUCCESS;
	}
	
	public String changeSubject() throws Exception {
		
		if (model != null && model.getId() != 1) {
			if (subjectmgr.changeSubject(model) != 0) {
				data = "{success:true}";
			} else {
				data = "{success:false}";
			}
		} else {
			data = "{success:false}";
		}
		
		return SUCCESS;
	}
	
	public String deleteSubject() throws Exception {
		if (model.getId() != 0 && model.getId() != 1 && 
				subjectmgr.removeSubject(model.getId())) {
			data = "{success:true}";
		} else {
			data = "{success:false}";
		}
		return SUCCESS;
	}
	
	
	public String doActiveSubject() throws Exception {
		if (model.getId() != 0 && model.getId() != 1 &&
				subjectmgr.changeSubjectStTo1(model.getId()) != 0) {
			data = "{success:true}";
		} else {
			data = "{success:false}";
		}
		return SUCCESS;
	}
	
	public String doUnactiveSubject() throws Exception {
		if (model.getId() != 0 && model.getId() != 1 &&
				subjectmgr.changeSubjectStTo0(model.getId()) != 0) {
			data = "{success:true}";
		} else {
			data = "{success:false}";
		}
		return SUCCESS;
	}
	
	public Subject getModel() {
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
