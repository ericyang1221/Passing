package com.passing.struts.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.passing.consts.SearchEnToCnConsts;
import com.passing.hibernate.beans.TbEnWord;
import com.passing.hibernate.dao.TbEnWordDao;
import com.passing.spring.service.impl.SearchEnToCnBean;
import com.passing.struts.form.SearchEnToCnForm;
import com.passing.struts.vo.EnExtdWordVo;
import com.passing.struts.vo.EnWordVo;

public class SearchEnToCnAction extends BaseAction {

	// 使用普遍依赖注入方式
	private SearchEnToCnBean searchEnToCnBean;

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String searchStr = request.getParameter("searchStr");

		List<Object[]> enWordInfo = searchEnToCnBean.searchEnToCn(searchStr);

		if (enWordInfo.isEmpty()) {
			Map<String, Object> jsonResponseLs = new HashMap<String, Object>();
//			jsonResponseLs.put("enWordInfo", SearchEnToCnConsts.ENTOCN_RSLT_NONE);
			jsonResponseLs.put("enWordInfo", null);
			
			this.makeJSONObject(response, "enWordResponse", jsonResponseLs);
		} else {
			// json process for EnWord
			List<EnWordVo> jsonEnWordLs = new ArrayList<EnWordVo>();
			EnWordVo jsonEnWord;
			for (int i = 0; i < enWordInfo.size(); i ++) {
				Object[] enWord = enWordInfo.get(i);
				jsonEnWord = new EnWordVo(enWord[0], enWord[1], enWord[2],
						enWord[3], enWord[4], enWord[5], enWord[6], enWord[7],
						enWord[8], enWord[9], enWord[10]);
				jsonEnWordLs.add(jsonEnWord);
			}
			
			// json process for EnExtdWord
			List<Object[]> enExtdWordInfo = searchEnToCnBean.searchEnToCnExtdInfo(searchStr);
			List<EnExtdWordVo> jsonEnExtdWordLs = new ArrayList<EnExtdWordVo>();
			if (enExtdWordInfo.isEmpty()) {
				jsonEnExtdWordLs = null;
			} else {
				EnExtdWordVo jsonEnExtdWord;
				for (int i = 0; i < enExtdWordInfo.size(); i ++) {
					Object[] enExtdWord = enExtdWordInfo.get(i);
					jsonEnExtdWord = new EnExtdWordVo(enExtdWord[0], enExtdWord[1], enExtdWord[2],
							enExtdWord[3], enExtdWord[4], enExtdWord[5], enExtdWord[6], enExtdWord[7],
							enExtdWord[8], enExtdWord[9], enExtdWord[10], enExtdWord[11]);
					jsonEnExtdWordLs.add(jsonEnExtdWord);
				}
			}
			
			Map<String, Object> jsonResponseLs = new HashMap<String, Object>();
			jsonResponseLs.put("enWordInfo", jsonEnWordLs);
			jsonResponseLs.put("enExtdWordInfo", jsonEnExtdWordLs);
			
			this.makeJSONObject(response, "enWordResponse", jsonResponseLs);
		}
		
		return (mapping.findForward(null));
	}

	public void setSearchEnToCnBean(SearchEnToCnBean searchEnToCnBean) {
		this.searchEnToCnBean = searchEnToCnBean;
	}

	public SearchEnToCnBean getSearchEnToCnBean() {
		return searchEnToCnBean;
	}
	
}
