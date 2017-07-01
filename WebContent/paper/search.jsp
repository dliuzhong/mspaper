<%@ page language="java"  pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>  
<form action="${pageContext.request.contextPath}/mspaper/doSearch" method="get" target="_blank">

<div class="search">
新闻搜索：
<input type="text" name="words" id="searchText" size="25">
<input type="submit" name="searchSubmit" id="searchSubmit" style="background: url('../img/search.png') no-repeat;width: 24px;height: 24px;border: none;vertical-align: bottom;" value="">

<input type="radio" name="select" value="word" checked="checked">关键字
<input type="radio" name="select" value="title">标题
<input type="radio" name="select" value="author">作者
<input type="radio" name="select" value="subject">专题

</div>
</form>