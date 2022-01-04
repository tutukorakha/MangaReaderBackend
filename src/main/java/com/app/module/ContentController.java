package com.app.module;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.app.AppConstant;
//import com.app.base.controller.BaseModuleController;
//import com.app.entity.bean.VendorType;
//import com.app.entity.bean.dao.VendorTypeDAO;
//import com.app.lazy.entity.bean.VendorTypeBean;
//import com.app.lazy.entity.bean.dao.VendorTypeBeanDAO;
//import com.app.model.Page;
//import com.app.model.Response;
//import com.app.model.RouteRequest;
//import com.app.model.TableRequest;

import com.app.entity.bean.Content;
import com.app.entity.dao.ContentDAO;

@RestController
@Transactional(readOnly = true)
@RequestMapping("/api/content")
public class ContentController {

}
