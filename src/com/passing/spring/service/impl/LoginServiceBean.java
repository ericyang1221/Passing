package com.passing.spring.service.impl;

import java.util.List;

import com.passing.spring.service.LoginService;
import com.passing.consts.LoginConsts;
import com.passing.hibernate.beans.PassingUser;
import com.passing.hibernate.dao.PassingUserDao;

public class LoginServiceBean implements LoginService {

	public LoginServiceBean() {
		super();
	}

	private PassingUserDao passingUserDao;

	public String login(String userInfo) {
		String result;
		String[] userInfoArr = userInfo.split(" ");
		if (userInfoArr.length == 0) {
			result = "请填写注册信息！";
		} else {
			List<PassingUser> searchRst = passingUserDao.getPassingUserByName(userInfoArr[LoginConsts.LOG_USERNAME]);
			if (searchRst.size() != 0) {
				if (searchRst.get(0).getPassword().equals(userInfoArr[LoginConsts.LOG_PWD])) {
					result = "登陆成功";
				} else {
					result = "密码错误";
				}
			} else {
				result = "用户不存在";
			}
		}
		return result;
	}

	public PassingUserDao getPassingUserDao() {
		return passingUserDao;
	}

	public void setPassingUserDao(PassingUserDao passingUserDao) {
		this.passingUserDao = passingUserDao;
	}

}
