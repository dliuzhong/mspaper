package cn.mnu.paper.tools;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;



public class FileDo {
	private String path = ""; 

	public FileDo() {
		
	}
	public FileDo(HttpServletRequest request) {
		
		path = request.getSession().getServletContext().getRealPath("/");
	}
	/***
	 * �ļ������ļ���
	 * @param dir
	 */
	public static void makeDir(File dir) {
		if(!dir.getParentFile().exists()) {
			makeDir(dir.getParentFile());
		}
		dir.mkdir();
	}
	/***
	 * �ļ�·�������ļ���
	 * @param dir
	 */
	public void makdirByName(String dir_name) throws IOException {
		File file = new File(path + dir_name);
		if (!file.exists()) {
			makeDir(file);
		}
		
	}
	/**
	 * ��ȡӦ��·��
	 * @return
	 */
	public String getDir() {
		return this.path;
	}
	/***
	 * �����ļ�·�����ж��ļ��Ƿ����
	 * @param sPath �ļ�·��
	 * @return boolean �ļ��Ƿ����
	 */
	public boolean isFileExist(String sPath) {
		boolean flag = false;  
	    File file = new File(sPath);  
	    // �ж��ļ��Ƿ����  
	    if (file.exists()) {
	        flag = true; 
	    }  
	    return flag;  
	}
	
	// ɾ���ļ���
	public boolean deleteFolder(String sname) {
		//boolean flag = false;
		//System.out.print(sname);
		sname = this.path + sname;
		//System.out.print(sname);
	    File file = new File(sname);  
	    // �ж�Ŀ¼���ļ��Ƿ����  
	    if (!file.exists()) {  // �����ڷ��� true  
	        return true;  
	    } else {  
	        // �ж��Ƿ�Ϊ�ļ�  
	        if (file.isFile()) {  // Ϊ�ļ�ʱ����ɾ���ļ�����  
	            return deleteFile(sname);  
	        } else {  // ΪĿ¼ʱ����ɾ��Ŀ¼����  
	            return deleteDirectory(sname);  
	        }  
	    }  
		
	}
	/** 
	 * ɾ�������ļ� 
	 * @param   sPath    ��ɾ���ļ����ļ��� 
	 * @return �����ļ�ɾ���ɹ�����true�����򷵻�false 
	 */  
	public boolean deleteFile(String sPath) {  
	    boolean flag = false;  
	    File file = new File(sPath);  
	    // ·��Ϊ�ļ��Ҳ�Ϊ�������ɾ��  
	    if (file.isFile() && file.exists()) {  
	        file.delete();  
	        flag = true; 
	    }  
	    return flag;  
	} 
	/** 
	 * ɾ��Ŀ¼���ļ��У��Լ�Ŀ¼�µ��ļ� 
	 * @param   sPath ��ɾ��Ŀ¼���ļ�·�� 
	 * @return  Ŀ¼ɾ���ɹ�����true�����򷵻�false 
	 */  
	public boolean deleteDirectory(String sPath) {  
	    //���sPath�����ļ��ָ�����β���Զ�����ļ��ָ���  
	    if (!sPath.endsWith(File.separator)) {  
	        sPath = sPath + File.separator;  
	    }  
	    File dirFile = new File(sPath);  
	    //���dir��Ӧ���ļ������ڣ����߲���һ��Ŀ¼
	    if (!dirFile.exists() || !dirFile.isDirectory()) {
	        return true;  
	    }  
	    boolean flag = true;  
	    //ɾ���ļ����µ������ļ�(������Ŀ¼)  
	    File[] files = dirFile.listFiles();  
	    for (int i = 0; i < files.length; i++) {  
	        //ɾ�����ļ�  
	        if (files[i].isFile()) {  
	            flag = deleteFile(files[i].getAbsolutePath());  
	            if (!flag) break; 
	            //log.debug("ɾ��" + sPath);
	        } //ɾ����Ŀ¼  
	        else {  
	            flag = deleteDirectory(files[i].getAbsolutePath());  
	            if (!flag) break; 
	            //log.debug("ɾ��" + sPath);
	        }  
	    }  
	    if (!flag) return false;  
	    //ɾ����ǰĿ¼  
	    if (dirFile.delete()) { 
	        return true;  
	    } else { 
	        return false;  
	    }  
	}
	
	public List<String> fileList(File file) {
		List<String> fileList = new ArrayList<String>();
		File[] files = file.listFiles();
		if (files != null) {
			for (File f : files) {
				fileList.add(f.getPath());
			}
		}
		return fileList;
	}
//	public static void main(String[] args) {
//		File file = new File("E:\\Web App\\MSPaper_my\\WebRoot\\manager\\system");
//		FileDo filedo = new FileDo();
//		List<String> list = filedo.fileList(file);
//		for (int i = 0;i < list.size(); i++) {
//			String filename = list.get(i);
//			System.out.println(filename);
//		}
//		
//	}
}
