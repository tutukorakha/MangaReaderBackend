package com.app.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.base.BaseEntity;

@SuppressWarnings("unchecked")
@Repository
public abstract class BaseEntityDAO<E extends BaseEntity> {

	@PersistenceContext
	private EntityManager entityManager;

	protected Class<? extends E> entityClass;
	protected Table entityTable;

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@SuppressWarnings("rawtypes")
	
	public BaseEntityDAO() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		this.entityClass = (Class) pt.getActualTypeArguments()[0];
		this.entityTable = entityClass.getAnnotation(Table.class);
		logger.debug("=> Reload DAO for " + this.entityClass.getSimpleName() + ", Table Name is : " + (this.entityTable != null ? this.entityTable.name() : ""));
	}
	
	protected EntityManager getEntityManager() {
		return entityManager;
	}
	
	public E getById(Integer id) {
		String q = "select e from " + entityClass.getSimpleName() + " e where e.id = :id AND isDeleted = " + BaseEntity.ENTITY_FLAG_NOT_DELETED;
		try {
			return (E) getEntityManager().createQuery(q).setParameter("id", id).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<E> getList() {
		String q = "select e from " + entityClass.getSimpleName() + " e where isDeleted = " + BaseEntity.ENTITY_FLAG_NOT_DELETED;
		return getEntityManager().createQuery(q).getResultList();
	}

	public List<E> getListAll() {
		String q = "select e from " + entityClass.getSimpleName() + " e ";
		return getEntityManager().createQuery(q).getResultList();
	}
	
	public List<E> getListByWhere(String strWhere) {
		String q = "SELECT e FROM " + this.entityClass.getSimpleName() + " e " + strWhere;
		return getEntityManager().createQuery(q).getResultList();
	}
	
	public E getByCode(String code) {
		String q = "select e from " + entityClass.getSimpleName() + " e where e.code = :code AND isDeleted = " + BaseEntity.ENTITY_FLAG_NOT_DELETED;
		try {
			return (E) getEntityManager().createQuery(q).setParameter("code", code).setMaxResults(1).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public void detach(E entity) {
		getEntityManager().detach(entity);
	}

	public E insert(E entity, Integer userId) {
		Date date = new Date();
		entity.setIsDeleted(BaseEntity.ENTITY_FLAG_NOT_DELETED);
		entity.setCreatedAt(date);
		entity.setUpdatedAt(date);
		entity.setCreatedBy(userId);
		entity.setUpdatedBy(userId);
		getEntityManager().persist(entity);
		return entity;
	}
	
	public E insert(E entity) {
		Date date = new Date();
		entity.setIsDeleted(BaseEntity.ENTITY_FLAG_NOT_DELETED);
		entity.setCreatedAt(date);
		entity.setUpdatedAt(date);
//		entity.setCreatedBy(currentUserSession.getUserId());
//		entity.setUpdatedBy(currentUserSession.getUserId());
		getEntityManager().persist(entity);
		return entity;
	}

	public E update(E entity, Integer userId) {
		E old = getEntityManager().find(entityClass, entity.getId());
		entity.setCreatedAt(old.getCreatedAt());
		entity.setCreatedBy(old.getCreatedBy());
		entity.setIsDeleted(old.getIsDeleted());
		entity.setUpdatedAt(new Date());
		entity.setUpdatedBy(userId);
		return getEntityManager().merge(entity);

	}
	
	public E update(E entity) {
		E old = getEntityManager().find(entityClass, entity.getId());
		entity.setCreatedAt(old.getCreatedAt());
		entity.setCreatedBy(old.getCreatedBy());
		entity.setIsDeleted(old.getIsDeleted());
		entity.setUpdatedAt(new Date());
//		entity.setUpdatedBy(currentUserSession.getUserId());
		return getEntityManager().merge(entity);

	}

	public E delete(E entity, Integer userId) {
		E old = getEntityManager().find(entityClass, entity.getId());
		entity.setCreatedAt(old.getCreatedAt());
		entity.setCreatedBy(old.getCreatedBy());

//		entity.setIsDeleted(new Date().getTime());
		entity.setUpdatedAt(new Date());
		entity.setUpdatedBy(userId);
		getEntityManager().merge(entity);
		return entity;
	}
	
	public E delete(E entity) {
		E old = getEntityManager().find(entityClass, entity.getId());
		entity.setCreatedAt(old.getCreatedAt());
		entity.setCreatedBy(old.getCreatedBy());

//		entity.setIsDeleted((new Date()).getTime());
		entity.setUpdatedAt(new Date());
//		entity.setUpdatedBy(currentUserSession.getUserId());
		getEntityManager().merge(entity);
		return entity;
	}

	public E deleteById(Integer id, Integer userId) {
		E entity = getEntityManager().find(entityClass, id);
		if (entity == null) {
			return null;
		}

//		entity.setIsDeleted(new Date().getTime());
		entity.setUpdatedAt(new Date());
		entity.setUpdatedBy(userId);
		getEntityManager().merge(entity);
		return entity;
	}

	public void deleteInBatchByIds(Iterable<Integer> ids, Integer userId) {

		String hql = "delete " + entityClass.getSimpleName() + " set isDeleted = " + new Date().getTime() + " , updatedAt = GETDATE(), updatedBy= :userId where vendorDocGroup.id in (:ids)";
		getEntityManager().createQuery(hql).setParameter("ids", ids).setParameter("userId", userId).executeUpdate();
	}

	public E remove(E entity) {

		return removeById(entity.getId());
	}

	public E removeById(Integer id) {
		E entity = getEntityManager().find(entityClass, id);
		if (entity == null) {
			return null;
		}
		getEntityManager().remove(entity);
		return entity;
	}

	public void removeInBatchByIds(Iterable<Integer> ids) {

		String hql = "delete " + entityClass.getSimpleName() + " where id in (:ids)";
		getEntityManager().createQuery(hql).setParameter("ids", ids).executeUpdate();
	}
}
