package cn.mnu.paper.common.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.mnu.paper.common.dao.BaseDao;


/***
 * ʵ��DAO����
 * @author mdl
 * @version 1.0
 * @date 2014-07-12
 */
public class BaseDaoHibernate<T> extends HibernateDaoSupport
		implements BaseDao<T> {
	// ����ID����ʵ��
	public T get(Class<T> entityClazz , Serializable id)
	{
		return (T)getSessionFactory().getCurrentSession().get(entityClazz , id);
	}
	// ����ʵ��
	public Serializable save(T entity)
	{
		return getSessionFactory().getCurrentSession()
			.save(entity);
	}
	// ����ʵ��
	public void update(T entity)
	{
		getSessionFactory().getCurrentSession().saveOrUpdate(entity);
	}
	// ɾ��ʵ��
	public void delete(T entity)
	{
		getSessionFactory().getCurrentSession().delete(entity);
	}
	// ����IDɾ��ʵ��
	public void delete(Class<T> entityClazz , Serializable id)
	{
		delete(get(entityClazz , id));
	}
	// ��ȡ����ʵ��
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
		// ������ѯ
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		// Ϊ����ռλ����HQL������ò���
		for(int i = 0 , len = params.length ; i < len ; i++)
		{
			query.setParameter(i + "" , params[i]);
		}
		return query.list();
	}
	
}
