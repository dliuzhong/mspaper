<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
  <head>
    
    <title>${model.title }-修改</title>

  </head>
  
  <body>
    <script>
  	  	
        KindEditor.ready(function(K) {
        	
                window.editor = K.create('#editor',
                {
            	 uploadJson : 'ajax2/uploadImage'
         		});
        });
        
	</script>
	<div class="changeArticleDiv">
	<center><h4>稿件内容修改</h4></center>
	<hr>
	<s:form action="changeMyArticle" method="POST" id="changeArticleForm">
		<s:hidden name="msuid" id="msuid" value="%{#request.msuid}"></s:hidden>
		<s:hidden name="id" id="id" value="%{#request.model.id}"></s:hidden>
		<s:textfield style="width: 600px" name="title" id="title" label="标题" value="%{#request.model.title}"></s:textfield>
		<s:textfield style="width: 600px" name="keyword" id="keyword" label="关键字" value="%{#request.model.keyword}"></s:textfield>
		<s:select list="atList" listValue="name" listKey="id" name="typeid"
			id="typeid" label="稿件类型"></s:select>
		<s:textarea label="稿件内容" id="editor" name="content"
			style="width:700px;height:400px;" value="%{#request.model.content}">

		</s:textarea>
		<s:submit style="width: 60px" value="提交" id="submit"></s:submit>
		<s:reset style="width: 60px" value="重置"></s:reset>
	</s:form>
	<br>
	<center><small onclick="closeWin()" style="cursor: pointer;">[关闭页面]</small></center>
	<br>
	</div>
</body>
</html>
