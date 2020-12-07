package servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import service.impl.DefaultManagerImpl;

/**
 * 取得最新消息(AJAX)
 * @author JOHN
 *
 */
public class GetNews extends HttpServlet{
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
		DefaultManagerImpl manager = (DefaultManagerImpl) ctx.getBean("defaultManager");
		String Oid=request.getParameter("Oid");
		//String name1=new String(Oid.getBytes("iso-8859-1"),"utf-8"); 
		
		Map map = manager.sqlGetMap("SELeCT * FROM Message m WHERE m.Oid='"+Oid+"' LIMIT 1");
		response.setContentType("text/xml; charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		
		String content;
		
		out.println("<pront>");
		
		if(map==null){
			out.println("<content>查無資料</content>");
			out.println("<username>誰呀</username>");
			out.println("<username>yyyy/MM/dd</username>");
		}else{
			content=map.get("Content").toString();
			content=content.replace("<" ,"&lt;"); //去除<
			content=content.replace(">" ,"&gt;"); //去除>
			content=content.replace("&" ,"&amp;"); //去除&
			content=content.replace("\"" ,"&quot;"); //去除"
			
			out.println("<content>"+content+"</content>");
			out.println("<username>"+map.get("Sender")+"</username>");
			out.println("<newsdate>"+map.get("StartDate")+"</newsdate>");
		}
		
		out.println("</pront>");
		out.close();
		
	}
}
