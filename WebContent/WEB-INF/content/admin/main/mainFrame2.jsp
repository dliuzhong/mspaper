<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>
<html>
  <head>
    
    <title>管理操作选择</title>
  
  </head>
  
  <body>
    <script type="text/javascript">
			Ext.onReady(function() {
				Ext.QuickTips.init();
				var html = '<div class="selectDiv">'+
			'<table style="margin:80px auto 80px auto;text-align: center;">'+
				'<tr>'+
					'<s:if test="#session.adminGrade==1"><td><a href="new/start"><img src="${pageContext.request.contextPath}/img/ad/new.png" style="border: none;"/><br/>添加新报纸</a>'+
					'</td>'+
					'<td><a href="search"><img src="${pageContext.request.contextPath}/img/ad/find.png" style="border: none;"/><br/>数据搜索</a>'+
					'</td>'+
					'<td><img src="${pageContext.request.contextPath}/img/ad/changepw.png" style="border: none;" onclick="changePW()"/><br/>密码修改'+
					'</td>'+
					'<td><a href="Loginout"><img src="${pageContext.request.contextPath}/img/ad/exit.png" style="border: none;"/><br/>注销</a>'+
					'</td></s:if>'+
				
					'<s:if test="#session.adminGrade==2"><td><a href="new/start"><img src="${pageContext.request.contextPath}/img/ad/new.png" style="border: none;"/><br/>添加新报纸</a>'+
					'</td>'+
					'<td><a href="edit/index"><img src="${pageContext.request.contextPath}/img/ad/edit.png" style="border: none;"/><br/>管理旧报纸</a>'+
					'</td>'+
					'<td><a href="search"><img src="${pageContext.request.contextPath}/img/ad/find.png" style="border: none;"/><br/>数据搜索</a>'+
					'</td>'+
					'<td><img src="${pageContext.request.contextPath}/img/ad/changepw.png" style="border: none;" onclick="changePW()"/><br/>密码修改'+
					'</td>'+
					'<td><a href="Loginout"><img src="${pageContext.request.contextPath}/img/ad/exit.png" style="border: none;"/><br/>注销</a>'+
					'</td></s:if>'+
				
					'<s:if test="#session.adminGrade==3"><td><a href="new/start"><img src="${pageContext.request.contextPath}/img/ad/new.png" style="border: none;"/><br/>添加新报纸</a>'+
					'</td>'+
					'<td><a href="edit/index"><img src="${pageContext.request.contextPath}/img/ad/edit.png" style="border: none;"/><br/>管理旧报纸</a>'+
					'</td>'+
					'<td><a href="search"><img src="${pageContext.request.contextPath}/img/ad/find.png" style="border: none;"/><br/>数据搜索</a>'+
					'</td>'+
					'<td><a href="system"><img src="${pageContext.request.contextPath}/img/ad/manage.png" style="border: none;"/><br/>系统管理</a>'+
					'</td>'+
					'<td><img src="${pageContext.request.contextPath}/img/ad/changepw.png" style="border: none;" onclick="changePW()"/><br/>密码修改'+
					'</td>'+
					'<td><a href="Loginout"><img src="${pageContext.request.contextPath}/img/ad/exit.png" style="border: none;"/><br/>注销</a>'+
					'</td></s:if>'+
					
				'</tr>'+
				
			'</table>'+
           
        '</div>';
        
        	var changeForm = new Ext.form.FormPanel({
        		id		:	'changeForm',
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
  						fieldLabel	:	'原密码',
  						allowBlank	:	false,
  						name		:	'oldpw',
  						id			:	'oldpw',
  						inputType	:	'password'
  					
  					},
  					{
  						fieldLabel	:	'新密码',
  						allowBlank	:	false,
  						name		:	'newpw',
  						id			:	'newpw',
  						inputType	:	'password'
  					
  					},
  					{
  						fieldLabel	:	'确认新密码',
  						allowBlank	:	false,
  						name		:	'renewpw',
  						id			:	'renewpw',
  						inputType	:	'password'
  					
  					}
  				
  				],
  				buttons	:	[
  					{
  						text	:	'确认',
  						handler	:	function() {
  							var newpw1 = Ext.getCmp('newpw').getValue();
  							var newpw2 = Ext.getCmp('renewpw').getValue();
  							if (newpw1 != newpw2) {
  								Ext.MessageBox.alert('警告', '新密码两次输入不一致！');
  							} else {
  								var form = Ext.getCmp('changeForm');
								form.getForm().submit({
    								waitMsg	:'正在提交中...',
    								waitTitle	:	'提示',
    								url			:	'changeUser',
    								params		:	'action=changepw&id=<%=session.getAttribute("userid") %>',
  									method		:	'POST',
    							
    								success : function(form, action) {
    								 	
                            			Ext.MessageBox.alert('消息','修改成功！', function closeWin() {var win = Ext.getCmp('changewin');win.hide();form.reset();});
                            		
                            		
                        			},
                        			failure : function(form, action) {
                            			form.reset();
                            			var outinfo = action.result.info;
                                		Ext.MessageBox.alert('警告',outinfo);
                       	 			}
    							});
  							}
  							
  						
  						}
  					},
  					{
  						text	:	'重置',
  						handler	:	function() {
  							var form = Ext.getCmp('changeForm');
    						form.getForm().reset();
  						}
  					},
  					{
  						text	:	'取消',
  						handler	:	function() {
  							Ext.getCmp('changewin').hide();
  						}
  					}
  				]
        	});
        	var changewin = new Ext.Window({
				id		:	'changewin',
    			title	:	'密码修改',
    			layout	:'form',  
        		width	:300,  
        		height	:160,  
       	 		closeAction	:'hide',  
        		   
        		modal		:true,  
        		
        		resizable	:	false,
        		items	:	[
        			changeForm
        		]
			});
			var myWin = new Ext.Window({
    			id		:	'myWin',
    			title	:	'绵阳师范学院校报网络版管理系统V2.0.1——主菜单（您为<s:if test="#session.adminGrade==1">低级管理员</s:if>' + 
    							'<s:if test="#session.adminGrade==2">中级管理员</s:if>' + 
    							'<s:if test="#session.adminGrade==3">高级管理员</s:if>）',
    			width	:	800,
    			height	:	300,
    			html	:	html,
    			closable	:	false,
    			border	:	false,
    			resizable	:	false,
    			draggable	:	false, 
    			items	:	[changewin],
    			tools	:	[
						{
							id	:	'help',
							handler	:	function() {
								open('help','_blank');
							}
						}
					]
    		});
    		myWin.show();
    		
		});
		 
		function changePW() {
			Ext.getCmp('changewin').show();
				
		};
		</script>
  </body>
</html>
