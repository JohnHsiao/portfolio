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
		
		<td width="200" align="left" valign="top" nowrap>		
		<!-- 左 menu start -->
		<%@ include file="/left.jsp"%>
		<!-- 左 menu END -->		
		</td>
		
		<td width="100%" align="left" valign="top" style="padding:5px;">
		
		
		
		<table class="hairLineTable" width="99%">
			<tr>
				<td class="hairLineTd">
				Q：何謂學生學習歷程？
				</td>
			</tr>
			<tr>
				<td class="hairLineTdF">
				A：學生學習歷程檔案(e-portfolio)以部落格方式呈現，是建構與記錄屬於學生個人的學習歷程，使得學習成果以數位化方式保留，其功能設計包括自我介紹、求學經歷、成績與學習記錄、成果展現、生涯目標等，在未來可藉此系統將學習歷程資料轉換成求職或求學履歷。
				</td>
			</tr>
		</table>
		
		
		<table class="hairLineTable" width="99%">
			<tr>
				<td class="hairLineTd">
				Q：如何登入學生學習歷程？
				</td>
			</tr>
			<tr>
				<td class="hairLineTdF">
				A：登入方式有兩種 1. 可由『學生專區』登入，登入後可以在左手邊的『數位學習歷程』選項進入您的學習歷程開始設計。 2. 由學生學習歷程首頁登入，登入後便可開始設計。
				</td>
			</tr>
		</table>
		
		<table class="hairLineTable" width="99%">
			<tr>
				<td class="hairLineTd">
				Q：學生製作e-Portfolio時可儲存的空間大小有多少？
				</td>
			</tr>
			<tr>
				<td class="hairLineTdF">
				A：目前每位學生可使用的空間大小無限定。
				</td>
			</tr>
		</table>		

		<table class="hairLineTable" width="99%">
			<tr>
				<td class="hairLineTd">
				Q：如何建置學生學習歷程？
				</td>
			</tr>
			<tr>
				<td class="hairLineTdF">
				A：這部分我們為同學提供了範本和操作手冊，同學可以在學習歷程檔案首頁的右手邊找到『展示範例』及『如何使用』，裡面有許多如何操作及功能顯示的相關資料，請多多利用，若是仍有不了解的部分歡迎聯繫我們。
				</td>
			</tr>
		</table>
		
		<table class="hairLineTable" width="99%">
			<tr>
				<td class="hairLineTd">
				畢業後還可以使用e-Portfolio嗎？
				</td>
			</tr>
			<tr>
				<td class="hairLineTdF">
				A：可以，畢業後會將您的e-Portfolio移置校友區。
				</td>
			</tr>
		</table>
		
 		<table class="hairLineTable" width="99%">
			<tr>
				<td class="hairLineTd">
				Q：學習歷程檔案可以匯出下載嗎？
				</td>
			</tr>
			<tr>
				<td class="hairLineTdF">
				A：這部分目前仍在規畫中，未來是可以匯出下載轉換成求職或求學履歷。
				</td>
			</tr>
		</table>
		  
		  
		  
		</td>
		
		
		
		
		
		
		
		
	</tr>
</table>




<%@ include file="/footer.jsp"%>


	</body>
</html>
