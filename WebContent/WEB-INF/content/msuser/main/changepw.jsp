<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@taglib prefix="s" uri="/struts-tags"%>

<html>
  <head>
    
    <title>修改密码</title>
    
  </head>
  
  <body>
  <div class="container">
		<div class="row-fluid">
			<div class="navbar span12">
				<div class="navbar-inner">
					<span class="brand">XXX报社电子报刊在线投稿&nbsp;&nbsp;&nbsp;</span>
					<ul class="nav">

						<li><a href="myArticle?myid=${session.msuserid }" target="_self"><i class="icon-home"></i>&nbsp;我的稿件</a>
						</li>
						<li><a href="viewMsuserData?id=${session.msuserid }" target="_self"><i class="icon-user"></i>&nbsp;我的资料</a>
						</li>
						<li class="active"><a href="changepw" target="_self"><i class="icon-lock"></i>&nbsp;修改密码</a>
						</li>
						<li><a href="${pageContext.request.contextPath}/msuser/loginout" target="_self"><i class="icon-eject"></i>&nbsp;注销</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="changepwDiv">
	<div class="tipDiv">
    	<span id="tip" style="color: red;font-weight: bold;font-size: 13px;padding: 0px 5px 10px 5px;">${tip }</span>
    </div>
     <s:form action="changeMsuserPW" method="POST" id="changepwForm" >
    	<input type="hidden" id="id" name="id" value="${session.msuserid }" />
    	<s:password name="password" id="password" label="旧密码" />
    	<s:password name="newpw" id="newpw" label="新密码" />
    	<s:password name="renewpw" id="renewpw" label="确认新密码" />
    	
		<s:submit style="width:60px;" value="修改" id="submit"/> <s:reset  style="width:60px;" value="重置"/>
		
    </s:form>
   </div>
  </body>
</html>
