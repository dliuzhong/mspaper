<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>
<html>
  <head>
    
    <title>layMain.jsp</title>
  
  </head>
  
  <body>
  <s:set name="pic" value="model.pic"/>
  <s:set name="pdf" value="model.pdf"/>
  <s:set name="zt" value="model.zt"/>
  <s:set name="bj" value="model.bj"/>
  <script type="text/javascript">
 
    Ext.onReady(function() {
    		Ext.QuickTips.init();
    		
    		
    		<s:if test="#pic != null && #pic != null && #pdf != null && #zt != null && #bj != null">
  					
  
    		var layFormPanelMain=new Ext.FormPanel({
    			
    			title	:	'第<s:property value="#session.newpaper" />期第<s:property value="model.layout_no" escape="false"/>版新闻内容',
    			id		:	'layFormPanelMain',
    			height	:	800,
    			frame	:	true,
    			defaultType	:	'textfield',
    			allowBlank	:	false,
    			html 		: '<iframe scrolling="no" frameborder="0" width="100%" height="100%" src="newNews?layoutid=<s:property value="model.id" escape="false"/>&layout_no=<s:property value="model.layout_no"/>&pic=<s:property value='model.pic' />" name="layFrame"></iframe>'
    		});
    		</s:if>
    		<s:else>
    		var layFormPaneltop=new Ext.FormPanel({
    			title	:	'第<s:property value="model.layout_no" escape="false"/>版：图片、PDF、主题和本版编辑/校对',
    			id		:	'layFormPaneltop',
    			frame	:	true,
    			labelWidth	:	150,
    			defaultType	:	'textfield',
    			allowBlank	:	false,
    			standardSubmit: true, 
  				url		:	'newLay?layout_no=<s:property value="model.layout_no"/>&paperid=<s:property value="#session.paperid" />',
  				
  				method	:	'post',
  				enctype:'multipart/form-data',
  				fileUpload	:	true,
    			collapsible	:	true,
    			enableTabScroll	:	true,
  				
    			items	:	[
    				{	
						fieldLabel	:	'图片(<s:property value="model.pic" escape="false"/>)',
						allowBlank	:	true,
						name		:	'upload',
						inputType	:	'file'
					},
					{
						fieldLabel	:	'PDF(<s:property value="model.pdf" escape="false"/>)',
						allowBlank	:	true,
						name		:	'upload',
						inputType	:	'file'
					},
					{
						xtype	:	'container',
						id		:	'container',
						border	:	false,
						layout	:	'column',
						anchor	:	'100%',
						items	:	[
							{
								xtype	:	'container',
								id		:	'container' ,
								border	:	false,
								columnWidth	:	.5,
								layout	:	'form',
								items 	:	[
									{
										xtype	:	'textfield',
										fieldLabel	:	'主题',
										id			:	'zt',
										value		:	'<s:property value="model.zt" escape="false" />',
										width		:	200,
										allowBlank	:	false
									}
								]
							},
							{
								xtype	:	'container',
								id		:	'containerb',
								border	:	false,
								columnWidth	:	.5,
								layout	:	'form',
								items 	:	[
									{
										xtype	:	'textfield',
										fieldLabel	:	'编辑/校对',
										id			:	'bj',
										value		:	'<s:property value="model.bj" escape="false" />',
										width		:	200,
										allowBlank	:	false
									}
								]
							}
						
					
						]
					}
    			
    			],
    			buttons		:	[
    				{
						text	:	'提交',
						handler	:	function() {
							var form = Ext.getCmp('layFormPaneltop');
							form.getForm().submit({
    							waitMsg	:'正在提交中...',
    							waitTitle	:	'提示',
    							
    							
    						});
						}
					},
					{
						text	:	'重置',
						handler	:	function() {
							var form = Ext.getCmp('layFormPaneltop');
							form.getForm().reset();
							
						}
						
					}
    			
    			]
    		
    		});
    		</s:else>
    		var mainPanel = new Ext.Panel({
    			renderTo	:	Ext.getBody(),
    			id 		:	'innerPanel',
    			width	:	'100%',
    			items	:	[
    				
					<s:if test="#pic != null && #pic != null && #pdf != null && #zt != null && #bj != null">    				
					layFormPanelMain
    				</s:if>
    				<s:else>
    				layFormPaneltop
    				</s:else>
    				
    				
    			]
    		
    		});
    	
    	});
    
    </script>
</html>
