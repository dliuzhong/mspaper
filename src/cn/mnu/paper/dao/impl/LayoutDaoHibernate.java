package cn.mnu.paper.dao.impl;

import java.util.List;

import cn.mnu.paper.common.dao.impl.BaseDaoHibernate;
import cn.mnu.paper.dao.LayoutDao;
import cn.mnu.paper.domain.Layout;


/***
 * LayoutDao�����ʵ����
 * @author mdl
 * @version 1.0
 * @date 2014-07-14
 */
public class LayoutDaoHibernate extends BaseDaoHibernate<Layout> 
	implements LayoutDao {

	/***
	 * �����ںźͰ���Ų��Ұ�����Ϣ
	 * @param paper �ںű�ֽ
	 * @param layno �����
	 * @return ��Ӧ�İ���
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
	 * ����paperid��ֽ�ں�ID�������а�����Ϣ
	 * @param paperid ��ֽ�ں�ID
	 * @return List<Layout> ���а�����Ϣ
	 */
	public List<Layout> findLayoutByPaper(Integer paperid) {
		return find("select l from Layout l where l.paper.id=?0", paperid);
	}

}
