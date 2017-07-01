<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <link href="../paper/css/comment.css" type="text/css" rel="stylesheet"/>
    <title>新闻评论</title>
  

  </head>
  
  <body onload="parent.reload();">
    <div class="commentEditDiv">
   		
   		<form action="addComment" method="post">
   			<input type="hidden" name="newsid" id="newsid" value="<s:property value="newsid" />" />
   			
   			
   			<table>
   				<tr>
   					<td width="50"><label>昵称：</label>
   					</td>
   					<td><input type="text" name="name" id="id" size="25" />&nbsp;&nbsp;
   						
   					</td>
   					<td width="170" align="right"><label style="font-size:11px;">评论后，通过审核后方可显示</label>
   					</td>
   				</tr>
   				<tr>
   					<td width="50"><label>内容：</label>
   					</td>
   					<td colspan="2"><textarea name="comment" id="comment"></textarea>
   					</td>
   				</tr>
   				<tr>
   					<td></td>
   					<td><input type="submit" name="submit" id="submit" value="提交" />&nbsp;
   						<input type="reset" name="reset" id="reset" value="重置" />
   						</td>
   				</tr>
   			</table>
   		</form>
   		
   	</div>
   	<ul>
   	
   	<s:iterator value="comments" status="st">
   		<s:if test="pass>0">
   		<li>
   		<hr>
   		<span style="font-size:11px;color: #333333;">昵称：<s:property value="name" />
   			<s:property value="ip" />&nbsp;
   			<s:date name="time" format="yyyy/MM/dd hh:mm:ss" /></span><br>
   			<span style="display: block;padding-top: 5px;"><s:property value="comment" /></span>
   			
   		</li>
   		</s:if>
   	</s:iterator>
   	
   	</ul>
  </body>
</html>
