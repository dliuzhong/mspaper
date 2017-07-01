<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html> 
<html> 
<head> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<title>投稿用户登录</title> 
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
			<p style="font-family:'黑体';font-size:22px;color:#FFFFFF">XXX报社电子报刊在线投稿登录</p>
		</div>
		<div class="inner1">
			<form action="doMsuserLogin" method="post">

				<table class="logintable">
					<tr>
						<td rowspan="4"></td>
						<td width="100"><span for="username">邮&nbsp;&nbsp;&nbsp;&nbsp;箱：</span></td>
						<td><span><input size="26" type="text" name="username"
								id="username" class="reqd"
								value="<s:property value='model.username'/>" />
						</span>&nbsp;</td>
					</tr>
					<tr>

						<td><span for="password">密&nbsp;&nbsp;&nbsp;&nbsp;码：</span></td>
						<td><span><input size="26" type="password"
								name="password" id="password" class="reqd" />&nbsp;</span></td>
					</tr>
					<tr>

						<td><span for="code">验证码：</span></td>
						<td><span><input type="text" name="vercode"
								id="checkwd" class="reqd" style="width:90px;" />&nbsp; <a
								href="javascript:refresh();"><img
									src="${pageContext.request.contextPath}/auth.jpg" id="authImg" />
							</a>
						</span></td>
					</tr>
					<tr>

						<td></td>
						<td><small style="color: red;font-size: 12px;"><s:actionerror /></small></td>

					</tr>
					<tr>
						<td></td>
						<td></td>
						<td><input type="submit" name="submit" id="submit" value="登录" />&nbsp;
							<input type="reset" name="reset" id="reset" value="重置" /></td>

					</tr>


				</table>
			</form>

		</div>
		<div class="inner2">
			<center style="font-size: 14px;">投稿系统说明</center><br>
			1. 本系统为XXX报社电子报刊在线投稿系统。<br>
			2. 用户须为注册用户，登录方可进行投稿。如想获取帐户，请联系XXX报社电子报刊。<br>
			电话：0816-XXXXXXX，邮箱：XXX@XXX.XXX。<br>
			3. 本系统最终解释权归XXX报社电子报刊所有。<br><br>
			<center>&copy;2014&nbsp;XXX报社电子报刊. All Rights Reserved. </center>
		</div>
	</div>


</body> 
</html> 