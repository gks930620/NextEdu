package com.next.classhomeworksanswer.service;

import java.util.List;

import com.next.classhomeworksanswer.vo.ClasshomeworksanswerVO;

public interface IClasshomeworksanswerService {

	public List<ClasshomeworksanswerVO> getClasshomeworksanswerVOList(ClasshomeworksanswerVO classhomeworksanswerVO);
	public ClasshomeworksanswerVO getClasshomeworkanswerVOS(int chaChNo, String userId) throws Exception;
	public ClasshomeworksanswerVO getClasshomeworkanswerVOT(int chaNo) throws Exception;
	public void insertClasshomeworksanswerVO(ClasshomeworksanswerVO classhomeworksanswerVO);
	public void updateClasshomeworksanswerVO(ClasshomeworksanswerVO classhomeworksanswerVO) throws Exception;
	public void deleteClasshomeworksanswerVO(ClasshomeworksanswerVO classhomeworksanswerVO) throws Exception;

	public void updateScoreClasshomeworksanswerVO(ClasshomeworksanswerVO classhomeworksanswerVO) throws Exception;
}
