package com.next.sitequestion.service;

import java.util.List;

import com.next.common.vo.PagingVO;
import com.next.login.vo.UserVO;
import com.next.sitequestion.vo.SitequestionVO;

public interface ISitequestionService {

	/* 사이트 1:1 문의게시판 전체 리스트 */
	public List<SitequestionVO> getSitequestionVOList(PagingVO pagingVO);
	/* 사이트 1:1 문의게시판 작성자별 리스트 */
	public List<SitequestionVO> getSitequestionVOEachList(UserVO userVO);
	public SitequestionVO getSitequestionVO(int sqNo) throws Exception;
	public void insertSitequestionVO(SitequestionVO sitequestionVO);
	public void updateSitequestionVO(SitequestionVO sitequestionVO) throws Exception;
	public void deleteSitequestionVO(SitequestionVO sitequestionVO) throws Exception;
}
