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
	
	
	
	
	
	
	
	
	
	

<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		
		<td width="20%" align="left" valign="top">		
		<!-- 左 menu start -->
		<%@ include file="/left.jsp"%>
		<!-- 左 menu END -->		
		</td>
		
		<td width="60%" align="left" valign="top" style="padding:5px;"> 
		 <br>
		<h3><font color="#ca3100" size=+2>參考資源</font></h3>

  <table class="hairLineTable" width="99%">
  
  <tr>
    <td class="hairLineTd">學校名稱</td>
    <td class="hairLineTd">ePortfolio 網址</td>
  </tr>
  <tr>
    <td class="hairLineTdF">逢甲大學</td>
    <td class="hairLineTdF"><a href="http://eportfolio.fcu.edu.tw/makeep/guides.html">http://eportfolio.fcu.edu.tw/makeep/guides.html</a></td>
  </tr>
  <tr>
    <td class="hairLineTd">景文科大</td>
    <td class="hairLineTd"><a href="http://eportfolio.just.edu.tw/web/10202">http://eportfolio.just.edu.tw/web/10202</a></td>
  </tr>
  <tr>
    <td class="hairLineTdF">明志科大</td>
    <td class="hairLineTdF"><a href="http://eportfolio.mcut.edu.tw/onweb.jsp?webno=3333333332">http://eportfolio.mcut.edu.tw/onweb.jsp?webno=3333333332</a></td>
  </tr>
  <tr>
    <td class="hairLineTd">朝陽科大</td>
    <td class="hairLineTd"><a href="http://lms.ctl.cyut.edu.tw/portfolio/index.php">http://lms.ctl.cyut.edu.tw/portfolio/index.php</a></td>
  </tr>
  <tr>
    <td class="hairLineTdF">海洋大學</td>
    <td class="hairLineTdF"><a href="http://eportfolio.ntou.edu.tw/">http://eportfolio.ntou.edu.tw/</a></td>
  </tr>
  <tr>
    <td class="hairLineTd">靜宜大學</td>
    <td class="hairLineTd"><a href="http://alcat.pu.edu.tw/eportfolio/index_e.html">http://alcat.pu.edu.tw/eportfolio/index_e.html</a></td>
  </tr>
  <tr>
    <td class="hairLineTdF">高雄應用科大</td>
    <td class="hairLineTdF"><a href="http://ep.kuas.edu.tw/index2.php?page=introduction">http://ep.kuas.edu.tw/index2.php?page=introduction</a></td>
  </tr>
  <tr>
    <td class="hairLineTd">成功大學</td>
    <td class="hairLineTd"><a href="http://eportfolio.ncku.edu.tw/index/index.php">http://eportfolio.ncku.edu.tw/index/index.php</a></td>
  </tr>
  <tr>
    <td class="hairLineTdF">聯合大學</td>
    <td class="hairLineTdF"><a href="http://eportfolio.nuu.edu.tw/ct.asp?xItem=826730&amp;ctNode=361&amp;mp=5">http://eportfolio.nuu.edu.tw/ct.asp?xItem=826730&amp;ctNode=361&amp;mp=5</a></td>
  </tr>
  <tr>
    <td class="hairLineTd">高雄師範大學</td>
    <td class="hairLineTd"><p><a href="http://cdtl.nknu.edu.tw/e_portfolio/">http://cdtl.nknu.edu.tw/e_portfolio/</a></p></td>
  </tr>
  <tr>
    <td class="hairLineTdF">實踐大 學</td>
    <td class="hairLineTdF"><a href="http://eportfolio.kh.usc.edu.tw/">http://eportfolio.kh.usc.edu.tw/</a></td></tr></tbody></table> 
		 
		</td>
		
		
		
		
		
		
		
		
		
	</tr>
</table>




<%@ include file="/footer.jsp"%>


	</body>
</html>
