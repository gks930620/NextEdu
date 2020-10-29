
package com.next.classreferenceroom.service;

import java.util.List;

import com.next.classreferenceroom.vo.ClassreferenceroomVO;
import com.next.common.vo.PagingVO;

public interface IClassreferenceroomService {

	public ClassreferenceroomVO getClassreferenceroom(int crNo);
	public void insertBoard(ClassreferenceroomVO classreferenceroomVO);
	public void updateBoard(ClassreferenceroomVO classreferenceroomVO);
	public void deleteBoard(ClassreferenceroomVO classreferenceroomVO);
	public List<ClassreferenceroomVO> getClassreferenceroomVOList(PagingVO pagingVO);
}
