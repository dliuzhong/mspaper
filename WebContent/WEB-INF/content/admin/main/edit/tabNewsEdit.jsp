<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>
<html>
  <head>
    
    <title>tabContentEdit.jsp</title>
    
  </head>
  
  <body>
    <script type="text/javascript">
    	Ext.onReady(function() {
    	<s:iterator value="#session.boxes" status="st">
    		var tab<s:property value="#st.index" /> = {
    			id		:	'tab<s:property value="#st.index" />',
    			title	:	'新闻id:<s:property/>',
    			closable	:	true,
    			height	:	660,
    			html	:	'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="getNewsEdit?id=<s:property />"></iframe>'
    		}
    	</s:iterator>
    		var editTabPanel = new Ext.TabPanel({
    			renderTo	:	Ext.getBody(),
    			title	:	'修改',
    			id		:	'editTabPanel',
    			enableTabScroll	:	true,	
    			activeTab	:	0,
    			items	:	[
    	<s:iterator value="#session.boxes" status="st">
    				
    				<s:if test="#st.last">
    				tab<s:property value="#st.index" />
    				</s:if>
    				<s:else>
    				tab<s:property value="#st.index" />,
    				</s:else>
    	</s:iterator>
    			]
    		
    		});
    		
    	});
    
    </script>
  </body>
</html>
