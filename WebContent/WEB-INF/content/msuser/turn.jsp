<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html> 
<html> 
<head> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<meta http-equiv="refresh" content="4;url=${pageContext.request.contextPath}/msuser/main/viewMsuserData?id=${session.msuserid }">
	<title>提示</title> 
</head> 
<body> 
	<script type="text/javascript">
	function refresh()
	{
		document.getElementById("authImg").src="${pageContext.request.contextPath}/auth.jpg?now=" + new Date();
	}
	
	</script>

	<div class="loginDiv">
		<div class="loginTop">
			<p style="font-family:'黑体';font-size:22px;color:#FFFFFF">请用户完善用户资料，并修改原始密码！</p>
			<p style="font-family:'黑体';font-size:16px;color:#FFFFFF">如果没有跳转请点击 <a href="${pageContext.request.contextPath}/msuser/main/viewMsuserData?id=${session.msuserid }">[我的资料]</a>，修改用户资料。
		</div>
		
	</div>


</body> 
</html> 