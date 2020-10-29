package com.next.common.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.next.attach.vo.AttachVO;

@Component
public class StudyAttachUtils {
	
	@Value("#{app['file.upload.path']}")
	private String uploadPath;
	
	private String uploadServerPath;
	
	@Inject
	private ServletContext context;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("YYMMDD"); 
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	/** MultipartFile에서 VO 설정 및 업로드 파일 처리 후 리턴, 없는 경우 null */
	public AttachVO getAttachByMultipart(MultipartFile multipart, String category, String path) throws IOException {
		if (!multipart.isEmpty()) {
			// 실제 저장할 파일명 
			// 상품 : 상품코드 기반 으로 이름 
			// 사용자가 올린 파일명 기반 (동일한 이름여부 확인) (승주아빠 선택)
			// 날짜기반으로 (승주아빠 선택)			
			// 랜덤하게 
			//   "java어려워.dmp"  -> 200918_java어려워_.dmp
			  // UUID.randomUUID().toString();
			String originalName = multipart.getOriginalFilename();	
			uploadServerPath = context.getRealPath("/upload");
			
			String fileName = sdf.format(new Date()) + "_" + originalName ;
			AttachVO vo = new AttachVO();
			vo.setAtchOriginalName(originalName);
			vo.setAtchFileSize(multipart.getSize());
			vo.setAtchContentType(multipart.getContentType());
			vo.setAtchFileName(fileName);
			vo.setAtchCategory(category);
			vo.setAtchPath(path);
			vo.setAtchFancySize(StudyFileUtil.fancySize(multipart.getSize()));
			
			String filePath = uploadPath + File.separatorChar + path;
			String fileServerPath = uploadServerPath + File.separatorChar + path;
			
			logger.debug("filePath = {}, fileName = {}", filePath, fileName);
			logger.debug("uploadServerPath={}",uploadServerPath);

			//multipart.transferTo(new File(filePath, fileName));
			FileUtils.copyInputStreamToFile(multipart.getInputStream(), new File(filePath, fileName));
			FileUtils.copyInputStreamToFile(multipart.getInputStream(), new File(fileServerPath, fileName));
			return vo;
		} else {
			return null;
		}
	}

	/** 다중 MultipartFile에서 VO 설정 및 업로드 파일 처리 후 List 리턴 */
	public List<AttachVO> getAttachListByMultiparts(MultipartFile[] multipartFiles, String category, String path)
			throws IOException {
		
		List<AttachVO> atchList = new ArrayList<AttachVO>();
		
		for (int i = 0; i < multipartFiles.length; i++) {
			MultipartFile multipart = multipartFiles[i];
			AttachVO vo = this.getAttachByMultipart(multipart, category, path);
			if (vo != null) {
				atchList.add(vo);
			}
		}
		return atchList;
	}
}
