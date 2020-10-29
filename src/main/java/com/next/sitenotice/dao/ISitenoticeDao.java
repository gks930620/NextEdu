package com.next.sitenotice.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.next.sitenotice.vo.SitenoticeVO;
import com.next.sitequestion.vo.SitequestionSearchVO;

@Mapper
public interface ISitenoticeDao {
	
	public int getSitenoticeVOCount(SitequestionSearchVO sitequestionSearchVO);
	public List<SitenoticeVO> getSitenoticeVOList(SitequestionSearchVO sitequestionSearchVO);	// site notice 리스트
	public List<SitenoticeVO> getSitenoticeVOTopList(SitequestionSearchVO sitequestionSearchVO);	// site notice 리스트
	public SitenoticeVO getSitenoticeVO(int snNo);
	public int insertSitenoticeVO(SitenoticeVO sitenoticeVO);
	public int updateSitenoticeVO(SitenoticeVO sitenoticeVO);
	public int deleteSitenoticeVO(SitenoticeVO sitenoticeVO);
}
