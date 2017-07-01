<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>
<html>
  <head>
    
    <title>editLayoutAndPaperMain.jsp</title>
    
  </head>
  
  <body>
        <script type="text/javascript">
    	Ext.onReady(function() {
    	
    		Ext.QuickTips.init();
    		var layFormPaneltop<s:property value="model.id" escape="false"/>=new Ext.FormPanel({
    			title	:	'图片和PDF修改',
    			id		:	'layFormPaneltop<s:property value="model.id" escape="false"/>',
    			frame	:	true,
    			labelWidth	:	150,
    			defaultType	:	'textfield',
    			allowBlank	:	false,
    			//standardSubmit: true, 
    			
    			collapsible	:	true,
    			enableTabScroll	:	true,
    			fileUpload	:	true,
    			enctype :	'multipart/form-data',
    			
  								
    			defaults	:	{
    				msgTarget	:	'side',
    				anchor	:	'-20'
    			},
    			items	:	[
    				{	
						fieldLabel	:	'图片(<s:property value="model.pic" escape="false"/>)',
						allowBlank	:	false,
						name		:	'upload',
						inputType	:	'file'
					},
					{
						fieldLabel	:	'PDF(<s:property value="model.pdf" escape="false"/>)',
						
						allowBlank	:	false,
						name		:	'upload',
						inputType	:	'file'
					}
    			
    			],
    			buttons		:	[
    				{
						text	:	'修改',
						handler	:	function() {
							var form = Ext.getCmp('layFormPaneltop<s:property value="model.id" escape="false"/>');
							form.getForm().submit({
    							waitMsg	:'正在提交中...',
    							waitTitle	:	'提示',
    							url		:	'ajax/editLayoutPicAndPdf?id=<s:property value="model.id" escape="false"/>',
  								method	:	'post',
    							
    							success : function(form, action) {
    								
                            		Ext.MessageBox.alert('消息','修改成功！', function reload(){location.reload();});
                            		
                        		},
                        		failure : function(form, action) {
                            		form.reset();
                                	Ext.MessageBox.alert('警告','修改失败！');
                       	 		}
    						});
						}
					},
					{
						text	:	'重置',
						handler	:	function() {
							var form = Ext.getCmp('layFormPaneltop<s:property value="model.id" escape="false"/>');
							form.getForm().reset();
							
						}
						
					}
    			
    			]
    		
    		});
    		var layFormPanelMiddle<s:property value="model.id" escape="false"/>=new Ext.FormPanel({
    			title	:	'主题和本版编辑/校对修改',
    			id		:	'layFormPanelMiddle<s:property value="model.id" escape="false"/>',
    			frame	:	true,
    			labelWidth	:	150,
    			defaultType	:	'textfield',
    			allowBlank	:	false,
    			collapsible	:	true,
    			enableTabScroll	:	true,				
    			defaults	:	{
    				msgTarget	:	'side',
    				anchor	:	'-20'
    			},
    			items	:	[
    				
					{
						xtype	:	'container',
						id		:	'container' + '<s:property value="model.id" escape="false"/>',
						border	:	false,
						layout	:	'column',
						anchor	:	'100%',
						items	:	[
							{
								xtype	:	'container',
								id		:	'containera' + '<s:property value="model.id" escape="false"/>' ,
								border	:	false,
								columnWidth	:	.5,
								layout	:	'form',
								items 	:	[
									{
										xtype	:	'textfield',
										fieldLabel	:	'主题',
										id			:	'zt' + '<s:property value="model.id" escape="false"/>',
										name		:	'zt',
										value		:	'<s:property value="model.zt" escape="false"/>',
										width		:	200,
										allowBlank	:	false
									}
								]
							},
							{
								xtype	:	'container',
								id		:	'containerb' + '<s:property value="model.id" escape="false"/>',
								border	:	false,
								columnWidth	:	.5,
								layout	:	'form',
								items 	:	[
									{
										xtype	:	'textfield',
										fieldLabel	:	'编辑/校对',
										id			:	'bj'  + '<s:property value="model.id" escape="false"/>',
										name		:	'bj',
										value		:	'<s:property value="model.bj" escape="false"/>',
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
						text	:	'修改',
						handler	:	function() {
							var form = Ext.getCmp('layFormPanelMiddle<s:property value="model.id" escape="false"/>');
							form.getForm().submit({
    							waitMsg	:'正在提交中...',
    							waitTitle	:	'提示',
    							url		:	'ajax/editLayoutData?id=<s:property value="model.id" escape="false"/>',
  								method	:	'post',
    							
    							success : function(form, action) {
    								
                            		Ext.MessageBox.alert('消息','修改成功！', function reload(){location.reload();});
                            		
                        		},
                        		failure : function(form, action) {
                            		form.reset();
                                	Ext.MessageBox.alert('警告','修改失败！');
                       	 		}
    						});
						}
					},
					{
						text	:	'重置',
						handler	:	function() {
							var form = Ext.getCmp('layFormPanelMiddle<s:property value="model.id" escape="false"/>');
							form.getForm().reset();
							
						}
						
					}
    			
    			]
    		
    		});
    		var layFormPanelMain<s:property value="model.id" escape="false"/>=new Ext.FormPanel({
    			
    			title	:	'新闻内容',
    			id		:	'layFormPanelMain<s:property value="model.id" escape="false"/>',
    			frame	:	true,
    			defaultType	:	'textfield',
    			allowBlank	:	false,
    			//standardSubmit: true,
    			
    			collapsible	:	true,
    			defaults	:	{
    				msgTarget	:	'side',
    				anchor	:	'-20'
    			},
    			items	:	[
    				{
    					xtype	:	'checkboxgroup',
    					id		:	'checkboxgroup<s:property value="model.id" escape="false"/>',
    					fieldLabel	:	'请选择',
    					columns	:	1,
    					items	:	[
    					
    				<s:iterator id="news" value="newss" status="st">
    						{
    							xtype		:	'checkbox',
    							boxLabel	:	'<s:property value="title" escape="false"/>&nbsp;【作者：<s:property value="author" escape="false"/>'+
    												'<s:property value="subject.name" escape="false"/>，最后编辑时间：<s:property value="time" escape="false"/>】<a href="${pageContext.request.contextPath}/paper/<s:property value="paper.paper" escape="false"/>/e<s:property value="layout.layout_no" escape="false"/>/<s:property value="file_path" escape="false"/>" target="_blank">【查看】</a>',
    							name		:	'boxes',
    							inputValue	:	'<s:property value="id" escape="false"/>'
    						},
    				</s:iterator>
    				
    						{
    							xtype	:	'checkbox',
    							boxLabel	:	'全选',
    							name		:	'all',
    							inputValue	:	'',
    							listeners: {
                    				check: function (obj, ischecked) {
                        				if (ischecked) {
                                         	var array = Ext.getCmp('checkboxgroup<s:property value="model.id" escape="false"/>').items;
		        							array.each(function(item){
		        		    					item.setValue(true);
		        							});   	
                                        } else {
                          					var array = Ext.getCmp('checkboxgroup<s:property value="model.id" escape="false"/>').items;
		        							array.each(function(item){
		        		    					item.setValue(false);
		        							});
                        				}
                        			}
                    			}  
    						}
    					]
    				
    				}
    			],
    			tbar	:	[
    				{
    					text	:	'添加',
    					iconCls	:	'add',
    					handler	:	function() {
    						new top.Ext.Window({
    							id		:	'newWindow',			
    							//renderTo	:	Ext.getBody(),
    							width	:	1030,
    							height	:	610,
    							layout	:	'fit',
    							modal	:	true,
    							maximizable : true,
    							minimizable :	true, 
    							html	:	'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="edit/addNews?id=<s:property value="model.id" escape="false"/>"></iframe>'
    						
    						}).show();
    					}
    				},
    				{
    					text	:	'修改',
    					iconCls	:	'edit',
    					handler	:	function() {
    						var form = Ext.getCmp('layFormPanelMain<s:property value="model.id" escape="false"/>');
							form.getForm().submit({
								
    								waiMsg	:'正在提交中...',
    								url		:	'ajax/getNewsBoxes',
  									method	:	'post',
    								waitTitle	:	'提示',
    								success : function(form, action) {
                            				new top.Ext.Window({
    											
    											//renderTo	:	Ext.getBody(),
    											width	:	1050,
    											height	:	610,
    											layout	:	'fit',
    											modal	:	true,
    											maximizable : true,
    											minimizable :	true, 
    											html	:	'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="edit/tabNewsEdit?paperid=<s:property value="model.paper.id" escape="false"/>"></iframe>'
    						
    										}).show();
                        			},
                        			failure : function(form, action) {
                            				form.reset();
                                			Ext.MessageBox.alert('警告','修改失败！');
                       	 			}
    						});
    						
    					}
    				},
    				{
    					text	:	'删除',
    					iconCls	:	'delete',
    					handler	:	function() {
							var form = Ext.getCmp('layFormPanelMain<s:property value="model.id" escape="false"/>');
							Ext.Msg.show({
    							title	:	'提示',
    							msg		:	'你确定删除？',
    							buttons	:	{
    					
    								'ok'	:	'是',
    								'no'	:	'否'
    							},
    							fn		:	function(btn){
    								if (btn == 'ok') {
    									form.getForm().submit({
								
    										waitMsg	:'正在提交中...',
    										url		:	'ajax/deleteNews',
  											method	:	'post',
    										waitTitle	:	'提示',
    										success : function(form, action) {
                            					Ext.MessageBox.alert('消息','删除成功！',function reload(){location.reload();});
                            					
                        					},
                        					failure : function(form, action) {
                            					form.reset();
                                				Ext.MessageBox.alert('警告','删除失败！');
                       	 					}
    									});
    								}
    							},
    							icon	:	Ext.MessageBox.QUESTION
							});
						}
    				},
    				{
    					text	:	'刷新',
    					iconCls	:	'refresh',
    					handler	:	function() {
    						location.reload();
    					}
    				}
    			]
    		});
    		var innerPanel = new Ext.Panel({
    			renderTo	:	Ext.getBody(),
    			id 		:	'innerPanel<s:property value="model.id" escape="false"/>,',
    			width	:	'100%',
    			items	:	[
    				layFormPaneltop<s:property value="model.id" escape="false"/>,
    				layFormPanelMiddle<s:property value="model.id" escape="false"/>,
    				layFormPanelMain<s:property value="model.id" escape="false"/>
    				
    			]
    	<s:if test="last==model.layout_no">
    			,
    			tbar	:	[
    				{
    					text	:	'删除本版',
    					iconCls	:	'delete',
    					handler	:	function() {
  							Ext.Msg.show({
    							title	:	'提示',
    							msg		:	'你确定删除？',
    							width	:	300,
    							buttons	:	{
    					
    								'ok'	:	'是',
    								'no'	:	'否'
    							},
    							fn		:	function(btn){
    								if (btn == 'ok') {
    									open('ajax/deleteLayout?id=<s:property value="model.id" escape="false"/>', '_self');
    								}
    							},
    							icon	:	Ext.MessageBox.QUESTION
    					
    						});
    					}
    				}
    			]
    	</s:if>
    		
    		});
    	
    	});
    
    </script>
  </body>
</html>
