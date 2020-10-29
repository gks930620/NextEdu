package com.next.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginCheckInterceptor extends HandlerInterceptorAdapter {
	final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
	
		HttpSession session = request.getSession(false);
		String XRequested = request.getHeader("X-Requested-With");
		if (session == null) {
			if (XRequested == null) {
				response.sendError(HttpServletResponse.SC_FORBIDDEN); // 403, 접근 금지.
				return false;
			} else {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED); // 401, 인증오류.
				//Ajax를 호출한 스크립트에서 에러났을 때 상태코드가 401로 왔다면 로그인 페이지로 이동한다.   근데 Ajax 스크립트가 어디있었는지 기억이안난다
				
				return false;
			}

		}
		if (session.getAttribute("USER_INFO") == null) {
			if (XRequested == null) {
				response.sendRedirect(request.getContextPath() + "/login/login.edu");
				return false;

			} else {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED); // 401, 인증오류.
				return false;

			}
		}
		return true;
	}

}
