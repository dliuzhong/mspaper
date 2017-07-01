package cn.mnu.paper.action;


import java.util.Map;

import org.apache.struts2.ServletActionContext;

import cn.mnu.paper.action.base.BasePaperAction;
import cn.mnu.paper.domain.Paper;
import cn.mnu.paper.tools.FileDo;
import cn.mnu.paper.tools.XmlParser;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public class PaperAction extends BasePaperAction 
	implements ModelDriven<Paper> {
	private Paper model = new Paper();
	private String tip;
	/**
	 * ��ȡ�����ļ���ı�ֽ������Ϣ
	 * @return SUCCESS
	 * @throws Exception
	 */
	public String startPaper() throws Exception {
		//System.out.println("����");
		// ��ȡ�����ļ�
		FileDo filedo = new FileDo(ServletActionContext.getRequest());
		String xmlName = filedo.getDir() + "WEB-INF\\config.xml";
		XmlParser xml = new XmlParser(xmlName);
		model.setZb(xml.getXMLRootNodeValue("paper", "zb"));
		model.setCb(xml.getXMLRootNodeValue("paper", "cb"));
		model.setZongb(xml.getXMLRootNodeValue("paper", "zongb"));
		model.setEmail(xml.getXMLRootNodeValue("paper", "email"));
		model.setLay_sum(Integer.parseInt(xml.getXMLRootNodeValue("paper", "laysum")));
		//System.out.println(zb + cb+ zongb+email);
		return SUCCESS;
		
	}
	/***
	 * �±���ӣ�������Ϣ���
	 * @return SUCCESS
	 * @throws Exception
	 */
	public String newPaper() throws Exception {
		Integer paperid = 0;
		model = papermgr.addPaper(model);
		paperid = model.getId();
		if (paperid != 0) {
			if (model.getPaper_url() != null && !model.getPaper_url().trim().equals("")) {
				tip = "<p style=\"color:red;\">��" + model.getPaper() + "�ڣ�����ӣ�</p>";
				return "failure";
			}
			FileDo filedo = new FileDo(ServletActionContext.getRequest());
			filedo.makdirByName("paper\\" + model.getPaper());
			
			
			Map<String, Object> session = ActionContext.getContext().getSession();
			session.put("paperid", paperid);
			session.put("newpaper", model.getPaper());
			session.put("laysum", model.getLay_sum());
			return SUCCESS;
		} else {
			tip = "<p style=\"color:red;\">���ʧ�ܣ�</p>";
			return "failure";
		}
	}
	


	@Override
	public Paper getModel() {
		// TODO Auto-generated method stub
		return model;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}

}
