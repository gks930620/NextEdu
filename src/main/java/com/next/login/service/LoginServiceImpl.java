package com.next.login.service;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.next.common.util.MybatisSqlSessionFactory;
import com.next.login.vo.UserVO;
import com.next.member.dao.IMemberDao;
import com.next.member.vo.MemberVO;

@Service
public class LoginServiceImpl implements ILoginService{

	@Autowired
	private IMemberDao memberDao;
	
	SqlSessionFactory factory =  MybatisSqlSessionFactory.getSqlSessionFactory();
	
	@Override
	public UserVO loginCheck(UserVO user) throws Exception {

		System.out.println(user.getUserId() + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		MemberVO vo = memberDao.getMember(user.getUserId());
		if (vo == null) {
			throw new Exception(user.getUserId() + "회원이 존재하지 않습니다");
		}

		if (!vo.getMemPass().equals(user.getUserPass())) {
			throw new Exception(user.getUserId() + "비밀번호가 다릅니다.");
		}

		System.out.println(vo.getMemId() + "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
		
		

		// 성공시
		UserVO userVO = new UserVO();
		userVO.setUserId(vo.getMemId());
		userVO.setUserPass(vo.getMemPass());
		userVO.setUserName(vo.getMemName());
		userVO.setUserRole(vo.getMemRole());
		//userVO.setUserRole(memberDao.getUserRoleByUserId(vo.getMemId()));

		return userVO;

	}

	@Override
	public void logout(UserVO user) {
		// TODO 로그인 이력테이블이 완성되면 처리
		
	}
	
}
