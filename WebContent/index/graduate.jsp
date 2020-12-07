<%@ page language="java" pageEncoding="UTF-8"%>
<table width="100%">
	<tr>
		<td>
		<img src="images/bg_home_blk_demos.gif"/>
		</td>
	</tr>
</table>


<table width="100%">
	<tr>
		<td>		
		
		<c:forEach items="${toPage}" var="t">
		
		
		
		<c:if test="${fn:length(t.std)>0}">
		
		
		<table width="99%" cellspacing="0" border="0" >
			<tr>
				<td class="underlineIndex" style="cursor:pointer;" onClick="showObj('dept${t.idno}')" width="100%">
				${t.name}
				</td>
				<td class="underlineIndex" style="cursor:pointer;" onClick="showObj('dept${t.idno}')" width="100" nowrap>
				網站數量 ${t.cnt}
				</td>
			</tr>					
		</table>
						
		<c:if test="${!empty t.std}">
		<table width="99%" cellspacing="0" border="0" id="dept${t.idno}" style="display:none;">
			<c:forEach items="${t.std}" var="ts">
			<tr>
				<td class="underlineIndex" width="1">
				<img src="images/icon/house.gif"/>
				</td>
				<td class="underlineIndex" width="100%">
				&nbsp;&nbsp;<a href="myPortfolio?path=${ts.path}" target="_blank">${ts.siteName}, ${ts.siteDescript}</a>
				</td>
				<td class="underlineIndex" style="cursor:pointer;" onClick="showObj('dept${t.idno}')" width="100" nowrap>
				瀏覽次數 ${ts.counter}
				</td>
			</tr>
			</c:forEach>			
		</table>
		</c:if>
		
		<c:if test="${empty t.std}">
		<table width="99%" cellspacing="0" border="0" id="dept${t.idno}" style="display:none;">
			<tr>
				<td class="underlineIndex">
				&nbsp;&nbsp;${t.name}暫時沒有網站
				</td>
			</tr>
		</table>
		</c:if>
		
		
		
		
		</c:if>
		
		
		
						
		
		</c:forEach>
		
		</td>
	</tr>
</table>