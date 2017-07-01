package cn.mnu.paper.dao;

import java.util.List;

import cn.mnu.paper.common.dao.BaseDao;
import cn.mnu.paper.domain.Layout;


/***
 * ��ֽ���������ϢDAO�ӿ�
 * @author mdl
 * @version 1.0
 * @date 2014-07-15
 */
public interface LayoutDao extends BaseDao<Layout> {
	/***
	 * �����ںźͰ���Ų��Ұ�����Ϣ
	 * @param paper �ںű�ֽ
	 * @param layno �����
	 * @return ��Ӧ�İ���
	 */
	Layout findLayoutByPaperAndLayno(Integer paperid, int layno);
	/***
	 * ����paperid��ֽ�ں�ID�������а�����Ϣ
	 * @param paperid ��ֽ�ں�ID
	 * @return List<Layout> ���а�����Ϣ
	 */
	List<Layout> findLayoutByPaper(Integer paperid);
}
