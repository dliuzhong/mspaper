<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>
<html>
  <head>
    
    <title>系统管理</title>
   
  </head>
  
  <body>
   <script type="text/javascript">
		Ext.onReady(function() {
			var commentTab = {
				title	:	'评论管理',
				id	:	'commentTab',
				html	:	'<iframe name="commentFrame" scrolling="auto" frameborder="0" width="100%" height="100%" src="commentSys"></iframe>'
			
			};
			var specialTab  = {
				title	:	'专题管理',
				id	:	'specialTab',
				html	:	'<iframe name="specialFrame" scrolling="auto" frameborder="0" width="100%" height="100%" src="subjectSys"></iframe>'
			};
			var adminTab	= {
				title	:	'管理员管理',
				id	:	'adminTab',
				html	:	'<iframe name="specialFrame" scrolling="auto" frameborder="0" width="100%" height="100%" src="adminSys"></iframe>'
			};
			var userTab	= {
				title	:	'投稿用户管理',
				id	:	'userTab',
				html	:	'<iframe name="specialFrame" scrolling="auto" frameborder="0" width="100%" height="100%" src="userSys"></iframe>'
			};
			var departmentTab	= {
				title	:	'投稿部门管理',
				id	:	'departmentTab',
				html	:	'<iframe name="specialFrame" scrolling="auto" frameborder="0" width="100%" height="100%" src="departmentSys"></iframe>'
			};
			var articleTypeTab	= {
				title	:	'稿件类型管理',
				id	:	'articleTypeTab',
				html	:	'<iframe name="specialFrame" scrolling="auto" frameborder="0" width="100%" height="100%" src="articleTypeSys"></iframe>'
			};
			var tabPanel = new Ext.TabPanel({
				activeTab	:	0,
				id	:	'tabPanel',
				anchor	:	'100% 100%',
				items	:	[
					specialTab,
					commentTab,
					adminTab,
					userTab,
					departmentTab,
					articleTypeTab
				]
			});
			myView = new Ext.Viewport({
				layout	:	'border',
				border	:	false,
				items	:	[
					
					{
						region	:	'center',
						title	:	'XXX报社电子报刊管理系统——系统管理',
						
						layout	:	'anchor',
						items	:	[
							tabPanel
						]
						
					
					}
					
					
				
				]
				
			});
		});
	</script>
  </body>
</html>
