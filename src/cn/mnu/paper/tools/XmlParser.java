package cn.mnu.paper.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * 读改xml
 * @author MDL
 * @version 1.0
 */
public class XmlParser {
	private String xmlfilename = null;
	
	public XmlParser(String xmlfilename) {
		this.xmlfilename = xmlfilename;
	}
	
	public String getXMLRootNodeValue(String rootName, String nodeName) {
		String value = null;
		SAXReader reader = new SAXReader();
		
		try {
			Document document = reader.read(new File(xmlfilename));
			Element root = document.getRootElement();
			Iterator it = root.elementIterator(rootName);
			// 遍历结点
			while (it.hasNext()) {
				// 子结点
				Element node = (Element) it.next();
				
				value = node.elementText(nodeName);
			}
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}
	public boolean setXMLRootNodeValue(String rootName, String nodeName, String value) {
		SAXReader reader = new SAXReader();
		
		try {
			Document document = reader.read(new File(xmlfilename));
			Element root = document.getRootElement();
			Iterator it = root.elementIterator(rootName);
			// 遍历结点
			while (it.hasNext()) {
			
				Element node = (Element) it.next();
				// 子结点
				Element iNode = node.element(nodeName);
				iNode.setText(value);
			}
			// 文件输出，更新文件
			OutputFormat format = new OutputFormat("", true, "utf-8");  
			
			try {
				XMLWriter writer = new XMLWriter(new FileOutputStream(xmlfilename), format);
				writer.write(document);
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("config.xml更新成功！");
			return true;
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
}
