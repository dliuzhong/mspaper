package cn.mnu.paper.dao;

import java.util.List;

import cn.mnu.paper.common.dao.BaseDao;
import cn.mnu.paper.domain.Layout;


/***
 * 报纸版面基本信息DAO接口
 * @author mdl
 * @version 1.0
 * @date 2014-07-15
 */
public interface LayoutDao extends BaseDao<Layout> {
	/***
	 * 根据期号和版面号查找版面信息
	 * @param paper 期号报纸
	 * @param layno 版面号
	 * @return 对应的版面
	 */
	Layout findLayoutByPaperAndLayno(Integer paperid, int layno);
	/***
	 * 根据paperid报纸期号ID查找所有版面信息
	 * @param paperid 报纸期号ID
	 * @return List<Layout> 所有版面信息
	 */
	List<Layout> findLayoutByPaper(Integer paperid);
}
