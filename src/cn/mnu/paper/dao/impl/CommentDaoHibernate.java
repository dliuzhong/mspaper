package cn.mnu.paper.dao.impl;

import java.util.List;

import cn.mnu.paper.common.dao.impl.BaseDaoHibernate;
import cn.mnu.paper.dao.CommentDao;
import cn.mnu.paper.domain.Comment;

/***
 * CommentDao�����ʵ����
 * @author mdl
 * @version 1.0
 * @date 2014-08-10
 */
public class CommentDaoHibernate extends BaseDaoHibernate<Comment>
	implements CommentDao {

	/***
	 * ��ѯ��������������
	 * @return int
	 */
	public int getAllCommentSum() {
		return findAll(Comment.class).size();
	}

	/***
	 * ��ѯ����������
	 * @param sort ����ؼ���
	 * @param dir ����ʽ
	 * @return 
	 */
	public List<Comment> getAllComments(int start, int limit, String sort, String dir) {
		String hql = "select c from Comment c ";
		if (sort != null && !sort.trim().equals("") && dir != null &&
				!dir.trim().equals("")) {
			if (sort.trim().equals("newsid")) {
				hql += "order by c.news.id " + dir;
			} else if(sort.trim().equals("paper")) {
				hql += "order by c.news.paper.paper " + dir;
			} else if (sort.trim().equals("layout_no")) {
				hql += "order by c.news.layout.layout_no " + dir;
			} else {
				hql += "order by c." + sort + " " + dir;
			}
		} else {
			hql += "order by c.time DESC";
		}
		//System.out.println(hql);
		List<Comment> cl = find(hql);
		if (start + limit <= cl.size())
			return cl.subList(start, start + limit);
		else 
			return cl.subList(start, cl.size());
	}

}
