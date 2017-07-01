package cn.mnu.paper.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import cn.mnu.paper.action.base.BaseAdminAction;
import cn.mnu.paper.domain.*;
/***
 * Admin�û�����action
 * @author mdl
 * @version 1.0
 * @date 2014-07-12
 *
 */
public class AdminAction extends BaseAdminAction 
	implements ModelDriven<Admin> {
	private Admin model = new Admin();	// ������ģ��
	private String vercode;	// ��֤��
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
		// ���Session�е������֤���ַ�����
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
					// ����Session
					int id = a.getId();
					session.clear();
					session.put("adminid" , id);
					session.put("adminGrade", a.getGrade());
					return "change_password";
				} else {
					// ����Session
					session.clear();
					int id = a.getId();
					session.put("adminid" , id);
					session.put("adminGrade", a.getGrade());
					return SUCCESS;
				}
				
			}
			else
			{
				addActionError("�û���/���벻ƥ��");
				return "failure";
			}
		}
		else
		{
			addActionError("��֤�벻ƥ��,����������");
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
