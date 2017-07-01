<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator" %>
<!DOCTYPE HTML>
<html>
  <head>
    
    <title><decorator:title default="admin_login装饰器" /></title>
    <link href="${pageContext.request.contextPath}/decorators/admin/css/manager.css" type="text/css" rel="stylesheet" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/decorators/admin/js/admin_login.js"></script>
	<decorator:head />
  </head>
  
  <body>
    <decorator:body />
  </body>
</html>
