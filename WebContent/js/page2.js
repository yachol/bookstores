/**
 * 
 */

$(function() {
	showtype();
	$('.type').each(function(index, element) {
		showbook(1, $(this).attr("id"));
	})
	$('.cai').each(function(index, element) {
		littletype($(this).attr("id"));
	})

	$('#caidan>li').each(function(idx, element) {
		$(element).hover(function() {
			$('.hi').eq(idx).show();

		}, function() {
			$('.hi').eq(idx).hide();
		})
	})
	$('.hi').hover(function() {
		$(this).show();

	}, function() {
		$(this).hide();
	})
})
function littletype(bigtypeid) {
	$.ajax({
		url : "type/showCaidan.do",// 请求的路由地址
		type : "get",// 请求的方式
		async : false,// 异步请求控制开关，默认为true
		data : {
			"bigtypeid" : bigtypeid
		},
		dataType : "json",
		success : function(resp) {// 成功获取响应
			var hi="";
			hi+='<ul class="hi">'
			for (var i = 0; i < resp.length; i++) {
				 hi+='<li><a href="books_list.jsp=?type='+ resp[i].type+'">'+resp[i].type+'</a></li>';
			}
			hi+='</ul>';
			alert(hi);
			$('#gcarouse').prepend(hi);
		},
		error : function() {
			alert("异常");
		}

	})
}
function showbook(currentPage, bigtypeid) {
	$
			.ajax({
				url : "book/showBook.do",// 请求的路由地址
				type : "post",// 请求的方式
				async : false,// 异步请求控制开关，默认为true
				data : {
					"bigtypeid" : bigtypeid,
					"currentPage" : currentPage

				},
				dataType : "json",// 接受服务器响应的类型,text,html.json，jsonp（跨域访问）
				success : function(inedxBookModel) {// 成功获取响应
					// 渲染书的展示
					var booklist = inedxBookModel.list
					var books = "";
					if (booklist == "") {
						books += "<h1>您查询的商品不存在</h1>";
						// alert($('#'+bigtypeid+'').text());
						$('#' + bigtypeid + '').html(books);
						return;
					}
					for (var i = 0; i < booklist.length; i++) {
						if (i % 4 == 0 || i % 4 == 1) {
							books += '<div class="col-sm-6 col-md-3 wow fadeInLeft animated">';
						}
						if (i % 4 == 2 || i % 4 == 3) {
							books += '<div class="col-sm-6 col-md-3 wow fadeInRight animated">';
						}
						books += '<div class="thumbnail" style="height:320px">'
								+ '<a href="details.html"><img src="images/'
								+ booklist[i].img + '" alt="通用的占位符缩略图"></a>'
								+ '<div class="caption"><h3>'
								+ booklist[i].bname + '</h3>' + '<p>价格:'
								+ booklist[i].currentPrice + '元&nbsp;&nbsp;作者:'
								+ booklist[i].author + '&nbsp;&nbsp;</p>'
								+ '<p><a href="details.html">更多信息</a>'
								+ '</p></div></div></div>'
					}
					// 页面
					books += '<div class="container"><ul class="pager">'
					var current = inedxBookModel.currentPage;
					var totalPage = inedxBookModel.totalPage
					if (current == 1) {
						books += '<li class="disabled"><a href="javascript:;">&larr;上一页</a></li>'
					} else if (current != 1) {
						books += '<li><a href="javascript:showbook('
								+ (current - 1) + ',\'' + bigtypeid
								+ '\')">&larr;上一页</a></li>'
					}
					if (current == totalPage) {
						books += '<li class="disabled"><a href="javascript:;">下一页 &rarr;</a></li>'
								+ '</ul></div>'
					} else if (current != totalPage) {
						books += '<li><a href="javascript:showbook('
								+ (current + 1) + ',\'' + bigtypeid
								+ '\')">下一页 &rarr;</a></li>' + '</ul></div>'
					}
					$('#' + bigtypeid + '').html(books);
				},

				error : function() {
					// 1.url匹配不到路由404
					// 2.服务器端的任何异常都会进入error
					// 3.服务器返回类型和期待类型不一样
					alert("分页异常");
				}
			});
}
function showtype() {
	$.ajax({
		url : "type/bigtype.do",// 请求的路由地址
		type : "get",// 请求的方式
		async : false,// 异步请求控制开关，默认为true
		data : {},
		dataType : "json",// 接受服务器响应的类型,text,html.json，jsonp（跨域访问）
		success : function(resp) {// 成功获取响应
			var bigtype = "";
			var caidan = "";
			for (var i = 0; i < resp.length; i++) {
				bigtype += '<div><div class="text-primary">'
						+ '<img src="images/bullet1.gif" alt="" title="">'
						+ '<a href="#">' + resp[i].type + '</a></div>'
						+ '<div class="row type" id="i' + resp[i].id
						+ '"></div></div>'
				caidan += '<li class="cai"id="c' + resp[i].id
						+ '"><a href="books_list.jsp=?type=' + resp[i].type
						+ '">' + resp[i].type + '</a></li>'

			}
			bigtype += "<hr>";
			$('#indexbook').html(bigtype);
			$('#caidan').html(caidan);
		},
		error : function() {
			// 1.url匹配不到路由404
			// 2.服务器端的任何异常都会进入error
			// 3.服务器返回类型和期待类型不一样

			alert("异常");
		}
	});
}
