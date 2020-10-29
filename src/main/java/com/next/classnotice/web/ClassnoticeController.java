package com.next.classnotice.web;

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
import com.next.classnotice.service.IClassnoticeService;
import com.next.classnotice.vo.ClassnoticeVO;
import com.next.common.valid.ModifyType;
import com.next.common.valid.RegistType;
import com.next.common.vo.PagingVO;
import com.next.common.vo.ResultMessageVO;
import com.next.login.vo.UserVO;

@Controller
@RequestMapping("/classnotice")
public class ClassnoticeController {

//	클래스 공지사항 컨트롤러
//	작성자 : 김지원
//	9월 24일 오후 4시 17분 작성

	@Inject
	IClassnoticeService classnoticeService;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping(value = "/classnoticeDeleteMessage.edu")
	public String classnoticeDeleteMessage(ModelMap model, ClassnoticeVO classnoticeVO) throws Exception {
		classnoticeService.deleteBoard(classnoticeVO);
		ResultMessageVO resultmessage = new ResultMessageVO();
		resultmessage.setResult(true).setTitle("삭제 성공").setMessage("해당 공지사항이 삭제되었습니다.")
				.setUrl("/classnotice/classnoticeList.edu").setUrlTitle("목록으로");
		model.addAttribute("resultMessageVO", resultmessage);
		return "common/message";
	}

	@RequestMapping(value = "/classnoticeInsert.edu")
	public void classnoticeInsert(@ModelAttribute("classnoticeVO") ClassnoticeVO classnoticeVO, ModelMap model)
			throws Exception {
	}

	@RequestMapping(value = "/classnoticeInsertMessage.edu", method = { RequestMethod.POST, RequestMethod.PUT })
	public String classnoticeInsertMessage(@ModelAttribute("classnoticeVO") @Validated({Default.class, RegistType.class}) ClassnoticeVO classnoticeVO, BindingResult errors, HttpSession session, ModelMap model)
			throws Exception {
		if (errors.hasErrors()) {
			return "classnotice/classrenoticeInsert";
		}
		UserVO user = (UserVO) session.getAttribute("USER_INFO");
		classnoticeVO.setCnMemId(user.getUserId());
		classnoticeService.insertBoard(classnoticeVO);
		ResultMessageVO resultmessage = new ResultMessageVO();
		resultmessage.setResult(true).setTitle("등록 성공").setMessage("해당 공지사항이 등록되었습니다.")
				.setUrl("/classnotice/classnoticeList.edu").setUrlTitle("목록으로");
		model.addAttribute("resultMessageVO", resultmessage);
		return "common/message";
	}

	@RequestMapping(value = "/classnoticeList.edu")
	public String classnoticeList(ModelMap model, HttpSession session, PagingVO pagingVO) throws Exception {
		List<ClassnoticeVO> classnoticeVOList = classnoticeService.getClassnoticeVOList(pagingVO);
		model.addAttribute("classnoticeVOList", classnoticeVOList);
		logger.debug("classnoticeVOList={}", classnoticeVOList);
		System.out.println("classnoticeVOList" + classnoticeVOList);

		return "classnotice/classnoticeList";
	}

	@RequestMapping(value = "/classnoticeUpdate.edu")
	public String classnoticeUpdate(int cnNo, ModelMap model) throws Exception {
		logger.debug("cnNo={}", cnNo);
		try {
			ClassnoticeVO classnoticeVO = classnoticeService.getClassnotice(cnNo);
			model.addAttribute("classnoticeVO", classnoticeVO);
			return "classnotice/classnoticeUpdate";

		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			ResultMessageVO resultmessage = new ResultMessageVO();
			resultmessage.setResult(false).setTitle("수정 실패").setMessage("해당 글이 존재하지 않습니다.")
					.setUrl("/classnotice/classnoticeList.edu").setUrlTitle("목록으로");
			model.addAttribute("resultMessageVO", resultmessage);
			return "common/message";
		}
	}

	@RequestMapping(value = "/classnoticeUpdateMessage.edu")
	public String classnoticeUpdateMessage(
			@ModelAttribute("classnotice") @Validated({ Default.class, ModifyType.class }) ClassnoticeVO classnotice,
			BindingResult errors, ModelMap model) throws Exception {
		logger.debug("classnotice={}", classnotice);
		if (errors.hasErrors()) {
			logger.debug("errors={}", errors);
			return "classnotice/classnoticeUpdate";
		}
		ResultMessageVO resultmessage = new ResultMessageVO();
		try {
			classnoticeService.updateBoard(classnotice);
			return "redirect:/classnotice/classnoticeView.edu?cnNo=" + classnotice.getCnNo();
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			resultmessage.setResult(false).setTitle("글 수정 실패").setMessage("해당 글이 존재하지 않습니다.")
					.setUrl("/classnotice/classnoticeList.edu").setUrlTitle("목록으로");
			model.addAttribute("resultMessageVO", resultmessage);
			return "common/message";
		}
	}

	@RequestMapping(value = "/classnoticeView.edu")
	public String classnoticeView(@RequestParam(value = "cnNo") int cnNo, ModelMap model) throws Exception {
		logger.debug("cnNo={}", cnNo);
		try {
			ClassnoticeVO classnoticeVO = classnoticeService.getClassnotice(cnNo);
			model.addAttribute("classnoticeVO", classnoticeVO);
			return "classnotice/classnoticeView";
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			ResultMessageVO resultmessage = new ResultMessageVO();
			resultmessage.setResult(false).setTitle("조회 실패").setMessage("해당 글이 존재하지 않습니다.")
					.setUrl("/classnotice/classnoticeList.edu").setUrlTitle("목록으로");
			model.addAttribute("resultMessageVO", resultmessage);
			return "common/message";
		}
	}
}
