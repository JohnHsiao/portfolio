<%@ page language="java" pageEncoding="UTF-8"%>
<table width="100%">
	<tr>
		<td>
		<img src="images/bg_home_blk_demos.gif"/>
		</td>
	</tr>
</table>

<b>人數: ${tol}, 網站${cnt}, 建置率: <fmt:formatNumber value="${(cnt/tol)*100}" pattern="##.##" type="number"/>%</b>


<c:forEach items="${toPage}" var="t">

	<c:if test="${t.cnt>0}">
	<table width="99%" cellspacing="0" border="0" >
		<tr>
			<td width="100%" class="underlineIndex"  style="cursor:pointer;" onClick="showObj('dept${t.idno}')">${t.name}</td>
			<td class="underlineIndex" align="right" style="cursor:pointer;" onClick="showObj('dept${t.idno}')" width="80" nowrap>班級數</td>
			<td class="underlineIndex" width="40" nowrap align="right">${t.cnt}</td>
			<td class="underlineIndex" align="right" style="cursor:pointer;" onClick="showObj('dept${t.idno}')" width="80" nowrap>學生數</td>
			<td class="underlineIndex" width="40" nowrap align="right">${t.pst}</td>
			<td class="underlineIndex" align="right" style="cursor:pointer;" onClick="showObj('dept${t.idno}')" width="80" nowrap>網站數</td>
			<td class="underlineIndex" width="40" nowrap align="right">${t.dst}</td>
			<td class="underlineIndex" align="right" style="cursor:pointer;" onClick="showObj('dept${t.idno}')" width="80" nowrap>建置率</td>
			<td class="underlineIndex" width="100" nowrap align="right"><fmt:formatNumber value="${(t.dst/t.pst)*100}" pattern="##.##" type="number"/>%</td>
		</tr>					
	</table>
	
	
	
	
	<c:if test="${!empty t.std}">
	<table width="99%" cellspacing="0" border="0" id="dept${t.idno}" style="display:none;">
		<c:forEach items="${t.std}" var="ts">
		<tr>
			<td class="underlineIndex" width="1">
			<img src="images/icon/folder_home.gif"/>
			</td>
			<td class="underlineIndex" width="100%">
			&nbsp;&nbsp;<a href="ClassInfo.jspx?ClassInfo=${ts.ClassNo}">${ts.ClassName}</a>
			</td>
			<td class="underlineIndex" align="right" nowrap width="80">學生數</td>
			<td class="underlineIndex" align="right" nowrap width="40">${ts.tol}</td>
			<td class="underlineIndex" align="right" nowrap width="80">網站數</td>
			<td class="underlineIndex" align="right" nowrap width="40">${ts.cnt}</td>
			<td class="underlineIndex" align="right" nowrap width="80">建置率</td>
			<td class="underlineIndex" align="right" nowrap width="100"><fmt:formatNumber value="${(ts.cnt/ts.tol)*100}" pattern="##.##" type="number"/>%</td>
		</tr>
		</c:forEach>			
	</table>		
	</c:if>	
	</c:if>		
</c:forEach>