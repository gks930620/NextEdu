package com.next.classmember.service;

import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import com.next.classmember.dao.IClassmemberDao;
import com.next.classmember.vo.ClassmemberSearchVO;
import com.next.classmember.vo.ClassmemberVO;

@Service
public class ClassmemberServiceImpl implements IClassmemberService {

	@Inject
	IClassmemberDao classmemberDao;

	@Override
	public void updateMemberVO(ClassmemberVO classmemberVO) throws Exception {
		ClassmemberVO memberVO = classmemberDao.getMember(classmemberVO.getMemId());
		if (memberVO == null) {
			throw new Exception("[" + classmemberVO.getMemId() + "] 조회에 실패했습니다.");
		}
		int count = classmemberDao.updateMember(classmemberVO);
		if (count < 1) {
			throw new Exception("[" + classmemberVO.getMemId() + "] 수정에 실패했습니다.");
		}
	}

	@Override
	public ClassmemberVO getMemberVO(String memId) throws Exception {
		ClassmemberVO classmemberVO = classmemberDao.getMember(memId);
		if (classmemberVO == null) {
			throw new Exception("[" + memId + "] 조회에 실패했습니다.");
		}
		return classmemberVO;
	}

	@Override
	public List<ClassmemberVO> getMemberVOList(ClassmemberSearchVO classmemberSearchVO) {
		int count = classmemberDao.getMemberCount(classmemberSearchVO);
		classmemberSearchVO.setTotalRowCount(count);
		classmemberSearchVO.pageSetting();
		List<ClassmemberVO> classmemberList = classmemberDao.getMemberList(classmemberSearchVO);
		return classmemberList;
	}

	@Override
	public int idCheck(ClassmemberVO classmemberVO) throws Exception {
		int count = classmemberDao.idCheck(classmemberVO);
		return 0;
	}
}
