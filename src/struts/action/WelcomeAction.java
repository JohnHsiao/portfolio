package struts.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import service.impl.DefaultManagerImpl;
/**
 * 登入前資訊.
 * 
 * @author JOHN
 *
 */
public class WelcomeAction extends BaseAction {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception{
		
		DefaultManagerImpl manager=(DefaultManagerImpl)getBean("defaultManager");
		
		String target="newsBox";
		if(request.getParameter("target")!=null){
			target=request.getParameter("target");
		}
		
		List<Map>result=null;
		List tmp;		
		//if(target.equals("total")){
		
		//所有網站數量
		request.setAttribute("countSite", manager.sqlGetStr("SELECT COUNT(*)FROM Eps_user"));
		request.setAttribute("countPage", manager.sqlGetStr("SELECT COUNT(*)FROM Eps_pages"));
		request.setAttribute("new5sites", manager.sqlGet("SELECT path, siteName, siteDescript FROM Eps_user ORDER BY Oid DESC LIMIT 20"));		
		request.setAttribute("new5pages", manager.sqlGet("SELECT ep.Oid, ep.Uid, ep.title, eu.path FROM Eps_pages ep, Eps_user eu WHERE eu.Uid=ep.Uid AND ep.title<>'index' ORDER BY ep.Oid DESC LIMIT 20"));
		request.setAttribute("top5sites", manager.sqlGet("SELECT path, siteName, siteDescript FROM Eps_user ORDER BY counter DESC LIMIT 20"));		
		request.setAttribute("top5pages", manager.sqlGet("SELECT title FROM Eps_pages WHERE title<>'index' ORDER BY Oid DESC LIMIT 20"));
		
		//競賽參與者
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-M-dd");
		String today=sf.format(new Date());
		
		List games=manager.sqlGet("SELECT * FROM Eps_Act_parameter p WHERE p.end>='"+today+"'");
		List players=new ArrayList();
		for(int i=0; i<games.size(); i++){
			players.addAll(manager.sqlGet("SELECT u.count, p.name, eu.siteDescript, c.ClassName, s.student_no, s.student_name, eu.siteName, eu.path FROM " +
			"Eps_Act_parameter p, Eps_Act_user u, stmd s, Class c, Eps_user eu WHERE eu.Uid=u.Uid AND p.Oid=u.act_oid AND " +
			"u.Uid=s.student_no AND c.ClassNo=s.depart_class AND u.act_oid="+((Map)games.get(i)).get("Oid")+" ORDER BY u.count DESC"));
		}
		
		request.setAttribute("players", players);
		//}
		
		List dept;
		//學生
		if(target.equals("student")){
			result=manager.sqlGet("SELECT name, id as idno FROM CODE_DEPT ORDER BY id");
			
			int dst;
			int pst;
			
			for(int i=0; i<result.size(); i++){
				dst=0;
				pst=0;
				tmp=manager.sqlGet("SELECT COUNT(*) as cnt, c.ClassName, c.ClassNo FROM Eps_user u, stmd s, Class c WHERE u.Uid=s.student_no AND " +
						"s.depart_class=c.ClassNo AND c.DeptNo='"+((Map)result.get(i)).get("idno")+"' GROUP BY c.ClassNo ORDER BY c.ClassNo");
				
				for(int j=0; j<tmp.size(); j++){
					((Map)tmp.get(j)).put("tol", manager.sqlGetInt("SELECT COUNT(*)FROM stmd WHERE depart_class='"+((Map)tmp.get(j)).get("ClassNo")+"'"));
					
					dst=dst+manager.sqlGetInt("SELECT COUNT(*) FROM stmd s, Eps_user u WHERE u.Uid=s.student_no AND s.depart_class='"+((Map)tmp.get(j)).get("ClassNo")+"'");
					pst=pst+manager.sqlGetInt("SELECT COUNT(*) FROM stmd WHERE depart_class='"+((Map)tmp.get(j)).get("ClassNo")+"'");
				}
				((Map)result.get(i)).put("std", tmp);			
				((Map)result.get(i)).put("cnt", tmp.size()+"");
				
				
				((Map)result.get(i)).put("pst", pst);
				((Map)result.get(i)).put("dst", dst);
				
				
			}
			
			//request.setAttribute("dst", dst);
			
			
			
			int cnt=0;
			int tol=0;
			List c;
			for(int i=0; i<result.size(); i++){
				
				c=(List)((Map)result.get(i)).get("std");
				for(int j=0; j<c.size(); j++){
					cnt=cnt+Integer.parseInt(((Map)c.get(j)).get("cnt").toString());
					tol=tol+Integer.parseInt(((Map)c.get(j)).get("tol").toString());
				}
				
			}
			request.setAttribute("cnt", cnt);
			request.setAttribute("tol", tol);
		}		
		
		//教師
		if(target.equals("teacher")){
			//result=manager.sqlGet("SELECT name, idno FROM CodeEmpl WHERE category='UnitTeach' OR category='Unit' ORDER BY sequence, category DESC");			
			
			result=manager.sqlGet("SELECT name, id as idno FROM CODE_DEPT ORDER BY id");	
			
			int tol=0;
			
			for(int i=0; i<result.size(); i++){	
				
				tmp=manager.sqlGet("SELECT e.idno, eu.siteName, eu.path, eu.siteDescript, eu.counter FROM " +
				"Eps_user eu, empl e WHERE e.idno=eu.Uid AND e.unit='"+((Map)result.get(i)).get("idno")+"' AND e.category='1'");				
				
				((Map)result.get(i)).put("std", tmp);			
				((Map)result.get(i)).put("cnt", tmp.size()+"");
				((Map)result.get(i)).put("all", manager.sqlGetInt("SELECT COUNT(*) FROM empl e WHERE e.unit='"+((Map)result.get(i)).get("idno")+"' AND e.category='1'"));
				tol=tol+manager.sqlGetInt("SELECT COUNT(*) FROM empl e WHERE e.unit='"+((Map)result.get(i)).get("idno")+"' AND e.category='1'");
				
				
			}
			int cnt=0;
			
			List c;
			for(int i=0; i<result.size(); i++){
				
				c=(List) ((Map)result.get(i)).get("std");
				cnt=cnt+c.size();
				
				
			}
			request.setAttribute("cnt", cnt);
			request.setAttribute("tol", tol);
		}
		
		//校友
		if(target.equals("graduate")){
			result=manager.sqlGet("SELECT name, id as idno FROM CODE_DEPT ORDER BY id");
			for(int i=0; i<result.size(); i++){				
				tmp=(manager.sqlGet("SELECT st.student_no, eu.siteName, eu.path, eu.siteDescript, eu.counter FROM " +
				"Eps_user eu, Gstmd st, Class c, code5 c5 WHERE c5.idno='"+((Map)result.get(i)).get("idno")+"' AND c5.idno=c.DeptNo AND " +
				"c5.category='Dept' AND st.depart_class=c.ClassNo AND eu.Uid=st.student_no ORDER BY eu.counter DESC"));
				((Map)result.get(i)).put("std", tmp);			
				((Map)result.get(i)).put("cnt", tmp.size()+"");
			}			
		}
		
		request.setAttribute("toPage", result);
		request.setAttribute("target", target);
		
		request.setAttribute("news", manager.sqlGet("SELECT title, StartDate, Oid FROM Message " +
				"WHERE category='Portfolio' " +
				"ORDER BY StartDate DESC LIMIT 20"));
		//session.setAttribute("Go", "main");
		//session.setAttribute("allUserMenu", menu);//存一般使用者菜單
		return mapping.findForward("continue");		
	}
}
