package priv.dengjl.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import priv.dengjl.util.CookieUtils;
import priv.dengjl.util.CookieeConstant;
import priv.dengjl.util.JwtInfo;
import priv.dengjl.util.JwtTokenUtil;

@Controller
@RequestMapping("/my")
public class MyController {
	
	private static final Logger logger = LoggerFactory.getLogger(MyController.class);
	
	
	@RequestMapping("/index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		return mv;
	}
	
	@RequestMapping("/login")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		return mv;
	}
	
	@RequestMapping("/doLogin")
	public void doLogin(HttpServletRequest request, HttpServletResponse response, String name, String password) {
		
		if (logger.isWarnEnabled()) {
			logger.warn("用户名：{}", name);
			logger.warn("密码：{}", password);
		}
		
		JwtInfo jwtInfo = new JwtInfo(name + password);
		String token = JwtTokenUtil.generateToken(jwtInfo, 30);
		CookieUtils.setCookie(request, response, CookieeConstant.LOGIN_KEY, token);
		
		try {
            response.setContentType("application/json");
            response.getWriter().write("{\"flag\":\"true\"}");
        } catch (IOException e) {
            e.printStackTrace();
        }        
	}
	
	@RequestMapping("/doLogout")
	public void doLogout(HttpServletRequest request, HttpServletResponse response, String name, String password) {
		
		if (logger.isWarnEnabled()) {
			logger.warn("注销：{}", "try");
		}
	
		CookieUtils.deleteCookie(request, response, CookieeConstant.LOGIN_KEY);       
		
		if (logger.isWarnEnabled()) {
			logger.warn("注销：{}", "comfirm");
		}
	}

}