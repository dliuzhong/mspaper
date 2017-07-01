<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
  <head>
    
    <title>用户资料</title>
 
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
						<li class="active"><a href="viewMsuserData?id=${session.msuserid }" target="_self"><i class="icon-user"></i>&nbsp;我的资料</a>
						</li>
						<li><a href="changepw" target="_self"><i class="icon-lock"></i>&nbsp;修改密码</a>
						</li>
						<li><a href="${pageContext.request.contextPath}/msuser/loginout" target="_self"><i class="icon-eject"></i>&nbsp;注销</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="changeDataDiv">
		<div class="tipDiv">
			<span id="tip" style="color: red;font-weight: bold;font-size: 12px;"></span>
		</div>
		<s:form action="changeMsuserData" method="POST" id="dataForm">
			<input type="hidden" id="id" name="id" value="${model.id}" />
			<s:textfield name="email" id="email" value="%{#request.model.email}"
				label="*邮箱" onblur="validateEmail(this);" />

			<s:textfield name="name" id="name" value="%{#request.model.name}"
				label="*姓名" />
			<s:select list="departmentList" listValue="name" listKey="id"
						name="departmentid" id="departmentid" label="*部门"></s:select>
			<s:textfield name="telephone" id="telephone"
				value="%{#request.model.telephone}" label="联系电话" />
			<s:textfield name="qq" id="qq" value="%{#request.model.qq}"
				label="qq" />

			<s:submit style="width:60px" value="修改" id="submit" />
			<s:reset style="width:60px"  value="重置" />
		</s:form>
	</div>
</body>
</html>