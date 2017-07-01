package cn.mnu.paper.action;

import com.opensymphony.xwork2.ModelDriven;

import cn.mnu.paper.action.base.BaseMsuserAction;
import cn.mnu.paper.domain.Msuser;

public class ChangeMsuserAction extends BaseMsuserAction 
	implements ModelDriven<Msuser> {
	private Msuser model = new Msuser();
	private String data;
	private String newpw;
	private String renewpw;
	
	private String tip;
	private Integer departmentid;
	
	public String addMsuser() throws Exception {
		
		if (model != null) {
			
			if (msusermgr.validateMsuserByEmail(0, model.getEmail())) {
				if (msusermgr.addMsuser(model) != 0) {
					data = "{success:true,info:'添加成功！'}";
				} else {
					data = "{success:false,info:'添加失败！'}";
				}
					
			} else {
				data = "{success:false,info:'添加失败！Email已存在！'}";
			}
			
		} else {
			data = "{success:false,info:'添加失败！'}";
		} 
		return SUCCESS;
	}
	
	public String changeUNPWMsuser() throws Exception {
		if (model != null) {
			if (msusermgr.validateMsuserByEmail(model.getId(), model.getEmail())) {
				if (newpw.trim().equals("")) {
					model.setPasswordNoMD5("");
				} else {
					model.setPassword(newpw);
				}
				if (msusermgr.changeUNPWMsuser(model) != 0) {
					if (msusermgr.changeMsuserType(model) != 0) {
						data = "{success:true,info:'修改成功！'}";
					} else {
						data = "{success:false,info:'修改失败！error: type'}";
					}
				} else {
					data = "{success:false,info:'修改失败！error: password'}";
				}
				
			} else {
				data = "{success:false,info:'修改失败！Email已存在！'}";
			}
			
			
		} else {
			data = "{success:false,info:'修改失败！'}";
		} 
		return SUCCESS;
	}
	
	public String deleteMsuser() throws Exception {
		if (model.getId() != 0 && msusermgr.deleteMsuser(model.getId())) {
			data = "{success:true}";
		} else {
			data = "{success:false}";
		}
		return SUCCESS;
	}
	
	public String changeMsuserData() throws Exception {
		System.out.println(model.getId() + model.getUsername());
		if (model != null && msusermgr.changeMsuser(model, departmentid) != 0) {
			tip = "修改成功！";
			return SUCCESS;
		} else {
			return "failure";
		}
		
	}
	
	public String changeMsuserPW() throws Exception {
		if (model != null) {
			if (!newpw.equals(renewpw)) {
				tip = "两次输入的新密码不一致！请重试！";
			} else {
				Msuser m = msusermgr.findMsuserById(model.getId());
				
				if (m != null) {
					if (m.getPassword().equals(model.getPassword())) {
						m.setPassword(newpw);
						if (msusermgr.changeUNPWMsuser(m) != 0) {
							tip = "修改成功！";
							
						} else {
							tip = "修改失败！";
						}
 					} else {
 						tip = "旧密码不正确！";
						
					}
				} else {
					tip = "修改失败！";
				}
			}
		} else {
			return "failure";
		}
		return SUCCESS;
	}
	
	
	@Override
	public Msuser getModel() {
		// TODO Auto-generated method stub
		return model;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getNewpw() {
		return newpw;
	}

	public void setNewpw(String newpw) {
		this.newpw = newpw;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public Integer getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(Integer departmentid) {
		this.departmentid = departmentid;
	}

	public String getRenewpw() {
		return renewpw;
	}

	public void setRenewpw(String renewpw) {
		this.renewpw = renewpw;
	}
}
