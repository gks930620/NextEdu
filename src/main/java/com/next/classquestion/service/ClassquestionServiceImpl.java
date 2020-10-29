package com.next.classquestion.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.next.attach.dao.IAttachDao;
import com.next.attach.vo.AttachVO;
import com.next.classquestion.dao.IClassquestionDao;
import com.next.classquestion.vo.ClassquestionSearchVO;
import com.next.classquestion.vo.ClassquestionVO;
import com.next.common.vo.PagingVO;
import com.next.login.vo.UserVO;

@Service
public class ClassquestionServiceImpl implements IClassquestionService {

	@Inject
	private IClassquestionDao classquestionDao;
	
	@Inject
	private IAttachDao attachDao;

	@Override
	public ClassquestionVO getClassquestion(int cqNo) {
		ClassquestionVO classquestionVO = classquestionDao.getClassquestion(cqNo);
		return classquestionVO;
	}

	@Override
	public void insertQuestion(ClassquestionVO classquestionVO) {
		classquestionDao.insertQuestion(classquestionVO);
		List<AttachVO> attachVO = classquestionVO.getAttaches();
		if (attachVO != null) {
			for (AttachVO vo : attachVO) {
				vo.setAtchParentNo(classquestionVO.getCqNo());
				attachDao.insertAttach(vo);
			}
		}
	}
	
	@Override
	public void updateQuestion(ClassquestionVO classquestionVO) {
		classquestionDao.updateQuestion(classquestionVO);		
	}
	
	@Override
	public void deleteQuestion(ClassquestionVO classquestionVO) {
		classquestionDao.deleteQuestion(classquestionVO);
		
	}

	@Override
	public void insertAnswer(ClassquestionVO classquestionVO) {
		classquestionDao.insertAnswer(classquestionVO);
	}
	

	@Override
	public List<ClassquestionVO> getClassquestionVOList(ClassquestionVO classquestionVO) {
		/*
		 * int cnt = classquestionDao.getClassquestionVOCount(classquestionVO);
		 * pagingVO.setTotalRowCount(cnt); pagingVO.pageSetting();
		 */
		
		List<ClassquestionVO> classquestionVOList = classquestionDao.getClassquestionVOList(classquestionVO);
		return classquestionVOList;
	}

}
