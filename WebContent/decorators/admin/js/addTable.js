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
//���һ����������д��
function AddRow(){ //��ȡ���һ�е��кţ������txtTRLastIndex�ı����� 
	var txtTRLastIndex = findObj("txtTRLastIndex", document);
	var rowID = parseInt(txtTRLastIndex.value);

	var layTable = findObj("layTable",document);
	//�����
	var newTR = layTable.insertRow(layTable.rows.length);
	newTR.id = "tableItem" + rowID;

	//�����:���
	var newNameTD = newTR.insertCell(0);
	//���������
	newNameTD.innerHTML = "��" + (newTR.rowIndex + 1).toString() + "��";
	//�����:ͼƬ��pdf
	var newNameTD=newTR.insertCell(1);
	//���������
	newNameTD.innerHTML = "ͼƬ��<input type='file' name='file_pic" + (newTR.rowIndex + 1).toString() + "'><br>PDF��<input type='file' name='file_pdf" + (newTR.rowIndex + 1).toString() + "'></td>";

	//�����:�����У��
	var newEmailTD = newTR.insertCell(2);
	//���������
	newEmailTD.innerHTML = "���⣺<input type='text' name='zt' id='zt'><br>�༭/У�ԣ�<input type='text' name='bj' id='bj'>";
	//�����:ɾ����ť
	var newDeleteTD = newTR.insertCell(3);
	//���������
	newDeleteTD.innerHTML = "<div align='center' style='width:40px'><a href='javascript:;' onclick=\"DeleteRow('tableItem" + rowID + "')\">ɾ��</a></div>";

	//���к��ƽ���һ��
	txtTRLastIndex.value = (rowID + 1).toString() ;
}
	//ɾ��ָ����
	function DeleteRow(rowid){
		var layTable = findObj("layTable",document);
		var tableItem = findObj(rowid,document);

		//��ȡ��Ҫɾ�����е�Index
		var rowIndex = tableItem.rowIndex;

		//ɾ��ָ��Index����
		layTable.deleteRow(rowIndex);

		//����������ţ����û����ţ���һ��ʡ��
		for(i = rowIndex;i < layTable.rows.length;i++){
			layTable.rows[i].cells[0].innerHTML = "��" + (i + 1).toString() + "��";
		}
}