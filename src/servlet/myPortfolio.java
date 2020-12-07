package servlet;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import dao.orm.EpsUser;

import service.impl.DefaultManagerImpl;

/**
 * 學習歷程主程式
 * 2009/3/1
 * TODO 效能檢測
 * @author JOHN
 */
public class myPortfolio extends HttpServlet {

	protected void doGet(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
		DefaultManagerImpl manager = (DefaultManagerImpl) ctx.getBean("defaultManager");
		
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String path=request.getParameter("path");//網頁路徑
		String Uid=manager.sqlGetStr("SELECT Uid FROM Eps_user WHERE path='"+path+"'");
		
		try{
			counter(request, response, path);//計數	
		}catch(Exception e){
			e.printStackTrace();
		}
			
		String output;//頁面本體		
		
		String table=request.getParameter("table");//萬用表格
		String free=request.getParameter("free");//自由表格
		String sysTable=request.getParameter("sysTable");
		
		String tag=null;//文章分類
		String page=request.getParameter("page");//文章	
		if(request.getParameter("tag")!=null){
			tag=new String((request.getParameter("tag")).getBytes("iso-8859-1"),"utf8");//將url分類轉碼
		}
		
		EpsUser user=null;
		//是否有此使用者
		try{
			user=(EpsUser)manager.hqlGetListBy("FROM EpsUser WHERE path='"+path+"'").get(0);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		//取樣版
		try{
			output = manager.sqlGetStr("SELECT template FROM Eps_user WHERE path='"+path+"'");//使用者的樣版
		}catch(Exception e){
			output = manager.sqlGetStr("SELECT template FROM Eps_user WHERE path='"+path+"'");//使用者的樣版
		}
		
		ServletOutputStream stream = response.getOutputStream();		
		output=getHeader(output, user);//取得標準頁首
		output=getFoot(output, user.getFootStyle());//取得標準頁尾			
		output=getTitle(path, output);//取網站標題			
		output=getTag(path, output);//取文章標籤
		output=getNewTopic(path, output, Uid);//取新文章
		
		output=getSysTableList(path, output, Uid);//系統菜單
		output=getTableList(path, output, Uid);//轉自訂菜單
		
		//有指明是萬用表格時
		if(table!=null){
			output=getTableInfo(path, table, output, Uid, free);
		}
		
		//指明要系統帶出的功能
		if(sysTable!=null){
			output=getSystemFunction(path, sysTable, output, Uid);
		}
		
		//指明要標籤分類
		if(tag!=null){
			output=getTag2List(Uid, tag, output, path);
		}
		
		//有指明頁面
		if(page!=null){
			output=getPage(path, page, output);			
		}else{
			//未指定系統表格或文章時 顯示首頁
			if(table==null && tag==null){
				output=getIndex(Uid, output);//取首頁
				output=getSubTitle(path, null, output);//取首頁副標題
			}
		}
		output=chGoogleMap(output);//若有googleMap時	
		output=getTimer(path, output);//時鐘
		output=getUserInfo(Uid, output);//基本資料
		output=putVotePath(path, output);//票選活動
		
		byte[] tmp = output.trim().getBytes("UTF-8");
		stream.write(tmp);		
		stream.close();				
	}
	
	/**
	 * 發生錯誤時要拋出的頁面
	 * @author JOHN
	 *
	 */
	public class ReplaceWordException extends Exception {

	}
	/**
	 * 轉顯示頁的google key
	 * TODO 多主機時待修正
	 * @param output
	 * @return
	 */
	private String chGoogleMap(String output){
		if(!testHost()){
			return output;
		}
		StringBuilder sb1=new StringBuilder(output);		
		int x=sb1.indexOf("maps");//字串發生位置
		//System.out.println("maps 出現位置"+x);
		//System.out.println(sb1);
		if(x>-1){
			int z=output.indexOf(">", x);
			int y=sb1.lastIndexOf("<", x);
			sb1.delete(y, z+1);
			sb1.insert(y, "<script src='http://maps.google.com/maps?file=api&amp;v=2&amp;key=ABQIAAAADHZE4i1v5rcAKlTDq6ooiBSHxdZ-KtLr5s1zqhpjn3F0Y6Bf8RTQQ4mkEExga-XkVXQ3F-pOhxhhIw' type='text/javascript' charset='utf-8'></script>");		
			return sb1.toString();
		}
		return output;
	}
	
	private boolean testHost(){
		try {			
			Enumeration e1 = (Enumeration) NetworkInterface.getNetworkInterfaces();			
			while (e1.hasMoreElements()) {
				NetworkInterface ni = (NetworkInterface) e1.nextElement();
				// System.out.print(ni.getName());
				// System.out.print(": ");
				Enumeration e2 = ni.getInetAddresses();
				while (e2.hasMoreElements()) {
					InetAddress ia = (InetAddress) e2.nextElement();
					if (ia instanceof Inet6Address)
						continue; 
					//System.out.println(ia.getHostName());
					//if(ia.getHostAddress().indexOf("all")>0){
					if(ia.getHostName().indexOf("all")>-1){
						return true;					
					} 
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		
		return false;
	}
	
	/**
	 * 取首頁順便
	 * @param path
	 * @return
	 */
	private String getIndex(String Uid, String output){
		WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
		DefaultManagerImpl manager = (DefaultManagerImpl) ctx.getBean("defaultManager");
		//取內容
		StringBuilder sb=new StringBuilder();
		
		String tmp;
		try{
			tmp=manager.sqlGetStr("SELECT content FROM Eps_pages WHERE title='index' AND Uid='"+Uid+"'LIMIT 1");
			sb.append(tmp);
		}catch(Exception e){
			sb.append("網頁不存在");
		}
		
		StringBuilder sb1=new StringBuilder(output);	
		
		int x=sb1.indexOf("eps_content.gif");//字串發生位置	
		if(x>-1){
			int z=output.indexOf(">", x);
			int y=sb1.lastIndexOf("<", x);
			sb1.delete(y, z+1);
			sb1.insert(y, sb);
			
			return sb1.toString();
		}
		return output;
	}
	
	/**
	 * 取得網頁正標題
	 * @param path
	 * @param output
	 * @return
	 */
	private String getTitle(String path, String output){
		WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
		DefaultManagerImpl manager = (DefaultManagerImpl) ctx.getBean("defaultManager");
		//取標題
		try{
			output=output.replaceAll("#網站名稱#", "<a class='SysHeader' href=myPortfolio?path="+path+" id='siteMap'>"+manager.sqlGetStr("SELECT siteName FROM Eps_user WHERE path='"+path+"'")+"</a> - #頁面名稱#");
			//轉橫幅
			output=replaceBanner(output ,path);
		}catch(Exception e){
			return output;
		}
		
		return output;
	}
	
	/**
	 * 取副標題
	 * @param path
	 * @param str
	 * @param output
	 * @return
	 */
	private String getSubTitle(String path, String str, String output){
		WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
		DefaultManagerImpl manager = (DefaultManagerImpl) ctx.getBean("defaultManager");
		try{
			//取標題		
			if(str==null){
				output=output.replaceAll("#頁面名稱#", manager.sqlGetStr("SELECT siteDescript FROM Eps_user WHERE path='"+path+"'"));
			}else{
				output=output.replaceAll("#頁面名稱#", str);
			}
			return output;
		}catch(Exception e){
			return output;
		}
	}	
	
	/**
	 * 取某標籤下的所有文章列表
	 * @param path
	 * @param output
	 * @return
	 */
	private String getTag2List(String Uid, String tag, String output, String path){
		WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
		DefaultManagerImpl manager = (DefaultManagerImpl) ctx.getBean("defaultManager");
		//System.out.println("SELECT Oid, title, editime FROM Eps_pages WHERE title<>'index' AND Uid='"+Uid+"'");
		
		List list=null;
		if(tag.equals("*")){
			list=manager.sqlGet("SELECT Oid, title, editime FROM Eps_pages WHERE title<>'index' AND Uid='"+Uid+"'");
			output=getSubTitle(Uid, "全部文章列表", output);
			tag="全部文章列表";
		}else{
			list=manager.sqlGet("SELECT Oid, title, editime FROM Eps_pages WHERE title<>'index' AND Uid='"+Uid+"' AND tag='"+tag+"'");
			output=getSubTitle(Uid, manager.sqlGetStr("SELECT tag FROM Eps_pages WHERE tag='"+tag+"' AND Uid='"+Uid+"' limit 1"), output);
		}
		
		StringBuilder sb=new StringBuilder("");
		if(list.size()>0){
			sb.append("<h1>"+tag+"</h1><ul id='tag'>");
			if(tag.equals("*")){
				tag="全部文章";
			}
			sb.append("文章列表");
			for(int i=0; i<list.size(); i++){
				//sb.append("<li><a href=myPortfolio?path="+path+"&page="+((Map)list.get(i)).get("Oid")+">"+((Map)list.get(i)).get("title")+" - "+((Map)list.get(i)).get("editime")+"</a></li>");
				sb.append("<li><a href=myPortfolio?path="+path+"&page="+((Map)list.get(i)).get("Oid")+">"+((Map)list.get(i)).get("title")+"</a></li>");
			}
			sb.append("</ul>");
		}
		
		StringBuilder sb1=new StringBuilder(output);
		int x=sb1.indexOf("eps_content.gif");//字串發生位置	
		if(x>-1){
			int z=output.indexOf(">", x);
			int y=sb1.lastIndexOf("<", x);
			sb1.delete(y, z+1);
			sb1.insert(y, sb);		
			return sb1.toString();
		}
		return output;
	}	
	
	/**
	 * 取一篇文章
	 * @param path
	 * @param page
	 * @return
	 */
	private String getPage(String path, String page, String output){
		WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
		DefaultManagerImpl manager = (DefaultManagerImpl) ctx.getBean("defaultManager");
		Map map=manager.sqlGetMap("SELECT * FROM Eps_pages WHERE Oid='"+page+"'");		
		output=getSubTitle(path, map.get("tag").toString()+" - "+map.get("title").toString(), output);
		StringBuilder sb=new StringBuilder("<h1>"+map.get("title").toString()+"</h1>");
		
		//sb.append(map.get("content").toString());
		
		//sb.append(getFeedback(page));//TODO 插入回覆列表
		sb.append(setfeedback(map.get("content").toString(), page, path));//TODO 插入表單
		StringBuilder sb1=new StringBuilder(output);		
		
		int x=sb1.indexOf("eps_content.gif");//字串發生位置	
		if(x>-1){
			int z=output.indexOf(">", x);
			int y=sb1.lastIndexOf("<", x);
			sb1.delete(y, z+1);
			sb1.insert(y, sb);		
			return sb1.toString();
		}
		
		return output;
	}	
	
	/**
	 * 轉頁首
	 * @param output
	 */
	private String getHeader(String output, EpsUser user){
		WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
		DefaultManagerImpl manager = (DefaultManagerImpl) ctx.getBean("defaultManager");
		
		StringBuilder header=new StringBuilder("<html>\n\t<head>\n" +
				"\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +//編碼
				//"\t<meta name=\"author\" content=\""+user.getUid()+"\">\n"+//作者
				"\t<meta name=\"description\" content=\""+user.getSiteName()+"\">\n"+//網站標題
				"\t<meta name=\"keywords\" content=\""+user.getSiteDescript()+"\">\n"+//網站副標題
				"\t<META NAME=\"generator\" content=\"Notepad\">\n" +//編驛工具
				"\t<META NAME=\"copyright\" content=\"中華科技大學\">\n" +//版權
				"\t<META NAME=\"distribution\" content=\"ZH-tw\">\n" +//版權
				"\t<META NAME=\"robots\" content=\"all\">\n" +//版權
				"\t<title>"+user.getSiteName()+" - 中華科技大學數位化學習歷程</title>" +//版權
				"\t<!--[if gte IE 5.5000]><script type='text/javascript' src='include/Filter4Png.js'></script><![endif]--></head>\n" +
				"\t<body style='margin:0px;'>\n");
		//System.out.println(user.getpath());
		header.append(manager.sqlGetStr("SELECT et.template FROM Eps_template et, Eps_user eu " +
				"WHERE et.Oid=eu.headerStyle AND eu.Uid='"+user.getUid()+"' AND type='H'"));//加入head模版		
		return header.toString()+output;
	}
	
	/**
	 * 轉頁尾
	 * @param output
	 * @return
	 */
	private String getFoot(String output, Integer footOid){
		WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
		DefaultManagerImpl manager = (DefaultManagerImpl) ctx.getBean("defaultManager");		
		
		StringBuilder footer=new StringBuilder(manager.sqlGetStr("SELECT template FROM Eps_template WHERE Oid='"+footOid+"'"));		
		return output+footer.toString();
	}
	
	/**
	 * 取系統(校務資料庫)建立功能
	 * @param path
	 * @param tableName
	 * @param output
	 * @return
	 */
	private String getSystemFunction(String path, String tableOid, String output, String Uid){
		WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
		DefaultManagerImpl manager = (DefaultManagerImpl) ctx.getBean("defaultManager");		
		
		String tag=manager.sqlGetStr("SELECT name FROM Eps_table WHERE Oid='"+tableOid+"'");
		StringBuilder sb=new StringBuilder("<h1>"+tag+"</h1>");
		output=getSubTitle(path, tag, output);		
		List total=manager.sqlGet("SELECT ec.sequence, et.sys_table, ec.content FROM Eps_content ec, Eps_table et WHERE " +
		"et.Oid=ec.table_oid AND ec.table_oid='"+tableOid+"' AND ec.Uid='"+Uid+"' " +
		"GROUP BY ec.sequence");		
		if(total.size()>0){
			for(int i=0; i<total.size(); i++){				
				sb.append(manager.getAboutMe(((Map)total.get(i)), path));				
			}
		}		
		StringBuilder sb1=new StringBuilder(output);
		int x=sb1.indexOf("eps_content.gif");//字串發生位置	
		if(x>-1){
			int z=output.indexOf(">", x);
			int y=sb1.lastIndexOf("<", x);
			sb1.delete(y, z+1);
			sb1.insert(y, sb);		
			return sb1.toString();
		}
		//System.out.println(sb);
		return output;
	}
	
	/**
	 * 取得萬用表格內容
	 * @param path
	 * @param tableName
	 * @param output
	 * @return
	 */
	private String getTableInfo(String path, String tableOid, String output, String Uid, String free){
		WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
		DefaultManagerImpl manager = (DefaultManagerImpl) ctx.getBean("defaultManager");		
		if(free==null){
			//不自由
			String tag=manager.sqlGetStr("SELECT name FROM Eps_table WHERE Oid='"+tableOid+"'");
			StringBuilder sb=new StringBuilder("<h1>"+tag+"</h1>");		
			String key;
			String value;		
			List total=manager.sqlGet("SELECT sequence FROM Eps_content WHERE table_oid='"+tableOid+"' AND Uid='"+Uid+"' GROUP BY sequence");
			output=getSubTitle(path, tag, output);
			if(total.size()>0){
				List data;			
				for(int i=0; i<total.size(); i++){
					data=manager.sqlGet("SELECT ec.*, ef.name as fieldName FROM Eps_content ec, Eps_field ef WHERE ec.field_oid=ef.Oid AND ec.table_oid='"+tableOid+"' AND ec.Uid='"+Uid+"' AND ec.sequence='"+((Map)total.get(i)).get("sequence")+"'");
					sb.append("<h3>第"+(i+1)+"個"+tag+"</h3><ul>");
					for(int j=0; j<data.size(); j++){
						key=((Map)data.get(j)).get("fieldName").toString();
						value=((Map)data.get(j)).get("content").toString();
						sb.append("<li class='eps_li_dynamic'><b>"+key+": </b>"+value+"</li>");
					}
					sb.append("</ul>");
				}
			}
			
			StringBuilder sb1=new StringBuilder(output);
			int x=sb1.indexOf("eps_content.gif");//字串發生位置	
			if(x>-1){
				int z=output.indexOf(">", x);
				int y=sb1.lastIndexOf("<", x);
				sb1.delete(y, z+1);
				sb1.insert(y, sb);		
				return sb1.toString();
			}
		}else{
			//自由表格
			String tag=manager.sqlGetStr("SELECT tag FROM Eps_free WHERE Oid='"+tableOid+"'");
			StringBuilder sb=new StringBuilder("<h1>"+tag+"</h1>");		
			//String key;
			String value;		
			List total=manager.sqlGet("SELECT * FROM Eps_free WHERE tag='"+tag+"' AND Uid='"+Uid+"'");
			output=getSubTitle(path, tag, output);
			if(total.size()>0){
				//List data;			
				for(int i=0; i<total.size(); i++){
					//data=manager.sqlGet("SELECT ec.*, ef.name as fieldName FROM Eps_content ec, Eps_field ef WHERE ec.field_oid=ef.Oid AND ec.table_oid='"+tableOid+"' AND ec.Uid='"+Uid+"' AND ec.sequence='"+((Map)total.get(i)).get("sequence")+"'");
					sb.append("<h3>第"+(i+1)+"個"+tag+"</h3><ul>");
					for(int j=0; j<total.size(); j++){
						//key=((Map)total.get(j)).get("fieldName").toString();
						value=((Map)total.get(j)).get("content").toString();
						sb.append("<li class='eps_li_dynamic'><b>"+tag+": </b>"+value+"</li>");
					}
					sb.append("</ul>");
				}
			}
			
			StringBuilder sb1=new StringBuilder(output);
			int x=sb1.indexOf("eps_content.gif");//字串發生位置	
			if(x>-1){
				int z=output.indexOf(">", x);
				int y=sb1.lastIndexOf("<", x);
				sb1.delete(y, z+1);
				sb1.insert(y, sb);		
				return sb1.toString();
			}
			
		}
		
		return output;
	}
	
	/**
	 * 轉頭
	 * @return
	 */
	private String replaceBanner(String output, String path){
		WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
		DefaultManagerImpl manager = (DefaultManagerImpl) ctx.getBean("defaultManager");	
		StringBuilder sb=new StringBuilder(output);
		int x=sb.indexOf("eps_banner.gif");//字串發生位置
		if(x>-1){
			int z=output.indexOf(">", x);
			int y=sb.lastIndexOf("<", x);
			sb.delete(y, z+1);
			sb.insert(y, manager.sqlGetStr("SELECT banner FROM Eps_user WHERE path='"+path+"'"));
			
			return sb.toString();
		}
		return output;
	}
	
	/**
	 * 學習歷程檔案 - 選單建構
	 * @param path
	 * @param tableName
	 * @return
	 */
	private String getTableList(String path, String output, String Uid){		
		WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
		DefaultManagerImpl manager = (DefaultManagerImpl) ctx.getBean("defaultManager");		
		//找尋我的萬用菜單		
		List myTable=manager.sqlGet("SELECT ec.table_oid, efd.name FROM  Eps_content ec, " +
				"Eps_table efd WHERE efd.sys='0' AND efd.Oid=ec.table_oid AND ec.Uid='"+Uid+"' GROUP BY ec.table_oid");
		//追加自由菜單
		List myFree=manager.sqlGet("SELECT * FROM Eps_free WHERE Uid='"+Uid+"' GROUP BY tag");
		StringBuilder sb=new StringBuilder(drawHR("歷程檔案 Portfolio", "pIcon"));
		sb.append("<ul id='tag'>");
		if(myTable.size()>0){			
			for(int i=0; i<myTable.size(); i++){
				sb.append("<li class='eps_dynamic'><a href='myPortfolio?path="+path+"&table="+((Map)myTable.get(i)).get("table_oid")+"'>"+((Map)myTable.get(i)).get("name").toString()+"</a>");
			}
		}
		if(myFree.size()>0){			
			for(int i=0; i<myFree.size(); i++){
				sb.append("<li class='eps_dynamic'><a href='myPortfolio?path="+path+"&table="+((Map)myFree.get(i)).get("Oid")+"&free=1'>"+((Map)myFree.get(i)).get("title").toString()+"</a>");
			}
		}
		sb.append("</ul>");
		
		StringBuilder sb1=new StringBuilder(output);
		int x=sb1.indexOf("eps_dynamic.gif");//字串發生位置	
		if(x>-1){
			int z=output.indexOf(">", x);
			int y=sb1.lastIndexOf("<", x);
			sb1.delete(y, z+1);
			sb1.insert(y, sb);		
			return sb1.toString();
		}
		return output;
	}
	
	/**
	 * 關於我 - 選單建構
	 * @param path
	 * @param output
	 * @return
	 */
	private String getSysTableList(String path, String output, String Uid){
		WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
		DefaultManagerImpl manager = (DefaultManagerImpl) ctx.getBean("defaultManager");
		
		//找尋我的系統選單		
		List myTable=manager.sqlGet("SELECT ec.table_oid, efd.name FROM  Eps_content ec, " +
		"Eps_table efd WHERE ec.content='1' AND efd.sys='1' AND efd.Oid=ec.table_oid AND ec.Uid='"+Uid+"' GROUP BY ec.table_oid");		
		
		//裝載
		StringBuilder sb=new StringBuilder();		
		if(myTable.size()>0){
			sb.append(drawHR("關於我 About Me", "sIcon"));
			sb.append("<ul id='tag'>");
			for(int i=0; i<myTable.size(); i++){
				
				sb.append("<li class='eps_static'><a href='myPortfolio?path="+path+"&sysTable="+((Map)myTable.get(i)).get("table_oid")+"'>"+((Map)myTable.get(i)).get("name").toString()+"</a></li>");//轉成
			}
			sb.append("</ul>");	
		}			
		
		StringBuilder sb1=new StringBuilder(output);
		int x=sb1.indexOf("eps_static.gif");//字串發生位置		
		if(x>-1){
			int z=output.indexOf(">", x);
			int y=sb1.lastIndexOf("<", x);
			sb1.delete(y, z+1);
			sb1.insert(y, sb);		
			return sb1.toString();
		}
		return output;
	}
	
	/**
	 * 取個人文章標籤列表
	 * @param path
	 * @param tag
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	private String getTag(String path, String output) throws UnsupportedEncodingException{
		WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
		DefaultManagerImpl manager = (DefaultManagerImpl) ctx.getBean("defaultManager");
		
		String Uid=manager.sqlGetStr("SELECT Uid FROM Eps_user WHERE path='"+path+"'");
		
		List list=manager.sqlGet("SELECT Oid, tag, tagId FROM Eps_pages WHERE title<>'index' AND Uid='"+Uid+"' GROUP BY tag");
		StringBuilder sb=new StringBuilder("");
		if(list.size()>0){
			sb.append(drawHR("文章分類 Labels", "cIcon"));
			sb.append("<ul id='tag'>");
			//sb.append("<li>文章分類</li>");
			for(int i=0; i<list.size(); i++){
				sb.append(
						"<li class='eps_tag'>" +
							"<a href=myPortfolio?path="+path+"&tag="+java.net.URLEncoder.encode(((Map)list.get(i)).get("tag").toString(), "utf-8")+">"+((Map)list.get(i)).get("tag")+"</a>" +
						"</li>");
			}
			sb.append("</ul>");
		}
		
		StringBuilder sb1=new StringBuilder(output);
		int x=sb1.indexOf("eps_tag.gif");//字串發生位置		
		if(x>-1){
			int z=output.indexOf(">", x);
			int y=sb1.lastIndexOf("<", x);
			sb1.delete(y, z+1);
			sb1.insert(y, sb);		
			return sb1.toString();
		}
		return output;
	}
	
	/**
	 * 取最新十篇文章標題
	 * @param path
	 * @param output
	 * @return
	 */
	private String getNewTopic(String path, String output, String Uid){
		WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
		DefaultManagerImpl manager = (DefaultManagerImpl) ctx.getBean("defaultManager");
		
		List list=manager.sqlGet("SELECT Oid, title FROM Eps_pages WHERE title<>'index' AND Uid='"+Uid+"'limit 10");
		StringBuilder sb=new StringBuilder("");
		if(list.size()>0){
			sb.append(drawHR("最新文章 Top10", "nIcon"));
			sb.append("<ul id='tag'>");
			for(int i=0; i<list.size(); i++){
				sb.append("<li class='eps_new'><a href=myPortfolio?path="+path+"&page="+((Map)list.get(i)).get("Oid")+">"+((Map)list.get(i)).get("title")+"</a></li>");
			}
			sb.append("<li class='eps_new'><a href=myPortfolio?path="+path+"&tag=*>顯示全部文章</a>");
			sb.append("</ul>");
		}
		
		StringBuilder sb1=new StringBuilder(output);
		int x=sb1.indexOf("eps_new.gif");//字串發生位置		
		if(x>-1){
			int z=output.indexOf(">", x);
			int y=sb1.lastIndexOf("<", x);
			sb1.delete(y, z+1);
			sb1.insert(y, sb);		
			return sb1.toString();
		}
		return output;
	}
	
	/**
	 * 取小時鐘
	 * @param path
	 * @param output
	 * @return
	 */
	private String getTimer(String path, String output){
		WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
		DefaultManagerImpl manager = (DefaultManagerImpl) ctx.getBean("defaultManager");	
		StringBuilder sb=new StringBuilder(output);
		int x=sb.indexOf("eps_time.gif");//字串發生位置
		String timer=manager.sqlGetStr("SELECT t.template FROM Eps_user u, Eps_template t WHERE t.Oid=u.timer AND u.path='"+path+"'");
		if(x>-1){//模版上有設定小時鐘
			if(timer!=null){//沒有指定時
				if(timer.trim().equals("")){
					int z=output.indexOf(">", x);
					int y=sb.lastIndexOf("<", x);
					sb.delete(y, z+1);
					sb.insert(y, "");				
					return sb.toString();
				}else{//有指定
					int z=output.indexOf(">", x);
					int y=sb.lastIndexOf("<", x);
					sb.delete(y, z+1);
					sb.insert(y, timer);				
					return sb.toString();
				}				
			}else{//什麼也沒有
				int z=output.indexOf(">", x);
				int y=sb.lastIndexOf("<", x);
				sb.delete(y, z+1);
				sb.insert(y, "");				
				return sb.toString();
			}
		}
		return output;
	}
	
	/**
	 * 取基本資料(含照片)
	 * @param path
	 * @param output
	 * @return
	 */
	private String getUserInfo(String Uid, String output){
		WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
		DefaultManagerImpl manager = (DefaultManagerImpl) ctx.getBean("defaultManager");
		
		boolean std=true;
		String host=manager.sqlGetStr("SELECT Value FROM Parameter WHERE category='ap'");	
		
		//在學		
		Map map;
		try{
			map=manager.sqlGetMap("SELECT s.student_name, s.student_no, c.ClassName FROM stmd s, Class c WHERE s.depart_class=c.ClassNo AND student_no='"+Uid+"'");
		}catch(Exception e){
			map=manager.sqlGetMap("SELECT s.student_name, s.student_no, c.ClassName FROM Gstmd s, Class c WHERE s.depart_class=c.ClassNo AND student_no='"+Uid+"'");
		}
		
		if(map==null){//在職
			map=manager.sqlGetMap("SELECT s.Oid,s.cname as student_name, s.sname as student_no, c.name as ClassName FROM " +
					"empl s, CodeEmpl c WHERE s.unit=c.idno AND s.idno='"+Uid+"'");
			std=false;
		}
		if(map==null){//非在職
			map=manager.sqlGetMap("SELECT s.Oid,s.cname as student_name, s.sname as student_no, c.name as ClassName FROM " +
					"dempl s, CodeEmpl c WHERE s.unit=c.idno AND s.idno='"+Uid+"'");
			std=false;
		}
		
		StringBuilder sb=new StringBuilder(output);
		int x=sb.indexOf("eps_basic.gif");//字串發生位置
		StringBuilder pic=new StringBuilder();
		pic.append("<table width='100%'><tr><td width='1'><table style='border: 1px outset #333333;'><tr><td><img src=");
		
		if(std){
			pic.append("http://"+host+"/CIS/ShowImage?studentNo="+Uid);
		}else{
			try{
				pic.append(host+"/CIS/Personnel/getFTPhoto4EmplOid?Oid="+map.get("Oid"));
			}catch(Exception e){
				pic.append("images/nouser.jpg");
			}
			
		}
		
		pic.append(" width=139></td></tr></table></td></tr><tr>");
		
		try{
			pic.append(	"<td align='left' valign='top'><font size=-2>"+
					map.get("ClassName")+"<br>"+map.get("student_no")+"<br>"+
					map.get("student_name")+"</font></td></table>");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(x>-1){//模版上有設定基本資料
			int z=output.indexOf(">", x);
			int y=sb.lastIndexOf("<", x);
			sb.delete(y, z+1);
			sb.insert(y, pic);
			//System.out.println(pic);
			return sb.toString();
		}
		return output;
	}
	
	/**
	 * 計數器
	 * @param request
	 */
	private void counter(HttpServletRequest request, HttpServletResponse response, String path){
		WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
		DefaultManagerImpl manager = (DefaultManagerImpl) ctx.getBean("defaultManager");
		Cookie[] cookies = request.getCookies();
		manager.exSql("UPDATE Eps_user SET counter=counter+1 WHERE path='"+path+"'");
		if (cookies != null) {
			boolean visited=false;
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals(path)) {
					visited=true;	
				}			
			}			
			if(!visited){				
				Cookie cookie=new Cookie(path, path);
				cookie.setMaxAge(86400);
				response.addCookie(cookie);
				//manager.exSql("UPDATE counter SET day=day+1");
				//System.out.println("UPDATE Eps_user SET counter=counter+1 WHERE path='"+path+"'");
			}
		}
	}
	
	/**
	 * 分隔線
	 * @param title
	 * @return
	 */
	private String drawHR(String title, String style){
		
		StringBuilder sb=new StringBuilder("<table width='100%' cellpadding='0' cellspacing='0'><tr><td width='10' align='left' nowrap>" +
			"<hr noshade class='listHr'/></td> <td align='center'class='"+style+"' nowrap></td> <td nowrap>"+title+"</td> <td width='100%' align='left'><hr noshade class='listHr'/></td></tr></table>");
		
		title=null;		
		return sb.toString();
	}
	
	/**
	 * 建立文章表單
	 * @param title
	 * @return
	 */
	private String setfeedback(String output, String pageOid, String path){
		return output+
		
				//"<script type='text/javascript' src='ckeditor/ckeditor.js'></script>" +
				"<link href='decorate/foruser.css' rel='stylesheet' type='text/css' />" +
				
				"<table align='center' width='100%'>" +
				"<tr><td colspan='3'>"+getFeedback(pageOid)+"</td></tr>" +
				"<tr><form action='feedback' method='post'><td colspan='3' align='right'>" +
				
				//"<textarea id='content' name='content'></textarea>" +
				//"<script>CKEDITOR.replace('content',{toolbar:'Basic',skin:'office2003'});" +
				//"</script>" +
				//"<textarea id='content' name='content' onClick='this.value='''>留言...</textarea>" +
				
				"<textarea style='width:99%;' name='content' onClick='this.value=\"\"'>留言...</textarea>" +
				
				
				"</td></tr>" +
				"<tr><td align='right' width='100%'>" +
				"<input type='hidden' name='path' value='"+path+"'>" +
				"<input type='hidden' name='Oid' value='"+pageOid+"'>" +
				"<input class='username' id='username' type='text' name='username'></td><td>" +
				"<input class='password' type='password' name='password'>" +
				"</td><td><input type='submit' class='gSubmit' " +
				
				"onClick=\"if(document.getElementById('username').value==''||document.getElementById('password').value=='')" +
				"alert('帳號或密碼未填\\n\\n在學同學、教職員為資訊系統帳號密碼\\n校友帳號為學號, 密碼為身份證字號');\" " +
				
				"value='回覆'></td></tr>" +				
				"</td><td colspan='3' align='right'><input type=checkbox disabled checked><font size=-2>登入帳號密碼代表遵守學術網路規範</font></td></form></tr>" +
				"</table>";
	}
	
	/**
	 * 建立回覆表格
	 * @param pageOid
	 * @return
	 */
	private String getFeedback(String pageOid){
		WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
		DefaultManagerImpl manager = (DefaultManagerImpl) ctx.getBean("defaultManager");
		//System.out.println("SELECT * FROM Eps_feedback WHERE page='"+pageOid+"'");
		List list=manager.sqlGet("SELECT * FROM Eps_feedback WHERE page='"+pageOid+"'");		
		StringBuilder sb=new StringBuilder();
		//SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM/dd HH:mm");
		
		sb.append("<table width='100%' style='font-size:10px;'>" +
				"<tr><td width='1'><img src='images/icon/comments.gif'></td><td nowrap>全部共 <b>"+list.size()+"</b>則留言</td></tr></table>");
		for(int i=0; i<list.size(); i++){			
			//((Map)list.get(i)).put("name", manager.getUsername(((Map)list.get(i)).get("author").toString()));			
			sb.append("<table width='100%' style='font-size:10px;'>" +
					"<tr><td width='1' valign='top'><img src='images/icon/comment.gif'></td><td nowrap valign='top'>");			
			sb.append(manager.getUsername(((Map)list.get(i)).get("author").toString()));
			sb.append(((Map)list.get(i)).get("postime").toString().substring(5, 17));
			sb.append("</td><td width='100%' valign='top'>");
			sb.append(((Map)list.get(i)).get("content").toString());
			sb.append("</td></tr></table>");
		}
		//output=output+sb.toString();
		return sb.toString();
	}
	
	/**
	 * 建立投票連結(如果有參與的話)
	 * TODO 日期時間 
	 * @return
	 */
	private String putVotePath(String path, String output){
		WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
		DefaultManagerImpl manager = (DefaultManagerImpl) ctx.getBean("defaultManager");
		
		//現在是否有活動
		//Eps_Act_parameter
		SimpleDateFormat sf=new SimpleDateFormat("yyyy/MM/dd");
		String date=sf.format(new Date());
		//System.out.println("SELECT COUNT(*) FROM Eps_Act_parameter WHERE sign_start<='"+date+"' AND sign_end>='"+date+"'");
		List list=manager.sqlGet("SELECT Oid FROM Eps_Act_parameter WHERE sign_start<='"+date+"' AND judge_end>='"+date+"'");
		if(list.size()<1){
			return output;
		}
		
		//是否有參加
		for(int i=0; i<list.size(); i++){
			//System.out.println("SELECT COUNT(*)FROM Eps_Act_user WHERE act_oid='"+((Map)list.get(i)).get("Oid")+"' AND Uid='"+Uid+"'");
			if(manager.sqlGetInt("SELECT COUNT(*)FROM Eps_Act_user WHERE act_oid='"+((Map)list.get(i)).get("Oid")+"' AND " +
					"Uid='"+manager.sqlGetStr("SELECT Uid FROM Eps_user WHERE path='"+path+"'")+"'")>0){				
				StringBuilder sb=new StringBuilder(output);		
				sb.append("<table style='position:absolute; top:10px; right:10px; z-index:1;'><tr><td>" +
						"<a href='/portfolio/vote.jspx?path="+path+"&act_oid="+((Map)list.get(i)).get("Oid")+"' target='_blank'><img border='0' src='images/vote.png'/></a></td></tr></table>");				
				return sb.toString();
			}
		}
		return output;
	}
	
}
