
package com.next.member.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.next.member.dao.IMemberDao;
import com.next.member.vo.MemberSearchVO;
import com.next.member.vo.MemberVO;

@Service
public class MemberServiceImpl implements IMemberService {

	@Inject
	IMemberDao memberDao;

	@Override
	public void insertMemberVO(MemberVO memberVO) throws Exception {
		MemberVO memberVO2 = memberDao.getMember(memberVO.getMemId());
		if (memberVO2 != null) {
			throw new Exception();
		}
		memberDao.insertMember(memberVO);
		int tli=memberDao.getTli(memberVO);     //DB에 있으면 1 없으면 0
		if(tli==1) {
			memberDao.updateTli(memberVO.getMemId());
		}
		
	}

	@Override
	public void updateMemberVO(MemberVO memberVO) throws Exception {
		int cnt = memberDao.updateMember(memberVO);
		if (cnt < 0) {
			throw new Exception("[" + memberVO.getMemId() + "] 수정 실패");
		}
	}

	@Override
	public void deleteMemberVO(MemberVO memberVO) throws Exception {
		int cnt = memberDao.deleteMember(memberVO);
		if (cnt < 1) {
			throw new Exception("[" + memberVO.getMemId() + "] 삭제 실패");
		}
	}

	@Override
	public MemberVO getMemberVO(String memId) throws Exception {
		MemberVO memberVO = memberDao.getMember(memId);
		return memberVO;
	}

	@Override
	public List<MemberVO> getMemberVOList(MemberSearchVO memberSearchVO) {
		return null;
	}

	@Override
	public int idCheck(MemberVO memberVO) throws Exception {
		int cnt = memberDao.idCheck(memberVO);
		return cnt;
	}
}
