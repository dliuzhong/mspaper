<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator" %>
<!DOCTYPE HTML>
<html>
  <head>
    
    <title><decorator:title default="admin_login装饰器" /></title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<link href="${pageContext.request.contextPath}/paper/css/main.css"
		type="text/css" rel="stylesheet">
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/paper/js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/paper/js/main.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/paper/js/backToTop.js" charset="gb2312"></script>

<decorator:head />
</head>
  
  <body>
	
	<decorator:body />
	
</body>
</html>
