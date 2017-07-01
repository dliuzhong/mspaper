$(function() {
	var $backToTopTxt = "TOP", $backToTopEle = $(
			'<div class="backToTop"></div>').appendTo($("#container")).text(
			$backToTopTxt).attr("title", $backToTopTxt).click(function() {
		$("html, body").animate({
			scrollTop : 0
		}, 120);
	}), $backToTopFun = function() {
		var st = $(document).scrollTop(), winh = $(window).height();
		(st > 500) ? $backToTopEle.show() : $backToTopEle.hide();
		// IE6
		if (!window.XMLHttpRequest) {
			$backToTopEle.css("top", st + winh - 166);
		}
	};
	$(window).bind("scroll", $backToTopFun);
	$(function() {
		$backToTopFun();
	});
});