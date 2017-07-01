package cn.mnu.paper.service;

import java.util.List;

import cn.mnu.paper.domain.Comment;
import cn.mnu.paper.exception.PaperException;

/***
 * Commentҵ���߼�����ӿ�
 * @author mdl
 * @version 1.0
 * @date 2014-08-10
 */
public interface CommentManager {

	/**
	 * ��ȡ�������۵�����
	 * @return int
	 * @throws PaperException
	 */
	int findAllCommentSum() throws PaperException;
	/***
	 * ��ȡ������������
	 * @param start ��ʼ
	 * @param limit ����
	 * @param sort ����ؼ���
	 * @param dir ����ʽ
	 * @return
	 * @throws Exception
	 */
	List<Comment> findAllComments(int start, int limit, 
			String sort, String dir) throws PaperException;
	/***
	 * ����IDɾ������
	 * @param id
	 * @return boolean
	 * @throws PaperException
	 */
	boolean removeComment(Integer id) throws PaperException;
	/***
	 * �޸�pass,���ͨ��
	 * @param id ��˵�ID
	 * @return Integer
	 * @throws PaperException
	 */
	Integer changeCommentPassTo1(Integer id) throws PaperException;
	/***
	 * �޸�pass,��ͨ�����
	 * @param id ��˵�ID
	 * @return Integer
	 * @throws PaperException
	 */
	Integer changeCommentPassTo0(Integer id) throws PaperException;
	/***
	 * ��������
	 * @param comment
	 * @param newsid
	 * @return true or false
	 * @throws PaperException
	 */
	boolean addComment(Comment comment, Integer newsid) throws PaperException;

	/***
	 * ��ѯ�����ŵ���������
	 * @param newsid
	 * @return List<Comment>
	 * @throws PaperException
	 */
	List<Comment> getNewsComment(Integer newsid) throws PaperException;
}
