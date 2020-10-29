package com.next.classmember.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.next.classmember.vo.ClassmemberSearchVO;
import com.next.classmember.vo.ClassmemberVO;
import com.next.common.vo.PagingVO;

@Mapper
public interface IClassmemberDao {

	public ClassmemberVO getMember(String memId);
	public String getUserRoleByUserId(String memId);
	public int updateMember(ClassmemberVO member);
	public int idCheck(ClassmemberVO member);
	public int getMemberCount(PagingVO pagingVO);
	public List<ClassmemberVO> getMemberList(ClassmemberSearchVO classmemberSearchVO);
}
