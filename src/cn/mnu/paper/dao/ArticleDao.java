package cn.mnu.paper.dao;

import java.util.Date;
import java.util.List;

import cn.mnu.paper.common.dao.BaseDao;
import cn.mnu.paper.domain.Article;

/***
 * ���DAO�ӿ�
 * @author mdl
 * @version 1.0
 * @date 2014-08-14
 */
public interface ArticleDao extends BaseDao<Article> {
	/***
	 * ��ѯ���и��������
	 * @return ���и��������
	 */
	int getAllArticleSum();
	/***
	 * ��ѯ���и��
	 * @param start ��ʼ
	 * @param limit ����
	 * @param sort ����ؼ���
	 * @param dir ����ʽ
	 * @return ���и��
	 */
	List<Article> getAllArticle(int start, int limit, String sort, String dir);
	/***
	 * ��ѯ��Ӧ״̬�ĸ��������
	 * @param Ҫ��ѯ��״̬
	 * @return ��Ӧ״̬�ĸ��������
	 */
	int getStatusArticleSum(int status);
	/***
	 * ��ѯ��Ӧ״̬�ĸ��
	 * @param start ��ʼ
	 * @param limit ����
	 * @param sort ����ؼ���
	 * @param dir ����ʽ
	 * @return ��Ӧ״̬�ĸ��
	 */
	List<Article> getStatusArticle(int status, int start, int limit, 
			String sort, String dir);
	/***
	 * �����û�id��ѯ������Ӧ���
	 * @param msuserid �û�id
	 * @param start ��ʼ
	 * @param limit ����
	 * @return List<Article>
	 */
	List<Article> getArticleByMsuserid(Integer msuserid, int start, int limit);
	/***
	 * �����û�id��ѯ������Ӧ�������
	 * @param msuserid �û�id
	 * @return int
	 */
	int getArticleByMsuseridSum(Integer msuserid);
	/***
	 * ���ݹؼ���sort��id��ѯ����
	 * @param sort ��ʽ
	 * @param id
	 * @param contain ��Χ
	 * @param date1 ʱ�䷶Χ ��
	 * @param date2 ʱ�䷶Χ ��
	 * @return ����
	 */
	int getArticleBySortSum(String sort, Integer id, String contain, Date date1, Date date2);
	/***
	 * ���ݷ�ʽ����Χ��ʱ�䷶Χ��ѯ����
	 * @param sort ��ʽ
	 * @param contain ��Χ
	 * @param date1 ʱ�䷶Χ ��
	 * @param date2 ʱ�䷶Χ ��
	 * @return
	 */
	int getArticleAllSum(String sortMain, String sort, String contain, Date date1, Date date2);
}
