package cn.mnu.paper.service;

import java.util.List;

import cn.mnu.paper.exception.PaperException;
import cn.mnu.paper.domain.ArticleType;

/***
 * ArticleType业务逻辑组件接口
 * @author mdl
 * @version 1.0
 * @date 2014-08-13
 */
public interface ArticleTypeManager {
	/***
	 * 获取所有稿件类型的总数量
	 * @return 所有稿件类型的总数量
	 * @throws PaperException
	 */
	int findAllArticleTypeSum() throws PaperException;
	/***
	 * 获取所有稿件类型
	 * @param start 开始
	 * @param limit 数量
	 * @param sort 排序关键字
	 * @param dir 排序方式
	 * @return
	 * @throws PaperException
	 */
	List<ArticleType> findAllArticleType(int start, int limit, String sort,
			String dir) throws PaperException;
	/***
	 * 添加稿件类型
	 * @param articleType
	 * @return articleTypeid
	 * @throws PaperException
	 */
	int addArticleType(ArticleType articleType) throws PaperException;
	/***
	 * 修改稿件类型
	 * @param articleType
	 * @return articleTypeid
	 * @throws PaperException
	 */
	int changeArticleType(ArticleType articleType) throws PaperException;
	/***
	 * 根据ID删除稿件类型
	 * @param id
	 * @return boolean
	 * @throws PaperException
	 */
	boolean removeArticleType(Integer id) throws PaperException;
	/***
	 * 获取所有稿件类型
	 * @return List<ArticleType>
	 * @throws PaperException
	 */
	List<ArticleType> findAllArticleType() throws PaperException;
	/***
	 * 根据ID获取稿件类型
	 * @param id
	 * @return ArticleType
	 * @throws PaperException
	 */
	ArticleType findArticleTypeById(Integer id) throws PaperException;
	/***
	 * 获取所有稿件类型
	 * @return 所有稿件类型
	 * @throws PaperException
	 */
	List<ArticleType> findAllArticleTypes() throws PaperException;
}
