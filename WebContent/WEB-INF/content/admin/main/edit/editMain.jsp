<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" 
"http://www.w3.org/TR/html4/frameset.dtd">
<html>
  <head>
    
    <title>editMain.jsp</title>
  
  </head>
  
  <body>
    <script type="text/javascript">
    	Ext.onReady(function() {
    		Ext.QuickTips.init();
    		var deleteBtn = new Ext.Button({
    			text	:	'删除本期',
    			iconCls	:	'delete',
    			handler	:	function() {
    				
    				Ext.Msg.confirm('提示', '你确定删除第<s:property value="model.paper" escape="false"/>期报纸？', function(_btn) {
                     	if (_btn == 'yes') {
                         	var conn = new Ext.data.Connection();
                            conn.request({
                             		url	:	'ajax/deletePaper',
                             		params	:	{
                             			id	:	<s:property value="model.id" escape="false"/>
                             		},
                             		method	:	'POST',
                             		scope	:	this,
                             		callback	:	function(options, success, response) {
                             			
                             			if (success) {
                             				Ext.MessageBox.alert('提示','删除成功！',function reload(){window.parent.location.reload();});
                             			} else {
                             				Ext.MessageBox.alert('提示','删除失败！');
                             			}
                             		}
                             		
                             	});
                          
                     	}
                 	});
    			}
    		});
    		var addBtn = new Ext.Button({
    			text	:	'增加版面',
    			iconCls	:	'add',
    			handler	:	function() {
    				Ext.QuickTips.init();
					var fieldset = {
						xtype	:	'fieldset',
						id		:	'fieldset',
						layout	:	'form',
						flex	:	4,
						border	:	true,
						labelWidth	:	50,
						title	: 	'版面信息',
						defaultType	:	'textfield',
						defaults	:	{
    						msgTarget	:	'side',
    						anchor	:	'-20',
    						allowBlank	:	false
    					},
						items	:	[
							{
								fieldLabel	:	'版面号',
								allowBlank	:	false,
								name		:	'layout_no',
								width		:	50,
								anchor	:	'50%',
								regex : /^[0-9]*[1-9][0-9]*$/,
  								regexText : '只能是数字',
								value	:	'<s:property value="model.lay_sum + 1"/>'
							},
							{
								fieldLabel	:	'图片',
								allowBlank	:	false,
								name		:	'upload',
								inputType	:	'file'
							},
							{
								fieldLabel	:	'PDF',
								allowBlank	:	false,
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
										id		:	'containera',
										border	:	false,
										columnWidth	:	.5,
										layout	:	'form',
										items 	:	[
											{
												xtype	:	'textfield',
												fieldLabel	:	'主题',
												id			:	'zt',
												name	:	'zt',
												width		:	150,
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
												name	:	'bj',
												width		:	150,
												allowBlank	:	false
											}
										]
									}
						
					
								]
							}
								
				
						]
		
					};
					var fp = new Ext.form.FormPanel({ 
  						fileUpload	:	true,
    					width		:	800,
    					height		:	640,
    					layout		:	'form',
    					layoutConfig	:	{
    						align	:	'stretch'
    					},
    					autoScroll	:	true,
    					id			:	'uploadForm',
    					frame		:	true,
    					bodyStyle	:	'padding: 6px',
    					labelWidth	:	100,
    					defaultType	:	'textfield',
    					defaults	:	{
    						msgTarget	:	'side',
    						anchor	:	'-10'
    					},
    					items	:	fieldset,
    					buttons	:	[
    						{
    							text	:	'确定',
    							handler	:	function() {
    								var form = Ext.getCmp('uploadForm');
									form.getForm().submit({
    									waitMsg		:	'正在提交中...',
    									waitTitle	:	'提示',
    									url		:	'ajax/addLayout?paperid=<s:property value="model.id" escape="false"/>&paperno=<s:property value="model.paper" escape="false"/>&lay_sum=<s:property value="model.lay_sum"/>',
  										method	:	'POST',
    									enctype	:	'multipart/form-data',
    									success : function(form, action) {
    								
                            				Ext.MessageBox.alert('消息','新增成功！',function closeWin() {var win = Ext.getCmp('newWindow');win.close(); location.reload();});
                            				
                        				},
                        				failure : function(form, action) {
                            				form.reset();
                                			Ext.MessageBox.alert('警告','新增失败！');
                       	 				}
    								});
    							}
    						},
    						{		
    							text	:	'重置',
    							handler	:	function() {
    								var form = Ext.getCmp('uploadForm');
									form.getForm().reset();
    							}
    						}
    					]
    				});
    				new Ext.Window({
    					id		:	'newWindow',
    					resizable :	false,
    					width	:	500,
    					height	:	260,
    					layout	:	'fit',
    					modal	:	true,
    					title	:	'增加版面',
    					items	:	[
    						fp
    					]
    						
    				}).show();
    			
    			}
    		});
    		var refreshBtn = new Ext.Button({
    		
    			text	:	'更新本期页面',
    			iconCls	:	'refresh',
    			handler	:	function() {
    				
    				Ext.Msg.confirm('提示', '你确定更新本期页面？', function(_btn) {
                     	if (_btn == 'yes') {
                         	var conn = new Ext.data.Connection();
                            conn.request({
                             		url	:	'ajax/refreshPages',
                             		params	:	{
                             			paperid	:	<s:property value="model.id" escape="false"/>
                             		},
                             		method	:	'POST',
                             		scope	:	this,
                             		callback	:	function(options, success, response) {
                             			
                             			if (success) {
                             				Ext.MessageBox.alert('提示','更新成功！');
                             			} else {
                             				Ext.MessageBox.alert('提示','更新失败！');
                             			}
                             		}
                             		
                             	});
                          
                     	}
                 	});
    			}
    		});
    	
    		var oneFormPanel = new Ext.form.FormPanel({
    			id		:	'baseInfoFormPanel',
    			//standardSubmit	:	true, 
    			collapsible	:	true,
    			title	:	'基本信息',
    			frame	:	true,
    			labelWidth	:	60,
    			defaultType	:	'textfield',
    			allowBlank	:	false,
    			defaults	:	{
    				msgTarget	:	'side',
    				anchor	:	'-20'
    			},
    			items	:	[
    				{
						xtype	:	'container',
						border	:	false,
						layout	:	'column',
						anchor	:	'100%',
						items	:	[
							{
								xtype	:	'container',
								border	:	false,
								layout	:	'form',
								columnWidth	:	.5,
								items 	:	[
									{
										xtype	:	'textfield',
										fieldLabel	:	'编号',
										disabled	:	true,
										name	:	'id',
										id		:	'id',
										allowBlank	:	false,
										value	:	'<s:property value="model.id" escape="false"/>',
										width	:	200
									
									}
								]
							},
							{
								xtype	:	'container',
								border	:	false,
								columnWidth	:	.5,
								layout	:	'form',
								items 	:	[
									{
										xtype	:	'textfield',
										fieldLabel	:	'期号',
										name	:	'paper',
										id		:	'paper',
										disabled	:	true,
										allowBlank	:	false,
										value	:	'<s:property value="model.paper" escape="false"/>',
										width	:	200
									}
								]
							}
						
					
						]
					},
					{
						xtype	:	'container',
						border	:	false,
						layout	:	'column',
						anchor	:	'100%',
						items	:	[
							{
								xtype	:	'container',
								border	:	false,
								layout	:	'form',
								columnWidth	:	.5,
								items 	:	[
									{
										xtype	:	'textfield',
										fieldLabel	:	'版数',
										name	:	'lay_sum',
										id		:	'lay_sum',
										disabled	:	true,
										allowBlank	:	false,
										value	:	'<s:property value="model.lay_sum" escape="false"/>',
										width	:	200
									
									}
								]
							},
							{
								xtype	:	'container',
								border	:	false,
								columnWidth	:	.5,
								layout	:	'form',
								items 	:	[
									{
										xtype	:	'textfield',
										fieldLabel	:	'主管主办',
										name	:	'zb',
										id		:	'zb',
										allowBlank	:	false,
										value	:	'<s:property value="model.zb" escape="false"/>',
										width	:	200
									}
								]
							}
						
					
						]
					},
    				{
						xtype	:	'container',
						border	:	false,
						layout	:	'column',
						anchor	:	'100%',
						items	:	[
							{
								xtype	:	'container',
								border	:	false,
								layout	:	'form',
								columnWidth	:	.5,
								items 	:	[
									{
										xtype	:	'textfield',
										fieldLabel	:	'编辑出版',
										name	:	'cb',
										id		:	'cb',
										allowBlank	:	false,
										value	:	'<s:property value="model.cb" escape="false"/>',
										width	:	200
									
									}
								]
							},
							{
								xtype	:	'container',
								border	:	false,
								columnWidth	:	.5,
								layout	:	'form',
								items 	:	[
									{
										xtype	:	'textfield',
										fieldLabel	:	'总编',
										name	:	'zongb',
										id		:	'zongb',
										allowBlank	:	false,
										value	:	'<s:property value="model.zongb" escape="false"/>',
										width	:	200
									}
								]
							}
						
					
						]
					},
					{
						xtype	:	'container',
						border	:	false,
						layout	:	'column',
						anchor	:	'100%',
						items	:	[
							{
								xtype	:	'container',
								border	:	false,
								layout	:	'form',
								columnWidth	:	.5,
								items 	:	[
									{
										xtype	:	'datefield',
										fieldLabel	:	'出版日期',
										name	:	'cbtime',
										id		:	'cbtime',
										format	:	'Y-m-d',
										allowBlank	:	false,
										value	:	'<s:date name="model.cbtime" format="yyyy-MM-dd" id="cbtime" /><s:property value="#cbtime"/>',
										width	:	200
									
									}
								]
							},
							{
								xtype	:	'container',
								border	:	false,
								columnWidth	:	.5,
								layout	:	'form',
								items 	:	[
									{
										xtype	:	'textfield',
										fieldLabel	:	'电子邮件E-mail',
										name	:	'email',
										id		:	'email',
										allowBlank	:	false,
										value	:	'<s:property value="model.email" escape="false"/>',
										width	:	200
									}
								]
							}
						
					
						]
					}
					
    			],
    			buttons	:	[
					
					{
						text	:	'修改',
						handler	:	function() {
							var form = Ext.getCmp('baseInfoFormPanel');
							form.getForm().submit({
    							waitMsg	:'正在提交中...',
    							url		:	'ajax/editPaper?id=<s:property value="model.id" escape="false"/>&lay_sum=<s:property value="model.lay_sum" escape="false"/>',
  								method	:	'POST',
    							waitTitle	:	'提示',
    							success : function(form, action) {
                            		Ext.MessageBox.alert('消息','修改成功！', function reload(){location.reload();});
                        		},
                        		failure : function(form, action) {
                            		form.reset();
                            		if (action.failureType == Ext.form.Action.SERVER_INVALID)
                                		Ext.MessageBox.alert('警告',action.result.errors.msg);
                       	 		}
    						});
						}
					},
					{
						text	:	'重置',
						handler	:	function() {
							var form = Ext.getCmp('baseInfoFormPanel');
							form.getForm().reset();
							
						}
						
					}
				]
    		
    		});
    		var twoFormPanel = new Ext.Panel({
    			title	:	'基本操作',
    			id	:	'twoFormPanel',
    			tbar	:	[
    				addBtn,
    				refreshBtn,
    				deleteBtn
    				
    				
    			]
    		
    		});
    		var oneTab = {
    			title	:	'第<s:property value="model.paper" escape="false"/>期',
    			id		:	'oneTab',
    			
    			closable	:	false,
    			items	:	[
    				twoFormPanel,
    				oneFormPanel
    				
    			]
    		};
    		<s:bean name="org.apache.struts2.util.Counter" id="counter">
   			<s:param name="first" value="1" />
   			<s:param name="last" value="model.lay_sum" />
   			<s:iterator>
    		var layTab<s:property value="#counter.current-1" escape="false"/> = {
    			title	:	'第<s:property value="#counter.current-1" escape="false"/>版',
    			id		:	'layTab<s:property value="#counter.current-1" escape="false"/>',
    			closable	:	false,
    			border	:	false,
    			height	:	640,
    			<s:if test="#counter.current-1==last">
    			
    				html 		: '<iframe scrolling="no" frameborder="0" width="100%" height="100%" src="editLayoutAndPaperMain?paperid=<s:property value="model.id" escape="false"/>&layout_no=<s:property value="#counter.current-1" escape="false"/>&last=<s:property value="#counter.current-1" escape="false"/>"></iframe>'	
    			</s:if>
    			<s:else>
    			
    			html 		: '<iframe scrolling="no" frameborder="0" width="100%" height="100%" src="editLayoutAndPaperMain?paperid=<s:property value="model.id" escape="false"/>&layout_no=<s:property value="#counter.current-1" escape="false"/>"></iframe>'
    			</s:else>
    		}
    		</s:iterator>
    		</s:bean>
    		var tabPanel = new Ext.TabPanel({
    			renderTo	:	Ext.getBody(),
    			id			:	'myTPanel',
    			enableTabScroll	:	true,	
    			activeTab	:	0,
    			items	:	[
    				oneTab,
    		<s:bean name="org.apache.struts2.util.Counter" id="counter">
   			<s:param name="first" value="1" />
   			<s:param name="last" value="model.lay_sum" />
   			<s:iterator status="st">
    				layTab<s:property/>
    				<s:if test="!#st.last">
    				,	
    				</s:if>
    			
    		</s:iterator>
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
