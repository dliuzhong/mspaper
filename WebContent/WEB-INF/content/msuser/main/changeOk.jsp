<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
alert("修改成功！");
location.replace("${pageContext.request.contextPath}/msuser/main/viewMsuserData?id=${model.id}");
</script>
<p><a href="${pageContext.request.contextPath}/msuser/main/viewMsuserData?id=${model.id}">返回</a></p>
