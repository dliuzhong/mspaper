package cn.mnu.paper.dao.impl;

import java.util.List;

import cn.mnu.paper.common.dao.impl.BaseDaoHibernate;
import cn.mnu.paper.dao.LayoutDao;
import cn.mnu.paper.domain.Layout;


/***
 * LayoutDao组件的实现类
 * @author mdl
 * @version 1.0
 * @date 2014-07-14
 */
public class LayoutDaoHibernate extends BaseDaoHibernate<Layout> 
	implements LayoutDao {

	/***
	 * 根据期号和版面号查找版面信息
	 * @param paper 期号报纸
	 * @param layno 版面号
	 * @return 对应的版面
	 */
	public Layout findLayoutByPaperAndLayno(Integer paperid, int layno) {
	
		List<Layout> ll = find("select l from Layout l where l.paper.id=?0 and l.layout_no=?1", 
					paperid, layno);
		
		if (ll != null && ll.size() >= 1) {
			return (Layout) ll.get(0);
		}
		return null;
	}
	/***
	 * 根据paperid报纸期号ID查找所有版面信息
	 * @param paperid 报纸期号ID
	 * @return List<Layout> 所有版面信息
	 */
	public List<Layout> findLayoutByPaper(Integer paperid) {
		return find("select l from Layout l where l.paper.id=?0", paperid);
	}

}
