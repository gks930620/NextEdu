
package com.next.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.next.member.vo.MemberVO;

@Mapper
public interface IMemberDao {

	public MemberVO getMember(String memId);
	public int insertMember(MemberVO member);
	public String getUserRoleByUserId(String memId);
	public int updateMember(MemberVO member);
	public int deleteMember(MemberVO member);
	public int idCheck(MemberVO member);
	public int getTli(MemberVO member);
	public int updateTli(String memId);
	
}
