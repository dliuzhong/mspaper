<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
  <head>
    
    <title>commentSys.jsp</title>
 

  </head>
  
  <body>
        <script type="text/javascript">
    	new Ext.onReady(function() {
    		var topFormPanel = new Ext.form.FormPanel({
    			title	:	'评论设置',
    			id		:	'topFormPanel',
    			frame	:	true,
    			allowBlank	:	false,
    			//standardSubmit: true,
    			columnWidth	:	.3,
    			collapsible	:	false,
    			defaults	:	{
    				msgTarget	:	'side',
    				anchor	:	'-20'
    			},
    			items	:	[
    				{
    					xtype	:	'radiogroup',
    					fieldLabel	:	'评论开关',
    					anchor	:	'100%',
    					items	:	[
    						{
    							boxLabel	:	'关',
    							inputValue	:	'false',
    							name	:	'isShow',
    							<s:if test='isShow=="false"'>
    							checked	:	true,
    							</s:if>
    							listeners: {
                    				check: function (obj, ischecked) {
                        				if (ischecked) {
                                         	tp = Ext.getCmp('topFormPanel');
    						
    										tp.getForm().submit({
    											waitMsg		:'正在提交中...',
    											waitTitle	:'提示',
    											url			:	'ajax/changeCommentOpen',
    											method	:	'post',
    											success : function(form, action) {
    												location.reload();
                        						},
                        						failure : function(form, action) {
                            						form.reset();
                                					Ext.MessageBox.alert('警告','修改失败！');
                       	 						}
    										});	
                                        } 
                        			}
                    			}  
    						},
    						{
    							boxLabel	:	'开',
    							name	:	'isShow',
    							inputValue	:	'true',
    							<s:if test='isShow=="true"'>
    							checked	:	true,
    							</s:if>
    							listeners: {
                    				check: function (obj, ischecked) {
                        				if (ischecked) {
                                         	tp = Ext.getCmp('topFormPanel');
    						
    										tp.getForm().submit({
    											waitMsg		:'正在提交中...',
    											waitTitle	:'提示',
    											url			:	'ajax/changeCommentOpen',
    											method	:	'post',
    											success : function(form, action) {
    												location.reload();
                        						},
                        						failure : function(form, action) {
                            						form.reset();
                                					Ext.MessageBox.alert('警告','修改失败！');
                       	 						}
    										});	
                                        } 
                        			}
                    			}
    						}
    					]
    				}
    			]
    		});
    		var topNoticePanel = new Ext.Panel({
    			title	:	'帮助',
    			columnWidth	:	.7,
    			html	:	'Notice:目前不开放。'
    		});
    		var topPanel = new Ext.Panel({
    			layout	:	'column',
    			items	:	[
    				topFormPanel,
    				topNoticePanel
    			]
    		
    		});
    		
    		var recordFields = [
    			{	
    				name : 'id', mapping : 'id'
    			},
    			{	
    				name : 'newsid', mapping : 'newsid'
    			},
    			{
    				name : 'paper', mapping : 'paper'
    			},
    			{
    				name : 'layout_no', mapping : 'layout_no'
    			},
    			{
    				name : 'name', mapping : 'name'
    			},
    			{
    				name : 'comment', mapping : 'comment'
    			},
    			{
    				name : 'time', mapping : 'time'
    			},
    			{
    				name : 'ip', mapping : 'ip'
    			},
    			{
    				name : 'pass', mapping : 'pass'
    			}
    		];
    		var remoteJsonStore = new Ext.data.JsonStore({
    			fields	:	recordFields,
    			
    			url		:	'http://localhost/pw/json/getCommentsJson.action',
    			
    			totalProperty	:	'totalCount',
    			root	:	'comments',
    			id		:	'commentRemoteStore',
    			autoLoad	:	false,
    			remoteSort	:	true
    		});
    		
    		var recordField = [
    			'id', 'newsid', 'paper', 'layout_no', 'name', 'comment', 'time', 'ip', 'pass'
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
    				width	:	40
    			},
    			{
    				header	:	'新闻号',
    				dataIndex	:	'newsid',
    				sortable	: true,
    				width	:	60
    			},
    			{
    				header	:	'期号',
    				dataIndex	:	'paper',
    				sortable	: true,
    				width	:	50
    			},
    			{
    				header	:	'版号',
    				dataIndex	:	'layout_no',
    				sortable	: false,
    				width	:	40
    			},
    			{
    				header	:	'昵称',
    				dataIndex	:	'name',
    				sortable	: true,
    				width	:	200
    			},
    			{
    				header	:	'评论内容',
    				dataIndex	:	'comment',
    				sortable	: false,
    				width	:	580
    			},
    			{
    				header	:	'评论时间',
    				dataIndex	:	'time',
    				sortable	: true,
    				width	:	130
    			},
    			{
    				header	:	'评论者IP地址',
    				dataIndex	:	'ip',
    				width	: 140
    			},
    			{
    				header	:	'审核通过',
    				dataIndex	:	'pass'
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
    			
                sm		:	checkbox,
				columns	:	colModel,
				bbar	:	pagingToolbar,
				tbar	:	[
					{
						text	:	'审核通过',
						iconCls	:	'ok',
						id	:	'pass'
					},
					{
						text	:	'不通过 ',
						iconCls	:	'stop',
						id	:	'unpass'
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
				loadMask	:	true,
    			autorExpandColumn	:	'addressCol'
    		});
    		new Ext.Viewport({
    			layout	:	'border',
    			items	:	[
    				{
    					region	:	'north',
    					height	:	70,
    					
    					items	:	topPanel
    				},
    				{
    					title	:	'所有评论',
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
                             		url	:	'ajax/deleteComment',
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
         	Ext.get('pass').on('click', function() {
             	var selections = grid.getSelectionModel().getSelections();    //先获取选择模型，然后从选择模型中获取选中的记录。
            	 if (selections.length > 0) {
                 	Ext.Msg.confirm('提示', '你确认审核通过选中的记录吗？', function(_btn) {
                     	if (_btn == 'yes') {
                         	for (var i = 0; i < selections.length; i++) {
                             	var record = selections[i];
                             	var conn = new Ext.data.Connection();
                             	conn.request({
                             		url	:	'ajax/passComment',
                             		params	:	{
                             			id	:	record.data.id
                             		},
                             		method	:	'POST',
                             		scope	:	this,
                             		callback	:	function(options, success, response) {
                             			if (success) {
                             				
                             			} else {
                             				Ext.MessageBox.alert('提示','审核失败！');
                   
                             			}
                             		}
                             		
                             	
                             	});
                            	
                         	}
                         	Ext.MessageBox.alert('消息','审核成功！', function reload() {location.reload();});
                     	}
                 	});
             	}
             	else
                 	Ext.Msg.alert("提示","你还没有选中记录.");
         	});
         	Ext.get('unpass').on('click', function() {
             	var selections = grid.getSelectionModel().getSelections();    //先获取选择模型，然后从选择模型中获取选中的记录。
            	 if (selections.length > 0) {
                 	Ext.Msg.confirm('提示', '你确认取消审核通过选中的记录吗？', function(_btn) {
                     	if (_btn == 'yes') {
                         	for (var i = 0; i < selections.length; i++) {
                             	var record = selections[i];
                             	var conn = new Ext.data.Connection();
                             	conn.request({
                             		url	:	'ajax/unpassComment',
                             		params	:	{
                             			id	:	record.data.id
                             		},
                             		method	:	'POST',
                             		scope	:	this,
                             		callback	:	function(options, success, response) {
                             			if (success) {
                             				//store.remove(record);
                             			} else {
                             				Ext.MessageBox.alert('提示','取消审核失败！');
                   
                             			}
                             		}
                             		
                             		
                             	
                             	});
                            	
                            	
                         	}
                         	Ext.MessageBox.alert('消息','取消审核成功！', function reload() {location.reload();});
                     	}
                 	});
             	}
             	else
                 	Ext.Msg.alert("提示","你还没有选中记录.");
         	});
         	Ext.StoreMgr.get('commentRemoteStore').load({
    			params	:	{
    				start	:	0,
    				limit	:	20
    			}
    		});
    	
    	});
    
    
    </script>
  </body>
</html>
