package com.app.service;

import com.app.entity.bean.User;
import com.app.entity.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Map checkUser = userDAO.getUserByUsername(userName);
        if(checkUser != null) {
            User userNew = new User();
            userNew.setId(Integer.parseInt(checkUser.get("id").toString()));
            userNew.setCode((String) checkUser.get("code"));
            userNew.setName((String) checkUser.get("name"));
            userNew.setAge(Integer.parseInt(checkUser.get("age").toString()));
            userNew.setRole((String) checkUser.get("role"));
            userNew.setEmail((String) checkUser.get("email"));
            userNew.setUsername((String) checkUser.get("username"));
            userNew.setPassword((String) checkUser.get("password"));

            SimpleGrantedAuthority role = new SimpleGrantedAuthority(userNew.getRole());
            return new org.springframework.security.core.userdetails.User(userNew.getUsername(), userNew.getPassword(), Collections.singleton(role));
        }
        throw new UsernameNotFoundException("USERNAME TIDAK DITEMUKAN !!!");
    }
}
