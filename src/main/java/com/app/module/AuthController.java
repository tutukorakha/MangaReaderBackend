package com.app.module;

import com.app.entity.bean.User;
import com.app.entity.dao.UserDAO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/oauth/user")
public class AuthController {

    private UserDAO userDAO;

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public User register(@RequestBody Map data) {
//
//    }
}
