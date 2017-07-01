package cn.mnu.paper.dao;

import java.util.List;

import cn.mnu.paper.common.dao.BaseDao;
import cn.mnu.paper.domain.Subject;

/***
 * 专题DAO接口
 * @author mdl
 * @version 1.0
 * @date 2014-07-17
 */
public interface SubjectDao extends BaseDao<Subject> {
	/***
	 * 获取所有subjects记录的总数
	 * @return
	 */
	int getAllRows();
	/***
	 * 获取所有活动的subjects记录的总数
	 * @return
	 */
	int getAllActiveRows();
	/***
	 * 获取所有的subjects记录
	 * @param sort 排序关键这
	 * @param dir 排序方式
	 * @return Subject记录
	 */
	List<Subject> getAllSubjects(String sort, String dir);
	/***
	 * 获取所有活动的subjects记录
	 * @return Subject记录
	 */
	List<Subject> getAllActiveSubjects();
	
	/***
	 * 获取query活动的subjects记录的总数
	 * @return
	 */
	int getActiveRows(String query);
	/***
	 * 对Name进行query匹配查找获取所有活动的subjects记录
	 * @param query
	 * @param start 开始
	 * @param limit 大小
	 * @return
	 */
	List<Subject> getActiveSubjectsByNameQurey(String query);
	
}
