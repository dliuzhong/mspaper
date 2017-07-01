package cn.mnu.paper.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import cn.mnu.paper.action.base.BaseMsuserAction;
import cn.mnu.paper.domain.Admin;
import cn.mnu.paper.domain.Msuser;

public class MsuserAction extends BaseMsuserAction
	implements ModelDriven<Msuser> {
	private Msuser model = new Msuser();
	private String vercode;	// ��֤��
	
	public String execute() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		String ver2 = (String )session.get("rand");
		// ���Session�е������֤���ַ�����
		session.put("rand" , null);
		if (vercode.equalsIgnoreCase(ver2))
		{
			Msuser m = msusermgr.loginValid(model);
			//System.out.println(model.getName());
			if (m != null)
			{
				model = m;
				if (m.getStatus() == 0) {
					
					// ����Session
					int id = m.getId();
					session.clear();
					session.put("msuserid" , id);
					session.put("msusername" , m.getUsername());
					return "show_notice";
				} else {
					
					int id = m.getId();
					session.put("msuserid" , id);
					session.put("msusername" , m.getUsername());
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
		if (session.get("msuserid") != null) {
			session.put("msuserid", null);
			session.put("msusername" , null);
			
		}
		return SUCCESS;
	}
	
	@Override
	public Msuser getModel() {
		// TODO Auto-generated method stub
		return model;
	}

	public String getVercode() {
		return vercode;
	}

	public void setVercode(String vercode) {
		this.vercode = vercode;
	}

}
