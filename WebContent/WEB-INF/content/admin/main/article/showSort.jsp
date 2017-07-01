<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>
<html>
  <head>
    
    
    <title>统计</title>
    <link href="${pageContext.request.contextPath}/decorators/admin/css/jquery-ui-1.10.4.custom.css" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath}/decorators/admin/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/decorators/admin/js/jquery-ui-1.10.4.custom.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/decorators/admin/js/jquery.ui.datepicker-zh-CN.js"></script>
    
    <style type="text/css">
    	body {
    		background:#DAE7f6;
    	}
    	table {
    		border:1px #E6CAFF;
    		cellspacing:0;
    		cellpadding:10;
    		color:#000079;
    		font-size: 15px;
    	}
    	tr {
    		height: 30px;
    		
    	}
    	td {
    		padding: 5px 5px 5px 5px;
    	}
    	.sortDiv {
    		font-size: 13px;
    		
    		height: 20px;
    	}
    	#sortLabel {
    	 	<s:if test='sortMain=="type"'>
    		 display: none;
    		 </s:if>
    	}
    	#departLabel1 {
    		<s:if test='sort == "all" || sort == "out" || depart1==0'>
    			display: none;
    		</s:if>
    	}
    	#departLabel2 {
    		<s:if test='sort=="all" || sort == "in" || depart2==0'>
    		display: none;
    		</s:if>
    		
    	}
    	.errorDiv {
    		padding: 40px 10px 10px 10px;
    	}
    </style>

  </head>
  
  <body>
  <script type="text/javascript">
  	$(document).ready(
		function() {
			$('input#date1').datepicker({
				minDate	: new Date(1990, 0, 1),
				maxDate	: new Date(2100, 11, 31)
			});
			$('input#date2').datepicker({
				minDate	: new Date(1990, 0, 1),
				maxDate	: new Date(2100, 11, 31)
			});
		}
	);
	// seletion 跳转
	function showSort() {
		var mainSort = document.getElementById("mainSort");
		var mainSortValue = mainSort.options[mainSort.selectedIndex].value;
		if (mainSortValue == 'depart' || mainSortValue == 'msuser') {
			
			$("label#sortLabel").css('display', 'inline');
			
			$("label#departLabel1").css('display', 'none');
			$("label#departLabel2").css('display', 'none');
			if (mainSortValue == 'msuser') {
				var sortLabel = document.getElementById("sort");
				var sortValue = sortLabel.options[sortLabel.selectedIndex].value;
				if (sortValue == 'in') {
					$("label#departLabel1").css('display', 'inline');
					$("label#departLabel2").css('display', 'none');
				} else if(sortValue == 'out') {
					$("label#departLabel2").css('display', 'inline');
					$("label#departLabel1").css('display', 'none');
				} else {
					$("label#departLabel1").css('display', 'none');
					$("label#departLabel2").css('display', 'none');
				}
			}
		} else {
		
			$("label#sortLabel").css('display', 'none');
			$("label#departLabel1").css('display', 'none');
			$("label#departLabel2").css('display', 'none');
		}
		
		
	}
	function showDepart() {
		var mainSort = document.getElementById("mainSort");
		var mainSortValue = mainSort.options[mainSort.selectedIndex].value;
		var sortLabel = document.getElementById("sort");
		var sortValue = sortLabel.options[sortLabel.selectedIndex].value;
		if (mainSortValue == 'msuser') {
			if (sortValue == 'in') {
				$("label#departLabel1").css('display', 'inline');
				$("label#departLabel2").css('display', 'none');
			} else if(sortValue == 'out') {
				$("label#departLabel2").css('display', 'inline');
				$("label#departLabel1").css('display', 'none');
			} else {
				$("label#departLabel1").css('display', 'none');
				$("label#departLabel2").css('display', 'none');
			}
		}
		
		
		
	}
	
	
</script>
  <br>
  <div class="sortDiv">
  <center>
  	<form action="" method="get" id="sortForm">
  		统计方式：
  		<select id="mainSort" onchange="showSort()" name="sortMain">
  			<option value="depart" <s:if test='sortMain=="depart"'>selected</s:if><s:if test="sortMain==null">selected</s:if>>部门</option>
  			<option value="msuser"  <s:if test='sortMain=="msuser"'>selected</s:if>>来稿用户</option>
  			<option value="type"  <s:if test='sortMain=="type"'>selected</s:if>>稿件类型</option>
  		</select>
  		<label id="sortLabel" onchange="showDepart()">
  			
  			<select id="sort" name="sort">
  				<option value="all" <s:if test='sort=="all"'>selected</s:if><s:if test="sort==null">selected</s:if>>所有</option>
  				<option value="in" <s:if test='sort=="in"'>selected</s:if>>内部</option>
  				<option value="out" <s:if test='sort=="out"'>selected</s:if>>外部</option>
  			</select>
  		</label>
  		<label id="departLabel1">
  			部门：
  			<select id="depart" name="depart1">
  			
  				<s:iterator value="dl1" status="st1">
  					<option value="<s:property value="id" />" <s:if test="depart1==id">selected</s:if>><s:property value="name" escape="false" /></option>
  				
  				</s:iterator>
  				
  			</select>
  		</label>
  		<label id="departLabel2">
  			部门：
  			<select id="depart" name="depart2">
  			
  				<s:iterator value="dl2" status="st2">
  					<option value="<s:property value="id" />"<s:if test="depart2==id">selected</s:if>><s:property value="name" escape="false" /></option>
  				
  				</s:iterator>
  			
  			</select>
  		</label>
  		|范围：
  		<input type="radio" name="contain" value="all" <s:if test='contain=="all"'>checked</s:if><s:if test="contain==null">checked</s:if> />所有稿件
  		<input type="radio" name="contain" value="pass"  <s:if test='contain=="pass"'>checked</s:if> />通过稿件
  		<input type="radio" name="contain" value="publish"  <s:if test='contain=="publish"'>checked</s:if> />发表稿件
  		|日期：<input type="text" id="date1" name="date1" size="10" value='<s:date name="date1" format="yyyy-MM-dd" />'/>
  		到
  		<input type="text" id="date2" name="date2" size="10" value='<s:date name="date2" format="yyyy-MM-dd" />'/>
  		|<input type="submit" value="提交"/>
  	</form>
  	
  	
  	
  </center>
  </div>
  <s:if test="allArticle>0">
  <center><h3>${data}<br>总数量为：<s:property value="allArticle"/>件
  
  &nbsp;&nbsp;<a href="${pageContext.request.contextPath}/admin/main/article/exportSort?sortMain=${sortMain}&sort=${sort}&depart1=<s:property value="depart1" />&depart2=<s:property value="depart2" />&contain=${contain}&date1=<s:date name="date1" format="yyyy-MM-dd" />&date2=<s:date name="date2" format="yyyy-MM-dd" />" >[导出为EXCEL]</a></h3>
 <label style="cursor: pointer;font-size:12px;" onclick="location.reload();">[刷新]</label>
  </center>
  	<br>
  	<s:bean var="comparator" name="cn.mnu.paper.comparator.MyComparator"/>
  	<s:sort source="sblist" comparator="#comparator" var="sortedList"/>
  	
  	<table align="center">
  		<tr bgcolor="#FFBB77" align="center">
  			<td width="300">名称</td>
  			<td width="400">比例</td>
  			<td width="150">数量</td>
  		</tr>
  		<s:iterator status="st" value="#attr.sortedList">
  		<tr bgcolor="#E6CAFF" align="center">
  			<td><s:property value="name" escape="false"/></td>
  			<td align="left"><div style="width:<s:property value="(all * 100 / allArticle) *400 / 100"/>px; height: 20px;background-color: #0072E3;"></div></td>
  			<td><s:property value="all"/>件</td>
  		</tr>
    	</s:iterator>
    </table>
   </s:if>
   <s:else>
   	<div class="errorDiv">
   		<center><small>${data}</small></center>
   	</div>
   </s:else>
  </body>
</html>
