package com.passing.spring.service.impl;

import java.util.List;

import com.passing.consts.RegisterConsts;
import com.passing.hibernate.beans.PassingUser;
import com.passing.hibernate.dao.PassingUserDao;
import com.passing.spring.service.RegisterService;

public class RegisterServiceBean implements RegisterService {

	public RegisterServiceBean() {
		super();
	}

	private PassingUserDao passingUserDao;

	public String register(String userInfo) {
		String msg;

		String[] userInfoArr = userInfo.split(" ");
		if (userInfoArr.length == 0) {
			msg = "请填写注册信息！";
		} else {
			if (passingUserDao.getPassingUserByName(
					userInfoArr[RegisterConsts.REG_USERNAME]).size() == 0) {
				if (passingUserDao.getPassingUserByEmail(
						userInfoArr[RegisterConsts.REG_EMAILADDRESS]).size() == 0) {
					PassingUser user = new PassingUser();
					user.setUsername(userInfoArr[RegisterConsts.REG_USERNAME]);
					user.setPassword(userInfoArr[RegisterConsts.REG_PWD]);
					user.setNickname(userInfoArr[RegisterConsts.REG_NICKNAME]);
					user
							.setEmailaddress(userInfoArr[RegisterConsts.REG_EMAILADDRESS]);
					user.setSex(Double.valueOf(
							userInfoArr[RegisterConsts.REG_SEX]).longValue());
					if (passingUserDao.insert(user)) {
						msg = "注册成功";
					} else {
						msg = "注册失败，请重新注册";
					}
				} else {
					msg = "该邮箱已被注册";
				}
			} else {
				msg = "该用户已存在";
			}
		}
		return msg;
	}

	public boolean isUserExist(String username) {
		List<PassingUser> user = passingUserDao.getPassingUserByName(username);
		if (user.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public PassingUserDao getPassingUserDao() {
		return passingUserDao;
	}

	public void setPassingUserDao(PassingUserDao passingUserDao) {
		this.passingUserDao = passingUserDao;
	}
}
