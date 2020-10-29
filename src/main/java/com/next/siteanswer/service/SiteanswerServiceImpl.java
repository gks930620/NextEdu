
package com.next.siteanswer.service;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import com.next.common.util.MybatisSqlSessionFactory;
import com.next.siteanswer.dao.ISiteanswerDao;
import com.next.siteanswer.vo.SiteanswerSearchVO;
import com.next.siteanswer.vo.SiteanswerVO;
import com.next.sitequestion.dao.ISitequestionDao;
import com.next.sitequestion.vo.SitequestionVO;

@Service
public class SiteanswerServiceImpl implements ISiteanswerService{


	SqlSessionFactory factory = MybatisSqlSessionFactory.getSqlSessionFactory();
	
	@Inject
	private ISiteanswerDao siteanswerDao;
	
	@Inject
	private ISitequestionDao sitequestionDao;
	
	@Override
	public List<SiteanswerVO> SiteanswerVOListByParent(SiteanswerSearchVO siteanswerSearchVO) {
		
		return siteanswerDao.getSiteanswerVOListByParent(siteanswerSearchVO);
	}

	@Override
	public void insertSiteanswer(SiteanswerVO siteanswerVO) throws Exception {
		SitequestionVO sitequestionVO = new SitequestionVO();
		SiteanswerVO vo = siteanswerDao.getSiteanswerVOByParent(siteanswerVO);
		
		if (vo == null) {
			siteanswerDao.insertSiteanswer(siteanswerVO);
		} else {
			siteanswerVO.setSaNo(vo.getSaNo());
			siteanswerDao.updateSiteanswer(siteanswerVO);
		}
		sitequestionVO.setSqNo(siteanswerVO.getSaSqNo());
		sitequestionDao.updateSitequestionVO2(sitequestionVO);
	}

	/*
	 * @Override public void updateSiteanswer(SiteanswerVO siteanswerVO) throws
	 * Exception { SiteanswerVO siteanswerVO2 =
	 * siteanswerDao.getSiteanswerVO(siteanswerVO.getSaNo()); if (siteanswerVO2 ==
	 * null) { throw new Exception("[" + siteanswerVO.getSaNo() + "] 조회 실패"); }
	 * siteanswerDao.updateSiteanswer(siteanswerVO); }
	 */

	@Override
	public void deleteSiteanswer(SiteanswerVO siteanswerVO) throws Exception {
		SiteanswerVO siteanswerVO2 = siteanswerDao.getSiteanswerVO(siteanswerVO.getSaNo());
		if (siteanswerVO2 == null) {
			throw new Exception("[" + siteanswerVO.getSaNo() + "] 조회 실패");
		}
		siteanswerDao.deleteSiteanswer(siteanswerVO);		
	}

}
