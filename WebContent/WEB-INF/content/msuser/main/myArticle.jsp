<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
  <head>
    
    <title>我的稿件</title>
    
  </head>
  
  <body>
  	  <script>
  	  	
        KindEditor.ready(function(K) {
        	
                window.editor = K.create('#editor',
                {
            	 uploadJson : 'ajax2/uploadImage'
         		});
        });
        function refresh()
		{
			document.getElementById("authImg").src="${pageContext.request.contextPath}/auth.jpg?now=" + new Date();
		}
	</script>
	<div class="container">
		<div class="row-fluid">
			<div class="navbar span12">
				<div class="navbar-inner">
					<span class="brand">XXX报社电子报刊在线投稿&nbsp;&nbsp;&nbsp;</span>
					<ul class="nav">

						<li class="active"><a href="myArticle?myid=${session.msuserid }" target="_self"><i class="icon-home"></i>&nbsp;我的稿件</a>
						</li>
						<li><a href="viewMsuserData?id=${session.msuserid }" target="_self"><i class="icon-user"></i>&nbsp;我的资料</a>
						</li>
						<li><a href="changepw" target="_self"><i class="icon-lock"></i>&nbsp;修改密码</a>
						</li>
						<li><a href="${pageContext.request.contextPath}/msuser/loginout" target="_self"><i class="icon-eject"></i>&nbsp;注销</a>
						</li>
					</ul>
				</div>
			</div>
		</div>

		<div class="row-fluid topDiv">
			
			<div class="span4 offset1 topLeftDiv">
				欢迎！用户${session.msusername }<br><br>
				<button class="btn btn-large btn-block btn-primary"
					onclick="showAddArticle();">
					<i class="icon-pencil icon-white"></i>&nbsp;我要投稿
				</button>
			</div>

			<div class="span6 offset1 topRightDiv">
				<div>
					<i class="icon-exclamation-sign"></i>&nbsp;<font style="font-size:14px;">投稿协议说明：</font><br>
					&nbsp;&nbsp;&nbsp;&nbsp;1. 用户投稿前请完善您的个人信息，请妥善保管登陆的密码。<br>
					&nbsp;&nbsp;&nbsp;&nbsp;2. 用户所投稿件作者归用户所有，XXX报社电子报刊有权对用户所投稿件所有内容进行使用、修改（除作者字段）、删除。<br>
					&nbsp;&nbsp;&nbsp;&nbsp;3. 六种稿件状态，分别为：等待审核、审核中、修改待审、通过审核、不予采用、已发表（有发表信息）。<br>
					&nbsp;&nbsp;&nbsp;&nbsp;4. 所有稿件归XXX报社电子报刊权所有，解释权归XXX报社电子报刊所有。<br>
					&nbsp;&nbsp;&nbsp;&nbsp;5. 用户进行投稿表示同意本投稿协议说明。
				</div>
			</div>
		</div>
		<div class="row-fluid">
			<div class="span12 blank"></div>

		</div>
	</div>
	
		<div class="row-fluid addArticleDiv">
			<div class="span12">

				<div class="span9 offset2">
					<s:form action="addMyArticle" method="POST" id="articleForm">
						<br>

						<s:hidden name="msuid" id="msuid" value="%{#session.msuserid}"></s:hidden>
						<s:textfield name="title" id="title" label="标题"
							style="width: 600px"></s:textfield>
						<s:textfield name="keyword" id="keyword" label="关键字"
							style="width: 600px"></s:textfield>
						<s:select list="atList" listValue="name" listKey="id"
							name="typeid" id="typeid" label="稿件类型"></s:select>
						<s:textarea label="稿件内容" id="editor" name="content"
							style="width:670px;height:400px;">

						</s:textarea>
						
						<s:submit value="提交" id="submit" style="width:100px;"></s:submit>

					</s:form>
				</div>
			</div>

		</div>
		<div class="row-fluid">
			<div class="span12 blank"></div>

		</div>
    
	<div class="container">
		<div class="myArticleDiv">
			<div class="row-fluid myArticle">

				<div class="span12 title">
					<p class="text-left">
						<i class="icon-list"></i>&nbsp;我的稿件
					</p>

				</div>

			</div>
			<div class="row-fluid">
				<s:if test="allsum>0">
				<div class="span12">
					
					<table class="table table-hover table-bordered">
						<tr class="info" style="font-size:14px;">
							<td>序号</td>
							<td width="300">标题</td>
							<td>关键字</td>
							<td>稿件类型</td>
							<td width="80">投稿时间</td>
							<td width="70">稿件状态</td>
							<td width="120">备注</td>
							<td width="100">操作&nbsp;&nbsp;
								<button class="btn" onclick="location.reload();"
									style="cursor: pointer;">刷新</button>
							</td>
						</tr>
						<s:iterator value="al" status="st">
							<tr>
								<td><s:property value="#st.index+1" />
								</td>
								<td><s:property value="title" escape="false" />
								</td>
								<td><s:property value="keyword" escape="false" />
								</td>
								<td><s:property value="type.name" escape="false" />
								</td>
								<td><s:date name="time" format="yyyy-MM-dd hh:mm:ss" />
								</td>
								<td <s:if test="status.id==3">style="color: #E800E8"</s:if><s:if test="status.id==4">style="color: green"</s:if><s:if test="status.id==5">style="color: red"</s:if>><s:if test="publish>=1">已发表<br><s:property value="status.name" escape="false" /></s:if><s:else><s:property value="status.name" escape="false" /></s:else>
								</td>
								<td><s:if test="status.id==3">修改意见：<s:property value="changeinfo" escape="false" /><br></s:if><s:property value="other" escape="false" />
								</td>
								<td><a href="viewMyArticle?id=<s:property value="id" />"
									target="_blank">查看</a>&nbsp;&nbsp; <s:if test="status.id==1">
										<a href="deleteMyArticle?msuid=${session.msuserid }&id=<s:property value="id" />">删除</a>&nbsp;</s:if>
									<s:if test="status.id==3">
										<a href="getMyArticle?msuid=${session.msuserid }&id=<s:property value="id" />"
											target="_blank">修改</a>
									</s:if></td>
							</tr>
						</s:iterator>
					</table>

				</div>
				<div class="span4 offset7 text-right">
					第
					<s:property value="start/limit + 1" />
					页&nbsp;
					<s:if test="start>0">
						<a href="myArticle?myid=${session.msuserid }&start=${start-limit}">上一页</a>&nbsp;</s:if>
					<s:if test="start+limit < allsum">
						<a href="myArticle?myid=${session.msuserid }&start=${start+limit}">下一页</a>
					</s:if>
					（共
					<s:property value="allsum" />
					条&nbsp;共
					<s:if test="allsum%limit > 0">
						<s:property value="allsum/limit + 1" />
					</s:if>
					<s:else>
						<s:property value="allsum/limit" />
					</s:else>
					页）
					<div class="span1 offset8">
						<s:form action="myArticle" method="post" id="toForm">
							<s:hidden name="myid" value="%{#session.msuserid}"></s:hidden>
							<s:textfield style="width:50px" name="page" id="page" label="到"
								value=""></s:textfield>
							<s:submit value="跳转"></s:submit>
						</s:form>
					</div>
				</div>
				</s:if>
				<s:else>
					<div class="span4"><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;亲，你还没有投稿哦！请点击【我要投稿】！<br><br><br><br><br><br><br>
					</div>
				</s:else>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row-fluid">
			<div class="span12 blank"></div>
			<div class="span12 bottom">

				<small>&copy;2014&nbsp;XXX报社电子报刊. All Rights Reserved. </small>&nbsp;
				
				<small>建议您使用IE8+、Firefox、Chrome或Opera浏览本网页。</small><br><br>
			</div>
		</div>
	</div>
</body>
</html>
