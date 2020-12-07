package service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import dao.HibernateDAO;
import dao.JdbcDAO;

/**
 * @author JOHN
 *
 */
public class DefaultManagerImpl {

	private JdbcDAO	jdbcDao;
	private HibernateDAO hibernateDAO;

	public JdbcDAO getJdbcDao() {
		return jdbcDao;
	}

	public void setJdbcDao(JdbcDAO jdbcDao) {
		this.jdbcDao = jdbcDao;
	}	
	
	public HibernateDAO getHibernateDAO() {
		return hibernateDAO;
	}

	public void setHibernateDAO(HibernateDAO hibernateDAO) {
		this.hibernateDAO = hibernateDAO;
	}
	
	/**
	 * 主要連線 C3P0 SQL得 整個查詢列表
	 * @param sql
	 * @return List of Map
	 */
	public List sqlGet(String sql) {
		return jdbcDao.StandardSqlQuery(sql);
	}
	
	/**
	 * C3P0 SQL執行
	 */
	public void exSql(String sql) {
		jdbcDao.exQuery(sql);		
	}
	
	/**
	 * C3P0 SQL得 1個數字
	 */
	public int sqlGetInt(String sql) {		
		return jdbcDao.sqlGetInt(sql);
	}
	
	/**
	 * C3P0 SQL得 1筆記錄
	 */
	public Map sqlGetMap(String sql) {		
		return jdbcDao.sqlGetMap(sql);
	}
	
	public String sqlGetStr(String sql){
		return jdbcDao.sqlGetString(sql);
	}
	
	/**
	 * C3P0 HQL得 1個列表
	 */
	public List hqlGetListBy(String hql) {
		return hibernateDAO.submitQuery(hql);
	}
	
	/**
	 * C3P0 更新 1個po
	 */
	public void update(Object po) {
		hibernateDAO.saveObject(po);
	}
	
	/**
	 * 現在學期
	 * @return
	 */
	public int getSchoolTerm(){		
		return jdbcDao.sqlGetInt("SELECT Value FROM Parameter WHERE Name='School_term'");
	}
	
	/**
	 * 現在學年
	 * @return
	 */
	public int getSchoolYear(){		
		return jdbcDao.sqlGetInt("SELECT Value FROM Parameter WHERE Name='School_year'");
	}
	
	/**
	 * 關於我的資訊
	 * @param map
	 * @param table
	 * @param Uid
	 * @return
	 */
	public String getAboutMe(Map map, String Uid){
		StringBuilder sb=new StringBuilder();
		//String path=request.getParameter("path");//網頁路徑
		Uid=sqlGetStr("SELECT Uid FROM Eps_user WHERE path='"+Uid+"'");
		try{
			sb.append("<ul>");
			//基本資料
			if(map.get("sys_table").equals("stmd")&&map.get("content").equals("1")){
				
				Map data=jdbcDao.sqlGetMap("SELECT s.student_no, s.student_name, s.birthday, s.Email, c.ClassName, d.fname, " +
						"d.school_name FROM(stmd s LEFT OUTER JOIN Class c ON s.depart_class=c.ClassNo) LEFT OUTER JOIN dept d ON c.Dept=d.no " +
						"WHERE s.student_no='"+Uid+"'");
				if(data==null){
					data=jdbcDao.sqlGetMap("SELECT s.student_no, s.student_name, s.birthday, s.Email, c.ClassName, d.fname, " +
							"d.school_name FROM(Gstmd s LEFT OUTER JOIN Class c ON s.depart_class=c.ClassNo) LEFT OUTER JOIN dept d ON c.Dept=d.no " +
							"WHERE s.student_no='"+Uid+"'");
				}
				sb.append( "<li class='eps_li_static'><b>部制:</b> "+data.get("school_name")+"</li>");	
				sb.append( "<li class='eps_li_static'><b>科系(所):</b> "+data.get("fname")+"</li>");
				sb.append( "<li class='eps_li_static'><b>就讀班級:</b> "+data.get("ClassName")+"</li>");
				sb.append( "<li class='eps_li_static'><b>學號 :</b> "+data.get("student_no")+"</li>");	
				sb.append( "<li class='eps_li_static'><b>姓名 :</b> "+data.get("student_name")+"</li>");	
				sb.append( "<li class='eps_li_static'><b>出生日期 :</b> "+data.get("birthday")+"</li>");	
				sb.append( "<li class='eps_li_static'><b>電子郵件 :</b> "+data.get("Email")+"</li>");	
				sb.append("</ul>");
				return sb.toString();
			}			
			
			//選課資料
			if(map.get("sys_table").equals("Seld") && map.get("content").equals("1")){				
				List data=jdbcDao.StandardSqlQuery("SELECT c.chi_name, c.cscode, d.credit, d.thour " +
				"FROM Seld s, Csno c, Dtime d WHERE s.Dtime_oid=d.Oid AND c.cscode=d.cscode AND " +
				"s.student_no='"+Uid+"' AND d.Sterm='"+getSchoolTerm()+"'");
				
				for(int i=0;i<data.size(); i++){
					sb.append("<h3>"+((Map)data.get(i)).get("chi_name")+"</h3>");
					sb.append("<ul>");
					sb.append( "<li class='eps_li_static'><b>課程名稱:</b> "+((Map)data.get(i)).get("cscode")+"</li>");	
					sb.append( "<li class='eps_li_static'><b>課程代碼:</b> "+((Map)data.get(i)).get("chi_name")+"</li>");
					sb.append( "<li class='eps_li_static'><b>學分:</b> "+((Map)data.get(i)).get("credit")+"</li>");
					sb.append( "<li class='eps_li_static'><b>時數:</b> "+((Map)data.get(i)).get("thour")+"</li>");
					sb.append("</ul>");
				}				
				return sb.toString();
			}
			
			//操行
			if(map.get("sys_table").equals("Savejust") && map.get("content").equals("1")){				
				List data=jdbcDao.StandardSqlQuery("SELECT j.* FROM stmd s, Savejust j WHERE " +
						"j.student_no=s.student_no AND s.student_no='"+Uid+"'");
				if(data==null){
					data=jdbcDao.StandardSqlQuery("SELECT j.* FROM Gstmd s, Savejust j WHERE " +
							"j.student_no=s.student_no AND s.student_no='"+Uid+"'");
				}				
				for(int i=0;i<data.size(); i++){
					sb.append("<h3>"+((Map)data.get(i)).get("school_year")+"學年第"+((Map)data.get(i)).get("school_term")+"學期</h3>");
					sb.append("<ul>");					
					sb.append( "<li class='eps_li_static'><b>評語:</b> "+((Map)data.get(i)).get("com_name1")+"</li>");
					if(((Map)data.get(i)).get("com_name2")!=null && !((Map)data.get(i)).get("com_name2").equals(""))
					sb.append( "<li class='eps_li_static'><b>評語2:</b> "+((Map)data.get(i)).get("com_name2")+"</li>");
					if(((Map)data.get(i)).get("com_name3")!=null && !((Map)data.get(i)).get("com_name3").equals(""))
					sb.append( "<li class='eps_li_static'><b>評語3:</b> "+((Map)data.get(i)).get("com_name3")+"</li>");
					sb.append( "<li class='eps_li_static'><b>成績:</b> "+((Map)data.get(i)).get("total_score")+"</li>");
					sb.append("</ul>");					
				}				
				return sb.toString();
			}
			
			//課表
			if(map.get("sys_table").equals("Table") && map.get("content").equals("1")){
				sb.append("<b>BETA</b><br><table style='border-color:#000000;border-style:solid;border-width:1px;border-spacing: 0;border-collapse: collapse;'>");
				String tmp;
				for(int i=1; i<=15; i++){
					sb.append("<tr height=30>");				
					for(int j=1; j<=7; j++){
						tmp=sqlGetStr("SELECT c.chi_name FROM Dtime d, Dtime_class dc, Seld s, Csno c WHERE " +
								"d.Oid=dc.Dtime_oid AND d.Oid=s.Dtime_oid AND c.cscode=d.cscode AND d.Sterm='"+getSchoolTerm()+"' AND " +
								"s.student_no='"+Uid+"' AND dc.week='"+j+"' AND (dc.begin<="+i+" && dc.end>="+i+")");
						if(tmp!=null){
							sb.append("<td style='margin:0;border-width:1px;border-style:solid;' width=14%><font size=-2>"+tmp);
						}else{
							sb.append("<td style='margin:0;border-width:1px;border-style:solid;' width=14%><font size=-2>");
						}
													
						
						sb.append("</font></td>");						
					}
					sb.append("</tr>");
					
				}
				sb.append("</table>");				
				return sb.toString();
			}
			
			//歷年成績
			if(map.get("sys_table").equals("ScoreHist") && map.get("content").equals("1")){
				List data=jdbcDao.StandardSqlQuery("SELECT sh.school_year, sh.school_term, c.chi_name, sh.opt, " +
				"sh.credit, sh.evgr_type, sh.score FROM ScoreHist sh, stmd s, Csno c WHERE c.cscode=sh.cscode AND " +
				"sh.student_no=s.student_no AND s.student_no='"+Uid+"' ORDER BY sh.school_year, sh.school_term");
				if(data.size()<1){
					data=jdbcDao.StandardSqlQuery("SELECT sh.school_year, sh.school_term, c.chi_name, sh.opt, " +
					"sh.credit, sh.evgr_type, sh.score FROM ScoreHist sh, Gstmd s, Csno c WHERE c.cscode=sh.cscode AND " +
					"sh.student_no=s.student_no AND s.student_no='"+Uid+"' ORDER BY sh.school_year, sh.school_term");
				}
						
				for(int i=0;i<data.size(); i++){
					sb.append("<h3>"+((Map)data.get(i)).get("chi_name")+"</h3>");
					sb.append("<ul>");
					sb.append( "<li class='eps_li_static'><b>學年:</b> "+((Map)data.get(i)).get("school_year")+"</li>");
					sb.append( "<li class='eps_li_static'><b>學期:</b> "+((Map)data.get(i)).get("school_term")+"</li>");					
					sb.append( "<li class='eps_li_static'><b>課程名稱:</b> "+((Map)data.get(i)).get("chi_name")+"</li>");
					sb.append( "<li class='eps_li_static'><b>選別:</b> "+((Map)data.get(i)).get("opt")+"</li>");
					sb.append( "<li class='eps_li_static'><b>學分:</b> "+((Map)data.get(i)).get("credit")+"</li>");
					sb.append( "<li class='eps_li_static'><b>成績:</b> "+((Map)data.get(i)).get("score")+"</li>");
					sb.append( "<li class='eps_li_static'><b>備註:</b> "+((Map)data.get(i)).get("evgr_type")+"</li>");					
					
					sb.append("</ul>");
				}				
				return sb.toString();
			}
			
			//獎懲
			if(map.get("sys_table").equals("Savedesd") && map.get("content").equals("1")){				
				List data=jdbcDao.StandardSqlQuery("SELECT d.school_year, d.school_term, c.name as kind1, " +
							"d.cnt1, c1.name as kind2, d.cnt2, c2.name FROM stmd s, ((Savedesd d LEFT OUTER JOIN code5 c ON " +
							"c.category='BonusPenalty' AND c.idno=d.kind1) LEFT OUTER JOIN code5 c1 ON c1.category='BonusPenalty' " +
							"AND c1.idno=d.kind2) LEFT OUTER JOIN code2 c2 ON c2.no=d.reason WHERE s.student_no=d.student_no AND s.student_no='"+Uid+"'");
				if(data==null){
					data=jdbcDao.StandardSqlQuery("SELECT d.school_year, d.school_term, c.name as kind1, " +
							"d.cnt1, c1.name as kind2, d.cnt2, c2.name FROM Gstmd s, ((Savedesd d LEFT OUTER JOIN code5 c ON " +
							"c.category='BonusPenalty' AND c.idno=d.kind1) LEFT OUTER JOIN code5 c1 ON c1.category='BonusPenalty' " +
							"AND c1.idno=d.kind2) LEFT OUTER JOIN code2 c2 ON c2.no=d.reason WHERE s.student_no=d.student_no AND s.student_no='"+Uid+"'");
				}				
				for(int i=0;i<data.size(); i++){
					sb.append("<h3>"+((Map)data.get(i)).get("school_year")+"學年 第"+((Map)data.get(i)).get("school_term")+"學期</h3>");
					sb.append("<ul>");
					//sb.append( "<li class='eps_li_static'><b>學年:</b> "+((Map)data.get(i)).get("school_year")+"</li>");
					//sb.append( "<li class='eps_li_static'><b>學期:</b> "+((Map)data.get(i)).get("school_term")+"</li>");					
					sb.append( "<li class='eps_li_static'><b>種類:</b> "+((Map)data.get(i)).get("kind1")+"</li>");
					sb.append( "<li class='eps_li_static'><b>次數:</b> "+((Map)data.get(i)).get("cnt1")+"</li>");
					if(((Map)data.get(i)).get("kind2")!=null && !((Map)data.get(i)).get("kind2").equals(""))
					sb.append( "<li class='eps_li_static'><b>種類:</b> "+((Map)data.get(i)).get("kind2")+"</li>");
					if(((Map)data.get(i)).get("cnt2")!=null && !((Map)data.get(i)).get("cnt2").equals(""))
					sb.append( "<li class='eps_li_static'><b>次數:</b> "+((Map)data.get(i)).get("cnt2")+"</li>");
					
					sb.append( "<li class='eps_li_static'><b>原因:</b> "+((Map)data.get(i)).get("name")+"</li>");					
					
					sb.append("</ul>");
				}				
				return sb.toString();
			}
			
			//學程
			if(map.get("sys_table").equals("CsGroup") && map.get("content").equals("1")){
				
				Map aStudent=sqlGetMap("SELECT st.*, c.*, c5.name as schoolName, c51.name as deptName FROM " +
						"((stmd st LEFT JOIN Class c ON st.depart_class=c.ClassNo)LEFT JOIN " +
						"code5 c5 ON c5.idno=c.SchoolNo)LEFT JOIN code5 c51 ON c51.idno=c.DeptNo WHERE " +
						"c5.category='School' AND c51.category='Dept' AND st.student_no='"+Uid+"'");
				if(aStudent==null){
					aStudent=sqlGetMap("SELECT st.*, c.*, c5.name as schoolName, c51.name as deptName FROM " +
							"((Gstmd st LEFT JOIN Class c ON st.depart_class=c.ClassNo)LEFT JOIN " +
							"code5 c5 ON c5.idno=c.SchoolNo)LEFT JOIN code5 c51 ON c51.idno=c.DeptNo WHERE " +
							"c5.category='School' AND c51.category='Dept' AND st.student_no='"+Uid+"'");
				}
				
				List data=CsGroup4One(Uid, aStudent, true);
				for(int i=0;i<data.size(); i++){					
					sb.append("<h3>"+((Map)data.get(i)).get("cname")+"</h3>");
					sb.append("應修必修學分:"+((Map)data.get(i)).get("major")+", ");
					sb.append("應修選修學分:"+((Map)data.get(i)).get("minor")+", ");
					sb.append("應修外系學分:"+((Map)data.get(i)).get("outdept"));
					sb.append("<ul>");
					if(((Map)data.get(i)).get("opt1")!=null){
						sb.append( "<li class='eps_li_static'><b>已得必修學分:</b>"+((Map)data.get(i)).get("opt1")+"</li>");
					}else{
						sb.append("<li class='eps_li_static'><b>已得必修學分:</b>0.0</li>");
					}
					if(((Map)data.get(i)).get("opt2")!=null){
						sb.append( "<li class='eps_li_static'><b>已得選修學分:</b>"+((Map)data.get(i)).get("opt2")+"</li>");
					}else{
						sb.append("<li class='eps_li_static'><b>已得選修學分:</b>0.0</li>");
					}
					if(((Map)data.get(i)).get("outOpt")!=null){
						sb.append( "<li class='eps_li_static'><b>已得外系學分:</b>"+((Map)data.get(i)).get("outOpt")+"</li>");
					}else{
						sb.append("<li class='eps_li_static'><b>已得外系學分:</b>0.0</li>");
					}
					sb.append("</ul>");
					
				}				
				return sb.toString();
			}
			
			//核心能力
			if(map.get("sys_table").equals("CsCore") && map.get("content").equals("1")){
				
				//sb.append("test");
				return 
				"<table width='100%'><tr><td>" +
				"<OBJECT classid='clsid:D27CDB6E-AE6D-11cf-96B8-444553540000' wmode='transparent'"+
				"			 codebase='http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0'"+
				"			 WIDTH='100%' HEIGHT='280' id='charts' ALIGN='center'>"+
				"			<param name='wmode' value='transparent' />"+
				"			<PARAM NAME=movie VALUE=http://ap.cust.edu.tw/CIS/pages/course/export/charts.swf?library_path=http://ap.cust.edu.tw/CIS/pages/course/export/charts_library/&xml_source=http://ap.cust.edu.tw/CIS/Print/course/StudentChartPlus.do?student_no="+Uid+"&<%=(int)(Math.random()*49)%>'>"+
				"			<PARAM NAME=quality VALUE=high>"+
				"			<PARAM NAME=bgcolor VALUE=#f0fcd7>"+
				"			<EMBED src='course/export/charts.swf?library_path=http://ap.cust.edu.tw/CIS/pages/course/export/charts_library/&xml_source=http://ap.cust.edu.tw/CIS/Print/registration/StudentChartPlus.do?student_no="+Uid+"&<%=(int)(Math.random()*49)%>'"+
				"			quality=high bgcolor=#cccccc  WIDTH='100%' HEIGHT='350' NAME='charts' ALIGN='center'"+
				"			TYPE='application/x-shockwave-flash' PLUGINSPAGE='http://www.macromedia.com/go/getflashplayer'></EMBED>"+
				"		</OBJECT>"+
				"		"+
				"		"+
				"		</td>"+
				"		</tr><tr>"+
				"		<td width='50%' class='hairLineTdF'>"+
				"		<OBJECT classid='clsid:D27CDB6E-AE6D-11cf-96B8-444553540000' wmode='transparent'"+
				"			 codebase='http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0'"+
				"			 WIDTH='100%' HEIGHT='280' id='charts' ALIGN='center'>"+
				"			<param name='wmode' value='transparent' />"+
				"			<PARAM NAME=movie VALUE=http://ap.cust.edu.tw/CIS/pages/course/export/charts.swf?library_path=http://ap.cust.edu.tw/CIS/pages/course/export/charts_library/&xml_source=http://ap.cust.edu.tw/CIS/Print/course/StudentChartNoPlus.do?student_no="+Uid+"&<%=(int)(Math.random()*49)%>'>"+
				"			<PARAM NAME=quality VALUE=high>"+
				"			<PARAM NAME=bgcolor VALUE=#f0fcd7>"+
				"			<EMBED src='course/export/charts.swf?library_path=http://ap.cust.edu.tw/CIS/pages/course/export/charts_library/&xml_source=http://ap.cust.edu.tw/CIS/Print/registration/StudentChartNoPlus.do?student_no="+Uid+"&<%=(int)(Math.random()*49)%>'"+
				"			quality=high bgcolor=#cccccc  WIDTH='100%' HEIGHT='350' NAME='charts' ALIGN='center'"+
				"			TYPE='application/x-shockwave-flash' PLUGINSPAGE='http://www.macromedia.com/go/getflashplayer'></EMBED>"+
				"		</OBJECT>" +
				"</td></tr></table>";
			}
			
			
		}catch(Exception e){			
			sb.append("<li>資料有誤</li></ul>");
		}
		
		sb.append("<ul>");
		
		//基本資料
		if(map.get("sys_table").equals("empl")&&map.get("content").equals("1")){
			
			Map data=jdbcDao.sqlGetMap("SELECT cname, bdate, Email, sname FROM empl WHERE idno='"+Uid+"'");						
			//System.out.println("SELECT cname, bdate, Email, sname FROM empl WHERE idno='"+Uid+"'");
			sb.append( "<li class='eps_li_static'><b>姓名 :</b> "+data.get("cname")+"</li>");
			sb.append( "<li class='eps_li_static'><b>職稱 :</b> "+data.get("sname")+"</li>");
			sb.append( "<li class='eps_li_static'><b>出生日期 :</b> "+data.get("bdate")+"</li>");	
			sb.append( "<li class='eps_li_static'><b>電子郵件 :</b> "+data.get("Email")+"</li>");	
			sb.append("</ul>");
			return sb.toString();
		}
		
		
		//學術活動
		if(map.get("sys_table").equals("Rcact")&&map.get("content").equals("1")){							
			List list=jdbcDao.StandardSqlQuery("SELECT school_year,actname,sponoff,kindid,typeid,placeid,joinid," +
					"bdate,edate,hour,certno,schspon,certyn,lastModified,intor,approve,approveTemp FROM Rcact WHERE idno='"+Uid+"'");			
			Map rcact;
			for(int i=0; i<list.size(); i++){
				rcact=((Map)list.get(i));
				sb.append("<ul>");
				/*
				for (Iterator iter = rcact.entrySet().iterator(); iter.hasNext();){
					 Map.Entry entry = (Map.Entry)iter.next();  
				     String key = (String)entry.getKey();  
				     //String value = (String)rcact.get(key);
				     //System.out.println(key);
				     sb.append( "<li class='eps_li_static'><b>"+key+" :</b> "+rcact.get(key)+"</li>");				     
				}
				*/
				sb.append( "<li class='eps_li_static'><b>學年度:</b> "+rcact.get("school_year")+"</li>");
				sb.append( "<li class='eps_li_static'><b>活動名稱:</b> "+rcact.get("actname")+"</li>");
				sb.append( "<li class='eps_li_static'><b>主辦單位:</b> "+rcact.get("sponoff")+"</li>");
				sb.append( "<li class='eps_li_static'><b>活動種類:</b> "+rcact.get("kindid")+"</li>");
				//sb.append( "<li class='eps_li_static'><b> :</b> "+rcact.get("typeid")+"</li>");
				//sb.append( "<li class='eps_li_static'><b> :</b> "+rcact.get("placeid")+"</li>");
				//sb.append( "<li class='eps_li_static'><b> :</b> "+rcact.get("joinid")+"</li>");
				sb.append( "<li class='eps_li_static'><b>開始日期:</b> "+rcact.get("bdate")+"</li>");
				sb.append( "<li class='eps_li_static'><b>結束日期:</b> "+rcact.get("edate")+"</li>");
				sb.append( "<li class='eps_li_static'><b>活動時數:</b> "+rcact.get("hour")+"</li>");
				sb.append( "<li class='eps_li_static'><b>證書字號:</b> "+rcact.get("certno")+"</li>");
				sb.append( "<li class='eps_li_static'><b>學校補助:</b> "+rcact.get("schspon")+"</li>");
				sb.append( "<li class='eps_li_static'><b>研習證明:</b> "+rcact.get("certyn")+"</li>");
				
				
				sb.append( "<li class='eps_li_static'><b>綱要簡介:</b> "+rcact.get("intor")+"</li>");
				
				
				
				sb.append("</ul>");
			}			
			return sb.toString();
		}
		

		//期刊論文
		if(map.get("sys_table").equals("Rcjour")&&map.get("content").equals("1")){					
			List list=jdbcDao.StandardSqlQuery("SELECT school_year,projno,title,authorno,kindid,jname," +
					"volume,period,pmonth,pyear,lastModified,intor,COM_authorno,type,place,approve,approveTemp FROM Rcjour WHERE idno='"+Uid+"'");
			Map rcact;
			for(int i=0; i<list.size(); i++){
				rcact=((Map)list.get(i));
				sb.append("<ul>");
				/*
				for (Iterator iter = rcact.entrySet().iterator(); iter.hasNext();){
					 Map.Entry entry = (Map.Entry)iter.next();  
				     String key = (String)entry.getKey();  
				     //String value = (String)rcact.get(key);
				     //System.out.println(key);
				     sb.append( "<li class='eps_li_static'><b>"+key+" :</b> "+rcact.get(key)+"</li>");				     
				}
				*/
				
				sb.append( "<li class='eps_li_static'><b>學年度:</b> "+rcact.get("school_year")+"</li>");
				sb.append( "<li class='eps_li_static'><b>計畫案號:</b> "+rcact.get("projno")+"</li>");
				sb.append( "<li class='eps_li_static'><b>論文名稱:</b> "+rcact.get("title")+"</li>");
				sb.append( "<li class='eps_li_static'><b>作者順序:</b> "+rcact.get("authorno")+"</li>");
				//sb.append( "<li class='eps_li_static'><b> :</b> "+rcact.get("kindid")+"</li>");
				sb.append( "<li class='eps_li_static'><b>期刊名稱:</b> "+rcact.get("jname")+"</li>");
				sb.append( "<li class='eps_li_static'><b>發表卷數:</b> "+rcact.get("volume")+"</li>");
				sb.append( "<li class='eps_li_static'><b>發表期數:</b> "+rcact.get("period")+"</li>");
				sb.append( "<li class='eps_li_static'><b>發表月份:</b> "+rcact.get("pmonth")+"</li>");
				sb.append( "<li class='eps_li_static'><b>發表年份:</b> "+rcact.get("pyear")+"</li>");
				
				
				//sb.append( "<li class='eps_li_static'><b> :</b> "+rcact.get("lastModified")+"</li>");
				sb.append( "<li class='eps_li_static'><b>摘要:</b> "+rcact.get("intor")+"</li>");
				sb.append( "<li class='eps_li_static'><b>通訊作者:</b> "+rcact.get("COM_authorno")+"</li>");
				sb.append( "<li class='eps_li_static'><b>發表形式:</b> "+rcact.get("type")+"</li>");
				sb.append( "<li class='eps_li_static'><b>發表地點:</b> "+rcact.get("place")+"</li>");
				//sb.append( "<li class='eps_li_static'><b> :</b> "+rcact.get("approve")+"</li>");
				//sb.append( "<li class='eps_li_static'><b> :</b> "+rcact.get("approveTemp")+"</li>");
				sb.append("</ul>");
				sb.append("<br><br>");
			}			
			return sb.toString();
		}
		

		//研討會論文
		if(map.get("sys_table").equals("Rcconf")&&map.get("content").equals("1")){					
			List list=jdbcDao.StandardSqlQuery("SELECT school_year,projno,title,authorno,jname,nation,city," +
					"bdate,edate,pyear,lastModified,intor,COM_authorno,approve,approveTemp FROM Rcconf WHERE idno='"+Uid+"'");
			Map rcact;
			for(int i=0; i<list.size(); i++){
				rcact=((Map)list.get(i));
				sb.append("<ul>");
				/*
				for (Iterator iter = rcact.entrySet().iterator(); iter.hasNext();){
					 Map.Entry entry = (Map.Entry)iter.next();  
				     String key = (String)entry.getKey();  
				     //String value = (String)rcact.get(key);
				     //System.out.println(key);
				     sb.append( "<li class='eps_li_static'><b>"+key+" :</b> "+rcact.get(key)+"</li>");				     
				}
				*/
				//sb.append( "<li class='eps_li_static'><b> :</b> "+rcact.get("idno")+"</li>");
				sb.append( "<li class='eps_li_static'><b>學年度:</b> "+rcact.get("school_year")+"</li>");
				sb.append( "<li class='eps_li_static'><b>計畫案號:</b> "+rcact.get("projno")+"</li>");
				sb.append( "<li class='eps_li_static'><b>論文名稱:</b> "+rcact.get("title")+"</li>");
				sb.append( "<li class='eps_li_static'><b>作者順序:</b> "+rcact.get("authorno")+"</li>");
				sb.append( "<li class='eps_li_static'><b>研討會名稱:</b> "+rcact.get("jname")+"</li>");
				sb.append( "<li class='eps_li_static'><b>舉辦國家:</b> "+rcact.get("nation")+"</li>");
				sb.append( "<li class='eps_li_static'><b>舉辦城市:</b> "+rcact.get("city")+"</li>");
				sb.append( "<li class='eps_li_static'><b>開始日期:</b> "+rcact.get("bdate")+"</li>");
				sb.append( "<li class='eps_li_static'><b>結束日期:</b> "+rcact.get("edate")+"</li>");
				sb.append( "<li class='eps_li_static'><b>發表年:</b> "+rcact.get("pyear")+"</li>");
				
				
				sb.append( "<li class='eps_li_static'><b>簡介:</b> "+rcact.get("intor")+"</li>");
				sb.append( "<li class='eps_li_static'><b>通訊作者:</b> "+rcact.get("COM_authorno")+"</li>");
				
				sb.append("</ul>");
				sb.append("<br><br>");
			}			
			return sb.toString();
		}
		
		
		
		
		//出版書籍
		if(map.get("sys_table").equals("Rcbook")&&map.get("content").equals("1")){					
			List list=jdbcDao.StandardSqlQuery("SELECT * FROM Rcbook WHERE idno='"+Uid+"'");
			Map rcact;
			for(int i=0; i<list.size(); i++){
				rcact=((Map)list.get(i));
				sb.append("<ul>");
				sb.append( "<li class='eps_li_static'><b>學年度:</b> "+rcact.get("school_year")+"</li>");
				sb.append( "<li class='eps_li_static'><b>計畫案號:</b> "+rcact.get("projno")+"</li>");
				sb.append( "<li class='eps_li_static'><b>專書名稱:</b> "+rcact.get("title")+"</li>");
				sb.append( "<li class='eps_li_static'><b>作者順序:</b> "+rcact.get("authorno")+"</li>");
				sb.append( "<li class='eps_li_static'><b>使用語言:</b> "+rcact.get("language")+"</li>");
				sb.append( "<li class='eps_li_static'><b>出版日期:</b> "+rcact.get("pdate")+"</li>");
				sb.append( "<li class='eps_li_static'><b>出版社:</b> "+rcact.get("publisher")+"</li>");
				sb.append( "<li class='eps_li_static'><b>isbn:</b> "+rcact.get("isbn")+"</li>");
				sb.append( "<li class='eps_li_static'><b>通訊作者:</b> "+rcact.get("COM_authorno")+"</li>");
				sb.append( "<li class='eps_li_static'><b>專書類別:</b> "+rcact.get("type")+"</li>");
				sb.append( "<li class='eps_li_static'><b>摘要:</b> "+rcact.get("intor")+"</li>");
				
				sb.append("</ul>");
				sb.append("<br><br>");
			}			
			return sb.toString();
		}
		
		
		
		
		
		
		
		//取得專利Rcpet
		if(map.get("sys_table").equals("Rcpet")&&map.get("content").equals("1")){					
			List list=jdbcDao.StandardSqlQuery("SELECT * FROM Rcpet WHERE idno='"+Uid+"'");
			Map rcact;
			for(int i=0; i<list.size(); i++){
				rcact=((Map)list.get(i));
				sb.append("<ul>");
				sb.append( "<li class='eps_li_static'><b>學年度:</b> "+rcact.get("school_year")+"</li>");
				sb.append( "<li class='eps_li_static'><b>計畫案號:</b> "+rcact.get("projno")+"</li>");
				sb.append( "<li class='eps_li_static'><b>專利名稱:</b> "+rcact.get("title")+"</li>");
				sb.append( "<li class='eps_li_static'><b>作者順序:</b> "+rcact.get("authorno")+"</li>");
				sb.append( "<li class='eps_li_static'><b>專利區域:</b> "+rcact.get("area")+"</li>");
				sb.append( "<li class='eps_li_static'><b>專利類型:</b> "+rcact.get("petType")+"</li>");
				sb.append( "<li class='eps_li_static'><b>進度狀況:</b> "+rcact.get("schedule")+"</li>");
				sb.append( "<li class='eps_li_static'><b>申請日期:</b> "+rcact.get("bdate")+"</li>");
				sb.append( "<li class='eps_li_static'><b>終止日期:</b> "+rcact.get("edate")+"</li>");
				sb.append( "<li class='eps_li_static'><b>發照機關:</b> "+rcact.get("inst")+"</li>");
				sb.append( "<li class='eps_li_static'><b>證書字號:</b> "+rcact.get("certno")+"</li>");
				
				sb.append( "<li class='eps_li_static'><b>授權移轉:</b> "+rcact.get("depute")+"</li>");
				
				sb.append( "<li class='eps_li_static'><b>權利人:</b> "+rcact.get("proposer")+"</li>");
				sb.append( "<li class='eps_li_static'><b>技術報告分數:</b> "+rcact.get("score")+"</li>");
				//sb.append( "<li class='eps_li_static'><b>權利類:</b> "+rcact.get("proposerType")+"</li>");
				sb.append( "<li class='eps_li_static'><b>授權金額:</b> "+rcact.get("deputeMoney")+"</li>");
				sb.append( "<li class='eps_li_static'><b>授權日期:</b> "+rcact.get("deputeSdate")+"</li>");
				sb.append( "<li class='eps_li_static'><b>授權停止日期:</b> "+rcact.get("deputeEdate")+"</li>");				
				sb.append( "<li class='eps_li_static'><b>簡介:</b> "+rcact.get("intor")+"</li>");
				
				sb.append("</ul>");
				sb.append("<br><br>");
			}			
			return sb.toString();
		}
		
		
		
		
		
		//產學活動Rcproj
		if(map.get("sys_table").equals("Rcproj")&&map.get("content").equals("1")){					
			List list=jdbcDao.StandardSqlQuery("SELECT * FROM Rcproj WHERE idno='"+Uid+"'");
			Map rcact;
			for(int i=0; i<list.size(); i++){
				rcact=((Map)list.get(i));
				sb.append("<ul>");
				sb.append( "<li class='eps_li_static'><b>學年度:</b> "+rcact.get("school_year")+"</li>");
				sb.append( "<li class='eps_li_static'><b>專案案號:</b> "+rcact.get("projno")+"</li>");
				sb.append( "<li class='eps_li_static'><b>專案名稱:</b> "+rcact.get("projname")+"</li>");
				sb.append( "<li class='eps_li_static'><b>開始日期:</b> "+rcact.get("bdate")+"</li>");
				sb.append( "<li class='eps_li_static'><b>結束日期:</b> "+rcact.get("edate")+"</li>");
				sb.append( "<li class='eps_li_static'><b>研究金額:</b> "+rcact.get("money")+"</li>");
				sb.append( "<li class='eps_li_static'><b>政府出資金額:</b> "+rcact.get("G_money")+"</li>");
				sb.append( "<li class='eps_li_static'><b>企業出資金額:</b> "+rcact.get("B_money")+"</li>");
				sb.append( "<li class='eps_li_static'><b>其他單位出資金額:</b> "+rcact.get("O_money")+"</li>");
				sb.append( "<li class='eps_li_static'><b>學校出資金額:</b> "+rcact.get("S_money")+"</li>");
				sb.append( "<li class='eps_li_static'><b>單位名稱:</b> "+rcact.get("unitname")+"</li>");
				sb.append( "<li class='eps_li_static'><b>國內委託單位:</b> "+rcact.get("authorunit1")+"</li>");
				sb.append( "<li class='eps_li_static'><b>國外委託單位:</b> "+rcact.get("authorunit2")+"</li>");
				sb.append( "<li class='eps_li_static'><b>國內合作單位:</b> "+rcact.get("coopunit1")+"</li>");
				sb.append( "<li class='eps_li_static'><b>國外合作單位:</b> "+rcact.get("coopunit2")+"</li>");
				sb.append("</ul>");
				sb.append("<br><br>");
			}			
			return sb.toString();
		}
		
		
		
		
		//獲獎Rchono
		if(map.get("sys_table").equals("Rchono")&&map.get("content").equals("1")){					
			List list=jdbcDao.StandardSqlQuery("SELECT * FROM Rchono WHERE idno='"+Uid+"'");
			Map rcact;
			for(int i=0; i<list.size(); i++){
				rcact=((Map)list.get(i));
				sb.append("<ul>");
				sb.append( "<li class='eps_li_static'><b>學年度:</b> "+rcact.get("school_year")+"</li>");
				sb.append( "<li class='eps_li_static'><b>專案案號:</b> "+rcact.get("projno")+"</li>");
				sb.append( "<li class='eps_li_static'><b>獲獎名稱:</b> "+rcact.get("title")+"</li>");
				sb.append( "<li class='eps_li_static'><b>作者順序:</b> "+rcact.get("authorno")+"</li>");
				sb.append( "<li class='eps_li_static'><b>頒獎國別:</b> "+rcact.get("nation")+"</li>");
				sb.append( "<li class='eps_li_static'><b>頒獎機構:</b> "+rcact.get("inst")+"</li>");
				sb.append( "<li class='eps_li_static'><b>頒獎日期:</b> "+rcact.get("bdate")+"</li>");
				sb.append( "<li class='eps_li_static'><b>摘要:</b> "+rcact.get("intor")+"</li>");
				sb.append("</ul>");
				sb.append("<br><br>");
			}			
			return sb.toString();
		}
		
		
		
		
		
		
		
		
		
		sb.append("<li>已關閉瀏覽</li></ul>");
		return sb.toString();
	}
	
	/**
	 * 利用班及代碼或學號來判斷及格標準
	 * 
	 * @param classNo
	 * @param studentNo
	 * @return 標準
	 */
	public int getPassScore(String classNo, String studentNo){
		int pa=60;
		try{
			if(classNo.subSequence(2, 3).equals("G")){
				pa=70;
			}
		}catch(Exception e){
			if(studentNo.indexOf("G")>-1){
				pa=70;
			}
		}
		return pa;
	}
	
	public List CsGroup4One(String student_no, Map aStudent, boolean staff) {		
		int schoolYear=getSchoolYear();//現在學年
		List result = new ArrayList();
		try{//入學年取得
			StringBuilder sb=new StringBuilder(aStudent.get("entrance").toString());
			aStudent.put("inYear", sb.delete(sb.length()-2, sb.length()).toString());
		}catch(Exception e){//取得錯誤立即給予最近4年
			aStudent.put("inYear", schoolYear-4);
		}
		//此生及格標
		int pa=getPassScore(aStudent.get("depart_class").toString(), aStudent.get("student_no").toString());		
		//所有學程
		List biGroup=sqlGet("SELECT cg.*, cg.Oid as cgOid, cgr.* FROM CsGroup cg, CsGroupRule cgr WHERE " +
				"cg.Oid=cgr.group_oid AND cgr.schoolNo='"+aStudent.get("SchoolNo")+"' AND " +
				"(cg.entrance>"+aStudent.get("inYear")+" OR cg.entrance='' OR cg.entrance is null)");//大圈		
		
		//學生成績
		List myScore=sqlGet("SELECT sh.evgr_type, sh.score, sh.credit, sh.cscode, sh.opt, c.DeptNo " +
				"FROM ScoreHist sh, Class c WHERE c.ClassNo=sh.stdepart_class AND " +
				"sh.cscode !='99999' AND sh.student_no='"+aStudent.get("student_no")+"' AND sh.score>="+pa);		
		List smallGroupMajor;//必修
		List smallGroupMinor;//選修
		for(int i=0; i<biGroup.size(); i++){//學程
			float opt1=0; //必修已得
			float opt2=0; //選修已得
			float outOpt=0; //外系已得
			//取專業必修
			smallGroupMajor=sqlGet("SELECT cgs.*, c.chi_name, c5.name as deptName FROM " +
					"CsGroupSet cgs, Csno c, code5 c5 WHERE " +
					"cgs.cscode=c.cscode AND cgs.opt='1' AND " +
					"c5.category='Dept' AND c5.idno=cgs.deptNo AND " +
					"cgs.group_oid='"+((Map)biGroup.get(i)).get("cgOid")+"'");			
			for(int j=0; j<smallGroupMajor.size(); j++){				
				((Map)smallGroupMajor.get(j)).put("igot", false);//預設取得為 "假"	
				float smallGroupMajorCredit=Float.parseFloat(((Map)smallGroupMajor.get(j)).get("credit").toString());//預先轉學程學分
				//多重抵免因此不移除得到的
				for(int k=0; k<myScore.size(); k++){
					float myScoreCredit=Float.parseFloat(((Map)myScore.get(k)).get("credit").toString());//預先轉成績學分					
					if(!((Map)myScore.get(k)).get("evgr_type").equals("6")){//非抵免						
						if(						
								((Map)myScore.get(k)).get("cscode").equals(((Map)smallGroupMajor.get(j)).get("cscode"))&&//代碼相同
								((Map)myScore.get(k)).get("DeptNo").equals(((Map)smallGroupMajor.get(j)).get("deptNo"))&&//系所相同
								myScoreCredit>=smallGroupMajorCredit//學分數大於等於
						){						
							//System.out.println("必修+1");
							opt1=opt1+smallGroupMajorCredit;//專業必修++
							((Map)smallGroupMajor.get(j)).put("igot", true);//設為"真"
							//計算外系課程
							if(!aStudent.get("DeptNo").equals(((Map)smallGroupMajor.get(j)).get("deptNo"))){//外系課程
								outOpt=outOpt+smallGroupMajorCredit;//外系課程++
							}
						}						
						
					}else{//抵免課程不比開課系						
						if(						
								((Map)myScore.get(k)).get("cscode").equals(((Map)smallGroupMajor.get(j)).get("cscode"))&&//代碼相同即可
								myScoreCredit>=smallGroupMajorCredit//學分數大於等於
						){						
							//System.out.println("必修+1");
							opt1=opt1+smallGroupMajorCredit;//專業必修++
							((Map)smallGroupMajor.get(j)).put("igot", true);//設為"真"
							//計算外系課程
							if(!aStudent.get("DeptNo").equals(((Map)smallGroupMajor.get(j)).get("deptNo"))){//外系課程
								outOpt=outOpt+smallGroupMajorCredit;//外系課程++
							}
						}						
					}
				}
			}
			((Map)biGroup.get(i)).put("smallGroupMajor", smallGroupMajor);			
			//取專業選修
			smallGroupMinor=sqlGet("SELECT cgs.*, c.chi_name, c5.name as deptName FROM " +
					"CsGroupSet cgs, Csno c, code5 c5 WHERE " +
					"cgs.cscode=c.cscode AND cgs.opt='2' AND " +
					"c5.category='Dept' AND c5.idno=cgs.deptNo AND " +
					"cgs.group_oid='"+((Map)biGroup.get(i)).get("cgOid")+"'");
			
			for(int j=0; j<smallGroupMinor.size(); j++){
				((Map)smallGroupMinor.get(j)).put("igot", false);//預設取得為false					
				float smallGroupMinorCredit=Float.parseFloat(((Map)smallGroupMinor.get(j)).get("credit").toString());//預先轉學程學分
				//多重抵免因此不移除得到的
				for(int k=0; k<myScore.size(); k++){
					float myScoreCredit=Float.parseFloat(((Map)myScore.get(k)).get("credit").toString());//預先轉成績學分
					if(							
							((Map)myScore.get(k)).get("cscode").equals(((Map)smallGroupMinor.get(j)).get("cscode"))&&//代碼相同
							((Map)myScore.get(k)).get("DeptNo").equals(((Map)smallGroupMinor.get(j)).get("deptNo"))&&//系所相同
							myScoreCredit>=smallGroupMinorCredit//學分數大於等於
					){	
						opt2=opt2+smallGroupMinorCredit;//專業必修++
						((Map)smallGroupMinor.get(j)).put("igot", true);//設為"真"
						//計算外系課程
						if(!aStudent.get("DeptNo").equals(((Map)smallGroupMinor.get(j)).get("deptNo"))){//外系課程
							outOpt=outOpt+smallGroupMinorCredit;//外系課程++
						}
					}
				}
			}			
			if(staff){//若是節約模式
				if((opt1+opt2+outOpt)>0.5){	
					((Map)biGroup.get(i)).put("smallGroupMinor", smallGroupMinor);			
					((Map)biGroup.get(i)).put("opt1", opt1);
					((Map)biGroup.get(i)).put("opt2", opt2);
					((Map)biGroup.get(i)).put("optOut", outOpt);					
					result.add(biGroup.get(i));
				}
			}else{
				result.add(biGroup.get(i));
			}						
		}
		biGroup=null;
		return result;
	}
	
	public String getUsername(String username){
		
		String name=sqlGetStr("SELECT student_name FROM stmd WHERE student_no='"+username+"'");
		if(name==null)
			name=sqlGetStr("SELECT student_name FROM Gstmd WHERE student_no='"+username+"'");
		if(name==null)
			name=sqlGetStr("SELECT cname FROM empl WHERE idno='"+username+"'");
		if(name==null)
			name=sqlGetStr("SELECT cname FROM dempl WHERE idno='"+username+"'");
		
		return name;
	}
	

	/**
	 * 將float四捨五入至小數第n位
	 * @param f某float
	 * @param n第幾位
	 * @return
	 */
	public float roundOff(float f, int n){
		try{
			BigDecimal b=new BigDecimal(f);	
			return b.setScale(n,BigDecimal.ROUND_HALF_UP).floatValue();
		}catch(NumberFormatException e){
			return 0;
		}		
	}
	
}
