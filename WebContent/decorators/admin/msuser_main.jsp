<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator" %>
<!DOCTYPE HTML>
<html>
  <head>
    
    <title><decorator:title default="admin_login装饰器" /></title>
    <!--  <link href="${pageContext.request.contextPath}/decorators/admin/css/manager.css" type="text/css" rel="stylesheet" /> -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/decorators/admin/js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/decorators/admin/js/jquery.validate.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/decorators/admin/js/msuserjs.js" charset="gb2312"></script>
	<script charset="utf-8" src="${pageContext.request.contextPath}/decorators/admin/js/kindeditor/kindeditor.js"></script>
	<script charset="utf-8" src="${pageContext.request.contextPath}/decorators/admin/js/kindeditor/lang/zh_CN.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/decorators/admin/javascript/bootstrap.js"></script>
	
	<link href="${pageContext.request.contextPath}/decorators/admin/css/bootstrap.css" type="text/css" rel="stylesheet" />
	<link href="${pageContext.request.contextPath}/decorators/admin/css/bootstrap-responsive.css" type="text/css" rel="stylesheet" />
	<link href="${pageContext.request.contextPath}/decorators/admin/css/mymsuser.css" type="text/css" rel="stylesheet" />
	<decorator:head />
  </head>
  
  <body>
	
	<decorator:body />
	
</body>
</html>
