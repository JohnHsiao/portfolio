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
	
	<%@ include file="jspx/staticHead.jsp"%>
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
	
	
	
	
	
	<table width="99%" cellpadding="0" cellspacing="0" style="position:absolute; top:135px; z-index:32760;">
			<tr>
				<td width="200"></td>
				<td align="right">		
				<table width="99%" cellpadding="0" cellspacing="0" style="filter:Alpha(opacity: 0.6);filter:alpha(opacity=60);-moz-opacity:0.6; opacity:.6">
					<tr>
						<td><img src="images/newMain_01.gif" width="5" height="5" alt=""></td>
						<td background="images/newMain_02.gif" width="100%"></td>
						<td><img src="images/newMain_03.gif" width="5" height="5" alt=""></td>
					</tr>	
					<tr>
						<td background="images/newMain_04.gif" width="5"></td>
						<td bgcolor="#ffffff" valign="top">				
						
						<!-- 資訊匣內容 -->
						<div style="font-size:12px;">
						
						<lu>
						<li>建置率分數計算: <br>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						系上學生的建置人數/系上學生人數*50<br><br></li>
						<li>項目完整度計算: <br>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						加總(每位學生自建的學習歷程，例如證照、社團等有資料者計數1點+每一篇文章計數1點)/系上人數*50</li>
						</lu>
						<p>以上計算公式由教務處制定</p>
						</div>
						
						</td>
						<td background="images/newMain_06.gif" width="5"></td>
					</tr>
							
					<tr>
						<td><img src="images/newMain_07.gif" width="5" height="5" alt=""></td>
						<td background="images/newMain_08.gif" width="100%"></td>
						<td><img src="images/newMain_09.gif" width="5" height="5" alt=""></td>
					</tr>		
				</table>
				</td>
			</tr>
		</table>
	
	
	
	

<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		
		<td width="200" align="left" valign="top">		
		<!-- 左 menu start -->
		<%@ include file="/left.jsp"%>
		<!-- 左 menu END -->		
		</td>
		<td>		
		<b><font color="#ca3100" size=+2>競賽活動</font></b>
		<br>
		<font color="#ca3100" size=-1>以下列表顯示最近1次的競賽活動成績, 點擊科系可查看細節資訊</font>
		
		<table width="99%" cellspacing="0" border="0">
		<c:forEach items="${joinParty}" var="j">
		<tr>
			<td class="underlineIndex">
			
			
			
			<table  style="cursor:pointer;" onClick="showObj('player${j.idno}');" cellspacing="0" border="0">
				<tr>
					<td><img src="images/icon/folder_home.gif"/></td>
					<td>${j.name}, 個人競賽參與人數 <b>${fn:length(j.player)}</b>人, 團體競賽得分 <b>${j.total}</b></td>
					<td nowrap></td>
				</tr>
			</table>			
			</td>
		</tr>
		
		
		
		
		
		
		
		<tr>
			<td class="underlineIndex" id="player${j.idno}" style="display:none;">
			
			<table width="45%" cellspacing="0" border="0" style="font-size:12px;" align="left">
				<tr>
					<td width="20"></td>
					<td>學生人數: ${j.sumstd}</td>
					
				</tr>
				<tr>
					<td width="20"></td>
					<td>學習歷程: ${j.sumcont}篇</td>
				</tr>
				<tr>
					<td width="20"></td>
					<td>文章: ${j.sumpages}篇</td>
				</tr>
				<tr>
					<td width="20"></td>
					<td>完整度得分: ${j.full}</td>
				</tr>
			</table>			
			
			<table width="45%" cellspacing="0" border="0" style="font-size:12px;">
				<tr>
					<td width="20"></td>
					<td>學生人數: ${j.sumstd}</td>
				</tr>
				<tr>
					<td width="20"></td>
					<td>網站數量: ${j.sumport}</td>
				</tr>
				<tr>
					<td width="20"></td>
					<td>建置率 : ${j.complate}, 建置率得分 ${j.sumComplate}</td>
				</tr>
				<tr>
					<td width="20"></td>
					<td>建置率得分: ${j.sumComplate}</td>
				</tr>
				
			</table>
			
			
			
			<c:forEach items="${j.player}" var="p">
			<table cellspacing="0" border="0">
				<tr>
					<td width="20"></td>
					<td><img src="images/icon/house.gif"/></td>
					<td><a href="myPortfolio?path=${p.path}" target="_blank">${p.ClassName}</a></td>
					<td><a href="myPortfolio?path=${p.path}" target="_blank">${p.siteName}</a></td>
					<td><a href="myPortfolio?path=${p.path}" target="_blank">${p.count}</a></td>
				</tr>
			</table>
			</c:forEach>
					
			</td>
		</tr>		
		
		</c:forEach>			
		</table>
		
		</td>		
	</tr>
</table>

<%@ include file="/footer.jsp"%>


	</body>
</html>
