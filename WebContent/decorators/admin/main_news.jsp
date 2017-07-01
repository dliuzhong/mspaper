<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator" %>

<!DOCTYPE HTML>
<html>
  <head>
    
    <title><decorator:title default="admin_main装饰器" /></title>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
     
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/decorators/admin/ext/resources/css/ext-all.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/decorators/admin/ext/adapter/ext/ext-base-debug.js" >
	</script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/decorators/admin/ext/ext-all-debug.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/decorators/admin/ext/src/locale/ext-lang-zh_CN.js"></script>
	<script type="text/javascript">
		Ext.BLANK_IMAGE_URL = '${pageContext.request.contextPath}/decorators/admin/ext/resources/images/default/s.gif';
	</script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/decorators/admin/ckeditor/ckeditor.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/decorators/admin/ckeditor/config.js" charset="utf-8"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/decorators/admin/js/Ext.form.CKEditor.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/decorators/admin/js/Ext.form.BasicForm.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/decorators/admin/js/draw.js"></script>
	<link href="${pageContext.request.contextPath}/decorators/admin/css/edit.css" type="text/css" rel="stylesheet">
	
    <decorator:head />
  </head>
  
  <body>
    <decorator:body />
  </body>
</html>
