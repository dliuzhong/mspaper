package cn.mnu.paper.service.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import cn.mnu.paper.bean.SortBean;
import cn.mnu.paper.dao.AdminDao;
import cn.mnu.paper.dao.ArticleDao;
import cn.mnu.paper.dao.ArticleStatusDao;
import cn.mnu.paper.dao.ArticleTypeDao;
import cn.mnu.paper.dao.MsuserDao;
import cn.mnu.paper.domain.Admin;
import cn.mnu.paper.domain.Article;
import cn.mnu.paper.domain.ArticleStatus;
import cn.mnu.paper.domain.ArticleType;
import cn.mnu.paper.domain.Msuser;
import cn.mnu.paper.exception.PaperException;
import cn.mnu.paper.service.ArticleManager;
import cn.mnu.paper.tools.MyTools;

/***
 * Article业务逻辑组件实现类
 * @author mdl
 * @version 1.0
 * @date 2014-08-14
 */
public class ArticleManagerImpl implements ArticleManager {
	static Logger log = Logger.getLogger(
			ArticleManagerImpl.class.getName());
	private AdminDao adminDao;
	private ArticleDao articleDao;
	
	private ArticleStatusDao articleStatusDao;
	private MsuserDao msuserDao;
	private ArticleTypeDao articleTypeDao;

	public ArticleDao getArticleDao() {
		return articleDao;
	}

	public void setArticleDao(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}
	public ArticleStatusDao getArticleStatusDao() {
		return articleStatusDao;
	}

	public void setArticleStatusDao(ArticleStatusDao articleStatusDao) {
		this.articleStatusDao = articleStatusDao;
	}

	public MsuserDao getMsuserDao() {
		return msuserDao;
	}

	public void setMsuserDao(MsuserDao msuserDao) {
		this.msuserDao = msuserDao;
	}

	public ArticleTypeDao getArticleTypeDao() {
		return articleTypeDao;
	}

	public void setArticleTypeDao(ArticleTypeDao articleTypeDao) {
		this.articleTypeDao = articleTypeDao;
	}
	public AdminDao getAdminDao() {
		return adminDao;
	}

	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}
	
	/***
	 * 获取所有稿件的总数量
	 * @return 所有稿件的总数量
	 * @throws PaperException
	 */
	public int findAllArticleSum() throws PaperException {
		try {
			return articleDao.getAllArticleSum();
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("查找所有稿件总数量出现失败，请重试");
		}
	}

	/***
	 * 获取所有稿件
	 * @param start 开始
	 * @param limit 数量
	 * @param sort 排序关键字
	 * @param dir 排序方式
	 * @return 所有稿件
	 * @throws PaperException
	 */
	public List<Article> findAllArticle(int start, int limit, String sort,
			String dir) throws PaperException {
		try {
			return articleDao.getAllArticle(start, limit, sort, dir);
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("查找所有稿件出现失败，请重试");
		}
	}

	/***
	 * 获取相应状态的稿件的总数量
	 * @return 相应状态的稿件的总数量
	 * @throws PaperException
	 */
	public int findStatusArticleSum(int status) throws PaperException {
		try {
			return articleDao.getStatusArticleSum(status);
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("查找相应状态的稿件的总数量出现失败，请重试");
		}
	}

	/***
	 * 获取相应状态的稿件
	 * @param start 开始
	 * @param limit 数量
	 * @param sort 排序关键字
	 * @param dir 排序方式
	 * @return 相应状态的稿件
	 * @throws PaperException
	 */
	public List<Article> findStatusArticle(int status, int start, int limit,
			String sort, String dir) throws PaperException {
		try {
			return articleDao.getStatusArticle(status, start, limit, sort, dir);
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("查找相应状态的稿件出现失败，请重试");
		}
	}

	/***
	 * 把相应ID的状态从status_f改为status_t
	 * @param article
	 * @param status_f 原状态
	 * @param status_t 目标状态
	 * @param adminid 审核的管理员ID
	 * @return articleid
	 * @throws PaperException
	 */
	public int changeArticleStatus(Article article, int status_f, int status_t,
			Integer adminid)
			throws PaperException {
		try {
			Article a = articleDao.get(Article.class, article.getId());
			if (a != null) {
				if (a.getStatus().getId() == status_f) {
					ArticleStatus as = articleStatusDao.get(ArticleStatus.class, status_t);
					if (as != null) {
						a.setStatus(as);
						if (a.getStatus().getId() !=1 && 
								a.getStatus().getId() != 2) {
							Date now = new Date();
							if (now.before(a.getTime())) {
								a.setChecktime(a.getTime());
							} else {
								a.setChecktime(now);
							}
							
						}
						if (adminid != 0) {
							Admin admin = adminDao.get(Admin.class, adminid);
							a.setAdmin(admin);
						}
						if (status_t == 3) {
							a.setChangeinfo(MyTools.replaceBlank(article.getChangeinfo()));
						}
						if (status_t == 5) {
							a.setOther("[不予采用]原因：" + article.getOther() + "(" + MyTools.changeTime(new Date()) + ")&nbsp;&nbsp;" + 
									(a.getOther() == null ? "" : a.getOther()));
						}
						if (status_t == 1) {
							a.setChangeinfo(null);
							a.setOther(null);
						}
						if (a.getPublish() == 1) {
							a.setPublish(0);
						}
						articleDao.update(a);
						return a.getId();
					} else {
						
						return 0;
					}
				} else {
					//System.out.println("2");
					return 0;
				}
			} else {
				;
				return 0;
			}
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("修改稿件状态出现失败，请重试");
		}
	}

	/***
	 * 根据ID获取Article
	 * @param id
	 * @return 相应ID的Article
	 * @throws PaperException
	 */
	public Article findArticleById(Integer id) throws PaperException {
		try {
			return articleDao.get(Article.class, id);
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("查找相应ID的稿件出现失败，请重试");
		}
	}

	/***
	 * 管理员修改article
	 * @param article 要修改的article
	 * @param adminid
	 * @return article id
	 * @throws PaperException
	 */
	public int adminChangeEditArticle(Article article, Integer adminid) throws PaperException {
		try {
			Article ar = articleDao.get(Article.class, article.getId());
			if (ar != null) {
				ar.setTitle(article.getTitle());
				ar.setKeyword(article.getKeyword());
				ar.setContent(MyTools.replaceBlank(article.getContent()));
				String other = "<font color='blue'>已修改</font>(管理员 " + MyTools.changeTime(new Date()) + ")";
				if (ar.getOther() != null && ar.getOther().indexOf("<font color='blue'>已修改") != -1) {
					ar.setOther(ar.getOther().substring(0, ar.getOther().indexOf("<font color='blue'>已修改")) + other);
				} else {
					ar.setOther(other + (ar.getOther() == null ? "" : ar.getOther()));
				}
				if (adminid != 0) {
					Admin admin = adminDao.get(Admin.class, adminid);
					ar.setAdmin(admin);
				}
				
				articleDao.update(ar);
				return ar.getId();
			} else {
				return 0;
			}
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("管理员修改稿件出现失败，请重试");
		}
	}

	/***
	 * 根据ID导出Word
	 * @param id
	 * @return InputStream
	 * @throws PaperException
	 */
	public InputStream findArticleToExport(Integer id) throws PaperException {
		
		Article a = articleDao.get(Article.class, id);
		String content = "";
		if (a != null) {
			String c = a.getContent().replaceAll("&nbsp;", " ").replaceAll("<br/>", "\n");
			content += "标题：" + a.getTitle() + "\n"
						+ "作者：" + a.getMsuser().getName() + "\n"
						+ "关键字：" + a.getKeyword() + "\n"
						+ "类型：" + a.getType().getName() + "\n"
						+ "内容：\n" + MyTools.delHTMLTag(c) + "\n"
						+ "投稿时间：" + MyTools.changeTime(a.getTime()) + "\n"
						+ "审核意见：" + a.getStatus().getName() + "\n";
			if (a.getChecktime() != null) {
				content += "审核时间：" + MyTools.changeTime(a.getChecktime())  + "\n";
			}
		}
		//content += "</html>";
		//System.out.println(content);
		InputStream in = null;
		try {
			byte[] b = content.getBytes("UTF-8");
			ByteArrayInputStream bais = new ByteArrayInputStream(b);
			POIFSFileSystem poifs = new POIFSFileSystem();
			DirectoryEntry dirEntry = poifs.getRoot();
			dirEntry.createDocument("WordDocument", bais);
			
			File file = new File("report.doc");
			FileOutputStream out = new FileOutputStream(file);
			poifs.writeFilesystem(out);
			out.flush();
			out.close();
			bais.close();
			// 创建输出流
			log.info("管理员将ID为" + id + "稿件导出Word");
			
			
			
			in = new FileInputStream(file);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return in;
		
	}

	/***
	 * 根据ID删除article
	 * @param id
	 * @return boolean
	 * @throws PaperException
	 */
	public boolean removeArticleById(Integer id) throws PaperException {
		try {
			Article a = articleDao.get(Article.class, id);
			if (a != null) {
				articleDao.delete(a);
				return true;
			} else {
				return false;
			}
			
			
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("删除稿件出现失败，请重试");
		}
	}

	/***
	 * 根据用户ID获取所有相应的稿件数量
	 * @param id 用户ID
	 * @return 相应的稿件数量
	 * @throws PaperExcetion
	 */
	public int findArticleByMsuserSum(Integer msuserid) throws PaperException {
		try {
			return articleDao.getArticleByMsuseridSum(msuserid);
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("查找所有稿件总数量出现失败，请重试");
		}
	}

	/***
	 * 根据用户ID获取所有相应的稿件
	 * @param id 用户ID
	 * @param start 开始
	 * @param limit 数量
	 * @return List<Article>
	 * @throws PaperException
	 */
	public List<Article> findArticleByMsuser(Integer msuserid, int start, int limit)
			throws PaperException {
		try {
			return articleDao.getArticleByMsuserid(msuserid, start, limit);
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("查找所有稿件出现失败，请重试");
		}
	}



	/***
	 * 用户投稿，添加稿件
	 * @param article 添加稿件
	 * @param Msuserid 用户ID
	 * @param typeid 稿件类型ID
	 * @return 添加稿件的ID
	 * @throws PaperException
	 */
	public Integer addArticle(Article article, Integer msuserid, Integer typeid)
			throws PaperException {
		try {
			
			if (article != null) {
				// 设置外键
				Msuser m = msuserDao.get(Msuser.class, msuserid);
				ArticleType at = articleTypeDao.get(ArticleType.class, typeid);
				ArticleStatus as = articleStatusDao.get(ArticleStatus.class, 1);
				article.setMsuser(m);
				article.setType(at);
				article.setStatus(as);
				
				// 设置其它
				article.setTime(new Date());
				
				article.setContent(MyTools.replaceBlank(article.getContent()));
				//System.out.println(article.getTitle() + article.getKeyword() + article.getType().getName() + article.getStatus().getName() + article.getMsuser().getName());
				
				
				articleDao.save(article);
				return article.getId();
			} else {
				return 0;
			}
		} catch (Exception e) {
			log.debug(e.getMessage());
			System.out.println("error");
			throw new PaperException("添加稿件出现失败，请重试");
		}
	}

	/***
	 * 根据ID删除article状态为1的记录
	 * @param id
	 * @return boolean
	 * @throws PaperException
	 */
	public boolean removeArticleStatus1ById(Integer id) throws PaperException {
		try {
			Article a = articleDao.get(Article.class, id);
			if (a != null && a.getStatus().getId() == 1) {
				articleDao.delete(a);
				return true;
			} else {
				return false;
			}
			
			
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("删除稿件出现失败，请重试");
		}
	}

	/***
	 * 用户修改article
	 * @param article
	 * @return article id
	 * @throws PaperException
	 */
	public Integer changeMyArticle(Article article, Integer typeid) throws PaperException {
		try {
			Article ar = articleDao.get(Article.class, article.getId());
			if (ar != null) {
				ArticleType at = articleTypeDao.get(ArticleType.class, typeid);
				ar.setType(at);
				
				ar.setTitle(article.getTitle());
				ar.setKeyword(article.getKeyword());
				ar.setContent(MyTools.replaceBlank(article.getContent()));
				String other = "<font color='blue'>已修改</font>(用户 " + MyTools.changeTime(new Date()) + ")";
				if (ar.getOther() != null && ar.getOther().indexOf("<font color='blue'>已修改") != -1) {
					ar.setOther(ar.getOther().substring(0, ar.getOther().indexOf("<font color='blue'>已修改")) + other);
				} else {
					ar.setOther(other + (ar.getOther() == null ? "" : ar.getOther()));
				}
				
				
				articleDao.update(ar);
				return ar.getId();
			} else {
				return 0;
			}
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("管理员修改稿件出现失败，请重试");
		}
	}

	/***
	 * 根据关键字sort和id查询数量
	 * @param sort
	 * @param id
	 * @return 数量
	 */
	public int findArticleBySortSum(String sort, Integer id, String contain, Date date1, Date date2)
			throws PaperException {
		try {
			return articleDao.getArticleBySortSum(sort, id, contain, date1, date2);
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("查找稿件出现失败，请重试");
		}
	}
	
	/***
	 * 根据方式，范围，时间范围查询数量
	 * @param sort 方式
	 * @param contain 范围
	 * @param date1 时间范围 起
	 * @param date2 时间范围 终
	 * @return
	 * @throws PaperException
	 */
	public int findAllArticleBySortSum(String sortMain, String sort, String contain, Date date1,
			Date date2) throws PaperException {
		try {
			return articleDao.getArticleAllSum(sortMain, sort, contain, date1, date2);
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("查找稿件出现失败，请重试");
		}
	}

	/***
	 * 在aritlcle.other里添加信息
	 * @param data 添加的信息
	 * @param article
	 * @return article id
	 * @throws PaperException
	 */
	public int changeArticleAddDataToOther(String data, Article article)
			throws PaperException {
		try {
			Article ar = articleDao.get(Article.class, article.getId());
			if (ar != null) {
				String other = data + (ar.getOther() == null ? "" : ar.getOther());
				ar.setOther(other);
				if (article.getPublish() == 1) {
					ar.setPublish(article.getPublish());
				}
				articleDao.update(ar);
				return ar.getId();
			} else {
				return 0;
			}
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("管理员修改稿件other出现失败，请重试");
		}
	}

	/***
	 * 导出Article统计SortBean
	 * @param sbList
	 * @param allArticle
	 * @return InputStream
	 * @throws PaperException
	 */
	public InputStream findInputStreamArticles(List<SortBean> sbList, int allArticle,
			String sortMain, String sort, String contain, Date date1, Date date2, String other)
			throws PaperException {
		if (sortMain.trim().equals("depart")) {
			sortMain = "部门";
		} else if (sortMain.trim().equals("msuser")) {
			sortMain = "来稿用户";
		} else {
			sortMain = "稿件类型";
		}
		if (sort.trim().equals("all")) {
			sort = "所有";
		} else if(sort.trim().equals("in")) {
			sort = "内部";
		} else {
			sort = "外部";
		}
		if (contain.trim().equals("all")) {
			contain = "所有稿件";
		} else if (contain.trim().equals("pass")) {
			contain = "通过稿件";
		} else {
			contain = "发表稿件";
		}
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("sheet1");
		// excel样式设置
		sheet.setColumnWidth(0, 2000); 
		sheet.setColumnWidth(1, 20000);
		sheet.setColumnWidth(2, 2000);
		sheet.setColumnWidth(3, 2000);
		
		HSSFFont font = wb.createFont(); 
		font.setFontHeight((short) 240); 
		HSSFCellStyle cellStyle = wb.createCellStyle(); 
		cellStyle.setWrapText(true);
		cellStyle.setFont(font);
		
		HSSFRow row = sheet.createRow(0);

		
		HSSFCell cell = row.createCell(1);
		cell.setCellValue("统计方式：" + sortMain + "，" + sort + 
				"，" + "范围：" + contain + 
				"，" + "日期：" + ((date1 == null || date2 == null) ? "" : MyTools.changeDate(date1)) + "到" + ((date1 == null || date2 == null) ? "" : MyTools.changeDate(date2)) + other);
		cell.setCellStyle(cellStyle);
		
		// 创建标题列
		row = sheet.createRow(1);
		cell = row.createCell(0);
		cell.setCellValue("编号");
		cell.setCellStyle(cellStyle);
		cell = row.createCell(1);
		cell.setCellValue("名称");
		cell.setCellStyle(cellStyle);
		
		cell = row.createCell(2);
		cell.setCellValue("比例");
		cell.setCellStyle(cellStyle);
		
		cell = row.createCell(3);
		cell.setCellValue("数量");
		cell.setCellStyle(cellStyle);
		
		
		for (int i = 0;i < sbList.size(); i++) {
			SortBean sb = sbList.get(i);
			
			row = sheet.createRow(i + 2);
			
			cell = row.createCell(0);
			cell.setCellValue((i + 1));
			cell.setCellStyle(cellStyle);
			
			cell = row.createCell(1);
			cell.setCellValue(sb.getName());
			cell.setCellStyle(cellStyle);
			
			cell = row.createCell(2);
			cell.setCellValue((double) (Math.round(sb.getAll() * 1.0 / allArticle*100)/100.0));
			cell.setCellStyle(cellStyle);
			
			cell = row.createCell(3);
			cell.setCellValue(sb.getAll());
			
			
		}
		
		// 创建输出流
		InputStream in = null;
		try {
			File file = new File("reportSort.xls");
			OutputStream out = new FileOutputStream(file);
			wb.write(out);
			out.flush();
			out.close();
			log.info("管理员将统计导出EXCEL");
			
			
			
			in = new FileInputStream(file);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return in;
	}


	

	


	
}
