window.onload = initAdd;
function initAdd() {
	AddRow();
	AddRow();
	AddRow();
}
function findObj(theObj, theDoc)
{ 
	var p, i, foundObj; 
	if(!theDoc) 
		theDoc = document; 
	if((p = theObj.indexOf("?")) > 0 && parent.frames.length) 
	{    
		theDoc = parent.frames[theObj.substring(p+1)].document;
		theObj = theObj.substring(0,p); 
	}
	if(!(foundObj = theDoc[theObj]) && theDoc.all)
		foundObj = theDoc.all[theObj]; 
	for (i=0; !foundObj && i < theDoc.forms.length; i++)     
		foundObj = theDoc.forms[i][theObj];
	for(i=0; !foundObj && theDoc.layers && i < theDoc.layers.length; i++)
		foundObj = findObj(theObj,theDoc.layers[i].document);
	if(!foundObj && document.getElementById)
		foundObj = document.getElementById(theObj);
		return foundObj;
}
//添加一个参与人填写行
function AddRow(){ //读取最后一行的行号，存放在txtTRLastIndex文本框中 
	var txtTRLastIndex = findObj("txtTRLastIndex", document);
	var rowID = parseInt(txtTRLastIndex.value);

	var layTable = findObj("layTable",document);
	//添加行
	var newTR = layTable.insertRow(layTable.rows.length);
	newTR.id = "tableItem" + rowID;

	//添加列:版号
	var newNameTD = newTR.insertCell(0);
	//添加列内容
	newNameTD.innerHTML = "第" + (newTR.rowIndex + 1).toString() + "版";
	//添加列:图片和pdf
	var newNameTD=newTR.insertCell(1);
	//添加列内容
	newNameTD.innerHTML = "图片：<input type='file' name='file_pic" + (newTR.rowIndex + 1).toString() + "'><br>PDF：<input type='file' name='file_pdf" + (newTR.rowIndex + 1).toString() + "'></td>";

	//添加列:主题和校对
	var newEmailTD = newTR.insertCell(2);
	//添加列内容
	newEmailTD.innerHTML = "主题：<input type='text' name='zt' id='zt'><br>编辑/校对：<input type='text' name='bj' id='bj'>";
	//添加列:删除按钮
	var newDeleteTD = newTR.insertCell(3);
	//添加列内容
	newDeleteTD.innerHTML = "<div align='center' style='width:40px'><a href='javascript:;' onclick=\"DeleteRow('tableItem" + rowID + "')\">删除</a></div>";

	//将行号推进下一行
	txtTRLastIndex.value = (rowID + 1).toString() ;
}
	//删除指定行
	function DeleteRow(rowid){
		var layTable = findObj("layTable",document);
		var tableItem = findObj(rowid,document);

		//获取将要删除的行的Index
		var rowIndex = tableItem.rowIndex;

		//删除指定Index的行
		layTable.deleteRow(rowIndex);

		//重新排列序号，如果没有序号，这一步省略
		for(i = rowIndex;i < layTable.rows.length;i++){
			layTable.rows[i].cells[0].innerHTML = "第" + (i + 1).toString() + "版";
		}
}