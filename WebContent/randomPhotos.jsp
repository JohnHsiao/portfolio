<%@ page language="java" pageEncoding="UTF-8" errorPage="/error.jsp" %>
<div style="position:absolute; left:0px;  top:0px; z-index:-1; cursor:pointer;">
<%@ include file="/taglibs.jsp" %>
</div>
<script type="text/javascript" src="images/photobox/js/jquery.js"></script>
<script type="text/javascript" src="images/photobox/js/jquery.lightbox-0.5.js"></script>
<link rel="stylesheet" type="text/css" href="images/photobox/css/jquery.lightbox-0.5.css" media="screen" />
<!-- Ativando o jQuery lightBox plugin -->
<script type="text/javascript">
    $(function() {
        $('#gallery a').lightBox();
        $('#demo2 a').lightBox();
    });
</script>

<style type="text/css">
	/* jQuery lightBox plugin - Gallery style */
	#gallery {
		background-color: #fff;
		padding: 5px;
		/*width: 520px;*/
	}
	#gallery ul { list-style: none; margin:0;padding;0}
	#gallery ul li { display: inline; }
	#gallery ul img {
		border: 1px solid #fadfa4;
		border-width: 2px 2px 2px;
	}
	#gallery ul a:hover img {
		border: 1px solid orange;
		border-width: 2px 2px 2px;
		color: #fff;
	}
	#gallery ul a:hover { color: red; }
</style>




<script type="text/javascript">
var x = navigator;
if(x.appVersion.indexOf("IE")>0){
	document.write("<div id='demo' style='overflow:hidden; width:100%; color:#ffffff;'>");
}else{
	document.write("<div id='demo' style='overflow:hidden; width:"+(document.body.clientWidth-250)+"; color:#ffffff;'>");
}
</script>

<table align=left cellpadding=0 cellspace=0 width="100%">
	<tr>
    	<td id=demo1 valign=top>      	
      	
            	<table cellSpacing=0 cellPadding=0 border=0>
              		<TBODY id="gallery">
              		<tr>
               		<c:if test="${!empty top_album}">
               		<c:forEach items="${top_album}" var="a">
               		<td>
               			<table class="hairLineTable">
							<tr>
								<td class="hairLineTdF">		                		
		                		<a href="getFile?type=photosPath&fileName=${a.project}/${a.filename}" title=""</a>">
		                		<img src="getFile?type=photosPath&fileName=${a.project}/small/${a.filename}"  border="0" height="100"/></a>		                		
		                		</td>
		                	</tr>
	                	</table>
               		</td>
               		</c:forEach>
               		</c:if>
               		
               		
               		
               		
                		
                		<c:if test="${empty top_album}">
                		<%for(int i=0; i<5; i++){ %>
                		<td> 
                		<table class="hairLineTable" align="left">
							<tr>
								<td class="hairLineTdF">
		                		<a href="images/photobox/photos/image1.jpg" title="第1張照片"><img src="images/photobox/photos/thumb_image1.jpg"  border="0"/></a>
		            			</td>
		            		</tr>
		            	</table>
		            	</td>
		            	
		            	
		            	<td>            			
            			<table class="hairLineTable" align="left">
							<tr>
								<td class="hairLineTdF">
		                		<a href="images/photobox/photos/image2.jpg" title="第2張照片"><img src="images/photobox/photos/thumb_image2.jpg"  border="0"/></a>
		            			</td>
		            		</tr>
		            	</table>
            			</td>
		            	<td>
            			<table class="hairLineTable" align="left">
							<tr>
								<td class="hairLineTdF">
		                		<a href="images/photobox/photos/image3.jpg" title="第3張照片"><img src="images/photobox/photos/thumb_image3.jpg"  border="0"/></a>
		            			</td>
		            		</tr>
		            	</table>
            			</td>
            			
		            	<td>
            			<table class="hairLineTable" align="left">
							<tr>
								<td class="hairLineTdF">
		                		<a href="images/photobox/photos/image4.jpg" title="第4張照片"><img src="images/photobox/photos/thumb_image4.jpg"  border="0"/></a>
		            			</td>
		            		</tr>
		            	</table>
            			</td>
		            	<td>
            			<table class="hairLineTable" align="left">
							<tr>
								<td class="hairLineTdF">
		                		<a href="images/photobox/photos/image5.jpg" title="第5張照片"><img src="images/photobox/photos/thumb_image5.jpg"  border="0"/></a>
		            			</td>
		            		</tr>
		            	</table>
            			</td>            			
                		<%} %>
                		</c:if>                		
                		
                	</tr>
              	</TBODY>
            	</table>
      	</td>      	
    	<td id="demo2" valign=top></td>
	</tr>
</table>
</div>

<script>
  var speed=30;  //數值越大速度越慢
  demo2.innerHTML=demo1.innerHTML
  function Marquee(){
  if(demo2.offsetWidth-demo.scrollLeft<=0)
  demo.scrollLeft-=demo1.offsetWidth
  else{
  demo.scrollLeft++
  }
  }
  var MyMar=setInterval(Marquee,speed)
  demo.onmouseover=function() {clearInterval(MyMar)}
  demo.onmouseout=function() {MyMar=setInterval(Marquee,speed)}

 function showPhotoHelp(id){
	
	document.getElementById('news').style.left=gt_cnameLeftDyna(document.getElementById(id))-200;	//y座標
	document.getElementById('news').style.top=gt_cnameTopDyna(document.getElementById(id));	//x座標
	document.getElementById('news').style.display="inline";
	document.getElementById('loadMsg').style.display="inline";		
}
</script>