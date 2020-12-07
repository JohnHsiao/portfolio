<%@ page language="java" contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<%@ include file="taglibs.jsp" %>  
<html:html locale="true">
<head>  
	<link href="include/home.css" type=text/css rel=stylesheet>
	<link href="include/decorate.css" type="text/css" rel="stylesheet" media="screen">    
	<title>Doh!</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
</head>
  
  <body>
  <table>
    	<tr>
    		<td>
    			<img src="images/icon/greyscale_34.gif">
    		</td>
    		<td align="left">
				<b>An Error has occurred in this application.</b>
			</td>
		</tr>
		<tr>
			<td>
			</td>
			<td><font>
			<% if (exception != null) { %>
    		<pre><% exception.printStackTrace(new java.io.PrintWriter(out)); %></pre>
			<% } else { %>
    		Please check your log files for further information.
			<% } %>
			</font>
			<br><br>
			</td>
		</tr>
	</table>

	</body>
</html:html>