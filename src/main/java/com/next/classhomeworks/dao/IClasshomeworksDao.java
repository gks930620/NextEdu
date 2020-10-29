package com.next.classhomeworks.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.next.classhomeworks.vo.ClasshomeworksVO;
import com.next.common.vo.PagingVO;
import com.next.login.vo.UserVO;

@Mapper
public interface IClasshomeworksDao {

	public int getClasshomeworksVOCount(PagingVO pagingVO);
	public List<ClasshomeworksVO> getClasshomeworksVOList(PagingVO pagingVO);	// 과제 리스트
	
	public List<ClasshomeworksVO> getClasshomeworksVOStudentList(UserVO userVO);	// 학생이 보는 과제 리스트(제출여부 포함)
	
	public ClasshomeworksVO getClasshomeworksVO(int chNo);
	public int insertClasshomeworksVO(ClasshomeworksVO classhomeworksVO);
	public int updateClasshomeworksVO(ClasshomeworksVO classhomeworksVO);
	public int deleteClasshomeworksVO(ClasshomeworksVO classhomeworksVO);
	
}
