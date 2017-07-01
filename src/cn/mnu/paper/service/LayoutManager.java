package cn.mnu.paper.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.mnu.paper.domain.Layout;
import cn.mnu.paper.exception.PaperException;



/***
 * Layoutҵ���߼�����ӿ�
 * @author mdl
 * @version 1.0
 * @date 2014-07-15
 */
public interface LayoutManager {
	/***
	 * ����µı�ֽ������Ϣ
	 * @param layout
	 * @param paperid
	 * @return
	 * @throws PaperException
	 */
	int addLayout(Layout layout, Integer paperid) throws PaperException;
	
	/***
	 * ��֤�Ƿ����иñ�ֽ������Ϣ
	 * @param paper ��Ҫ��֤���ںű�ֽ
	 * @param layno ��Ҫ��֤�İ���
	 * @return boolean
	 * @throws PaperException
	 */
	boolean validateLayoutByPaperAndLayno(Integer paperid, int layno) throws PaperException;
	/***
	 * ��֤�Ƿ����иñ�ֽ������Ϣ
	 * @param paper ��Ҫ��֤���ںű�ֽ
	 * @param layno ��Ҫ��֤�İ���
	 * @return Layout
	 * @throws PaperException
	 */
	Layout findLayoutByPaperAndLayno(Integer paperid, int layno) throws PaperException;
	
	/***
	 * ����id��ȡLayout
	 * @param id
	 * @return Layout
	 * @throws PaperException
	 */
	Layout findLayoutByID(Integer id) throws PaperException;

	/***
	 * ����paperid��ֽ�ں�ID�������а�����Ϣ
	 * @param paperid ��ֽ�ں�ID
	 * @return List<Layout> ���а�����Ϣ
	 * @throws PaperException
	 */
	List<Layout> getLayoutByPaper(Integer paperid) throws PaperException;
	/***
	 * �޸İ��������ϢLayout
	 * @param layout Ҫ�޸ĵ�Layout
	 * @param request �����޸�main.html
	 * @return layoutid
	 * @throws PaperException
	 */
	int changeLayoutData(Layout layout, HttpServletRequest request) throws PaperException;
	
	/***
	 * �޸İ���pic pdf ��ϢLayout
	 * @param layout Ҫ�޸ĵ�Layout
	 * @return layoutid
	 * @throws PaperException
	 */
	int changeLayoutPicAndPdf(Layout layout) throws PaperException;
	/***
	 * ����idɾ�����漰�������е��������ݼ��ļ�
	 * @param id
	 * @param request �û�ɾ����Ӧ���ļ��м��ļ�����������ļ�
	 * @return boolean
	 * @throws PaperException
	 */
	boolean removeLayoutById(Integer id, HttpServletRequest request) throws PaperException;
}
