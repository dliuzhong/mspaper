/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
addOnload(initLinks);
addOnload(rolloverInit);
function addOnload(newFunction) {
	var oldOnload = window.onload;
	if (typeof oldOnload == "function") {
		window.onload = function() {
			if (oldOnload) {
				oldOnload();
			}
			newFunction();
		};
	}
	else {
		window.onload = newFunction;
	}
}




var pageArray = new Array("e1/page.html");
var mainArray = new Array("e1/main.html");
var thisPage = 0;
var n = 0;
var prev;
var next;
var now;

function initLinks() {
	prev = document.getElementById("buttonPrev");
	next = document.getElementById("buttonNext");
	
	if (navigator.appName.indexOf("Explorer") > -1) {
		n = document.getElementById("lay_sum").innerText;
	}
	else {
		n = document.getElementById("lay_sum").textContent;
	}
	
	if (n > 1) {
		for (var i = 2;i <= n; i++) {
			pageArray.push("e" + i + "/page.html");
			mainArray.push("e" + i + "/main.html");
		}
		
	} else {
		next.style.display="none";
	}
	prev.onclick = processPrev;
	next.onclick = processNext;
}

function processPrev() {
       
        thisPage--;
         if (thisPage == 0) {
        	prev.style.display="none";
        	next.style.top ="90px";
        } else {
        	prev.style.display="block";
        	next.style.top ="90px";
        }
        next.style.display="block";
		window.frames["page"].location.href = pageArray[thisPage];
        window.frames["main"].location.href = mainArray[thisPage];
        return false;
}

function processNext() {
        thisPage++;
        if (thisPage == n - 1) {
        	next.style.display="none";
        } else {
        	next.style.display="block";
        }
        prev.style.display="block";
        next.style.top ="90px";
		window.frames["page"].location.href = pageArray[thisPage];
        window.frames["main"].location.href = mainArray[thisPage];
        return false;
}



function rolloverInit() {
	for (var i = 0;i < document.images.length; i++) {
		if (document.images[i].parentNode.tagName == "A") {
			
			setupRollover(document.images[i]);
			
		}
	}
}
function setupRollover(thisImage) {
	thisImage.outImage = new Image();
	thisImage.outImage.src = thisImage.src;
	thisImage.onmouseout = function() {
		this.src = this.outImage.src;
	};
	
	thisImage.clickImage = new Image();
	thisImage.clickImage.src = "../img/" + thisImage.id + "_on.gif";
	thisImage.onclick = function() {
		this.src = this.clickImage.src;
	};
	
	thisImage.overImage = new Image();
	thisImage.overImage.src = "../img/" + thisImage.id + "_on.gif";
	thisImage.onmouseover = function() {
		this.src = this.overImage.src;
	};
}

function iFrameHeight(id,name) {
	var iframeid = document.getElementById(id); //iframe id 
 	
	if (document.getElementById) { 
		if (iframeid && !window.opera) { 
			if (iframeid.contentDocument && iframeid.contentDocument.body.offsetHeight) { 
				iframeid.height = iframeid.contentDocument.body.offsetHeight + 12;
			}
			iframeid.style.height = window.frames[id].document.body.scrollHeight;
		} 
	}   
}
 
function iFrameHeight_add(id,name) {
	var iframeid = document.getElementById(id); //iframe id 
 	
	if (document.getElementById) { 
		if (iframeid && !window.opera) { 
			if (iframeid.contentDocument && iframeid.contentDocument.body.offsetHeight) { 
				iframeid.height = iframeid.contentDocument.body.offsetHeight + 40; 
			}
			iframeid.style.height = window.frames[id].document.body.scrollHeight + 80;
		} 
	}   
}
function iFrameHeight_2(id,name) {
	var ifm= document.getElementById(id); 
	var subWeb = document.frames ? document.frames[name].document : ifm.contentDocument;
	if(ifm != null && subWeb != null) {
		if (subWeb.body.scrollHeight > 150) {
			ifm.height = subWeb.body.scrollHeight;
		} else {
			ifm.height = 1;
		}
	     
	 }   
}


