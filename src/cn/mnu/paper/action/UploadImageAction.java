package cn.mnu.paper.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;


import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import cn.mnu.paper.action.base.BaseNewsAction;
import cn.mnu.paper.tools.MyTools;

public class UploadImageAction extends BaseNewsAction {
	
	private File upload;
	private File imgFile;
	
	// 使用List集合封闭多个文件域对应的文件类型
	private String uploadContentType;
	
	private String imgFileContentType;
	// 使用List集合封闭多个文件域对应的文件名
	private String uploadFileName;
	
	private String imgFileFileName;
	// 接受依赖注入的属性
	private String savePath;
	
	private String data;
	
	public String execute() throws Exception {
		String fileType = uploadFileName.substring(uploadFileName.lastIndexOf("."));
		String newName = MyTools.randomString(10) + fileType;
		
		// 以服务器的文件保存地址和文件名建立上传文件输出流
		FileOutputStream fos = new FileOutputStream(getSavePath() + "\\" + newName);
		// 以每个需要上传的文件建立文件输入流
		FileInputStream fis = new FileInputStream(getUpload());
		// 将每个需要上传的文件写到服务器对应的文件中
		byte[] buffer = new byte[512];
		int len = 0;
		while ((len = fis.read(buffer)) > 0) {
			fos.write(buffer, 0, len);
		}
		fos.close();
		setUploadFileName(newName);
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String path = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/paper/image/" + newName;  
        String callback = request.getParameter("CKEditorFuncNum");
        data += "<script type=\"text/javascript\">";
        data += "window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + path + "',''" + ")";
        data += "</script>";
		return SUCCESS;
	}
	
	public String uploadImage() throws Exception {
		
		String fileType = imgFileFileName.substring(imgFileFileName.lastIndexOf("."));
		String newName = MyTools.randomString(10) + fileType;
		//System.out.println("imgFile" + fileType + newName);
		// 以服务器的文件保存地址和文件名建立上传文件输出流
		FileOutputStream fos = new FileOutputStream(getSavePath() + "\\" + newName);
		// 以每个需要上传的文件建立文件输入流
		FileInputStream fis = new FileInputStream(getImgFile());
		// 将每个需要上传的文件写到服务器对应的文件中
		byte[] buffer = new byte[512];
		int len = 0;
		while ((len = fis.read(buffer)) > 0) {
			fos.write(buffer, 0, len);
		}
		fos.close();
		setImgFileFileName(newName);
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String path = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/paper/image/" + newName;  
        data = " {\"error\":0,\"message\":\"erorr!\",\"url\":\"" + path + "\"}";
		return SUCCESS;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getSavePath() {
		return ServletActionContext.getRequest().getSession().getServletContext().getRealPath(savePath);
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public File getImgFile() {
		return imgFile;
	}

	public void setImgFile(File imgFile) {
		this.imgFile = imgFile;
	}

	public String getImgFileContentType() {
		return imgFileContentType;
	}

	public void setImgFileContentType(String imgFileContentType) {
		this.imgFileContentType = imgFileContentType;
	}

	public String getImgFileFileName() {
		return imgFileFileName;
	}

	public void setImgFileFileName(String imgFileFileName) {
		this.imgFileFileName = imgFileFileName;
	}


}
