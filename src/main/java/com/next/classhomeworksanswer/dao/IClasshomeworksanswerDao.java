package com.next.classhomeworksanswer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.next.classhomeworksanswer.vo.ClasshomeworksanswerVO;
import com.next.common.vo.PagingVO;

@Mapper
public interface IClasshomeworksanswerDao {
	
	public int getClasshomeworksanswerVOCount(PagingVO pagingVO);
	public List<ClasshomeworksanswerVO> getClasshomeworksanswerVOList(ClasshomeworksanswerVO classhomeworksanswerVO);
	public ClasshomeworksanswerVO getClasshomeworkanswerVOS(@Param("chaChNo") int chaChNo, @Param("userId") String userId);
	public ClasshomeworksanswerVO getClasshomeworkanswerVOT(int chaNo);
	public int insertClasshomeworksanswerVO(ClasshomeworksanswerVO classhomeworksanswerVO);
	public int updateClasshomeworksanswerVO(ClasshomeworksanswerVO classhomeworksanswerVO);
	public int deleteClasshomeworksanswerVO(ClasshomeworksanswerVO classhomeworksanswerVO);

	public int updateScoreClasshomeworksanswerVO(ClasshomeworksanswerVO classhomeworksanswerVO);
}
