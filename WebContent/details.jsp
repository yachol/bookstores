<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书详情</title>
<%
	String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<base href="<%=path%>">
</head>
<body>
	<!--导航部分  begin-->
	<%@ include file="indexnav.jsp"%>
	<!--导航部分  end-->
	<!--外层div-->
	<div class="container">
		<!--左边-->
		<div class="col-md-8 col-sm-12">
			<ol class="breadcrumb">
				<li><a href="javascript:;">图书详情</a></li>
				<li id="bookname"></li>
			</ol>

			<div class="row">
				<div class="col-sm-12 col-md-6">
					<div class="thumbnail">
						<a id="bimg"></a>
						<div class="caption" id='info'></div>
					</div>
				</div>

				<div class="col-sm-12 col-md-6">
					<div class="thumbnail">
						<ul class="list-group" id="info2">

						</ul>
					</div>
				</div>
			</div>
			<!--<hr>-->
			<!--折叠部分 begin-->
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#accordion"
							href="#collapseTwo"> 展开详细信息 </a>
					</h4>
				</div>
				<div id="collapseTwo" class="panel-collapse collapse">
					<div class="panel-body">
						<!--tab选项卡 begin-->
						<ul id="myTab" class="nav nav-tabs">
							<li class="active"><a href="#home" data-toggle="tab">
									商品详情 </a></li>
							<li><a href="#ios" data-toggle="tab"> <span
									class="badge pull-right">50</span>累计评价
							</a></li>
						</ul>
						<div id="myTabContent" class="tab-content">
							<div class="tab-pane fade in active" id="home">
								<p>
									<em>产品参数：</em>
								</p>
								<div class="row">
									<div class="col-sm-12 col-md-6">
										<ul class="nav navbar-collapse" style="line-height: 3;"
											id="details">

										</ul>
									</div>
									<span class="visible-sm visible-xs"></span>

								</div>
							</div>
							<div class="tab-pane fade" id="ios">
								<ul class="list-group">
									<li class="list-group-item"><span class="text-info">小明:</span><span
										class="text-success ">这真的是一本好书<img
											src="images/emotions/1.gif "></span></li>
									<li class="list-group-item"><span class="text-info">success:</span><span
										class="text-danger ">书的质量很差<img
											src="images/emotions/2.gif "></span></li>
									<li class="list-group-item"><span class="text-info">叶老师:</span><span
										class="text-success ">很值得初学者学习<img
											src="images/emotions/13.gif "></span></li>
									<li class="list-group-item"><span class="text-info">amdin:</span><span
										class="text-danger ">被坑大发了.<img
											src="images/emotions/17.gif "></span></li>
									<li class="list-group-item"><span class="text-info">rose:</span><span
										class="text-danger ">卖家态度恶劣.<img
											src="images/emotions/11.gif "></span></li>
								</ul>
								<ul class="pager">
									<li><a href="# ">&larr;上一页</a> <a href="# ">下一页 &rarr;</a>
									</li>
								</ul>
							</div>
						</div>
						<!--tab选项卡 end-->
					</div>
				</div>
			</div>
			<!--折叠部分end-->
		</div>

		<!--右边-->
		<div class="col-md-4 col-sm-3 col-xs-8">
			<ol class="breadcrumb " id="cartcount">

				<c:if test="${empty sessionScope.user}">
					<li><a href="javascript:;" class="text-success "><span
							class="glyphicon glyphicon-shopping-cart "></span>我的购物车</a></li>
					<li id="showcount"><a>未登录</a></li>
					<li id="showprice">
						<a href="javascript:;">总价0元</a>
					</li>
				</c:if>
			</ol>
			<!--猜您喜欢-->
			<div>
				<span class="text-info"><span
					class="glyphicon glyphicon-heart"></span><span
					style="font-size: 20px;">猜您喜欢</span></span>
				<div class="row" id="love">
					<div class="col-sm-12 col-md-12 wow fadeInRight animated">
						<div class="thumbnail">
							<a href="details.html"><img
								src="images/xiaodaolifencunzhijian.jpg " style="height: 200px;"
								alt="通用的占位符缩略图 "></a>
							<div class="caption ">
								<h3>小道理：分寸之间</h3>
							</div>
						</div>
					</div>
					<div class="col-sm-12 col-md-12 wow fadeInRight animated">
						<div class="thumbnail ">
							<a href="details.html"><img src="images/NO SECRETS.jpg "
								style="height: 200px;" alt="通用的占位符缩略图 "></a>
							<div class="caption ">
								<h3>名创优品没有秘密</h3>
							</div>
						</div>
					</div>
					<div class="col-sm-12 col-md-12 wow fadeInRight animated">
						<div class="thumbnail ">
							<a href="details.html"><img
								src="images/maichanpinjiushimaiziji.jpg" style="height: 200px;"
								alt="通用的占位符缩略图 "></a>
							<div class="caption ">
								<h3>卖产品就是卖自己</h3>
							</div>
						</div>
					</div>
					<div>
						<ul class="pager ">
							<li><a href="# " onclick="pageUp() ">&larr;上一页</a> <a
								href="# " onclick="pageDown() ">下一页 &rarr;</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="js/details.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
		$(function() {
			var bookid = window.location.search.substring(4);
			var bookid_reg = /^\d+$/;
			if (bookid_reg.test(bookid)) {
				bookdetails(bookid);
			}
			if("${sessionScope.user.id}"!=""){
				showcount();
			}
		})
		function showcount(){
			$.ajax({
				url:"cart/showcount.do",//请求的路由地址
				type:"get",//请求的方式
				async:true,//异步请求控制开关，默认为true
				data:{},
				dataType:"json",//接受服务器响应的类型,text,html.json，jsonp（跨域访问）
				success:function(data){//成功获取响应
					var count='<li><a href="cart.jsp" class="text-success "><span'
							+'class="glyphicon glyphicon-shopping-cart "></span>我的购物车</a></li>'
					+'<li ><a>'+data.numberTotal+'件商品</a></li>'
					+'<li ><a href="javascript:;">总价'+data.bpriceTotal+'元</a></li>';
					$('#cartcount').html(count);
						},
				error:function(){
					//1.url匹配不到路由404
					//2.服务器端的任何异常都会进入error
					//3.服务器返回类型和期待类型不一样
				
					alert("右边小购物车展示异常");
				}
}); 
		}
		function addcart(bid, bimg, bname, bprice) {
			$.post("cart/addcart.do", {
				"bid" : bid,
				"uid" : "${sessionScope.user.id}",
				"bnumber" : $('#bnumber').val(),
				"bimg" : bimg,
				"bname" : bname,
				"bprice" : bprice

			}, function(data) {
				if (data == "请登录") {
					if (confirm("未登录，请登录?")) {
						$('#login').modal('show');
						return;
					}
				} else if (data == "true") {
					alert("加入购物车成功");
					showcount();
				} else {
					alert("加入购物车失败");
				}

			})
		}
		function bookdetails(bookid) {
			$
					.ajax({
						url : "book/details.do",//请求的路由地址
						type : "post",//请求的方式
						async : true,//异步请求控制开关，默认为true
						data : {
							"bookid" : bookid
						},
						dataType : "json",//接受服务器响应的类型,text,html.json，jsonp（跨域访问）
						success : function(resp) {//成功获取响应
							if (resp == undefined) {
								$('#bookname').html(
										'<a href="javascript:;">不存在的书id</a>')
								return;
							}
							//s书名渲染
							$('#bookname').html(
									'<a href="javascript:;">' + resp.bname
											+ '</a>')
							//
							$('#bimg')
									.html(
											'<img src="images/'+resp.img+'" style="height: 200px;" alt="通用的占位符缩略图">')
							//info
							var info = "";
							info += '	<h3>'
									+ resp.bname
									+ '</h3><p>双十一特价,包邮哟!亲'
									+ '<img src="images/3.gif" style="width: 24px;height: 24px;"></p>'
									+ '<div style="margin-bottom: 5px;width: 100px;">'
									+ '<input id="bnumber" type="number" class="form-control" placeholder="购买数量" value="1" min="1" max="'+resp.number+'" onclick=>'
									+ '<span>库存数量'+resp.number+'</span></div><div style="clear: both;">'
									+ '<a href="confirm_order.jsp" class="btn btn-default" role="button">'
									+ '<span class="glyphicon glyphicon-usd"></span> 立即购买'
									+ '</a><a href="javascript:;"onclick=addcart('
									+ resp.id
									+ ',\''+ resp.img+ '\',\''+ resp.bname+ '\','
									+ resp.currentPrice
									+ ') class="btn btn-default" role="button">'
									+ '<span class="glyphicon glyphicon-shopping-cart"></span> 加入购物车</a></div>';
							$('#info').html(info);
							var info2 = '<li class="list-group-item"><span class="text-success">图书名称:'
									+ resp.bname
									+ '</span></li>'
									+ '<li class="list-group-item"><span class="text-info">作者:</span>'
									+ resp.author
									+ '</li>'
									+ '<li class="list-group-item"><span class="text-info">出版日期:'
									+ resp.publishDate
									+ '</span></li>'
									+ '<li class="list-group-item"><span class="text-info">出版社:</span>'
									+ resp.publisher
									+ '</li>'
									+ '<li class="list-group-item"><span class="text-info" style="text-decoration: line-through;">原价:</span><span style="text-decoration: line-through;">$'
									+ resp.originalPrice
									+ '元</span></li>'
									+ '<li class="list-group-item"><span class="text-info">现价:</span>$'
									+ resp.currentPrice
									+ '元</li>'
									+ '<li class="list-group-item"><span class="text-info">说明:</span>'
									+ resp.info + '</li>';
							$('#info2').html(info2);
							var details = '<li class="text-info">书名:'
									+ resp.bname + '</li>'
									+ '<li class="text-info">是否是套装: '
									+ resp.istao + '</li>'
									+ '<li class="text-info">定价:'
									+ resp.currentPrice + '元</li>'
									+ '<li class="text-info">出版社名称: '
									+ resp.publisher + '</li>'

									+ '<li class="text-info">出版时间: '
									+ resp.publishDate + '</li>'
									+ '<li class="text-info">作者:' + resp.author
									+ ' </li>' + '<li class="text-info">作者地区: '
									+ resp.country + '</li>'
									+ '<li class="text-info">ISBN编号: '
									+ resp.isbn + '</li>'
							$('#details').html(details);
						},
						error : function() {

							alert("详情异常");
						}
					});
		}
	</script>
</body>
</html>