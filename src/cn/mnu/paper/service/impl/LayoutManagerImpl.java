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
 * Paperҵ���߼����ʵ����
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
	 * ����µı�ֽ������Ϣ
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
			throw new PaperException("����µı�ֽ������Ϣ����ʧ�ܣ�������");
		}
	}

	/***
	 * ��֤�Ƿ����иñ�ֽ������Ϣ
	 * @param paper ��Ҫ��֤���ںű�ֽ
	 * @param layno ��Ҫ��֤�İ���
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
			throw new PaperException("���ںŰ�����Ҳ鱨ֽ������Ϣ����ʧ�ܣ�������");
			
		}
	}

	/***
	 * ��֤�Ƿ����иñ�ֽ������Ϣ
	 * @param paper ��Ҫ��֤���ںű�ֽ
	 * @param layno ��Ҫ��֤�İ���
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
			throw new PaperException("���ںŰ�����Ҳ鱨ֽ������Ϣ����ʧ�ܣ�������");
			
		}
	}

	/***
	 * ����paperid��ֽ�ں�ID�������а�����Ϣ
	 * @param paperid ��ֽ�ں�ID
	 * @return List<Layout> ���а�����Ϣ
	 * @throws PaperException
	 */
	public List<Layout> getLayoutByPaper(Integer paperid)
			throws PaperException {
		try {
			return layoutDao.findLayoutByPaper(paperid);
			
		} catch(Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("���ں��Ҳ鱨ֽ������Ϣ����ʧ�ܣ�������");
			
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
	 * �޸İ��������ϢLayout
	 * @param layout Ҫ�޸ĵ�Layout
	 * @param request �����޸�main.html
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
					// �����ļ�main.html
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
			throw new PaperException("�޸�ֽ���������Ϣ����ʧ�ܣ�������");
			
		}
	}

	/***
	 * �޸İ���pic pdf ��ϢLayout
	 * @param layout Ҫ�޸ĵ�Layout
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
			throw new PaperException("�޸�ֽ����Pic��Pdf��Ϣ����ʧ�ܣ�������");
			
		}
	}
	
	public NewsDao getNewsDao() {
		return newsDao;
	}

	public void setNewsDao(NewsDao newsDao) {
		this.newsDao = newsDao;
	}

	/***
	 * ����id��ȡLayout
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
			throw new PaperException("����ֽ������Ϣ����ʧ�ܣ�������");
			
		}
	}

	/***
	 * ����idɾ�����漰�������е��������ݼ��ļ�
	 * @param id
	 * @param request �û�ɾ����Ӧ���ļ��м��ļ�����������ļ�
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
							// ����index
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
			throw new PaperException("����IDɾ��ֽ������Ϣ����ʧ�ܣ�������");
			
		}
	}

	

	


}
