package cn.mnu.paper.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import cn.mnu.paper.dao.NewsDao;
import cn.mnu.paper.dao.SubjectDao;
import cn.mnu.paper.domain.News;
import cn.mnu.paper.domain.Subject;
import cn.mnu.paper.exception.PaperException;
import cn.mnu.paper.service.SubjectManager;

public class SubjectManagerImpl implements SubjectManager {
	static Logger log = Logger.getLogger(
			SubjectManagerImpl.class.getName());
	private SubjectDao subjectDao;
	private NewsDao newsDao;
	
	public SubjectDao getSubjectDao() {
		return subjectDao;
	}
	public void setSubjectDao(SubjectDao subjectDao) {
		this.subjectDao = subjectDao;
	}
	public NewsDao getNewsDao() {
		return newsDao;
	}
	public void setNewsDao(NewsDao newsDao) {
		this.newsDao = newsDao;
	}
	
	/***
	 * 获取所有活动的Subject
	 * @return 活动的Subject
	 * @throws PaperException 
	 */
	public List<Subject> findActiveSubjects() throws PaperException {
		try {
			List<Subject> sl = subjectDao.getAllActiveSubjects();
			return sl;
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("查找所有活动的专题出现失败，请重试");
		}
	}
	
	
	@Override
	public int getAllAciveSubjectSum() throws PaperException {
		try {
			int sum = subjectDao.getAllActiveRows();
			return sum;
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("查找所有活动的专题总数量出现失败，请重试");
		}
	}

	/***
	 * 获取所有的Subject总数量
	 * @return 总数量
	 */
	public int getAllSubjectSum() throws PaperException {
		try {
			int sum = subjectDao.getAllRows();
			return sum;
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("查找所有活动的专题总数量出现失败，请重试");
		}
	}
	/***
	 * 获取query所有的Subject总数量
	 * @return 总数量
	 */
	public int getQuerySubjectSum(String query) throws PaperException {
		try {
			int sum = subjectDao.getActiveRows(query);
			return sum;
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("查找所有活动的专题总数量出现失败，请重试");
		}
	}
	/***
	 * 对Name进行query匹配查找获取所有活动的Subject
	 * @param query
	 * @param start 开始
	 * @param limit 大小
	 * @return 满足条件的Subject
	 * @throws PaperException
	 */
	public List<Subject> findActiveSubjectsByNameQuery(String query, int start,
			int limit) throws PaperException {
		try {
			List<Subject> sl = subjectDao.getActiveSubjectsByNameQurey(query);
			List<Subject> s = new ArrayList<Subject>();
			Subject sb;
			for (int i = 0;i < sl.size(); i++) {
				sb = sl.get(i);
				//System.out.println(sb.getName());
				if (i >= start && i < start + limit) {
					
					s.add(sb);
				}
			}
			return s;
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("查找所有活动的专题出现失败，请重试");
		}
	}
	/***
	 * 获取所有的Subject
	 * @return 活动的Subject
	 * @throws PaperException 
	 */
	public List<Subject> findAllSubjects(int start, int limit, String sort, String dir) throws PaperException {
		try {
			List<Subject> sl = subjectDao.getAllSubjects(sort, dir);
			List<Subject> s = new ArrayList<Subject>();
			Subject sb;
			for (int i = 0;i < sl.size(); i++) {
				sb = sl.get(i);
				//System.out.println(sb.getName());
				if (i >= start && i < start + limit) {
					
					s.add(sb);
				}
			}
			return s;
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("查找所有的专题出现失败，请重试");
		}
	}
	/***
	 * 添加专题内容
	 * @param subject 所添加的新专题
	 * @return subjet id
	 * @throws PaperException
	 */
	public Integer addSubject(Subject subject) throws PaperException {
		try {
			subject.setTime(new Date());
			subjectDao.save(subject);
			if (subject.getId() != 0) {
				return subject.getId();
			} else {
				return 0;
			}
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("添加专题出现失败，请重试");
		}
	}
	/***
	 * 修改专题
	 * @param subject 要修改的专题
	 * @return 修改后的subject id
	 * @throws PaperException
	 */
	public Integer changeSubject(Subject subject) throws PaperException {
		try {
			Subject s = subjectDao.get(Subject.class, subject.getId());
			if (s != null) {
				s.setName(subject.getName());
				s.setDe(subject.getDe());
				subjectDao.update(s);
				
				if (s.getId() != 0) {
					return s.getId();
				} else {
					return 0;
				}
			} else {
				return 0;
			}
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("修改专题出现失败，请重试");
		}
	}
	/***
	 * 根据ID删除专题
	 * @param id
	 * @return boolean
	 * @throws PaperException
	 */
	public boolean removeSubject(Integer id) throws PaperException {
		try {
			if (id != 0 && id != 1) {
				Subject subject = subjectDao.get(Subject.class, id);
				Set<News> newss = subject.getSubject_news();
				for (News n : newss) {
					//System.out.println(n.getTitle());
					n.setSubject(subjectDao.get(Subject.class, 1));
				}
				subjectDao.delete(Subject.class, id);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("删除专题出现失败，请重试");
		}
		
	}
	/***
	 * 根据ID修改专题的状态，修改为1
	 * @param id
	 * @return Integer
	 * @throws PaperException
	 */
	public Integer changeSubjectStTo1(Integer id) throws PaperException {
		try {
			Subject s = subjectDao.get(Subject.class, id);
			if (s != null) {
				s.setSt(1);
				subjectDao.update(s);
				return s.getId();
			} else {
				return 0;
			}
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("修改专题状态为1出现失败，请重试");
		}
	}
	/***
	 * 根据ID修改专题的状态，修改为0
	 * @param id
	 * @return Integer
	 * @throws PaperException
	 */
	public Integer changeSubjectStTo0(Integer id) throws PaperException {
		
		try {
			Subject s = subjectDao.get(Subject.class, id);
			if (s != null) {
				s.setSt(0);
				subjectDao.update(s);
				return s.getId();
			} else {
				return 0;
			}
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new PaperException("修改专题状态为0出现失败，请重试");
		}
	}
	
	
}
