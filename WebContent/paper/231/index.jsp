<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
     
        <link href="${pageContext.request.contextPath}/paper/css/main.css" type="text/css" rel="stylesheet">
        <script type="text/javascript" src="${pageContext.request.contextPath}/paper/js/jquery-1.10.2.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/paper/js/main.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/paper/js/backToTop.js"></script>
        <title>报刊首页</title>
    </head>
    <body>
       
        <s:action name="mstop" executeResult="true" namespace="/mspaper">
       		<s:param name="id">129</s:param>
       	</s:action>
        <div class="indexTop">
        主办：XXX报社集团&nbsp;出版：XXX出版社&nbsp;电子邮箱：XXX@163.com<br>
            <span class="num">第231期(本期共3版）</span>&nbsp;出版日期：2015年05月04日&nbsp;总编：杨明荣

            <div id="lay_sum" style="display: none">3</div>
        </div>
        <div id="container" class="container">
            <div class="leftDiv">
        	    <iframe src="e1/page.html" class="frameLeft" name="page" id="page"  frameborder="no" scrolling="no"></iframe>
            </div>
   	        <div class="buttonPrev" id="buttonPrev"><a><img src="../img/prev_off.gif" alt="prev" name="prev" width="35" border="0" id="prev"></a></div><div class="buttonNext" id="buttonNext"><a><img src="../img/next_off.gif" alt="next" name="next" width="35" border="0" id="next"></a></div>
			&nbsp;&nbsp;&nbsp;
			<div class="search">
    			<jsp:include page="../search.jsp"></jsp:include><br>
   		 	 </div>
   	        <iframe src="e1/main.html" class="frameRight" height="560"  name="main" id="main"  frameborder="no" scrolling="no"  onload="iFrameHeight(this.id, this.name)"></iframe>
          	<br>
          </div>
        <jsp:include page="../bottom.jsp"></jsp:include>
    </body>
</html>
