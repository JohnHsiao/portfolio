<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<table style="position:absolute; left:45px; top:60px; z-index:2; cursor:pointer;">
	<tr>
		<td><img src="images/logo-en.gif"/></td>
		<td>
		
		<table cellspacing="0" cellpadding="0">
			<tr>
				<td style="font-size:10px;">
				搜尋 基本資料 | 學習歷程 | 文章標題 | 論文標題
				</td>
			</tr>
			<form method="get" action="http://www.google.com/search">
			<tr>
				<td>
				<table cellspacing="0" cellpadding="0">
					<tr>
						<td><img src="images/input_left.gif"/></td>
						<td><input type="text" class="colorInput" name="q" size="25" maxlength="255" value="" /></td>
						<td><img src="images/input_right.gif"/></td>
						<td>&nbsp;<input type="submit" class="gCancle" value="搜詢" />
						<input type="checkbox"  name="sitesearch" value="http://alliance.cust.edu.tw" checked />站內
						</td>
					</tr>
				</table>				
				</td>				
			</tr>
			</form>
		</table>
		
		</td>
	</tr>
</table>