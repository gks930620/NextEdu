package com.next.classhome.web;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.next.login.vo.UserVO;
import com.next.member.service.IMemberService;
import com.next.member.vo.MemberVO;

@Controller
@RequestMapping("/classhome")
public class ClasshomeController {
	@Inject
	IMemberService memberService;
	@RequestMapping(value = "/classhome.edu")
	public String classhomeworksList(HttpServletRequest request, HttpSession session,ModelMap model) throws Exception {
		UserVO user = (UserVO) session.getAttribute("USER_INFO");
    	MemberVO memberVO= memberService.getMemberVO(user.getUserId());
    	String roomId=memberVO.getMemScid()+memberVO.getMemGrade()+memberVO.getMemClass();
    	model.addAttribute("roomId",roomId);
		return "classhome/classhome";
	}
	
}
