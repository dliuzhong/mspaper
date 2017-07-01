package cn.mnu.paper.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import cn.mnu.paper.dao.LayoutDao;
import cn.mnu.paper.dao.NewsDao;
import cn.mnu.paper.dao.PaperDao;
import cn.mnu.paper.dao.SubjectDao;
import cn.mnu.paper.domain.Layout;
import cn.mnu.paper.domain.News;
import cn.mnu.paper.domain.Paper;
import cn.mnu.paper.domain.Subject;
import cn.mnu.paper.exception.PaperException;
import cn.mnu.paper.service.NewsManager;
import cn.mnu.paper.tools.FileDo;
import cn.mnu.paper.tools.MyTools;
import cn.mnu.paper.tools.NewFiles;

public class NewsManagerImpl implements NewsManager {
	static Logger log = Logger.getLogger(
			NewsManagerImpl.class.getName());
	private NewsDao newsDao;
	private PaperDao paperDao;
	private LayoutDao layoutDao;
	private SubjectDao subjectDao;
	/***
	 * 添加报纸新闻内容
	 * @param news
	 * @param paperid
	 * @param layoutid
	 * @param subjectid
	 * @return
	 * @throws Exception
	 */
	public Integer addNews(News news, Integer paperid, Integer layoutid, Integer subjectid,
			HttpServletRequest request) throws PaperException {
		try {
			news.setTitle(MyTools.replaceBlank(news.getTitle()));
			news.setAuthor(MyTools.replaceBlank(news.getAuthor()));
			news.setContent(MyTools.replaceBlank(news.getContent()));
			news.setTime(new Date());
			news.setFile_path(MyTools.randomString(20) + ".html");
			Paper p = paperDao.get(Paper.class, paperid);
			Layout l = layoutDao.get(Layout.class, layoutid);
			Subject s = subjectDao.get(Subject.class, subjectid);
			news.setPaper(p);
			news.setLayout(l);
			news.setSubject(s);	
			newsDao.save(news);
			
			if (news.getId() != 0){
				NewFiles nf = new NewFiles();
				if (nf.newContent(news, request)) {
					return news.getId();
				} else {
					return 0;
				}
			} else {
				return 0;
			}
		} catch(Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("添加新报出现失败，请重试");
		}
	}
	
	/***
	 * 添加报纸新闻内容,更新Page.html和Main.html
	 * @param news
	 * @param paperid
	 * @param layoutid
	 * @param subjectid
	 * @return
	 * @throws Exception
	 */
	public Integer addNewsRefresh(News news, Integer paperid, Integer layoutid, Integer subjectid,
			HttpServletRequest request) throws PaperException {
		try {
			news.setContent(MyTools.replaceBlank(news.getContent()));
			news.setTime(new Date());
			news.setFile_path(MyTools.randomString(20) + ".html");
			Paper p = paperDao.get(Paper.class, paperid);
			Layout l = layoutDao.get(Layout.class, layoutid);
			Subject s = subjectDao.get(Subject.class, subjectid);
			news.setPaper(p);
			news.setLayout(l);
			news.setSubject(s);	
			newsDao.save(news);
			
			if (news.getId() != 0){
				NewFiles nf = new NewFiles();
				if (nf.newContent(news, request)) {
					List<Layout> ls = layoutDao.findLayoutByPaper(paperid);
					
					List<News> ns = newsDao.findNewsByPaperAndLayout(paperid,
							l.getId());
					// 更新page和main
					if (!nf.newPageAndMain(l, ns, ls, request)) {
						return 0;
					}
					
					return news.getId();
				} else {
					return 0;
				}
			} else {
				return 0;
			}
		} catch(Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("添加新报出现失败，请重试");
		}
	}
	/***
	 * 根据期号和版面号获取所有相应的News
	 * @param paperid 期号id
	 * @param layoutid 版面号id
	 * @return 相应的News
	 * @throws Exception
	 */
	public List<News> getNewsByPaperAndLayout(Integer paperid, Integer layoutid)
			throws PaperException {
		try {
			
			return newsDao.findNewsByPaperAndLayout(paperid, layoutid);
			
		} catch(Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("查找报纸新闻内容出现失败，请重试");
		}
	}
	
	
	/***
	 * 根据 ID获取News
	 * @param id
	 * @return News
	 */
	public News findNewsById(Integer id) throws PaperException {
		try {
			
			return newsDao.get(News.class, id);
			
		} catch(Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("ID查找报纸新闻内容出现失败，请重试");
		}
	}
	
	
	/***
	 * 根据更改后的News修改News
	 * @param news 要修改的News
	 * @param subjectid 修改的专题id
	 * @return News.id
	 * @throws PaperException
	 */
	public Integer changeNews(News news, Integer subjectid, HttpServletRequest request) 
			throws PaperException {
		try {
			
			News n = newsDao.get(News.class, news.getId());
			if (n != null) {
				n.setZuo(news.getZuo());
				n.setShang(news.getShang());
				n.setKuan(news.getKuan());
				n.setGao(news.getGao());
				n.setTitle(news.getTitle());
				n.setAuthor(news.getAuthor());
				Subject s = subjectDao.get(Subject.class, subjectid);
				n.setSubject(s);
				n.setContent(MyTools.replaceBlank(news.getContent()));
				n.setFile_path(news.getFile_path());
				n.setTime(new Date());
				newsDao.update(n);
				if (n.getId() != 0) {
					NewFiles nf = new NewFiles();
					if (nf.newContent(n, request)) {
						Integer paperid = n.getPaper().getId();
						List<Layout> ls = layoutDao.findLayoutByPaper(paperid);
						for (int i = 0; i < ls.size(); i++) {
							Layout l = ls.get(i);
							List<News> ns = newsDao.findNewsByPaperAndLayout(
									paperid, l.getId());
							// 更新page和main
							if (!nf.newPageAndMain(l, ns, ls, request)) {
								return 0;
							}
						}
						return n.getId();
					} else {
						return 0;
					}
				} else {
					return 0;
				}
			} else {
				return 0;
			}
			
		} catch(Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("修改报纸新闻内容出现失败，请重试");
		}
	}
	
	/***
	 * 要所ID删除相应的新闻内容
	 * @param id[] ID数组
	 * @param request 用于更新Page.html和Main.html
	 * @return Boolean
	 * @throws PaperException
	 */
	public boolean removeNewss(Integer[] id, HttpServletRequest request)
			throws PaperException {
		try {
			News news = null;
			String filename;
			for (int i = 0;i < id.length; i++) {
				Integer paperid = id[i];
				
				news = newsDao.get(News.class, paperid);
				if (news != null) {
					newsDao.delete(News.class, paperid);
					FileDo fd = new FileDo(request);
					filename = fd.getDir() + "paper\\" + news.getPaper().getPaper() + 
							"\\e" + news.getLayout().getLayout_no() + "\\" + news.getFile_path();
					if (fd.deleteFile(filename)) {
						NewFiles nf = new NewFiles();
						List<Layout> ls = layoutDao.findLayoutByPaper(paperid);
						for (int j = 0; j < ls.size(); j++) {
							Layout l = ls.get(j);
							List<News> ns = newsDao.findNewsByPaperAndLayout(
									paperid, l.getId());
							// 更新page和main
							if (!nf.newPageAndMain(l, ns, ls, request)) {
								
								return false;
							}
						}
					} else {
						return false;
					}
					
				} else {
					return false;
				}
				
			}
			return true;
		} catch(Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("根据ID删除报纸新闻内容出现失败，请重试");
		}
	}
	
	public NewsDao getNewsDao() {
		return newsDao;
	}
	public void setNewsDao(NewsDao newsDao) {
		this.newsDao = newsDao;
	}
	public PaperDao getPaperDao() {
		return paperDao;
	}
	public void setPaperDao(PaperDao paperDao) {
		this.paperDao = paperDao;
	}
	public LayoutDao getLayoutDao() {
		return layoutDao;
	}
	public void setLayoutDao(LayoutDao layoutDao) {
		this.layoutDao = layoutDao;
	}
	public SubjectDao getSubjectDao() {
		return subjectDao;
	}
	public void setSubjectDao(SubjectDao subjectDao) {
		this.subjectDao = subjectDao;
	}

	/***
	 * 获取所有News的总数
	 * @return
	 * @throws PaperException
	 */
	public int findAllNewsSum() throws PaperException {
		try {
			int sum = newsDao.getAllRows();
			return sum;
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("查找所有News总数量出现失败，请重试");
		}
	}

	/***
	 * 根据搜索方式和关键字获取Newsr的总数
	 * @param work 搜索方式
	 * @param query 关键字
	 * @return
	 * @throws PaperException
	 */
	public int findNewsSumByWorkAndQuerySum(String work, String query)
			throws PaperException {
		try {
			int sum = newsDao.getAllRowsByWorkAndQuery(work, query);
			return sum;
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("查找所有News总数量出现失败，请重试");
		}
	}

	/***
	 * 根据搜索方式和关键字获取News
	 * @param work 搜索方式
	 * @param query 关键字
	 * @return
	 * @throws PaperException
	 */
	public List<News> findNewsByWorkAndQurey(String work, String query, 
			int start, int limit) throws PaperException {
		try {
			List<News> nl = newsDao.getNewsByWorkQuery(work, query);
			List<News> n = new ArrayList<News>();
			News news;
			for (int i = 0;i < nl.size(); i++) {
				news = nl.get(i);
				//System.out.println(sb.getName());
				if (i >= start && i < start + limit) {
					
					n.add(news);
				}
			}
			return n;
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("查找所有News出现失败，请重试");
		}
	}

	/***
	 * 根据报纸ID、专题ID、年份、作者组合搜索总数
	 * @param paperid
	 * @param subjectid
	 * @param year
	 * @param author
	 * @return
	 * @throws PaperException
	 */
	public int findNewsSumFind(Integer paperid, Integer subjectid, String year,
			String author) throws PaperException {
		try {
			int sum = newsDao.getNewsByFindSum(paperid, subjectid, year, author);
			return sum;
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("查找所有News总数量出现失败，请重试");
		}
	}

	/***
	 * 根据搜索方式和关键字获取News
	 * @param work 搜索方式
	 * @param query 关键字
	 * @param start
	 * @param limit
	 * @return List<News>
	 * @throws PaperException
	 */
	public List<News> findNewsFind(Integer paperid, Integer subjectid,
			String year, String author, int start, int limit)
			throws PaperException {
		try {
			List<News> nl = newsDao.getNewsByFind(paperid, subjectid, year, author);
			
			List<News> n = new ArrayList<News>();
			News news;
			for (int i = 0;i < nl.size(); i++) {
				news = nl.get(i);
				//System.out.println(sb.getName());
				if (i >= start && i < start + limit) {
					
					n.add(news);
				}
			}
			return n;
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("查找所有News出现失败，请重试");
		}
	}

	/***
	 * 根据搜索方式和关键字获取News
	 * @param work
	 * @param query
	 * @return List<News>
	 * @throws PaperException
	 */
	public List<News> findNewsByWorkAndQurey(String work, String query)
			throws PaperException {
		try {
			List<News> nl = newsDao.getNewsByWorkQuery(work, query);
			
			return nl;
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("查找所有News出现失败，请重试");
		}
	}

	/***
	 * 根据报纸ID、专题ID、年份、作者组合搜索
	 * @param paperid
	 * @param subjectid
	 * @param year
	 * @param author
	 * @return
	 * @throws PaperException
	 */
	public List<News> findNewsFind(Integer paperid, Integer subjectid,
			String year, String author) throws PaperException {
		try {
			List<News> nl = newsDao.getNewsByFind(paperid, subjectid, year, author);
			
			return nl;
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("查找所有News出现失败，请重试");
		}
	}

	/***
	 * POI导出InputStream->EXCEL
	 * @param newss
	 * @return
	 * @throws PaperException
	 */
	public InputStream findInputStreamNews(List<News> newss)
			throws PaperException {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("sheet1");
		// excel样式设置
		sheet.setColumnWidth(0, 2000); 
		sheet.setColumnWidth(1, 20000);
		sheet.setColumnWidth(2, 3000);
		sheet.setColumnWidth(3, 2000);
		sheet.setColumnWidth(4, 18000);
		sheet.setColumnWidth(5, 18000);
		sheet.setColumnWidth(6, 5000);
		sheet.setColumnWidth(7, 7000);
		HSSFFont font = wb.createFont(); 
		font.setFontHeight((short) 240); 
		HSSFCellStyle cellStyle = wb.createCellStyle(); 
		cellStyle.setWrapText(true);
		cellStyle.setFont(font);
		
		HSSFRow row = sheet.createRow(0);
		// 创建标题列
		HSSFCell cell = row.createCell(0);
		cell.setCellValue("编号");
		cell.setCellStyle(cellStyle);
		cell = row.createCell(1);
		cell.setCellValue("标题");
		cell.setCellStyle(cellStyle);
		
		cell = row.createCell(2);
		cell.setCellValue("期号");
		cell.setCellStyle(cellStyle);
		
		cell = row.createCell(3);
		cell.setCellValue("版号");
		cell.setCellStyle(cellStyle);
		
		cell = row.createCell(4);
		cell.setCellValue("作者");
		cell.setCellStyle(cellStyle);
		
		cell = row.createCell(5);
		cell.setCellValue("专题");
		cell.setCellStyle(cellStyle);
		
		cell = row.createCell(6);
		cell.setCellValue("出版日期");
		cell.setCellStyle(cellStyle);
		
		cell = row.createCell(7);
		cell.setCellValue("最后编辑时间");
		cell.setCellStyle(cellStyle);
		
		// 循环resultList中的LayoutBean数据
		//System.out.print("size:" + resultList.size());
		for (int i = 0;i < newss.size(); i++) {
			News news = newss.get(i);
			
			row = sheet.createRow(i + 1);
			
			cell = row.createCell(0);
			cell.setCellValue((i + 1));
			cell.setCellStyle(cellStyle);
			
			cell = row.createCell(1);
			cell.setCellValue(news.getTitle());
			cell.setCellStyle(cellStyle);
			
			cell = row.createCell(2);
			cell.setCellValue(news.getPaper().getPaper());
			cell.setCellStyle(cellStyle);
			
			cell = row.createCell(3);
			cell.setCellValue(news.getLayout().getLayout_no());
			
			cell = row.createCell(4);
			cell.setCellValue(news.getAuthor());
			cell.setCellStyle(cellStyle);
			
			cell = row.createCell(5);
			cell.setCellValue(news.getSubject().getName());
			cell.setCellStyle(cellStyle);
			
			cell = row.createCell(6);
			cell.setCellValue(MyTools.changeDate(news.getPaper().getCbtime()));
			cell.setCellStyle(cellStyle);
			
			cell = row.createCell(7);
			cell.setCellValue(MyTools.changeTime(news.getTime()));
			cell.setCellStyle(cellStyle);
		}
		
		// 创建输出流
		InputStream in = null;
		try {
			File file = new File("report.xls");
			OutputStream out = new FileOutputStream(file);
			wb.write(out);
			out.flush();
			out.close();
			log.info("管理员将新闻搜索导出EXCEL");
			
			
			
			in = new FileInputStream(file);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return in;
		
		
	}

	/***
	 * 根据subjectid获取相应新闻的数量
	 * @param subjectid
	 * @return int
	 * @throws PaperException
	 */
	public int findNewsSumBySubject(Integer subjectid) throws PaperException {
		try {
			return newsDao.getNewsSumBySubject(subjectid);
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("根据subjectid获取相应News的总数出现失败，请重试");
		}
	}

	/***
	 * 根据搜索字段和关键字获取所有news记录数量
	 * @param select
	 * @param words
	 * @return int
	 * @throws PaperException
	 */
	public int findNewsBySelectWordsSum(String select, String words)
			throws PaperException {
		try {
			int sum = newsDao.getNewsBySelectWordsSum(select, words);
			return sum;
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("搜索字段和关键字News总数量出现失败，请重试");
		}
	}

	/***
	 * 根据搜索字段和关键字获取所有news记录
	 * @param start
	 * @param limit
	 * @param select
	 * @param words
	 * @return List<News>
	 * @throws PaperException
	 */
	public List<News> findNewsBySelectWords(int start, int limit,
			String select, String words) throws PaperException {
		try {
			return newsDao.getNewsBySelectWords(start, limit, select, words);
		
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("搜索字段和关键字News出现失败，请重试");
		}
	}

	
	
	


	

}
