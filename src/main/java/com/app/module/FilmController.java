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
import com.app.entity.bean.UserNew;
//import com.app.base.controller.BaseModuleController;
import com.app.entity.bean.Film;
import com.app.entity.dao.FilmDAO;
import com.app.entity.dao.UserNewDAO;
//import com.app.lazy.entity.bean.VendorTypeBean;
//import com.app.lazy.entity.bean.dao.VendorTypeBeanDAO;
//import com.app.model.Page;
import com.app.model.Response;
//import com.app.model.RouteRequest;
//import com.app.model.TableRequest;

@RestController
@RequestMapping("/film")
@Transactional(readOnly = true)
public class FilmController {

	@Autowired
	private FilmDAO filmDAO;
		
	@GetMapping("/get-all-films")
	public List<Film> getAllFilms() {
		return filmDAO.getListAll();
	}
	
	@GetMapping("/get-all-films_is_buy_false")
	public List<Film> getFilmsIsBuyFalse() {
		return filmDAO.getListWhereIsBuyFalse();
	}
	
	@PostMapping("/insert-film")
	@Transactional(readOnly = false)
	public Response<Film> insertFilm(@RequestBody Film data) {
		Film inputFilm = new Film();
		inputFilm = data;	
		Film checkFilm = filmDAO.getByCode(data.getCode());
		if(checkFilm == null) {
			inputFilm.setIsBuy(false);
			filmDAO.insert(inputFilm);
			return Response.ok(AppConstant.STATUS_TEXT_SUCCESS, inputFilm);
		} 
		
		else {
			return Response.error(AppConstant.STATUS_TEXT_ERROR, inputFilm);
		}
	}
}
