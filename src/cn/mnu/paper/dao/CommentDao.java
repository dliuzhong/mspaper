package cn.mnu.paper.dao;

import java.util.List;

import cn.mnu.paper.common.dao.BaseDao;
import cn.mnu.paper.domain.Comment;

/***
 * ����DAO�ӿ�
 * @author mdl
 * @version 1.0
 * @date 2014-08-10
 */
public interface CommentDao extends BaseDao<Comment> {
	/***
	 * ��ѯ��������������
	 * @return int
	 */
	int getAllCommentSum();
	/***
	 * ��ѯ����������
	 * @param sort ����ؼ���
	 * @param dir ����ʽ
	 * @return 
	 */
	List<Comment> getAllComments(int start, int limit, String sort, String dir);
	
	
	
}
