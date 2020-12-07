package struts.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import service.impl.DefaultManagerImpl;
/**
 * 投票
 * @author JOHN
 *
 */
public class VoteAction extends BaseAction {

	public ActionForward execute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response)throws Exception {	
		
		HttpSession session = request.getSession(false);
		DynaActionForm aForm = (DynaActionForm) form;		
		DefaultManagerImpl manager = (DefaultManagerImpl)getBean("defaultManager");	
		
		String path=aForm.getString("path");
		String act_oid=aForm.getString("act_oid");
		
		
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		Date date=new Date();		
		
		Map map=manager.sqlGetMap("SELECT * FROM Eps_Act_parameter WHERE Oid="+act_oid);		
		
		if(sf.parse(map.get("judge_start").toString()).after(date)){
			request.setAttribute("message", "投票時間為"+map.get("judge_start")+"至"+map.get("judge_end")+"，目前尚未開放。");
			return mapping.findForward("vote");
		}
		
		String username;
		String password;
		if(session.getAttribute("account")==null){
			username=aForm.getString("username");
			password=aForm.getString("password");				
			if(manager.sqlGetInt("SELECT COUNT(*)FROM wwpass WHERE username='"+username+"' AND password='"+password+"'")<1){
				request.setAttribute("message", "帳號或密碼錯誤");
				return mapping.findForward("vote");
			}
			session.setAttribute("account", username);
		}else{
			username=(String)session.getAttribute("account");
		}
		
		if(path.trim().equals("")){
			request.setAttribute("message", "操作錯誤");
			return mapping.findForward("vote");
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		String EpsActUserOid=manager.sqlGetStr("SELECT Uid FROM Eps_user WHERE path='"+path+"'");
		
		try{
			manager.exSql("INSERT INTO Eps_Act_vote(act_oid, EpsActUid, elector)VALUES('"+act_oid+"', '"+EpsActUserOid+"', '"+username+"');");
			manager.exSql("UPDATE Eps_Act_user SET count=count+1 WHERE Uid='"+EpsActUserOid+"' AND act_oid='"+act_oid+"'");
			
			//System.out.println("UPDATE Eps_Act_user SET count=count+1 WHERE Uid='"+EpsActUserOid+"' AND act_oid='"+act_oid+"'");
			request.setAttribute("message", "投票完成");
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("message", "你已經投過票了。");
		}
		
		return mapping.findForward("vote");
	}
}