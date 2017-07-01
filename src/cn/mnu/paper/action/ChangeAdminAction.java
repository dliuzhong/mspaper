package cn.mnu.paper.action;

import com.opensymphony.xwork2.ModelDriven;

import cn.mnu.paper.action.base.BaseAdminAction;
import cn.mnu.paper.domain.*;

public class ChangeAdminAction extends BaseAdminAction
	implements ModelDriven<Admin> {
	private Admin model = new Admin();
	private String data;
	private Integer myid;
	private String mypw;
	private String newpw;
	private String renewpw;
	
	public String addAdmin() throws Exception {
		
		if (model != null) {
			
			if (adminmgr.passwordValid(myid, mypw)) {
				int a = adminmgr.addAdmin(model);
				//System.out.println(a);
				if (a == -1) {
					data = "{success:false,info:'添加失败！该用户名已存在！'}";
				} else if (a == -2){
					data = "{success:false,info:'该等级的管理员已达到上限！上限为4人。'}";
				} else if (a == 0){
					data = "{success:false,info:'添加失败！'}";
				} else {
					data = "{success:true}";
				}
				
			} else {
				data = "{success:false,info:'添加失败！您的密码错误！'}";
			}
		} else {
			data = "{success:false,info:'添加失败！'}";
		} 
		return SUCCESS;
	}
	
	public String changeAdmin() throws Exception {
		if (model != null) {
			if (newpw.trim().equals("")) {
				model.setPasswordNoMD5("");
			} else {
				model.setPassword(newpw);
			}
			if (adminmgr.passwordValid(myid, mypw)) {
				if (adminmgr.validateByName(model.getId(), model.getUsername())) {
					int a = adminmgr.updateAdmin(model);
					if (a == 0) {
						data = "{success:false,info:'修改失败！'}";
					} else if (a == -2) {
						data = "{success:false,info:'该等级的管理员已达到上限！上限为4人。'}";
					} else {
						data = "{success:true}";
					}
				} else {
					
					data = "{success:false,info:'修改失败！该用户名已存在！'}";
				}
			} else {
				data = "{success:false,info:'修改失败！您的密码错误！'}";
			}
		} else {
			data = "{success:false,info:'修改失败！'}";
		} 
		return SUCCESS;
	}
	
	public String deleteAdmin() throws Exception {
		if (model.getId() != 0 && adminmgr.deleteAdmin(model) != 0) {
			data = "{success:true}";
		} else {
			data = "{success:false}";
		}
		return SUCCESS;
	}
	
	
	public String changeAdminPwd() throws Exception {
		System.out.println("amdin chagnepwd");
		if (newpw.equals(renewpw)) {
			if (model != null) {
				Admin a = adminmgr.findAdminById(model.getId());
				if (a != null) {
					if (a.getPassword().equals(model.getPassword())) {
						a.setPassword(newpw);
						if (adminmgr.updateAdmin(a) != 0) {
							data = "{success:true,info:'修改成功！'}";
						} else {
							data = "{success:false,info:'修改失败！'}";
						}
					} else {
						data = "{success:false,info:'原密码不正确！'}";
					}
				} else {
					data = "{success:false,info:'用户不存在！'}";
				}
			} else {
				data = "{success:false,info:'修改失败！'}";
			}
		} else {
			data = "{success:false,info:'确认新密码与新密码不一致！'}";
		}
		System.out.println(data);
		return SUCCESS;
	}
	
	@Override
	public Admin getModel() {
		// TODO Auto-generated method stub
		return model;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Integer getMyid() {
		return myid;
	}

	public void setMyid(Integer myid) {
		this.myid = myid;
	}

	public String getMypw() {
		return mypw;
	}

	public void setMypw(String mypw) {
		this.mypw = mypw;
	}

	public String getNewpw() {
		return newpw;
	}

	public void setNewpw(String newpw) {
		this.newpw = newpw;
	}

	public String getRenewpw() {
		return renewpw;
	}

	public void setRenewpw(String renewpw) {
		this.renewpw = renewpw;
	}

}
