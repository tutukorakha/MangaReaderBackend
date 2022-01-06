package com.app.entity.dao;

import com.app.AppConstant;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

import com.app.base.BaseEntityDAO;
import com.app.entity.bean.User;
import com.app.helper.MapHelper;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Repository
public class UserDAO extends BaseEntityDAO<User> {

    public Map getUserByUsername(String username) {
        Map<String, Object> returnMap = new HashMap();
        String query = "SELECT u FROM User u WHERE u.username = ? AND u.is_deleted = " + User.ENTITY_FLAG_NOT_DELETED;
        Query resultQuery = getEntityManager().createNativeQuery(query).setParameter("username", username);
        if(resultQuery.getSingleResult() != null) {
            returnMap = MapHelper.convertObject(resultQuery.getSingleResult());
        }
        return returnMap;
    }

    public Map getLastUser() {
        Map<String, Object> returnMap = new HashMap();
        String query = "SELECT * FROM User u WHERE u.is_deleted = " + User.ENTITY_FLAG_NOT_DELETED + " ORDER BY u.id DESC LIMIT 1";
        Query resultQuery = getEntityManager().createNativeQuery(query, User.class);
        if(resultQuery.getSingleResult() != null) {
            returnMap = MapHelper.convertObject(resultQuery.getSingleResult());
        }
        return returnMap;
    }
}
