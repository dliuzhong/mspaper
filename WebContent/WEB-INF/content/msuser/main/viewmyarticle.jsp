<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
  <head>
    
    <title>${model.title}-查看</title>
   

  </head>
  
  <body>
    <div class="viewDiv">
    		<center><h3>${model.title}</h3></center>
    		<center><small>投稿人：${model.msuser.name }&nbsp;&nbsp;
    			        稿件类型：${model.type.name }</small></center>
    	 	<p>关键字：${model.keyword }</p>
    	 	<hr>
    	 	${model.content}
    	 	<br>
    	 	<hr>
    	 	<p>稿件状态：${model.status.name }</p>
    	 	<p>投稿时间：<s:date name="model.time" format="yyyy-MM-dd hh:mm:ss" /></p>
    		<br>
    		<br>
    		<center><small onclick="closeWin()" style="cursor: pointer;">[关闭页面]</small></center>
    	
    </div>
  </body>
</html>
