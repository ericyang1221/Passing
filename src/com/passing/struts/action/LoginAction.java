package com.passing.struts.action;

import static com.passing.consts.CommonConsts.COM_SPACE;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.passing.consts.LoginConsts;
import com.passing.spring.service.impl.LoginServiceBean;
import com.passing.struts.form.LoginForm;
import com.passing.util.LogUtil;

public class LoginAction extends Action {

	private LoginServiceBean loginServiceBean;
	private String encoding = "UTF-8";
	private String contentType = "application/json";
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LoginForm loginForm = (LoginForm) form;

		String userName = loginForm.getUserName();
		String password = loginForm.getPassword();

		String loginRst = loginServiceBean.login(userName + COM_SPACE
				+ password);

		this.contentType = contentType + ";charset=" + encoding;
		
		LogUtil.log.info("Set contentType to: " + contentType);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("loginResult", loginRst);
		
		response.setContentType(contentType);
		response.setCharacterEncoding(encoding);
		PrintWriter pw = response.getWriter(); 
		pw.write(jsonObj.toString());
		pw.flush();
//		response.getOutputStream().write(jsonObj.toString().getBytes(encoding));
		
		return mapping.findForward(null);
		
//		if (LoginConsts.LOG_SUCCESS.equals(loginRst)) {
//
//			return (mapping.findForward("success"));
//		} else {
//
//			request.getSession().setAttribute("errMsg", loginRst);
//			return (mapping.findForward("failure"));
//		}
		
		
		
	}

	public LoginServiceBean getLoginServiceBean() {
		return loginServiceBean;
	}

	public void setLoginServiceBean(LoginServiceBean loginServiceBean) {
		this.loginServiceBean = loginServiceBean;
	}
}
