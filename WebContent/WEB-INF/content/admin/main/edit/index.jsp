<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>管理旧报纸</title>
   
  </head>
  
  <body>
    <script type="text/javascript">
    	Ext.onReady(function() {
    		new Ext.Viewport({
    			id		:	'mainviewpor',
    			layout	:	'border',
    			border	: 	false,
    			defaults	:	{
					frame	:	true,
					split	:	true
			
				},
    			items	:	[
    			
    				{
    					width	:	'314',
    					region	:	'west',
    					title	:	'往期报纸',
    					html	:	'<iframe name="left_edit_view" src="doSearchPaper" width="100%" height="100%" frameborder="0" scrolling="auto"></iframe>'
    				},
    				{
    					region	:	'center',
    					html	:	'<iframe name="edit_view" id="edit_view" src = "notice" width="100%" height="100%" frameborder="0" scrolling="auto"></iframe>'
    				}
    			
    			]
    			
    			
    		
    		});
    	
    	});
    
    </script>
  </body>
</html>
