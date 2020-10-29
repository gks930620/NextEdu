package com.next.sitenotice.service;

import java.util.List;
import com.next.sitenotice.vo.SitenoticeVO;
import com.next.sitequestion.vo.SitequestionSearchVO;

public interface ISitenoticeService {

	public List<SitenoticeVO> getSitenoticeVOList(SitequestionSearchVO sitequestionSearchVO);	// site notice 리스트
	public List<SitenoticeVO> getSitenoticeVOTopList(SitequestionSearchVO sitequestionSearchVO);	// site notice 중요공지 리스트
	public SitenoticeVO getSitenoticeVO(int snNo) throws Exception;
	public void insertSitenoticeVO(SitenoticeVO sitenoticeVO);
	public void updateSitenoticeVO(SitenoticeVO sitenoticeVO) throws Exception;
	public void deleteSitenoticeVO(SitenoticeVO sitenoticeVO) throws Exception;
}
