<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>
<html>
  <head>
    
    <title>adminSys.jsp</title>
    
  </head>
  
  <body>
        <script type="text/javascript">
    	new Ext.onReady(function() {
    		Ext.QuickTips.init();
    		var gradeStore = new Ext.data.SimpleStore({  
				fields : ['name', 'code'],  
				data : [['低级管理员', '1'],
						['中级级管理员', '2'],
						['高级管理员', '3']]
    			
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
    				name : 'grade', mapping : 'grade'
    			},
    			{
    				name : 'other', mapping : 'other'
    			},
    			{
    				name : 'datetime', mapping : 'datetime'
    			}
    		];
    		var remoteJsonStore = new Ext.data.JsonStore({
    			fields	:	recordFields,
    			
    			url		:	'http://localhost/pw/json/getAdminsJson.action',
    			
    			totalProperty	:	'totalCount',
    			root	:	'admins',
    			id		:	'adminRemoteStore',
    			autoLoad	:	false,
    			remoteSort	:	true
    		});
    		
    		var recordField = [
    			'id', 'username', 'password', 'name', 'grade', 'other', 'datetime'
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
    				width	:	200
    			},
    			{
    				header	:	'用户姓名',
    				dataIndex	:	'name',
    				sortable	: true,
    				width	:	100
    			},
    			{
    				header	:	'管理员等级',
    				dataIndex	:	'grade',
    				sortable	: true,
    				width	:	80 
    			},
    			{
    				header	:	'备注',
    				dataIndex	:	'other',
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
						text	:	'添加管理员',
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
						text	:	'注：管理员等级[1-低级管理员;2-中级管理员;3-高级管理员]'
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
    					title	:	'所有管理员',
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
                             		url	:	'ajax/deleteAdmin',
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
  									fieldLabel	:	'您的登陆密码',
  									name	:	'mypw',
  									allowBlank	:	false,
  									inputType	:	'password'
  								},
  								{
  									fieldLabel	:	'编号',
  									name	:	'id',
  									readOnly	:	true,
  									allowBlank	:	false,
  									value	:	record.data.id
  								},
  								{
  									fieldLabel	:	'用户名',
  									name	:	'username',
  									allowBlank	:	false,
  									value	:	record.data.username
  								},
  								{
  									fieldLabel	:	'新密码',
  									name	:	'newpw',
  									inputType	:	'password'
  								},
  								{
  									fieldLabel	:	'管理员姓名',
  									name	:	'name',
  									allowBlank	:	false,
  									value	:	record.data.name
  								},
  								{
  									xtype		:	'combo',
    								fieldLabel	:	'等级',
    								anchor		:	'-50',
    								store		:	gradeStore,
    								displayField	:	'name',
    		 						valueField		:	'code',
    		 						hiddenName	:	'grade',
    		 						mode 		: 	'local',
    		 						allowBlank	:	false,
    		 						editable    :	false,
    		 						triggerAction: 'all',  
    								selectOnFocus:true,
    								value	:	record.data.grade
  								},
  								{
  									fieldLabel	:	'备注',
  									name	:	'other',
  									value	:	record.data.other
  								},
  								{
  									fieldLabel	:	'创建时间',
  									name	:	'datetime',
  									readOnly	:	true,
  									allowBlank	:	false,
  									value	:	record.data.datetime
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
    										url			:	'ajax/changeAdmin',
    										params		:	'myid=<s:property value="#session.adminid"/>',
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
    							height	:	300,
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
  							fieldLabel	:	'您的登陆密码',
  							name	:	'mypw',
  							allowBlank	:	false,
  							inputType	:	'password'
  						},
  						{
  							fieldLabel	:	'用户名',
  							name	:	'username',
  							allowBlank	:	false
  						},
  						{
  							fieldLabel	:	'密码',
  							name	:	'password',
  							readOnly	:	true,
  							allowBlank	:	false,
  							value	:	'000000'
  
						},
  						{
  							fieldLabel	:	'管理员姓名',
  							name	:	'name',
  							allowBlank	:	false
  						},
  						{
  							xtype		:	'combo',
    						fieldLabel	:	'等级',
    						anchor		:	'-50',
    						store		:	gradeStore,
    						displayField	:	'name',
    		 				valueField		:	'code',
    		 				hiddenName	:	'grade',
    		 				mode 		: 	'local',
    		 				allowBlank	:	false,
    		 				editable    :	false,
    		 				triggerAction: 'all'
  						},
  						{
  							fieldLabel	:	'备注',
  							name	:	'other'
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
    								url			:	'ajax/addAdmin',
    								params		:	'myid=<s:property value="#session.adminid"/>',
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
    					height	:	250,
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
    		Ext.StoreMgr.get('adminRemoteStore').load({
    			params	:	{
    				start	:	0,
    				limit	:	20
    			}
    		});
    	});
    	
    
    </script> 
  </body>
</html>
