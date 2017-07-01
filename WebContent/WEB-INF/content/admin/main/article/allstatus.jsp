<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>
<html>
  <head>
    
    <title>allstatus.jsp</title>
    
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
    				name : 'status', mapping : 'status'
    			},
    			{
    				name : 'checktime', mapping : 'checktime'
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
    			
    			url		:	'http://localhost/pw/json/getAllStatusArticlesJson.action',
    			
    			totalProperty	:	'totalCount',
    			root	:	'status',
    			id		:	'allStatusRemoteStore',
    			autoLoad	:	false,
    			remoteSort	:	true
    		});
    		
    		var recordField = [
    			'id', 'title', 'msuser', 'keyword', 'content', 'type', 'time', 'status', 'checktime', 'other', 'see'
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
    				header	:	'状态',
    				dataIndex	:	'status',
    				sortable	: true,
    				width	:	100
    			},
    			{
    				header	:	'审核时间',
    				dataIndex	:	'checktime',
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
						text	:	'查看',
						iconCls	:	'check',
						id	:	'check'
					},
					{
						text	:	'删除',
						iconCls	:	'delete',
						id	:	'remove'
					},
					
					{
						text	:	'Word导出',
						iconCls	:	'export',
						id	:	'export'
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
    					title	:	'所有未审核稿件',
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
  										'<font color="blue">审核意见：' + record.data.status +
  										'</font><br/>' + 
  										'审核时间：' + record.data.checktime +
  										'<br/>' + 
  										'备注：' + record.data.other,
  						 
  							buttons	:	[
  								
    							{
    								text	:	'关闭窗口',
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
         	Ext.get('export').on('click', function() {
             	var selections = grid.getSelectionModel().getSelections();    //先获取选择模型，然后从选择模型中获取选中的记录。
            	 if (selections.length == 1) {
            	 
                 	Ext.Msg.confirm('提示', '你确认Word导出选中的记录吗？', function(_btn) {
                     	
                     	if (_btn == 'yes') {
                     		
                        	var record = selections[0];
                        	open('ajax/exportArticle?id=' + record.data.id, '_self');
                            	
                     	}
                 	});
             	}
             	else
                 	Ext.Msg.alert("提示","你还没有选中记录或选中了多条记录！");
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
                             		url	:	'ajax/deleteArticle',
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
    		Ext.StoreMgr.get('allStatusRemoteStore').load({
    			params	:	{
    				start	:	0,
    				limit	:	20
    			}
    		});
    	});
    	
    
    </script> 
  </body>
</html>
