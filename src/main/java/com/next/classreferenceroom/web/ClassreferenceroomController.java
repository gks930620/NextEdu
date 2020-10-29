package com.next.classreferenceroom.web;

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
import org.springframework.web.multipart.MultipartFile;

import com.next.attach.vo.AttachVO;
import com.next.classreferenceroom.service.IClassreferenceroomService;
import com.next.classreferenceroom.vo.ClassreferenceroomSearchVO;
import com.next.classreferenceroom.vo.ClassreferenceroomVO;
import com.next.common.util.StudyAttachUtils;
import com.next.common.valid.ModifyType;
import com.next.common.valid.RegistType;
import com.next.common.vo.PagingVO;
import com.next.common.vo.ResultMessageVO;
import com.next.login.vo.UserVO;

@Controller
@RequestMapping("/classreferenceroom")
public class ClassreferenceroomController {

//	클래스 자료실 컨트롤러
//	작성자 : 김지원
//	9월 25일 오후 4시 17분 작성

	@Inject
	IClassreferenceroomService classreferenceroomService;
	
	@Inject
	private StudyAttachUtils attachUtils;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	// 완료
	@RequestMapping(value = "/classreferenceroomDeleteMessage.edu")
	public String classreferenceDeleteMessage(ModelMap model, @ModelAttribute("classreferenceroomVO") ClassreferenceroomVO classreferenceroomVO) throws Exception {
		classreferenceroomService.deleteBoard(classreferenceroomVO);
		ResultMessageVO resultMessageVO = new ResultMessageVO();
		resultMessageVO.setResult(true).setTitle("삭제 성공").setMessage("해당 공지사항이 삭제되었습니다.")
				.setUrl("/classreferenceroom/classreferenceroomList.edu").setUrlTitle("목록으로");
		model.addAttribute("resultMessageVO", resultMessageVO);
		return "common/message";
	}

	// 완료
	@RequestMapping(value = "/classreferenceroomInsert.edu")
	public void classreferenceInsert(@ModelAttribute("classreferenceroomVO") ClassreferenceroomVO classreferenceroomVO,
			ModelMap model) throws Exception {
	}

	// 완료
	@RequestMapping(value = "/classreferenceroomInsertMessage.edu", method = { RequestMethod.POST, RequestMethod.PUT })
	public String classreferenceInsertMessage(
			@ModelAttribute("classreferenceroomVO") @Validated({Default.class, RegistType.class}) ClassreferenceroomVO classreferenceroomVO, BindingResult errors, HttpSession session, ModelMap model, @RequestParam(name = "crFiles", required = false) MultipartFile[] crFiles)
			throws Exception {
		if (errors.hasErrors()) {
			return "classreferenceroom/classreferenceroomInsert";
		}
		UserVO user = (UserVO) session.getAttribute("USER_INFO");
		if (crFiles != null) {
			List<AttachVO> attachList = attachUtils.getAttachListByMultiparts(crFiles, "CLASSREFERENCEROOM", "classreferenceroom");
			classreferenceroomVO.setAttaches(attachList);
		}
		classreferenceroomVO.setCrMemId(user.getUserId());
		classreferenceroomService.insertBoard(classreferenceroomVO);
		ResultMessageVO resultmessageVO = new ResultMessageVO();
		resultmessageVO.setResult(true).setTitle("등록 성공").setMessage("해당 공지사항이 등록되었습니다.")
				.setUrl("/classreferenceroom/classreferenceroomList.edu").setUrlTitle("목록으로");
		
		model.addAttribute("resultMessageVO", resultmessageVO);
		return "common/message";
	}

	// 완료
	@RequestMapping(value = "/classreferenceroomList.edu")
	public String classreferenceList(@ModelAttribute("classreferenceroomSearchVO") ClassreferenceroomSearchVO classreferenceroomSearchVO, ModelMap model) throws Exception {
		List<ClassreferenceroomVO> classreferenceroomVOList = classreferenceroomService.getClassreferenceroomVOList(classreferenceroomSearchVO);
		model.addAttribute("classreferenceroomVOList", classreferenceroomVOList);
		logger.debug("classreferenceroomVOList={}", classreferenceroomVOList);
		System.out.println("classreferenceroomVOList" + classreferenceroomVOList);
		return "classreferenceroom/classreferenceroomList";
	}

	@RequestMapping(value = "/classreferenceroomUpdate.edu")
	public String classreferenceUpdate(int crNo, ModelMap model) throws Exception {
		logger.debug("crNo={}", crNo);
		try {
			ClassreferenceroomVO classreferenceroomVO = classreferenceroomService.getClassreferenceroom(crNo);
			model.addAttribute("classreferenceroomVO", classreferenceroomVO);
			return "classreferenceroom/classreferenceroomUpdate";
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			ResultMessageVO resultmessageVO = new ResultMessageVO();
			resultmessageVO.setResult(false).setTitle("수정 실패").setMessage("해당 글이 존재하지 않습니다.")
					.setUrl("/classreferenceroom/classreferenceroomList.edu").setUrlTitle("목록으로");
			model.addAttribute("resultMessageVO", resultmessageVO);
			return "common/message";
		}
	}

	@RequestMapping(value = "/classreferenceroomUpdateMessage.edu")
	public String classreferenceUpdateMessage(
			@ModelAttribute("classreferenceroom") @Validated({ Default.class,
					ModifyType.class }) ClassreferenceroomVO classreferenceroomVO,
			BindingResult errors, ModelMap model, @RequestParam(name = "crFiles", required = false) MultipartFile[] crFiles) throws Exception {
		logger.debug("classreferenceroom={}", classreferenceroomVO);
		if (errors.hasErrors()) {
			logger.debug("errors={}", errors);
			return "classreferenceroom/classreferenceroomUpdate";
		}
		ResultMessageVO resultmessageVO = new ResultMessageVO();
		
		if (crFiles != null) {
			List<AttachVO> attachList = attachUtils.getAttachListByMultiparts(crFiles, "CLASSREFERENCEROOM", "classreferenceroom");
			classreferenceroomVO.setAttaches(attachList);
		}
		
		try {
			classreferenceroomService.updateBoard(classreferenceroomVO);
			return "redirect:/classreferenceroom/classreferenceroomView.edu?crNo=" + classreferenceroomVO.getCrNo();
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			resultmessageVO.setResult(false).setTitle("글 수정 실패").setMessage("해당 글이 존재하지 않습니다.")
					.setUrl("/classreferenceroom/classreferenceroomList.edu").setUrlTitle("목록으로");
			model.addAttribute("resultMessageVO", resultmessageVO);
			return "common/message";
		}
	}

	@RequestMapping(value = "/classreferenceroomView.edu")
	public String classreferenceView(@RequestParam(value = "crNo") int crNo, ModelMap model) throws Exception {
		logger.debug("crNo={}", crNo);
		try {
			ClassreferenceroomVO classreferenceroomVO = classreferenceroomService.getClassreferenceroom(crNo);
			model.addAttribute("classreferenceroomVO", classreferenceroomVO);
			return "classreferenceroom/classreferenceroomView";
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			ResultMessageVO resultmessageVO = new ResultMessageVO();
			resultmessageVO.setResult(false).setTitle("조회 실패").setMessage("해당 글이 존재하지 않습니다.")
					.setUrl("/classreferenceroom/classreferenceroomList.edu").setUrlTitle("목록으로");
			model.addAttribute("resultMessageVO", resultmessageVO);
			return "common/message";
		}
	}
}
