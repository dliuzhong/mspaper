<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>
<html>
  <head>
    
    <title>输入新报基本信息</title>
  
  </head>
  
  <body>
 
    <script type="text/javascript">
  		Ext.onReady(function() {
  		
  			Ext.QuickTips.init();
  			
  			var fpItems = [
  				{
  					fieldLabel	:	'添加新报期号',
  					name		:	'paper',
  					id			:	'paper',
  					allowBlank	:	false,
  					regex : /^\d+$/g,
  					regexText : '期号只能由数字组成',
  					emptyText	:	'请输入添加期号'
  				},
  				{
  					fieldLabel	:	'主办',
  					name		:	'zb',
  					id			:	'zb',
  					value		:	'<s:property value="model.zb" escape="false"/>',
  					allowBlank	:	false
  				},
  				{
  					fieldLabel	:	'出版编辑',
  					name		:	'cb',
  					id			:	'cb',
  					value		:	'<s:property value="model.cb" escape="false"/>',
  					allowBlank	:	false
  				},
  				{
  					fieldLabel	:	'总编',
  					name		:	'zongb',
  					id			:	'zongb',
  					value		:	'<s:property value="model.zongb" escape="false"/>',
  					allowBlank	:	false
  				},
  				{
  					xtype	:	'datefield',
  					fieldLabel	:	'出版日期',
  					name	:	'cbtime',
  					id		:	'cbtime',
  					format	:	'Y-m-d',
  					value	:	new Date().format('Y-m-d'),
  					allowBlank	:	false
  				},
  				{
  					fieldLabel	:	'E-mail',
  					name		:	'email',
  					id			:	'email',
  					value		:	'<s:property value="model.email" escape="false"/>',
  					allowBlank	:	false,
  					regex : /[a-zA-Z\d!#%&'*+-/\?\^\(\|~][\w\d!#%&'*+-\./\?\^\(\|~]+@[a-zA-Z\d-]+(\.[a-zA-Z\d-]{2,})+/,
  					regexText : '输入的邮箱地址不正确！' 
  				},
  				{
  					fieldLabel	:	'版数',
  					name		:	'lay_sum',
  					id			:	'lay_sum',
  					regex : /^[0-9]*[1-9][0-9]*$/,
  					regexText : '只能是数字',
  					value		:	'<s:property value="model.lay_sum" escape="false"/>',
  					allowBlank	:	false
  				}
  			];
  			function dateFormat(value){ 
    			if(null != value){ 
        			return Ext.Date.format(new Date(value),'Y-m-d H:i:s'); 
    			}else{ 
        			return null; 
    			} 
			} 
			
  			var fp = new Ext.form.FormPanel({
  				standardSubmit: true, 
  				url:'newPaper',
  				method:'POST',
    			width		:	500,
    			height		:	300,
    			id			:	'newForm',
    			title		:	'输入新报基本信息',
    			frame		:	true,
    			bodyStyle	:	'padding: 6px',
    			labelWidth	:	100,
    			defaultType	:	'textfield',
    			defaults	:	{
    				msgTarget	:	'side',
    				anchor	:	'-20'
    			},
    			items	:	[
    				fpItems
    			],
    			buttons	:	[
    				{
    					text	:	'下一步',
    					handler	:	submit
    				},
    				{
    					text	:	'重置',
    					handler	:	reset
    				}
    			],
 
				bbar	:	[
					'TIPS:<s:property value="tip" escape="false"/>'
				]
    			
    		});
    		frm = Ext.getCmp('newForm');
    		function submit() {
    			
    			frm.getForm().submit({
    				waitMsg:'正在提交中...',
    				waitTitle:'提示'
    			});
    		};
    		function reset() {
    			frm.getForm().reset();
    		};
    		var myWin = new Ext.Window({
  				width	:	510,
  				closable	:	false,
  				resizable	:	false,
  				title	:	' 绵阳师范学院校报网络版管理系统——添加新报纸',
  				items	:	[
  					fp
  				]
  			
  			});
  			myWin.show();
  		});
  		
  	</script>
  </body>
</html>
