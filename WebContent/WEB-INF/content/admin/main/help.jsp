<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
  <head>
  	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>使用说明</title>
    <style type="text/css">
    .container {
    	width: 1000px;
    	margin: 0 auto;
    	margin-top:0px;
    	margin-left:auto;
    	margin-right:auto;
    	padding: 20px 20px 20px 20px;
		font-weight: 5px;
		font-size: 16px;
		line-height:30px;
	}
    .notice {
    	color: red;
    	font-size: 14px;
    }
    footer {
    	font-size: 14px;
    	text-align: right;
    }
    hr {
    	height:3px;
    	border:none;
    	border-top:1px dashed #0066CC;
    }
    p {
    	font-size:14px;
    }
    ul li {
    	font-size:14px;
    }
    a {
    	font-size: 13px;
    }
    header a {
    	font-size:16px;
    }
    </style>
   
  	
  </head>
  
  <body>
  	<s:set name="no" value="0" />
    <div class="container"  id="c">
    	<header>
    		<center><p style="font-size: 25px;color: #0061AE;">XXX报社电子报刊管理系统帮助说明</p></center>
    		<hr>
    		<h2>
    			目录>>
    		</h2>
    		<h3>
    			<ol>
    				<li><a href="#<s:set name="no" value="#no+1" /><s:property value="#no"/>"><s:property value="#no"/>.系统及管理员说明</a></li>
    				<li><a href="#<s:set name="no" value="#no+1" /><s:property value="#no"/>"><s:property value="#no"/>.添加新报纸</a></li>
    				<s:if test="#session.adminGrade==2 || #session.adminGrade==3">
    				<li><a href="#<s:set name="no" value="#no+1" /><s:property value="#no"/>"><s:property value="#no"/>.管理旧报纸</a></li>
    				</s:if>
    				<li><a href="#<s:set name="no" value="#no+1" /><s:property value="#no"/>"><s:property value="#no"/>.数据搜索</a></li>
    				<s:if test="#session.adminGrade==2 || #session.adminGrade==3">
    				<li><a href="#<s:set name="no" value="#no+1" /><s:property value="#no"/>"><s:property value="#no"/>.投稿管理</a></li>
    				</s:if>
    				<s:if test="#session.adminGrade==3">
    				<li><a href="#<s:set name="no" value="#no+1" /><s:property value="#no"/>"><s:property value="#no"/>.系统管理</a></li>
    				</s:if>
    				<li><a href="#<s:set name="no" value="#no+1" /><s:property value="#no"/>"><s:property value="#no"/>.密码修改</a></li>
    				<li><a href="#<s:set name="no" value="#no+1" /><s:property value="#no"/>"><s:property value="#no"/>.注销</a></li>
    				<li><a href="#<s:set name="no" value="#no+1" /><s:property value="#no"/>"><s:property value="#no"/>.其它</a></li>
    			</ol>
    		
    		</h3>
    	</header>
    	<br>
    	<hr>
    	<br>
    	<s:set name="no" value="0" />
    	<article>
    		<h3 id="<s:set name="no" value="#no+1" /><s:property value="#no"/>"><s:property value="#no"/>. 系统及管理员说明&nbsp;<a href="#c">返回目录</a></h3>
    		<ul>
    			<li>本系统为XXX报社电子报刊管理系统，目前版本为0.1。</li>
    			
    			<p>管理员分三个等级：低级管理员（等级：1）、中级管理员（等级：2）和高级管理员（等级：3）。</p>
    			<ul>
    				<s:if test="#session.adminGrade==1">
    				<li>您为低级管理员，可操作：添加新报纸、数据搜索、密码修改、帮助、注销。</li>
    				</s:if>
    				<s:if test="#session.adminGrade==2">
    				<li>您为中级管理员可操作：添加新报纸、管理旧报纸、数据搜索、稿件管理、密码修改、帮助、注销。</li>
    				</s:if>
    				<s:if test="#session.adminGrade==3">
    				<li>您为高级管理员可操作：添加新报纸、管理旧报纸、数据搜索、稿件管理、系统管理、密码修改、帮助、注销。</li>
    				</s:if>
    			</ul>
    			
    		</ul>
    	</article>
    	<br>
    	<article>
    		<h3 id="<s:set name="no" value="#no+1" /><s:property value="#no"/>"><s:property value="#no"/>. 添加新报纸&nbsp;<a href="#c">返回目录</a></h3>
    		<p>本功能通过步骤引导方式。</p>
    		<ul>
    			<li><i>第一步：输入新报基本信息</i></li>
    			<p>请输入新报的[期号]、[主办]、[出版编辑]、[总编]、[出版日期]、[Email]、[版数]，除[期号]外其它项都会依照系统自动给出，[出版日期]为当前日期。如要修改请重新输入即可。输入点击[下一步]。</p>
    			<p class="notice">注意：在输入期号的时候，不要有中文和符号出现。</p>
    			
    			<li><i>第二步：各版内容编辑</i></li>
    			<P>请认真编辑每版内容。可以在选项卡中选择你要编辑的版面，每一版内容编辑完了，本期才会在首页显示。<br>
    			先填写版面信息，填写成功后才能录入版面新闻内容。录入版面新闻内容时，在每个选项卡的左边为本版的图片，点击[截取]为画出每条新闻所在报纸版面的区域，画好
    			后会自动填充到中间表单相应的字段中，无需手动输入。如果画错，重新画即可。在中间的表单中输入新闻内容，专题可点击下拉选择或输入匹配选择，如果没有专题，输入“无”，选择；如果有，请输入，选择；专题可输入空格，进行选择。新闻添加成功后，会在右边[已添加]中显示，在左边相应画出区域。如果添加错误可点击[删除]。
    			<br>编辑完成，请选择选项卡[完成]，单击[完成]按钮，返回主菜单。一期报纸的新增加完成。</P>
    			<p>在[内容]编辑框中，下图中，点击红圈中那个按钮可开启全屏编辑模式，再点一次则退出全屏编辑模式。</p>
    			<img src="${pageContext.request.contextPath}/img/ad/00.jpg">
    			<p class="notice">注意：录入版面新闻内容时，录入之后不可删除（可在[管理旧报纸]中删除），请确认后再提交！<br>
    			上传的版面图片文件，格式为JPEG，图片大小为400*600像素左右（<a href="#aa" id="a">通过PDF转换为图片的方法</a>）。<br>
    			在填写[标题]字段时，请不要换行（回车）;在填写[内容]字段时，请不要从Word中直接复制粘贴，请使用无格式的复制粘贴（方法：Word中复制->记事本中粘贴->记事本中复制->[内容]中粘贴），在[内容]中插入图片，文件大小不能超过2MB。</p>
    		</ul>
    	</article>
    	<s:if test="#session.adminGrade==2 || #session.adminGrade==3">
    	<br>
    	<article>
    		<h3 id="<s:set name="no" value="#no+1" /><s:property value="#no"/>"><s:property value="#no"/>. 管理旧报纸&nbsp;<a href="#c">返回目录</a></h3>
    		<p>往期报纸：可能对[年份]或[期号]进行检索，也可以[年份]、[期号]组合检索。</p>
    		<p>选择相应的期号进行管理，根据右边框中的内容进行管理，在新闻内容管理时请选择您要修改、删除的新闻。删除版面，只能从最后一版开始删除。添加、修改新闻内容方法同<a href="#2">[2. 添加新报纸]</a>中第三步相似。请在添加、修改或删除后，点击[刷新Page和Main页面]。如果添加修改后没有看到结果，请[刷新]，看是否添加修改成功。</p>
    		<p class="notice">注意：相关注意事项请查看<a href="#2">[2. 添加新报纸]</a>。</p>
    	</article>
    	</s:if>
    	<br>
    	<article>
    		<h3 id="<s:set name="no" value="#no+1" /><s:property value="#no"/>"><s:property value="#no"/>. 数据搜索&nbsp;<a href="#c">返回目录</a></h3>
    		<ul>
    			<li>新闻搜索</li>
    			<p>可能根据关键字进行[关键字]、[标题]、[作者]或[专题]进行搜索.</p>
    			<li>信息检索</li>
    			<p>可能根据[期号]、[专题]、[年份]、[作者]进行组合检索，如果没有一个都没选择，全检索出所有新闻内容。</p>
    			<li>所有搜索、检索的结果都可以点击[导出Excel]导出Excel表。</li>
    		</ul>
    	</article>
    	<s:if test="#session.adminGrade==2 || #session.adminGrade==3">
    	<br>
    	<article>
    		<h3 id="<s:set name="no" value="#no+1" /><s:property value="#no"/>"><s:property value="#no"/>. 投稿管理&nbsp;<a href="#c">返回目录</a></h3>
    		
    		<ul>
    			<li><i>未审稿件</i></li>
    			<p>列表为未被审核的稿件，对列表中的稿件进行选择操作。</p>
    			<p>操作：查看审核、修改重审、通过审核、不予采用。</p>
    			<li><i>修改重审稿件</i></li>
    			<p>列表为修改重审的稿件，对列表中的稿件进行选择操作。</p>
    			<p>操作：查看重审、修改、通过审核、不予采用、取消审核。</p>
    			<li><i>通过审核稿件</i></li>
    			<p>列表为通过审核的稿件，对列表中的稿件进行选择操作。</p>
    			<p>操作：查看重审、修改重审、不予采用、取消审核、Word导出、添加发表信息。</p>
    			<li><i>不予采用稿件</i></li>
    			<p>列表为不予采用的稿件，对列表中的稿件进行选择操作。</p>
    			<p>操作：查看重审、修改重审、不予采用、取消审核。</p>
    			<li><i>所有状态稿件</i></li>
    			<p>列表为所有状态的稿件，对列表中的稿件进行选择操作。</p>
    			<p>操作：查看、删除、Word导出。</p>
    			<li><i>稿件统计</i></li>
    			<p>对所有稿件进行选择统计，统计结果可导出Excel。日期不填入表示不按日期统计。</p>
    		</ul>
    		<p class="notice">注意：<br>
    		1. 投稿状态：等待审核、审核中、修改重审、通过审核、不予采用、已发表。<br>
    		2. 其中“通过审核、取消审核、删除”、可选择多项进行操作。<br>
    		其中“查看审核、查看重审、查看、修改重审、不予采用、Word导出”，只能选择一项进行操作。
    		</p>
    	</article>
    	</s:if>
    	<s:if test="#session.adminGrade==3">
    	<br>
    	<article>
    		<h3 id="<s:set name="no" value="#no+1" /><s:property value="#no"/>"><s:property value="#no"/>. 系统管理&nbsp;<a href="#c">返回目录</a></h3>
    		<ul>
    			<li><i>专题管理</i></li>
    			<p>请选择您要设置活动、取消活动、修改、删除的专题内容。修改时，您只能为一项专题内容进行修改。修改操作成功，为有弹出窗口提示成功，没有则表示失败。</p>
    			<li><i>评论管理</i></li>
    			<p>目前评论功能不开通，为关闭状态。</p>
    			<li><i>管理员管理</i></li>
    			<p>添加管理员，请按要求填写相关字段，必须正确输入您的登录密码，密码有默认密码不可更改，备注可以不填。请选择您要修改或删除的管理员。在修改时，正确输入您的登录密码，[新密码]字段不填时，不修改密码。添加、修改操作成功，有弹出窗口提示成功，没有则表示失败。</p>
    			<li><i>投稿用户管理</i></li>
    			<p>操作同[管理员管理员]相同 。</p>
    			<li><i>投稿部门管理</i></li>
    			<p>选择列表中的记录进行选择操作。</p>
    			<li><i>稿件类型管理</i></li>
    			<p>选择列表中的内容进行选择操作。不可更改内容为“无”的记录。</p>
    		</ul>
    	</article>
    	</s:if>
    	<br>
    	<article>
    		<h3 id="<s:set name="no" value="#no+1" /><s:property value="#no"/>"><s:property value="#no"/>. 密码修改&nbsp;<a href="#c">返回目录</a></h3>
    		<p>输入您的原密码，输入两次您的新密码，原密码必须正确，两次新密码必须相同，否则无法修改密码。</p>
    	</article>
    	<br>
    	<article>
    		<h3 id="<s:set name="no" value="#no+1" /><s:property value="#no"/>"><s:property value="#no"/>. 注销&nbsp;<a href="#c">返回目录</a></h3>
    		<p>管理员登出，返回登录页面。</p>
    	</article>
    	<br>
		<article>
    		<h3 id="<s:set name="no" value="#no+1" /><s:property value="#no"/>"><s:property value="#no"/>. 其它&nbsp;<a href="#c">返回目录</a></h3>
    		<p>无</p>
    	</article>
    	<hr>
    	<footer>
    		
    		<p>版本号：V0.1</p>
    	</footer>
    </div>
   	<br>
   	
    
  </body>
</html>

