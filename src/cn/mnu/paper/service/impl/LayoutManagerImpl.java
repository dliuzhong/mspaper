package cn.mnu.paper.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import cn.mnu.paper.dao.LayoutDao;
import cn.mnu.paper.dao.NewsDao;
import cn.mnu.paper.dao.PaperDao;
import cn.mnu.paper.domain.Layout;
import cn.mnu.paper.domain.News;
import cn.mnu.paper.domain.Paper;
import cn.mnu.paper.exception.PaperException;
import cn.mnu.paper.service.LayoutManager;
import cn.mnu.paper.tools.FileDo;
import cn.mnu.paper.tools.NewFiles;

/***
 * Paper业务逻辑组件实现类
 * @author com
 * @version 1.0
 * @date 2014-07-15
 */
public class LayoutManagerImpl implements LayoutManager{
	static Logger log = Logger.getLogger(
			LayoutManagerImpl.class.getName());
	private LayoutDao layoutDao;
	private PaperDao paperDao;
	private NewsDao newsDao;
	
	/***
	 * 添加新的报纸版面信息
	 * @param layout
	 * @param paperid
	 * @return
	 * @throws PaperException
	 */
	public int addLayout(Layout layout, Integer paperid) throws PaperException {
		try {
			
			Paper p = paperDao.get(Paper.class, paperid);
			if (p != null) {
				layout.setPaper(p);
				Layout l = findLayoutByPaperAndLayno(paperid, layout.getLayout_no());
				if (l != null) {
					l.setPic(layout.getPic());
					l.setPdf(layout.getPdf());
					l.setZt(layout.getZt());
					l.setBj(layout.getBj());
					layoutDao.update(l);
					return l.getId();
				} else {
					layoutDao.save(layout);
					return layout.getId();
				}
			} else {
				return 0;
			}
			
			
		} catch(Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("添加新的报纸版面信息出现失败，请重试");
		}
	}

	/***
	 * 验证是否已有该报纸版面信息
	 * @param paper 需要验证的期号报纸
	 * @param layno 需要验证的版面
	 * @return boolean
	 * @throws PaperException
	 */
	public boolean validateLayoutByPaperAndLayno(Integer paperid, int layno)
			throws PaperException {
		try {
			if (layoutDao.findLayoutByPaperAndLayno(paperid, layno) != null) {
				return true;
			} else {
				return false;
			}
				
			
		} catch(Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("按期号版面号找查报纸版面信息出现失败，请重试");
			
		}
	}

	/***
	 * 验证是否已有该报纸版面信息
	 * @param paper 需要验证的期号报纸
	 * @param layno 需要验证的版面
	 * @return Layout
	 * @throws PaperException
	 */
	public Layout findLayoutByPaperAndLayno(Integer paperid, int layno)
			throws PaperException {
		try {
			Layout l = layoutDao.findLayoutByPaperAndLayno(paperid, layno);
			if (l != null) {
				return l;
			} else {
				return null;
			}
				
			
		} catch(Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("按期号版面号找查报纸版面信息出现失败，请重试");
			
		}
	}

	/***
	 * 根据paperid报纸期号ID查找所有版面信息
	 * @param paperid 报纸期号ID
	 * @return List<Layout> 所有版面信息
	 * @throws PaperException
	 */
	public List<Layout> getLayoutByPaper(Integer paperid)
			throws PaperException {
		try {
			return layoutDao.findLayoutByPaper(paperid);
			
		} catch(Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("按期号找查报纸版面信息出现失败，请重试");
			
		}
	}
	
	public LayoutDao getLayoutDao() {
		return layoutDao;
	}

	public void setLayoutDao(LayoutDao layoutDao) {
		this.layoutDao = layoutDao;
	}

	public PaperDao getPaperDao() {
		return paperDao;
	}

	public void setPaperDao(PaperDao paperDao) {
		this.paperDao = paperDao;
	}

	/***
	 * 修改版面基本信息Layout
	 * @param layout 要修改的Layout
	 * @param request 用于修改main.html
	 * @return layoutid
	 * @throws PaperException
	 */
	public int changeLayoutData(Layout layout, HttpServletRequest request)
			throws PaperException {
		try {
			Layout l = layoutDao.get(Layout.class, layout.getId());
			if (l != null) {
				l.setZt(layout.getZt());
				l.setBj(layout.getBj());
				layoutDao.update(l);
				if (l.getId() != 0) {
					// 更新文件main.html
					List<Layout> layouts = layoutDao.findLayoutByPaper(l.getPaper().getId());
					List<News> newss = newsDao.findNewsByPaperAndLayout(l.getPaper().getId(), l.getId());
					NewFiles nf = new NewFiles();
					if (nf.newMainHtml(l, newss, layouts, request)) {
						return l.getId();
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
			throw new PaperException("修改纸版面基本信息出现失败，请重试");
			
		}
	}

	/***
	 * 修改版面pic pdf 信息Layout
	 * @param layout 要修改的Layout
	 * @return layoutid
	 * @throws PaperException
	 */
	public int changeLayoutPicAndPdf(Layout layout)
			throws PaperException {
		try {
			Layout l = layoutDao.get(Layout.class, layout.getId());
			if (l != null) {
				l.setPic(layout.getPic());
				l.setPdf(layout.getPdf());
				layoutDao.update(l);
				if (l.getId() != 0) {
					return l.getId();
				} else {
					return 0;
				}
			} else {
				return 0;
			}
			
		} catch(Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("修改纸版面Pic和Pdf信息出现失败，请重试");
			
		}
	}
	
	public NewsDao getNewsDao() {
		return newsDao;
	}

	public void setNewsDao(NewsDao newsDao) {
		this.newsDao = newsDao;
	}

	/***
	 * 根据id获取Layout
	 * @param id
	 * @return Layout
	 * @throws PaperException
	 */
	public Layout findLayoutByID(Integer id) throws PaperException {
		try {
			Layout layout = layoutDao.get(Layout.class, id);
			if (layout != null) {
				return layout;
			} else {
				return null;
			}
		} catch(Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("查找纸版面信息出现失败，请重试");
			
		}
	}

	/***
	 * 根据id删除版面及版面所有的新闻内容及文件
	 * @param id
	 * @param request 用户删除对应的文件夹及文件夹里的所有文件
	 * @return boolean
	 * @throws PaperException
	 */
	public boolean removeLayoutById(Integer id, HttpServletRequest request)
			throws PaperException {
		try {
			Layout layout = layoutDao.get(Layout.class, id);
			if (layout != null) {
			
				Paper paper = paperDao.get(Paper.class, layout.getPaper().getId());
				if (paper != null) {
					paper.setLay_sum(paper.getLay_sum() - 1);
					paperDao.update(paper);
					if (paper.getId() != 0) {
						layoutDao.delete(layout);
						FileDo fd = new FileDo(request);
						String filename = fd.getDir() + "paper\\" + paper.getPaper() + "\\l" + layout.getId();
						String pic = fd.getDir() + "paper\\" + layout.getPic();
						String pdf = fd.getDir() + "paper\\" + layout.getPdf();
						if (fd.deleteDirectory(filename) && fd.deleteFile(pic) &&
								fd.deleteFile(pdf)) {
							// 更新index
							NewFiles nf = new NewFiles();
							if (nf.newIndex(paper, request)) {
								return true;
							} else {
								return false;
							}
							
						} else {
							return false;
							
						}
					} else {
						return false;
					}
					
				} else {
					return false;
				}
			} else {
				return false;
			}
		} catch(Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("根据ID删除纸版面信息出现失败，请重试");
			
		}
	}

	

	


}
