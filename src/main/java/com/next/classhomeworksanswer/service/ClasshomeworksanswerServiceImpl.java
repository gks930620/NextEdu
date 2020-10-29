package com.next.classhomeworksanswer.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.next.attach.dao.IAttachDao;
import com.next.attach.vo.AttachVO;
import com.next.classhomeworksanswer.dao.IClasshomeworksanswerDao;
import com.next.classhomeworksanswer.vo.ClasshomeworksanswerVO;

@Service
public class ClasshomeworksanswerServiceImpl implements IClasshomeworksanswerService {

	@Inject
	private IClasshomeworksanswerDao classhomeworksanswerDao;
	
	@Inject
	private IAttachDao attachDao;
	
	@Override
	public List<ClasshomeworksanswerVO> getClasshomeworksanswerVOList(ClasshomeworksanswerVO classhomeworksanswerVO) {
		int cnt = classhomeworksanswerDao.getClasshomeworksanswerVOCount(classhomeworksanswerVO);
		classhomeworksanswerVO.setTotalRowCount(cnt);
		classhomeworksanswerVO.pageSetting();
		
		List<ClasshomeworksanswerVO> classhomeworksanswerVOList
					= classhomeworksanswerDao.getClasshomeworksanswerVOList(classhomeworksanswerVO);
		
		return classhomeworksanswerVOList;
	}

	@Override
	public ClasshomeworksanswerVO getClasshomeworkanswerVOS(int chaChNo, String userId) throws Exception {
		ClasshomeworksanswerVO classhomeworksanswerVO
					= classhomeworksanswerDao.getClasshomeworkanswerVOS(chaChNo, userId);
		
		if (classhomeworksanswerVO == null) {
			throw new Exception("["+chaChNo+"] 조회 실패");
		}
		return classhomeworksanswerVO;
	}
	
	@Override
	public ClasshomeworksanswerVO getClasshomeworkanswerVOT(int chaNo) throws Exception {
		ClasshomeworksanswerVO classhomeworksanswerVO
					= classhomeworksanswerDao.getClasshomeworkanswerVOT(chaNo);
		
		if (classhomeworksanswerVO == null) {
			throw new Exception("["+chaNo+"] 조회 실패");
		}
		return classhomeworksanswerVO;
	}

	@Override
	public void insertClasshomeworksanswerVO(ClasshomeworksanswerVO classhomeworksanswerVO) {
		classhomeworksanswerDao.insertClasshomeworksanswerVO(classhomeworksanswerVO);
		
		// 글 등록할 때 attach파일 Insert하는 구문
		List<AttachVO> attachList = classhomeworksanswerVO.getAttaches();
		if (attachList != null) {
			for(AttachVO attachVO : attachList) {
				attachVO.setAtchParentNo(classhomeworksanswerVO.getChaNo());
				attachDao.insertAttach(attachVO);
			}
		}
	}

	@Override
	public void updateClasshomeworksanswerVO(ClasshomeworksanswerVO classhomeworksanswerVO) throws Exception {
		ClasshomeworksanswerVO classhomeworksanswerVO2
					= classhomeworksanswerDao.getClasshomeworkanswerVOT(classhomeworksanswerVO.getChaNo());
		
		if (classhomeworksanswerVO2 == null) {
			throw new Exception("["+ classhomeworksanswerVO.getChaNo()+"] 조회 실패");
		}
		
		int cnt = classhomeworksanswerDao.updateClasshomeworksanswerVO(classhomeworksanswerVO);
		
		if (cnt < 1) {
			throw new Exception("["+ classhomeworksanswerVO.getChaNo()+"] 수정 실패");
		}
		// 수정화면 안에 attach파일 추가 
		List<AttachVO> attachList = classhomeworksanswerVO.getAttaches();
		if (attachList != null) {
			for(AttachVO attachVO : attachList) {
				attachVO.setAtchParentNo(classhomeworksanswerVO.getChaNo());
				attachDao.insertAttach(attachVO);
			}
		}
		// 수정화면 안에 attach파일 삭제
		int[] deleteAtchNos = classhomeworksanswerVO.getDelAtchNos();
		if (deleteAtchNos != null) {
			for(int i = 0; i<deleteAtchNos.length; i++) {
				attachDao.deleteAttaches(deleteAtchNos);
			}
		}
	}

	@Override
	public void deleteClasshomeworksanswerVO(ClasshomeworksanswerVO classhomeworksanswerVO) throws Exception {
		ClasshomeworksanswerVO classhomeworksanswerVO2
					= classhomeworksanswerDao.getClasshomeworkanswerVOT(classhomeworksanswerVO.getChaNo());

		if (classhomeworksanswerVO2 == null) {
			throw new Exception("["+ classhomeworksanswerVO.getChaNo() +"] 조회 실패");
		}
		
		int cnt = classhomeworksanswerDao.deleteClasshomeworksanswerVO(classhomeworksanswerVO);
		
		if (cnt < 1) {
			throw new Exception("["+ classhomeworksanswerVO.getChaNo()+"] 수정 실패");
		}
	}

	@Override
	public void updateScoreClasshomeworksanswerVO(ClasshomeworksanswerVO classhomeworksanswerVO) throws Exception {
		ClasshomeworksanswerVO classhomeworksanswerVO2
				= classhomeworksanswerDao.getClasshomeworkanswerVOT(classhomeworksanswerVO.getChaNo());
		
		if (classhomeworksanswerVO2 == null) {
		throw new Exception("["+ classhomeworksanswerVO.getChaNo()+"] 조회 실패");
		}
		
		int cnt = classhomeworksanswerDao.updateScoreClasshomeworksanswerVO(classhomeworksanswerVO);
		
		if (cnt < 1) {
		throw new Exception("["+ classhomeworksanswerVO.getChaNo()+"] 수정 실패");
		}
	}

}
