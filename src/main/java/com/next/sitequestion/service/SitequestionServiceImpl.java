package com.next.sitequestion.service;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import com.next.attach.dao.IAttachDao;
import com.next.attach.vo.AttachVO;
import com.next.common.util.MybatisSqlSessionFactory;
import com.next.common.vo.PagingVO;
import com.next.login.vo.UserVO;
import com.next.sitequestion.dao.ISitequestionDao;
import com.next.sitequestion.vo.SitequestionVO;


@Service
public class SitequestionServiceImpl implements ISitequestionService {

	SqlSessionFactory factory = MybatisSqlSessionFactory.getSqlSessionFactory();
	
	@Inject
	private ISitequestionDao sitequestionDao;
	
	@Inject
	private IAttachDao attachDao;
	
	/* 사이트 1:1 문의게시판 전체 리스트 */
	@Override
	public List<SitequestionVO> getSitequestionVOList(PagingVO pagingVO) {
		int cnt = sitequestionDao.getSitequestionVOCount(pagingVO);
		pagingVO.setTotalRowCount(cnt);
		pagingVO.pageSetting();
		List<SitequestionVO> sitequestionVOList = sitequestionDao.getSitequestionVOList(pagingVO);
		return sitequestionVOList;
	}

	/* 사이트 1:1 문의게시판 작성자별 리스트 */
	@Override
	public List<SitequestionVO> getSitequestionVOEachList(UserVO userVO) {
		int cnt = sitequestionDao.getSitequestionVOCountEach(userVO);
		userVO.setTotalRowCount(cnt);
		userVO.pageSetting();
		List<SitequestionVO> sitequesionVOEachList = sitequestionDao.getSitequestionVOEachList(userVO);
		return sitequesionVOEachList;
	}

	/* 사이트 1:1 문의게시판 상세보기 */
	@Override
	public SitequestionVO getSitequestionVO(int sqNo) throws Exception {
		SitequestionVO sitequestionVO = sitequestionDao.getSitequestionVO(sqNo);
		if (sitequestionVO == null) {
			throw new Exception("[" + sqNo + "] 조회 실패");
		}
		return sitequestionVO;
	}

	@Override
	public void insertSitequestionVO(SitequestionVO sitequestionVO) {
		sitequestionDao.insertSitequestionVO(sitequestionVO);
		// 글 등록할 때 attach파일 Insert하는 구문
		List<AttachVO> attachList = sitequestionVO.getAttaches();
		if (attachList != null) {
			for(AttachVO attachVO : attachList) {
				attachVO.setAtchParentNo(sitequestionVO.getSqNo());
				attachDao.insertAttach(attachVO);
			}
		}
	}

	@Override
	public void updateSitequestionVO(SitequestionVO sitequestionVO) throws Exception {
		SitequestionVO sitequestionVO2 = sitequestionDao.getSitequestionVO(sitequestionVO.getSqNo());
		if (sitequestionVO2 == null) {
			throw new Exception("[" + sitequestionVO.getSqNo() + "] 조회 실패");
		}
		int cnt = sitequestionDao.updateSitequestionVO(sitequestionVO);
		if (cnt < 1) {
			throw new Exception("[" + sitequestionVO.getSqNo() + "] 수정 실패");
		}
		// 수정화면 안에 attach파일 추가 
		List<AttachVO> attachList = sitequestionVO.getAttaches();
		if (attachList != null) {
			for(AttachVO attachVO : attachList) {
				attachVO.setAtchParentNo(sitequestionVO.getSqNo());
				attachDao.insertAttach(attachVO);
			}
		}
		// 수정화면 안에 attach파일 삭제
		int[] deleteAtchNos = sitequestionVO.getDelAtchNos();
		if (deleteAtchNos != null) {
			for(int i = 0; i < deleteAtchNos.length; i++) {
				attachDao.deleteAttaches(deleteAtchNos);
			}
		}
	}

	@Override
	public void deleteSitequestionVO(SitequestionVO sitequestionVO) throws Exception {
		SitequestionVO sitequestionVO2 = sitequestionDao.getSitequestionVO(sitequestionVO.getSqNo());
		if (sitequestionVO2 == null) {
			throw new Exception("[" + sitequestionVO.getSqNo() + "] 조회 실패");
		}
		int cnt = sitequestionDao.deleteSitequestionVO(sitequestionVO);
		if (cnt < 1) {
			throw new Exception("[" + sitequestionVO.getSqNo() + "] 삭제 실패");
		}
	}

}
