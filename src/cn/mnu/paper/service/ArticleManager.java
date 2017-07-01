package cn.mnu.paper.service;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import cn.mnu.paper.exception.PaperException;
import cn.mnu.paper.bean.SortBean;
import cn.mnu.paper.domain.Article;
/***
 * Articleҵ���߼�����ӿ�
 * @author mdl
 * @version 1.0
 * @date 2014-08-14
 */
public interface ArticleManager {

	/***
	 * ��ȡ���и����������
	 * @return ���и����������
	 * @throws PaperException
	 */
	int findAllArticleSum() throws PaperException;
	/***
	 * ��ȡ���и��
	 * @param start ��ʼ
	 * @param limit ����
	 * @param sort ����ؼ���
	 * @param dir ����ʽ
	 * @return ���и��
	 * @throws PaperException
	 */
	List<Article> findAllArticle(int start, int limit, String sort, 
			String dir) throws PaperException;
	/***
	 * ��ȡ��Ӧ״̬�ĸ����������
	 * @return ��Ӧ״̬�ĸ����������
	 * @throws PaperException
	 */
	int findStatusArticleSum(int status) throws PaperException;
	/***
	 * ��ȡ��Ӧ״̬�ĸ��
	 * @param start ��ʼ
	 * @param limit ����
	 * @param sort ����ؼ���
	 * @param dir ����ʽ
	 * @return ��Ӧ״̬�ĸ��
	 * @throws PaperException
	 */
	List<Article> findStatusArticle(int status, int start, int limit, 
			String sort, String dir) throws PaperException;
	/***
	 * ����ӦID��״̬��status_f��Ϊstatus_t
	 * @param article 
	 * @param status_f ԭ״̬
	 * @param status_t Ŀ��״̬
	 * @param adminid ��˵Ĺ���ԱID
	 * @return articleid
	 * @throws PaperException
	 */
	int changeArticleStatus(Article article, int status_f, int status_t,
			Integer adminid) throws PaperException;
	
	/***
	 * ����ID��ȡArticle
	 * @param id
	 * @return ��ӦID��Article
	 * @throws PaperException
	 */
	Article findArticleById(Integer id) throws PaperException;
	/***
	 * ����Ա�޸�article
	 * @param article Ҫ�޸ĵ�article
	 * @param adminid
	 * @return article id
	 * @throws PaperException
	 */
	int adminChangeEditArticle(Article article, Integer adminid) throws PaperException;
	/***
	 * ����ID����Word
	 * @param id
	 * @return InputStream
	 * @throws PaperException
	 */
	InputStream findArticleToExport(Integer id) throws PaperException;
	/***
	 * ����IDɾ��article
	 * @param id
	 * @return boolean
	 * @throws PaperException
	 */
	boolean removeArticleById(Integer id) throws PaperException;
	/***
	 * �����û�ID��ȡ������Ӧ�ĸ������
	 * @param id �û�ID
	 * @return ��Ӧ�ĸ������
	 * @throws PaperExcetion
	 */
	int findArticleByMsuserSum(Integer msuserid) throws PaperException;
	/***
	 * �����û�ID��ȡ������Ӧ�ĸ��
	 * @param id �û�ID
	 * @param start ��ʼ
	 * @param limit ����
	 * @return List<Article>
	 * @throws PaperException
	 */
	List<Article> findArticleByMsuser(Integer msuserid, int start, int limit)
		throws PaperException;
	/***
	 * �û�Ͷ�壬��Ӹ��
	 * @param article ��Ӹ��
	 * @param Msuserid �û�ID
	 * @param typeid �������ID
	 * @return ��Ӹ����ID
	 * @throws PaperException
	 */
	Integer addArticle(Article article, Integer msuserid, Integer typeid)
		throws PaperException;
	
	/***
	 * ����IDɾ��article״̬Ϊ1�ļ�¼
	 * @param id
	 * @return boolean
	 * @throws PaperException
	 */
	boolean removeArticleStatus1ById(Integer id) throws PaperException;
	/***
	 * �û��޸�article
	 * @param article
	 * @param typeid
	 * @return article id
	 * @throws PaperException
	 */
	Integer changeMyArticle(Article article, Integer typeid) throws PaperException;
	/***
	 * ���ݹؼ���sort��id��ѯ����
	 * @param sort ��ʽ
	 * @param contain ��Χ
	 * @param date1 ʱ�䷶Χ ��
	 * @param date2 ʱ�䷶Χ ��
	 * @return ����
	 */
	int findArticleBySortSum(String sort, Integer id, String contain, Date date1, Date date2) throws PaperException;
	/***
	 * ���ݷ�ʽ����Χ��ʱ�䷶Χ��ѯ����
	 * @param sort ��ʽ
	 * @param contain ��Χ
	 * @param date1 ʱ�䷶Χ ��
	 * @param date2 ʱ�䷶Χ ��
	 * @return
	 * @throws PaperException
	 */
	int findAllArticleBySortSum(String sortMain, String sort, String contain, Date date1, Date date2) throws PaperException;
	/***
	 * ��aritlcle.other�������Ϣ
	 * @param data ��ӵ���Ϣ
	 * @param article
	 * @return article id
	 * @throws PaperException
	 */
	int changeArticleAddDataToOther(String data, Article article) throws PaperException;
	/***
	 * ����Articleͳ��SortBean
	 * @param sbList
	 * @param allArticle
	 * @return InputStream
	 * @throws PaperException
	 */
	InputStream findInputStreamArticles(List<SortBean> sbList, int allArticle,
			String sortMain, String sort, String contain, Date date1, Date date2, String other) throws PaperException;
}
