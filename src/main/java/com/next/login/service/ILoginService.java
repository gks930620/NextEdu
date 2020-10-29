package com.next.login.service;



import com.next.login.vo.UserVO;

public interface ILoginService {

	/**
	 * <b>사용자 로그인 체크</b>
	 * @param  user
	 * @return UserVO
	 * @throws BizNotFoundException
	 * @throws BizPasswordNotMatchedException
	 */
	public UserVO loginCheck(UserVO user) throws Exception;
	
	/**
	 * 로그아웃 할 때 처리 <br>
	 * 로그아웃 기록  
	 * @param user
	 * @throws BizException
	 */
	public void logout(UserVO user);
	
}
