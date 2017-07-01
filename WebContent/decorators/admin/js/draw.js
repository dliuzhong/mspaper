/*
var wId = "w";
var dID = "digiDiv";
var index = 0;
var startX = 0, startY = 0;
var flag = false;
var retcLeft = 0, retcTop = 0, retcHeight = 0, retcWidth = 0;
var divRetc;
document.onmousedown = function(e){
	try{
		
		var evt = window.event || e;
		var scrollLeft = document.body.scrollLeft || document.documentElement.scrollLeft;
		var scrollTop = document.body.scrollTop || document.documentElement.scrollTop;
		startX = evt.clientX + scrollLeft;
		startY = evt.clientY + scrollTop;
		if (startX < 400) {
			if (divRetc) {
				document.body.removeChild(divRetc);
			}
			flag = true;
			
			
			startX = evt.clientX + scrollLeft;
			startY = evt.clientY + scrollTop;
			index++;
		
			var div = document.createElement("div");
			div.id = wId + index;
			div.className = "div";
			div.style.marginLeft = startX + "px";
			div.style.marginTop = startY + "px";
			document.body.appendChild(div);
			
			var digiDiv = document.createElement("div");
			digiDiv.id = dID + index;
			digiDiv.className = "digidiv";
			digiDiv.style.marginLeft = startX + 5 + "px";
			digiDiv.style.marginTop = startY + 5 + "px";
			document.body.appendChild(digiDiv);
			
		}
	}catch(e){
	//alert(e);
	}
}
document.onmouseup = function(){
	try{
		if (retcLeft < 400) {
			document.body.removeChild($(wId + index));
			document.body.removeChild($("digiDiv" + index));
			divRetc = document.createElement("div");
			divRetc.className = "retc";
			divRetc.style.left = retcLeft + "px";
			divRetc.style.top = retcTop + "px";
			divRetc.style.width = retcWidth + "px";
			divRetc.style.height = retcHeight + "px";
			document.body.appendChild(divRetc);
			document.getElementById("left").value = retcLeft;
			document.getElementById("top").value = retcTop;
			document.getElementById("width").value = retcWidth;
			document.getElementById("height").value = retcHeight;
			
		}
		retcLeft = 0;
		retcTop = 0;
		retcWidth = 0;
		retcHeight = 0;
		startX = 0;
		startY = 0;

	}catch(e){
		//alert(e);
	}
	flag = false;
}
document.onmousemove = function(e){
	var evt = window.event || e;
	if(flag && evt.clientX < 425&& evt.clientY < 650){
	try{
		
		var scrollTop = document.body.scrollTop || document.documentElement.scrollTop;
		var scrollLeft = document.body.scrollLeft || document.documentElement.scrollLeft;
		retcLeft = (startX - evt.clientX - scrollLeft > 0 ? evt.clientX + scrollLeft : startX);
		retcTop = (startY - evt.clientY - scrollTop > 0 ? evt.clientY + scrollTop : startY);
		retcHeight = Math.abs(startY - evt.clientY - scrollTop);
		retcWidth = Math.abs(startX - evt.clientX - scrollLeft);
		$(wId + index).style.marginLeft = retcLeft;
		$(wId + index).style.marginTop = retcTop;
		$(wId + index).style.width = retcWidth;
		$(wId + index).style.height = retcHeight;
		$(dID + index).style.marginLeft = retcLeft + retcWidth + 5;
		$(dID + index).style.marginTop = retcTop + retcHeight + 5;
		$(dID + index).innerHTML = retcWidth + " " + retcHeight;
		}catch(e){
			//alert(e);
		}	
	}
}
var $ = function(id){
	return document.getElementById(id);
}
*/


