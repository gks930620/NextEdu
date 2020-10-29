package com.next.siteanswer.web;


import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.next.common.vo.ResultMessageVO;
import com.next.siteanswer.service.ISiteanswerService;
import com.next.siteanswer.vo.SiteanswerVO;

@Controller
@RequestMapping("/siteanswer")
public class SiteanswerController {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Inject
	private ISiteanswerService siteanswerService;
	
	/*
	 * @RequestMapping(value = "/siteanswerList.edu", produces =
	 * "application/json;charset=UTF-8") public Map<String, Object>
	 * siteanswerList(SiteanswerSearchVO siteanswerSearchVO) throws Exception {
	 * List<SiteanswerVO> list =
	 * siteanswerService.SiteanswerVOListByParent(siteanswerSearchVO); Map<String,
	 * Object> map = new HashMap<String, Object>(); map.put("result", true);
	 * map.put("data", list); map.put("count", list.size()); return map; }
	 */

	/* insert와 update를 동시에 처리 serviceimpl에서 구현되어있음 */
	@RequestMapping(value = "/siteanswerInsert.edu")
	public String insertSiteanswer(@ModelAttribute("siteanswerVO") @Validated() SiteanswerVO siteanswerVO
								  , BindingResult errors 
								  , ModelMap model) throws Exception {
		logger.debug("siteanswerVO={}", siteanswerVO);
		ResultMessageVO resultMessageVO = new ResultMessageVO();
		if (errors.hasErrors()) {
			return "forward:/sitequestion/sitequestionView.edu?sqNo=" + siteanswerVO.getSaSqNo();
		}
		siteanswerService.insertSiteanswer(siteanswerVO);
		resultMessageVO.setResult(true)
		   .setTitle("저장 완료")
		   .setMessage("답변을 저장하였습니다.")
		   .setUrl("/sitequestion/sitequestionList.edu")
		   .setUrlTitle("목록으로");

		model.addAttribute("resultMessageVO",resultMessageVO);
		return "common/message";
	}
	
	/*
	 * @RequestMapping(value = "/siteanswerUpdate.edu") public String
	 * updateSiteanswer(SiteanswerVO siteanswerVO , HttpSession session , ModelMap
	 * model) throws Exception { // UserVO userVO = (UserVO)
	 * session.getAttribute("USER_INFO");
	 * 
	 * logger.debug("siteanswerVO={}", siteanswerVO); ResultMessageVO
	 * resultMessageVO = new ResultMessageVO();
	 * siteanswerService.insertSiteanswer(siteanswerVO);
	 * resultMessageVO.setResult(true) .setTitle("수정 완료")
	 * .setMessage("답변을 수정하였습니다.") .setUrl("/sitequestion/sitequestionList.edu")
	 * .setUrlTitle("목록으로");
	 * 
	 * model.addAttribute("messageVO",resultMessageVO); return "common/message"; }
	 */
	
	@RequestMapping(value = "/siteanswerDeleteMessage.edu")
	public String deleteSiteanswer(SiteanswerVO siteanswerVO
			                     , HttpSession sesstion
			                     , ModelMap model) throws Exception {
//		UserVO userVO = (UserVO) session.getAttribute("USER_INFO");
		
		logger.debug("siteanswerVO={}", siteanswerVO);
		ResultMessageVO resultMessageVO = new ResultMessageVO();
		siteanswerService.deleteSiteanswer(siteanswerVO);
		resultMessageVO.setResult(true)
		   .setTitle("삭제 완료")
		   .setMessage("답변을 삭제하였습니다.")
		   .setUrl("/sitequestion/sitequestionList.edu")
		   .setUrlTitle("목록으로");

		model.addAttribute("resultMessageVO",resultMessageVO);
		return "common/message"; 
	}
}
