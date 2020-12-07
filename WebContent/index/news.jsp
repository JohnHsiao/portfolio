<%@ page language="java" pageEncoding="UTF-8"%>
<div id="news" style="display:none;">
<table>
	<tr>
		<td class="menu-title">
		最新訊息
		</td>
	</tr>
</table>


<table width="100%" height="300" cellspacing="0" border="0">
	<tr>
		<td valign="top">
		
		<c:forEach items="${news}" begin="0" end="9" var="n">
		<table width="100%" class="news-border" id="news${n.Oid}">
			<tr>
				<td class="news-border" width="1">		
				<c:choose>
					<c:when test="${n.top=='1'}"><img src="images/icon/icon_info_exclamation.gif"/></c:when>
					<c:when test="${n.top!='1'}"><img src="images/icon/feed.gif"/></c:when>
				</c:choose>		
				</td>
				<td class="news-border" style="cursor:pointer;" nowrap onclick="getNews('${n.Oid}', 'newsInfo'), showNews('news${n.Oid}');"><font size="-1">${n.StartDate}</font></td>		
				<td class="news-border" style="cursor:pointer;" width="100%" align="left" nowrap onclick="getNews('${n.Oid}', 'newsInfo'), showNews('news${n.Oid}');">		
				
				<c:choose>
					<c:when test="${fn:length(n.title)>=25&&fn:length(n.title)<=45}"><font size="-1">${n.title}</font></c:when>
					<c:when test="${fn:length(n.title)<25}">${n.title}</c:when>
					<c:when test="${fn:length(n.title)>=45}"><font size="-2">${fn:substring(n.title, 0, 50)}...</font></c:when>
				</c:choose>		
				
				</td>
				
				<td nowrap class="news-border">
					<c:if test="${n.url!=null && n.url!=''}" >
						<a href="${n.url}" target="_blank">
						<img src="images/icon/house_go.gif" border="0"/>
						</a>
					</c:if>
		
					<c:if test="${n.file_name!=null && n.file_name!=''}" >
						<a href="getFile?type=newsPath&fileName=${n.file_name}" target="_top">
						<img src="images/icon/disk.gif" border="0"/>
						</a>
					</c:if>
		
					<c:if test="${n.email!=null && n.email!=''}" >
						<a href="mailto:${n.email}" target="_blank">
						<img src="images/icon/email_go.gif" border="0"/>
						</a>
					</c:if>			
				</td>		
				<td class="news-border" width="1"><img src="images/icon/comment_0.gif"/></td>
				
				<td class="news-border" nowrap align="left">		
				<font size="-1">
				<c:choose>
					<c:when test="${fn:length(n.schoolName)>=10}"><font size=-2>${n.schoolName}</font></c:when>
					<c:when test="${fn:length(n.schoolName)<10}">${n.schoolName}</c:when>
					<c:when test="${n.schoolName==''||n.schoolName==null}"><img src="images/icon/loading2.gif"/></c:when>
				</c:choose>
				</font>		
				</td>		
			</tr>	
		</table>
		</c:forEach>
		
		<table width="100%" onClick="showObj('allNews')" style="cursor:pointer;">
			<tr>
				<td width="1"><img src="images/icon/feed_add.gif"></td>
				<td><b>更多訊息</b></td>		
			</tr>
		</table>
		
		<table cellspacing="0" cellpadding="0" width="100%" id="allNews" style="display:none;"><tr><td>
		<c:forEach items="${news}" begin="10" end="20" var="n">
		<table width="100%" class="news-border" id="news${n.Oid}" 
		onclick="getNews('${n.Oid}', 'newsInfo'), showNews(this.id);">
			<tr>
				<td class="news-border" width="1">		
				<c:choose>
					<c:when test="${n.top!=null}"><img src="images/icon/icon_info_exclamation.gif"/></c:when>
					<c:when test="${n.top==null}"><img src="images/icon/feed.gif"/></c:when>
				</c:choose>		
				</td>
				<td class="news-border" style="cursor:pointer;" nowrap><font size="-1">${n.StartDate}</font></td>		
				<td class="news-border" style="cursor:pointer;" width="100%" align="left" nowrap>		
				<c:choose>
					<c:when test="${fn:length(n.title)>=25&&fn:length(n.title)<=45}"><font size="-1">${n.title}</font></c:when>
					<c:when test="${fn:length(n.title)<25}">${n.title}</c:when>
					<c:when test="${fn:length(n.title)>=45}"><font size="-2">${fn:substring(n.title, 0, 50)}...</font></c:when>
				</c:choose>		
				</td>		
				<td nowrap class="news-border">
					<c:if test="${n.url!=null && n.url!=''}" >
						<a href="${n.url}">
						<img src="images/icon/disk.gif" border="0" target="_blank"/>
						</a>
					</c:if>
					<c:if test="${n.file_name!=null && n.file_name!=''}" >
						<a href="getFile?type=newsPath&fileName=${n.file_name}" target="_top">
						<img src="images/icon/house_go.gif" border="0"/>
						</a>
					</c:if>
					<c:if test="${n.email!=null && n.email!=''}" >
						<a href="mailto:${n.email}" target="_blank">
						<img src="images/icon/email_go.gif" border="0"/>
						</a>
					</c:if>			
				</td>		
				<td class="news-border" width="1"><img src="images/icon/comment_0.gif"/></td>
				
				<td class="news-border" nowrap align="left">		
				<font size="-1">
				<c:choose>
					<c:when test="${fn:length(n.schoolName)>=10}"><font size=-2>${n.schoolName}</font></c:when>
					<c:when test="${fn:length(n.schoolName)<10}">${n.schoolName}</c:when>
					<c:when test="${n.schoolName==''||n.schoolName==null}"><img src="images/icon/loading2.gif"/></c:when>
				</c:choose>
				</font>		
				</td>		
			</tr>	
		</table>
		</c:forEach>
		
		
		<table width="100%" style="cursor:pointer;">
			<tr>
				<td width="1"><img src="images/icon/feed_go.gif"></td>
				<td><b><a href="AllNews.jspx?menuOid=276">全部訊息</a></b></td>		
			</tr>
		</table>
		
		
		</td></tr>
		</table>
		
		</td>
	</tr>
</table>
</div>



<table id="newsBox" style="display:none" class="new_ds_box" width="85%">	
	<tr>
		<td
		onClick="document.getElementById('newsBox').style.display='none', document.getElementById('loadMsg').style.display='none'">
		<table style="cursor:pointer;" width="100%">
			<tr>
				<td><img src="images/icon/icon_feed.gif"></td>
				<td style="cursor:pointer;" nowrap><font color="555555">訊息內容</font></td>
				<td width="100%" align="right"><input type="button" value="關閉" 
				class="gCancelSmall" onClick="document.getElementById('newsBox').style.display='none', document.getElementById('loadMsg').style.display='none'"/></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td>
		<div id="newsInfo" style="width:100%; padding: 10px;">
		<img src="images/icon/loading2.gif"/>
		</div>
		</td>
	</tr>
	<tr>
		<td align="right" style="cursor:pointer;">
		<div id="newsInfosub">
		
		</div>
		</td>
	</tr>
</table>


<script>

var objectId='newsInfo';
try{
	var XMLHttpReqDyna=new XMLHttpRequest();	
}catch(e){
	var XMLHttpReqDyna=false;
}

var XMLHttpRequest=null;

function createXMLHttpRequestDyna(){
	if (window.ActiveXObject) {
        XMLHttpReqDyna = new ActiveXObject("Microsoft.XMLHTTP");
    }
    else if (window.ActiveXObject) {
        XMLHttpReqDyna = new ActiveXObject("Msxml2.XMLHTTP");
    }
    
    if(!XMLHttpReqDyna) {
    	alert("作業系統不支援這次的非同步請求");
	}
}

function proceDyna(){
	if(XMLHttpReqDyna.readyState==4){
 		if(XMLHttpReqDyna.status==200){
 			document.getElementById(objectId).innerHTML='';
 			//alert("work1");
 			content=XMLHttpReqDyna.responseXML.getElementsByTagName("content")[0].firstChild.data;
 			//content=content.replace(/</g,"&lt;");
 			//content=content.replace(/>/g,"&gt;");
 			//alert("work2");
 			document.getElementById(objectId).innerHTML=(content);
 			document.getElementById(objectId+"sub").innerHTML=(XMLHttpReqDyna.responseXML.getElementsByTagName("username")[0].firstChild.data+
 			" 於"+XMLHttpReqDyna.responseXML.getElementsByTagName("newsdate")[0].firstChild.data+" 發佈"); 			
 		}
 	}
}

function getNews(Oid, objId) {
		objectId=objId;
		sendDyna('GetNews?Oid='+Oid+'&'+Math.floor(Math.random()*999));
	}
	
function sendDyna(url){
	createXMLHttpRequestDyna();
	try{
		XMLHttpReqDyna.open("GET",url,true);
	}catch(e){
		alert("ajax error");
	}
	
	XMLHttpReqDyna.onreadystatechange=proceDyna;
	XMLHttpReqDyna.send(null);
}
 	
function showAllNews(){
	if(document.getElementById('allNews').style.display=='none'){
		document.getElementById('allNews').style.display='inline';
	}else{
		document.getElementById('allNews').style.display='none';
	}
}

function gt_cnameLeftDyna(ed) {
	var tmp = ed.offsetLeft;
	ed = ed.offsetParent
	while(ed) {
		tmp += ed.offsetLeft;
		ed = ed.offsetParent;
	}
	return tmp;
}
	
function gt_cnameTopDyna(ed) {
	var tmp = ed.offsetTop;
	ed = ed.offsetParent
	while(ed) {
		tmp += ed.offsetTop;
		ed = ed.offsetParent;
	}
	return tmp+24;
}	
			
function showNews(oId){
	
	document.getElementById("newsBox").style.display="inline";	
	document.getElementById("newsBox").style.left=gt_cnameLeftDyna(document.getElementById(oId));	//y座標
	document.getElementById("newsBox").style.top=gt_cnameTopDyna(document.getElementById(oId));	//x座標
	document.getElementById("loadMsg").style.display="inline";		
	document.getElementById("loadMsg").style.width=document.body.scrollWidth;
	
	if (navigator.appName.indexOf("Microsoft")!=-1) {		
		var scrollHeight=document.body.scrollHeight;
		var clientHeight=document.body.clientHeight;
		
		if (scrollHeight>clientHeight){
			document.getElementById('loadMsg').style.height=scrollHeight+50;// IE
		}else{
			document.getElementById('loadMsg').style.height=clientHeight;// IE
		}
	}else{
		document.getElementById('loadMsg').style.height=document.body.scrollHeight;// FF, ns
	}
}
</script>