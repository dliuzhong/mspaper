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
	<script type="text/javascript" src="${pageContext.request.contextPath}/decorators/admin/js/changeGridpanel.js"></script>
	
	<!--  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/decorators/admin/css/manager.css" /> -->
	<style type="text/css">
.back {
	background: url(${pageContext.request.contextPath}/img/ad/back_icon.png)
		no-repeat !important;
}

.close {
	background:
		url(${pageContext.request.contextPath}/img/ad/close_icon.png)
		no-repeat !important;
}

.add {
	background: url(${pageContext.request.contextPath}/img/ad/add_icon.png)
		no-repeat !important;
}

.delete {
	background:
		url(${pageContext.request.contextPath}/img/ad/delete_icon.png)
		no-repeat !important;
}

.refresh {
	background:
		url(${pageContext.request.contextPath}/img/ad/refresh_icon.png)
		no-repeat !important;
}

.edit {
	background: url(${pageContext.request.contextPath}/img/ad/edit_icon.png)
		no-repeat !important;
}

.home {
	background: url(${pageContext.request.contextPath}/img/ad/home_icon.png) no-repeat !important;
}

.export {
	background: url(${pageContext.request.contextPath}/img/ad/export_icon.png) no-repeat !important;
}
.ok {
	background: url(${pageContext.request.contextPath}/img/ad/ok_icon.png) no-repeat !important;
}
.stop {
	background: url(${pageContext.request.contextPath}/img/ad/stop_icon.png) no-repeat !important;
}
.adduser {
	background:url(${pageContext.request.contextPath}/img/ad/adduser_icon.png) no-repeat !important;
}
.check {
	background:url(${pageContext.request.contextPath}/img/ad/check_icon.png) no-repeat !important;
}
.publish {
	background:url(${pageContext.request.contextPath}/img/ad/publish_icon.png) no-repeat !important;
}
.pic_cut {
	background:url(${pageContext.request.contextPath}/img/ad/pic_cut.png) no-repeat !important;
}
.x-selectable,.x-selectable * {
	-moz-user-select: text ! important;
	-khtml-user-select: text ! important;
}

.body {
	background: "#DAE7f6";
}
</style>
    <decorator:head />
  </head>
  
  <body>
    <decorator:body />
  </body>
</html>
