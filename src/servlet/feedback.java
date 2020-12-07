package servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import dao.orm.EpsFeedback;

import service.impl.DefaultManagerImpl;

/**
 * 
 * @author JOHN
 *
 */
public class feedback extends HttpServlet{
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String Oid=request.getParameter("Oid");
		String path=request.getParameter("path");
		String content=request.getParameter("content");
		
		WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
		DefaultManagerImpl manager = (DefaultManagerImpl) ctx.getBean("defaultManager");
		
		//System.out.println("SELECT COUNT(*)FROM wwpass WHERE username='"+username+"' AND password='"+password+"'");
		if(manager.sqlGetInt("SELECT COUNT(*)FROM wwpass WHERE username='"+username+"' AND password='"+password+"'")>0)
		if(!content.trim().equals("")){			
			EpsFeedback ef=new EpsFeedback();
			ef.setAuthor(username);
			ef.setContent(content);
			ef.setPage(Integer.parseInt(Oid));			
			ef.setPostime(new Date());
			manager.update(ef);
		}
		
		HttpSession session = request.getSession(false);
		session.setAttribute("account", username);//儲存我的帳號
		response.sendRedirect("myPortfolio?path="+path+"&page="+Oid);
	}

}
