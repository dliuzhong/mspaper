package cn.mnu.paper.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import cn.mnu.paper.domain.Paper;
import cn.mnu.paper.exception.PaperException;

/***
 * Paper业务逻辑组件接口
 * @author mdl
 * @version 1.0
 * @date 2014-07-14
 */
public interface PaperManager {
	/***
	 * 新增报纸信息
	 * @param paper 新增报纸的Paper实例
	 * @return 新增报纸
	 */
	Paper addPaper(Paper paper) throws PaperException;
	/***
	 * 验证系统中是否已有该报纸
	 * @param paper 需要验证的期号
	 * @return 报纸是否可用
	 */
	boolean validatePaperByName(String paper) throws PaperException;
	/***
	 * 根据报纸期号找查
	 * @param paper 需要找查的期号
	 * @return 报纸
	 * @throws PaperException 
	 */
	Paper findPaperByName(String paper) throws PaperException;
	/***
	 * 根据期号和年查找报纸
	 * @param paper
	 * @param year
	 * @return
	 * @throws PaperException
	 */
	List<Paper> getPaperByPaperAndYear(String paper, String year) throws PaperException;

	/***
	 * 根据 ID查找报纸基本信息
	 * @param id ID
	 * @return Paper
	 * @throws PaperException
	 */
	Paper getPaperById(Integer id) throws PaperException;
	
	/***
	 * 根据paperID更改版面数 + 1
	 * @param paperid
	 * @return int
	 * @throws PaperException
	 */
	int changePaperLay_num(Integer paperid) throws PaperException;
	/***
	 * 根据ID删除报纸
	 * @param id ID
	 * @param request 用户删除文件
	 * @return boolean
	 * @throws PaperException
	 */
	boolean removePaperById(Integer id, HttpServletRequest request) throws PaperException;
	/***
	 * 修改Paper
	 * @param paper 修改的内容
	 * @param request 用于修改index.jsp
	 * @return paper-id
	 * @throws PaperException
	 */
	int changePaper(Paper paper, HttpServletRequest request) throws PaperException;
	/***
	 * 根据 query关键字获取相关的总数
	 * @param query 关键字
	 * @return int
	 * @throws PaperException
	 */
	int findQueryPaperSum(String query) throws PaperException;
	/***
	 * 对paper进行query匹配查找获取所有papers
	 * @param query
	 * @param start
	 * @param limit
	 * @return
	 * @throws PaperException
	 */
	List<Paper> findPaperByPaperQuery(String query, int start, int limit) throws PaperException;
	/***
	 * 根据出版时间cbtime获取所有Paper
	 * @return List<Paper>
	 * @throws PapaerException
	 */
	List<Paper> findAllPaperOrderByCbtime() throws PaperException;
	/***
	 * 获取最新一期Paper
	 * @return Paper
	 * @throws PaperException
	 */
	Paper findLastPaper() throws PaperException;
	
	/***
	 * 获取ID的期的前一期
	 * @param id
	 * @return 如果当前为最前一期，返回0，否则返回前一期的期号
	 * @throws PaperException
	 */
	String findPrevPaper(Integer id) throws PaperException;  
	/***
	 * 获取ID的期的后一期
	 * @param id
	 * @return 如果当前为最后一期，返回0，否则返回后一期的期号
	 * @throws PaperException
	 */
	String findNextPaper(Integer id) throws PaperException;
}
