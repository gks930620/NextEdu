package com.next.sitenotice.web;


import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.validation.groups.Default;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.next.common.valid.ModifyType;
import com.next.common.valid.RegistType;
import com.next.common.vo.ResultMessageVO;
import com.next.sitenotice.service.ISitenoticeService;
import com.next.sitenotice.vo.SitenoticeVO;
import com.next.sitequestion.vo.SitequestionSearchVO;

@Controller
@RequestMapping("/sitenotice")
public class SitenoticeController {
	
	/*
	 사이트 공지사항 컨트롤러
	 작성자 김아름 
	 */
	
	@Inject
	ISitenoticeService sitenoticeService;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	// 사이트 공지사항 리스트
	@RequestMapping(value = "/sitenoticeList.edu")
	public String sitenoticeList(ModelMap model, SitequestionSearchVO sitequestionSearchVO) throws Exception {
		logger.debug("sitequestionSearchVO={}",sitequestionSearchVO);
		
		// 전체 리스트
		List<SitenoticeVO> sitenoticeVOList = sitenoticeService.getSitenoticeVOList(sitequestionSearchVO);
		model.addAttribute("sitenoticeVOList", sitenoticeVOList);
		logger.debug("sitenoticeVOList={}",sitenoticeVOList);
		
		// 중요공지 리스트
		List<SitenoticeVO> sitenoticeVOTopList = sitenoticeService.getSitenoticeVOTopList(sitequestionSearchVO);
		model.addAttribute("sitenoticeVOTopList", sitenoticeVOTopList);
		logger.debug("sitenoticeVOTopList={}",sitenoticeVOTopList);
		
		return "sitenotice/sitenoticeList";
	}
	
	// 사이트 공지사항 상세보기 화면
	@RequestMapping(value = "/sitenoticeView.edu")
	public String sitenoticeView(int snNo, ModelMap model) throws Exception {
		
		logger.debug("snNo={}",snNo);
		
		try {
			SitenoticeVO sitenoticeVO = sitenoticeService.getSitenoticeVO(snNo);
			
			model.addAttribute("sitenoticeVO", sitenoticeVO);
			
			return "sitenotice/sitenoticeView";
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			
			ResultMessageVO resultMessageVO = new ResultMessageVO();
			
			resultMessageVO.setResult(false)
						   .setTitle("조회 실패")
						   .setMessage("해당 글이 존재하지 않습니다.")
						   .setUrl("/sitenotice/sitenoticeList.edu")
						   .setUrlTitle("목록");
			
			model.addAttribute("resultMessageVO", resultMessageVO);
			
			return "common/message";
		}
	}
	
	// 사이트 공지사항 새글쓰기
	@RequestMapping(value = "/sitenoticeInsert.edu")
	public void sitenoticeInsert(@ModelAttribute("sitenoticeVO") SitenoticeVO sitenoticeVO
								 , ModelMap model) throws Exception {
		
	}
	
	// 사이트 공지사항 새글쓰기	완료
	@RequestMapping(value = "/sitenoticeInsertMessage.edu"
				  , method = {RequestMethod.POST, RequestMethod.PUT})
	public String sitenoticeInsertMessage(@ModelAttribute("sitenoticeVO") @Validated({Default.class, RegistType.class}) SitenoticeVO sitenoticeVO
										, BindingResult errors
										, ModelMap model) throws Exception {
		
		logger.debug("sitenoticeVO={}", sitenoticeVO);
		
		ResultMessageVO resultMessageVO = new ResultMessageVO();
		
		if (errors.hasErrors()) {
			return "sitenotice/sitenoticeInsert";
		}
		
		try {
			sitenoticeService.insertSitenoticeVO(sitenoticeVO);
			
			resultMessageVO.setResult(true)
						   .setTitle("공지 등록 성공")
						   .setMessage("새 공지사항이 성공적으로 등록되었습니다.")
						   .setUrl("/sitenotice/sitenoticeList.edu")
						   .setUrlTitle("목록");
			
			model.addAttribute("resultMessageVO",resultMessageVO);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			
			resultMessageVO.setResult(false)
						   .setTitle("조회 실패")
						   .setMessage("해당 글번호가 이미 존재합니다.")
						   .setUrl("javascript:history.back(-1)")
						   .setUrlTitle("뒤로가기");

			model.addAttribute("resultMessageVO", resultMessageVO);

		}
		return "common/message";
	}
	
	// 사이트 공지사항 수정
	@RequestMapping(value = "/sitenoticeUpdate.edu")
	public String sitenoticeUpdate(int snNo, ModelMap model) throws Exception {
		
		logger.debug("snNo={}", snNo);
		
		try {
			SitenoticeVO sitenoticeVO = sitenoticeService.getSitenoticeVO(snNo);
			model.addAttribute("sitenoticeVO", sitenoticeVO);
			
			return "sitenotice/sitenoticeUpdate";
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			
			ResultMessageVO resultMessageVO = new ResultMessageVO();
			
			resultMessageVO.setResult(false)
						   .setTitle("조회 실패")
						   .setMessage("해당 글이 존재하지 않습니다.")
						   .setUrl("/sitenotice/sitenoticeList.edu")
						   .setUrlTitle("목록");

			model.addAttribute("resultMessageVO", resultMessageVO);

			return "common/message";
		}
	}
	
	// 사이트 공지사항 수정	완료
	@RequestMapping(value = "/sitenoticeUpdateMessage.edu")
	public String sitenoticeUpdateMessage(@Validated({Default.class, ModifyType.class}) SitenoticeVO sitenoticeVO
										, BindingResult errors
										, ModelMap model) throws Exception {
		
		logger.debug("sitenoticeVO={}", sitenoticeVO);
		
		if (errors.hasErrors()) {
			return "sitenotice/sitenoticeUpdate";
		}
		
		try {
			sitenoticeService.updateSitenoticeVO(sitenoticeVO);
			
			return "redirect:/sitenotice/sitenoticeView.edu?snNo=" + sitenoticeVO.getSnNo();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			
			ResultMessageVO resultMessageVO = new ResultMessageVO();
			
			resultMessageVO.setResult(false)
						   .setTitle("수정 실패")
						   .setMessage("글번호를 찾을 수 없습니다.")
						   .setUrl("javascript:history.back(-1)")
						   .setUrlTitle("뒤로가기");

			model.addAttribute("resultMessageVO", resultMessageVO);

			return "common/message";
		}
	}
	
	// 사이트 공지사항 삭제
	@RequestMapping(value = "/sitenoticeDelete.edu")
	public String sitenoticeDeleteMessage(SitenoticeVO sitenoticeVO, ModelMap model) throws Exception {
		
		logger.debug("sitenoticeVO={}", sitenoticeVO);
		
		ResultMessageVO resultMessageVO = new ResultMessageVO();
		try {
			sitenoticeService.deleteSitenoticeVO(sitenoticeVO);
			
			resultMessageVO.setResult(true)
						   .setTitle("글 삭제 성공")
						   .setMessage("게시물이 삭제되었습니다.")
						   .setUrl("/sitenotice/sitenoticeList.edu")
						   .setUrlTitle("목록");
			
			model.addAttribute("resultMessageVO", resultMessageVO);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			
			resultMessageVO.setResult(false)
						   .setTitle("삭제 실패")
						   .setMessage("글번호를 찾을 수 없습니다.")
						   .setUrl("javascript:history.back(-1)")
						   .setUrlTitle("뒤로가기");

		}
		model.addAttribute("resultMessageVO", resultMessageVO);
		return "common/message";
	}

	// Excel다운로드 
	@RequestMapping(value = "/ExcelPoi.edu")
	public void ExcelPoi(SitequestionSearchVO sitequestionSearchVO,
			              @RequestParam(required = false, defaultValue = "download") String filename, 
						   HttpServletResponse response,
						   Model model) throws Exception {
		
		HSSFWorkbook objWorkbook = new HSSFWorkbook(); // 엑셀을 만든다
		HSSFSheet objSheet = null; // 시트 생성
		HSSFRow objRow = null; // 행 생성
		HSSFCell objCell = null; // 셀 생성
		 
		/* 폰트 설정 */
		HSSFFont font = objWorkbook.createFont();
		font.setFontHeightInPoints((short) 14); // 글자 크기 설정
		font.setBoldweight((short) font.BOLDWEIGHT_BOLD); // 글자 굻게 하기
		font.setFontName("맑은고딕"); // 글씨체 설정
		
		/* 제목 스타일에 폰트 적용, 정렬 */
		HSSFCellStyle styleHd = objWorkbook.createCellStyle(); // 제목 스타일
		styleHd.setFont(font); // 
		// 가운데 정렬 2가지
		styleHd.setAlignment(HSSFCellStyle.ALIGN_CENTER); 
		styleHd.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	
		/* 워크시트 생성 */
		objSheet = objWorkbook.createSheet("시트1"); // 워크시트 생성
		
		List<SitenoticeVO> list = sitenoticeService.getSitenoticeVOList(sitequestionSearchVO);
		
		objRow = objSheet.createRow(0); // 1행 생성, 1행은 제목을 적어준다
		objRow.setHeight((short) 0x150); // 행 높이 지정
		
		objCell = objRow.createCell(0);
		objCell.setCellValue("글번호");
		objCell.setCellStyle(styleHd);
		
		objCell = objRow.createCell(1);
		objCell.setCellValue("제목");
		objCell.setCellStyle(styleHd);
		
		objCell = objRow.createCell(2);
		objCell.setCellValue("등록일");
		objCell.setCellStyle(styleHd);
		
		int index = 1;
		for(SitenoticeVO sitenoticeVO : list) {
			objRow = objSheet.createRow(index); // 2행 생성, 2행부터는 데이터 삽입
			objRow.setHeight((short) 0x150); // 행 높이 지정
			
			objCell = objRow.createCell(0);
			objCell.setCellValue(sitenoticeVO.getSnNo());
			objCell.setCellStyle(styleHd);
			
			objCell = objRow.createCell(1);
			objCell.setCellValue((String)sitenoticeVO.getSnTitle());
			objCell.setCellStyle(styleHd);
			
			objCell = objRow.createCell(2);
			objCell.setCellValue(sitenoticeVO.getSnRegDate());
			objCell.setCellStyle(styleHd);
			
			index++;
		}
		for (int i = 0; i < list.size(); i++) {
			objSheet.autoSizeColumn(i);	// 사이즈를 자동으로 설정
		}
		
		response.setContentType("Application/Msexcel");
		response.setHeader("Content-Disposition", "ATTachment; Filename=" + URLEncoder.encode(filename, "UTF-8") + ".xls");
		
		OutputStream fileOut = response.getOutputStream();
		objWorkbook.write(fileOut);
		fileOut.close();
		
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}
}
