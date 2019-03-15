package priv.dengjl.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import priv.dengjl.util.CookieUtils;
import priv.dengjl.util.CookieeConstant;
import priv.dengjl.util.JwtTokenUtil;

public class AuthHandlerInterceptor  implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(AuthHandlerInterceptor.class);
	
	private static final String LOGIN_URL_END = "/my/login";
	
	private static final String DO_LOGIN_URL_END = "/my/doLogin";

	// 前置方法  若返回false，则直接结束
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String requestURI = request.getRequestURI();
		if (logger.isWarnEnabled()) {
			logger.warn("{}, =>当前URL为：{}", "权限拦截器开始，开始校验是否登录", requestURI);
		}
		
		// 登录页面不拦截
		if (requestURI.endsWith(LOGIN_URL_END) || requestURI.endsWith(DO_LOGIN_URL_END) ) {
			return true;
		}
		
		String cookieValue = CookieUtils.getCookieValue(request, CookieeConstant.LOGIN_KEY);
		// 未登录过 | 登陆过，但无效了
		if (StringUtils.isBlank(cookieValue) || !JwtTokenUtil.isValidate(cookieValue)) {
			response.sendRedirect(request.getContextPath() + LOGIN_URL_END);
			return false;
		}
		
		return true;
	}

	// 后置方法
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	// 视图结束后方法
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}