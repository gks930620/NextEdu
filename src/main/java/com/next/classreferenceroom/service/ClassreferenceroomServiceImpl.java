
package com.next.classreferenceroom.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.next.attach.dao.IAttachDao;
import com.next.attach.vo.AttachVO;
import com.next.classreferenceroom.dao.IClassreferenceroomDao;
import com.next.classreferenceroom.vo.ClassreferenceroomVO;
import com.next.common.vo.PagingVO;

@Service
public class ClassreferenceroomServiceImpl implements IClassreferenceroomService {

	@Inject
	private IClassreferenceroomDao classreferenceroomDao;
	@Inject
	private IAttachDao attachDao;

	@Override
	public List<ClassreferenceroomVO> getClassreferenceroomVOList(PagingVO pagingVO) {
		int cnt = classreferenceroomDao.getClassreferenceVOCount(pagingVO);
		pagingVO.setTotalRowCount(cnt);
		pagingVO.pageSetting();
		List<ClassreferenceroomVO> classreferenceVOList = classreferenceroomDao.getClassreferenceroomVOList(pagingVO);
		return classreferenceVOList;
	}

	@Override
	public ClassreferenceroomVO getClassreferenceroom(int crNo) {
		ClassreferenceroomVO classreferenceroomVO = classreferenceroomDao.getClassreferenceroom(crNo);
		return classreferenceroomVO;
	}

	@Override
	public void insertBoard(ClassreferenceroomVO classreferenceroomVO) {
		classreferenceroomDao.insertBoard(classreferenceroomVO);
		List<AttachVO> attachVO = classreferenceroomVO.getAttaches();
		if(attachVO != null) {
			for(AttachVO vo : attachVO) {		
				vo.setAtchParentNo(classreferenceroomVO.getCrNo());
				attachDao.insertAttach(vo);
			}
		}
	}

	@Override
	public void updateBoard(ClassreferenceroomVO classreferenceroomVO) {
	classreferenceroomDao.updateBoard(classreferenceroomVO);
	}

	@Override
	public void deleteBoard(ClassreferenceroomVO classreferenceroomVO) {
		classreferenceroomDao.deleteBoard(classreferenceroomVO);
	}

}
