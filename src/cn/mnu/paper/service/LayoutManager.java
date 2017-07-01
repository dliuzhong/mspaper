package cn.mnu.paper.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.mnu.paper.domain.Layout;
import cn.mnu.paper.exception.PaperException;



/***
 * Layout业务逻辑组件接口
 * @author mdl
 * @version 1.0
 * @date 2014-07-15
 */
public interface LayoutManager {
	/***
	 * 添加新的报纸版面信息
	 * @param layout
	 * @param paperid
	 * @return
	 * @throws PaperException
	 */
	int addLayout(Layout layout, Integer paperid) throws PaperException;
	
	/***
	 * 验证是否已有该报纸版面信息
	 * @param paper 需要验证的期号报纸
	 * @param layno 需要验证的版面
	 * @return boolean
	 * @throws PaperException
	 */
	boolean validateLayoutByPaperAndLayno(Integer paperid, int layno) throws PaperException;
	/***
	 * 验证是否已有该报纸版面信息
	 * @param paper 需要验证的期号报纸
	 * @param layno 需要验证的版面
	 * @return Layout
	 * @throws PaperException
	 */
	Layout findLayoutByPaperAndLayno(Integer paperid, int layno) throws PaperException;
	
	/***
	 * 根据id获取Layout
	 * @param id
	 * @return Layout
	 * @throws PaperException
	 */
	Layout findLayoutByID(Integer id) throws PaperException;

	/***
	 * 根据paperid报纸期号ID查找所有版面信息
	 * @param paperid 报纸期号ID
	 * @return List<Layout> 所有版面信息
	 * @throws PaperException
	 */
	List<Layout> getLayoutByPaper(Integer paperid) throws PaperException;
	/***
	 * 修改版面基本信息Layout
	 * @param layout 要修改的Layout
	 * @param request 用于修改main.html
	 * @return layoutid
	 * @throws PaperException
	 */
	int changeLayoutData(Layout layout, HttpServletRequest request) throws PaperException;
	
	/***
	 * 修改版面pic pdf 信息Layout
	 * @param layout 要修改的Layout
	 * @return layoutid
	 * @throws PaperException
	 */
	int changeLayoutPicAndPdf(Layout layout) throws PaperException;
	/***
	 * 根据id删除版面及版面所有的新闻内容及文件
	 * @param id
	 * @param request 用户删除对应的文件夹及文件夹里的所有文件
	 * @return boolean
	 * @throws PaperException
	 */
	boolean removeLayoutById(Integer id, HttpServletRequest request) throws PaperException;
}
