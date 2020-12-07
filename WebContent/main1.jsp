<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
	<base href="<%=basePath%>">
	<title>中華科技大學數位化歷程服務網</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="e-Portfolio, 數位化學習歷程">
	<meta http-equiv="description" content="中華科技大學數位化歷程服務網">
	<link rel="stylesheet" type="text/css" href="decorate/style.css">
	<!--[if gte IE 5.5000]><script type="text/javascript" src="include/Filter4Png.js"></script><![endif]-->
	<script type="text/javascript" src="include/globel.js"></script>
</head>

<body>

<%@ include file="html/jspx/staticHead.jsp"%>

<%@ include file="header.jsp"%>
	
<table width="100%" height="300" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td>
			<img src="images/main_01.gif" width="780" height="100" alt=""></td>
		<td background="images/main_02.gif" width="100%" height="100"></td>
	</tr>
	<tr>
		<td>
			<img src="images/main_03.gif" width="780" height="100" alt=""></td>
		<td background="images/main_04.gif" width="100%" height="100"></td>
	</tr>
	<tr>
		<td>
			<img src="images/main_04-05.gif" width="780" height="100" alt=""></td>
		<td background="images/main_06.gif" width="100%" height="100"></td>
	</tr>
</table>
	
<%@ include file="banner.jsp"%>
<table width="99%" cellpadding="0" cellspacing="0">
	<tr>		
		<td valign="top" width="200">
		<%@ include file="left.jsp"%>
		</td>		
		<td valign="top">
		
		<table cellspacing="0" cellpadding="0" width="100%">
			<tr>
				<td align="left">
				
				<c:if test="${target=='newsBox'}">				
				<table align="left" id="newsItem" class="menuItem_off">
					<tr>
						<td width="1"><img src="images/icon/folder_feed.gif"></td>
						<td onClick="showObj4News('news')"><b>最新消息</b></td>
					</tr>
				</table>
				
				<table align="left" cellspacing="0" cellpadding="0" width="2"><tr><td></td></tr></table>
				
				<table align="left" id="newSiteItem" class="menuItem_off">
					<tr>
						<td width="1"><img src="images/icon/folder_home.gif"></td>
						<td onClick="showObj4News('newSite')"><b>最新網站</b></td>
					</tr>
				</table>
				
				<table align="left" cellspacing="0" cellpadding="0" width="2"><tr><td></td></tr></table>
				
				<table align="left" id="newArticleItem" class="menuItem_off">
					<tr>
						<td width="1"><img src="images/icon/folder_page.gif"></td>
						<td onClick="showObj4News('newArticle')"><b>最新文章</b></td>
					</tr>
				</table>
				
				<table align="left" cellspacing="0" cellpadding="0" width="2"><tr><td></td></tr></table>
				
				<table align="left" id="bestSiteItem" class="menuItem">
					<tr>
						<td width="1"><img src="images/icon/folder_star.gif"></td>
						<td onClick="showObj4News('bestSite')"><b>推薦網站</b></td>
					</tr>
				</table>		
				
				<table align="left" cellspacing="0" cellpadding="0" width="2"><tr><td></td></tr></table>
				
				<table align="left" id="bestArticleItem" class="menuItem_off">
					<tr>
						<td width="1"><img src="images/icon/folder_lightbulb.gif"></td>
						<td onClick="showObj4News('bestArticle')"><b>競賽活動</b></td>
					</tr>
				</table>
				
				</td>
			</tr>
		</table>
		
		<table cellpadding="0" cellspacing="0">
			<tr>
				<td><img src="images/newMain_01.gif" width="5" height="5" alt=""></td>
				<td background="images/newMain_02.gif" width="100%"></td>
				<td><img src="images/newMain_03.gif" width="5" height="5" alt=""></td>
			</tr>	
			<tr>
				<td background="images/newMain_04.gif" width="5"></td>
				<td bgcolor="#ffffff" valign="top">				
				
				<!-- 資訊匣內容 -->
				<%@ include file="index/total.jsp"%>				
				
				</td>
				<td background="images/newMain_06.gif" width="5"></td>
			</tr>
					
			<tr>
				<td><img src="images/newMain_07.gif" width="5" height="5" alt=""></td>
				<td background="images/newMain_08.gif" width="100%"></td>
				<td><img src="images/newMain_09.gif" width="5" height="5" alt=""></td>
			</tr>		
		</table>		
		</c:if>
				
				<c:if test="${target=='student'}">
				<%@ include file="index/student.jsp"%>
				</c:if>	
							
				<c:if test="${target=='teacher'}">
				<%@ include file="index/teacher.jsp"%>
				</c:if>
								
				<c:if test="${target=='graduate'}">
				<%@ include file="index/graduate.jsp"%>
				</c:if>
								
				<c:if test="${target=='site'}">
				<%@ include file="index/site.jsp"%>
				</c:if>
				
				<c:if test="${target=='classInfo'}">
				<%@ include file="html/classInfo.jsp"%>
				</c:if>
				
				</td>
			</tr>
		</table>
		</td>
					
	</tr>
</table>





<%@ include file="footer.jsp"%>


</body>
</html>




<script>
function showObj4News(id){
	document.getElementById("news").style.display="none";
	document.getElementById("newSite").style.display="none";
	document.getElementById("newArticle").style.display="none";
	document.getElementById("bestSite").style.display="none";
	document.getElementById("bestArticle").style.display="none";
	document.getElementById(id).style.display="inline";
	
	document.getElementById("newsItem").className="menuItem_off";
	document.getElementById("newSiteItem").className="menuItem_off";
	document.getElementById("newArticleItem").className="menuItem_off";
	document.getElementById("bestSiteItem").className="menuItem_off";
	document.getElementById("bestArticleItem").className="menuItem_off";	
	document.getElementById(id+"Item").className="menuItem";
}
</script>