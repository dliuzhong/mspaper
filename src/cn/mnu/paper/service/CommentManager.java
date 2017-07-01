package cn.mnu.paper.service;

import java.util.List;

import cn.mnu.paper.domain.Comment;
import cn.mnu.paper.exception.PaperException;

/***
 * Comment业务逻辑组件接口
 * @author mdl
 * @version 1.0
 * @date 2014-08-10
 */
public interface CommentManager {

	/**
	 * 获取所有评论的总数
	 * @return int
	 * @throws PaperException
	 */
	int findAllCommentSum() throws PaperException;
	/***
	 * 获取所有评论内容
	 * @param start 开始
	 * @param limit 数量
	 * @param sort 排序关键字
	 * @param dir 排序方式
	 * @return
	 * @throws Exception
	 */
	List<Comment> findAllComments(int start, int limit, 
			String sort, String dir) throws PaperException;
	/***
	 * 根据ID删除评论
	 * @param id
	 * @return boolean
	 * @throws PaperException
	 */
	boolean removeComment(Integer id) throws PaperException;
	/***
	 * 修改pass,审核通过
	 * @param id 审核的ID
	 * @return Integer
	 * @throws PaperException
	 */
	Integer changeCommentPassTo1(Integer id) throws PaperException;
	/***
	 * 修改pass,不通过审核
	 * @param id 审核的ID
	 * @return Integer
	 * @throws PaperException
	 */
	Integer changeCommentPassTo0(Integer id) throws PaperException;
	/***
	 * 增加评论
	 * @param comment
	 * @param newsid
	 * @return true or false
	 * @throws PaperException
	 */
	boolean addComment(Comment comment, Integer newsid) throws PaperException;

	/***
	 * 查询出新闻的所有评论
	 * @param newsid
	 * @return List<Comment>
	 * @throws PaperException
	 */
	List<Comment> getNewsComment(Integer newsid) throws PaperException;
}
