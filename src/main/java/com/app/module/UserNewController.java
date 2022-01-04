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
import com.app.entity.bean.User;
//import com.app.base.controller.BaseModuleController;
import com.app.entity.bean.UserNew;
import com.app.entity.dao.UserDAO;
import com.app.entity.dao.UserNewDAO;
//import com.app.lazy.entity.bean.VendorTypeBean;
//import com.app.lazy.entity.bean.dao.VendorTypeBeanDAO;
//import com.app.model.Page;
import com.app.model.Response;
//import com.app.model.RouteRequest;
//import com.app.model.TableRequest;

@RestController
@RequestMapping("/user_new")
@Transactional(readOnly = true)
public class UserNewController {

	@Autowired
	private UserNewDAO userNewDAO;
		
	@GetMapping("/get-all-users-new")
	public List<UserNew> getAllUsersNew() {
		return userNewDAO.getListAll();
	}
	
	@PostMapping("/insert-user-new")
	@Transactional(readOnly = false)
	public Response<UserNew> insertUserNew(@RequestBody UserNew data) {
		UserNew inputUser = new UserNew();
		inputUser = data;	
		UserNew checkUser = userNewDAO.getByCode(data.getCode());
		if(checkUser == null) {
			userNewDAO.insert(inputUser);
			return Response.ok(AppConstant.STATUS_TEXT_SUCCESS, inputUser);
		} 
		
		else {
			return Response.error(AppConstant.STATUS_TEXT_ERROR, inputUser);
		}
	}
}
