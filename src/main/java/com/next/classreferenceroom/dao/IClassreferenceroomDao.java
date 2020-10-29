
package com.next.classreferenceroom.dao;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.next.classreferenceroom.vo.ClassreferenceroomSearchVO;
import com.next.classreferenceroom.vo.ClassreferenceroomVO;
import com.next.common.vo.PagingVO;

@Mapper
public interface IClassreferenceroomDao {

	public ClassreferenceroomVO getClassreferenceroom(int crNo);
	public int getClassreferenceVOCount(PagingVO pagingVO);
	public int insertBoard(ClassreferenceroomVO classreferenceroomVO);
	public int updateBoard(ClassreferenceroomVO classreferenceroomVO);
	public int deleteBoard(ClassreferenceroomVO classreferenceroomVO);
	public List<ClassreferenceroomVO> getClassreferenceroomVOList(PagingVO pagingVO);
}
