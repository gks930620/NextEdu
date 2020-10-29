
package com.next.siteanswer.service;

import java.util.List;

import com.next.siteanswer.vo.SiteanswerSearchVO;
import com.next.siteanswer.vo.SiteanswerVO;

public interface ISiteanswerService {

	/* 답변 목록 조회 */
	public List<SiteanswerVO> SiteanswerVOListByParent(SiteanswerSearchVO siteanswerSearchVO);
	public void insertSiteanswer(SiteanswerVO siteanswerVO) throws Exception;
	// public void updateSiteanswer(SiteanswerVO siteanswerVO) throws Exception;
	public void deleteSiteanswer(SiteanswerVO siteanswerVO) throws Exception;

}
