package cn.mnu.paper.common.dao;
import java.io.Serializable;
import java.util.List;

/**
 * 
 * DAO���࣬ʵ��CRUD
 * @author mdl
 * @version 1.0
 * @date 2014-07-12
 */
public interface BaseDao<T> {
	// ����ID����ʵ��
	T get(Class<T> entityClazz , Serializable id);
	// ����ʵ��
	Serializable save(T entity);
	// ����ʵ��
	void update(T entity);
	// ɾ��ʵ��
	void delete(T entity);
	// ����IDɾ��ʵ��
	void delete(Class<T> entityClazz , Serializable id);
	// ��ȡ����ʵ��
	List<T> findAll(Class<T> entityClazz);
}
