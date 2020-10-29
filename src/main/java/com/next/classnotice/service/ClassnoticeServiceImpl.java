package com.next.classnotice.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.next.attach.dao.IAttachDao;
import com.next.attach.vo.AttachVO;
import com.next.classnotice.dao.IClassnoticeDao;
import com.next.classnotice.vo.ClassnoticeVO;
import com.next.common.vo.PagingVO;


@Service
public class ClassnoticeServiceImpl implements IClassnoticeService {

	@Inject
	private IClassnoticeDao classnoticeDao;
	@Inject
	private IAttachDao attachDao;

	@Override
	public List<ClassnoticeVO> getClassnoticeVOList(PagingVO pagingVO) {
		int cnt = classnoticeDao.getClassnoticeVOCount(pagingVO);
		pagingVO.setTotalRowCount(cnt);
		pagingVO.pageSetting();
		List<ClassnoticeVO> classnoticeVOList = classnoticeDao.getClassnoticeVOList(pagingVO);
		return classnoticeVOList;
	}

	@Override
	public ClassnoticeVO getClassnotice(int cnNo) {
		ClassnoticeVO classnoticeVO = classnoticeDao.getClassnotice(cnNo);
		return classnoticeVO;
	}

	@Override
	public void insertBoard(ClassnoticeVO classnoticeVO) {
		classnoticeDao.insertBoard(classnoticeVO);
		List<AttachVO> attachVO = classnoticeVO.getAttacheVO();
		
		if(attachVO != null) {
			for(AttachVO vo : attachVO) {		
				vo.setAtchParentNo(classnoticeVO.getCnNo());
				attachDao.insertAttach(vo);
			}
		}
	}

	@Override
	public void updateBoard(ClassnoticeVO classnoticeVO) {
		// TODO : 예외가 있으면 표시를 해놔야한다
		// classnoticeDao.getClassnotice(classnoticeVO.getCnNo());
		classnoticeDao.updateBoard(classnoticeVO);
	}

	@Override
	public void deleteBoard(ClassnoticeVO classnoticeVO) {
		// ClassnoticeVO classnoticevo = classnoticeDao.getClassnotice(classnoticeVO.getCnNo());
		classnoticeDao.deleteBoard(classnoticeVO);
		
		/*
		 * if (classnoticevo == null) { throw new BizNotFoundException("[" +
		 * classnoticeVO.getCnNo() + "]조회 실패"); } if
		 * (!classnoticevo.getCnMemId().equals(classnoticeVO.getCnMemId())) { throw new
		 * BizPasswordNotMatchedException("권한이 없습니다.."); } int cnt =
		 * classnoticeDao.deleteBoard(classnoticeVO); if (cnt < 1) { throw new
		 * BizNotEffectedException("[" + classnoticeVO.getCnNo() + "]삭제 실패"); }
		 */
	}


}
