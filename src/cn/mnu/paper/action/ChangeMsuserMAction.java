package cn.mnu.paper.action;

import cn.mnu.paper.action.base.BaseMsuserAction;
import cn.mnu.paper.domain.Msuser;

import com.opensymphony.xwork2.ModelDriven;

public class ChangeMsuserMAction extends BaseMsuserAction 
	implements ModelDriven<Msuser> {
	private Msuser model = new Msuser();
	
	
	@Override
	public Msuser getModel() {
		// TODO Auto-generated method stub
		return model;
	}

}
