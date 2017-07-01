package cn.mnu.paper.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


import org.apache.log4j.Logger;

import cn.mnu.paper.dao.PaperDao;
import cn.mnu.paper.domain.Paper;
import cn.mnu.paper.exception.PaperException;
import cn.mnu.paper.service.PaperManager;
import cn.mnu.paper.tools.FileDo;
import cn.mnu.paper.tools.NewFiles;

/***
 * Paper业务逻辑组件实现类
 * @author com
 * @version 1.0
 * @date 2014-07-14
 */
public class PaperManagerImpl implements PaperManager {
	static Logger log = Logger.getLogger(
			PaperManagerImpl.class.getName());
	private PaperDao paperDao;
	/***
	 * 新增报纸信息
	 * @param paper 新增报纸的Paper实例
	 * @return 新增报纸
	 */
	public Paper addPaper(Paper paper) throws PaperException {
		try {
			Paper p = findPaperByName(paper.getPaper());
			if (p != null) {
				p.setZb(paper.getZb());
				p.setCb(paper.getCb());
				p.setZongb(paper.getZongb());
				p.setCbtime(paper.getCbtime());
				p.setEmail(paper.getEmail());
				p.setLay_sum(paper.getLay_sum());
				paperDao.update(p);
				return p;
			} else {
				paperDao.save(paper);
				return paper;
			}



		} catch(Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("添加新报出现失败，请重试");
		}
	}

	/***
	 * 验证系统中是否已有该报纸
	 * @param paper 需要验证的期号
	 * @return 报纸是否可用
	 * @throws PaperException
	 */
	public boolean validatePaperByName(String paper) throws PaperException {
		try {
			if (paperDao.findPaperByPaper(paper) != null) {
				return true;
			} else {
				return false;
			}


		} catch(Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("按期号找查报纸出现失败，请重试");

		}
	}
	/***
	 * 根据报纸期号找查
	 * @param paper 需要找查的期号
	 * @return 报纸
	 * @throws PaperException
	 */
	public Paper findPaperByName(String paper) throws PaperException {
		try {
			Paper p = paperDao.findPaperByPaper(paper);
			if (p != null) {
				return p;
			} else {
				return null;
			}


		} catch(Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("按期号找查报纸出现失败，请重试");

		}
	}

	public PaperDao getPaperDao() {
		return paperDao;
	}
	public void setPaperDao(PaperDao paperDao) {
		this.paperDao = paperDao;
	}

	/***
	 * 根据期号和年查找报纸
	 * @param paper
	 * @param year
	 * @return
	 * @throws PaperException
	 */
	public List<Paper> getPaperByPaperAndYear(String paper, String year)
			throws PaperException {
		try {
			List<Paper> ps = paperDao.findPaperByPaperAndYear(paper, year);
			return ps;

		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("查找所有报纸基本信息出现失败，请重试");
		}
	}

	/***
	 * 根据 ID查找报纸基本信息
	 * @param id ID
	 * @return Paper
	 * @throws PaperException
	 */
	public Paper getPaperById(Integer id) throws PaperException {
		try {
			return paperDao.get(Paper.class, id);
		}  catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("根据ID查找报纸基本信息出现失败，请重试");
		}
	}

	/***
	 * 根据paperID更改版面数 + 1
	 * @param paperid
	 * @return int
	 * @throws PaperException
	 */
	public int changePaperLay_num(Integer paperid) throws PaperException {
		try {
			Paper p = paperDao.get(Paper.class, paperid);
			if (p != null) {
				p.setLay_sum(p.getLay_sum() + 1);
				paperDao.update(p);
				return p.getLay_sum();
			}
			return 0;
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("更改版面数出现失败，请重试");
		}
	}

	/***
	 * 根据ID删除报纸
	 * @param id ID
	 * @param request 用户删除文件
	 * @return boolean
	 * @throws PaperException
	 */
	public boolean removePaperById(Integer id, HttpServletRequest request) throws PaperException {
		try {
			Paper p = paperDao.get(Paper.class, id);
			paperDao.delete(p);
			FileDo fd = new FileDo(request);
			String filepath = fd.getDir() + "paper\\" + p.getPaper();
			//System.out.println(filepath);
			if (fd.isFileExist(filepath)) {
				if (fd.deleteFolder("paper\\" + p.getPaper())) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}

		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("根据ID删除报纸出现失败，请重试");
		}

	}
	/***
	 * 修改Paper
	 * @param paper 修改的内容
	 * @param request 用于修改index.jsp
	 * @return paper-id
	 * @throws PaperException
	 */
	public int changePaper(Paper paper, HttpServletRequest request)
			throws PaperException {
		try {
			Paper p = paperDao.get(Paper.class, paper.getId());
			if (p != null) {
				p.setZb(paper.getZb());
				p.setCb(paper.getCb());
				p.setZongb(paper.getZongb());
				p.setCbtime(paper.getCbtime());
				p.setEmail(paper.getEmail());
				if (paper.getPaper_url() != null) {
					p.setPaper_url(paper.getPaper_url());
				}
				paperDao.update(p);
				NewFiles nf = new NewFiles();
				if (nf.newIndex(p, request)) {
					return p.getId();
				} else {
					return 0;
				}
			} else {
				return 0;
			}

		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("报纸修改出现失败，请重试");
		}
	}

	/***
	 * 根据 query关键字获取相关的总数
	 * @param query 关键字
	 * @return int
	 * @throws PaperException
	 */
	public int findQueryPaperSum(String query) throws PaperException {
		try {
			int sum = paperDao.getAllRows(query);
			return sum;
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("查找所有的报纸总数量出现失败，请重试");
		}
	}

	/***
	 * 对paper进行query匹配查找获取所有papers
	 * @param query
	 * @param start
	 * @param limit
	 * @return
	 * @throws PaperException
	 */
	public List<Paper> findPaperByPaperQuery(String query, int start, int limit)
			throws PaperException {
		try {
			List<Paper> pl = paperDao.getPaperByPaperQuery(query);
			List<Paper> p = new ArrayList<Paper>();
			Paper pa;
			for (int i = 0;i < pl.size(); i++) {
				pa = pl.get(i);
				//System.out.println(sb.getName());
				if (i >= start && i < start + limit) {

					p.add(pa);
				}
			}
			return p;
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("查找所有报纸出现失败，请重试");
		}
	}

	/***
	 * 根据出版时间cbtime获取所有Paper
	 * @return List<Paper>
	 * @throws PapaerException
	 */
	public List<Paper> findAllPaperOrderByCbtime() throws PaperException {
		try {
			return paperDao.getAllPaperOrderByCbtime();
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("根据cbtime查找所有的报纸出现失败，请重试");
		}
	}

	/***
	 * 获取最新一期Paper
	 * @return Paper
	 * @throws PaperException
	 */
	public Paper findLastPaper() throws PaperException {
		try {
			return paperDao.getLastPaper();
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("获取最新一期报纸出现失败，请重试");
		}
	}

	/***
	 * 获取ID的期的前一期
	 * @param id
	 * @return 如果当前为最前一期，返回0，否则返回前一期的期号
	 * @throws PaperException
	 */
	public String findPrevPaper(Integer id) throws PaperException {
		try {
			// 取得按出版时间降序排列的报纸信息List
			List<Paper> paperList = paperDao.getAllPaperOrderByCbtime();

			int no;
			// 从每一个开始（即从0开始）
			for (no = 0;no < paperList.size(); no++) {
				// 如果当前ID与id相等，则break
				if (Integer.parseInt(paperList.get(no).getId().toString()) == id ||
						paperList.get(no).getId().toString().equals(id.toString()))
					break;

			}
			// 如果ID为id的这一期为数据的最后一个，则没有前一期，返回0
			if (no == (paperList.size() - 1) || no == paperList.size()) {
				return "0";
			} else {
				// 否则++no;
				++no;
			}
//			返回前一期的URL
			return paperList.get(no).getPaper_url();

		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("获取最前一期报纸出现失败，请重试");
		}
	}

	/***
	 * 获取ID的期的后一期
	 * @param id
	 * @return 如果当前为最后一期，返回0，否则返回后一期的期号
	 * @throws PaperException
	 */
	public String findNextPaper(Integer id) throws PaperException {
		try {
			// 取得按出版时间降序排列的报纸信息List
			List<Paper> paperList = paperDao.getAllPaperOrderByCbtime();

			int no;
			// 从第一个开始（即从0开始）
			for (no = 0;no < paperList.size(); no++) {
				// 如果当前ID与id相等，则break
				if (Integer.parseInt(paperList.get(no).getId().toString()) == id ||
						paperList.get(no).getId().toString().equals(id.toString()))
					break;

			}
			// 如果ID为id的这一期为数据的第一个，则没有后一期，返回0
			if (no == 0 || no == paperList.size()) {
				return "0";
			} else {
//				否则--no
				--no;
			}
//			返回后一期的URL
			return paperList.get(no).getPaper_url();
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("获取最后一期报纸出现失败，请重试");
		}
	}


}
