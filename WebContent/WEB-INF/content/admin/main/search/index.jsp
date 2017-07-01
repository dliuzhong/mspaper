<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>新闻信息搜索</title>

  </head>
  
  <body>
    <script type="text/javascript">
    	Ext.onReady(function() {
    		Ext.QuickTips.init();
    		
    		var paperJsonStore = new Ext.data.JsonStore({
				root	:	'papers',
				totalProperty	:	'totalCount',
				baseParams	:	{
					column	:	'paper'
				},
				fields	:	[
					{
						name	:	'id',
						mapping	:	'id'
					},
					{
						name	:	'paper',
						mapping	:	'paper'
					}
				
				],
				proxy	:	new Ext.data.ScriptTagProxy({
					url	:	'http://localhost/pw/json/papersQuery.action'
					//url	:	'http://extjsinaction.com/dataQuery.php'
				})
			});
    		
    		var subjectJsonStore = new Ext.data.JsonStore({
				root	:	'subjects',
				totalProperty	:	'totalCount',
				baseParams	:	{
					column	:	'name'
				},
				fields	:	[
					{
						name	:	'id',
						mapping	:	'id'
					},
					{
						name	:	'name',
						mapping	:	'name'
					}
				
				],
				proxy	:	new Ext.data.ScriptTagProxy({
					url	:	'http://localhost/pw/json/subjectsQuery.action'
					//url	:	'http://extjsinaction.com/dataQuery.php'
				})
			});
    		var searchFormPanel = new Ext.form.FormPanel({
    			id		:	'searchFormPanel',
    			standardSubmit	:	true, 
  				url		:	'adminSeach',
  				method	:	'POST',
    			collapsible	:	true,
    			title	:	'新闻搜索',
    			frame	:	true,
    			labelWidth	:	100,
    			defaultType	:	'textfield',
    			allowBlank	:	false,
    			defaults	:	{
    				msgTarget	:	'side',
    				anchor	:	'-20'
    			},
    			columnWidth	:	.4,
    			items	:	[
   
    				{
  
    					fieldLabel	:	'新闻搜索',
    					name	:	'query',
    					id		:	'searchText',
    					width	:	300,
    					value	:	'<s:property value="query" escape="false" />'
    				},
    				{
    					xtype	:	'radiogroup',
    					anchor	:	'100%',
    					
    					items	:	[
    						{
    							boxLabel	:	'关键字',
    							inputValue	:	'word',
    							name	:	'work'
    							<s:if test='work=="word" || work==null ||work=="find"'>
    							,checked	:	true
    							</s:if>
    						},
    						{
    							boxLabel	:	'标题',
    							name	:	'work',
    							inputValue	:	'title'
    							<s:if test='work=="title"'>
    							,checked	:	true
    							</s:if>
    						},
    						{
    							boxLabel	:	'作者',
    							name	:	'work',
    							inputValue	:	'author'
    							<s:if test='work=="author"'>
    							,checked	:	true
    							</s:if>
    						},
    						{
    							boxLabel	:	'专题',
    							name	:	'work',
    							inputValue	:	'subject'
    							<s:if test='work=="subject"'>
    							,checked	:	true
    							</s:if>
    						}
    					]
    				}
   
    			],
     
	
    			buttons	:	[
    				{
    					text	:	'提交',
    					handler	:	function() {
    						tp = Ext.getCmp('searchFormPanel');
    						
    						tp.getForm().submit({
    							
    							waitMsg:'正在提交中...',
    							waitTitle:'提示'
    						});
    					}
    				},
    				{
    					text	:	'重置',
    					handler	:	function() {
    						tp = Ext.getCmp('searchFormPanel');
    						tp.form.reset();
    					}
    				}
    			]
    
    		}); 
    		
    	
	 		var findFormPanel = new Ext.form.FormPanel({
    			id		:	'findFormPanel',
    			standardSubmit	:	true, 
  				url		:	'adminFind',
  				method	:	'POST',
    			collapsible	:	true,
    			title	:	'信息检索',
    			frame	:	true,
    			labelWidth	:	50,
    			width	:	300,
    			allowBlank	:	true,
    			defaults	:	{
    				msgTarget	:	'side',
    				anchor	:	'-20'
    			},
    			columnWidth	:	.6,
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
										
    		 							xtype		:	'combo',
    									fieldLabel	:	'期号',
    									forceSelection	:	true,
    									width	:	250,
    									displayField	:	'paper',
    		 							valueField		:	'id',
    		 							hiddenName	:	'paperid',
    		 							loadingText	:	'期号加载中...',
    		 							minChars	:	1,
    		 							pageSize	:	6,
    		 							store	:	paperJsonStore,
    		 							triggerAction: 'all',  
    									selectOnFocus:true,
    		 							value	:	'<s:property value="paperid" escape="false"/>'
									
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
										xtype		:	'combo', 
    									fieldLabel	:	'专题',
    									width	:	250,
    									forceSelection	:	true,
    									displayField	:	'name',
    		 							valueField		:	'id',
    		 							hiddenName	:	'subjectid',
    		 							loadingText	:	'专题加载中...',
    		 							minChars	:	1,
    		 							pageSize	:	6,
    		 							store	:	subjectJsonStore,
    		 							triggerAction: 'all',  
    									selectOnFocus:true,
    		 							value	:	'<s:property value="subjectid" escape="false"/>'
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
										xtype	:	'numberfield',
    									fieldLabel	:	'年份',
    									maxLength	:	4,
    									minLength	:	4,
    									name	:	'year',
    									id		:	'year',
    									width	:	200,
    									value	:	'<s:property value="year" escape="false"/>'
									
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
										fieldLabel	:	'作者',
										name	:	'author',
										id		:	'author',
										width	:	200,
										value	:	'<s:property value="author" escape="false"/>'
									}
								]
							}
						
					
						]
					}
    			
    			],
    			buttons	:	[
    				{
    					text	:	'提交',
    					handler	:	function() {
    						tp = Ext.getCmp('findFormPanel');
    						
    						tp.getForm().submit({
    							
    							waitMsg:'正在提交中...',
    							waitTitle:'提示'
    						});
    					}
    				},
    				{
    					text	:	'重置',
    					handler	:	function() {
    						tp = Ext.getCmp('findFormPanel');
    						tp.form.reset();
    					}
    				}
    			]
	 		
	 		});
	 		
	 		
    		var recordFields = [
    			{	
    				name : 'id', mapping : 'id'
    			},
    			{	
    				name : 'title', mapping : 'title'
    			},
    			{
    				name : 'paper', mapping : 'paper'
    			},
    			{
    				name : 'layout_no', mapping : 'layout_no'
    			},
    			{
    				name : 'author', mapping : 'author'
    			},
    			{
    				name : 'subject', mapping : 'subject'
    			},
    			{
    				name : 'cbtime', mapping : 'cbtime'
    			},
    			{
    				name : 'lasteditdatetime', mapping : 'lasteditdatetime'
    			},
    			{
    				name : 'see', mapping : 'see'
    			}
    		];
    		
    		var remoteJsonStore = new Ext.data.JsonStore({
    			fields	:	recordFields,
    			<s:if test="url!=null">
    			url		:	'http://localhost/pw/' + '<s:property value="url" escape="false"/>',
    			</s:if>
    			<s:else>
    			url		:	'http://localhost/pw/json/seachQuery.action?work=all',
    			</s:else>
    			totalProperty	:	'totalCount',
    			root	:	'results',
    			id		:	'searchRemoteStore',
    			autoLoad	:	false,
    			remoteSort	:	true
    		});
    		
    		var recordField = [
    			'id', 'title', 'paper', 'layout_no', 'author', 'subject', 'cbtime', 'lasteditdatetime', 'see'
    		];
    		
    		var colorTextBlue = function(id) {
    			return	'<span style="color: #000FF;">' + id + '</span>';
    		};
    		
    		var colModel = [
    			{
    				header	:	'编号',
    				sortable	: false,
    				dataIndex	:	'id',
    				width	:	35,
    				renderer	:	colorTextBlue
    			},
    			{
    				header	:	'标题',
    				dataIndex	:	'title',
    				sortable	: false,
    				width	:	300
    			},
    			{
    				header	:	'期号',
    				dataIndex	:	'paper',
    				sortable	: false,
    				width	:	50
    			},
    			{
    				header	:	'版号',
    				dataIndex	:	'layout_no',
    				sortable	: false,
    				width	:	40
    			},
    			{
    				header	:	'作者',
    				dataIndex	:	'author',
    				sortable	: false,
    				width	:	200
    			},
    			{
    				header	:	'专题',
    				dataIndex	:	'subject',
    				sortable	: false,
    				width	:	200
    			},
    			{
    				header	:	'出版日期',
    				dataIndex	:	'cbtime',
    				sortable	: false,
    				width	:	100
    			},
    			{
    				header	:	'最后编辑时间',
    				dataIndex	:	'lasteditdatetime',
    				sortable	: false,
    				width	:	150
    			},
    			{
    				header	:	'查看',
    				dataIndex	:	'see'
    			}
    			
    		];
    		
    		var pagingToolbar = {
    			xtype	:	'paging',
    			store	:	remoteJsonStore,
    			pageSize	:	15,
    			displayInfo	:	true
    		}
    		
    		var gridView = new Ext.grid.GridView();
    		var selModel = new Ext.grid.RowSelectionModel({
    			singleSelect	:	true
    		});
    		
    		var grid = new Ext.grid.GridPanel({
    			autoHeight	:	true,
    			autoWidth	:	true,
    			autoScroll	: 	true,
    			store	:	remoteJsonStore,
    			view	:	gridView,
    			columns	:	colModel,
    			bbar	:	pagingToolbar,
    			loadMask	:	true,
    			autorExpandColumn	:	'addressCol'
    		});
    		new Ext.Viewport({
    			layout	:	'border',
    			items	:	[
    				{
    					title	:	'XXX报社电子报刊管理系统——新闻信息搜索',
    					region	:	'north',
    					height	:	150,
    					layout	:	'column',
    					items	:	[
    						searchFormPanel,
    						findFormPanel
    					]
    				},
    				{
    					title	:	'搜索结果',
    					region	:	'center',
    					items	:	[
    						grid
    					],
    					tbar	:	[
    						{
    							text	:	'导出Excel',
    							iconCls	:	'export',
    							handler :	function() {
    							<s:if test="work!=null">
    								open('downloadReport?work=<s:property value="work" escape="false"/>&query=<s:property value="query" escape="false"/>&paperid=<s:property value="paperid" escape="false"/>&subjectid=<s:property value="subjectid" escape="false"/>&year=<s:property value="year" escape="false"/>&author=<s:property value="author" escape="false"/>', '_self');
    							</s:if>
    							<s:else>
    							    open('downloadReport?work=all&query=<s:property value="query" escape="false"/>&paperid=<s:property value="paperid" escape="false"/>&subjectid=<s:property value="subjectid" escape="false"/>&year=<s:property value="year" escape="false"/>&author=<s:property value="author" escape="false"/>', '_self');
    							</s:else>
    							}
    						}
    							
    				
    						
    						
    					]
    				}
    			]
    			
    		});
    		Ext.StoreMgr.get('searchRemoteStore').load({
    			params	:	{
    				start	:	0,
    				limit	:	15
    			}
    		});
    	});
    
    </script>
  </body>
</html>
