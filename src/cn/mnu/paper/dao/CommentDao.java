package cn.mnu.paper.dao;

import java.util.List;

import cn.mnu.paper.common.dao.BaseDao;
import cn.mnu.paper.domain.Comment;

/***
 * 评论DAO接口
 * @author mdl
 * @version 1.0
 * @date 2014-08-10
 */
public interface CommentDao extends BaseDao<Comment> {
	/***
	 * 查询出所有评论总数
	 * @return int
	 */
	int getAllCommentSum();
	/***
	 * 查询出所有评论
	 * @param sort 排序关键字
	 * @param dir 排序方式
	 * @return 
	 */
	List<Comment> getAllComments(int start, int limit, String sort, String dir);
	
	
	
}
