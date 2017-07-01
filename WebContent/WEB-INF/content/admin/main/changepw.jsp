<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
  <head>
    
    <title>管理员密码修改</title>
  

  </head>
  
  <body>
    <script type="text/javascript">
    	Ext.onReady(function() {
    		Ext.QuickTips.init();
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
  						name		:	'password',
  						id			:	'password',
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
    								url			:	'ajax/changeAdminPwd',
    								params		:	'id=<s:property value="#session.adminid"/>',
  									method		:	'POST',
    							
    								success : function(form, action) {
    								 	
                            			Ext.MessageBox.alert('消息','修改成功！', form.reset());
                            		
                            		
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
  					}
  				]
        	});
        	var changewin = new Ext.Window({
				id		:	'changewin',
    			title	:	'密码修改',
    			layout	:'form',  
        		width	:300, 
       	 		closeAction	:'hide',  
        		closable	:	false,
    			border	:	false,
    			resizable	:	false,
    			draggable	:	false,    
        		modal		:true,  
        		
        		resizable	:	false,
        		items	:	[
        			changeForm
        		]
			});
			changewin.show();
    	});
    
    </script>
  </body>
</html>
