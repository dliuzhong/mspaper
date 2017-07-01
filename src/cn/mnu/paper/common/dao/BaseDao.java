package cn.mnu.paper.common.dao;
import java.io.Serializable;
import java.util.List;

/**
 * 
 * DAO基类，实现CRUD
 * @author mdl
 * @version 1.0
 * @date 2014-07-12
 */
public interface BaseDao<T> {
	// 根据ID加载实体
	T get(Class<T> entityClazz , Serializable id);
	// 保存实体
	Serializable save(T entity);
	// 更新实体
	void update(T entity);
	// 删除实体
	void delete(T entity);
	// 根据ID删除实体
	void delete(Class<T> entityClazz , Serializable id);
	// 获取所有实体
	List<T> findAll(Class<T> entityClazz);
}
