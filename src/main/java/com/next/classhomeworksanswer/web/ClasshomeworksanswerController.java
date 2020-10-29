package com.next.classhomeworksanswer.web;

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
import com.next.classhomeworks.vo.ClasshomeworksVO;
import com.next.classhomeworksanswer.service.IClasshomeworksanswerService;
import com.next.classhomeworksanswer.vo.ClasshomeworksanswerVO;
import com.next.common.util.StudyAttachUtils;
import com.next.common.valid.ModifyType;
import com.next.common.valid.RegistType;
import com.next.common.valid.ScoreType;
import com.next.common.vo.ResultMessageVO;
import com.next.login.vo.UserVO;

@Controller
@RequestMapping("/classhomeworksanswer")
public class ClasshomeworksanswerController {
	/*
	 클래스 과제 답변 게시판(학생 답변한) 컨트롤러
	 작성자 김아름 
	*/
	
	// 교사 화면
	
	@Inject
	IClasshomeworksanswerService classhomeworksanswerService;
	
	@Inject
	private StudyAttachUtils attachUtils;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	// 과제 답변 리스트
	@RequestMapping(value = "/classhomeworksanswerList.edu")
	public String classhomeworksanswerList(ClasshomeworksanswerVO classhomeworksanswerVO, ModelMap model, int chaChNo, HttpSession session) throws Exception {
		logger.debug("classhomeworksanswerVO={}",classhomeworksanswerVO);
		logger.debug("chaChNo={}",chaChNo);
		
		session.setAttribute("parentNo", chaChNo);
		
		List<ClasshomeworksanswerVO> classhomeworksanswerVOList = classhomeworksanswerService.getClasshomeworksanswerVOList(classhomeworksanswerVO);
		model.addAttribute("classhomeworksanswerVOList", classhomeworksanswerVOList);
		logger.debug("classhomeworksanswerVOList={}",classhomeworksanswerVOList);
		
		return "classhomeworksanswer/classhomeworksanswerList";
	}

	// 제출한 과제 상세보기 화면(학생이 등록한) 학생이 보는 내 과제 화면 !!
	@RequestMapping(value = "/classhomeworksanswerViewS.edu")
	public String classhomeworksanswerViewS(int chaChNo, String userId, ModelMap model, HttpSession session) throws Exception {
		logger.debug("chaChNo={}",chaChNo);
		
		UserVO userVO = (UserVO)session.getAttribute("USER_INFO");
		userId = userVO.getUserId(); 
		
		ResultMessageVO resultMessageVO = new ResultMessageVO();
		
		try {
			ClasshomeworksanswerVO classhomeworksanswerVO = classhomeworksanswerService.getClasshomeworkanswerVOS(chaChNo, userId);
			model.addAttribute("classhomeworksanswerVO", classhomeworksanswerVO);
			
			return "classhomeworksanswer/classhomeworksanswerView";
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			
			resultMessageVO.setResult(false)
						   .setTitle("조회 실패")
						   .setMessage("과제를 제출하지 않았습니다.")
						   .setUrl("javascript:history.back(-1)")
						   .setUrlTitle("뒤로가기");
			
			model.addAttribute("resultMessageVO", resultMessageVO);
			
			return "common/message";
		}
	}
	
	// 제출한 과제 상세보기 화면(학생이 등록한) 선생님이 보는 학생 과제 화면 !!
	@RequestMapping(value = "/classhomeworksanswerViewT.edu")
	public String classhomeworksanswerViewT(int chaNo, ModelMap model, HttpSession session) throws Exception {
		logger.debug("chaNo={}",chaNo);
		
		ResultMessageVO resultMessageVO = new ResultMessageVO();
		
		try {
			ClasshomeworksanswerVO classhomeworksanswerVO = classhomeworksanswerService.getClasshomeworkanswerVOT(chaNo);
			model.addAttribute("classhomeworksanswerVO", classhomeworksanswerVO);
			
			return "classhomeworksanswer/classhomeworksanswerView";
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			
			resultMessageVO.setResult(false)
						   .setTitle("조회 실패")
						   .setMessage("과제를 제출하지 않았습니다.")
						   .setUrl("javascript:history.back(-1)")
						   .setUrlTitle("뒤로가기");
			
			model.addAttribute("resultMessageVO", resultMessageVO);
			
			return "common/message";
		}
	}
	
	@RequestMapping(value = "/classhomeworksanswerInsert.edu")
	public String classhomeworksanswerInsert(@ModelAttribute("classhomeworksVO") ClasshomeworksVO classhomeworksVO
										   , @ModelAttribute("classhomeworksanswerVO") ClasshomeworksanswerVO classhomeworksanswerVO
										   , @ModelAttribute("userVO") UserVO userVO
										   , HttpSession session
										   , @RequestParam("chNo") int chNo
										   , @RequestParam("chCategory") String chCategory
										   , @RequestParam("chTitle") String chTitle
										   , @RequestParam("chDeadline") String chDeadline
										   , ModelMap model) throws Exception {
		
		
		userVO = (UserVO)session.getAttribute("USER_INFO");
		String memId = userVO.getUserId(); 
		String memName = userVO.getUserName(); 
		classhomeworksanswerVO.setChaMemId(memId);
		classhomeworksanswerVO.setChaMemName(memName);
		
		classhomeworksanswerVO.setChaChNo(chNo);
		classhomeworksanswerVO.setChaCategory(chCategory);
		classhomeworksanswerVO.setChaTitle(chTitle);
		classhomeworksanswerVO.setChaDeadline(chDeadline);
		
		return "classhomeworksanswer/classhomeworksanswerInsert";
	}
	
	@RequestMapping(value = "/classhomeworksanswerInsertMessage.edu"
				  , method = {RequestMethod.POST, RequestMethod.PUT})
	public String classhomeworksanswerInsertMessage(@ModelAttribute("classhomeworksanswerVO") @Validated({Default.class, RegistType.class}) ClasshomeworksanswerVO classhomeworksanswerVO
												  , BindingResult errors
												  , @RequestParam(name = "chaFiles", required = false) MultipartFile[] chaFiles
												  , ModelMap model) throws Exception {
		
		logger.debug("classhomeworksanswerVO={}",classhomeworksanswerVO);
		
		if (errors.hasErrors()) {
			return "classhomeworksanswer/classhomeworksanswerInsert";
		}
		
		ResultMessageVO resultMessageVO = new ResultMessageVO();
		
		if (classhomeworksanswerVO != null) {
			List<AttachVO> attachList = attachUtils.getAttachListByMultiparts(chaFiles, "CLASSHOMEWORKSANSWER", "classhomeworksanswer");
			classhomeworksanswerVO.setAttaches(attachList);
		}
		
		try {
			classhomeworksanswerService.insertClasshomeworksanswerVO(classhomeworksanswerVO);
			
			return "redirect:/classhomeworks/classhomeworksList.edu";
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			
			resultMessageVO.setResult(false)
						   .setTitle("등록 실패")
						   .setMessage("등록에 실패했습니다.")
						   .setUrl("javascript:history.back(-1)")
						   .setUrlTitle("뒤로가기");
			
			model.addAttribute("resultMessageVO", resultMessageVO);
			
			return "common/message";
		}
	}
	
	// 제출한 과제 수정 화면
	@RequestMapping(value = "/classhomeworksanswerUpdate.edu")
	public String classhomeworksanswerUpdate(int chaNo, ModelMap model) throws Exception {
		logger.debug("chaNo={}", chaNo);
		
		ResultMessageVO resultMessageVO = new ResultMessageVO();
		
		try {
			ClasshomeworksanswerVO classhomeworksanswerVO = classhomeworksanswerService.getClasshomeworkanswerVOT(chaNo);
			model.addAttribute("classhomeworksanswerVO",classhomeworksanswerVO);
			
			return "classhomeworksanswer/classhomeworksanswerUpdate";
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			
			resultMessageVO.setResult(false)
						   .setTitle("조회 실패")
						   .setMessage("해당 글번호가 존재하지 않습니다.")
						   .setUrl("javascript:history.back(-1)")
						   .setUrlTitle("뒤로가기");
			
			model.addAttribute("resultMessageVO", resultMessageVO);
			return "common/message";
		}
	}
	
	@RequestMapping(value = "/classhomeworksanswerUpdateMessage.edu")
	public String classhomeworksanswerUpdateMessage(@ModelAttribute("classhomeworksanswerVO") @Validated({Default.class, ModifyType.class}) ClasshomeworksanswerVO classhomeworksanswerVO
												  , BindingResult errors
												  , @RequestParam(name = "chaFiles", required = false) MultipartFile[] chaFiles
												  , int[] delAtchNos 
												  , ModelMap model
												  , HttpSession session) throws Exception {
		logger.debug("classhomeworksanswerVO={}", classhomeworksanswerVO);
		logger.debug("delAtchNos={}", delAtchNos);
		
		// classhomeworksanswerList에서부터 세션에 담아 온 parentNo(chaChNo)를 chaChNo에 넣음
		int chNo = (int) session.getAttribute("parentNo");
		
		if (errors.hasErrors()) {
			return "classhomeworksanswer/classhomeworksanswerUpdate";
		}
		
		if (chaFiles != null) {
			List<AttachVO> attachList = attachUtils.getAttachListByMultiparts(chaFiles, "CLASSHOMEWORKSANSWER", "classhomeworksanswer");
			classhomeworksanswerVO.setAttaches(attachList);
		}
		
		ResultMessageVO resultMessageVO = new ResultMessageVO();
		
		try {
			classhomeworksanswerService.updateClasshomeworksanswerVO(classhomeworksanswerVO);
			
			return "redirect:/classhomeworksanswer/classhomeworksanswerViewS.edu?chaChNo=" + chNo;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			
			resultMessageVO.setResult(false)
						   .setTitle("수정 실패")
						   .setMessage("해당 글번호가 존재하지 않습니다.")
						   .setUrl("javascript:history.back(-1)")
						   .setUrlTitle("뒤로가기");
			
			model.addAttribute("resultMessageVO", resultMessageVO);
			return "common/message";
		}
	}

	@RequestMapping(value = "/classhomeworksanswerDelete.edu")
	public String classhomeworksanswerDeleteMessage(ClasshomeworksanswerVO classhomeworksanswerVO, ModelMap model) throws Exception {
		logger.debug("classhomeworksanswerVO={}", classhomeworksanswerVO);
		
		ResultMessageVO resultMessageVO = new ResultMessageVO();
		
		try {
			classhomeworksanswerService.deleteClasshomeworksanswerVO(classhomeworksanswerVO);
			
			resultMessageVO.setResult(true)
						   .setTitle("글 삭제 성공")
						   .setMessage("게시물이 삭제되었습니다.")
						   .setUrl("/classhomeworks/classhomeworksList.edu")
						   .setUrlTitle("목록");
			
			model.addAttribute("resultMessageVO", resultMessageVO);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			
			resultMessageVO.setResult(false)
						   .setTitle("삭제 실패")
						   .setMessage("글번호를 찾을 수 없습니다.")
						   .setUrl("javascript:history.back(-1)")
						   .setUrlTitle("뒤로가기");

			model.addAttribute("resultMessageVO", resultMessageVO);

		}
		return "common/message";
	}
	
	// 교사가 학생 과제에 점수주기
	@RequestMapping(value = "/classhomeworksScoreUpdate.edu")
	public String classhomeworksanswerScoreUpdate(@ModelAttribute("classhomeworksanswerVO") ClasshomeworksanswerVO classhomeworksanswerVO
												, int chaChNo
												, HttpSession session
												, ModelMap model) throws Exception {
		logger.debug("classhomeworksanswerVO={}", classhomeworksanswerVO);
		
		// classhomeworksanswerList에서부터 세션에 담아 온 parentNo(chaChNo)를 chaChNo에 넣음
		chaChNo = (int) session.getAttribute("parentNo");
		ResultMessageVO resultMessageVO = new ResultMessageVO();
//		System.out.println("부모번호====="+chaChNo);
		try {
			classhomeworksanswerService.updateScoreClasshomeworksanswerVO(classhomeworksanswerVO);
			
			// 세션 삭제해야.. 하나? 다음에 저장하면 덮어씌울텐데
//			session.removeAttribute("parentNo");
			return "redirect:/classhomeworksanswer/classhomeworksanswerList.edu?chaChNo=" + chaChNo;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			
			resultMessageVO.setResult(false)
						   .setTitle("수정 실패")
						   .setMessage("해당 과제가 존재하지 않습니다.")
						   .setUrl("javascript:history.back(-1)")
						   .setUrlTitle("뒤로가기");
			
			model.addAttribute("resultMessageVO", resultMessageVO);
			return "common/message";
		}
	}
}
