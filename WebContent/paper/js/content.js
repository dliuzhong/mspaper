/*
window.onload = initFont;
*/
function initFont() {
	
		document.images[0].onclick = smallFont;
		document.images[1].onclick = bigFont;
}

function smallFont() {
	var text = document.getElementById("textDiv");
	text.style.fontSize = "13px";
}
function bigFont() {
	var text = document.getElementById("textDiv");
	text.style.fontSize = "15px";
}