package com.next.classhomeworks.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.next.attach.dao.IAttachDao;
import com.next.attach.vo.AttachVO;
import com.next.classhomeworks.dao.IClasshomeworksDao;
import com.next.classhomeworks.vo.ClasshomeworksVO;
import com.next.common.vo.PagingVO;
import com.next.login.vo.UserVO;

@Service
public class ClasshomeworksServiceImpl implements IClasshomeworksService {

	@Inject
	private IClasshomeworksDao classhomeworksDao;
	
	@Inject
	private IAttachDao attachDao;
	
	// 교사 화면
	@Override
	public List<ClasshomeworksVO> getClasshomeworksVOList(PagingVO pagingVO) {
		int cnt = classhomeworksDao.getClasshomeworksVOCount(pagingVO);
		pagingVO.setTotalRowCount(cnt);
		pagingVO.pageSetting();
		
		List<ClasshomeworksVO> classhomeworksVOList = classhomeworksDao.getClasshomeworksVOList(pagingVO);
		
		return classhomeworksVOList;
	}

	// 학생 화면
	@Override
	public List<ClasshomeworksVO> getClasshomeworksVOStudentList(UserVO userVO) {
		int cnt = classhomeworksDao.getClasshomeworksVOCount(userVO);
		userVO.setTotalRowCount(cnt);
		userVO.pageSetting();
		
		List<ClasshomeworksVO> classhomeworksVOStudentList = classhomeworksDao.getClasshomeworksVOStudentList(userVO);
		
		return classhomeworksVOStudentList;
	}
	
	@Override
	public ClasshomeworksVO getClasshomeworksVO(int chNo) throws Exception {
		
		ClasshomeworksVO classhomeworksVO = classhomeworksDao.getClasshomeworksVO(chNo);
		
		if (classhomeworksVO == null) {
			throw new Exception("["+chNo+"] 조회 실패");
		}
		
		return classhomeworksVO;
	}

	@Override
	public void insertClasshomeworksVO(ClasshomeworksVO classhomeworksVO) {
		classhomeworksDao.insertClasshomeworksVO(classhomeworksVO);
		
		// 글 등록할 때 attach파일 Insert하는 구문
		List<AttachVO> attachList = classhomeworksVO.getAttaches();
		if (attachList != null) {
			for(AttachVO attachVO : attachList) {
				attachVO.setAtchParentNo(classhomeworksVO.getChNo());
				attachDao.insertAttach(attachVO);
			}
		}
	}
	

	@Override
	public void updateClasshomeworksVO(ClasshomeworksVO classhomeworksVO) throws Exception {
		ClasshomeworksVO classhomeworksVO2 = classhomeworksDao.getClasshomeworksVO(classhomeworksVO.getChNo());
		
		if (classhomeworksVO2 == null) {
			throw new Exception("["+ classhomeworksVO.getChNo()+"] 조회 실패");
		}
		
		int cnt = classhomeworksDao.updateClasshomeworksVO(classhomeworksVO);
		
		if (cnt < 1) {
			throw new Exception("["+ classhomeworksVO.getChNo()+"] 수정 실패");
		}
		// 수정화면 안에 attach파일 추가 
		List<AttachVO> attachList = classhomeworksVO.getAttaches();
		if (attachList != null) {
			for(AttachVO attachVO : attachList) {
				attachVO.setAtchParentNo(classhomeworksVO.getChNo());
				attachDao.insertAttach(attachVO);
			}
		}
		// 수정화면 안에 attach파일 삭제
		int[] deleteAtchNos = classhomeworksVO.getDelAtchNos();
		if (deleteAtchNos != null) {
			for(int i = 0; i<deleteAtchNos.length; i++) {
				attachDao.deleteAttaches(deleteAtchNos);
			}
		}
	}

	@Override
	public void deleteClasshomeworksVO(ClasshomeworksVO classhomeworksVO) throws Exception {
		ClasshomeworksVO classhomeworksVO2 = classhomeworksDao.getClasshomeworksVO(classhomeworksVO.getChNo());
		
		if (classhomeworksVO2 == null) {
			throw new Exception("["+ classhomeworksVO.getChNo() +"] 조회 실패");
		}
		
		int cnt = classhomeworksDao.deleteClasshomeworksVO(classhomeworksVO);
		
		if (cnt < 1) {
			throw new Exception("["+ classhomeworksVO.getChNo()+"] 수정 실패");
		}
	}


}
