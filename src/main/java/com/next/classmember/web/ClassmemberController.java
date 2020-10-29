package com.next.classmember.web;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.groups.Default;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.next.classmember.service.IClassmemberService;
import com.next.classmember.vo.ClassmemberSearchVO;
import com.next.classmember.vo.ClassmemberVO;
import com.next.common.valid.ModifyType;
import com.next.common.vo.ResultMessageVO;

@Controller
@RequestMapping("/classmember")
public class ClassmemberController {

// 우리반 학생 목록 컨트롤러
// 작성자 : 김지원
// 10월 7일 오후 6시 11분 첫 작성

	@Inject
	IClassmemberService classmemberService;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping("/classmemberList.edu")
	public String MemberVOList(@ModelAttribute("classmemberSearchVO") ClassmemberSearchVO classmemberSearchVO,
			ModelMap model) throws Exception {
		logger.debug("ClassmemberList 메서드 call");
		List<ClassmemberVO> classmemberVO = classmemberService.getMemberVOList(classmemberSearchVO);
		model.addAttribute("classmemberVO", classmemberVO);
		classmemberSearchVO.pageSetting();
		model.addAttribute("classmemberSearchVO", classmemberSearchVO);
		return "classmember/classmemberList";
	}

	@RequestMapping(path = "/classmemberView.edu", params = "memId")
	public String MemberVO(@RequestParam(value = "memId") String memId, ModelMap model) throws Exception {
		try {
			ClassmemberVO classmemberVO = classmemberService.getMemberVO(memId);
			model.addAttribute("classmemberVO", classmemberVO);
			return "classmember/classmemberView";
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			ResultMessageVO resultmessage = new ResultMessageVO();
			resultmessage.setResult(false).setTitle("회원 조회 실패").setMessage("해당 회원이 존재하지 않습니다.")
					.setUrl("/classmember/classmemberList.edu").setUrlTitle("목록으로");
			model.addAttribute("resultmessage", resultmessage);
			return "common/message";
		}

	}

	@RequestMapping(path = "/classmemberUpdate.edu", params = "memId")
	public String updateMemberVO(@RequestParam(value = "memId") String memId, ModelMap model) throws Exception {
		logger.debug("memId={}", memId);
		try {
			ClassmemberVO classmemberVO = classmemberService.getMemberVO(memId);
			model.addAttribute("classmemberVO", classmemberVO);
			return "classmember/classmemberUpdate";
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			ResultMessageVO resultmessage = new ResultMessageVO();
			resultmessage.setResult(false).setTitle("멤버 조회 실패").setMessage("해당 멤버가 존재하지 않습니다.")
					.setUrl("/classmember/classmemberList.edu").setUrlTitle("목록으로");
			model.addAttribute("resultmessage", resultmessage);
			return "common/message";
		}
	}

	@RequestMapping(path = "/classmemberUpdateMessage.edu", params = "memId", method = RequestMethod.POST)
	public String classmemberUpdateMessage(@ModelAttribute("classmemberVO") @Validated({Default.class, ModifyType.class}) ClassmemberVO classmemberVO
									, BindingResult errors
									, ModelMap model
									, HttpServletRequest req) throws Exception {
		logger.debug("classmemberVO={}", classmemberVO);
	
		if(errors.hasErrors()) {
			//검증 오류가 있으므로 입력화면으로 뷰 이동
			return "/classmemberUpdate";
		}
		ResultMessageVO message = new ResultMessageVO();
		try {
			classmemberService.updateMemberVO(classmemberVO);
			return "redirect:/classmember/classmemberView.edu?memId=" + classmemberVO.getMemId();

		} catch (Exception ex) {
			logger.error(ex.getMessage(),ex);
			message.setResult(false)
					.setTitle("학생 정보 수정 실패")
					.setMessage("정보 수정에 실패했습니다.")
					.setUrl("/classmember/classmemberList.edu");
		}
		model.addAttribute("messageVO", message);
		return "common/message";
	}
	
}
