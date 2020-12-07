<%@ page language="java" contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<%@ include file="/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
		<meta http-equiv="description" content="中華科技大學數位化歷程服務網......嘰哩呱啦....">
		<link rel="stylesheet" type="text/css" href="decorate/style.css">
		<!--[if gte IE 5.5000]><script type="text/javascript" src="include/Filter4Png.js"></script><![endif]-->
		<script type="text/javascript" src="include/globel.js"></script>
	</head>

	<body>	
	
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
	
	
	
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		
		<td width="20%" align="left" valign="top">		
		<!-- 左 menu start -->
		<%@ include file="left.jsp"%>
		<!-- 左 menu END -->		
		</td>
		
		<td width="100%" align="left" valign="top">
		
		<table>
	    	<tr height="10">
	    		<td width="1">
	    			
	    		</td>
	    		<td align="left" width="100%">
	    		
	    		</td>
	    	</tr>
	    	<tr>
	    		<td width="1">
	    		<img src="images/icon/exclamation.gif"/>
	    		</td>
	    		<td align="left" width="100%">
	    		<font face="arial">出現了一些問題, 細節如下:</font>
	    		</td>
	    	</tr>
	    	<tr height="300" valign="top">
	    		<td colspan="2"><font face="arial" size="-1">
					<% if (exception != null) { %>
					<% exception.printStackTrace(new java.io.PrintWriter(out)); %>
					<% } else { %>
					很抱歉, 這個問題系統無法判斷錯誤原因.
					<% } %>
				</font></td>
			</tr>
	    </table>
		
		</td>
		
		
		
		
	</tr>
</table>




<%@ include file="footer.jsp"%>
	
	
	
	
	
	







	</body>
</html>