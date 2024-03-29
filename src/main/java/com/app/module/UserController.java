package com.app.module;

import java.util.List;
import java.util.Map;

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

import javax.persistence.NoResultException;
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
	public Response<User> insertUser(@RequestBody Map data) {
		User inputUser = new User();
		inputUser.setCode((String) data.get("code"));
		inputUser.setName((String) data.get("name"));
		inputUser.setAge(Integer.parseInt(data.get("age").toString()));
		inputUser.setRole((String) data.get("role"));
		inputUser.setEmail((String) data.get("email"));
		inputUser.setUsername((String) data.get("username"));
		inputUser.setPassword((String) data.get("password"));
		User checkUser = userDAO.getByCode(inputUser.getCode());
		try{
			if(checkUser == null) {
				userDAO.insert(inputUser);
				return Response.ok();
			}
			else {
				return Response.error(AppConstant.STATUS_TEXT_ERROR + " : Gunakan kode lain karena kode duplikat. Kode yang duplikat " + inputUser.getCode());
			}
		} catch (NoResultException e) {
			return Response.error(e.getMessage());
		}
	}

	@PostMapping("/update-user")
	@Transactional(readOnly = false)
	public Response<User> updateUser(@RequestBody Map data) {
		User inputUser = new User();
		inputUser.setId(Integer.parseInt(data.get("id").toString()));
		inputUser.setCode((String) data.get("code"));
		inputUser.setName((String) data.get("name"));
		inputUser.setAge(Integer.parseInt(data.get("age").toString()));
		inputUser.setRole((String) data.get("role"));
		inputUser.setEmail((String) data.get("email"));
		inputUser.setUsername((String) data.get("username"));
		inputUser.setPassword((String) data.get("password"));
		User checkUser = userDAO.getByCode(inputUser.getCode());
		try{
			if(checkUser != null && checkUser.getId() == inputUser.getId()) {
				userDAO.update(inputUser);
				return Response.ok();
			}
			else {
				return Response.error(AppConstant.STATUS_TEXT_ERROR + " : Gunakan kode lain karena kode duplikat. Kode yang duplikat " + inputUser.getCode());
			}
		} catch (NoResultException e) {
			return Response.error(e.getMessage());
		}
	}

	@PostMapping("/delete-user")
	@Transactional(readOnly = false)
	public Response<User> deleteUser(@RequestBody Map data) {
		User inputUser = new User();
		inputUser.setId(Integer.parseInt(data.get("id").toString()));
		inputUser.setCode((String) data.get("code"));
		inputUser.setName((String) data.get("name"));
		inputUser.setAge(Integer.parseInt(data.get("age").toString()));
		inputUser.setRole((String) data.get("role"));
		inputUser.setEmail((String) data.get("email"));
		inputUser.setUsername((String) data.get("username"));
		inputUser.setPassword((String) data.get("password"));
		try{
			userDAO.delete(inputUser);
			return Response.ok();
		} catch (NoResultException e) {
			return Response.error(e.getMessage());
		}
	}
}
