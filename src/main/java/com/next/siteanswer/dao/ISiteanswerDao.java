package com.next.siteanswer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.next.siteanswer.vo.SiteanswerSearchVO;
import com.next.siteanswer.vo.SiteanswerVO;

@Mapper
public interface ISiteanswerDao {

	public int getSiteanswerCountByParent(SiteanswerSearchVO siteanswerSearchVO);
	public List<SiteanswerVO> getSiteanswerVOListByParent(SiteanswerSearchVO siteanswerSearchVO);
	public SiteanswerVO getSiteanswerVOByParent(SiteanswerVO siteanswerVO);
	public SiteanswerVO getSiteanswerVO(int saNo);
	public int insertSiteanswer(SiteanswerVO siteanswerVO);
	public int updateSiteanswer(SiteanswerVO siteanswerVO);
	public int deleteSiteanswer(SiteanswerVO siteanswerVO);

}
