package cn.mnu.paper.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


import cn.mnu.paper.domain.Layout;
import cn.mnu.paper.domain.News;
import cn.mnu.paper.domain.Paper;


public class NewFiles {
   
	// ����ģ����������html
	public boolean newContent(News news, HttpServletRequest request) {
		try {
		    //ģ��·��
			FileDo file = new FileDo(request);
		    String filePath = file.getDir() + "\\WEB-INF\\content\\temp\\contentTemp.html";
		    
		  //System.out.print(filePath);
		    //log.debug(filePath);
		    FileInputStream fileinputstream = new FileInputStream(filePath);// ��ȡģ���ļ�
		    // ��ģ���е��ַ�������String��
		    int lenght = fileinputstream.available();
		    byte bytes[] = new byte[lenght];
		    fileinputstream.read(bytes);
		    fileinputstream.close();
		    String templateContent = new String(bytes);
		     
		    templateContent = templateContent.replaceAll("###title###", news.getTitle());
		    templateContent = templateContent.replaceAll("###author###", news.getAuthor());
		    templateContent = templateContent
		      .replaceAll("###content###", news.getContent());// �滻��ģ������Ӧ�ĵط�
		    templateContent = templateContent
		      .replaceAll("###newsid###", "" + news.getId());
//		    templateContent = templateContent
//		      .replaceAll("###paperid###", "" + news.getPaper().getId());
//		    templateContent = templateContent
//		      .replaceAll("###layid###", "" + news.getLayout().getId());
		    String filename = news.getFile_path();
		    //System.out.println(filename);
		    file.makdirByName("paper\\" + news.getPaper().getPaper() + "\\e" + news.getLayout().getLayout_no());
		    filename = file.getDir() + "paper\\" + news.getPaper().getPaper() + "\\e" + news.getLayout().getLayout_no() + "\\" + filename;// ���ɵ�html�ļ�����·����
		    //System.out.println(templateContent);
		    if (file.isFileExist(filename)) {
		    	if (!file.deleteFile(filename)) {
			    	return false;
			    }
		    }
		    FileOutputStream fileoutputstream = new FileOutputStream(filename);// �����ļ������
		    //System.out.print("�ļ����·��:");
		   // System.out.print(filename);
		    //log.debug("�ļ����·��:" + filename);
		    byte tag_bytes[] = templateContent.getBytes();
		    fileoutputstream.write(tag_bytes);
		    fileoutputstream.close();
		    return true;
		} catch (Exception e) {
			System.out.print(e.toString());
			return false;
		}
	}
	// ����ģ������page.html
	public boolean newPageHtml(Layout layout, List<News> newss,  HttpServletRequest request) {
		/*
		try {
		    //ģ��·��
			FileDo file = new FileDo(request);
		    String filePath = file.getDir() + "\\admin\\temp\\pageTemp.html";
		    //System.out.print(filePath);
		    log.debug(filePath);
		    FileInputStream fileinputstream = new FileInputStream(filePath);// ��ȡģ���ļ�
		    // ��ģ���е��ַ�������String��
		    int lenght = fileinputstream.available();
		    byte bytes[] = new byte[lenght];
		    fileinputstream.read(bytes);
		    fileinputstream.close();
		    String templatePage = new String(bytes);
		    //System.out.print(templatePage);
		    // �滻
		    templatePage = templatePage.replaceAll("###pic_url###", layout.getPic());
		    // ѭ�����html���ݣ�ÿ�����ŵ�url�����꣩
		    int i;
		    for (i = 0;i < list.size(); i++) {
		    	LayoutBean layout = list.get(i);
		    	templatePage = templatePage + "<a href='" + layout.getFile_path() + "' target='main'><div title='" + layout.getTitle() + "' id='" + (i + 1) + "' class='box'" +
		    			" style='position: absolute; z-index: 990;left:" + (Integer.parseInt(layout.getLeft()) + 10) + "px;top: " + (Integer.parseInt(layout.getTop()) + 10) + "px;width: " + 
		    			layout.getWidth() + "px;height: " + layout.getHeight() + "px;cursor: pointer;'></div></a>";
		    }
		    templatePage = templatePage + "</body>                </html>";
		    log.debug(templatePage);
		    String filename = file.getDir() + paper_id + "\\e" + lay_id + "\\page.html";// ���ɵ�html�ļ�����·����
		    FileOutputStream fileoutputstream = new FileOutputStream(filename);// �����ļ������
		    //System.out.print("�ļ����·��:");
		   // System.out.print(filename);
		    log.debug("�ļ����·��:" + filename);
		    byte tag_bytes[] = templatePage.getBytes();
		    fileoutputstream.write(tag_bytes);
		    fileoutputstream.close();
		    return true;
		} catch (Exception e) {
			System.out.print(e.toString());
			return false;
		}
		*/
		try {
		    //ģ��·��
			FileDo file = new FileDo(request);
		    String filePath = file.getDir() + "\\WEB-INF\\content\\temp\\pageTemp.html";
		    
		    FileInputStream fileinputstream = new FileInputStream(filePath);// ��ȡģ���ļ�

		    InputStreamReader isr = new InputStreamReader(fileinputstream,"UTF-8"); 
		    BufferedReader br = new BufferedReader(isr); 
		    String templatePage = "";
		    String str = "";
		    while((str=br.readLine()) != null){ 
		    	templatePage += str + "\n";
		    }
		    // �滻
		    templatePage = templatePage.replaceAll("###pic_url###", layout.getPic());
		
		    for (int i = 0;i < newss.size(); i++) {
		    	News n = newss.get(i);
		    	templatePage = templatePage + "<a href='" + n.getFile_path() + "' target='main'><div title='" + n.getTitle() + "' id='" + (i + 1) + "' class='box'" +
		    			" style='position: absolute; z-index: 990;left:" + (n.getZuo()+8) + "px;top: " + (n.getShang()+5) + "px;width: " + 
		    			n.getKuan() + "px;height: " + n.getGao() + "px;cursor: pointer;'></div></a>\n";
		    }
		    templatePage = templatePage + "</body>\n</html>";
		    
		    String filename = file.getDir() +"paper\\" + layout.getPaper().getPaper() + "\\e" + layout.getLayout_no() + "\\page.html";// ���ɵ�html�ļ�����·��
		    //System.out.println("1:" + filename);
		    if (file.isFileExist(filename)) {
		    	if (!file.deleteFile(filename)) {
			    	return false;
			    }
		    }
		    
		    
		    //System.out.println(filename);
		    FileOutputStream fileoutputstream = new FileOutputStream(filename, true);// �����ļ������
		   
		    OutputStreamWriter oWriter = new OutputStreamWriter(fileoutputstream,"UTF-8");
		    BufferedWriter bWriter = new BufferedWriter(oWriter);
		    bWriter.write(templatePage);
		    bWriter.close();
		    return true;
		} catch (Exception e) {
			System.out.print(e.toString());
			return false;
		}
	}
	// ����ģ����������index.jsp
	public boolean newIndex(Paper paper, HttpServletRequest request) {
		
		try {
		    //ģ��·��
			FileDo file = new FileDo(request);
		    String filePath = file.getDir() + "\\WEB-INF\\content\\temp\\indexTemp.jsp";
		    
		    FileInputStream fileinputstream = new FileInputStream(filePath);// ��ȡģ���ļ�

		    InputStreamReader isr = new InputStreamReader(fileinputstream,"UTF-8"); 
		    BufferedReader br = new BufferedReader(isr); 
		    String templateContent = "";
		    String str = "";
		    while((str=br.readLine()) != null){ 
		    	templateContent += str + "\n";
		    }
		     
		    templateContent = templateContent.replaceAll("###zb###","���죺" + paper.getZb());
		    templateContent = templateContent.replaceAll("###cb###", "���棺" + paper.getCb());
		    templateContent = templateContent.replaceAll("###zongb###", "�ܱࣺ" + paper.getZongb());
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd��"); 
		    templateContent = templateContent.replaceAll("###cbtime###", "�������ڣ�" + sdf.format(paper.getCbtime()));
		    templateContent = templateContent.replaceAll("###email###", "�������䣺" + paper.getEmail());
		    templateContent = templateContent.replaceAll("###lay_sum###", "" + paper.getLay_sum());
		    templateContent = templateContent.replaceAll("###paper###", "��" + paper.getPaper() + "��(���ڹ�" + paper.getLay_sum() + "�棩");
		    templateContent = templateContent.replaceAll("###id###", "" + paper.getId());
		    
		    String filename = file.getDir() + "paper\\" + paper.getPaper() + "\\index.jsp";
		    //System.out.println("1:" + filename);
		    if (file.isFileExist(filename)) {
		    	if (!file.deleteFile(filename)) {
			    	return false;
			    }
		    }
		    
		    
		    //System.out.println(filename);
		    FileOutputStream fileoutputstream = new FileOutputStream(filename, true);// �����ļ������
		   
		    OutputStreamWriter oWriter = new OutputStreamWriter(fileoutputstream,"UTF-8");
		    BufferedWriter bWriter = new BufferedWriter(oWriter);
		    bWriter.write(templateContent);
		    bWriter.close();
		    return true;
		} catch (Exception e) {
			System.out.print(e.toString());
			return false;
		}
	}
	
	// ����ģ������main.html
	public boolean newMainHtml(Layout layout, List<News> newss, List<Layout> layouts, HttpServletRequest request) {
		
		try {
		    //ģ��·��
			FileDo file = new FileDo(request);
			// ��һ����
		    String filePath1 = file.getDir() + "\\WEB-INF\\content\\temp\\mainTemp1.html";
		    //System.out.print(filePath1);
		    //log.debug(filePath1);
		    FileInputStream fileinputstream1 = new FileInputStream(filePath1);// ��ȡģ���ļ�
		    // ��ģ���е��ַ�������String��
		    int lenght1 = fileinputstream1.available();
		    byte bytes1[] = new byte[lenght1];
		    fileinputstream1.read(bytes1);
		    fileinputstream1.close();
		    String templateMain1 = new String(bytes1);
		    //System.out.print(templateMain1);
		  
		    // �滻1
		    templateMain1 = templateMain1.replaceAll("###lay_no###", "��" + MyTools.toChineseNumber(layout.getLayout_no() + "") + "��");
		    templateMain1 = templateMain1.replaceAll("###zt###", layout.getZt());
		    templateMain1 = templateMain1.replaceAll("###newslist###", "�������б�");
		    
		    // ѭ�����html���ݣ�ÿ�����ŵ�url�����꣩
		    
		    for (int i = 0;i < newss.size(); i++) {
		    	News n = newss.get(i);
		    	templateMain1 = templateMain1 + "\n<tr>\n<td><a href='" + n.getFile_path() + "'>" + n.getTitle() + "</a></td>\n</tr>\n";
		    }
		    templateMain1 += "</table>\n" 
		    	+ "</td>\n"
                + "<td width='30%'>\n"
                  + "<table class='downTable' cellpadding='5' cellspacing='0' >\n"
                   + "<tr bgcolor='#0061AE' align='center' valign='middle' style='color:#FFFFFF'>\n"
                      + "<th>\n"
                      + "��PDF������</th>\n"
                    + "</tr>\n";
		    // �ڶ�����
		    String filePath2 = file.getDir() + "\\WEB-INF\\content\\temp\\mainTemp2.html";
		    //System.out.print(filePath2);
		    //log.debug(filePath2);
		    FileInputStream fileinputstream2 = new FileInputStream(filePath2);// ��ȡģ���ļ�
		    // ��ģ���е��ַ�������String��
		    int lenght2 = fileinputstream2.available();
		    byte bytes2[] = new byte[lenght2];
		    fileinputstream2.read(bytes2);
		    fileinputstream2.close();
		    String templateMain2 = new String(bytes2);
		    //System.out.print(templateMain2);
		    for (int n = 0;n < layouts.size(); ++n) {
		    	Layout l = layouts.get(n);
		    	templateMain1 = templateMain1 + "\n<tr>\n<td><a href='../../" + l.getPdf() + "'>��" + MyTools.toChineseNumber((n + 1) + "")  + "��<img src='../../img/pdf.png' style='border: none;'/></a></a></td>\n</tr>\n";
		    }
		    templateMain2 = templateMain2.replaceAll("###bj###", "����༭/У�ԣ�" + layout.getBj());
		    // �ϲ�
		    String templateMain = templateMain1 + templateMain2;
		   
		    String filename = file.getDir() + "paper\\" + layout.getPaper().getPaper() + "\\e" + layout.getLayout_no() + "\\main.html";// ���ɵ�html�ļ�����·����
		    if (file.isFileExist(filename)) {
		    	if (!file.deleteFile(filename)) {
			    	return false;
			    }
		    }
		    FileOutputStream fileoutputstream = new FileOutputStream(filename);// �����ļ������
		    //System.out.print("�ļ����·��:");
		    //System.out.print(filename);
		    
		    byte tag_bytes[] = templateMain.getBytes();
		    fileoutputstream.write(tag_bytes);
		    fileoutputstream.close();
		    return true;
		} catch (Exception e) {
			System.out.print(e.toString());
			return false;
		}
		/*
		try {
		    //ģ��·��
			FileDo file = new FileDo(request);
			// ��һ����
		    String filePath1 = file.getDir() + "\\WEB-INF\\content\\temp\\mainTemp1.html";
		    
		    FileInputStream fileinputstream1 = new FileInputStream(filePath1);// ��ȡģ���ļ�

		    InputStreamReader isr1 = new InputStreamReader(fileinputstream1,"utf-8"); 
		    BufferedReader br1 = new BufferedReader(isr1); 
		    String templateMain1 = "";
		    String str1 = "";
		    while((str1 = br1.readLine()) != null){ 
		    	templateMain1 += str1 + "\n";
		    }
		     
		    // �滻1
		    templateMain1 = templateMain1.replaceAll("###lay_no###", "��" + MyTools.toChineseNumber(layout.getLayout_no() + "") + "��");
		    templateMain1 = templateMain1.replaceAll("###zt###", layout.getZt());
		    templateMain1 = templateMain1.replaceAll("###newslist###", "�������б�");
		    
		    // ѭ�����html���ݣ�ÿ�����ŵ�url�����꣩
		    
		    for (int i = 0;i < newss.size(); i++) {
		    	News n = newss.get(i);
		    	templateMain1 = templateMain1 + "\n<tr>\n<td><a href='" + n.getFile_path() + "'>" + n.getTitle() + "</a></td>\n</tr>\n";
		    }
		    templateMain1 += "</table>\n" 
		    	+ "</td>\n"
                + "<td width='30%'>\n"
                  + "<table class='downTable' cellpadding='5' cellspacing='0' >\n"
                   + "<tr bgcolor='#0061AE' align='center' valign='middle' style='color:#FFFFFF'>\n"
                      + "<th>\n"
                      + "��PDF������</th>\n"
                    + "</tr>\n";
		    // �ڶ�����
		    String filePath2 = file.getDir() + "\\WEB-INF\\content\\temp\\mainTemp2.html";
		    FileInputStream fileinputstream2 = new FileInputStream(filePath2);// ��ȡģ���ļ�

		    InputStreamReader isr2 = new InputStreamReader(fileinputstream2,"utf-8"); 
		    BufferedReader br2 = new BufferedReader(isr2); 
		    String templateMain2 = "";
		    String str2 = "";
		    while((str2 = br2.readLine()) != null){ 
		    	templateMain2 += str2 + "\n";
		    }
		   
		    for (int n = 0;n < layouts.size(); ++n) {
		    	Layout l = layouts.get(n);
		    	templateMain1 = templateMain1 + "\n<tr>\n<td><a href='../../" + l.getPdf() + "'>��" + MyTools.toChineseNumber((n + 1) + "")  + "��<img src='../../img/pdf.png' style='border: none;'/></a></a></td>\n</tr>\n";
		    }
		    templateMain2 = templateMain2.replaceAll("###bj###", "����༭/У�ԣ�" + layout.getBj());
		    // �ϲ�
		    String templateMain = templateMain1 + templateMain2;
		    
		    String filename = file.getDir() + "paper\\" + layout.getPaper().getPaper() + "\\e" + layout.getLayout_no() + "\\main.html";// ���ɵ�html�ļ�����·����
		    //System.out.println("1:" + filename);
		    if (file.isFileExist(filename)) {
		    	if (!file.deleteFile(filename)) {
			    	return false;
			    }
		    }
		    
		    //System.out.println(filename);
		    FileOutputStream fileoutputstream = new FileOutputStream(filename, true);// �����ļ������
		   
		    OutputStreamWriter oWriter = new OutputStreamWriter(fileoutputstream,"UTF-8");
		    BufferedWriter bWriter = new BufferedWriter(oWriter);
		    bWriter.write(templateMain);
		    bWriter.close();
		    return true;
		} catch (Exception e) {
			System.out.print(e.toString());
			return false;
		}
		*/
	}
	/***
	 * ����Page.html��Main.html
	 * @param layout
	 * @param newss
	 * @param layouts
	 * @param request
	 * @return Boolean
	 */
	public Boolean newPageAndMain(Layout layout, List<News> newss, List<Layout> layouts,  HttpServletRequest request) {
		if (newPageHtml(layout, newss, request) && newMainHtml(layout, newss, layouts, request)) {
			return true;
		} else {
			return false;
		}
	}
}
