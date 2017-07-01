function changeSpecial() {
	var div = document.getElementById("special");
	var select = div.options[div.selectedIndex].value;
	if (select != "") {
		var specialDiv = document.getElementById("specialDiv");
		specialDiv.style.display = "none";
		
	} else {
		var specialDiv = document.getElementById("specialDiv");
		specialDiv.style.display = "block";
	}
}
function changeSpecialNew() {
	var id = event.srcElement.id;
	if (id == "checkSpecail") {
		var specialDiv = document.getElementById("specialNewDiv");
		if (specialDiv.style.display == "none")
			specialDiv.style.display = "block";
		else
			specialDiv.style.display = "none";
	}
}