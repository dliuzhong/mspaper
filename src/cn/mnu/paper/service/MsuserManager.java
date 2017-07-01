package cn.mnu.paper.service;

import java.util.List;

import cn.mnu.paper.domain.Admin;
import cn.mnu.paper.domain.Msuser;
import cn.mnu.paper.exception.PaperException;

/***
 * MsUser业务逻辑组件接口
 * @author mdl
 * @version 1.0
 * @date 2014-08-12
 */
public interface MsuserManager {
	/***
	 * 获取所有用户的总数量
	 * @return int 所有用户的总数量
	 * @throws PaperException
	 */
	int findAllMsuserSum() throws PaperException;
	/***
	 * 获取所有用户
	 * @param start 开始
	 * @param limit 数量
	 * @param sort 排序关键字
	 * @param dir 排序方式
	 * @return 获取所有用户
	 * @throws PaperException
	 */
	List<Msuser> findAllMsuser(int start, int limit, String sort, String dir)
		throws PaperException;
	/***
	 * 增加投稿用户
	 * @param msuer
	 * @return Integer
	 * @throws PaperException
	 */
	int addMsuser(Msuser msuser) throws PaperException;
	/***
	 * 修改投稿用户
	 * @param msuser
	 * @return
	 * @throws PaperException
	 */
	int changeUNPWMsuser(Msuser msuser) throws PaperException;
	/***
	 * 根据ID删除投稿用户
	 * @param id
	 * @return boolean
	 * @throws PaperException
	 */
	boolean deleteMsuser(Integer id) throws PaperException;
	/***
	 * 根据departmentid获取相应用户的数量
	 * @param departmentid
	 * @return 相应用户的数量
	 * @throws PaperException
	 */
	int findMsuserSumByDepartment(Integer departmentid) throws PaperException;
	/***
	 * 根据ID获取msuser
	 * @param id
	 * @return msuser
	 * @throws PaperException
	 */
	Msuser findMsuserById(Integer id) throws PaperException;
	/***
	 * 修改Msuser
	 * @param msuser
	 * @param departmentid
	 * @return msuser id
	 * @throws PaperException
	 */
	int changeMsuser(Msuser msuser, Integer departmentid) throws PaperException;
	/***
	 * 修改Msuser的类型
	 * @param msuser
	 * @return msuser id
	 * @throws PaperException
	 */
	int changeMsuserType(Msuser msuser) throws PaperException;
	/***
	 * 验证email是否已有用户
	 * @param email
	 * @return boolean
	 * @throws PaperException
	 */
	boolean validateMsuserByEmail(Integer id, String email) throws PaperException;
	
	/***
	 * 验证投稿用户登录
	 * @param admin 需要验证的Msuser实例 
	 * @return 验证成功后的Msuser实例，否则为Null
	 */
	Msuser loginValid(Msuser msuser) throws PaperException;
	/***
	 * 获取所有Msuser
	 * @return 所有Msuser
	 * @throws PaperException
	 */
	List<Msuser> findAllMsuser() throws PaperException;
}
