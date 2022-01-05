package com.app.entity.dao;

import org.springframework.stereotype.Repository;

import javax.persistence.Query;

import com.app.base.BaseEntityDAO;
import com.app.entity.bean.User;

import java.util.Map;

@Repository
public class UserDAO extends BaseEntityDAO<User> {

    public Map getUserByUsername(String username) {
        String query = "SELECT * FROM User u WHERE u.username = ?";
        Query resultQuery = getEntityManager().createQuery(query).setParameter("username", username);
        return (Map) resultQuery.getSingleResult();
    }
}
