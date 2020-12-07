package struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import service.impl.DefaultManagerImpl;
import struts.action.BaseAction;

public class ClassInfoAction extends BaseAction{
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)throws Exception {
			HttpSession session = request.getSession(false);
			DefaultManagerImpl manager = (DefaultManagerImpl)getBean("defaultManager");
			request.setAttribute("classInfo", manager.sqlGet("SELECT path, siteName, siteDescript FROM Eps_user eu, stmd s WHERE " +
					"eu.Uid=s.student_no AND s.depart_class='"+request.getParameter("ClassInfo")+"'"));
			
			request.setAttribute("target", "classInfo");
			return mapping.findForward("ClassInfo");
		}
}
