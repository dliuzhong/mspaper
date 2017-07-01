package cn.mnu.paper.dao.impl;

import java.util.Date;
import java.util.List;

import cn.mnu.paper.common.dao.impl.BaseDaoHibernate;
import cn.mnu.paper.dao.ArticleDao;
import cn.mnu.paper.domain.Article;
/***
 * ArticleDao�����ʵ����
 * @author mdl
 * @version 1.0
 * @date 2014-08-14
 */
public class ArticleDaoHibernate extends BaseDaoHibernate<Article>
	implements ArticleDao {

	/***
	 * ��ѯ���и��������
	 * @return ���и��������
	 */
	public int getAllArticleSum() {
		return findAll(Article.class).size();
	}

	/***
	 * ��ѯ���и��
	 * @param start ��ʼ
	 * @param limit ����
	 * @param sort ����ؼ���
	 * @param dir ����ʽ
	 * @return ���и��
	 */
	public List<Article> getAllArticle(int start, int limit, String sort,
			String dir) {
		String hql = "select a from Article a ";
		if (sort != null && !sort.trim().equals("") && dir != null &&
				!dir.trim().equals("")) {
			if (sort.equals("msuser")) {
				hql += "order by a.msuser.name " + dir;
			} else if (sort.equals("type")) {
				hql += "order by a.type.name " + dir;
			}  else if (sort.equals("status")) {
				hql += "order by a.status.name " + dir;
			} else {
				hql += "order by a." + sort + " " + dir;
			}
		} else {
			hql += "order by a.checktime DESC, a.time DESC";
		}
		List<Article> al = find(hql);
		if (start + limit <= al.size())
			return al.subList(start, start + limit);
		else 
			return al.subList(start, al.size());
	}

	/***
	 * ��ѯ��Ӧ״̬�ĸ��������
	 * @param Ҫ��ѯ��״̬
	 * @return ��Ӧ״̬�ĸ��������
	 */
	public int getStatusArticleSum(int status) {
		if (status == 1) {
			return find("select a from Article a where a.status.id=1 or a.status.id=2").size();
		}
		return find("select a from Article a where a.status.id=?0", status).size();
	}

	/***
	 * ��ѯ��Ӧ״̬�ĸ��
	 * @param start ��ʼ
	 * @param limit ����
	 * @param sort ����ؼ���
	 * @param dir ����ʽ
	 * @return ��Ӧ״̬�ĸ��
	 */
	public List<Article> getStatusArticle(int status, int start,
			int limit, String sort, String dir) {
		String hql;
		if (status == 0 || status == 1) {
			hql = "select a from Article a where a.status.id=1 or a.status.id=2";
		} else {
			hql = "select a from Article a where a.status.id=" + status + " ";
		}
		if (sort != null && !sort.trim().equals("") && dir != null &&
				!dir.trim().equals("")) {
			if (sort.equals("msuser")) {
				hql += "order by a.msuser.name " + dir;
			} else if (sort.equals("type")) {
				hql += "order by a.type.name " + dir;
			} else {
				hql += "order by a." + sort + " " + dir;
			}
		} else {
			if (status == 0 || status == 1) {
				hql += "order by a.time DESC";
			} else {
				hql += "order by a.checktime DESC, a.time DESC";
			}
			
		}
		List<Article> al = find(hql);
		if (start + limit <= al.size())
			return al.subList(start, start + limit);
		else 
			return al.subList(start, al.size());
	}

	/***
	 * �����û�id��ѯ������Ӧ���
	 * @param msuserid �û�id
	 * @param start ��ʼ
	 * @param limit ����
	 * @return List<Article>
	 */
	public List<Article> getArticleByMsuserid(Integer msuserid, int start,
			int limit) {
		List<Article> al = find("select a from Article a where a.msuser.id=?0 order by a.time DESC",
				msuserid);
		if (start + limit <= al.size())
			return al.subList(start, start + limit);
		else 
			return al.subList(start, al.size());
	}

	/***
	 * �����û�id��ѯ������Ӧ�������
	 * @param msuserid �û�id
	 * @return int
	 */
	public int getArticleByMsuseridSum(Integer msuserid) {
		return find("select a from Article a where a.msuser.id=?0 order by a.time DESC",
				msuserid).size();
	}

	/***
	 * ���ݹؼ���sort��id��ѯ����
	 * @param sort
	 * @param id
	 * @return ����
	 */
	public int getArticleBySortSum(String sort, Integer id, String contain, Date date1, Date date2) {
		String hql = "select a from Article a where";
		if (sort != null && !sort.trim().equals("")) {
			if (sort.trim().equals("depart")) {
				hql += " a.msuser.department.id=" + id;
			}
			if (sort.trim().equals("msuser")) {
				hql += " a.msuser.id=" + id;
			}
			if (sort.trim().equals("type")) {
				hql += " a.type.id=" + id;
				
			}
			if (contain != null && contain.trim().equals("pass")) {
				hql += " and a.status=4";
			}
			if (contain != null && contain.trim().equals("publish")) {
				hql += " and a.publish=1";
			}
			if (date1 != null && date2 != null) {
				hql += " and a.time>=?0 and a.time<=?1";
				return find(hql, date1, date2).size();
			}
			return find(hql).size();
		} else {
			return 0;
		}
	}

	/***
	 * ���ݷ�ʽ����Χ��ʱ�䷶Χ��ѯ����
	 * @param sort ��ʽ
	 * @param contain ��Χ
	 * @param date1 ʱ�䷶Χ ��
	 * @param date2 ʱ�䷶Χ ��
	 * @return
	 */
	public int getArticleAllSum(String sortMain, String sort, String contain, Date date1,
			Date date2) {
		String hql = "select a from Article a where";
		if (sort != null && !sort.trim().equals("")) {
			// ����
			if (sortMain.trim().equals("depart")) {
				hql += " a.msuser.department.";
				// �ڣ��⣬����
				if (sort.trim().equals("in")) {
					hql += "type=1";
				} else if(sort.trim().equals("out")) {
					hql += "type=2";
				} else {
					hql += "type!=null";
				}
				// �û�
			} else if (sortMain.trim().equals("msuser")) {
				hql += " a.msuser.";
				// �ڣ��⣬����
				if (sort.trim().equals("in")) {
					hql += "type like '�ڲ��û�'";
				} else if (sort.trim().equals("out")){
					hql += "type like '�ⲿ�û�'";
				} else {
					hql += "type!=null";
				}
				// �������
			} else if (sortMain.trim().equals("type")) {
				hql += " a.id != null";
				
			}
			// ���״̬
			if (contain != null && contain.trim().equals("pass")) {
				hql += " and a.status=4";
			}
			// ����Ƿ񷢱�
			if (contain != null && contain.trim().equals("publish")) {
				hql += " and a.publish=1";
			}
			// ���ڷ�Χ
			if (date1 != null && date2 != null) {
				hql += " and a.time>=?0 and a.time<=?1";
				//System.out.println(hql);
				return find(hql, date1, date2).size();
			}
			//System.out.println(hql);
			return find(hql).size();
		} else {
			return 0;
		}
	}

}
