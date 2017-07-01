package cn.mnu.paper.dao;

import java.util.List;

import cn.mnu.paper.common.dao.BaseDao;
import cn.mnu.paper.domain.Paper;

/***
 * 报纸基本信息DAO接口
 * @author mdl
 * @version 1.0
 * @date 2014-07-14
 */
public interface PaperDao extends BaseDao<Paper> {
	/***
	 * 根据期号查找报纸
	 * @param paper 期号
	 * @return 对应的报纸
	 */
	Paper findPaperByPaper(String paper);
	/***
	 * 根据期号和年查找报纸
	 * @param paper
	 * @param year
	 * @return List<Paper>
	 */
	List<Paper> findPaperByPaperAndYear(String paper, String year);
	/***
	 * 获取所有papers记录的总数
	 * @return int
	 */
	int getAllRows();
	/***
	 * 获取所有papers记录的总数
	 * @param query 关键字
	 * @return int
	 */
	int getAllRows(String query);
	/***
	 * 对paper进行query匹配查找获取所有活动的papers记录
	 * @param query
	 * @return
	 */
	List<Paper> getPaperByPaperQuery(String query);
	/***
	 * 根据 出版时间cbtime查询所有paper
	 * @return List<Paper>
	 */
	List<Paper> getAllPaperOrderByCbtime();
	/***
	 * 查询日期最近的Paper
	 * @return Paper
	 */
	Paper getLastPaper();
	/***
	 * 查询第一期报纸
	 * @return Paper
	 */
	Paper getFirstPaper();
}
