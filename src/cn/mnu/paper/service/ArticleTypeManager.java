package cn.mnu.paper.service;

import java.util.List;

import cn.mnu.paper.exception.PaperException;
import cn.mnu.paper.domain.ArticleType;

/***
 * ArticleTypeҵ���߼�����ӿ�
 * @author mdl
 * @version 1.0
 * @date 2014-08-13
 */
public interface ArticleTypeManager {
	/***
	 * ��ȡ���и�����͵�������
	 * @return ���и�����͵�������
	 * @throws PaperException
	 */
	int findAllArticleTypeSum() throws PaperException;
	/***
	 * ��ȡ���и������
	 * @param start ��ʼ
	 * @param limit ����
	 * @param sort ����ؼ���
	 * @param dir ����ʽ
	 * @return
	 * @throws PaperException
	 */
	List<ArticleType> findAllArticleType(int start, int limit, String sort,
			String dir) throws PaperException;
	/***
	 * ��Ӹ������
	 * @param articleType
	 * @return articleTypeid
	 * @throws PaperException
	 */
	int addArticleType(ArticleType articleType) throws PaperException;
	/***
	 * �޸ĸ������
	 * @param articleType
	 * @return articleTypeid
	 * @throws PaperException
	 */
	int changeArticleType(ArticleType articleType) throws PaperException;
	/***
	 * ����IDɾ���������
	 * @param id
	 * @return boolean
	 * @throws PaperException
	 */
	boolean removeArticleType(Integer id) throws PaperException;
	/***
	 * ��ȡ���и������
	 * @return List<ArticleType>
	 * @throws PaperException
	 */
	List<ArticleType> findAllArticleType() throws PaperException;
	/***
	 * ����ID��ȡ�������
	 * @param id
	 * @return ArticleType
	 * @throws PaperException
	 */
	ArticleType findArticleTypeById(Integer id) throws PaperException;
	/***
	 * ��ȡ���и������
	 * @return ���и������
	 * @throws PaperException
	 */
	List<ArticleType> findAllArticleTypes() throws PaperException;
}
