package com.next.classquestion.web;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
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

import com.next.classquestion.service.IClassquestionService;
import com.next.classquestion.vo.ClassquestionSearchVO;
import com.next.classquestion.vo.ClassquestionVO;
import com.next.common.valid.ModifyType;
import com.next.common.valid.RegistType;
import com.next.common.vo.PagingVO;
import com.next.common.vo.ResultMessageVO;
import com.next.login.vo.UserVO;

@Controller
@RequestMapping("/classquestion")
public class ClassquestionController {

	// 클래스 문의 컨트롤러
	// 작성자 : 김지원
	// 10월 5일 오후 7시 3분 첫 작성

	@Inject
	IClassquestionService ClassquestionService;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping(value = "/classquestionDeleteMessge.edu")
	public String classquestionDeleteMessage(ModelMap model,
			@ModelAttribute("classquestionVO") ClassquestionVO classquestionVO) throws Exception {
		ClassquestionService.deleteQuestion(classquestionVO);
		ResultMessageVO resultMessageVO = new ResultMessageVO();
		resultMessageVO.setResult(true).setTitle("삭제 성공").setMessage("해당 문의사항이 삭제되었습니다.")
				.setUrl("/classquestion/classquestionList.edu").setUrlTitle("목록으로");
		model.addAttribute("resultMessageVO", resultMessageVO);
		return "common/message";
	}

	@RequestMapping(value = "/classquestionInsert.edu")
	public void classquestionInsert(@ModelAttribute("classquestionVO") ClassquestionVO classquestionVO, ModelMap model)
			throws Exception {
	}

	@RequestMapping(value = "/classanswerInsert.edu")
	public void classanswerInsert(@ModelAttribute("classquestionVO") ClassquestionVO classquestionVO, ModelMap model) {
	}

	@RequestMapping(value = "/classanswerInsertMessage.edu", method = { RequestMethod.POST, RequestMethod.PUT })
	public String classanswerInsertMessage(
			@ModelAttribute("classquestionVO") @Validated({ Default.class,
					RegistType.class }) ClassquestionVO classquestionVO,
			BindingResult errors, HttpSession session, ModelMap model) throws Exception {
		UserVO user = (UserVO) session.getAttribute("USER_INFO");
		classquestionVO.setCaMemId(user.getUserId());
		ClassquestionService.insertAnswer(classquestionVO);
		ResultMessageVO resultMessageVO = new ResultMessageVO();
		resultMessageVO.setResult(true).setTitle("등록 성공").setMessage("해당 답변이 등록되었습니다.")
				.setUrl("/classquestion/classquestionList.edu").setUrlTitle("목록으로");
		model.addAttribute("resultMessageVO", resultMessageVO);
		return "common/message";
	}

	@RequestMapping(value = "/classquestionInsertMessage.edu", method = { RequestMethod.POST, RequestMethod.PUT })
	public String classquestionInsertMessage(
			@ModelAttribute("classquestionVO") @Validated({ Default.class,
					RegistType.class }) ClassquestionVO classquestionVO,
			BindingResult errors, HttpSession session, ModelMap model) throws Exception {
		if (errors.hasErrors()) {
			return "classquestion/classquestionInsert";
		}
		UserVO user = (UserVO) session.getAttribute("USER_INFO");
		classquestionVO.setCqMemId(user.getUserId());
		ClassquestionService.insertQuestion(classquestionVO);
		ResultMessageVO resultMessageVO = new ResultMessageVO();
		resultMessageVO.setResult(true).setTitle("등록 성공").setMessage("해당 문의사항이 등록되었습니다.")
				.setUrl("/classquestion/classquestionList.edu").setUrlTitle("목록으로");
		model.addAttribute("resultMessageVO", resultMessageVO);
		return "common/message";
	}

	@RequestMapping(value = "/classquestionList.edu")
	public String classquestionList(@ModelAttribute("classquestionVO") ClassquestionVO classquestionVO,
			HttpSession session, ModelMap model) throws Exception {
		UserVO user = (UserVO) session.getAttribute("USER_INFO");
		if (!user.getUserRole().equals("TEACHER")) {
			classquestionVO.setCqMemId(user.getUserId());
		}
		List<ClassquestionVO> classquestionVOList = ClassquestionService.getClassquestionVOList(classquestionVO);
		model.addAttribute("classquestionVOList", classquestionVOList);
		logger.debug("classquestionVOList={}", classquestionVOList);
		return "classquestion/classquestionList";
	}

	@RequestMapping(value = "/classquestionUpdate.edu")
	public String classquestionUpdate(int cqNo, ModelMap model) throws Exception {
		logger.debug("cqNo={}", cqNo);
		try {
			ClassquestionVO classquestionVO = ClassquestionService.getClassquestion(cqNo);
			model.addAttribute("classquestionVO", classquestionVO);
			return "classquestion/classquestionUpdate";
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			ResultMessageVO resultMessageVO = new ResultMessageVO();
			resultMessageVO.setResult(false).setTitle("수정 실패").setMessage("해당 문의사항 수정에 실패하였습니다.")
					.setUrl("/classquestion/classquestionList.edu").setUrlTitle("목록으로");
			model.addAttribute("resultMessageVO", resultMessageVO);
			return "common/message";
		}
	}

	@RequestMapping(value = "/classquestionUpdateMessage.edu")
	public String classquestionUpdateMessage(
			@ModelAttribute("classquestionVO") @Validated({ Default.class,
					ModifyType.class }) ClassquestionVO classquestionVO,
			BindingResult errors, ModelMap model) throws Exception {
		logger.debug("classquestion={}", classquestionVO);
		if (errors.hasErrors()) {
			logger.debug("errors={}", errors);
			return "classquestion/classquestionUpdateMessage";
		}
		ResultMessageVO resultMessageVO = new ResultMessageVO();
		try {
			ClassquestionService.updateQuestion(classquestionVO);
			return "redirect:/classquestion/classquestionView.edu?cqNo=" + classquestionVO.getCqNo();
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			resultMessageVO.setResult(false).setTitle("수정 실패").setMessage("해당 문의사항 수정에 실패하였습니다.")
					.setUrl("/classquestion/classquestionList.edu").setUrlTitle("목록으로");
			model.addAttribute("resultMessageVO", resultMessageVO);
			return "common/message";
		}
	}

	@RequestMapping(value = "/classquestionView.edu")
	public String classquestionView(@RequestParam(value = "cqNo") int cqNo, ModelMap model) throws Exception {
		logger.debug("cqNo={}", cqNo);
		try {
			ClassquestionVO classquestionVO = ClassquestionService.getClassquestion(cqNo);
			model.addAttribute("classquestionVO", classquestionVO);
			return "classquestion/classquestionView";
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			ResultMessageVO resultMessageVO = new ResultMessageVO();
			resultMessageVO.setResult(false).setTitle("조회 실패").setMessage("해당 문의사항 조회에 실패하였습니다.")
					.setUrl("/classquestion/classquestionList.edu").setUrlTitle("목록으로");
			model.addAttribute("resultMessageVO", resultMessageVO);
			return "common/message";
		}
	}
}
