<%@ page language="java" pageEncoding="UTF-8"%>

<%@ include file="news.jsp"%>

<div id="newSite" style="display:none;">
<table>
	<tr>
		<td class="menu-title">
		本系統已收錄 <b><font color="#000000">${countSite}</font></b>個網站
		</td>
	</tr>
</table>
<table width="99%" cellspacing="0" border="0" >
	<c:forEach items="${new5sites}" var="ts" begin="0" end="19">
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

<table width="100%" onClick="showObj('morenewsite')" style="cursor:pointer;">
	<tr>
		<td width="1"><img src="images/icon/neighbourhood.gif"></td>
		<td><b>更多網站</b></td>		
	</tr>
</table>
<table width="99%" cellspacing="0" border="0" id="morenewsite" style="display:none;">
	<c:forEach items="${new5sites}" var="ts" begin="20" end="29">
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
</div>


















<div id="newArticle" style="display:none;">
<table>
	<tr>
		<td class="menu-title">
		目前共收錄 <b><font color="#000000">${countPage}</font></b>篇文章
		</td>
	</tr>
</table>

<table width="99%" cellspacing="0" border="0" >
	<c:forEach items="${new5pages}" var="ts" begin="0" end="9">
	<tr>
		<td class="news-border" width="1">
		<img src="images/icon/page_portrait.gif"/>
		</td>
		<td class="news-border" width="100%" nowrap>
		&nbsp;&nbsp;
		<c:choose>
			<c:when test="${fn:length(ts.title)>=25&&fn:length(ts.title)<=45}"><font size="-1"><a href="myPortfolio?path=${ts.path}&page=${ts.Oid}" target="_blank">${ts.title}</a></font></c:when>
			<c:when test="${fn:length(ts.title)<25}"><a href="myPortfolio?path=${ts.path}&page=${ts.Oid}" target="_blank">${ts.title}</a></c:when>
			<c:when test="${fn:length(ts.title)>=45}"><font size="-2"><a href="myPortfolio?path=${ts.path}&page=${ts.Oid}" target="_blank">${fn:substring(ts.title, 0, 50)}...</a></font></c:when>
		</c:choose>
		</td>
	</tr>
	</c:forEach>					
</table>
<table width="100%" onClick="showObj('morearticle')" style="cursor:pointer;">
	<tr>
		<td width="1"><img src="images/icon/page_copy.gif"></td>
		<td><b>更多文章</b></td>		
	</tr>
</table>
<table width="99%" cellspacing="0" border="0" id="morearticle" style="display:none;">
	<c:forEach items="${new5pages}" var="ts" begin="10" end="19">
	<tr>
		<td class="news-border" width="1">
		<img src="images/icon/page_portrait.gif"/>
		</td>
		<td class="news-border" width="100%" nowrap>
		&nbsp;&nbsp;
		<c:choose>
			<c:when test="${fn:length(ts.title)>=25&&fn:length(ts.title)<=45}"><font size="-1"><a href="myPortfolio?path=${ts.path}&page=${ts.Oid}" target="_blank">${ts.title}</a></font></c:when>
			<c:when test="${fn:length(ts.title)<25}"><a href="myPortfolio?path=${ts.path}&page=${ts.Oid}" target="_blank">${ts.title}</a></c:when>
			<c:when test="${fn:length(ts.title)>=45}"><font size="-2"><a href="myPortfolio?path=${ts.path}&page=${ts.Oid}" target="_blank">${fn:substring(ts.title, 0, 50)}...</a></font></c:when>
		</c:choose>
		</td>
	</tr>
	</c:forEach>					
</table>
</div>

<div id="bestSite" style="display:inline;">
<table>
	<tr>
		<td class="menu-title">
		瀏覽人數最多的網站
		</td>
	</tr>
</table>
				
<table width="99%" cellspacing="0" border="0" >
	<c:forEach items="${top5sites}" var="ts" begin="0" end="19">
	<tr>
		<td class="news-border" width="1">
		<img src="images/icon/house_star.gif"/>
		</td>
		<td class="news-border" width="100%" nowrap>
		&nbsp;&nbsp;<a href="myPortfolio?path=${ts.path}" target="_blank">${fn:substring(ts.siteName, 0, 9)}, ${fn:substring(ts.siteDescript, 0, 9)}...</a>
		</td>
	</tr>
	</c:forEach>						
</table>
<table width="100%" onClick="showObj('moresite')" style="cursor:pointer;">
	<tr>
		<td width="1"><img src="images/icon/neighbourhood.gif"></td>
		<td><b>更多網站</b></td>		
	</tr>
</table>
<table width="99%" cellspacing="0" border="0" id="moresite" style="display:none;">
	<c:forEach items="${top5sites}" var="ts" begin="20" end="29">
	<tr>
		<td class="news-border" width="1">
		<img src="images/icon/house_star.gif"/>
		</td>
		<td class="news-border" width="100%" nowrap>
		&nbsp;&nbsp;<a href="myPortfolio?path=${ts.path}" target="_blank">${fn:substring(ts.siteName, 0, 9)}, ${fn:substring(ts.siteDescript, 0, 9)}...</a>
		</td>
	</tr>
	</c:forEach>						
</table>
</div>

<div id="bestArticle" style="display:none;">
<table>
	<tr>
		<td class="menu-title">
		目前參與競賽活動的網站
		</td>
	</tr>
</table>
<table width="100%" cellspacing="0" border="0" cellpadding="0">
	<tr height="300">
		<td width="100%" valign="top">
		<table width="99%" cellspacing="0" border="0" >
			<c:forEach items="${players}" var="ts">
			<tr>
				<td class="news-border" width="1"><img src="images/icon/house_link.gif"/></td>
				<td class="news-border" width="100" align="center">${ts.student_no}</td>
				<td class="news-border" nowrap><a title="${ts.siteDescript}" href="myPortfolio?path=${ts.path}" target="_blank" nowrap>${fn:substring(ts.siteName, 0, 24)}</a></td>
				<!-- td class="news-border" nowrap><a href="myPortfolio?path=${ts.path}" target="_blank" nowrap>${fn:substring(ts.siteDescript, 0, 24)}</a></td-->
				<td class="news-border" nowrap>${ts.count}</td>
				
			</tr>
			</c:forEach>						
		</table>
		</td>
	</tr>
</table>
</div>			
