package cn.mnu.paper.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import cn.mnu.paper.action.base.BaseAdminAction;
import cn.mnu.paper.domain.*;
/***
 * Admin用户请求action
 * @author mdl
 * @version 1.0
 * @date 2014-07-12
 *
 */
public class AdminAction extends BaseAdminAction 
	implements ModelDriven<Admin> {
	private Admin model = new Admin();	// 管理类模型
	private String vercode;	// 验证码
	private int flag;
	


	public Admin getModel() {

		return model;
	}



	public String getVercode() {
		return vercode;
	}

	public void setVercode(String vercode) {
		this.vercode = vercode;
	}
	
	public String execute() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		String ver2 = (String )session.get("rand");
		// 清空Session中的随机验证码字符串。
		session.put("rand" , null);
		if (vercode.equalsIgnoreCase(ver2))
		{
			Admin a = adminmgr.loginValid(model);
			//System.out.println(model.getName());
			if (a != null)
			{
			
				flag = 0;
				if (a.getOther().trim().equals("0")) {
					flag = 1;
					// 设置Session
					int id = a.getId();
					session.clear();
					session.put("adminid" , id);
					session.put("adminGrade", a.getGrade());
					return "change_password";
				} else {
					// 设置Session
					session.clear();
					int id = a.getId();
					session.put("adminid" , id);
					session.put("adminGrade", a.getGrade());
					return SUCCESS;
				}
				
			}
			else
			{
				addActionError("用户名/密码不匹配");
				return "failure";
			}
		}
		else
		{
			addActionError("验证码不匹配,请重新输入");
			return "failure";
		}
	}
	
	public String loginout() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		if (session.get("adminid") != null) {
			session.put("adminid", null);
			session.put("adminGrade" , null);
			
		}
		return SUCCESS;
	}



	public int getFlag() {
		return flag;
	}



	public void setFlag(int flag) {
		this.flag = flag;
	}
}
