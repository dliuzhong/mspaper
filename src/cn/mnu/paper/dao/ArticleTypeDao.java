package cn.mnu.paper.dao;

import java.util.List;

import cn.mnu.paper.common.dao.BaseDao;
import cn.mnu.paper.domain.ArticleType;

/***
 * �������DAO�ӿ�
 * @author mdl
 * @version 1.0
 * @date 2014-08-13
 */
public interface ArticleTypeDao extends BaseDao<ArticleType> {
	/***
	 * ��ѯ���и�����͵�������
	 * @return int ������
	 */
	int getAllArticleTypeSum();
	/***
	 * ��ѯ���и������
	 * @param start ��ʼ
	 * @param limit ����
	 * @param sort ����ؼ���
	 * @param dir ����ʽ
	 * @return���и������
	 */
	List<ArticleType> getAllArticleType(int start, int limit, String sort, String dir);
}
