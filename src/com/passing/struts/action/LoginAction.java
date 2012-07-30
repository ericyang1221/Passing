package com.passing.struts.action;

import static com.passing.consts.CommonConsts.COM_SPACE;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

public class LoginAction extends BaseAction {

	private LoginServiceBean loginServiceBean;
//	private String encoding = "UTF-8";
//	private String contentType = "application/json";

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		/** when using form for data transform */
//		LoginForm loginForm = (LoginForm) form;
//		String userName = loginForm.getUserName();
//		String password = loginForm.getPassword();
		
		/** when using ajax for data transform */
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");

		String loginRst = loginServiceBean.login(userName + COM_SPACE
				+ password);

//		this.contentType = contentType + ";charset=" + encoding;
		
//		LogUtil.log.info("Set contentType to: " + contentType);
		
//		JSONObject jsonObj = new JSONObject();
//		jsonObj.put("loginResult", loginRst);
		makeJSONObject(response, "loginResult", loginRst);
		
		/** make a jsonObject with map */
//		Map<String, Object> person1 = new HashMap<String, Object>();
//		Map<String, String> person2 = new HashMap<String, String>();
//		person1.put("age", "10");
//		person1.put("sex", "male");
//		
//		List<String> habits = new ArrayList<String>();
//		habits.add("basket ball");
//		habits.add("foot ball");
//		
//		person1.put("habits", habits);
//		person2.put("age2", "10");
//		person2.put("sex2", "male");
//		person2.put("habit2", "afda");
		
		/** make a jsonObject with bean_part1(its the best way) */
//		List<Object> persons = new ArrayList<Object>();
//		persons.add(new Person("name1",12,new String[]{"basketball","football"}));
//		persons.add(new Person("name13",122,new String[]{"basketball222","football222"}));
//		
//		jsonObj.put("loginResult", persons);
		
//		response.setContentType(contentType);
//		response.setCharacterEncoding(encoding);
//		PrintWriter pw = response.getWriter(); 
//		pw.write(jsonObj.toString());
//		pw.flush();
		
		return mapping.findForward(null);
		
		/** actionForward must be returned when using form for data transform */
//		if (LoginConsts.LOG_SUCCESS.equals(loginRst)) {
//
//			return (mapping.findForward("success"));
//		} else {
//
//			request.getSession().setAttribute("errMsg", loginRst);
//			return (mapping.findForward("failure"));
//		}
		
	}

	/** make a jsonObject with bean_part2(its the best way) */
//	public class Person {
//		private String name;
//		private int age;
//		private String[] habats;
//
//		public Person() {
//		}
//
//		public Person(String name, int age, String[] habits) {
//			this.name = name;
//			this.age = age;
//			this.habats = habits;
//		}
//
//		public String getName() {
//			return name;
//		}
//
//		public void setName(String name) {
//			this.name = name;
//		}
//
//		public int getAge() {
//			return age;
//		}
//
//		public void setAge(int age) {
//			this.age = age;
//		}
//
//		public String[] getHabats() {
//			return habats;
//		}
//
//		public void setHabats(String[] habats) {
//			this.habats = habats;
//		}
//	}

	public LoginServiceBean getLoginServiceBean() {
		return loginServiceBean;
	}

	public void setLoginServiceBean(LoginServiceBean loginServiceBean) {
		this.loginServiceBean = loginServiceBean;
	}
}
