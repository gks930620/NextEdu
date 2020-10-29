package com.next.classquestion.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.next.classquestion.vo.ClassquestionSearchVO;
import com.next.classquestion.vo.ClassquestionVO;
import com.next.common.vo.PagingVO;
import com.next.login.vo.UserVO;


@Mapper
public interface IClassquestionDao {
	/**
	 * 문의내용 상세정보 <br>
	 * 글번호로 조회함 
	 * @param cqNo
	 * @return
	 */
	public ClassquestionVO getClassquestion(int cqNo);
	
	/**
	 * 문의내용 등록
	 * @param classquestionVO
	 * @return
	 */
	public int insertQuestion(ClassquestionVO classquestionVO);
	/**
	 * 문의내용 수정 <br>
	 * <b>답변여부가 'N' 인 경우만 처리되어야 함</b>
	 * @param classquestionVO
	 * @return
	 */
	public int updateQuestion(ClassquestionVO classquestionVO);
	
	/**
	 * 문의내용 삭제  <br>
	 * <b>답변여부가 'N' 인 경우만 처리되어야 함</b>
	 * @param classquestionVO
	 * @return
	 */
	public int deleteQuestion(ClassquestionVO classquestionVO);
	
	
	/**
	 * 답변 등록
	 * @param classquestionVO
	 * @return
	 */
	public int insertAnswer(ClassquestionVO classquestionVO);
	
	
	/**
	 * 문의내용 조회 <br> 
	 * <b>학생인 경우 아이디로 조회 <br>
	 * 선생님은 모든 문의 조회</b>  
	 * @param classquestionSearchVO
	 * @return
	 */
	public List<ClassquestionVO> getClassquestionVOList(ClassquestionVO classquestionVO);
	
	public int getClassquestionVOCount(PagingVO pagingVO);
}
