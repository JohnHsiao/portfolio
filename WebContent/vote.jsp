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





	
	
<table cellpadding="0" cellspacing="0" width="100%" height="100%">
	<tr>
		<td>		
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
		</td>
	</tr>
	<tr height="100%">
		<td>
		<table width="99%" cellpadding="0" cellspacing="0">
			<tr>		
				<td valign="top">
				
				
				
				
				
				
				
				
				

			<c:if test="${message!=null}">
			<table align="center" style="position:relative; top:-50px; z-index:32761;">
				<tr>
					<td colspan=2 nowrap>
					${message}
					</td>
				</tr>	
			</table>
			</c:if>

			<table align="center" class="hairLineTable" style="position:relative; top:-50px; z-index:32761;">
				<tr>
					
					<td nowrap>投票給<a href="myPortfolio?path=<%=request.getParameter("path")%>"><%=request.getParameter("path")%></a></td>
				</tr>
				<html:form action='/Vote' method='post'>
				
				
				
				
				<c:if test="${account==null}">
					<tr>
						<td class="hairLineTdF">
						
						<input class='username' id='username' type='text' name='username'>
						<input class='password' type='password' name='password'> 
						</td>
					</tr>
				</c:if>
					<tr>
						<td class="hairLineTdF">
						<input type="hidden" name="path" value="<%=request.getParameter("path")%>"/>
						<input type="hidden" name="act_oid" value="<%=request.getParameter("act_oid")%>"/>
						<input type="submit" class='gSubmit' 
						onClick="if(document.getElementById('username').value==''||document.getElementById('password').value=='') 
						alert('帳號或密碼未填在學同學、教職員為資訊系統帳號密碼校友帳號為學號, 密碼為身份證字號');" value="確定投票"/>
						<input type=checkbox disabled checked>
						<font size=-2>同意所有競賽規則</font>
						</td>
					</tr>
				</html:form>
				</table>
				
				
				
				
				
				
				
				
						
						
				</td>	
			</tr>
		</table>		
		</td>
	</tr>
	<tr>
		<td>
		<%@ include file="html/jspx/staticHead.jsp"%>
		<%@ include file="header.jsp"%>
		<%@ include file="footer.jsp"%>
		</td>
	</tr>
</table>
</body>
</html>