<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
		<meta http-equiv="description" content="中華科技大學數位化歷程服務網">
		<link rel="stylesheet" type="text/css" href="decorate/style.css">
		<!--[if gte IE 5.5000]><script type="text/javascript" src="include/Filter4Png.js"></script><![endif]-->
		<script type="text/javascript" src="include/globel.js"></script>
	</head>

	<body>	
	
	<%@ include file="staticHead.jsp"%>
	<%@ include file="/header.jsp"%>
	
	
	
	
	
	
	
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
	
	
	
	
	
	
	
	
	
	

<table width="100%" height="400" border="0" cellpadding="0" cellspacing="0">
	<tr>
		
		<td width="200" align="left" valign="top" nowrap>		
		<!-- 左 menu start -->
		<%@ include file="/left.jsp"%>
		<!-- 左 menu END -->		
		</td>
		
		<td width="100%" align="left" valign="top" style="padding:5px;">
		<img src="/portfolio/images/bg_home_blk_epintro.gif"/>
		
		
		
		<table class="hairLineTable" width="99%">
			<tr>
				<td class="hairLineTd">
				靜態網頁說明
				</td>
			</tr>
			<tr>
				<td class="hairLineTdF">
				
				<table>
					<tr>
						<td style="font-size:12px;">瀏覽: </td>
						<td><a href="html/help/index.htm" target="_blank"><img src="images/icon/application_firefox.gif"/></a>
						<td><a href="html/help/index.htm" target="_blank"><img src="images/icon/noie.gif"/></a>
						</td>
					</tr>
				</table>				
				</td>
			</tr>
		</table>
		
		
		<table class="hairLineTable" width="99%">
			<tr>
				<td class="hairLineTd">
				動態網頁說明
				</td>
			</tr>
			<tr>
				<td class="hairLineTdF">
				
				<table>
					<tr>
						<td style="font-size:12px;">觀看: </td>
						<td>
						<a href="html/flash/index.html" target="_blank"><img src="images/icon/television.gif"/></a>
						<a href="html/flash/index.html" target="_blank"><img src="images/icon/mimetypes/application_flash.gif"/></a>
						</td>
					</tr>
				</table>				
				</td>
			</tr>
		</table>
		
		<table class="hairLineTable" width="99%">
			<tr>
				<td class="hairLineTd">
				說明文件
				</td>
			</tr>
			<tr>
				<td class="hairLineTdF">
				
				<table>
					<tr>
						<td style="font-size:12px;">下載: </td>
						<td>
						<a href="html/ePortfolio_manual.pdf" target="_blank"><img src="images/icon/mimetypes/icon_pdf.gif"/></a>
						<a href="html/ePortfolio_manual.pdf" target="_blank"><img src="images/icon/mimetypes/ico_file_word.gif"/></a>
						<a href="html/ePortfolio_manual.pdf" target="_blank"><img src="images/icon/mimetypes/icon_xls.gif"/></a>
						</td>
					</tr>
				</table>				
				</td>
			</tr>
		</table>
		
		</td>
		
		
		
		
		
		
		
	</tr>
</table>




<%@ include file="/footer.jsp"%>


	</body>
</html>
