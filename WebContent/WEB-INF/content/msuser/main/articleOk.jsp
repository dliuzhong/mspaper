<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
alert("${data}");
location.replace("${pageContext.request.contextPath}/msuser/main/myArticle?myid=${msuid}");
</script>
<p><a href="${pageContext.request.contextPath}/msuser/main/myArticle?myid=${msuid}">返回</a></p>
