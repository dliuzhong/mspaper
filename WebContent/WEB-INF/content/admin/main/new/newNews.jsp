<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    <title>newNews.jsp</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/decorators/admin/js/jquery.min.js" ></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/decorators/admin/ckeditor/ckeditor.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/decorators/admin/ckeditor/config.js" charset="utf-8"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/decorators/admin/js/Ext.form.CKEditor.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/decorators/admin/js/Ext.form.BasicForm.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/decorators/admin/js/jquery.Jcrop.min.js"></script>
	<link href="${pageContext.request.contextPath}/decorators/admin/css/edit.css" type="text/css" rel="stylesheet">
  	<link href="${pageContext.request.contextPath}/decorators/admin/css/jquery.Jcrop.css" type="text/css" rel="stylesheet">
  	<style type="text/css">
    .retcOK {
    	z-index:1;
    	filter:alpha(Opacity=80);-moz-opacity:0.5;opacity: 0.5;
    }
    
    
    </style>
  </head>
  
  <body>

<s:iterator id="news" value="newss" status="st"> 
  <div id='ok<s:property value="#st.count" escape="false"/>' class='retcOK' style='left:<s:property value="%{zuo+10}" escape="false"/>px;top:<s:property value="%{shang+20}" escape="false"/>px;width:<s:property value="kuan" escape="false"/>px;height:<s:property value="gao" escape="false"/>px;color:white;font-size:18px;text-align:center;vertical-align:middle;line-height:<s:property value="gao-18" escape="false"/>px;' >(<s:property value="#st.count"/>)</div>
</s:iterator> 


<script type="text/javascript">

	Ext.onReady(function() {
		Ext.QuickTips.init();
		
		var leftPanel = new Ext.Panel({
			height	:	600,
			width	:	410,
			border	:	false,
			html	:	'<div style="width:400px;height:600px;padding-left:10px;"><img src="${pageContext.request.contextPath}/paper/<s:property value="pic" escape="false"/>" id="cropbox"></div>'
		});
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
		  
		var centerPanel = new Ext.FormPanel({
			standardSubmit: true, 
  			url:'addNews?paperid=<s:property value="#session.paperid" />&layoutid=<s:property value="layoutid"/>&pic=<s:property value="pic" escape="false"/>',
  			method:'POST',
			height	:	640,
			width	:	654,
			labelWidth	:	60,
			title	:	'新闻内容',
			frame	:	true,
			bodyStyle	:	'padding: 6px',
			defaultType	:	'textfield',
			defaults	:	{
				msgTarget	:	'side',
				anchor	:	'-20'
			},
  			id		:	'dataForm',
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
									readOnly	:	true,
									allowBlank	:	false,
									value	:	'<s:property value="#session.newpaper"/>'
									
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
									readOnly	:	true,
									allowBlank	:	false,
									value	:	'<s:property value="layout_no"/>'
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
									allowBlank	:	false,
									readOnly	:	true,
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
									xtype : 'textfield',
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
    				handler	:	submit
    			},
    			{
    				text	:	'重置',
    				handler	:	reset
    			}
    		]
		});
		var buttomLeftPanel = new Ext.Panel({
			
			height	:	100,
			width	:	500,
		});
		var buttomRightPanel = new Ext.Panel({
			
			title	:	'已添加',
			height	:	150,
			width	:	654,
			border	:	false,
			html	:	'<samll style="font-size:13px;color:green;"><s:iterator id="news" value="newss" status="st">(<s:property value="#st.count"/>). <s:property value="title" escape="false"/>&nbsp;<a href="delete?id=<s:property value="id"/>&layoutid=<s:property value="layout.id"/>&layout_no=<s:property value="layout.layout_no"/>&pic=<s:property value="pic" escape="false"/>" style="color:red;text-decoration:none;">[删除]</a><a href="${pageContext.request.contextPath}/paper/<s:property value="paper.paper" escape="false"/>/e<s:property value="layout.layout_no" escape="false"/>/<s:property value="file_path" escape="false"/>" target="_blank" style="text-decoration:none;color:blue">[查看]</a></span></div>&nbsp;&nbsp;</s:iterator></small>'
		});
		var panel = new Ext.Panel({
			width	:	'100%',
			
			renderTo	:	Ext.getBody(),
			layout	:	'table',
			layoutConfig	:	{
				columns	:	2
			},
			items	:	[
				leftPanel,
				centerPanel,
				buttomLeftPanel,
				buttomRightPanel
			]
		});
		frm = Ext.getCmp('dataForm');
		function submit() {
    			
    		frm.getForm().submit({
    			waitMsg:'正在提交中...',
    			waitTitle:'提示'
    		});
    	};
    	function reset() {
    		frm.getForm().reset();
    	};
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
