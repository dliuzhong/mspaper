package cn.mnu.paper.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import cn.mnu.paper.action.base.BaseLayoutAction;
import cn.mnu.paper.domain.Layout;
import cn.mnu.paper.tools.FileDo;


public class LayUploadAction extends BaseLayoutAction 
	implements ModelDriven<Layout>{
	private Layout model = new Layout();
	private Integer paperid;
	// 使用List集合封闭多个文件域对应的文件内容
	private String zt;
	private String bj;
	private int layout_no;
	private List<File> upload;
	// 使用List集合封闭多个文件域对应的文件类型
	private List<String> uploadContentType;
	// 使用List集合封闭多个文件域对应的文件名
	private List<String> uploadFileName;
	// 接受依赖注入的属性
	private String savePath;
	
	public String execute() throws Exception {
		model.setZt(zt);
		model.setBj(bj);
		model.setLayout_no(layout_no);
		Map session = ActionContext.getContext().getSession();
		FileDo filedo = new FileDo(ServletActionContext.getRequest());
		String newpaper = "" + session.get("newpaper");
		//System.out.println("qian");
		// 遍历每个需要上传的文件
		for (int i = 0; i < getUpload().size(); i++) {
			
			String fileType = uploadFileName.get(i).substring(
					uploadFileName.get(i).lastIndexOf("."));
			String newName = "";
			if (fileType.indexOf("pdf") != -1) {
				filedo.makdirByName("paper\\" + newpaper + "\\pdf");
				
				newName = newpaper + "0" + model.getLayout_no() + fileType;
				
				// 以服务器的文件保存地址和文件名建立上传文件输出流
				FileOutputStream fos = new FileOutputStream(getSavePath()
						+ "\\" + newpaper + "\\pdf" + "\\" + newName);
				// 以每个需要上传的文件建立文件输入流
				FileInputStream fis = new FileInputStream(getUpload().get(i));
				// 将每个需要上传的文件写到服务器对应的文件中
				byte[] buffer = new byte[512];
				int len = 0;
				while ((len = fis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				fos.close();
				getUploadFileName().set(i, newName);
				model.setPdf(newpaper + "/pdf" + "/" + newName);
			} else {
				filedo.makdirByName("paper\\" + newpaper + "\\pic");
				
				newName = newpaper + "0" + model.getLayout_no() + fileType;
				
				// 以服务器的文件保存地址和文件名建立上传文件输出流
				FileOutputStream fos = new FileOutputStream(getSavePath()
						+ "\\" + newpaper + "\\pic" + "\\" + newName);
				// 以每个需要上传的文件建立文件输入流
				FileInputStream fis = new FileInputStream(getUpload().get(i));
				// 将每个需要上传的文件写到服务器对应的文件中
				byte[] buffer = new byte[512];
				int len = 0;
				while ((len = fis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				fos.close();
				getUploadFileName().set(i, newName);
				model.setPic(newpaper + "/pic" + "/" + newName);
			}
			
		}
		//System.out.println(paperid + model.getZt() + model.getBj() + model.getLayout_no());
		Integer layoutid = layoutmgr.addLayout(model, paperid);
		if (layoutid != 0) {
			filedo.makdirByName("paper\\" + newpaper + "\\e" + model.getLayout_no());
			return SUCCESS;
		} else {
			return "failure";
		}
		
	}
	
	public Layout getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer getPaperid() {
		return paperid;
	}

	public void setPaperid(Integer paperid) {
		this.paperid = paperid;
	}

	public List<File> getUpload() {
		return upload;
	}

	public void setUpload(List<File> upload) {
		this.upload = upload;
	}

	public List<String> getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(List<String> uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public List<String> getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(List<String> uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getSavePath() {
		return ServletActionContext.getRequest().getSession().getServletContext().getRealPath(savePath);
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	public String getBj() {
		return bj;
	}

	public void setBj(String bj) {
		this.bj = bj;
	}

	public int getLayout_no() {
		return layout_no;
	}

	public void setLayout_no(int layout_no) {
		this.layout_no = layout_no;
	}
	
}
