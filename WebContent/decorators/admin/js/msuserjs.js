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
			
			name	:	"����!",
			email	:	'email��д��ȷ��'
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
			
			password	:	"����!",
			newpw	:	'����!',
			renewpw	:	'��������������벻һ�£�'
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
			
			title	:	"����!",
			keyword	:	'����!',
			editor	:	'����!'
			
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
			
			title	:	"����!",
			keyword	:	'����!',
			editor	:	'����!'
		},
		onfocusout:true
	});
	
});

// ajax ��֤email�Ƿ����
function validateEmail(ele) {
	var msid = $("#id").val();
	if ($.trim(ele.value).length > 1) {
		$.post('ajax/checkEmail', {email:ele.value, id:msid}
			, function(data) {
				$("#tip").html(data.tip);
				if ($.trim(data.tip).length > 1) {
					// ��submit����
					$(":submit:visible").attr("disabled","disabled");
				} else {
					// ����submit
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