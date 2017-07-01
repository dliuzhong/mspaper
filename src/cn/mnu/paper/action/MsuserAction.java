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
	private String vercode;	// 验证码
	
	public String execute() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		String ver2 = (String )session.get("rand");
		// 清空Session中的随机验证码字符串。
		session.put("rand" , null);
		if (vercode.equalsIgnoreCase(ver2))
		{
			Msuser m = msusermgr.loginValid(model);
			//System.out.println(model.getName());
			if (m != null)
			{
				model = m;
				if (m.getStatus() == 0) {
					
					// 设置Session
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
