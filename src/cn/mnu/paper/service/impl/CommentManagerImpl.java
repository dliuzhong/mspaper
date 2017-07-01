package cn.mnu.paper.service.impl;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import cn.mnu.paper.comparator.MyComparatorComment;
import cn.mnu.paper.dao.CommentDao;
import cn.mnu.paper.dao.NewsDao;
import cn.mnu.paper.domain.Comment;
import cn.mnu.paper.domain.News;
import cn.mnu.paper.exception.PaperException;
import cn.mnu.paper.service.CommentManager;
import cn.mnu.paper.tools.MyTools;

/***
 * Comment业务逻辑组件实现类
 * @author mdl
 * @version 1.0
 * @date 2014-08-10
 */
public class CommentManagerImpl implements CommentManager {
	static Logger log = Logger.getLogger(
			CommentManagerImpl.class.getName());
	private CommentDao commentDao;
	private NewsDao newsDao;
	
	public CommentDao getCommentDao() {
		return commentDao;
	}
	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}
	public NewsDao getNewsDao() {
		return newsDao;
	}
	public void setNewsDao(NewsDao newsDao) {
		this.newsDao = newsDao;
	}
	/**
	 * 获取所有评论的总数
	 * @return int
	 * @throws PaperException
	 */
	public int findAllCommentSum() throws PaperException {
		try {
			return commentDao.getAllCommentSum();
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("查找所有评论总数量出现失败，请重试");
		}
	}
	/***
	 * 获取所有评论内容
	 * @param start 开始
	 * @param limit 数量
	 * @param sort 排序关键字
	 * @param dir 排序方式
	 * @return
	 * @throws Exception
	 */
	public List<Comment> findAllComments(int start, int limit, String sort,
			String dir) throws PaperException {
		try {
			List<Comment> cl = commentDao.getAllComments(start, limit, sort, dir);
//			List<Comment> c = new ArrayList<Comment>();
//			System.out.println("manager");
//			Comment cm;
//			for (int i = 0;i < cl.size(); i++) {
//				cm = cl.get(i);
//				//System.out.println(sb.getName());
//				if (i >= start && i < start + limit) {
//					
//					c.add(cm);
//				}
//			}
			return cl;
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("查找所有评论出现失败，请重试");
		}
	}
	/***
	 * 根据ID删除评论
	 * @param id
	 * @return boolean
	 * @throws PaperException
	 */
	public boolean removeComment(Integer id) throws PaperException {
		try {
			
			Comment c = commentDao.get(Comment.class, id);
			
			if (c != null) {
				commentDao.delete(c);
				return true;
			} else {
				return false;
			}
			
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("删除评论出现失败，请重试");
		}
	}
	/***
	 * 根据ID修改专题的状态，修改为1
	 * @param id
	 * @return Integer
	 * @throws PaperException
	 */
	public Integer changeCommentPassTo1(Integer id) throws PaperException {
		try {
			Comment c = commentDao.get(Comment.class, id);
			if (c != null) {
				c.setPass(1);
				commentDao.update(c);
				return c.getId();
			} else {
				return 0;
			}
			
			
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("通过审核评论出现失败，请重试");
		}
	}
	/***
	 * 根据ID修改专题的状态，修改为0
	 * @param id
	 * @return Integer
	 * @throws PaperException
	 */
	public Integer changeCommentPassTo0(Integer id) throws PaperException {
		try {
			Comment c = commentDao.get(Comment.class, id);
			if (c != null) {
				c.setPass(0);
				commentDao.update(c);
				return c.getId();
			} else {
				return 0;
			}
			
			
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("不通过审核评论出现失败，请重试");
		}
	}
	/***
	 * 增加评论
	 * @param comment
	 * @param newsid
	 * @return true or false
	 * @throws PaperException
	 */
	public boolean addComment(Comment comment, Integer newsid)
			throws PaperException {
		News news = newsDao.get(News.class, newsid);
		if (news != null) {
			comment.setNews(news);
			comment.setTime(new Date());
			comment.setPass(0);
			comment.setComment(MyTools.replaceBlank(comment.getComment()));
			commentDao.save(comment);
			if (comment.getId() > 0) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	/***
	 * 查询出新闻的所有评论
	 * @param newsid
	 * @return List<Comment>
	 * @throws PaperException
	 */
	public List<Comment> getNewsComment(Integer newsid) throws PaperException {
		List<Comment> comments = null;
		News news = newsDao.get(News.class, newsid);
		if (news != null) {
			comments = new ArrayList(news.getNew_comments());
			Collections.sort(comments, new Comparator<Comment>() {
			      @Override
			      public int compare(Comment o1, Comment o2) {
			        return o2.getId()-o1.getId();
			      }

				
			});
			return comments;
		}
		
		return null;
	}
	
}
