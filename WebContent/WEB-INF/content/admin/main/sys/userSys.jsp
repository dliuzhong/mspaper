<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>
<html>
  <head>
    
    <title>userSys.jsp</title>
    
  </head>
  
  <body>
        <script type="text/javascript">
    	new Ext.onReady(function() {
    		Ext.QuickTips.init();
    		var typeStore = new Ext.data.SimpleStore({  
				fields : ['name', 'code'],  
				data : [['内部用户', '内部用户'],
						['外部用户', '外部用户']]
    			
        	});    
    		var recordFields = [
    			{	
    				name : 'id', mapping : 'id'
    			},
    			{	
    				name : 'username', mapping : 'username'
    			},
    			{
    				name : 'password', mapping : 'password'
    			},
    			{
    				name : 'name', mapping : 'name'
    			},
    			{
    				name : 'email', mapping : 'email'
    			},
    			{
    				name : 'telephone', mapping : 'telephone'
    			},
    			{
    				name : 'qq', mapping : 'qq'
    			},
    			{
    				name : 'type', mapping : 'type'
    			},
    			{
    				name : 'department', mapping : 'department'
    			},
    			{
    				name : 'status', mapping : 'status'
    			},
    			{
    				name : 'datetime', mapping : 'datetime'
    			}
    		];
    		var remoteJsonStore = new Ext.data.JsonStore({
    			fields	:	recordFields,
    			
    			url		:	'http://localhost/pw/json/getMsusersJson.action',
    			
    			totalProperty	:	'totalCount',
    			root	:	'users',
    			id		:	'userRemoteStore',
    			autoLoad	:	false,
    			remoteSort	:	true
    		});
    		
    		var recordField = [
    			'id', 'username', 'password', 'name', 'email', 'telephone', 'qq', 'type', 'department', 'status', 'datetime'
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
    				header	:	'用户名',
    				dataIndex	:	'username',
    				width	:	150
    			},
    			{
    				header	:	'密码（加密后）',
    				dataIndex	:	'password',
    				sortable	: true,
    				width	:	100
    			},
    			{
    				header	:	'用户姓名',
    				dataIndex	:	'name',
    				sortable	: true,
    				width	:	100
    			},
    			{
    				header	:	'email',
    				dataIndex	:	'email',
    				sortable	: true,
    				width	:	200
    			},
    			{
    				header	:	'联系电话',
    				dataIndex	:	'telephone',
    				sortable	: true,
    				width	:	150
    			},
    			{
    				header	:	'qq',
    				dataIndex	:	'qq',
    				sortable	: true,
    				width	:	100
    			},
    			{
    				header	:	'类型',
    				dataIndex	:	'type',
    				sortable	: true,
    				width	:	100
    			},
    			{
    				header	:	'部门',
    				dataIndex	:	'department',
    				sortable	: true,
    				width	:	200
    			},
    			{
    				header	:	'状态',
    				dataIndex	:	'status',
    				sortable	: true,
    				width	:	120
    			},
    			{
    				header	:	'用户创建时间',
    				dataIndex	:	'datetime',
    				sortable	: true,
    				width	:	150
    			}
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
						text	:	'添加用户',
						iconCls	:	'adduser',
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
					},
					{
						xtype	:	'tbtext',
						text	:	'注：用户类型[内部用户-宣统部内部，外部用户-除宣统部以外]'
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
    					title	:	'所有投稿用户',
    					region	:	'center',
    					items	:	grid
    				}
    				
    			]
    		
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
                             		url	:	'ajax/deleteMsuser',
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
                            	remoteJsonStore.remove(record);
                            	
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
  									allowBlank	:	false,
  									value	:	record.data.id
  								},
  								{
  									fieldLabel	:	'邮箱',
  									name	:	'email',
  									allowBlank	:	false,
  									value	:	record.data.email,
  									regex : /[a-zA-Z\d!#%&'*+-/\?\^\(\|~][\w\d!#%&'*+-\./\?\^\(\|~]+@[a-zA-Z\d-]+(\.[a-zA-Z\d-]{2,})+/,
  									regexText : '输入的邮箱地址不正确！' 
  								},
  								{
  									xtype		:	'combo',
    								fieldLabel	:	'类型',
    								anchor		:	'-50',
    								store		:	typeStore,
    								displayField	:	'name',
    		 						valueField		:	'code',
    		 						hiddenName	:	'type',
    		 						mode 		: 	'local',
    		 						allowBlank	:	false,
    		 						editable    :	false,
    		 						triggerAction: 'all',  
    								selectOnFocus:true,
    								value	:	record.data.type
  								},
  								{
  									fieldLabel	:	'新密码',
  									name	:	'newpw',
  									inputType	:	'password'
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
    										url			:	'ajax/changeUNPWMsuser',
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
    							width	:	400,
    							height	:	200,
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
  					allowBlank	:	false,
  					items	:	[
  						
  						{
  							fieldLabel	:	'邮箱',
  							name	:	'email',
  							allowBlank	:	false,
  							regex : /[a-zA-Z\d!#%&'*+-/\?\^\(\|~][\w\d!#%&'*+-\./\?\^\(\|~]+@[a-zA-Z\d-]+(\.[a-zA-Z\d-]{2,})+/,
  							regexText : '输入的邮箱地址不正确！' 
  						},
  						{
  							xtype		:	'combo',
    						fieldLabel	:	'类型',
    						anchor		:	'-50',
    						store		:	typeStore,
    						displayField	:	'name',
    		 				valueField		:	'code',
    		 				hiddenName	:	'type',
    		 				mode 		: 	'local',
    		 				allowBlank	:	false,
    		 				editable    :	false,
    		 				triggerAction: 'all',  
    						selectOnFocus:true
  						},
  						{
  							fieldLabel	:	'密码',
  							name	:	'password',
  							readOnly	:	true,
  							allowBlank	:	false,
  							value	:	'123456'
  
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
    								url			:	'ajax/addMsuser',
  									method		:	'POST',
    					
 									success : function(form, action) {
    										
                            			Ext.MessageBox.alert('消息','添加成功！', function closeWin() {var win = Ext.getCmp('addformWindow');win.close(); location.reload();});
                            		
                            		
                        			},
                        			failure : function(form, action) {
                            			var outinfo = action.result.info;
                                		Ext.MessageBox.alert('警告', outinfo);
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
    					height	:	200,
    					layout	:	'fit',
   						modal	:	true,
  						title	:	'添加管理员窗口',
    					resizable	:	false,
    					items	:	addformPanel
    				
   					});
   					addwin.show();
                }
          		grid.view.refresh();
             
         	});
    		Ext.StoreMgr.get('userRemoteStore').load({
    			params	:	{
    				start	:	0,
    				limit	:	20
    			}
    		});
    	});
    	
    
    </script> 
  </body>
</html>
