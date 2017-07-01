package cn.mnu.paper.dao.impl;

import java.util.List;

import cn.mnu.paper.common.dao.impl.BaseDaoHibernate;
import cn.mnu.paper.dao.ArticleTypeDao;
import cn.mnu.paper.domain.ArticleType;
/***
 * ArticleTypeDao�����ʵ����
 * @author mdl
 * @version 1.0
 * @date 2014-08-13
 */
public class ArticleTypeDaoHibernate extends BaseDaoHibernate<ArticleType> 
	implements ArticleTypeDao {

	/***
	 * ��ѯ���и�����͵�������
	 * @return int ������
	 */
	public int getAllArticleTypeSum() {
		return findAll(ArticleType.class).size();
	}

	/***
	 * ��ѯ���и������
	 * @param start ��ʼ
	 * @param limit ����
	 * @param sort ����ؼ���
	 * @param dir ����ʽ
	 * @return���и������
	 */
	public List<ArticleType> getAllArticleType(int start, int limit,
			String sort, String dir) {
		String hql = "select at from ArticleType at ";
		if (sort != null && !sort.trim().equals("") && dir != null &&
				!dir.trim().equals("")) {
			hql += "order by at." + sort + " " + dir;
		} else {
			hql += "order by at.id DESC";
		}
		List<ArticleType> al = find(hql);
		if (start + limit <= al.size())
			return al.subList(start, start + limit);
		else 
			return al.subList(start, al.size());
	}

}
