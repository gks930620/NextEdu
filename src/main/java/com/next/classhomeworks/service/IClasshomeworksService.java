package com.next.classhomeworks.service;

import java.util.List;

import com.next.classhomeworks.vo.ClasshomeworksVO;
import com.next.common.vo.PagingVO;
import com.next.login.vo.UserVO;

public interface IClasshomeworksService {
	
	public List<ClasshomeworksVO> getClasshomeworksVOList(PagingVO pagingVO);	// 과제 리스트(교사 화면)
	public List<ClasshomeworksVO> getClasshomeworksVOStudentList(UserVO userVO);	// 과제 리스트(학생 화면)
	public ClasshomeworksVO getClasshomeworksVO(int chNo) throws Exception;
	public void insertClasshomeworksVO(ClasshomeworksVO classhomeworksVO);
	public void updateClasshomeworksVO(ClasshomeworksVO classhomeworksVO) throws Exception;
	public void deleteClasshomeworksVO(ClasshomeworksVO classhomeworksVO) throws Exception;
}
