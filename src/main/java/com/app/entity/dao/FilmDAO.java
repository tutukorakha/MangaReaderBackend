package com.app.entity.dao;

import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.app.base.BaseEntityDAO;
import com.app.entity.bean.Film;

@Repository
public class FilmDAO extends BaseEntityDAO<Film>{

	public List<Film> getListWhereIsBuyFalse() {
		String strQuery = "SELECT f FROM Film f WHERE f.isBuy = false";
		Query query = getEntityManager().createQuery(strQuery);
		return query.getResultList();
	}
}
