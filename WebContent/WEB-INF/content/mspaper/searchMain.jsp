<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%> 
<!DOCTYPE html>
<html>
  <head>
    
    <title>XXX报社电子报刊信息搜索</title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
	
	<link href="../paper/css/main.css" type="text/css" rel="stylesheet"/>
	
  </head>
  
  <body>
	<s:action name="searchtop" executeResult="true" namespace="/mspaper" />
	<div class="searchDiv">
		<form action="../mspaper/doSearch" method="get">

			<div class="search">
				新闻搜索： <input type="text" name="words" id="searchText" size="25" value="<s:property value="words" escape="false"/>">
				<input type="submit" name="searchSubmit" id="searchSubmit"
					style="background: url('../paper/img/search.png') no-repeat;width: 24px;height: 24px;border: none;vertical-align: bottom;" value=""> 
					<input type="radio" name="select" value="word"<s:if test="select==null"> checked="checked"</s:if><s:if test='select=="word"'> checked="checked"</s:if>>关键字 
					<input type="radio" name="select" value="title"<s:if test='select=="title"'> checked="checked"</s:if>>标题
					<input type="radio" name="select" value="author"<s:if test='select=="author"'> checked="checked"</s:if>>作者 
					<input type="radio" name="select" value="subject"<s:if test='select=="subject"'> checked="checked"</s:if>>专题

			</div>
		</form>

	</div>
	<br>
	<div class="resultDiv" id="resultDiv">
		<s:if test="allsum>0">
		
		<div class='pageMainDiv'>
		
			第
			<s:property value="start/limit + 1" />
			页
			<s:if test="start>0">
				<a href="doSearch?select=<s:property value="select" escape="false"/>&words=<s:property value="words" escape="false"/>&start=${start-limit}">上一页</a>&nbsp;</s:if>
			<s:if test="start+limit < allsum">
				<a href="doSearch?select=<s:property value="select" escape="false"/>&words=<s:property value="words" escape="false"/>&start=${start+limit}">下一页</a>
			</s:if>
			（共找到
			<s:property value="allsum" />
			条新闻&nbsp;共
			<s:if test="allsum%limit > 0">
				<s:property value="allsum/limit + 1" />
			</s:if>
			<s:else>
				<s:property value="allsum/limit" />
			</s:else>
			页）
			<br>
	
		</div>
		</s:if>
		<s:iterator value="newsl" status="st">
			<b><a href="../paper/<s:property value="paper.paper" escape="false"/>/e<s:property value="layout.layout_no" escape="false"/>/<s:property value="file_path" escape="false"/>" target="_blank">
				<s:if test="%{title.indexOf(words)!=-1}">
					<s:property value="%{title.substring(0, title.indexOf(words))}" escape="false"/><font style="color:red;"><s:property value="%{words}" escape="false"/></font><s:property value="%{title.substring(title.indexOf(words)+words.length(), title.length())}" escape="false"/>
				</s:if>
				<s:else>
					<s:property value="title" escape="false"/>
				</s:else></a>
			</b>&nbsp;
			<small><i>
			(
			第<s:property value="paper.paper" escape="false"/>期&nbsp;
			第<s:property value="layout.layout_no" escape="false"/>版
			&nbsp;作者：
				<s:if test="%{author.indexOf(words)!=-1}">
					<s:property value="%{author.substring(0, author.indexOf(words))}" escape="false"/><font style="color:red;"><s:property value="%{words}" escape="false"/></font><s:property value="%{author.substring(author.indexOf(words)+words.length(),author.length())}" escape="false"/>
				</s:if>
				<s:else>
					<s:property value="author" escape="false"/>
				</s:else>
			&nbsp;
			<s:if test="subject.id>1">
				专题：
				<s:if test="%{subject.name.indexOf(words)!=-1}">
					<s:property value="%{subject.name.substring(0, subject.name.indexOf(words))}" escape="false"/><font style="color:red;"><s:property value="%{words}" escape="false"/></font><s:property value="%{subject.name.substring(subject.name.indexOf(words)+words.length(), subject.name.length())}" escape="false"/>
				</s:if>
				<s:else>
					<s:property value="subject.name" escape="false"/>
				</s:else>
			</s:if>)
			
			</i></small>
			<s:if test="%{null!=other&&content.length()>0}"><br><br><img ${other} style="width: 100px;height: 80px;" /></s:if>
			
			<h5>
			<s:if test="%{content.indexOf(words)!=-1}">
				<s:if test='select=="word"'>
					<s:if test="%{content.indexOf(words)>160}">
						...<s:property value="%{content.substring(content.indexOf(words)-80, content.indexOf(words))}" escape="false"/><font style="color: red"><s:property value="%{words}" escape="false"/></font><s:if test="%{content.substring(content.indexOf(words)+words.length(), content.length()).length()>80}"><s:property value="%{content.substring(content.indexOf(words)+words.length(), content.indexOf(words)+words.length()+80)}" escape="false"/>... </s:if><s:else><s:property value="%{content.substring(content.indexOf(words)+words.length(), content.length())}" escape="false"/>... </s:else></s:if><s:else><s:property value="%{content.substring(0, content.indexOf(words))}" escape="false"/><font style="color: red"><s:property value="%{words}" escape="false"/></font><s:if test="%{content.substring(content.indexOf(words)+words.length(), content.length()).length()>80}"><s:property value="%{content.substring(content.indexOf(words)+words.length(), content.indexOf(words)+words.length()+80)}" escape="false"/>... </s:if><s:else><s:property value="%{content.substring(content.indexOf(words)+words.length(), content.length())}" escape="false"/>...</s:else></s:else>
				</s:if>
				<s:else>
					<s:property value="%{content.substring(0, 160)}" escape="false"/>...
				</s:else>
			</s:if>
				
			<s:else>
				<s:if test="%{null!=content&&content.length()>160}">
					<s:property value="%{content.substring(0, 160)}" escape="false"/>...
				</s:if>
				<s:else>
				
					<s:property value="%{content.substring(0, content.length())}" escape="false"/>...
				</s:else>
			</s:else></h5>
			<s:if test="!#st.last">
				<hr style="height:3px;border:none;border-top:1px dashed #0066CC;">
			</s:if>
		</s:iterator>
		<s:if test="allsum>0">
		<div class='pageMainDiv'>
		
			第
			<s:property value="start/limit + 1" />
			页&nbsp;
			<s:if test="start>0">
				<a href="doSearch?select=<s:property value="select" escape="false"/>&words=<s:property value="words" escape="false"/>&start=${start-limit}">上一页</a>&nbsp;</s:if>
			<s:if test="start+limit < allsum">
				<a href="doSearch?select=<s:property value="select" escape="false"/>&words=<s:property value="words" escape="false"/>&start=${start+limit}">下一页</a>
			</s:if>
			（共找到
			<s:property value="allsum" />
			条新闻&nbsp;共
			<s:if test="allsum%limit > 0">
				<s:property value="allsum/limit + 1" />
			</s:if>
			<s:else>
				<s:property value="allsum/limit" />
			</s:else>
			页）
			<br><br>
			<div class='pageDiv'>
			<form action="doSearch" method="get" id="toForm">
				<input type="hidden" name="select" value="<s:property value="select" escape="false"/>" />
				<input type="hidden" name="words" value="<s:property value="words" escape="false"/>" />
				到：<input type="text" style="width:40px" name="page" id="page" value="<s:property value="start/limit + 1" />"/>
				<input type="submit" value="跳转" />
			</form>

			</div>
	
		</div>
		</s:if>
		<s:else>
		
		<small>未能为您搜索到相关新闻。</small>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		</s:else>
	</div>
	
	<br>
	
	<div class="bottomDiv">
    <p class="bottomfont">国内统一刊号：CN51-3100<br><br>
    &copy;2015&nbsp;MDL. All Rights Reserved. </p>
 </div>
	<br>
</body>
</html>
