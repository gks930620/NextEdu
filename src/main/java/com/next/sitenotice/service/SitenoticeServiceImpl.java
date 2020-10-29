package com.next.sitenotice.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.next.sitenotice.dao.ISitenoticeDao;
import com.next.sitenotice.vo.SitenoticeVO;
import com.next.sitequestion.vo.SitequestionSearchVO;

@Service
public class SitenoticeServiceImpl implements ISitenoticeService {
	
	@Inject
	private ISitenoticeDao sitenoticeDao;

	// site notice 리스트
	@Override
	public List<SitenoticeVO> getSitenoticeVOList(SitequestionSearchVO sitequestionSearchVO) {
		int cnt = sitenoticeDao.getSitenoticeVOCount(sitequestionSearchVO);
		sitequestionSearchVO.setTotalRowCount(cnt);
		sitequestionSearchVO.pageSetting();
		
		List<SitenoticeVO> sitenoticeVOList = sitenoticeDao.getSitenoticeVOList(sitequestionSearchVO);
		return sitenoticeVOList;
	}

	@Override
	public List<SitenoticeVO> getSitenoticeVOTopList(SitequestionSearchVO sitequestionSearchVO) {
		List<SitenoticeVO> sitenoticeVOTopList = sitenoticeDao.getSitenoticeVOTopList(sitequestionSearchVO);
		return sitenoticeVOTopList;
	}
	
	@Override
	public SitenoticeVO getSitenoticeVO(int snNo) throws Exception {
		
		SitenoticeVO sitenoticeVO = sitenoticeDao.getSitenoticeVO(snNo);
		
		if (sitenoticeVO == null) {
			throw new Exception("["+snNo+"] 조회 실패");
		}
		
		return sitenoticeVO;
	}

	@Override
	public void insertSitenoticeVO(SitenoticeVO sitenoticeVO) {
		sitenoticeDao.insertSitenoticeVO(sitenoticeVO);
		
	}

	@Override
	public void updateSitenoticeVO(SitenoticeVO sitenoticeVO) throws Exception {
		SitenoticeVO sitenoticeVO2 = sitenoticeDao.getSitenoticeVO(sitenoticeVO.getSnNo());
		
		if (sitenoticeVO2 == null) {
			throw new Exception("["+ sitenoticeVO.getSnNo()+"] 조회 실패");
		}
		
		int cnt = sitenoticeDao.updateSitenoticeVO(sitenoticeVO);
		
		if (cnt < 1) {
			throw new Exception("["+ sitenoticeVO.getSnNo()+"] 수정 실패");
		}
	}

	@Override
	public void deleteSitenoticeVO(SitenoticeVO sitenoticeVO) throws Exception {
		
		SitenoticeVO sitenoticeVO2 = sitenoticeDao.getSitenoticeVO(sitenoticeVO.getSnNo());

		if (sitenoticeVO2 == null) {
			throw new Exception("["+ sitenoticeVO.getSnNo() +"] 조회 실패");
		}
		
		int cnt = sitenoticeDao.deleteSitenoticeVO(sitenoticeVO);
		
		if (cnt < 1) {
			throw new Exception("["+ sitenoticeVO.getSnNo()+"] 수정 실패");
		}
	}


}
