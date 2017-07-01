<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>
<html>
  <head>
    
    <title>投稿系统管理</title>
   
  </head>
  
  <body>
   <script type="text/javascript">
		Ext.onReady(function() {
			var status1Tab = {
				title	:	'未审稿件',
				id	:	'commentTab',
				html	:	'<iframe name="commentFrame" scrolling="auto" frameborder="0" width="100%" height="100%" src="status1"></iframe>'
			
			};
			var status3Tab  = {
				title	:	'修改重审稿件',
				id	:	'specialTab',
				html	:	'<iframe name="specialFrame" scrolling="auto" frameborder="0" width="100%" height="100%" src="status3"></iframe>'
			};
			var status4Tab	= {
				title	:	'通过审核稿件',
				id	:	'adminTab',
				html	:	'<iframe name="specialFrame" scrolling="auto" frameborder="0" width="100%" height="100%" src="status4"></iframe>'
			};
			var status5Tab	= {
				title	:	'不予采用稿件',
				id	:	'userTab',
				html	:	'<iframe name="specialFrame" scrolling="auto" frameborder="0" width="100%" height="100%" src="status5"></iframe>'
			};
			var allstatusTab	= {
				title	:	'所有状态稿件',
				id	:	'departmentTab',
				html	:	'<iframe name="specialFrame" scrolling="auto" frameborder="0" width="100%" height="100%" src="allstatus"></iframe>'
			};
			var sortDepartmentTab	= {
				title	:	'稿件统计',
				id	:	'sortDepartmentTab',
				html	:	'<iframe name="specialFrame" scrolling="auto" frameborder="0" width="100%" height="100%" src="ajax/getSort?sortMain=none"></iframe>'
			};
				
			
			var tabPanel = new Ext.TabPanel({
				activeTab	:	0,
				id	:	'tabPanel',
				anchor	:	'100% 100%',
				items	:	[
					status1Tab,
					status3Tab,
					status4Tab,
					status5Tab,
					allstatusTab,
					sortDepartmentTab
					
				]
			});
			myView = new Ext.Viewport({
				layout	:	'border',
				border	:	false,
				items	:	[
					
					{
						region	:	'center',
						title	:	'XXX报社电子报刊管理系统——投稿系统管理',
						
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
