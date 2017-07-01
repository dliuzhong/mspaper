$(document).ready(function(){
	
	$("#dataForm").validate({
		rules:{
			
			
			name	:	{
				required:true,
				minlength:1,
				maxlength:25
			},
			
			email	:	{
				required:true,
				email	:	true
			},
			telephone	:	{
				number:true
			},
			qq	:	{
				number:true
			}
		},
		messages :	{
			
			name	:	"必填!",
			email	:	'email填写正确！'
		},
		onfocusout:true
	});
	$("#changepwForm").validate({
		rules:{
			
			
			password	:	{
				required:true,
				minlength:4,
				maxlength:25
			},
			
			newpw	:	{
				required	:true,
				minlength	:4,
				maxlength	:25
			},
			renewpw	:	{
				required	:true,
				equalTo		:	"#newpw"
			}
		},
		messages :	{
			
			password	:	"必填!",
			newpw	:	'必填!',
			renewpw	:	'两次输入的新密码不一致！'
		},
		onfocusout:true
	});
	$("#articleForm").validate({
		rules:{
			
			
			title	:	{
				required:true
			},
			
			keyword	:	{
				required	:true
			},
			typeid	:	{
				required	:true
			},
			editor	:	{
				required	:true
			}
			
		},
		messages :	{
			
			title	:	"必填!",
			keyword	:	'必填!',
			editor	:	'必填!'
			
		},
		onfocusout:true
	});
	$("#changeArticleForm").validate({
		rules:{
			
			
			title	:	{
				required:true
			},
			
			keyword	:	{
				required	:true
			},
			typeid	:	{
				required	:true
			},
			editor	:	{
				required	:true
			}
		},
		messages :	{
			
			title	:	"必填!",
			keyword	:	'必填!',
			editor	:	'必填!'
		},
		onfocusout:true
	});
	
});

// ajax 验证email是否可用
function validateEmail(ele) {
	var msid = $("#id").val();
	if ($.trim(ele.value).length > 1) {
		$.post('ajax/checkEmail', {email:ele.value, id:msid}
			, function(data) {
				$("#tip").html(data.tip);
				if ($.trim(data.tip).length > 1) {
					// 把submit禁用
					$(":submit:visible").attr("disabled","disabled");
				} else {
					// 开起submit
					$(":submit:visible").removeAttr("disabled");
				}
			});
	}
}
function closeWin() {
	var browserName = navigator.appName;
	if (browserName=="Netscape") {
		window.opener = null;
		window.open('', '_self', '');
		window.close();
	} else {
			if (browserName == "Microsoft Internet Explorer"){
			window.opener = "whocares";
			window.opener = null;
				window.open('', '_top');
			window.close();
		}
	}
}

function showAddArticle() {
	if ($("div.addArticleDiv").css("display")=="none") {
		$("div.addArticleDiv").slideDown('slow');
	} else {
		$("div.addArticleDiv").slideUp(500);
	}
}