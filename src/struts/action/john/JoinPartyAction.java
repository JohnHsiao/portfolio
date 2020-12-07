package struts.action.john;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

public class JoinPartyAction extends BaseAction{
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
				HttpSession session = request.getSession(false);
				DefaultManagerImpl manager = (DefaultManagerImpl)getBean("defaultManager");	
				
				List depts=new ArrayList();
				String gameNo="";
				
				try{
					depts=manager.sqlGet("SELECT c5.idno, c5.name FROM Class c, code5 c5 WHERE c.DeptNo=c5.idno AND c5.category='Dept' GROUP BY c.DeptNo");
					gameNo=manager.sqlGetStr("SELECT Oid FROM Eps_Act_parameter ORDER BY Oid DESC LIMIT 1");
					
				}catch(Exception e){
					e.printStackTrace();
				}
				
				float sumstd;
				float sumport;
				float complate;
				float sumComplate;
				
				float sumcont;
				float sumpages;
				
				float full;
				
				float total;
				
				for(int i=0; i<depts.size(); i++){
					
					sumstd=0;
					sumport=0;
					complate=0;
					sumComplate=0;
					
					sumcont=0;
					sumpages=0;
					full=0;
					
					total=0;
					//裝載參賽者
					((Map)depts.get(i)).put("player", manager.sqlGet("SELECT eu.*, c.ClassName FROM Eps_Act_user u, stmd s, Class c, Eps_user eu WHERE " +
							"c.ClassNo=s.depart_class AND u.Uid=s.student_no AND eu.Uid=u.Uid AND " +
							"c.DeptNo='"+((Map)depts.get(i)).get("idno")+"' AND u.act_oid='"+gameNo+"'"));
					
					sumport=manager.sqlGetInt("SELECT COUNT(*) FROM stmd s, Class c, Eps_user eu WHERE c.ClassNo=s.depart_class AND  s.student_no=eu.Uid AND " +
							"c.DeptNo='"+((Map)depts.get(i)).get("idno")+"'");
					
					
					sumstd=manager.sqlGetInt("SELECT COUNT(*) FROM stmd s, Class c WHERE c.ClassNo=s.depart_class AND c.DeptNo='"+((Map)depts.get(i)).get("idno")+"'");	
					sumcont=manager.sqlGetInt("SELECT COUNT(*) FROM Eps_content ec, stmd s, Class c WHERE ec.Uid=s.student_no AND s.depart_class=c.ClassNo AND c.DeptNo='"+((Map)depts.get(i)).get("idno")+"'");
					sumpages=manager.sqlGetInt("SELECT COUNT(*) FROM Eps_pages ep, stmd s, Class c WHERE c.ClassNo=s.depart_class AND ep.Uid=s.student_no AND c.DeptNo='"+((Map)depts.get(i)).get("idno")+"'");
					
					
					try{
						
						complate=(sumport/sumstd)*100;
						sumComplate=manager.roundOff(complate/2, 2);
						((Map)depts.get(i)).put("complate", manager.roundOff(complate, 2));
						((Map)depts.get(i)).put("sumComplate", sumComplate);//total
						
						((Map)depts.get(i)).put("sumcont", sumcont);
						((Map)depts.get(i)).put("sumpages", sumpages);
						
						((Map)depts.get(i)).put("sumstd", sumstd);
						((Map)depts.get(i)).put("sumport", sumport);
						
						
						
						full=((sumcont+sumpages)/sumstd)*50;
						((Map)depts.get(i)).put("full", manager.roundOff(full ,2));						
						((Map)depts.get(i)).put("total", manager.roundOff(full+sumComplate ,2));
						
						
						
						
					}catch(Exception e){
						((Map)depts.get(i)).put("complate", 0);
						((Map)depts.get(i)).put("sumComplate", 0);
					}
				}
				
				
				
				
				request.setAttribute("joinParty", depts);
				return mapping.findForward("joinParty");
			}

}
