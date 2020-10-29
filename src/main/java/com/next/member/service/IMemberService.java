
package com.next.member.service;

import java.util.List;

import com.next.member.vo.MemberSearchVO;
import com.next.member.vo.MemberVO;

public interface IMemberService {
	public void insertMemberVO(MemberVO memberVO) throws Exception; 
	public void updateMemberVO(MemberVO memberVO) throws Exception; 
	public void deleteMemberVO(MemberVO memberVO) throws Exception; 
	public MemberVO getMemberVO(String memId) throws Exception;
	public List<MemberVO> getMemberVOList(MemberSearchVO memberSearchVO);
	int idCheck(MemberVO memberVO) throws Exception;
	
	//public void checkMemberDelete(String[] checkMemIds);	

}
