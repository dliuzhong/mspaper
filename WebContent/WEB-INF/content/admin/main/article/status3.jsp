<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>
<html>
  <head>
    
    <title>status3.jsp</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/decorators/admin/js/jquery-1.10.2.min.js" ></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/decorators/admin/ckeditor/ckeditor.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/decorators/admin/ckeditor/config.js" charset="utf-8"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/decorators/admin/js/Ext.form.CKEditor.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/decorators/admin/js/Ext.form.BasicForm.js"></script>
  </head>
  
  <body>
        <script type="text/javascript">
    	new Ext.onReady(function() {
    		Ext.QuickTips.init();
    		var becauseStore = new Ext.data.SimpleStore({  
				fields : ['name', 'code'],  
				data : [['1--主旨不合本刊旨趣', '主旨不合本刊旨趣'],
						['2--文笔不畅通', '文笔不畅通'],
						['3--文体不合要求', '文体不合要求'],
						['4--内容不健康', '内容不健康']]
    			
        	});  
    		var recordFields = [
    			{	
    				name : 'id', mapping : 'id'
    			},
    			{	
    				name : 'title', mapping : 'title'
    			},
    			{	
    				name : 'msuser', mapping : 'msuser'
    			},
    			{
    				name : 'keyword', mapping : 'keyword'
    			},
    			{
    				name : 'content', mapping : 'content'
    			},
    			{
    				name : 'type', mapping : 'type'
    			},
    			{
    				name : 'time', mapping : 'time'
    			},
    			{
    				name : 'checktime', mapping : 'checktime'
    			},
    			{
    				name : 'admin', mapping : 'admin'
    			},
    			{
    				name : 'changeinfo', mapping : 'changeinfo'
    			},
    			{
    				name : 'other', mapping : 'other'
    			},
    			{
    				name : 'see', mapping : 'see'
    			}
    		];
    		var remoteJsonStore = new Ext.data.JsonStore({
    			fields	:	recordFields,
    			
    			url		:	'http://localhost/pw/json/getstatusArticlesJson.action?status=3',
    			
    			totalProperty	:	'totalCount',
    			root	:	'status',
    			id		:	'status3RemoteStore',
    			autoLoad	:	false,
    			remoteSort	:	true
    		});
    		
    		var recordField = [
    			'id', 'title', 'msuser', 'keyword', 'content', 'type', 'time', 'checktime', 'admin', 'changeinfo', 'other', 'see'
    		];
    		var colorTextBlue = function(id) {
    			return	'<span style="color: #000FF;">' + id + '</span>';
    		};
    		
    		var checkbox = new Ext.grid.CheckboxSelectionModel({
    			checkOnly : true,
    			singleSelect : false
   			});
    		var pagingToolbar = {
    			xtype	:	'paging',
    			store	:	remoteJsonStore,
    			pageSize	:	20,
    			displayInfo	:	true
    		};
    		
    		var colModel = [
    			checkbox,
    			{
    				header	:	'编号',
    				sortable	: true,
    				dataIndex	:	'id',
    				width	:	40
    			},
    			{
    				header	:	'标题',
    				dataIndex	:	'title',
    				sortable	: true,
    				width	:	200
    			},
    			{
    				header	:	'投稿人',
    				dataIndex	:	'msuser',
    				sortable	: true,
    				width	:	100
    			},
    			{
    				header	:	'关键字',
    				dataIndex	:	'keyword',
    				sortable	: true,
    				width	:	100
    			},
    			
    			{
    				header	:	'稿件类型',
    				dataIndex	:	'type',
    				sortable	: true,
    				width	:	100
    			},
    			{
    				header	:	'投稿时间',
    				dataIndex	:	'time',
    				sortable	: true,
    				width	:	150
    			},
    			{
    				header	:	'审核时间',
    				dataIndex	:	'checktime',
    				sortable	: true,
    				width	:	150
    			},
    			{
    				header	:	'审核人',
    				dataIndex	:	'admin',
    				sortable	: true,
    				width	:	60
    			},
    			{
    				header	:	'修改意见',
    				dataIndex	:	'changeinfo',
    				sortable	: true,
    				width	:	150
    			},
    			{
    				header	:	'备注',
    				dataIndex	:	'other',
    				sortable	: true,
    				width	:	100
    			}/*,
    			{
    				header	:	'查看',
    				dataIndex	:	'see',
    				sortable	: true,
    				width	:	40
    			}*/
    		];
    		
    		var gridView = new Ext.grid.GridView();
    		var selModel = new Ext.grid.RowSelectionModel({
    			singleSelect	:	true
    		});
    		
    		var grid = new Ext.grid.GridPanel({
    			id 	:	'maingrid',
    			autoHeight	:	true,
    			autoWidth	:	true,
    			store	:	remoteJsonStore,
    			view	:	gridView,
    			autoHeight	: true,
                autoScroll	: true,
                sm		:	checkbox,
				columns	:	colModel,
				
				tbar	:	[
					{
						text	:	'查看重审',
						iconCls	:	'check',
						id	:	'check'
					},
					{
						text	:	'修改',
						iconCls	:	'edit',
						id	:	'edit'
					},
					{
						text	:	'通过审核',
						iconCls	:	'ok',
						id	:	'pass'
					},
					{
						text	:	'取消审核',
						iconCls	:	'delete',
						id	:	'cancel'
					},
					{
						text	:	'刷新',
						iconCls	:	'refresh',
						handler :	function() {
							location.reload();
						}
					}
				],
                bbar	:	pagingToolbar,
                loadMask	:	true,
    			autorExpandColumn	:	'addressCol'
    		});
    		new Ext.Viewport({
    			layout	:	'border',
    			items	:	[
    				
    				{
    					title	:	'所有修改待审稿件',
    					region	:	'center',
    					items	:	grid
    				}
    				
    			]
    		
    		});
    		Ext.get('check').on('click', function() {
             	var selections = grid.getSelectionModel().getSelections();    //先获取选择模型，然后从选择模型中获取选中的记录。
             	if (selections.length == 1) {
             		for (var i = 0; i < selections.length; i++) {
                		var record = selections[i];
                		Ext.QuickTips.init();
                		var formPanel = new Ext.form.FormPanel({
         					id 		:	'formPanel',
         					labelWidth	:	80,
         					frame	:	true,
							bodyStyle	:	'padding: 5px',
							defaultType	:	'textfield',
							defaults	:	{
								msgTarget	:	'side',
								anchor	:	'-20'
							},
							autoScroll	:	true, 
  							allowBlank	:	false,
  							html	:	'编号：' + record.data.id + 
  										'<br/>' + 
  										'<font color="blue">标题：' + record.data.title +
  										'</font><br/>' + 
  										'投稿人：' + record.data.msuser +
  										'<br/>' + 
  										'<font color="blue">关键字：' + record.data.keyword +
  										'</font><br/>' + 
  										'<font color="blue">稿件类型：' + record.data.type +
  										'</font><br/>' + 
  										'内容：<br/>' + record.data.content + 
  										'<br/>' + 
  										'投稿时间：' + record.data.time + 
  										
  										'<br/>' + 
  										'修改意见：' + record.data.changeinfo +
  										'<br/>' + 
  										'审核人：' + record.data.admin +
  										
  										'<br/>' + 
  										'审核时间：' + record.data.checktime + 
  										'<br/>' + 
  										'备注：' + record.data.other,
  						 
  							buttons	:	[
  								{
    								text	:	'通过审核',
    								handler	:	function() {
    						
    									var form = Ext.getCmp('formPanel');
										form.getForm().submit({
    										waitMsg	:'正在提交中...',
    										waitTitle	:	'提示',
    										url			:	'ajax/changeStatusArticle',
  											method		:	'POST',
    										params	:	{
                             					id	:	record.data.id,
                             					status_t	:	4
                             				},
    										success : function(form, action) {
    								 			//Ext.MessageBox.alert("提示框","这是一个提示框",function(){alert("提示框关闭")});
                            					Ext.MessageBox.alert('消息','通过审核成功！', function closeWin() {var win = Ext.getCmp('formWindow');win.close();});
                            					remoteJsonStore.remove(record);
                            					grid.view.refresh();
                            		
                        					},
                        					failure : function(form, action) {
                            					form.reset();
                                				Ext.MessageBox.alert('警告','通过审核失败！');
                       	 					}
    									});
    								}
    							},
    							{
    								text	:	'不予采用',
    								handler	:	function() {
    									var formPanel = new Ext.form.FormPanel({
         									id 		:	'formPanel',
         									labelWidth	:	80,
         									frame	:	true,
											bodyStyle	:	'padding: 5px',
											defaults	:	{
												msgTarget	:	'side',
												anchor	:	'-20'
											},
											autoScroll	:	true, 
  											allowBlank	:	false,
  											items	:	[
  								
  												
  												{
  													xtype		:	'combo',
    												fieldLabel	:	'原因',
    												anchor		:	'-50',
    												store		:	becauseStore,
    												displayField	:	'name',
    		 										valueField		:	'code',
    		 										hiddenName	:	'other',
    		 										mode 		: 	'local',
    		 										allowBlank	:	false,
    		 										editable    :	false,
    		 										triggerAction: 'all'
  												}
  							
  											],
  											buttons	:	[
  												{
    												text	:	'提交',
    												handler	:	function() {
    						
    													var form = Ext.getCmp('formPanel');
														form.getForm().submit({
    														waitMsg	:'正在提交中...',
    														waitTitle	:	'提示',
    														url			:	'ajax/changeStatusArticle',
  															method		:	'POST',
    														params	:	{
                             									id	:	record.data.id,
                             									status_t	:	5
                             								},
    														success : function(form, action) {
    										
                            									Ext.MessageBox.alert('消息','不予采用成功！', function closeWin() {var win1 = Ext.getCmp('formWindow');var win2 = Ext.getCmp('unpassInfoWindow');win2.close();win1.close();});
                            									remoteJsonStore.remove(record);
                            									grid.view.refresh();
                            		
                        									},
                        									failure : function(form, action) {
                            									form.reset();
                                								Ext.MessageBox.alert('警告', '不予采用失败！');
                       	 									}
    													});
    												}
    											},
    											{
    												text	:	'重置',
    												handler	:	function() {
    													var form = Ext.getCmp('formPanel');
    													form.getForm().reset();
    					
    												}
    											}
  							
  											]
         	
         								});
         								if (!win) {
                    						var win = new Ext.Window({
    											id		:	'unpassInfoWindow',
    											width	:	400,
    											height	:	120,
    											layout	:	'fit',
    											modal	:	true,
    											title	:	'不予采用原因',
    											resizable	:	false,
    											
    											items	:	formPanel
    						
    										});
    										win.show();
                    					}
    									
    								}
    							},
    							{
    								text	:	'取消审核',
    								handler	:	function() {
    						
    									var form = Ext.getCmp('formPanel');
										form.getForm().submit({
    										waitMsg	:'正在提交中...',
    										waitTitle	:	'提示',
    										url			:	'ajax/changeStatusArticle',
  											method		:	'POST',
    										params	:	{
                             					id	:	record.data.id,
                             					status_t	:	1
                             				},
    										success : function(form, action) {
    								 			//Ext.MessageBox.alert("提示框","这是一个提示框",function(){alert("提示框关闭")});
                            					Ext.MessageBox.alert('消息','取消审核成功！', function closeWin() {var win = Ext.getCmp('formWindow');win.close();});
                            					remoteJsonStore.remove(record);
                            					grid.view.refresh();
                            		
                        					},
                        					failure : function(form, action) {
                            					form.reset();
                                				Ext.MessageBox.alert('警告','取消审核失败！');
                       	 					}
    									});
    								}
    							},
    							{
    								text	:	'取消',
    								handler	:	function() {
    									var win = Ext.getCmp('formWindow');
    									win.close();
    								}
    							}
  							
  							]
         	
         				});
         				if (!win) {
                    		var win = new Ext.Window({
    							id		:	'formWindow',
    							width	:	650,
    							height	:	500,
    							layout	:	'fit',
    							modal	:	true,
    							title	:	'查看窗口',
    							resizable	:	true,
    							items	:	formPanel
    						
    						});
    						win.show();
                    	}
                            	
                    }
                    grid.view.refresh();
               
             	} else
             		Ext.Msg.alert("提示","你还没有选中记录或选中多条记录。");
         	});
    		
    		
         	
         	
         	Ext.get('edit').on('click', function() {
             	var selections = grid.getSelectionModel().getSelections();    //先获取选择模型，然后从选择模型中获取选中的记录。
             	if (selections.length == 1) {
             		for (var i = 0; i < selections.length; i++) {
                		var record = selections[i];
                		Ext.QuickTips.init();
                		var formPanel = new Ext.form.FormPanel({
         					id 		:	'formPanel',
         					labelWidth	:	80,
         					frame	:	true,
							bodyStyle	:	'padding: 5px',
							defaultType	:	'textfield',
							defaults	:	{
								msgTarget	:	'side',
								anchor	:	'-20'
							},
							autoScroll	:	true, 
  							allowBlank	:	false,
  							items	:	[
  								
  								{
  									fieldLabel	:	'编号',
  									name	:	'id',
  									readOnly	:	true,
  									allowBlank	:	false,
  									value	:	record.data.id
  								},
  								{
  									fieldLabel	:	'标题',
  									name	:	'title',
  									allowBlank	:	false,
  									value	:	record.data.title
  								},
  								{
  									fieldLabel	:	'关键字',
  									name	:	'keyword',
  									allowBlank	:	false,
  									value	:	record.data.keyword
  								},
  								{
									xtype: 'ckeditor', 
  									fieldLabel: '内容', 
  									name: 'content',
									id		:	'content',
									value	:	record.data.content
								}
  							
  							],
  							buttons	:	[
  								{
    								text	:	'提交',
    								handler	:	function() {
    						
    									var form = Ext.getCmp('formPanel');
										form.getForm().submit({
    										waitMsg	:'正在提交中...',
    										waitTitle	:	'提示',
    										url			:	'ajax/changeEditArticle',
  											method		:	'POST',
    							
    										success : function(form, action) {
    										
                            					Ext.MessageBox.alert('消息','修改成功！', function closeWin() {var win = Ext.getCmp('formWindow');win.close(); location.reload();});
                            		
                            		
                        					},
                        					failure : function(form, action) {
                            					form.reset();
                            					var outinfo = action.result.info;
                                				Ext.MessageBox.alert('警告', outinfo);
                       	 					}
    									});
    								}
    							},
    							{
    								text	:	'重置',
    								handler	:	function() {
    									var form = Ext.getCmp('formPanel');
    									form.getForm().reset();
    					
    								}
    							}
  							
  							]
         	
         				});
         				if (!win) {
                    		var win = new Ext.Window({
    							id		:	'formWindow',
    							width	:	600,
    							height	:	500,
    							layout	:	'fit',
    							modal	:	true,
    							title	:	'修改窗口',
    							resizable	:	true,
    							maximizable : true,
    							minimizable :	true,
    							items	:	formPanel
    						
    						});
    						win.show();
                    	}
                            	
                    }
                    grid.view.refresh();
               
             	} else
             		Ext.Msg.alert("提示","你还没有选中记录或选中多条记录。");
         	});
         	Ext.get('pass').on('click', function() {
             	var selections = grid.getSelectionModel().getSelections();    //先获取选择模型，然后从选择模型中获取选中的记录。
            	 if (selections.length > 0) {
                 	Ext.Msg.confirm('提示', '你确认通过审核所选中的记录吗？', function(_btn) {
                     	if (_btn == 'yes') {
                         	for (var i = 0; i < selections.length; i++) {
                             	var record = selections[i];
                             	var conn = new Ext.data.Connection();
                             	conn.request({
                             		url	:	'ajax/changeStatusArticle',
                             		params	:	{
                             			id	:	record.data.id,
                             			status_t	:	4
                             		},
                             		method	:	'POST',
                             		scope	:	this,
                             		callback	:	function(options, success, response) {
                             			if (success) {
                             				//store.remove(record);
                             			} else {
                             				Ext.MessageBox.alert('提示','通过审核失败！');
                             			}
                             		}
                             		
                             		
                             	
                             	});
                            	remoteJsonStore.remove(record);
                         	}
                         	Ext.MessageBox.alert('消息','通过审核成功！');
                         	grid.view.refresh();
                     	}
                 	});
             	}
             	else
                 	Ext.Msg.alert("提示","你还没有选中记录.");
         	});
         	Ext.get('cancel').on('click', function() {
             	var selections = grid.getSelectionModel().getSelections();    //先获取选择模型，然后从选择模型中获取选中的记录。
            	 if (selections.length > 0) {
                 	Ext.Msg.confirm('提示', '你确认取消核审所选中的记录吗？', function(_btn) {
                     	if (_btn == 'yes') {
                         	for (var i = 0; i < selections.length; i++) {
                             	var record = selections[i];
                             	var conn = new Ext.data.Connection();
                             	conn.request({
                             		url	:	'ajax/changeStatusArticle',
                             		params	:	{
                             			id	:	record.data.id,
                             			status_t	:	1
                             		},
                             		method	:	'POST',
                             		scope	:	this,
                             		callback	:	function(options, success, response) {
                             			if (success) {
                             				//store.remove(record);
                             			} else {
                             				Ext.MessageBox.alert('提示','取消核审失败！');
                             			}
                             		}
                             		
                             		
                             	
                             	});
                            	remoteJsonStore.remove(record);
                         	}
                         	Ext.MessageBox.alert('消息','取消核审成功！');
                         	grid.view.refresh();
                     	}
                 	});
             	}
             	else
                 	Ext.Msg.alert("提示","你还没有选中记录.");
         	});
    		Ext.StoreMgr.get('status3RemoteStore').load({
    			params	:	{
    				start	:	0,
    				limit	:	20
    			}
    		});
    	});
    	
    
    </script> 
  </body>
</html>
