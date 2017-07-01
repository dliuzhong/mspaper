<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    
    <title>addContent.jsp</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/decorators/admin/js/jquery.min.js" ></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/decorators/admin/ckeditor/ckeditor.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/decorators/admin/ckeditor/config.js" charset="utf-8"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/decorators/admin/js/Ext.form.CKEditor.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/decorators/admin/js/Ext.form.BasicForm.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/decorators/admin/js/jquery.Jcrop.min.js"></script>
	<link href="${pageContext.request.contextPath}/decorators/admin/css/edit.css" type="text/css" rel="stylesheet">
  	<link href="${pageContext.request.contextPath}/decorators/admin/css/jquery.Jcrop.css" type="text/css" rel="stylesheet">
  </head>
  
  <body>
     <script type="text/javascript">
    
    	Ext.onReady(function() {
    		Ext.QuickTips.init();
			var remoteJsonStore = new Ext.data.JsonStore({
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
			
    		var leftPanel = new Ext.Panel({
				height	:	600,
				width	:	415,
				border	:	false,
				html	:	'<div id="coords" style="width:400px;height:600px;padding-left:5px;"><img src="${pageContext.request.contextPath}/paper/<s:property value="model.pic" escape="false"/>" id="cropbox"></div>'
			});
			var editFormPanel = new Ext.form.FormPanel({
				id		:	'editFormPanel',
				height	:	650,
				width	:	580,
				labelWidth	:	60,
				title	:	'新闻内容',
				frame	:	true,
				bodyStyle	:	'padding: 6px',
				defaultType	:	'textfield',
				defaults	:	{
					msgTarget	:	'side',
					anchor	:	'-20'
				},
  				allowBlank	:	false,
  				tbar:	[
  					{
  						text:'截取',
  						iconCls	:	'pic_cut',
            			handler:function(){
  						
							$('#cropbox').Jcrop({
								onChange : showCoords,
								onSelect : showCoords
							});
						}
  					}
  				],
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
										fieldLabel	:	'报纸期号',
										name	:	'paper_id',
										id		:	'paper_id',
										allowBlank	:	false,
										readOnly	:	true,
										value	:	'<s:property value="model.paper.paper" escape="false"/>'
									
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
										fieldLabel	:	'所在版号',
										name	:	'layout_no',
										id		:	'layout_no',
										allowBlank	:	false,
										readOnly	:	true,
										value	:	'<s:property value="model.layout_no" escape="false"/>'
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
										fieldLabel	:	'左',
										allowBlank	:	false,
										readOnly	:	true,
										name	:	'zuo',
										id		:	'left'
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
										fieldLabel	:	'上',
										allowBlank	:	false,
										readOnly	:	true,
										name	:	'shang',
										id		:	'top'
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
										fieldLabel	:	'宽',
										readOnly	:	true,
										allowBlank	:	false,
										name	:	'kuan',
										id		:	'width'
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
										xtype : "textfield",
										fieldLabel	:	'高',
										allowBlank	:	false,
										readOnly	:	true,
										name	:	'gao',
										id		:	'height'
									}
								]
							}
						
					
						]
					},
				
					{
						xtype	:	'textarea',
						fieldLabel	:	'标题',
						width	:	200,
						height	:	40,
						allowBlank	:	false,
						name	:	'title',
						id		:	'title'
					},
					{
						fieldLabel	:	'作者',
						allowBlank	:	false,
						name	:	'author',
						id		:	'author'
					},
					{
						xtype		:	'combo',
						emptyText : '-- 请选择 --', 
    					fieldLabel	:	'专题',
    					anchor		:	'-2',
    					forceSelection	:	true,
    					displayField	:	'name',
    		 			valueField		:	'id',
    		 			hiddenName	:	'subjectid',
    		 			loadingText	:	'专题加载中...',
    		 			minChars	:	1,
    		 			pageSize	:	6,
    		 			store	:	remoteJsonStore,
    		 			triggerAction: 'all',  
    					selectOnFocus	:	true,
    		 			allowBlank	:	false
					},
					{
						xtype: 'ckeditor',
  						fieldLabel: '内容', 
  						name: 'content',
						id		:	'content'
					}
				
				],
				buttons	:	[
    				{
    					text	:	'提交',
    					handler	:	function() {
    						
    						var form = Ext.getCmp('editFormPanel');
							form.getForm().submit({
    							waitMsg	:'正在提交中...',
    							waitTitle	:	'提示',
    							url			:	'ajax/addNews?paperid=<s:property value="model.paper.id" escape="false"/>&layoutid=<s:property value="model.id" escape="false"/>',
  								method		:	'POST',
    							
    							success : function(form, action) {
    								 //Ext.MessageBox.alert("提示框","这是一个提示框",function(){alert("提示框关闭")});
                            		Ext.MessageBox.alert('消息','新增成功！', function closeWin() {var win = parent.Ext.getCmp('newWindow');win.close(); });
                            		
                            		
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
    						var form = Ext.getCmp('editFormPanel');
    						form.getForm().reset();
    					
    					}
    				}
    			]
			});
    		
    		var editPanel= new Ext.Panel({
    			id		:	'editPanel',
    			height	:	660,
				width	:	'100%',
				renderTo	:	Ext.getBody(),
				layout	:	'column',
    			items	:	[
    				leftPanel,
    				editFormPanel
    			]
    		});
    		
    	});
    	function showCoords(c) {
			$("#left").val(c.x);
			$("#top").val(c.y);
			$("#width").val(c.w);
			$("#height").val(c.h);
	
		};
    
    </script>
  </body>
</html>
