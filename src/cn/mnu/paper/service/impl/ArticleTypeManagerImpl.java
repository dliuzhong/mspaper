package cn.mnu.paper.service.impl;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import cn.mnu.paper.dao.ArticleTypeDao;
import cn.mnu.paper.domain.Article;
import cn.mnu.paper.domain.ArticleType;
import cn.mnu.paper.exception.PaperException;
import cn.mnu.paper.service.ArticleTypeManager;
/***
 * ArticleType业务逻辑组件实现类
 * @author com.pie
 * @version 1.0
 * @date 2014-08-13
 */
public class ArticleTypeManagerImpl implements ArticleTypeManager {
	static Logger log = Logger.getLogger(
			ArticleTypeManagerImpl.class.getName());
	private ArticleTypeDao articleTypeDao;

	
	public ArticleTypeDao getArticleTypeDao() {
		return articleTypeDao;
	}

	public void setArticleTypeDao(ArticleTypeDao articleTypeDao) {
		this.articleTypeDao = articleTypeDao;
	}
	
	
	/***
	 * 获取所有稿件类型的总数量
	 * @return 所有稿件类型的总数量
	 * @throws PaperException
	 */
	public int findAllArticleTypeSum() throws PaperException {
		try {
			return articleTypeDao.getAllArticleTypeSum();
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("查找所有稿件类型总数量出现失败，请重试");
		}
	}

	/***
	 * 获取所有稿件类型
	 * @param start 开始
	 * @param limit 数量
	 * @param sort 排序关键字
	 * @param dir 排序方式
	 * @return
	 * @throws PaperException
	 */
	public List<ArticleType> findAllArticleType(int start, int limit,
			String sort, String dir) throws PaperException {
		try {
			return articleTypeDao.getAllArticleType(start, limit, sort, dir);
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("查找所有稿件类型出现失败，请重试");
		}
	}

	/***
	 * 添加稿件类型
	 * @param articleType
	 * @return articleTypeid
	 * @throws PaperException
	 */
	public int addArticleType(ArticleType articleType) throws PaperException {
		try {
			if (articleType != null) {
				articleTypeDao.save(articleType);
				return articleType.getId();
			} else {
				return 0;
			}
			
			
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("添加稿件类型出现失败，请重试");
		}
	}

	/***
	 * 修改稿件类型
	 * @param articleType
	 * @return articleTypeid
	 * @throws PaperException
	 */
	public int changeArticleType(ArticleType articleType) throws PaperException {
		try {
			if (articleType != null && articleType.getId() != 0
						&& articleType.getId() != 1) {
				ArticleType a = articleTypeDao.get(ArticleType.class, articleType.getId());
				if (a != null) {
					a.setName(articleType.getName());
					a.setDe(articleType.getDe());
					
					articleTypeDao.update(a);
					return a.getId();
				} else {
					return 0;
				}
				
			} else {
				return 0;
			}
			
			
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("修改稿件类型出现失败，请重试");
		}
	}

	/***
	 * 根据ID删除稿件类型
	 * @param id
	 * @return boolean
	 * @throws PaperException
	 */
	public boolean removeArticleType(Integer id) throws PaperException {
		try {
			if (id != 0 && id != 1) {
				ArticleType a = articleTypeDao.get(ArticleType.class, id);
				if (a != null) {
					Set<Article> al = a.getType_articles();
					for (Article ar : al) {
						ar.setType(articleTypeDao.get(ArticleType.class, 1));
					}
					articleTypeDao.delete(a);
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
			
			
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("添加稿件类型出现失败，请重试");
		}
	}

	/***
	 * 获取所有稿件类型
	 * @return List<ArticleType>
	 * @throws PaperException
	 */
	public List<ArticleType> findAllArticleType() throws PaperException {
		try {
			return articleTypeDao.findAll(ArticleType.class);
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("查找所有稿件类型出现失败，请重试");
		}
	}

	/***
	 * 根据ID获取稿件类型
	 * @param id
	 * @return ArticleType
	 * @throws PaperException
	 */
	public ArticleType findArticleTypeById(Integer id) throws PaperException {
		try {
			return articleTypeDao.get(ArticleType.class, id);
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("根据ID查找稿件类型出现失败，请重试");
		}
	}

	/***
	 * 获取所有稿件类型
	 * @return 所有稿件类型
	 * @throws PaperException
	 */
	public List<ArticleType> findAllArticleTypes() throws PaperException {
		try {
			return articleTypeDao.findAll(ArticleType.class);
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("查找所有稿件类型出现失败，请重试");
		}
	}

	
}
