package cn.mnu.paper.action;

import cn.mnu.paper.action.base.BaseMsuserAction;

public class VadidateMsuserEmailAction extends BaseMsuserAction {
	private Integer id;
	private String email;
	private String tip;
	
	public String execute() throws Exception {
		if (msusermgr.validateMsuserByEmail(id, email)) {
			
		} else {
			setTip("系统已存在该邮箱！");
		}
		return SUCCESS;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
