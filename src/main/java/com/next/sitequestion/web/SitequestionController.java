package com.next.sitequestion.web;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.next.attach.vo.AttachVO;
import com.next.common.util.StudyAttachUtils;
import com.next.common.valid.ModifyType;
import com.next.common.valid.RegistType;
import com.next.common.vo.PagingVO;
import com.next.common.vo.ResultMessageVO;
import com.next.login.vo.UserVO;
import com.next.sitequestion.service.ISitequestionService;
import com.next.sitequestion.vo.SitequestionVO;

@Controller
@RequestMapping("/sitequestion")
public class SitequestionController {  
	
	@Inject
	ISitequestionService sitequestionService;
	
	@Inject
	private StudyAttachUtils attachUtils;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	// 사이트 1:1 문의게시판 목록
		@RequestMapping(value = "/sitequestionList.edu")
		public String sitequestionList(PagingVO pagingVO, HttpSession session, ModelMap model) throws Exception {
			List<SitequestionVO> sitequestionVOList = sitequestionService.getSitequestionVOList(pagingVO);
			logger.debug("pagingVO={}",pagingVO);
			logger.debug("==================================================");
			
			/* 사이트 1:1 문의게시판 관리자 전체목록*/
			model.addAttribute("sitequestionVOList", sitequestionVOList);
			logger.debug("sitequestionVOList={}",sitequestionVOList);
			
			/* 사이트 1:1 문의게시판 작성자 별 목록*/
			UserVO userVO = (UserVO) session.getAttribute("USER_INFO");
			userVO.setCurPage(pagingVO.getCurPage());
			
			List<SitequestionVO> sitequestionVOEachList = sitequestionService.getSitequestionVOEachList(userVO);
			model.addAttribute("sitequestionVOEachList", sitequestionVOEachList);
			logger.debug("==================================================");
			model.addAttribute("userVO",userVO);
			logger.debug("==================================================");
			logger.debug("userVO={}", userVO);
			logger.debug("sitequestionVOEachList={}", sitequestionVOEachList);
			return "sitequestion/sitequestionList";
		}
	
	// 사이트 1:1 문의게시판 상세보기
	@RequestMapping(value = "/sitequestionView.edu")
	public String sitequestionView(int sqNo, ModelMap model) throws Exception {
		logger.debug("sqNo={}",sqNo);
		ResultMessageVO resultMessageVO = new ResultMessageVO();
		try {
			SitequestionVO sitequestionVO = sitequestionService.getSitequestionVO(sqNo);
			model.addAttribute("sitequestionVO",sitequestionVO);
			return "sitequestion/sitequestionView";
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			resultMessageVO.setResult(false)
						    .setTitle("조회실패")
						    .setMessage("해당 글이 존재하지 않습니다.")
						    .setUrl("/sitequestion/sitequestionList.edu")
						    .setUrlTitle("목록으로");
			model.addAttribute("resultMessageVO",resultMessageVO);
			return "common/message";
		}
	}
	
	// 사이트 1:1 문의게시판 등록
	@RequestMapping(value = "/sitequestionInsert.edu")
	public String sitequestionInsert(@ModelAttribute("sitequestionVO") SitequestionVO sitequestionVO,ModelMap model) throws Exception {
			
		return "sitequestion/sitequestionInsert";
	}
	
	// 사이트 1:1 문의게시판 등록 완료
	@RequestMapping(value = "/sitequestionInsertMessage.edu")
	public String sitequestionInsertMessage(@Validated({Default.class, RegistType.class}) SitequestionVO sitequestionVO
										  , BindingResult errors
										  , @RequestParam(name = "sqFiles", required = false) MultipartFile[] sqFiles
										  , ModelMap model) throws Exception {
		ResultMessageVO resultMessageVO = new ResultMessageVO();
		logger.debug("sitequestionVO= {}", sitequestionVO);
		if (errors.hasErrors()) {
			return "sitequestion/sitequestionInsert";
		}
		
		if (sqFiles != null) {
			List<AttachVO> attachList = attachUtils.getAttachListByMultiparts(sqFiles, "SITEQUESTION", "sitequestion");
			sitequestionVO.setAttaches(attachList);
		}
		
		try {
			sitequestionService.insertSitequestionVO(sitequestionVO);
			return "redirect:/sitequestion/sitequestionList.edu";
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			resultMessageVO.setResult(false)
				 		    .setTitle("글 등록 실패")
				 		    .setMessage("글 등록에 실패하였습니다")
				 		    .setUrl("/sitequestion/sitequestionList.edu")
				 		    .setUrlTitle("목록으로");
			model.addAttribute("resultMessageVO",resultMessageVO);
			return "common/message";
		}
	}
	
	// 사이트 1:1 문의게시판 수정
	@RequestMapping(value = "/sitequestionUpdate.edu")
	public String sitequestionUpdate(int sqNo, ModelMap model) throws Exception {
		logger.debug("sqNo={}", sqNo);
		try {
			SitequestionVO sitequestionVO = sitequestionService.getSitequestionVO(sqNo);
			model.addAttribute("sitequestionVO",sitequestionVO);
			return "sitequestion/sitequestionUpdate";
		} catch (Exception e){
			logger.error(e.getMessage(), e);
			ResultMessageVO resultMessageVO = new ResultMessageVO();
			resultMessageVO.setResult(false)
					        .setTitle("수정 실패")
					        .setMessage("해당글이 존재하지않습니다.")
					        .setUrl("/sitequestion/sitequestionList.edu")
					        .setUrlTitle("목록으로");
			model.addAttribute("resultMessageVO", resultMessageVO);
			return "common/message";
		}
	}
	
	// 사이트 1:1 문의게시판 수정 완료
	@RequestMapping(value = "/sitequestionUpdateMessage.edu")
	public String sitequestionUpdateMessage(@ModelAttribute("sitequestionVO") @Validated({Default.class, ModifyType.class }) SitequestionVO sitequestionVO
										  , BindingResult errors
										  , @RequestParam(name = "sqFiles", required = false) MultipartFile[] sqFiles
										  , int[] delAtchNos 
										  , ModelMap model) throws Exception {
		logger.debug("sitequestionVO={}", sitequestionVO);
		logger.debug("delAtchNos={}", delAtchNos);
		ResultMessageVO resultMessageVO = new ResultMessageVO();
		if (errors.hasErrors()) {
			return "sitequestion/sitequestionUpdate";
		}
		
		/* 다른 곳에서 사용할 때 (sqFiles, "SITEQUESTION", "sitequestion")
											↑  이 부분 category랑 ↑ path만 바꿔서 사용하시면 될거같습니다. 
											여기는 수업시간에 했던 그대로 했습니다 */
		if (sqFiles != null) {
			List<AttachVO> attachList = attachUtils.getAttachListByMultiparts(sqFiles, "SITEQUESTION", "sitequestion");
			sitequestionVO.setAttaches(attachList);
		}
		try {
			sitequestionService.updateSitequestionVO(sitequestionVO);
			return "redirect:/sitequestion/sitequestionView.edu?sqNo=" + sitequestionVO.getSqNo();
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
			resultMessageVO.setResult(false)
						    .setTitle("글 수정 실패")
						    .setMessage("해당 글을 찾을 수 없다")
						    .setUrl("/sitequestion/sitequestionList.edu")
						    .setUrlTitle("목록으로");
		}
		model.addAttribute("resultMessageVO", resultMessageVO);
		return "common/message";
	}
	
	// 사이트 1:1 문의게시판 삭제
	@RequestMapping(value = "/sitequestionDeleteMessage.edu")
	public String sitequestionDeleteMessage(SitequestionVO sitequestionVO, ModelMap model) throws Exception {
		ResultMessageVO resultMessageVO = new ResultMessageVO();
		logger.debug("sitequestionVO={}", sitequestionVO);
		try {
			sitequestionService.deleteSitequestionVO(sitequestionVO);
			resultMessageVO.setResult(true)
						    .setTitle("삭제 완료")
						    .setMessage("글이 삭제되었습니다")
						    .setUrl("/sitequestion/sitequestionList.edu")
						    .setUrlTitle("목록으로");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			resultMessageVO.setResult(false)
						    .setTitle("삭제 실패")
						    .setMessage("글을 삭제실패하였습니다.")
						    .setUrl("/sitequestion/sitequestionList.edu")
						    .setUrlTitle("목록으로");
		}
		model.addAttribute("resultMessageVO",resultMessageVO);
		return "common/message";
	}
}
