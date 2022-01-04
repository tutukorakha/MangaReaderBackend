package com.app.session;

//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.app.AppConstant;
//import com.app.AppContext;
//import com.app.entity.bean.Token;
//import com.app.entity.bean.User;
//import com.app.entity.bean.UserRole;
//import com.app.entity.bean.dao.UserRoleDAO;
//import com.app.entity.bean.services.TokenService;
//import com.app.lazy.entity.bean.service.TokenBeanService;
//import com.app.model.UserSession;

//@Service
public class CurrentUserSession {

//	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
//
//	@Autowired
//	protected HttpServletRequest httpServletRequest;
//	
//	@Autowired
//	protected TokenService tokenService;
//	
//	@Autowired
//	protected TokenBeanService tokenBeanService;
//
//	@Autowired
//	protected UserRoleDAO userRoleDAO;
//
//	public String getStrToken() {
//		try {
//			return httpServletRequest.getHeader(AppConstant.TOKEN_KEY);
//		} catch (Exception e) {
//			logger.debug("getUser: NULL");
//			return null;
//		}
//
//	}
//
//	public UserSession getUserSession() {
//
//		try {
//
//			String strToken = getStrToken();
//
//			if (strToken == null)
//				return null;
//
//			UserSession userSession = AppContext.getActiveUserSession().get(strToken);
//
//			logger.debug("UserSession return = " + userSession);
//
//			if (userSession == null) {
//				logger.debug("Usersession reread from token table!, strToken = " + strToken);
//				Token token = tokenService.getValidByTokenString(strToken);
//
//				logger.debug(" Usersession reread from token, token =" + token);
//				if (token != null) {
//					User user = token.getUser();
//					userSession = new UserSession();
//					userSession.setUser(user);
//					userSession.setCurrentLangCode(user.getLang().getCode());
//					List<UserRole> userRoleList = userRoleDAO.getListByWhere(" WHERE e.user.id = " + user.getId() + " and e.role.id = " + token.getRole().getId());
//					userSession.setActiveUserRole(userRoleList.get(0));
//					AppContext.getActiveUserSession().put(strToken, userSession);
//
//				}
//
//			}
//
//			logger.debug("return userSession =  " + userSession);
//			return userSession;
//
//		} catch (Exception e) {
//			logger.error("Catch error getUserSession: NULL");
//		}
//
//		return null;
//	}
//	
//	public User getUser() {
//		try {
//			return getUserSession().getUser();
//		} catch (Exception e) {
//			logger.debug("getUser: NULL");
//		}
//		return null;
//	}
//	
//	public Integer getUserId() {
//		try {
//			return getUserSession().getUser().getId();
//		} catch (Exception e) {
//			logger.debug("getUserId: NULL");
//		}
//		return null;
//	}
//
//	public String getLangCode() {
//		return getUserSession() == null ? null : getUserSession().getCurrentLangCode();
//	}
//
//	public UserRole getActiveUserRole() {
//		return getUserSession() == null ? null : getUserSession().getActiveUserRole();
//	}
}
