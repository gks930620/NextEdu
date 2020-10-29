package com.next.classhomeworks.web;

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
import com.next.classhomeworks.service.IClasshomeworksService;
import com.next.classhomeworks.vo.ClasshomeworksVO;
import com.next.classhomeworksanswer.service.IClasshomeworksanswerService;
import com.next.common.util.StudyAttachUtils;
import com.next.common.valid.ModifyType;
import com.next.common.valid.RegistType;
import com.next.common.vo.PagingVO;
import com.next.common.vo.ResultMessageVO;
import com.next.login.vo.UserVO;

@Controller
@RequestMapping("/classhomeworks")
public class ClasshomeworksController {
	
	/*
	 클래스 과제게시판(교사가 등록한) 컨트롤러
	 작성자 김아름 
	*/
	
	@Inject
	IClasshomeworksService classhomeworksService;
	
	@Inject
	IClasshomeworksanswerService classhomeworksanswerService;
	
	@Inject
	private StudyAttachUtils attachUtils;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	// 과제 리스트
	@RequestMapping(value = "classhomeworksList.edu")
	public String classhomeworksList(ModelMap model, HttpSession session, PagingVO pagingVO) throws Exception {
		logger.debug("pagingVO={}",pagingVO);
		
		// 교사 화면
		List<ClasshomeworksVO> classhomeworksVOList = classhomeworksService.getClasshomeworksVOList(pagingVO);
		model.addAttribute("classhomeworksVOList", classhomeworksVOList);
		logger.debug("classhomeworksVOList={}",classhomeworksVOList);
		
		// 학생 화면
		
		//파라미터로 받은 curPage는 pagingVO에 curPage입니다.  
		UserVO userVO = (UserVO)session.getAttribute("USER_INFO");  //UserVO를 session에서 새로 받으면 그 userVO는 curPage가 1입니다.
		userVO.setCurPage(pagingVO.getCurPage());		//UserVO의 curPage를 파라미터로 받은 curPage로 바꿔줍니다. 끝 
		
		List<ClasshomeworksVO> classhomeworksVOStudentList = classhomeworksService.getClasshomeworksVOStudentList(userVO);
		model.addAttribute("classhomeworksVOStudentList", classhomeworksVOStudentList);
		logger.debug("classhomeworksVOStudentList={}",classhomeworksVOStudentList);
		
		return "classhomeworks/classhomeworksList";
	}
	
	// 과제 상세보기 화면(교사가 등록한)
	@RequestMapping(value = "classhomeworksView.edu")
	public String classhomeworksView(int chNo, ModelMap model, HttpSession session) throws Exception {
		logger.debug("chNo={}", chNo);
		
		// classhomeworksanswer에서 update 한 후 해당 과제 뷰로 돌아오기 위해 chNo 정보 가져가려고 세션에 담아준 것
		session.setAttribute("chNo", chNo);
		
		try {
			ClasshomeworksVO classhomeworksVO = classhomeworksService.getClasshomeworksVO(chNo);
			model.addAttribute("classhomeworksVO", classhomeworksVO);
			
			return "classhomeworks/classhomeworksView";
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			
			ResultMessageVO resultMessageVO = new ResultMessageVO();
			
			resultMessageVO.setResult(false)
						   .setTitle("조회 실패")
						   .setMessage("해당 과제가 존재하지 않습니다.")
						   .setUrl("javascript:history.back(-1)")
						   .setUrlTitle("뒤로가기");
			
			model.addAttribute("resultMessageVO", resultMessageVO);
			
			return "common/message";
		}
		
		
	}
	
	@RequestMapping(value = "classhomeworksInsert.edu")
	public void classhomeworksInsert(@ModelAttribute("classhomeworksVO") ClasshomeworksVO classhomeworksVO
								   , @ModelAttribute("userVO") UserVO userVO
								   , HttpSession session
								   , ModelMap model) throws Exception {
		
		userVO = (UserVO)session.getAttribute("USER_INFO");
		String memId = userVO.getUserId(); 
		String memName = userVO.getUserName();
		classhomeworksVO.setChMemId(memId);
		classhomeworksVO.setChMemName(memName);
		
	}
	
	@RequestMapping(value = "classhomeworksInsertMessage.edu"
			  	  , method = {RequestMethod.POST, RequestMethod.PUT})
	public String classhomeworksInsertMessage(@ModelAttribute("classhomeworksVO") @Validated({Default.class, RegistType.class}) ClasshomeworksVO classhomeworksVO
											, BindingResult errors
											, @RequestParam(name = "chFiles", required = false) MultipartFile[] chFiles
											, ModelMap model) throws Exception {
		
		logger.debug("classhomeworksVO={}", classhomeworksVO);
		
		if (errors.hasErrors()) {
			return "classhomeworks/classhomeworksInsert";
		}
		
		ResultMessageVO resultMessageVO = new ResultMessageVO();
		
		if (classhomeworksVO != null) {
			List<AttachVO> attachList = attachUtils.getAttachListByMultiparts(chFiles, "CLASSHOMEWORKS", "classhomeworks");
			classhomeworksVO.setAttaches(attachList);
		}
		
		try {
			classhomeworksService.insertClasshomeworksVO(classhomeworksVO);
			
			resultMessageVO.setResult(true)
						   .setTitle("글 등록 성공")
						   .setMessage("게시물이 등록되었습니다.")
						   .setUrl("/classhomeworks/classhomeworksList.edu")
						   .setUrlTitle("목록");
			
			model.addAttribute("resultMessageVO",resultMessageVO);
			
//			return "redirect:/classhomeworks/classhomeworksList.edu";
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			
			resultMessageVO.setResult(false)
						   .setTitle("조회 실패")
						   .setMessage("해당 글번호가 이미 존재합니다.")
						   .setUrl("javascript:history.back(-1)")
						   .setUrlTitle("뒤로가기");
			
			model.addAttribute("resultMessageVO",resultMessageVO);
		}
		return "common/message";
	}
	
	// 과제 수정 화면(교사가 등록한)
	@RequestMapping(value = "classhomeworksUpdate.edu")
	public String classhomeworksUpdate(int chNo, ModelMap model) throws Exception {
		logger.debug("chNo={}", chNo);
		
		ResultMessageVO resultMessageVO = new ResultMessageVO();
		try {
			ClasshomeworksVO classhomeworksVO = classhomeworksService.getClasshomeworksVO(chNo);
			
			model.addAttribute("classhomeworksVO", classhomeworksVO);
			return "classhomeworks/classhomeworksUpdate";
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
	
	@RequestMapping(value = "/classhomeworksUpdateMessage.edu")
	public String classhomeworksUpdateMessage(@ModelAttribute("classhomeworksVO") @Validated({Default.class, ModifyType.class}) ClasshomeworksVO classhomeworksVO
											, BindingResult errors
											, @RequestParam(name = "chFiles", required = false) MultipartFile[] chFiles
											, int[] delAtchNos 
											, ModelMap model) throws Exception {
		
		logger.debug("classhomeworksVO={}", classhomeworksVO);
		logger.debug("delAtchNos={}", delAtchNos);

		ResultMessageVO resultMessageVO = new ResultMessageVO();
		
		if (errors.hasErrors()) {
			return "classhomeworks/classhomeworksUpdate";
		}
		
		if (chFiles != null) {
			List<AttachVO> attachList = attachUtils.getAttachListByMultiparts(chFiles, "CLASSHOMEWORKS", "classhomeworks");
			classhomeworksVO.setAttaches(attachList);
		}
		
		try {
			classhomeworksService.updateClasshomeworksVO(classhomeworksVO);
			return "redirect:/classhomeworks/classhomeworksView.edu?chNo=" + classhomeworksVO.getChNo();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			
			resultMessageVO.setResult(false)
						   .setTitle("수정 실패")
						   .setMessage("해당 글번호가 존재하지 않습니다.")
						   .setUrl("javascript:history.back(-1)")
						   .setUrlTitle("뒤로가기");
			
		}
		model.addAttribute("resultMessageVO", resultMessageVO);
		return "common/message";
	}
	
	@RequestMapping(value = "classhomeworksDelete.edu")
	public String classhomeworksDeleteMessage(ClasshomeworksVO classhomeworksVO, ModelMap model) throws Exception {
		logger.debug("classhomeworksVO={}", classhomeworksVO);
		
		ResultMessageVO resultMessageVO = new ResultMessageVO();
		try {
			classhomeworksService.deleteClasshomeworksVO(classhomeworksVO);
			
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
}
