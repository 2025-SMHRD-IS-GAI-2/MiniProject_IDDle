package controller;

import model.UserVO;
import model.UserDAO;

public class UserController {
	private UserDAO dao = new UserDAO();

	public UserVO registerUser(String id, String pw, String name, int age) {
		UserVO user = new UserVO(id, pw, name, age);
		return dao.registerUser(user);
	}

	public UserVO loginUser(String id, String pw) {
		return dao.login(id, pw);
	}
}
