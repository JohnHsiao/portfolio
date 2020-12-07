//自動顯示或隱藏物件
function showObj(id){
	//若FireFox
	if(getBrowser()=="FF"){
		if(document.getElementById(id).style.display=="block"){
			document.getElementById(id).style.display="none";
			return;
		}
		document.getElementById(id).style.display="block";
	}	
	//若IE
	if(getBrowser()=="IE"){		
		if(document.getElementById(id).style.display=="none"){
			document.getElementById(id).style.display="inline";
		}else{
			document.getElementById(id).style.display="none";
		}
	}	
}

//判斷瀏覽器
function getBrowser() {   
   if (window.XMLHttpRequest) { // Mozilla, Safari,...
      return "FF";
   } else if (window.ActiveXObject) { // IE
      return "IE";
   }
}

function correctPNG(){
   for(var i=0; i<document.images.length; i++){
   //for(var i=0; i<10; i++){
	var img = document.images[i]
	var imgName = img.src.toUpperCase()
	if (imgName.substring(imgName.length-3, imgName.length) == "PNG")
	   {
	   var imgID = (img.id) ? "id='" + img.id + "' " : ""
	   var imgClass = (img.className) ? "class='" + img.className + "' " : ""
	   var imgTitle = (img.title) ? "title='" + img.title + "' " : "title='" + img.alt + "' "
	   var imgStyle = "display:inline-block;" + img.style.cssText 
	   if (img.align == "left") imgStyle = "float:left;" + imgStyle
	   if (img.align == "right") imgStyle = "float:right;" + imgStyle
	   if (img.parentElement.href) imgStyle = "cursor:hand;" + imgStyle        
	   var strNewHTML = "<span " + imgID + imgClass + imgTitle
	   + " style=\"" + "width:" + img.width + "px; height:" + img.height + "px;" + imgStyle + ";"
	   + "filter:progid:DXImageTransform.Microsoft.AlphaImageLoader"
	   + "(src=\'" + img.src + "\', sizingMethod='scale');\"></span>" 
	   img.outerHTML = strNewHTML
	   i = i-1
	   }
   }
}
window.attachEvent("onload", correctPNG);