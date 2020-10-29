package com.next.classnotice.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.next.classnotice.vo.ClassnoticeVO;
import com.next.common.vo.PagingVO;

@Mapper
public interface IClassnoticeDao {
	
	public List<ClassnoticeVO> getClassnoticeVOList(PagingVO pagingVO);
	public int getClassnoticeVOCount(PagingVO pagingVO);
	public ClassnoticeVO getClassnotice(int cnNo);
	public int insertBoard(ClassnoticeVO classnoticeVO);
	public int updateBoard(ClassnoticeVO classnoticeVO);
	public int deleteBoard(ClassnoticeVO classnoticeVO);
	
}
