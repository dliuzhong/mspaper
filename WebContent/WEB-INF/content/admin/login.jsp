<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html> 
<head> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<title>管理员登录</title> 
</head> 
<body> 
	<script type="text/javascript">
	function refresh()
	{
		document.getElementById("authImg").src="${pageContext.request.contextPath}/auth.jpg?now=" + new Date();
	}
	
	</script>
	
      <div class="loginDiv">
      	
      	<p style="font-family:'黑体';">XXX报社电子报刊管理系统V0.1</p>
      	
      	<hr size="2" color="#FFFFFF"/>
      		<div class="inner">
            <form action="doLogin" method="post">
            	
            	<table class="logintable">
            		<tr>
            			<td rowspan="4">
            			<img src="${pageContext.request.contextPath}/img/login.png"/>&nbsp;&nbsp;
            			</td>
            			<td width="100">
            				<span for="username">用户名：</span>
            			</td>
            			<td>
            				<span><input type="text" name="username" id="username" class="reqd" value="<s:property value='model.username'/>" /></span>&nbsp;
            			</td>
            		</tr>
					<tr>
						
            			<td>
            				<span for="password">密&nbsp;&nbsp;码：</span>
            			</td>
            			<td>
            				<span><input type="password" name="password" id="password" class="reqd" />&nbsp;</span>
            			</td>
            		</tr>
            		<tr>
            			
            			<td>
            				<span for="code">验证码：</span>
            			</td>
            			<td>
            				<span><input type="text" name="vercode" id="checkwd" class="reqd" style="width:90px;"/>&nbsp;
            				<a href="javascript:refresh();"><img src="${pageContext.request.contextPath}/auth.jpg" id="authImg"/></a></span>
            			</td>
            		</tr>
            		<tr>
            			
            			<td>
            			
            			</td>
            			<td>
            				<small style="color: red;font-size: 12px;"><s:actionerror/></small>
            			</td>
            			
            		</tr>
            		<tr>
            			<td>
            			</td>
            			<td>
            			
            			</td>
            			<td>
            				<input type="submit" name="submit" id="submit" value="登录" />&nbsp;
                			<input type="reset" name="reset" id="reset" value="重置" />
            			</td>
            			
            		</tr>
					
                
               </table>
            </form>
           
           </div>
           
        </div>
   
</body> 
</html> 