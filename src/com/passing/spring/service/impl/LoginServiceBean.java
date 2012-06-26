package com.passing.spring.service.impl;

import java.util.List;

import com.passing.spring.service.LoginService;
import com.passing.consts.LoginConsts;
import com.passing.hibernate.beans.PassingUser;
import com.passing.hibernate.dao.PassingUserDao;
import static com.passing.consts.CommonConsts.*;

public class LoginServiceBean implements LoginService {

	public LoginServiceBean() {
		super();
	}

	private PassingUserDao passingUserDao;

	public String login(String userInfo) {
		String result;
		String[] userInfoArr = userInfo.split(COM_SPACE);
		if (userInfoArr.length == 0) {
			result = LoginConsts.LOG_INFO_NULL;
		} else {
			List<PassingUser> searchRst = passingUserDao.getPassingUserByName(userInfoArr[LoginConsts.LOG_USERNAME]);
			if (searchRst.size() != 0) {
				if (searchRst.get(0).getPassword().equals(userInfoArr[LoginConsts.LOG_PWD])) {
					result = LoginConsts.LOG_SUCCESS;
				} else {
					result = LoginConsts.LOG_INFO_PWD_ERR;
				}
			} else {
				result = LoginConsts.LOG_INFO_NOUSER;
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
