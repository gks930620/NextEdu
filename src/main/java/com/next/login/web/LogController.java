package com.next.login.web;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.next.common.util.CookieUtils;
import com.next.common.vo.ResultMessageVO;
import com.next.login.service.ILoginService;
import com.next.login.service.LoginServiceImpl;
import com.next.login.vo.UserVO;

@Controller
@RequestMapping("/login")
public class LogController {
	
	@Autowired
	private ILoginService loginService;
	//SLF4J  == 로거
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	
	@RequestMapping(path = "/login.edu",method = RequestMethod.GET)
	public void logInGet() throws Exception {

	}
	
	@RequestMapping(path = "/login.edu",method = RequestMethod.POST)
	public String LogInPost(UserVO vo, HttpServletResponse resp, @RequestParam(name = "rememberMe",required = false) String rememberMe, HttpSession session, Model model) {
//		UserVO vo = new UserVO();
//		vo.setUserId(req.getParameter("userId"));
//		vo.setUserPass(req.getParameter("userPass")); <-- Spring에선 VO를 파라미터로 넣으면 자동으로 브라우저 파라미터를 매핑해서 일치하는 프라퍼티에 값을 할당해줌
		//spring에선 session이나 브라우저에 대한 객체를 파라미터에 넣어주면 자동으로 해당 브라우저의 값을 받아서 할당해줌
		
		//SLF4J 여러 값을 콤마로 구분해서 처리 가능, {}을 사용해서도 처리가능 ex)) logger.debug("UserVO={}, remember={}", vo, rememberMe);
		logger.debug("UserVO=",vo,", remember=",rememberMe);
		try {
			UserVO userVO = loginService.loginCheck(vo);
			System.out.println(userVO.getUserId()+"cccccccccccccccccccccccccccccccccccccccccccccccccccccccccc");
			
			if(rememberMe!=null){
				if(rememberMe.equals("Y")){
					Cookie cookie = CookieUtils.createCookie("SAVE_ID",vo.getUserId());
					resp.addCookie(cookie);
				}
			}else{
				Cookie cookie = CookieUtils.createCookie("SAVE_ID","","/",0);
				/* maxAge에 0을 주면 삭제 */
			    resp.addCookie(cookie);
			}
//			System.out.print("세션에 정보저장 :" +userVO);
			logger.debug("세션에 정보 저장 = {}",userVO);
			//현재 사용자 정보를 세션에 저장
			session.setAttribute("USER_INFO", userVO);
			
			return "redirect:/";
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			ResultMessageVO resultMessageVO = new ResultMessageVO();
			resultMessageVO.setResult(false).setTitle("로그인 실패").setMessage("회원이 존재하지 않거나ㅣ 비밀번호가 틀립니다.");
			model.addAttribute("resultMessageVO", resultMessageVO);
			return "common/message";
		}
	}
	
	@RequestMapping("logout.edu")
	public String logOut(HttpSession session) {
		UserVO user = (UserVO) session.getAttribute("USER_INFO");
		loginService.logout(user);
		logger.debug("로그아웃={}",user);
		//현재 세션을 전부 무효화 invalidate 
		session.invalidate();
		return "redirect:/";
	}
}
