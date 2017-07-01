<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>
<html>
  <head>
    
    <title>departmentSys.jsp</title>
    
  </head>
  
  <body>
        <script type="text/javascript">
    	new Ext.onReady(function() {
    		Ext.QuickTips.init();
    		var typeStore = new Ext.data.SimpleStore({  
				fields : ['name', 'code'],  
				data : [['内部部门', '1'],
						['外部部门', '2']]
    			
        	});    
    		var recordFields = [
    			{	
    				name : 'id', mapping : 'id'
    			},
    			{	
    				name : 'name', mapping : 'name'
    			},
    			{
    				name : 'de', mapping : 'de'
    			},
    			{
    				name : 'type', mapping : 'type'
    			},
    			{
    				name : 'sum', mapping : 'sum'
    			}
    		];
    		var remoteJsonStore = new Ext.data.JsonStore({
    			fields	:	recordFields,
    			
    			url		:	'http://localhost/pw/json/getDepartmentsJson.action',
    			
    			totalProperty	:	'totalCount',
    			root	:	'departments',
    			id		:	'departmentRemoteStore',
    			autoLoad	:	false,
    			remoteSort	:	true
    		});
    		
    		var recordField = [
    			'id', 'name', 'de','type' ,'sum'
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
    				header	:	'部门名称',
    				dataIndex	:	'name',
    				sortable	: true,
    				width	:	200
    			},
    			{
    				header	:	'部门描述',
    				dataIndex	:	'de',
    				sortable	: true,
    				width	:	250
    			},
    			{
    				header	:	'部门类型',
    				dataIndex	:	'type',
    				sortable	: true,
    				width	:	100
    			},
    			{
    				header	:	'用户数量',
    				dataIndex	:	'sum',
    				sortable	: false,
    				width	:	100
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
						text	:	'添加部门',
						iconCls	:	'add',
						id	:	'add'
					},
					{
						text	:	'修改部门',
						iconCls	:	'edit',
						id	:	'edit'
					},
					{
						text	:	'删除部门',
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
    					title	:	'所有投稿部门',
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
                             		url	:	'ajax/deleteDepartment',
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
  									fieldLabel	:	'部门名称',
  									name	:	'name',
  									allowBlank	:	false,
  									value	:	record.data.name
  								},
  								{
  									fieldLabel	:	'部门描述',
  									name	:	'de',
  									allowBlank	:	false,
  									value	:	record.data.de
  								},
  								{
  									xtype		:	'combo',
    								fieldLabel	:	'部门类型',
    								anchor		:	'-50',
    								store		:	typeStore,
    								displayField	:	'name',
    		 						valueField		:	'code',
    		 						hiddenName	:	'type',
    		 						mode 		: 	'local',
    		 						allowBlank	:	false,
    		 						editable    :	false,
    		 						triggerAction	: 	'all',  
    								selectOnFocus	:	true,
  									value	:	record.data.type
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
    										url			:	'ajax/changeDepartment',
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
  							fieldLabel	:	'部门名称 ',
  							name	:	'name',
  							allowBlank	:	false
  						},
  						{
  							fieldLabel	:	'部门描述 ',
  							name	:	'de',
  							allowBlank	:	false
  						},
  						{
  							xtype		:	'combo',
    						fieldLabel	:	'部门类型',
    						anchor		:	'-50',
    						store		:	typeStore,
    						displayField	:	'name',
    		 				valueField		:	'code',
    		 				hiddenName	:	'type',
    		 				mode 		: 	'local',
    		 				allowBlank	:	false,
    		 				editable    :	false,
    		 				triggerAction	: 	'all',  
    						selectOnFocus	:	true
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
    								url			:	'ajax/addDepartment',
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
    					height	:	180,
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
    		Ext.StoreMgr.get('departmentRemoteStore').load({
    			params	:	{
    				start	:	0,
    				limit	:	20
    			}
    		});
    	});
    	
    
    </script> 
  </body>
</html>
