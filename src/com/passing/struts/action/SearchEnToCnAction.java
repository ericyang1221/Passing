package com.passing.struts.action;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.passing.hibernate.beans.TbEnWord;
import com.passing.hibernate.dao.TbEnWordDao;
import com.passing.spring.service.impl.SearchEnToCnBean;
import com.passing.struts.form.SearchEnToCnForm;

public class SearchEnToCnAction extends Action {

	// 使用普遍依赖注入方式
	private SearchEnToCnBean searchEnToCnBean;
//	TbEnWordDao dao;

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		 SearchEnToCnForm searchEnToCnForm = (SearchEnToCnForm)form;
//		 ApplicationContext ctx=new
//				 FileSystemXmlApplicationContext("C:/Users/lovelease/git/Passing/WebContent/WEB-INF/config/spring/web-application-config.xml");
//		 ServletContext servletContext = request.getSession().getServletContext(); 
//		 WebApplicationContext ctx=WebApplicationContextUtils
//					.getWebApplicationContext(servletContext);
		 // TbEnWordDao ebEnWordDao=(TbEnWordDao)ctx.getBean("tbEnWordDao");
		 
//		 SearchEnToCnBean searchEnToCnBean =
//		 (SearchEnToCnBean)ctx.getBean("searchEnToCnBean");
		
		 String searchStr = searchEnToCnForm.getSearchStr();
		 
		 List<TbEnWord> searchRslt = searchEnToCnBean.searchEnToCn(searchStr);
		
		 if (searchRslt.size() != 0) {
		 request.getSession().setAttribute("searchRslt", searchRslt);
		 return (mapping.findForward("success"));
		 } else {
		 request.getSession().setAttribute("error", "对不起，您输入的用户名或者密码错误！");
		 return (mapping.findForward("failure"));
		 }
	}

	// public SearchEnToCnBean getSearchEnToCnBean() {
	// return searchEnToCnBean;
	// }

	public void setSearchEnToCnBean(SearchEnToCnBean searchEnToCnBean) {
		this.searchEnToCnBean = searchEnToCnBean;
	}

	public SearchEnToCnBean getSearchEnToCnBean() {
		return searchEnToCnBean;
	}

}
