package com.next.login.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserList {
	private Map<String, UserVO> usersMap = null;

	public UserList() {
		UserVO user = null;
		usersMap = new HashMap<String, UserVO>();
		user = new UserVO("malja", "말자", "1004", "ADMIN");
		usersMap.put(user.getUserId(), user);
		user = new UserVO("sunja", "순자", "1111", "USER");
		usersMap.put(user.getUserId(), user);
		user = new UserVO("nolja", "야놀자", "1004", "USER");
		usersMap.put(user.getUserId(), user);
		user = new UserVO("milkis", "밀키스", "1004", "MANAGER");
		usersMap.put(user.getUserId(), user);
	}

	public UserVO getUser(String id) {
		System.out.println("UserList getUser id=" + id);
		if (usersMap.containsKey(id)) {
			System.out.println("[" + id + "] 회원 존재");
			return usersMap.get(id);
		} else {
			System.out.println("[" + id + "] 회원이 존재하지 않음");
			return null;
		}
	}

	public List<UserVO> getUserList() {
		return new ArrayList<UserVO>(usersMap.values());
	}
	
	public Map<String, UserVO> getUsersMap() {
		return usersMap;
	}

}
