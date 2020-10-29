package com.next.attach.service;

import com.next.attach.vo.AttachVO;

public interface IAttachService {
	
	/** 첨부파일 상세 조회 */
	public AttachVO getAttach(int atchNo) throws Exception;

	/** 다운로드 횟수 증가 */
	public void increaseDownHit(int atchNo);
	
}