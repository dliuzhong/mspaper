<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>
<html>
  <head>
    
    <title>每版编辑主页</title>
    
  </head>
  
  <body>
  
  <script type="text/javascript">
    	Ext.onReady(function() {
    		Ext.QuickTips.init();
    	<s:bean name="org.apache.struts2.util.Counter" id="counter">
   			<s:param name="first" value="1" />
   			<s:param name="last" value="#session.laysum" />
   			<s:iterator>
    		var layTab<s:property/> = {
    			title	:	'第<s:property/>版',
    			id		:	'layTab<s:property/>',
    			closable	:	false,
    			border	:	false,
    			height	:	800,
    			html 		: '<iframe scrolling="no" frameborder="0" width="100%" height="100%" src="layMain?layout_no=<s:property/>" name="layFrame"></iframe>'
    		}
    		</s:iterator>
    		var layTab<s:property value="#session.laysum + 1"/> = {
    			title	:	'完成',
    			id		:	'layTab<s:property value="#session.laysum + 1"/>',
    			closable	:	false,
    			border	:	false,
    			html 		: '<br><h2 style="color: blue">&nbsp;&nbsp;如果您已经把本期内容添加完毕，请点击这里<button type="button" onclick="self.location=\'newPages?paperid=<s:property value="#session.paperid" />\'">完成</button>!</h2><br>'
    		}
		</s:bean>
    		var tabPanel = new Ext.TabPanel({
    			renderTo	:	Ext.getBody(),
    			id			:	'myTPanel',
    			enableTabScroll	:	true,	
    			activeTab	:	0,
    			items	:	[
    		<s:bean name="org.apache.struts2.util.Counter" id="counter">
   			<s:param name="first" value="1" />
   			<s:param name="last" value="#session.laysum" />
   			<s:iterator>
    				layTab<s:property value="#counter.current-1" escape="false"/>,
    		</s:iterator>
    		layTab<s:property value="#session.laysum + 1"/>
		</s:bean>
    			],
    			listeners	:	{
     				activate 	:	function(tabPanel){
      					tabPanel.getUpdater().refresh();
     				}
    			}
    		
    		});
    	
    	});
    
    </script>
  </body>
</html>
