<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>newsEdit.jsp</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/decorators/admin/js/jquery.min.js" ></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/decorators/admin/ckeditor/ckeditor.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/decorators/admin/ckeditor/config.js" charset="utf-8"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/decorators/admin/js/Ext.form.CKEditor.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/decorators/admin/js/Ext.form.BasicForm.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/decorators/admin/js/jquery.Jcrop.min.js"></script>	<link href="${pageContext.request.contextPath}/decorators/admin/css/edit.css" type="text/css" rel="stylesheet">
  	
  	<link href="${pageContext.request.contextPath}/decorators/admin/css/jquery.Jcrop.css" type="text/css" rel="stylesheet">
  	<style type="text/css">
    .retcOK {
    	z-index:1;
    	filter:alpha(Opacity=80);-moz-opacity:0.5;opacity: 0.5;
    }
    
    
    </style>
  </head>
  
  <body>
  	<div id="ok" class="retcOK" style="left:${model.zuo}px;top:${model.shang}px;width:${model.kuan}px;height:${model.gao}px;"></div>
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
			
    		var leftPanel<s:property value="model.id"/> = new Ext.Panel({
				height	:	600,
				width	:	415,
				border	:	false,
				html	:	'<div id="coords" style="width:400px;height:600px;padding-left:5px;"><img src="${pageContext.request.contextPath}/paper/<s:property value="pic" escape="false"/>" id="cropbox"></div>'
			});
			
			var editFormPanel<s:property value="model.id"/> = new Ext.form.FormPanel({
				id		:	'editFormPanel<s:property value="model.id"/>',
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
										value	:	'<s:property value="model.layout.layout_no" escape="false"/>'
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
										name	:	'zuo',
										id		:	'left',
										readOnly	:	true,
										value	:	'<s:property value="model.zuo" escape="false"/>'
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
										name	:	'shang',
										id		:	'top',
										readOnly	:	true,
										value	:	'<s:property value="model.shang" escape="false"/>'
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
										allowBlank	:	false,
										name	:	'kuan',
										id		:	'width',
										readOnly	:	true,
										value	:	'<s:property value="model.kuan" escape="false"/>'
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
										name	:	'gao',
										id		:	'height',
										readOnly	:	true,
										value	:	'<s:property value="model.gao" escape="false"/>'
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
						id		:	'title',
						value	:	'<s:property value="model.title" escape="false"/>'
					},
					{
						fieldLabel	:	'作者',
						allowBlank	:	false,
						name	:	'author',
						id		:	'author',
						value	:	'<s:property value="model.author" escape="false"/>'
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
    		 			allowBlank	:	false,
    		 			triggerAction: 'all',  
    					selectOnFocus	:	true,
    		 			value	:	'<s:property value="model.subject.id" escape="false"/>'
					},
					{
						xtype: 'ckeditor', 
  						fieldLabel: '内容', 
  						name: 'content',
						id		:	'content',
						value	:	'<s:property value="model.content" escape="false"/>'
					}
				
				],
				buttons	:	[
    				{
    					text	:	'确定',
    					handler	:	function() {
    						var form = Ext.getCmp('editFormPanel<s:property value="model.id" escape="false"/>');
							form.getForm().submit({
    							waitMsg	:'正在提交中...',
    							waitTitle	:	'提示',
    							url			:	'ajax/doReditNews?id=<s:property value="model.id" escape="false"/>&file_path=<s:property value="model.file_path" escape="false"/>',
  								method		:	'POST',
    							
    							success : function(form, action) {
    								
                            		Ext.MessageBox.alert('消息','修改成功！', function() {location.reload();});
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
    						var form = Ext.getCmp('editFormPanel<s:property value="model.id" escape="false"/>');
    						form.getForm().reset();
    					
    					}
    				}
    			]
			});
    		
    		var editPanel<s:property value="model.id" escape="false"/>= new Ext.Panel({
    			id		:	'editPanel<s:property value="model.id" escape="false"/>',
    			height	:	660,
				width	:	'100%',
				renderTo	:	Ext.getBody(),
				layout	:	'column',
    			items	:	[
    				leftPanel<s:property value="model.id" escape="false"/>,
    				editFormPanel<s:property value="model.id" escape="false"/>
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
