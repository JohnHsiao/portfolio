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
/**
 * 登入
 * @author JOHN
 *
 */
public class LoginAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
	HttpServletRequest request, HttpServletResponse response)throws Exception {
		HttpSession session = request.getSession(false);
		DynaActionForm loginForm = (DynaActionForm) form;
		String username=(String)loginForm.get("username");
		String password=(String)loginForm.get("password");		
		
		if(username.trim().length()<3||password.trim().length()<3){
			ActionMessages error = new ActionMessages();	//建立共用錯誤訊息
			error.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("Message1","帳號或密碼欄位輸入不正確"));
			saveErrors(request, error);
			return mapping.findForward("Welcome");
		}
		
		session.setAttribute("account", username);//儲存我的帳號
		return mapping.findForward("continue");
	}
	
	
	
}