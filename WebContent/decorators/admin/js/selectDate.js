
var DS_x,DS_y;

function dateSelector() //����dateSelector��������ʵ��һ��������ʽ�����������
{
var myDate=new Date();
this.year=myDate.getFullYear() + 1; //����year���ԣ���ݣ�Ĭ��ֵΪ��ǰϵͳ��ݡ�
this.month=myDate.getMonth()+1; //����month���ԣ��·ݣ�Ĭ��ֵΪ��ǰϵͳ�·ݡ�
this.date=myDate.getDate(); //����date���ԣ��գ�Ĭ��ֵΪ��ǰϵͳ���ա�
this.inputName=''; //����inputName���ԣ���������name��Ĭ��ֵΪ�ա�ע�⣺��ͬһҳ�г��ֶ����������򣬲������ظ���name��
this.display=display; //����display������������ʾ���������
}

function display() //����dateSelector��display����������ʵ��һ��������ʽ������ѡ���
{
var week=new Array('��','һ','��','��','��','��','��');

document.write("<style type=text/css>");
document.write(" .ds_font td,span { font: normal 12px ����; color: #000000; }");
document.write(" .ds_border { border: 1px solid #000000; cursor: hand; background-color: #DDDDDD }");
document.write(" .ds_border2 { border: 1px solid #000000; cursor: hand; background-color: #DDDDDD }");
document.write("</style>");

document.write("<input name='cbtime' style='text-align:left;' id='DS_"+this.inputName+"' size='10' value='"+this.year+"-"+((this.month<10)?"0"+this.month:this.month) +"-"+((this.date<10)?"0"+this.date:this.date)+"' title='˫���ɽ��б༩' ondblclick='this.readOnly=false;this.focus()' onblur='this.readOnly=true' readonly>");
document.write("<button style='width:60px;height:20px;font-size:13px;margin:1px;border:1px solid #A4B3C8;background-color:#DFE7EF;' type=button onclick=this.nextSibling.style.display='block' onfocus=this.blur()>ѡ������</button>");

document.write("<div style='position:absolute;display:none;text-align:center;width:0px;height:0px;overflow:visible' onselectstart='return false;'>");
document.write(" <div style='position:absolute;left:-60px;top:20px;width:142px;height:165px;background-color:#F6F6F6;border:1px solid #245B7D;' class=ds_font>");
document.write(" <table cellpadding=0 cellspacing=1 width=140 height=20 bgcolor=#CEDAE7 onmousedown='DS_x=event.x-parentNode.style.pixelLeft;DS_y=event.y-parentNode.style.pixelTop;setCapture();' onmouseup='releaseCapture();' onmousemove='dsMove(this.parentNode)' style='cursor:move;'>");
document.write(" <tr align=center>");
document.write(" <td width=12% onmouseover=this.className='ds_border' onmouseout=this.className='' onclick=subYear(this) title='��С���'><<</td>");
document.write(" <td width=12% onmouseover=this.className='ds_border' onmouseout=this.className='' onclick=subMonth(this) title='��С�·�'><</td>");
document.write(" <td width=52%><b>"+this.year+"</b><b>��</b><b>"+this.month+"</b><b>��</b></td>");
document.write(" <td width=12% onmouseover=this.className='ds_border' onmouseout=this.className='' onclick=addMonth(this) title='�����·�'>></td>");
document.write(" <td width=12% onmouseover=this.className='ds_border' onmouseout=this.className='' onclick=addYear(this) title='�������'>>></td>");
document.write(" </tr>");
document.write(" </table>");

document.write(" <table cellpadding=0 cellspacing=0 width=140 height=20 onmousedown='DS_x=event.x-parentNode.style.pixelLeft;DS_y=event.y-parentNode.style.pixelTop;setCapture();' onmouseup='releaseCapture();' onmousemove='dsMove(this.parentNode)' style='cursor:move;'>");
document.write(" <tr align=center>");
for(i=0;i<7;i++)
document.write(" <td>"+week[i]+"</td>");
document.write(" </tr>");
document.write(" </table>");

document.write(" <table cellpadding=0 cellspacing=2 width=140 bgcolor=#EEEEEE>");
for(i=0;i<6;i++)
{
document.write(" <tr align=center>");
for(j=0;j<7;j++)
document.write(" <td width=10% height=16 onmouseover=if(this.innerText!=''&&this.className!='ds_border2')this.className='ds_border' onmouseout=if(this.className!='ds_border2')this.className='' onclick=getvalue(this,document.all('DS_"+this.inputName+"'))></td>");
document.write(" </tr>");
}
document.write(" </table>");

document.write(" <span style=cursor:hand onclick=this.parentNode.parentNode.style.display='none'>���رա�</span>");
document.write(" </div>");
document.write("</div>");

dateShow(document.all("DS_"+this.inputName).nextSibling.nextSibling.childNodes[0].childNodes[2],this.year,this.month)
}

function subYear(obj) //��С���
{
var myObj=obj.parentNode.parentNode.parentNode.cells[2].childNodes;
myObj[0].innerHTML=eval(myObj[0].innerHTML)-1;
dateShow(obj.parentNode.parentNode.parentNode.nextSibling.nextSibling,eval(myObj[0].innerHTML),eval(myObj[2].innerHTML))
}

function addYear(obj) //�������
{
var myObj=obj.parentNode.parentNode.parentNode.cells[2].childNodes;
myObj[0].innerHTML=eval(myObj[0].innerHTML)+1;
dateShow(obj.parentNode.parentNode.parentNode.nextSibling.nextSibling,eval(myObj[0].innerHTML),eval(myObj[2].innerHTML))
}

function subMonth(obj) //��С�·�
{
var myObj=obj.parentNode.parentNode.parentNode.cells[2].childNodes;
var month=eval(myObj[2].innerHTML)-1;
if(month==0)
{
month=12;
subYear(obj);
}
myObj[2].innerHTML=month;
dateShow(obj.parentNode.parentNode.parentNode.nextSibling.nextSibling,eval(myObj[0].innerHTML),eval(myObj[2].innerHTML))
}

function addMonth(obj) //�����·�
{
var myObj=obj.parentNode.parentNode.parentNode.cells[2].childNodes;
var month=eval(myObj[2].innerHTML)+1;
if(month==13)
{
month=1;
addYear(obj);
}
myObj[2].innerHTML=month;
dateShow(obj.parentNode.parentNode.parentNode.nextSibling.nextSibling,eval(myObj[0].innerHTML),eval(myObj[2].innerHTML))
}

function dateShow(obj,year,month) //��ʾ���·ݵ���
{
var myDate=new Date(year,month-1,1);
var today=new Date();
var day=myDate.getDay();
var selectDate=obj.parentNode.parentNode.previousSibling.previousSibling.value.split('-');
var length;
switch(month)
{
case 1:
case 3:
case 5:
case 7:
case 8:
case 10:
case 12:
length=31;
break;
case 4:
case 6:
case 9:
case 11:
length=30;
break;
case 2:
if((year%4==0)&&(year%100!=0)||(year%400==0))
length=29;
else
length=28;
}
for(i=0;i<obj.cells.length;i++)
{
obj.cells[i].innerHTML='';
obj.cells[i].style.color='';
obj.cells[i].className='';
}
for(i=0;i<length;i++)
{
obj.cells[i+day].innerHTML=(i+1);
if(year==today.getFullYear()&&(month-1)==today.getMonth()&&(i+1)==today.getDate())
obj.cells[i+day].style.color='red';
if(year==eval(selectDate[0])&&month==eval(selectDate[1])&&(i+1)==eval(selectDate[2]))
obj.cells[i+day].className='ds_border2';
}
}

function getvalue(obj,inputObj) //��ѡ������ڴ��������
{
var myObj=inputObj.nextSibling.nextSibling.childNodes[0].childNodes[0].cells[2].childNodes;
if(obj.innerHTML)
inputObj.value=myObj[0].innerHTML+"-"+((myObj[2].innerHTML<10)?"0"+myObj[2].innerHTML:myObj[2].innerHTML)+"-"+((obj.innerHTML<10)?"0"+obj.innerHTML:obj.innerHTML);
inputObj.nextSibling.nextSibling.style.display='none';
for(i=0;i<obj.parentNode.parentNode.parentNode.cells.length;i++)
obj.parentNode.parentNode.parentNode.cells[i].className='';
obj.className='ds_border2'
}

function dsMove(obj) //ʵ�ֲ������
{
if(event.button==1)
{
var X=obj.clientLeft;
var Y=obj.clientTop;
obj.style.pixelLeft=X+(event.x-DS_x);
obj.style.pixelTop=Y+(event.y-DS_y);
}
}