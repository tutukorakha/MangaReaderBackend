package com.app.module;

import com.app.AppConstant;
import com.app.entity.bean.User;
import com.app.entity.dao.UserDAO;
import com.app.model.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import com.app.model.Response;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;

@RestController
@RequestMapping("/oauth/user")
public class AuthController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User register(@RequestBody Map data) {
        User user = new ObjectMapper().convertValue(data, User.class);
        Map lastUser = userDAO.getLastUser();
        String userCode = (String) lastUser.get("code");
        String initialUserCode = userCode.substring(0, 3);
        Integer countUserCode = Integer.parseInt(userCode.substring(3, 4)) + 1;
        String finalCode = initialUserCode + countUserCode.toString();
        user.setCode(finalCode);
        user.setName((String) data.get("name"));
        user.setAge(Integer.parseInt(data.get("age").toString()));
        user.setRole(AppConstant.SUBSCRIBER);
        user.setEmail((String) data.get("email"));
        user.setUsername((String) data.get("username"));
        user.setPassword(passwordEncoder.encode(data.get("password").toString()));
        User checkUser = userDAO.getByCode(user.getCode());
        if(checkUser == null) {
            userDAO.insert(user);
        }
        throw new RuntimeException("USER ALREADY EXISTS !!!");
    }
}
