package com.next.classmember.web;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.next.classmember.service.IClassmemberService;
import com.next.classmember.vo.ClassmemberSearchVO;
import com.next.classmember.vo.ClassmemberVO;

@Controller
@RequestMapping("/classmember")
public class ClassmemberExcelController {
	
	@Inject
	IClassmemberService classmemberService;

	@RequestMapping(value = "/ExcelPoi.edu")
	public void ExcelPoi(ClassmemberSearchVO classmemberSearchVO,  HttpServletResponse response, Model model,
			@RequestParam(required = false, defaultValue = "학생 목록") String fileName) throws Exception {

		HSSFWorkbook objWorkBook = new HSSFWorkbook();
		HSSFSheet objSheet = null;
		HSSFRow objRow = null;
		HSSFCell objCell = null; // 셀 생성

		// 제목 폰트
		HSSFFont font = objWorkBook.createFont();
		font.setFontHeightInPoints((short) 9);
		font.setBoldweight((short) font.BOLDWEIGHT_BOLD);
		font.setFontName("맑은고딕");

		// 제목 스타일에 폰트 적용, 정렬
		HSSFCellStyle styleHd = objWorkBook.createCellStyle(); // 제목 스타일
		styleHd.setFont(font);
		styleHd.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		styleHd.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		objSheet = objWorkBook.createSheet("첫번째 시트"); // 워크시트 생성

		// 1행
		objRow = objSheet.createRow(0);
		objRow.setHeight((short) 0x150);

		objCell = objRow.createCell(0);
		objCell.setCellValue("이름");
		objCell.setCellStyle(styleHd);

		objCell = objRow.createCell(1);
		objCell.setCellValue("ID");
		objCell.setCellStyle(styleHd);
		
		objCell = objRow.createCell(2);
		objCell.setCellValue("학번");
		objCell.setCellStyle(styleHd);
		
		objCell = objRow.createCell(3);
		objCell.setCellValue("HP");
		objCell.setCellStyle(styleHd);
		
		objCell = objRow.createCell(4);
		objCell.setCellValue("주소");
		objCell.setCellStyle(styleHd);
		
		objCell = objRow.createCell(5);
		objCell.setCellValue("상세 주소");
		objCell.setCellStyle(styleHd);
		
		List<ClassmemberVO> list = classmemberService.getMemberVOList(classmemberSearchVO);

		int index = 1;
		for (ClassmemberVO classmemberVO : list) {
			objRow = objSheet.createRow(index);
			objRow.setHeight((short) 0x150);

			objCell = objRow.createCell(0);
			objCell.setCellValue((String) classmemberVO.getMemName());
			objCell.setCellStyle(styleHd);

			objCell = objRow.createCell(1);
			objCell.setCellValue((String) classmemberVO.getMemId());
			objCell.setCellStyle(styleHd);
			
			objCell = objRow.createCell(2);
			objCell.setCellValue((int) classmemberVO.getMemStuno());
			objCell.setCellStyle(styleHd);
			
			objCell = objRow.createCell(3);
			objCell.setCellValue((String) classmemberVO.getMemHp());
			objCell.setCellStyle(styleHd);
			
			objCell = objRow.createCell(4);
			objCell.setCellValue((String) classmemberVO.getMemAdd1());
			objCell.setCellStyle(styleHd);

			objCell = objRow.createCell(5);
			objCell.setCellValue((String) classmemberVO.getMemAdd2());
			objCell.setCellStyle(styleHd);

			
			index++;
		}

		for (int i = 0; i < list.size(); i++) {
			objSheet.autoSizeColumn(i);
		}

		response.setContentType("Application/Msexcel");
		response.setHeader("Content-Disposition",
				"ATTachment; Filename=" + URLEncoder.encode(fileName, "UTF-8") + ".xls");

		OutputStream fileOut = response.getOutputStream();
		objWorkBook.write(fileOut);
		fileOut.close();

		response.getOutputStream().flush();
		response.getOutputStream().close();
	}
}
