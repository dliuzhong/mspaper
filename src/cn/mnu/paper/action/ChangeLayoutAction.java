package cn.mnu.paper.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import cn.mnu.paper.action.base.BaseLayoutAction;
import cn.mnu.paper.domain.Layout;
import cn.mnu.paper.tools.FileDo;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public class ChangeLayoutAction extends BaseLayoutAction 
	implements ModelDriven<Layout> {
	private Layout model = new Layout();
	private Integer paperid;
	
	private String zt;
	private String bj;
	private String paperno;
	private int lay_sum;
	
	private String data;
	
	// ʹ��List���Ϸ�ն���ļ����Ӧ���ļ�����
	private List<File> upload;
	// ʹ��List���Ϸ�ն���ļ����Ӧ���ļ�����
	private List<String> uploadContentType;
	// ʹ��List���Ϸ�ն���ļ����Ӧ���ļ���
	private List<String> uploadFileName;
	// ��������ע�������
	private String savePath;
	
	public String addLayout() throws Exception {
		//model.setZt(zt);
		//model.setBj(bj);
		// ���Ҫ���ӵ�С���ܰ��������������ӵİ����Ϊԭ�ܰ�����+1
		
		if (model.getLayout_no() <= lay_sum || model.getLayout_no() != lay_sum + 1) {
			data = "{success:false}";
		} else {
			FileDo filedo = new FileDo(ServletActionContext.getRequest());
			// ����ÿ����Ҫ�ϴ����ļ�
			for (int i = 0; i < getUpload().size(); i++) {
				
				String fileType = uploadFileName.get(i).substring(
						uploadFileName.get(i).lastIndexOf("."));
				String newName = "";
				if (fileType.indexOf("pdf") != -1) {
					filedo.makdirByName("paper\\" + paperno + "\\pdf");
					
					newName = paperno + "0" + model.getLayout_no() + fileType;
					
					// �Է��������ļ������ַ���ļ��������ϴ��ļ������
					FileOutputStream fos = new FileOutputStream(getSavePath()
							+ "\\" + paperno + "\\pdf" + "\\" + newName);
					// ��ÿ����Ҫ�ϴ����ļ������ļ�������
					FileInputStream fis = new FileInputStream(getUpload().get(i));
					// ��ÿ����Ҫ�ϴ����ļ�д����������Ӧ���ļ���
					byte[] buffer = new byte[512];
					int len = 0;
					while ((len = fis.read(buffer)) > 0) {
						fos.write(buffer, 0, len);
					}
					fos.close();
					getUploadFileName().set(i, newName);
					model.setPdf(paperno + "/pdf" + "/" + newName);
				} else {
					filedo.makdirByName("paper\\" + paperno + "\\pic");
					
					newName = paperno + "0" + model.getLayout_no() + fileType;
					
					// �Է��������ļ������ַ���ļ��������ϴ��ļ������
					FileOutputStream fos = new FileOutputStream(getSavePath()
							+ "\\" + paperno + "\\pic" + "\\" + newName);
					// ��ÿ����Ҫ�ϴ����ļ������ļ�������
					FileInputStream fis = new FileInputStream(getUpload().get(i));
					// ��ÿ����Ҫ�ϴ����ļ�д����������Ӧ���ļ���
					byte[] buffer = new byte[512];
					int len = 0;
					while ((len = fis.read(buffer)) > 0) {
						fos.write(buffer, 0, len);
					}
					fos.close();
					getUploadFileName().set(i, newName);
					model.setPic(paperno + "/pic" + "/" + newName);
				}
				
			}
			int l_id;
			if (( l_id = layoutmgr.addLayout(model, paperid)) != 0) {
				int  getnum= papermgr.changePaperLay_num(paperid);
				if (getnum!= 0 && getnum > lay_sum) {
					filedo.makdirByName("paper\\" + paperno + "\\e" + model.getLayout_no());
					data = "{success:true}";
				}
					
				else
					data = "{success:false}";
				
			} else {
				data = "{success:false}";
				
			}
		}
		
		return SUCCESS;
	}
	/***
	 * �޸�zt��bj
	 * @return
	 * @throws Exception
	 */
	
	public String changeLayoutData() throws Exception {
		//System.out.println("model:" + model.getBj() + model.getZt());
		HttpServletRequest request = ServletActionContext.getRequest();
		if (layoutmgr.changeLayoutData(model, request) != 0) {
			data = "{success:true}";
		} else {
			data = "{success:false}";
		}
		
		return SUCCESS;
	}
	
	public String changeLayoutPicAndPdf() throws Exception {
		FileDo filedo = new FileDo(ServletActionContext.getRequest());
		model = layoutmgr.findLayoutByID(model.getId());
		paperno = model.getPaper().getPaper();
		// ����ÿ����Ҫ�ϴ����ļ�
		for (int i = 0; i < getUpload().size(); i++) {
			
			String fileType = uploadFileName.get(i).substring(
					uploadFileName.get(i).lastIndexOf("."));
			String newName = "";
			String savePath;

			if (fileType.indexOf("pdf") != -1) {
				
				newName = paperno + "0" + model.getLayout_no() + fileType;
				
				savePath = getSavePath() + "\\" + paperno + "\\pdf" + "\\" + newName;
				
				if (filedo.isFileExist(savePath)) {
					if (!filedo.deleteFile(savePath)) {
				    	break;
				    }
				}
				
				
				// �Է��������ļ������ַ���ļ��������ϴ��ļ������
				FileOutputStream fos = new FileOutputStream(savePath);
				// ��ÿ����Ҫ�ϴ����ļ������ļ�������
				FileInputStream fis = new FileInputStream(getUpload().get(i));
				// ��ÿ����Ҫ�ϴ����ļ�д����������Ӧ���ļ���
				byte[] buffer = new byte[512];
				int len = 0;
				while ((len = fis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				fos.close();
				getUploadFileName().set(i, newName);
				model.setPdf(paperno + "/pdf" + "/" + newName);

			} else if (fileType.indexOf("jpg") != -1 || fileType.indexOf("jpeg") != -1
						|| fileType.indexOf("bmp") != -1) {
				newName = paperno + "0" + model.getLayout_no() + fileType;
				savePath = getSavePath() + "\\" + paperno + "\\pic" + "\\" + newName;
				if (filedo.isFileExist(savePath)) {
					if (!filedo.deleteFile(savePath)) {
				    	break;
				    }
				}
				// �Է��������ļ������ַ���ļ��������ϴ��ļ������
				FileOutputStream fos = new FileOutputStream(savePath);
				// ��ÿ����Ҫ�ϴ����ļ������ļ�������
				FileInputStream fis = new FileInputStream(getUpload().get(i));
				// ��ÿ����Ҫ�ϴ����ļ�д����������Ӧ���ļ���
				byte[] buffer = new byte[512];
				int len = 0;
				while ((len = fis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				fos.close();
				getUploadFileName().set(i, newName);
				model.setPic(paperno + "/pic" + "/" + newName);

			} else {
				data = "{success:false}";
				return SUCCESS;
			}
		}
		int l_id = layoutmgr.changeLayoutPicAndPdf(model);
		
		if (l_id != 0) {
			data = "{success:true}";
		} else {
			data = "{success:false}";
		}
		return SUCCESS;
		
	}
	
	
	public String deleteLayout() throws Exception {
		if (layoutmgr.removeLayoutById(model.getId(), ServletActionContext.getRequest())) {
			data = "<script>window.parent.location.reload();</script>";
		} else {
			data = "<script>window.parent.location.reload();</script>";
		}
		return SUCCESS;
	}
	
	public Layout getModel() {
		
		return model;
	}


	public Integer getPaperid() {
		return paperid;
	}


	public void setPaperid(Integer paperid) {
		this.paperid = paperid;
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

	public String getPaperno() {
		return paperno;
	}

	public void setPaperno(String paperno) {
		this.paperno = paperno;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getLay_sum() {
		return lay_sum;
	}

	public void setLay_sum(int lay_sum) {
		this.lay_sum = lay_sum;
	}

}
