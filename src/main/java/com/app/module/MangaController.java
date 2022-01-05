package com.app.module;

import java.util.List;
import java.util.Map;

import com.app.entity.bean.User;
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
import com.app.entity.bean.Manga;
import com.app.entity.dao.MangaDAO;
//import com.app.lazy.entity.bean.VendorTypeBean;
//import com.app.lazy.entity.bean.dao.VendorTypeBeanDAO;
//import com.app.model.Page;
import com.app.model.Response;

import javax.persistence.NoResultException;
//import com.app.model.RouteRequest;
//import com.app.model.TableRequest;

@RestController
@RequestMapping("/manga")
@Transactional(readOnly = true)
public class MangaController {

	@Autowired
	private MangaDAO mangaDAO;
		
	@GetMapping("/get-all-manga")
	public List<Manga> getAllManga() {
		return mangaDAO.getListAll();
	}

	@PostMapping("/insert-manga")
	@Transactional(readOnly = false)
	public Response<Manga> insertManga(@RequestBody Map data) {
		Manga inputManga = new Manga();
		inputManga.setCode((String) data.get("code"));
		inputManga.setName((String) data.get("name"));
		inputManga.setSynopsis((String) data.get("synopsis"));
		inputManga.setPathCoverImage((String) data.get("path_cover_image"));
		Manga checkManga = mangaDAO.getByCode(inputManga.getCode());
		try{
			if(checkManga == null) {
				mangaDAO.insert(inputManga);
				return Response.ok();
			}
			else {
				return Response.error(AppConstant.STATUS_TEXT_ERROR + " : Gunakan kode lain karena kode duplikat. Kode yang duplikat " + inputManga.getCode());
			}
		} catch (NoResultException e) {
			return Response.error(e.getMessage());
		}
	}

	@PostMapping("/update-manga")
	@Transactional(readOnly = false)
	public Response<Manga> updateManga(@RequestBody Map data) {
		Manga inputManga = new Manga();
		inputManga.setCode((String) data.get("code"));
		inputManga.setName((String) data.get("name"));
		inputManga.setSynopsis((String) data.get("synopsis"));
		inputManga.setPathCoverImage((String) data.get("path_cover_image"));
		Manga checkManga = mangaDAO.getByCode(inputManga.getCode());
		try{
			if(checkManga != null && checkManga.getId() == inputManga.getId()) {
				mangaDAO.update(inputManga);
				return Response.ok();
			}
			else {
				return Response.error(AppConstant.STATUS_TEXT_ERROR + " : Gunakan kode lain karena kode duplikat. Kode yang duplikat " + inputManga.getCode());
			}
		} catch (NoResultException e) {
			return Response.error(e.getMessage());
		}
	}

	@PostMapping("/delete-manga")
	@Transactional(readOnly = false)
	public Response<Manga> deleteManga(@RequestBody Map data) {
		Manga inputManga = new Manga();
		inputManga.setCode((String) data.get("code"));
		inputManga.setName((String) data.get("name"));
		inputManga.setSynopsis((String) data.get("synopsis"));
		inputManga.setPathCoverImage((String) data.get("path_cover_image"));
		try{
			mangaDAO.delete(inputManga);
			return Response.ok();
		} catch (NoResultException e) {
			return Response.error(e.getMessage());
		}
	}
}
