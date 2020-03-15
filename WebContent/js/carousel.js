//轮播
$(function() {
	// 每过2秒钟进行一次轮播
	$('#myCarousel').carousel({
		interval : 2000
	});

	$.ajax({
		url : "book/carousel.do",// 请求的路由地址
		type : "post",// 请求的方式
		async : true,// 异步请求控制开关，默认为true
		data : {"hhh":1},
		dataType : "json",// 接受服务器响应的类型,text,html.json，jsonp（跨域访问）
		success : function(resp) {// 成功获取响应
			
			var carousel = "";
			for (var i = 0; i < resp.length; i++) {
				if(i==0){
					carousel +='<div class="item active"><a href="details.html"><img src="images/'+resp[i].img+'" class="pull-left" alt="First slide"></a></div>';
				}else{
					carousel +='<div class="item"><a href="details.html"><img src="images/'+resp[i].img+'" class="pull-left" alt="First slide"></a></div>';
				}
			
			}
			$('.carousel-inner').eq(0).html(carousel);
		},
		error : function() {
			alert("轮播图异常");
		}
	});
	
});