package com.next.classnotice.service;

import java.util.List;
import com.next.classnotice.vo.ClassnoticeVO;
import com.next.common.vo.PagingVO;

public interface IClassnoticeService {

	public List<ClassnoticeVO> getClassnoticeVOList(PagingVO pagingVO);
	public ClassnoticeVO getClassnotice(int cnNo);
	public void insertBoard(ClassnoticeVO classnoticeVO);
	public void updateBoard(ClassnoticeVO classnoticeVO);
	public void deleteBoard(ClassnoticeVO classnoticeVO);
}
