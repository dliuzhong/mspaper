<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    
    <title>index_left</title>
    
  </head>
   
  <body>
  
    <script type="text/javascript">
    		Ext.onReady(function() {
    			Ext.QuickTips.init();
    			var topPanel = new Ext.form.FormPanel({
    				id		:	'topPanel',
    				standardSubmit	:	true, 
  					url		:	'doSearchPaper',
  					method	:	'POST',
    				height	:	128,
    				width	:	300,
    				collapsible	:	true,
    				title	:	'往期检索',
    				frame	:	true,
    				labelWidth	:	40,
    				defaultType	:	'textfield',
    				allowBlank	:	false,
    				defaults	:	{
    					msgTarget	:	'side',
    					anchor	:	'-20'
    				},
    				items	:	[
    					
    					{
    						xtype	:	'numberfield',
    						fieldLabel	:	'年份',
    						maxLength	:	4,
    						minLength	:	4,
    						name	:	'year',
    						id		:	'year',
    						width	:	300,
    						regex : /^[0-9]*[1-9][0-9]*$/,
  							regexText : '只能是数字',
    						value	:	'<s:property value="year" />'
    					
    					},
    					{
    						fieldLabel	:	'期号',
    						allowBlank	:	true,
    						name	:	'paperno',
    						id		:	'paperno',
    						width	:	300,
    						regex : /\w{1,15}/,
  							regexText : '期号只能为字母和数字组成，1-15',
    						value	:	'<s:property value="paperno" />'
    						
    					}
    				
    				],
    				buttons	:	[
    					{
    						text	:	'确定',
    						handler	:	submit
    					}
    				]
    			});
    			tp = Ext.getCmp('topPanel');
    			function submit() {
    				tp.getForm().submit({
    					waitMsg:'正在提交中...',
    					waitTitle:'提示'
    				});
    			};
    			var mainPanel = new Ext.Panel({
    				collapsible	:	true,
    				id		:	'mainPanel',
    				title	:	'往期',
    				height	:	400,
    				width	:	300,
    				autoScroll	:	true,
    				html	:	'<div id="allpaper">'+
    								'<table>'+
    			<s:if test="#papers.size()==0">
    			
    				'<tr><td>对不起！没有检索到结果！</td><tr>'+
    			</s:if>
    			<s:iterator id="paper" value="papers" status="st"> 
  					'<tr><td style="border-buttom: 1px;"><h4><a href=\"toEditMain?id=<s:property value="id" escape="false" />\" target=\"edit_view\">第<s:property value="paper" escape="false" />期</a>（<s:date name="cbtime" format="yyyy/MM/dd" id="cbtime" /><s:property value="#cbtime"/>）</h4></td></tr>'+
				</s:iterator> 	
    	
    							  '</table>'+
    							'</div>',
					bbar 	:	[
						{
							text	:	'显示全部',
							handler	:	function() {
								open('doSearchPaper','_self');
							}
						}
					],
					tools	:	[
						{
							id	:	'refresh',
							title:	'刷新',
							handler	:	function() {
								window.location.reload(); 
							}
						}
					]
    			
    			});
    			var panel = new Ext.Panel({
					renderTo	:	Ext.getBody(),
					items	:	[
						topPanel,
						mainPanel
					]
			});
    		
    	});
    
    </script>
  </body>
</html>
