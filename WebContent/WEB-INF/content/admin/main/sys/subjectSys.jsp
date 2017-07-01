<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>
<html>
  <head>
    
    <title>subjectSys.jspe</title>
    
  </head>
  
  <body>
     <script type="text/javascript">
    	new Ext.onReady(function() {
    		Ext.QuickTips.init();
    		
    		var recordFields = [
    			{	
    				name : 'id', mapping : 'id'
    			},
    			{	
    				name : 'name', mapping : 'name'
    			},
    			{
    				name : 'de', mapping : 'desc'
    			},
    			{
    				name : 'sum', mapping : 'sum'
    			},
    			{
    				name : 'st', mapping : 'status'
    			},
    			{
    				name : 'datetime', mapping : 'datetime'
    			}
    		];
    		
    		
    		var remoteJsonStore = new Ext.data.JsonStore({
    			fields	:	recordFields,
    			
    			url		:	'http://localhost/pw/json/getSubjectsJson.action',
    			
    			totalProperty	:	'totalCount',
    			root	:	'subjects',
    			id		:	'subjectRemoteStore',
    			autoLoad	:	false,
    			remoteSort	:	true
    		});
    		
    		var recordField = [
    			'id', 'name', 'de', 'sum', 'st', 'datetime'
    		];
    		var colorTextBlue = function(id) {
    			return	'<span style="color: #000FF;">' + id + '</span>';
    		};
    		
    		var checkbox = new Ext.grid.CheckboxSelectionModel({
    			checkOnly : true,
    			singleSelect : false
   			});
   			
   			var colModel = [
    			checkbox,
    			{
    				header	:	'编号',
    				sortable	: true,
    				dataIndex	:	'id',
    				width	:	80,
    				renderer	:	colorTextBlue
    			},
    			{
    				header	:	'专题名称',
    				dataIndex	:	'name',
    				sortable	: true,
    				width	:	280
    			},
    			{
    				header	:	'专题描述',
    				dataIndex	:	'de',
    				sortable	: true,
    				width	:	320
    			},
    			{
    				header	:	'专题状态',
    				dataIndex	:	'st',
    				sortable	: true,
    				width	:	70
    			},
    			{
    				header	:	'专题新闻数量',
    				dataIndex	:	'sum',
    				sortable	: false,
    				width	:	90
    			},
    			{
    				header	:	'创建时间',
    				dataIndex	:	'datetime',
    				sortable	: true,
    				width	:	130
    			}
    			
    		];
    		var gridView = new Ext.grid.GridView();
    		var selModel = new Ext.grid.RowSelectionModel({
    			singleSelect	:	true
    		});
    		var pagingToolbar = {
    			xtype	:	'paging',
    			store	:	remoteJsonStore,
    			pageSize	:	20,
    			displayInfo	:	true
    		};
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
						text	:	'设置活动',
						iconCls	:	'ok',
						id	:	'active'
					},
					{
						text	:	'取消活动',
						iconCls	:	'stop',
						id	:	'unactive'
					},
					{
						text	:	'添加',
						iconCls	:	'add',
						id	:	'add'
					},
					{
						text	:	'修改',
						iconCls	:	'edit',
						id	:	'edit'
					},
					{
						text	:	'删除',
						iconCls	:	'delete',
						id		:	'remove'
					}
					,
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
    					title	:	'所有专题',
    					region	:	'center',
    					items	:	grid
    				}
    				
    			]
    		
    		});
    		Ext.get('add').on('click', function() {
             	Ext.QuickTips.init();
                var addformPanel = new Ext.form.FormPanel({
         			id 		:	'addformPanel',
         			labelWidth	:	80,
         			frame	:	true,
					bodyStyle	:	'padding: 5px',
					defaultType	:	'textfield',
					defaults	:	{
						msgTarget	:	'side',
						anchor	:	'-20'
					},
  					//allowBlank	:	false,
  					items	:	[
  						{
  							fieldLabel	:	'专题名称',
  							name	:	'name',
  							allowBlank	:	false
  						},
  						{
  							fieldLabel	:	'描述',
  							name	:	'de',
  							allowBlank	:	true
  						},
  						{
    						xtype	:	'radiogroup',
    						fieldLabel	:	'状态',
    						anchor	:	'100%',
    						items	:	[
    							{
    								boxLabel	:	'活动',
    								inputValue	:	'1',
    								name	:	'st',
    								checked	:	true
    							  
    							},
    							{
    								boxLabel	:	'不活动',
    								name	:	'st',
    								inputValue	:	'0'
    							
    							}
    						]
    					
  						}
  					],
  					buttons	:	[
  						{
    						text	:	'添加',
    						handler	:	function() {
    							var form = Ext.getCmp('addformPanel');
								form.getForm().submit({
    								waitMsg	:'正在提交中...',
   									waitTitle	:	'提示',
    								url			:	'ajax/addSubject',
  									method		:	'POST',
    					
 									success : function(form, action) {
    										
                            			Ext.MessageBox.alert('消息','添加成功！', function closeWin() {var win = Ext.getCmp('addformWindow');win.close(); location.reload();});
                            		
                            		
                        			},
                        			failure : function(form, action) {
                                		Ext.MessageBox.alert('警告', '添加失败！');
                       	 			}
    							});
    						}
    					},
    					{
    						text	:	'重置',
    						handler	:	function() {
    							var form = Ext.getCmp('addformPanel');
    							form.getForm().reset();
    			
    						}
    					}
  					
					]         	
         		});
         		if (!addwin) {
                  	var addwin = new Ext.Window({
    					id		:	'addformWindow',
    					width	:	400,
    					height	:	170,
    					layout	:	'fit',
   						modal	:	true,
  						title	:	'添加专题',
    					resizable	:	false,
    					items	:	addformPanel
    				
   					});
   					addwin.show();
                }
          		grid.view.refresh();
             
         	});
         	Ext.get('active').on('click', function() {
             	var selections = grid.getSelectionModel().getSelections();    //先获取选择模型，然后从选择模型中获取选中的记录。
            	 if (selections.length > 0) {
                 	Ext.Msg.confirm('提示', '你确认设置选中的专题为活动吗？', function(_btn) {
                     	if (_btn == 'yes') {
                         	for (var i = 0; i < selections.length; i++) {
                             	var record = selections[i];
                             	var conn = new Ext.data.Connection();
                             	conn.request({
                             		url	:	'ajax/doActiveSubject',
                             		params	:	{
                             			id	:	record.data.id
                             		},
                             		method	:	'POST',
                             		scope	:	this,
                             		callback	:	function(options, success, response) {
                             			if (success) {
                             				
                             			} else {
                             				Ext.MessageBox.alert('提示','设置失败！');
                             			}
                             		}
                             		
                             		
                             	
                             	});
                            	
                         	}
                         	Ext.MessageBox.alert('消息','设置成功！', function reload() {location.reload();});
                     	}
                 	});
             	}
             	else
                 	Ext.Msg.alert("提示","你还没有选中记录.");
         	});
         	Ext.get('unactive').on('click', function() {
             	var selections = grid.getSelectionModel().getSelections();    //先获取选择模型，然后从选择模型中获取选中的记录。
            	 if (selections.length > 0) {
                 	Ext.Msg.confirm('提示', '你确认取消选中的专题为活动吗？', function(_btn) {
                     	if (_btn == 'yes') {
                         	for (var i = 0; i < selections.length; i++) {
                             	var record = selections[i];
                             	var conn = new Ext.data.Connection();
                             	conn.request({
                             		url	:	'ajax/doUnactiveSubject',
                             		params	:	{
                             			id	:	record.data.id
                             		},
                             		method	:	'POST',
                             		scope	:	this,
                             		callback	:	function(options, success, response) {
                             			if (success) {
                             				
                             			} else {
                             				Ext.MessageBox.alert('提示','取消失败！');
                             			}
                             		}
                             		
                             		
                             	
                             	});
                            	
                         	}
                         	Ext.MessageBox.alert('消息','取消成功！', function reload() {location.reload();});
                     	}
                 	});
             	}
             	else
                 	Ext.Msg.alert("提示","你还没有选中记录.");
         	});
    		Ext.get('remove').on('click', function() {
             	var selections = grid.getSelectionModel().getSelections();    //先获取选择模型，然后从选择模型中获取选中的记录。
            	 if (selections.length > 0) {
                 	Ext.Msg.confirm('提示', '你确认删除选中的记录吗？', function(_btn) {
                     	if (_btn == 'yes') {
                         	for (var i = 0; i < selections.length; i++) {
                             	var record = selections[i];
                             	var conn = new Ext.data.Connection();
                             	conn.request({
                             		url	:	'ajax/deleteSubject',
                             		params	:	{
                             			id	:	record.data.id
                             		},
                             		method	:	'POST',
                             		scope	:	this,
                             		callback	:	function(options, success, response) {
                             			if (success) {
                             				//store.remove(record);
                             			} else {
                             				Ext.MessageBox.alert('提示','删除失败！');
                             			}
                             		}
                             		
                             		
                             	
                             	});
                             	if (record.data.id != 1) {
                             		remoteJsonStore.remove(record);
                             	}
                            	
                            	
                         	}
                         	grid.view.refresh();
                     	}
                 	});
             	}
             	else
                 	Ext.Msg.alert("提示","你还没有选中记录.");
         	});
         	
         	Ext.get('edit').on('click', function() {
             	var selections = grid.getSelectionModel().getSelections();    //先获取选择模型，然后从选择模型中获取选中的记录。
             	if (selections.length > 0) {
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
  							allowBlank	:	false,
  							items	:	[
  								{
  									fieldLabel	:	'编号',
  									name	:	'id',
  									readOnly	:	true,
  									value	:	record.data.id
  								},
  								{
  									fieldLabel	:	'专题名称',
  									name	:	'name',
  									readOnly	:	false,
  									value	:	record.data.name
  								},
  								{
  									fieldLabel	:	'专题描述',
  									name	:	'de',
  									readOnly	:	false,
  									value	:	record.data.desc
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
    										url			:	'ajax/changeSubject',
  											method		:	'POST',
    							
    										success : function(form, action) {
    								 			//Ext.MessageBox.alert("提示框","这是一个提示框",function(){alert("提示框关闭")});
                            					Ext.MessageBox.alert('消息','修改成功！', function closeWin() {var win = Ext.getCmp('formWindow');win.close(); location.reload();});
                            		
                            		
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
    									var form = Ext.getCmp('formPanel');
    									form.getForm().reset();
    					
    								}
    							}
  							
  							]
         	
         				});
         				if (!win) {
                    		var win = new Ext.Window({
    							id		:	'formWindow',
    							width	:	400,
    							height	:	170,
    							layout	:	'fit',
    							modal	:	true,
    							title	:	'修改窗口',
    							resizable	:	false,
    							items	:	formPanel
    						
    						});
    						win.show();
                    	}
                            	
                    }
                    grid.view.refresh();
               
             	} else
             		Ext.Msg.alert("提示","你还没有选中记录.");
         	});
    		Ext.StoreMgr.get('subjectRemoteStore').load({
    			params	:	{
    				start	:	0,
    				limit	:	20
    			}
    		});
    	});
    	
    
    </script> 
  </body>
</html>
