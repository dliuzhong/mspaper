<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>XXX报社电子报刊管理系统</title>
  	<style type="text/css">
  		img {
  			width : 45px;
  		}
  	</style>
  </head>
  
  <body>
    <script type="text/javascript">
		Ext.onReady(function() {
			
			var html = '<div class="selectDiv">'+
			'<table style="margin:5px auto 0px auto;text-align: center;">'+
				'<tr>'+
					'<s:if test="#session.adminGrade==1"><td><a href="new/start" target="main"><img src="${pageContext.request.contextPath}/img/ad/new.png" style="border: none;"/><br/>添加新报纸</a><br/>'+
					'</td></tr>'+
					'<tr><td><a href="search/index" target="main"><img src="${pageContext.request.contextPath}/img/ad/find.png" style="border: none;"/><br/>数据搜索</a><br/>'+
					'</td></tr>'+
					'<tr><td><a href="changepw" target="main"><img src="${pageContext.request.contextPath}/img/ad/changepw.png" style="border: none;"/><br/>密码修改</a><br/>'+
					'</td></tr>'+
					'<tr><td><a href="help" target="main"><img src="${pageContext.request.contextPath}/img/ad/help.png" style="border: none;"/><br/>帮助</a>'+
					'</td></tr>'+
					'<tr><td><a href="../loginout"><img src="${pageContext.request.contextPath}/img/ad/exit.png" style="border: none;"/><br/>注销</a>'+
					'</td></tr></s:if>'+
				
					'<s:if test="#session.adminGrade==2"><td><a href="new/start" target="main"><img src="${pageContext.request.contextPath}/img/ad/new.png" style="border: none;"/><br/>添加新报纸</a><br/>'+
					'</td></tr>'+
					'<tr><td><a href="edit/index" target="main"><img src="${pageContext.request.contextPath}/img/ad/edit.png" style="border: none;"/><br/>管理旧报纸</a><br/>'+
					'</td></tr>'+
					'<tr><td><a href="search/index" target="main"><img src="${pageContext.request.contextPath}/img/ad/find.png" style="border: none;"/><br/>数据搜索</a><br/>'+
					'</td></tr>'+
					'<tr><td><a href="article/index" target="main"><img src="${pageContext.request.contextPath}/img/ad/article.png" style="border: none;"/><br/>投稿管理</a><br/>'+
					'</td></tr>'+
					'<tr><td><a href="changepw" target="main"><img src="${pageContext.request.contextPath}/img/ad/changepw.png" style="border: none;"/><br/>密码修改</a><br/>'+
					'</td></tr>'+
					'<tr><td><a href="help" target="main"><img src="${pageContext.request.contextPath}/img/ad/help.png" style="border: none;"/><br/>帮助</a>'+
					'</td></tr>'+
					'<tr><td><a href="../loginout"><img src="${pageContext.request.contextPath}/img/ad/exit.png" style="border: none;"/><br/>注销</a>'+
					'</td></tr></s:if>'+
				
					'<s:if test="#session.adminGrade==3"><td><a href="new/start" target="main"><img src="${pageContext.request.contextPath}/img/ad/new.png" style="border: none;"/><br/>添加新报纸</a><br/>'+
					'</td></tr>'+
					'<tr><td><a href="edit/index" target="main"><img src="${pageContext.request.contextPath}/img/ad/edit.png" style="border: none;"/><br/>管理旧报纸</a><br/>'+
					'</td></tr>'+
					'<tr><td><a href="search/index" target="main"><img src="${pageContext.request.contextPath}/img/ad/find.png" style="border: none;"/><br/>数据搜索</a><br/>'+
					'</td></tr>'+
					'<tr><td><a href="article/index" target="main"><img src="${pageContext.request.contextPath}/img/ad/article.png" style="border: none;"/><br/>投稿管理</a><br/>'+
					'</td></tr>'+
					'<tr><td><a href="sys/index" target="main"><img src="${pageContext.request.contextPath}/img/ad/manage.png" style="border: none;"/><br/>系统管理</a><br/>'+
					'</td></tr>'+
					'<tr><td><a href="changepw" target="main"><img src="${pageContext.request.contextPath}/img/ad/changepw.png" style="border: none;"/><br/>密码修改</a><br/>'+
					'</td></tr>'+
					'<tr><td><a href="help" target="main"><img src="${pageContext.request.contextPath}/img/ad/help.png" style="border: none;"/><br/>帮助</a>'+
					'</td></tr>'+
					'<tr><td><a href="../loginout"><img src="${pageContext.request.contextPath}/img/ad/exit.png" style="border: none;"/><br/>注销</a>'+
					'</td></tr></s:if>'+
					
				'</tr>'+
				
			'</table>'+
           
        '</div>';
			new Ext.Viewport({
				layout	:	'border',
				defaults	:	{
					frame	:	true,
					split	:	true
				},
				items	:	[
					{
						title	:	'主菜单',
						region	:	'west',
						width	: 150,
						collapsible	:	true,
						html	: 	html
					},
					{
						title	:	'主窗口<s:if test="#session.adminGrade==1">--低级管理员</s:if>' + 
    							'<s:if test="#session.adminGrade==2">--中级管理员</s:if>' + 
    							'<s:if test="#session.adminGrade==3">--高级管理员</s:if>',
						region	:	'center',
						html	:	'<iframe name="main" src="main" width="100%" height="100%" frameborder="0" scrolling="auto"></iframe>'
					}
					
				]
				
			});
				
			
        
        	
		});
		</script>
  </body>
</html>
