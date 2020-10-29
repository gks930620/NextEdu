package com.next.classmember.service;

import java.util.List;

import com.next.classmember.vo.ClassmemberSearchVO;
import com.next.classmember.vo.ClassmemberVO;

public interface IClassmemberService { 
	public void updateMemberVO(ClassmemberVO classmemberVO) throws Exception; 
	public ClassmemberVO getMemberVO(String memId) throws Exception;
	int idCheck(ClassmemberVO classmemberVO) throws Exception;
	public List<ClassmemberVO> getMemberVOList(ClassmemberSearchVO classmemberSearchVO);
	//public void checkMemberDelete(String[] checkMemIds);	

}
