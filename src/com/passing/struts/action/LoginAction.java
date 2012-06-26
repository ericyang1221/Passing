package com.passing.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.passing.consts.LoginConsts;
import com.passing.spring.service.impl.LoginServiceBean;
import com.passing.struts.form.LoginForm;
import static com.passing.consts.CommonConsts.*;

public class LoginAction extends Action {

	private LoginServiceBean loginServiceBean;
	
	public ActionForward excute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		LoginForm loginForm = (LoginForm)form;
		
		String userName = loginForm.getUserName();
		String password = loginForm.getPassword();
		
		String loginRst = loginServiceBean.login(userName + COM_SPACE + password);
		
		if (LoginConsts.LOG_SUCCESS.equals(loginRst)) {
			
			return (mapping.findForward("success"));
		} else {
			
			request.getSession().setAttribute("errMsg", loginRst);
			return (mapping.findForward("failure"));
		}
	}

	public LoginServiceBean getLoginServiceBean() {
		return loginServiceBean;
	}

	public void setLoginServiceBean(LoginServiceBean loginServiceBean) {
		this.loginServiceBean = loginServiceBean;
	}
}
