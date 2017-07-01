package cn.mnu.paper.common.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.mnu.paper.common.dao.BaseDao;


/***
 * 实现DAO基类
 * @author mdl
 * @version 1.0
 * @date 2014-07-12
 */
public class BaseDaoHibernate<T> extends HibernateDaoSupport
		implements BaseDao<T> {
	// 根据ID加载实体
	public T get(Class<T> entityClazz , Serializable id)
	{
		return (T)getSessionFactory().getCurrentSession().get(entityClazz , id);
	}
	// 保存实体
	public Serializable save(T entity)
	{
		return getSessionFactory().getCurrentSession()
			.save(entity);
	}
	// 更新实体
	public void update(T entity)
	{
		getSessionFactory().getCurrentSession().saveOrUpdate(entity);
	}
	// 删除实体
	public void delete(T entity)
	{
		getSessionFactory().getCurrentSession().delete(entity);
	}
	// 根据ID删除实体
	public void delete(Class<T> entityClazz , Serializable id)
	{
		delete(get(entityClazz , id));
	}
	// 获取所有实体
	public List<T> findAll(Class<T> entityClazz)
	{
		return find("select en from "
			+ entityClazz.getSimpleName() + " en");
	}
	protected List<T> find(String hql)
	{
		return getSessionFactory().getCurrentSession().createQuery(hql).list();
	}
	protected List<T> find(String hql , Object... params)
	{
		// 创建查询
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		// 为包含占位符的HQL语句设置参数
		for(int i = 0 , len = params.length ; i < len ; i++)
		{
			query.setParameter(i + "" , params[i]);
		}
		return query.list();
	}
	
}
