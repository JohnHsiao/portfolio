<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
	<head>
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
	
	<%@ include file="jspx/staticHead.jsp"%>
	<%@ include file="../header.jsp"%>	
	
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

	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td width="200" align="left" valign="top" nowrap>		
			<!-- 左 menu start -->
			<%@ include file="../left.jsp"%>
			<!-- 左 menu END -->		
			</td>
			
			<td width="100%" align="left" valign="top" style="padding:5px;">
			<table width="99%" cellspacing="0" border="0" >
				<c:forEach items="${classInfo}" var="ts">
				<tr>
					<td class="news-border" width="1">
					<img src="images/icon/house.gif"/>
					</td>
					<td class="news-border" width="100%" nowrap>
					&nbsp;&nbsp;<a href="myPortfolio?path=${ts.path}" target="_blank">${ts.siteName}, ${ts.siteDescript}</a>
					</td>
				</tr>
				</c:forEach>					
			</table>
			</td>		
		</tr>
	</table>
	<%@ include file="../footer.jsp"%>
	</body>
</html>
