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
	 * 文件创建文件夹
	 * @param dir
	 */
	public static void makeDir(File dir) {
		if(!dir.getParentFile().exists()) {
			makeDir(dir.getParentFile());
		}
		dir.mkdir();
	}
	/***
	 * 文件路径创建文件夹
	 * @param dir
	 */
	public void makdirByName(String dir_name) throws IOException {
		File file = new File(path + dir_name);
		if (!file.exists()) {
			makeDir(file);
		}
		
	}
	/**
	 * 获取应用路径
	 * @return
	 */
	public String getDir() {
		return this.path;
	}
	/***
	 * 根据文件路径，判断文件是否存在
	 * @param sPath 文件路径
	 * @return boolean 文件是否存在
	 */
	public boolean isFileExist(String sPath) {
		boolean flag = false;  
	    File file = new File(sPath);  
	    // 判断文件是否存在  
	    if (file.exists()) {
	        flag = true; 
	    }  
	    return flag;  
	}
	
	// 删除文件夹
	public boolean deleteFolder(String sname) {
		//boolean flag = false;
		//System.out.print(sname);
		sname = this.path + sname;
		//System.out.print(sname);
	    File file = new File(sname);  
	    // 判断目录或文件是否存在  
	    if (!file.exists()) {  // 不存在返回 true  
	        return true;  
	    } else {  
	        // 判断是否为文件  
	        if (file.isFile()) {  // 为文件时调用删除文件方法  
	            return deleteFile(sname);  
	        } else {  // 为目录时调用删除目录方法  
	            return deleteDirectory(sname);  
	        }  
	    }  
		
	}
	/** 
	 * 删除单个文件 
	 * @param   sPath    被删除文件的文件名 
	 * @return 单个文件删除成功返回true，否则返回false 
	 */  
	public boolean deleteFile(String sPath) {  
	    boolean flag = false;  
	    File file = new File(sPath);  
	    // 路径为文件且不为空则进行删除  
	    if (file.isFile() && file.exists()) {  
	        file.delete();  
	        flag = true; 
	    }  
	    return flag;  
	} 
	/** 
	 * 删除目录（文件夹）以及目录下的文件 
	 * @param   sPath 被删除目录的文件路径 
	 * @return  目录删除成功返回true，否则返回false 
	 */  
	public boolean deleteDirectory(String sPath) {  
	    //如果sPath不以文件分隔符结尾，自动添加文件分隔符  
	    if (!sPath.endsWith(File.separator)) {  
	        sPath = sPath + File.separator;  
	    }  
	    File dirFile = new File(sPath);  
	    //如果dir对应的文件不存在，或者不是一个目录
	    if (!dirFile.exists() || !dirFile.isDirectory()) {
	        return true;  
	    }  
	    boolean flag = true;  
	    //删除文件夹下的所有文件(包括子目录)  
	    File[] files = dirFile.listFiles();  
	    for (int i = 0; i < files.length; i++) {  
	        //删除子文件  
	        if (files[i].isFile()) {  
	            flag = deleteFile(files[i].getAbsolutePath());  
	            if (!flag) break; 
	            //log.debug("删除" + sPath);
	        } //删除子目录  
	        else {  
	            flag = deleteDirectory(files[i].getAbsolutePath());  
	            if (!flag) break; 
	            //log.debug("删除" + sPath);
	        }  
	    }  
	    if (!flag) return false;  
	    //删除当前目录  
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
