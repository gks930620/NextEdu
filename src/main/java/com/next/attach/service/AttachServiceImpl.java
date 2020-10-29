package com.next.attach.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.next.attach.dao.IAttachDao;
import com.next.attach.vo.AttachVO;

@Service
public class AttachServiceImpl implements IAttachService {
	
	@Inject
	private IAttachDao attachDao;

	@Override
	public AttachVO getAttach(int atchNo) throws Exception {
		AttachVO vo = attachDao.getAttach(atchNo);
		if (vo == null) {
			throw new Exception("첨부파일 [" + atchNo + "]에 대한 조회 실패");
		}
		return vo;
	}

	@Override
	public void increaseDownHit(int atchNo) {
		attachDao.increaseDownHit(atchNo);
	}

}