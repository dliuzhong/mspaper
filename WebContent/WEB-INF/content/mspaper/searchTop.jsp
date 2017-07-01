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
<div style=" width: 100%;">
<div style="width:1000px;margin: 0 auto; height:110px; ">
<img src="../paper/img/log.png" height="88px" ><div style="font-size: 26px;color: #000000;font-family:'楷体';margin-left: 50px;">&nbsp;&nbsp;校报新闻搜索</div></div>

<div style="width: 1000px;margin: 0 auto;">
  <div style="text-align: right;height:24px;width: 100%"> 
  	<span class="top">
  		
  		
  		<a class="top" href="../admin/login" target="_blank">管理登录</a>
  		|
  		<a class="top" href="${pageContext.request.contextPath}/msuser/login" target="_blank" style="color: blue;">[在线投稿]</a></span>
  		<br>
  		
  </div>
 </div>
</div>
</div>
 <script type="text/javascript" src="js/main.js"></script>