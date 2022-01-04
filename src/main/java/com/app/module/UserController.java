package com.app.module;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.AppConstant;
//import com.app.base.controller.BaseModuleController;
import com.app.entity.bean.User;
import com.app.entity.dao.UserDAO;
//import com.app.lazy.entity.bean.VendorTypeBean;
//import com.app.lazy.entity.bean.dao.VendorTypeBeanDAO;
//import com.app.model.Page;
import com.app.model.Response;
//import com.app.model.RouteRequest;
//import com.app.model.TableRequest;

@RestController
@RequestMapping("/user")
@Transactional(readOnly = true)
public class UserController {
	
	@Autowired
	private UserDAO userDAO;
		
	@GetMapping("/get-all-users")
	public List<User> getAllUsers() {
		return userDAO.getListAll();
	}
	
	@PostMapping("/insert-user")
	@Transactional(readOnly = false)
	public Response<User> insertUser(@RequestBody User data) {
		User inputUser = new User();
		inputUser = data;	
		User checkUser = userDAO.getByCode(data.getCode());
		if(checkUser == null) {
			userDAO.insert(inputUser);
			return Response.ok(AppConstant.STATUS_TEXT_SUCCESS, inputUser);
		} 
		
		else {
			return Response.error(AppConstant.STATUS_TEXT_ERROR, inputUser);
		}
	}
}