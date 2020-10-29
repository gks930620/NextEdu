package com.next.sitequestion.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.next.common.vo.PagingVO;
import com.next.login.vo.UserVO;
import com.next.sitequestion.vo.SitequestionVO;

@Mapper
public interface ISitequestionDao {

	/* 사이트 1:1 문의게시판 전체글 갯수 조회*/
	public int getSitequestionVOCount(PagingVO pagingVO);
	/* 사이트 1:1 문의게시판 작성자별 글 갯수 조회*/
	public int getSitequestionVOCountEach(UserVO userVO);
	/* 사이트 1:1 문의게시판 전체 리스트 */
	public List<SitequestionVO> getSitequestionVOList(PagingVO pagingVO);
	/* 사이트 1:1 문의게시판 작성자 별 리스트*/
	public List<SitequestionVO> getSitequestionVOEachList(UserVO userVO); /* */
	
	public SitequestionVO getSitequestionVO(int sqNo);
	public int insertSitequestionVO(SitequestionVO sitequestionVO);
	public int updateSitequestionVO(SitequestionVO sitequestionVO);
	public int updateSitequestionVO2(SitequestionVO sitequestionVO); /* 답변완료 변경부분 */
	public int deleteSitequestionVO(SitequestionVO sitequestionVO);
	
}
