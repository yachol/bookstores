<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
<%
	String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<base href="<%=path%>">
<link rel="stylesheet" type="text/css" href="css/cart.css" />

</head>
<body>
	<!--导航部分  begin-->
	<%@ include file="indexnav.jsp"%>
	<!--导航部分  end-->
	<!--购物车 begin-->
	<!--外层div-->
	<div class="container">
		<!--左边-->
		<div class="col-md-8 col-sm-12">
			<!--左边-->
			<ol class="breadcrumb">
				<li><a href="#" class="text-success"><span
						class="glyphicon glyphicon-heart"></span>&nbsp;&nbsp;我的宝贝</a></li>
			</ol>

			<!--购物车表格 begin-->
			<div class="table-responsive" id="imgDiv">
					<form action="cart/confirmorder.do" method="post">
				<table class="table table-hover table-striped"
					style="vertical-align: middle;">
					<thead>
						<tr class="text-success success">
							<th><input type="checkbox" id="selectAll"></th>
							<th id="sequence">序号</th>
							<th>图片</th>
							<th>书名</th>
							<th>单价</th>
							<th>数量</th>
							<th>合计</th>
						</tr>
					</thead>
					
					<tbody id="tby">
					</tbody>
					<tfoot>
						<tr>
							<td>已选中<span id="choice">0</span>条
							</td>
							<td colspan="4"></td>
							<td class="text-success">总价:</td>
							<td class="text-success" id="jiesuanjiage"><span>0</span>元</td>
						</tr>
						<tr style="background-color: white;">
							<td><a href="books_list.jsp" class="btn btn-info">&lt;&lt;继续购买</a>
							</td>
							<td><input type="button" class="btn btn-danger" data-toggle="modal"
									data-target="#myModal" onclick="del()"value="删除商品"/></td>
							<td colspan="4"></td>
							
							<td><input data-toggle="modal"
									data-target="#confirm_order" type="button" class="btn btn-warning" onclick=confirmorder() value="结算商品"/>
							</td>
						</tr>
					</tfoot>
				</table>
					</form>
			</div>
			<!--购物车 end-->
			<!-- 结算模态框 -->
			<div class="modal fade" id="confirm_order" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
			<div id="jiesuan_del" style="display: none;">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
								<h4 class="modal-title text-warning" id="myModalLabel">抱歉!请您先选择商品!</h4>
							</div>
							<div class="modal-body">请选进行勾选!再执行结算!</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">关闭</button>
							</div>
						</div>
						<!-- /.modal-content -->
					</div>
				</div>
			</div>
			<!-- 结束 结算-->
			<!--删除提示模态框 begin-->
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<!--如果没有选中任何一个商品的时候,出现-->
				<div id="first_del" style="display: none;">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
								<h4 class="modal-title text-warning" id="myModalLabel">抱歉!请您先选择商品!</h4>
							</div>
							<div class="modal-body">请选进行勾选!再执行删除删除!</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">关闭</button>
							</div>
						</div>
						<!-- /.modal-content -->
					</div>
				</div>
				<!--first_del  end-->
				
				<!--如果没有选中了一个商品的时候,出现-->
				<div id="two_del" style="display: none;">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
								<h4 class="modal-title text-warning" id="myModalLabel">删除商品,慎重操作!</h4>
							</div>
							<div class="modal-body">此操作一旦进行,数据将不可恢复!</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">关闭</button>
								<button type="button" class="btn btn-danger" id="mydel">确定删除</button>
							</div>
						</div>
						<!-- /.modal-content -->
					</div>
				</div>
				<!--two_del  end-->
			</div>
			<!--删除提示模态框 end-->
		</div>
		<!--右边-->
		<div class="col-md-4 col-sm-3 col-xs-8">
			<ol class="breadcrumb " id="cartcount">
				<c:if test="${empty sessionScope.user}">
					<li><a href="javascript:;" class="text-success "><span
							class="glyphicon glyphicon-shopping-cart "></span>我的购物车</a></li>
					<li id="showcount"><a>未登录</a></li>
					<li id="showprice"><a href="javascript:;">总价0元</a></li>
				</c:if>
			</ol>
			<!--猜您喜欢-->
			<div>
				<span class="text-info"><span
					class="glyphicon glyphicon-heart"></span><span
					style="font-size: 20px;">&nbsp;&nbsp;猜您喜欢</span></span>
				<div class="row" id="love">
					<div class="col-sm-12 col-md-12 wow fadeInRight animated">
						<div class="thumbnail">
							<a href="details.html"><img src="images/cikehuakai.jpg "
								style="height: 200px;" alt="通用的占位符缩略图 "></a>
							<div class="caption ">
								<h3>此刻花开</h3>
							</div>
						</div>
					</div>
					<div class="col-sm-12 col-md-12 wow fadeInRight animated">
						<div class="thumbnail ">
							<a href="details.html"><img src="images/jindumanbu.jpg "
								style="height: 200px;" alt="通用的占位符缩略图 "></a>
							<div class="caption ">
								<h3>京都漫步</h3>
							</div>
						</div>
					</div>
					<div class="col-sm-12 col-md-12 wow fadeInRight animated">
						<div class="thumbnail ">
							<a href="details.html"><img src="images/lapulasidemonv.jpg"
								style="height: 200px;" alt="通用的占位符缩略图 "></a>
							<div class="caption ">
								<h3>拉普拉斯的魔女</h3>
							</div>
						</div>
					</div>
					<div>
						<ul class="pager ">
							<li><a href="#" onclick="pageUp() ">&larr;上一页</a> <a
								href="#" onclick="pageDown() ">下一页 &rarr;</a></li>
						</ul>
					</div>
				</div>
			</div>
			<!--喜欢end-->
		</div>
	</div>

	<div class="container">
		<!--首页底部信息 begin-->
		<div class="container ">
			<div class="row ">
				<div class="col-md-offset-2 col-md-8">
					<div class="row ">
						<div class="col-md-offset-2 col-md-1"></div>
						<div class="col-md-6">
							<div class="row">
								<div class="col-md-offset-2 col-md-10">
									<a href="http://www.sztzedu.com">蜗牛创想科技有限公司<sup>&copy;</sup>所有
									</a>
								</div>
							</div>
							<div class="gap"></div>
							<div class="row">
								<div class="col-md-offset-2 col-md-10">
									<span class="text-info">蜗牛学院上海中心<sup>&reg;</sup> |&nbsp;
									</span> <span class="text-info">图书管理平台 |&nbsp;</span> <span
										class="text-info">2019</span>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--end-->
<script type="text/javascript">
//被选中的个数
var choice = 0;
// 总个数
var count = 0;
$(function() {
	if ("${sessionScope.user.id}" != "") {
		showcount();
		showcart("${sessionScope.user.id}");
		count = $('input[name="cks"]').length;
		changeNum()
	}
})
// 全选按钮
$('#selectAll').click(function all() {
	var checked = $(this).prop('checked');
	$('input[name="cks"]').prop("checked", checked);
	// 被选中的数量
	if (checked) {
		choice = count;
	} else {
		choice = 0;
	}
	// .更新被选中的商品的个数
	$('#choice').text(choice);
	calculateTotal();
})
// 每个商品的选中都需要1.更新总价 2.更新被选中的商品的个数
function each(obj) {
	//
	if ($(obj).prop('checked')) {
		choice++;
	} else {
		choice--;
	}
	// 更新全选按钮的checked状态
	if (choice == count) {
		$('#selectAll').prop("checked", true);
	} else {
		$('#selectAll').prop("checked", false);
	}
	$('#choice').text(choice);
	calculateTotal();
}
// 价格计算
function calculateTotal() {
	var sum = 0;
	$('input[name="cks"]').each(
			function(index, element) {
				if ($(element).prop('checked')) {
					// 小计的价格加起来就是总价
					var xiaoji = $(element).parent().next().next().next()
							.next().next().next().children().eq(0).text();
					sum += parseInt(xiaoji);
				}
			})
	$('#jiesuanjiage>span').text(sum);

}
// 更新数据库的number
function changenumber(id, bnumber) {
	// 更新数据库的number
	$.post("cart/changbnumber.do", {
		id : id,
		bnumber : bnumber
	}, function(data) {
		if(data=="数量有误"){
			alert(data);
		}
		
	})
}
function showcount() {
	$
			.ajax({
				url : "cart/showcount.do",// 请求的路由地址
				type : "get",// 请求的方式
				async : true,// 异步请求控制开关，默认为true
				data : {
					"uid" : "${sessionScope.user.id}"
				},
				dataType : "json",// 接受服务器响应的类型,text,html.json，jsonp（跨域访问）
				success : function(data) {// 成功获取响应
					var counts = '<li><a href="cart.jsp" class="text-success "><span'
							+ 'class="glyphicon glyphicon-shopping-cart "></span>我的购物车</a></li>'
							+ '<li ><a>'
							+ data.numberTotal
							+ '件商品</a></li>'
							+ '<li ><a href="javascript:;">总价'
							+ data.bpriceTotal + '元</a></li>';
					$('#cartcount').html(counts);
				},
				error : function() {
					alert("右边小购物车展示异常");
				}
			});
}
function showcart(uid) {
	$
			.ajax({
				url : "cart/showcart.do",// 请求的路由地址
				type : "post",// 请求的方式
				async : false,// 异步请求控制开关，默认为true
				data : {
					"uid" : "${sessionScope.user.id}"
				},
				dataType : "json",// 接受服务器响应的类型,text,html.json，jsonp（跨域访问）
				success : function(data) {// 成功获取响应
					var cart = '';
					for (var i = 0; i < data.length; i++) {
						cart += '<tr><td><input value="'+data[i].id+'"type="checkbox" name="cks" onclick=each(this)></td>'
								+ '<td>'
								+ data[i].id
								+ '</td>'
								+ '<td><a href="details.jsp?id='+data[i].bid+'"><img src="images/'
								+ data[i].bimg
								+ '"></a></td>'
								+ '<td>'
								+ data[i].bname
								+ '</td>'
								+ '<td><span>'
								+ data[i].bprice
								+ '</span>元</td>'
								+ '<td id="bid'+data[i].bid+'">'
								+ data[i].bnumber
								+ '</td>'
								+ '<td><span>'
								+ data[i].bnumber
								* data[i].bprice
								+ '</span>元</td></tr>'
					}
					$('#tby').html(cart);

				},
				error : function() {
					alert("购物车展示异常");
				}
			});
}
// 修改数量
function changeNum() {
	var trs = document.getElementById("tby").getElementsByTagName("tr");
	for (var i = 0; i < trs.length; i++) {
		var td = trs[i].getElementsByTagName("td")[5];
		td.onclick = function() {
			var reg = /^[0-9]+$/;
			var oldValue = this.innerHTML;
			var to = this;

			if (!reg.test(oldValue)) {
				return;
			}
			this.innerHTML = "<div class='input-group' style='width: 105px;'>"
					+ "<span class='input-group-btn'>"
					+ "<button class='btn btn-default' type='button'>-</button>"
					+ "</span><input type='text' class='form-control shenqi' value='"
					+ oldValue
					+ "'><span class='input-group-btn'>"
					+ "<button class='btn btn-default' type='button'>+</button>"
					+ "</span>" + "</div>";

			this.onclick = "";

			var inp = this.getElementsByTagName("input")[0];
			var bt = this.getElementsByTagName("button")[0];
			var btn = this.getElementsByTagName("button")[1];

			inp.onclick = function(event) {
				var e = window.evnet || event;
				if (window.event) {
					e.cancelBubble = true;
				} else {
					// e.preventDefault();
					// //在基于firefox内核的浏览器中支持做法stopPropagation
					e.stopPropagation();
				}
				this.select();
			}

			bt.onclick = function(event) {
				var e = window.evnet || event;
				if (window.event) {
					e.cancelBubble = true;
				} else {
					// e.preventDefault();
					// //在基于firefox内核的浏览器中支持做法stopPropagation
					e.stopPropagation();
				}
				
				// 更新数量
				inp.value = parseInt(inp.value.trim()) - 1 <= 1 ? 1
						: parseInt(inp.value.trim()) - 1;
				// 计算小计
				var xiaoji = inp.value.trim()
						* $(this).parent().parent().parent().prev().children()
								.eq(0).text();
				$(this).parent().parent().parent().next().children().eq(0)
						.text(xiaoji);
				var checked = $(this).parent().parent().parent().prev().prev()
						.prev().prev().prev().children().eq(0).prop('checked');
				if (checked){
					calculateTotal();
				}
				changenumber($(this).parent().parent().parent().prev().prev()
						.prev().prev().text(), inp.value);
			}
			btn.onclick = function(event) {
				var e = window.evnet || event;
				if (window.event) {
					e.cancelBubble = true;
				} else {
					// e.preventDefault();
					// //在基于firefox内核的浏览器中支持做法stopPropagation
					e.stopPropagation();
				}
				//
				 Bignumber=querycount($(this).parent().parent().parent().attr("id").substring(3));
				Bignumber=parseInt(Bignumber);
				//alert(Bignumber)
				// 更新数量
				inp.value = parseInt(inp.value.trim()) + 1>Bignumber?Bignumber:parseInt(inp.value.trim()) + 1;
				if(parseInt(inp.value.trim())>=Bignumber){
					alert("书籍数量已达最大值")
					
				} 
				/* inp.value = parseInt(inp.value.trim()) + 1; */
				// 计算小计
				var xiaoji = inp.value.trim()
						* $(this).parent().parent().parent().prev().children()
								.eq(0).text();
				$(this).parent().parent().parent().next().children().eq(0)
						.text(xiaoji);
				var checked = $(this).parent().parent().parent().prev().prev()
						.prev().prev().prev().children().eq(0).prop('checked');
				if (checked) {
					calculateTotal();
				}
			changenumber($(this).parent().parent().parent().prev().prev()
						.prev().prev().text(), inp.value); 
			}

			// inp.select();
			inp.onblur = function() {
				alert("onblur1")
				var n = this.value.trim();
				if (!reg.test(n)) {
					n = oldValue;
				}
				 alert("onblur");
				Bignumber=querycount($(to).attr("id").substring(3));
				if(n>Bignumber){
					alert("书库存是"+Bignumber+"本");
					n=Bignumber;
				}  
				to.innerHTML = n;
				alert(456)//这里卡死了
				to.onclick = changeNum;
				alert(789)
				 changenumber($(to).prev().prev().prev().prev(), $(this).val()); 
				var xiaoji = $(this).val()
						* $(to).prev().children().eq(0).text();
				$(to).next().children().eq(0).text(xiaoji);
			}
			inp.onkeydown = function(event) {
				var e = window.event || event;
				if (e.keyCode == 13) {
					var n = this.value.trim();
					if (!reg.test(n)) {
						n = oldValue;
					}
					to.innerHTML = n;
					to.onclick = changeNum;
				}
			 	changenumber($(to).prev().prev().prev().prev(), $(this).val()); 
				var xiaoji = $(this).val()
						* $(to).prev().children().eq(0).text();
				$(to).next().children().eq(0).text(xiaoji);
			}
		}
	}
}
// 删除按钮
function del() {
	// 获取first_del
	var fd = document.getElementById("first_del");
	var td = document.getElementById("two_del");
	td.style.display = "none";
	fd.style.display = "none";
	// 判断是否选择了商品
	var count = 0;
	// 获取所有的checkbox
	var cks = document.getElementsByName("cks");
	// 遍历
	for (var i = 0; i < cks.length; i++) {
		if (cks[i].checked) {
			count++;
		}
	}
	if (count == 0) {
		fd.style.display = "block";
	} else {
		td.style.display = "block";
	}
}

// 删除任意行
$('#mydel').click(function() {

	var cks = document.getElementsByName("cks");
	var tby = document.getElementById("tby");
	var array = new Array();
	var j = 0;
	for (var i = cks.length - 1; i >= 0; i--) {
		if (cks[i].checked) {
			array[j++] = $(cks[i]).parent().next().text();
			// 删除
			tby.removeChild(cks[i].parentNode.parentNode);
		}
	}
	this.previousElementSibling.click();
	$.post("cart/delcart.do", {
		"array" : array
	}, function(data) {
		//alert(data)
		if (data == "false") {
			alert("删除失败，请重试");
		} else {
			// alert("删除成功，请重试");
		}
	})
	// 调用关闭按钮的单击事件

})
//
function querycount(bid){
	var Bignumber;
	$.ajax({
		url:"book/querycount.do",//请求的路由地址
		type:"post",//请求的方式
		async:false,//异步请求控制开关，默认为true
		data:{"bid":bid},
		dataType:"text",//接受服务器响应的类型,text,html.json，jsonp（跨域访问）
		success:function(data){//成功获取响应
			Bignumber=data;
				},
		error:function(){
			alert("异常");
		}
}); 
	return Bignumber;
}
function confirmorder(){
	var cks= $('input[name="cks"]')
	var count=0;
	for(var i=0;i<cks.length;i++){
		if(cks.eq(i).prop('checked'))
			count++;
	}
	if(count>0)
		$('form').submit();
	else
		$('#jiesuan_del').css("display","block");
}
</script>
	<!--引入js文件-->


	
</body>
</html>