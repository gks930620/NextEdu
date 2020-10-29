
package com.next.member.web;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.next.common.vo.ResultMessageVO;

import com.next.login.vo.UserVO;
import com.next.member.service.IMemberService;
import com.next.member.vo.MemberVO;

@Controller
@RequestMapping("/mypage")
public class MypageController {
	
	@Inject
	IMemberService memberService;
	
	@RequestMapping(value = "/mypageUpdate.edu")
	public String mypageUpdate(ModelMap model, HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ResultMessageVO resultMessageVO = new ResultMessageVO();
		try {
			HttpSession session = req.getSession();
			UserVO user = (UserVO) session.getAttribute("USER_INFO");
			String memId = user.getUserId();
			MemberVO memberVO = memberService.getMemberVO(memId);
			req.setAttribute("memberVO", memberVO);
			return "mypage/mypageUpdate";
		} catch (Exception ex) {
			resultMessageVO.setResult(true)
					  .setTitle("회원정보 표시 실패")
					  .setMessage("해당 회원이 존재하지 않습니다")
					  .setUrl("redirect:/")
					  .setUrlTitle("목록으로");
			req.setAttribute("resultMessageVO", resultMessageVO);
			return "common/message";
		}
	}

	@RequestMapping(value = "/mypageUpdateMessage.edu")
	public String mypageUpdateMessage(ModelMap modelMap,MemberVO memberVO) throws Exception {
		ResultMessageVO resultMessageVO = new ResultMessageVO();
		memberService.updateMemberVO(memberVO);
		resultMessageVO.setResult(true)
						 .setTitle("수정완료")
						 .setMessage("회원정보 수정을 완료했습니다")
						 .setUrl("/mypage/mypage.edu")
						 .setUrlTitle("목록으로");
		modelMap.addAttribute("resultMessageVO",resultMessageVO);
		return "mypage/mypageUpdateMessage";
	}
	
	@RequestMapping(value = "/mypageDeleteMessage.edu")
	public String mypageDeleteMessage(ModelMap modelMap, MemberVO memberVO,  HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ResultMessageVO resultMessageVO = new ResultMessageVO();
		memberService.deleteMemberVO(memberVO);
		HttpSession session = req.getSession();
		
		session.setAttribute("USER_INFO", null);
			resultMessageVO.setResult(true)
							 .setTitle("탈퇴완료")
							 .setMessage("회원탈퇴을 완료했습니다")
							 .setUrl("/")
							 .setUrlTitle("목록으로");
		modelMap.addAttribute("resultMessageVO",resultMessageVO);
		return "common/message";
	}
	
	
	
}