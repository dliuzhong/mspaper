<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
	alert("修改成功！请刷新我的稿件！");

	var browserName = navigator.appName;
	if (browserName == "Netscape") {
		window.opener = null;
		window.open('', '_self', '');
		window.close();
	} else {
		if (browserName == "Microsoft Internet Explorer") {
			window.opener = "whocares";
			window.opener = null;
			window.open('', '_top');
			window.close();
		}
	}
</script>
