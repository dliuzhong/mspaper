<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@taglib prefix="s" uri="/struts-tags"%><style type="text/css">
<!--
.STYLE1 {color: #FFFFFF}
-->
</style>
<script type="text/javascript">
// seletion 跳转
function jumpPage() {
	var newLoc = document.getElementById("newLocation");
	var newPage = newLoc.options[newLoc.selectedIndex].value;
	if (newPage != "")
		window.location = "${pageContext.request.contextPath}/paper/" + newPage;
}
</script>
<div style="width: 100%;">
<div style="width: 1000px;margin: 0 auto;">
<div style="height:90px;">
<img src="../img/log.png" height="88px" style="margin-left: 50%;">
</div>
<div>
  <div style="text-align: right;line-height:20px;height:50px;width: 500px;position: relative;margin-left: 500px;">
  	<span class="top">
  		
  		
  		<a class="top" href="../../admin/login" target="_blank">管理登录</a>
  		|
  		<a class="top" href="${pageContext.request.contextPath}/msuser/login" target="_blank" style="color: blue;">[在线投稿]</a></span>
  		<br>
  		<div style="float:right;">
  		<small class="prevPaper" id="prevPaper"><a href="${pageContext.request.contextPath}/mspaper/prev?id=${id}">上一期</a>&nbsp;&nbsp;</small>
  		<div style="float: left;">
  		<select id="newLocation" onchange="jumpPage()">
  			<option value="<s:property value="paperfirst.paper" />" selected>往期查询</option>
  			<s:iterator value="papers" status="st">
  			<option value="<s:property value="paper" />">第<s:property value="paper" />期--<s:date name="cbtime" format="yyyy/MM/dd" /></option>
  			</s:iterator>
  		</select>
  		</div>
  		<small class="nextPaper" id="nextPaper"><a href="${pageContext.request.contextPath}/mspaper/next?id=${id}">下一期</a></small>
  		</div>
  		<br><br>
  </div>
 </div>
</div>
</div>
 <script type="text/javascript" src="js/main.js"></script>