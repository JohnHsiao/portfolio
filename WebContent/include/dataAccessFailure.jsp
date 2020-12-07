<%@ page language="java" contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<%@ include file="taglibs.jsp" %>  
<html:html locale="true">
<HEAD><TITLE><bean:message key="title" /></TITLE>
  <html:base/>
  <LINK href="pages/images/home.css" type=text/css rel=stylesheet>
  <link href="pages/images/decorate.css" type="text/css" rel="stylesheet" media="screen">
  <Link href="pages/score/cust.css" type="text/css" rel="stylesheet" media="screen">
    
    <title>系統錯誤</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <table>
    	<tr>
    		<td>
    			<img src="images/icon/greyscale_34.gif">
    		</td>
    		<td align="left">
    		出現了一個問題, 很有可能是資料庫存取錯誤所造成的, 細節如下:
    		</td>
    	</tr>
    	<tr>
    		<td></td>
    		<td>
				<% if (exception != null) { %>
				<pre><% exception.printStackTrace(new java.io.PrintWriter(out)); %></pre>
				<% } else { %>
				很抱歉, 這個問題系統無法產生錯誤訊息.
				<% } %>
			</td>
		</tr>
    </table>
  </body>
</html:html>
