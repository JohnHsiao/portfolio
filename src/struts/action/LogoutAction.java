package struts.action;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
/**
 * 登出
 * @author JOHN
 *
 */
public class LogoutAction extends BaseAction{
	
	public ActionForward execute(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {
		
		//HttpSession session = request.getSession(false);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//ActionMessages msg = new ActionMessages();
		//msg.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("Message1", "已順利登出"));
		//session.setAttribute("content", aPage);
		return mapping.findForward("Welcome");
	}

}
