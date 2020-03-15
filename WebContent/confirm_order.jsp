<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
<meta charset="UTF-8">
<title>结算</title>
<%
	String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<base href="<%=path%>">
<link type="text/css" rel="stylesheet" href="dist/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="css/index.css" />
		<link rel="stylesheet" type="text/css" href="css/cart.css" />
		<link rel="stylesheet" type="text/css" href="css/animate.css" />
<style type="text/css">
			.form-control{
				width: 100%;
			}
		</style>
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
				<ol class="breadcrumb">
					<li>
						<a href="#" class="text-success"><span class="glyphicon glyphicon-home"></span>&nbsp;&nbsp;送货地址</a>
					</li>
				</ol>
				
				<!--送货地址  begin-->
				<div style="margin-bottom: 5px;" id="addrDiv">
					<ul class="list-group" id="address">
						
					</ul>
					<a href="javascript:;" class="text-info" onclick="dispalyMoreAddr(this)">更多地址↓&nbsp;&nbsp;</a>
					<a href="javascript:;" id="addAddr" class="text-success" data-toggle="modal" data-target="#myModal">添加新地址</a>
				</div>
				<hr>
				
				
				<!--添加新地址模态框 begin-->
				<!-- 模态框（Modal） -->
				<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
								<h4 class="modal-title" id="myModalLabel">
									<span class="glyphicon glyphicon-home"></span>&nbsp;&nbsp;添加新收货地址
								</h4>
							</div>
							<div class="modal-body">
							<!--dizhi 的form表单-->
							<form class="form-horizontal" role="form">
								<div class="form-group">
									<label class="col-md-2 control-label" style="margin-right: 2.9%;">所在地区:</label>
										
									<div data-toggle="distpicker">
										<div class="form-group col-sm-12 col-md-3">
										  <label class="sr-only" for="province1">Province</label>
										  <select class="form-control" id="province1"></select>
										</div>
										<div class="form-group col-sm-12 col-md-3">
										  <label class="sr-only" for="city1">City</label>
										  <select class="form-control" id="city1"></select>
										</div>
										<div class="form-group col-sm-12 col-md-3">
										  <label class="sr-only" for="district1">District</label>
										  <select class="form-control" id="district1"></select>
										</div>
									 </div>
									 <div class="col-sm-1" style="padding-top: 10px;">
									<label class="alert-danger"></label>
								</div>
								</div>
								<div class="form-group has-feedback" style="clear:both">
									<label for="deatails" class="col-sm-2 control-label">详细地址:</label>
									<div class="col-sm-5">
										<textarea class="form-control" id="deatails"></textarea>
									</div>
									<div class="col-sm-3" style="padding-top: 10px;">
										<label class="alert-danger" id=""></label>
									</div>
								</div>
								<div class="form-group has-feedback">
									<label class="col-sm-2 control-label">邮政编码</label>
									<div class="col-md-5">
										<input type="text" class="form-control" id="post_code" placeholder="邮政编码">
										<span class="glyphicon glyphicon-hand-left form-control-feedback"></span>
									</div>
									<div class="col-sm-3" style="padding-top: 10px;">
										<label class="alert-danger" id=""></label>
									</div>
								</div>
	
								<div class="form-group has-feedback">
									<label class="col-sm-2 control-label">姓名</label>
									<div class="col-sm-5">
										<input type="text" class="form-control" id="username" placeholder="收货人姓名">
										<span class="glyphicon glyphicon-user form-control-feedback"></span>
									</div>
									<div class="col-sm-3" style="padding-top: 10px;">
										<label class="alert-danger" id=""></label>
									</div>
								</div>

								<div class="form-group has-feedback">
									<label class="col-sm-2 control-label">电话</label>
									<div class="col-sm-5">
										<input type="tel" class="form-control" id="tel" placeholder="合法手机格式">
										<span class="glyphicon glyphicon-phone form-control-feedback"></span>
									</div>
									<div class="col-sm-3" style="padding-top: 10px;">
										<label class="alert-danger" id=""></label>
									</div>
								</div>
								
								<div class="form-group">
								    <div class="col-sm-offset-2 col-sm-10">
								      <div class="checkbox">
								        <label>
								          <input type="checkbox" id="setDefaultAddr"><span class="text-success">设置默认地址</span>
								        </label>
								      </div>
								    </div>
								</div>

								<div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
									<input type="button" class="btn btn-primary" value="提交地址" id="tijiaoadress">
								</div>
							</form>
						<!--form结束-->
						</div>
					</div>
				<!-- /.modal-content -->
				</div>
			</div>
				<!--左边-->
				<ol class="breadcrumb">
					<li>
						<a href="javascript:;" class="text-success"><span class="glyphicon glyphicon-list-alt"></span>&nbsp;&nbsp;结算清单</a>
					</li>
				</ol>

				<!--购物车表格 begin-->
				<div class="table-responsive" id="imgDiv">
					<form id="addorder"action="order/addorder.do" method="post">
					<table class="table table-hover table-striped" style="vertical-align:middle;">
						<thead>
							<tr class="text-success success">
								<th id="sequence">序号</th>
								<th>图片</th>
								<th>书名</th>
								<th>单价</th>
								<th>数量</th>
								<th>合计</th>
							</tr>
						</thead>
						<tbody id="tby">
						<c:forEach items="${requestScope.list}" var="cart">
								<tr>
								<td>${cart.id }<input type="hidden" name="id" value="${cart.id }" ></td>  
								<td><img src="images/${cart.bimg }"><input type="hidden" name="bimg" value="${cart.bimg }" ></td>
								<td>${cart.bname }<input type="hidden" name="bname" value="${cart.bname }" ></td>
								<td>${cart.bprice }元<input type="hidden" name="bprice" value="${cart.bprice }" ></td>
								<td>${cart.bnumber }<input type="hidden" name="bnumber" value="${cart.bnumber }" ></td>
								<td>${cart.bprice*cart.bnumber }元<input type="hidden" name="bid" value="${cart.bid }" ><input id="oadress" type="hidden" name="orderadress" value="" ></td>
								
							</tr>
						
						</c:forEach>
						
						</tbody>
						<tfoot>
							<tr>
								<td colspan="4"></td>
								<td class="text-success">总价:</td>
								<td class="text-success">${requestScope.totalPraice }元</td>
							</tr>
							<tr style="background-color: white;">
								<td>
									<a href="books_list.jsp" class="btn btn-info">&lt;&lt;继续购买</a>
								</td>
								<td colspan="4"></td>
								<td>
									<input id="oadress" type="hidden" name="orderadress" value="" >
									<input type="button"class="btn btn-warning" onclick=toOrder() value="提交订单"/>
								</td>
							</tr>
						</tfoot>
					</table>
					</form>
				</div>
				<!--购物车 end-->

			</div>

			<!--右边-->
			<div class="col-md-4 col-sm-3 col-xs-8">
				<ol class="breadcrumb">
					<li>
						<a href="#" class="text-success"><span class="glyphicon glyphicon-shopping-cart "></span>我的购物车</a>
					</li>
					<li>
						<a href="# ">3个商品</a>
					</li>
					<li>
						<a href="# ">总价100元</a>
					</li>
				</ol>

			<!--猜您喜欢-->
				<div>
					<span class="text-info"><span class="glyphicon glyphicon-heart"></span><span style="font-size: 20px; ">&nbsp;&nbsp;猜您喜欢</span></span>
					<div class="row" id="love">
						<div class="wow fadeInRight animated">
							<div class="thumbnail">
								<a href="details.html"><img src="images/dongjian.jpg " style="height: 200px; " alt="通用的占位符缩略图 "></a>
								<div class="caption ">
									<h3>洞见</h3>
								</div>
							</div>
						</div>
						<div class="wow fadeInRight animated">
							<div class="thumbnail ">
								<a href="details.html"><img src="images/tenxunchuan.jpg" style="height: 200px; " alt="通用的占位符缩略图 "></a>
								<div class="caption ">
									<h3>腾讯传</h3>
								</div>
							</div>
						</div>
						<div class="wow fadeInRight animated">
							<div class="thumbnail ">
								<a href="details.html"><img src="images/taikong.jpg" style="height: 200px; " alt="通用的占位符缩略图 "></a>
								<div class="caption ">
									<h3>太空</h3>
								</div>
							</div>
						</div>
						<div>
							<ul class="pager">
								<li>
									<a href="#" onclick="pageUp() ">&larr;上一页</a>
									<a href="#" onclick="pageDown() ">下一页 &rarr;</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
				<!--喜欢end-->
			</div>
		</div>

		<script src="dist/js/jquery.min.js"></script>
		<script src="dist/js/bootstrap.min.js"></script>
		<script src="dist/js/distpicker.data.min.js"></script>
		<script src="dist/js/distpicker.min.js"></script>
		<script src="js/details.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/confirm_order.js"></script>
		<!-- <script type="text/javascript">
			if(!(/msie [6|7|8|9]/i.test(navigator.userAgent))) {
				new WOW().init();
			};
		</script> -->
		 <script type="text/javascript">
		 $(function(){
			 showAddress();
		 })
		 $('#myModal').on('shown.bs.modal', function () {
				var flag_deatails=false;
				 $('#deatails').blur(function(){
				var deatails = $('#deatails').val();
						if(deatails==""){
							$(this).parent().next().children().eq(0).text("详细地址不能为空");
						}else{
							$(this).parent().next().children().eq(0).text("");
							flag_deatails=true;
						}
				 });
				 var flag_post=false;
				 $('#post_code').blur(function(){
				 var post_code=$('#post_code').val();
					 var reg_post= /^[1-9][0-9]{5}$/
						if(!reg_post.test(post_code)){
							$('#post_code').parent().next().children().eq(0).text("邮政编码格式不正确");
						}else{
			            	$(this).parent().next().children().eq(0).text("");
					 		flag_post=true;
			            }
				 });
				 var flag_username=false;
				 $('#username').blur(function(){
				 var username=$('#username').val();
						if(username==""){
							$('#username').parent().next().children().eq(0).text("用户名不能为空");
						}else{
			            	$(this).parent().next().children().eq(0).text("");
							flag_username=true;
			            }
				 });
				 var flag_tel=false;
				  $('#tel').blur(function () {
					 var tel=$('#tel').val();
			            var regx_tel = /^1[3-9]\d{9}$/;
			            if(!regx_tel.test(tel)){
			            	$(this).parent().next().children().eq(0).text("号码格式不正确");
			            }else{
			            	$(this).parent().next().children().eq(0).text("");
			            	 flag_tel= true;
			            }
			        });
				$('#tijiaoadress').click(function(){
					 var province1 = $('#province1').val();
					 var city1=$('#city1').val();
					 if(flag_tel&&city1!=""&&flag_username&&flag_post&&flag_deatails&&province1!=""){
						var setDefaultAddr=$('#setDefaultAddr').prop('checked');
						$.ajax({
							url:"shipadress/addadress.do",//请求的路由地址
							type:"post",//请求的方式
							async:true,//异步请求控制开关，默认为true
							data:{
								"province1":province1,
								"city1":city1,
								"district1":$('#district1').val(),
								"deatails":$('#deatails').val(),
								"post_code":$('#post_code').val(),
								"name":$('#username').val(),
								"mobile":$('#tel').val(),
								"setDefaultAddr":setDefaultAddr
							},
							dataType:"text",//接受服务器响应的类型,text,html.json，jsonp（跨域访问）
							success:function(resp){//成功获取响应
								if(resp=="true"){
									 $('#myModal').modal('hide');
									 showAddress();
								}
								else{
									alert("添加失败");
								}
									},
							error:function(){
								alert("异常");
							}
					}); 
			}else{
					alert("请检查地址信息");
					}
				});
		 });
		function showAddress(){
			 $.ajax({
					url:"shipadress/showaddress.do",//请求的路由地址
					type:"post",//请求的方式
					async:true,//异步请求控制开关，默认为true
					data:{},
					dataType:"json",//接受服务器响应的类型,text,html.json，jsonp（跨域访问）
					success:function(list){//成功获取响应
						if(list==null){
							$('#address').html("<li>还没有添加过地址</li>");
							return;
						}
						var a='';
						for(var i=0;i<list.length;i++){
							if(i==0){
								a+='<li class="list-group-item"><input id="s'+list[i].id+'"type="radio" name="addr" checked>&nbsp;'+list[i].adress+'&nbsp;&nbsp;'+list[i].name+'&nbsp;&nbsp;'+list[i].mobile+'&nbsp;<span class="text-success">默认地址</span></li>'
							}else if(i<4){
								a+='<li class="list-group-item"><input id="s'+list[i].id+'" type="radio" name="addr">&nbsp;'+list[i].adress+'&nbsp;&nbsp;'+list[i].name+'&nbsp;&nbsp;'+list[i].mobile+'&nbsp;</li>'
							}else{
								a+='<li class="list-group-item hidden"><input type="radio" id="s'+list[i].id+'" name="addr">&nbsp;'+list[i].adress+'&nbsp;&nbsp;'+list[i].name+'&nbsp;&nbsp;'+list[i].mobile+'&nbsp;</li>'
							}
						}
						$('#address').html(a);
					},
					error:function(){
						//1.url匹配不到路由404
						//2.服务器端的任何异常都会进入error
						//3.服务器返回类型和期待类型不一样
						alert("异常");
					}
			}); 
		 }
		
		//检查有木有地址
	 	function checkAddress(){
			var radios=$('input[name="addr"]');
			if(radios.length==0){
				alert("请添加地址");
				return false
			}else{
				var count=0;
				for(var i=0;i<radios.length;i++){
					//是否被选中
					if(radios.eq(i).prop('checked')){
						count++;
						$('#oadress').val(radios.eq(i).attr("id").substring(1));
					}
				}
				if(count==1){
					return true;
				}
				alert("请选择一个地址");
				return false;
			}
		} 
		//此功能暂时放弃
		 function checkBnumber(){
			var flag;
			alert(888);
			var ids=[];
			$('input[name="id"]').each(function(index,element){
				ids.push($(this).val());
			})
			$.ajax({
				url:"book/checkSomeBooknumber.do",//请求的路由地址
				type:"post",//请求的方式
				async:false,//异步请求控制开关，默认为true
				data:{"ids":ids},
				dataType:"json",//接受服务器响应的类型,text,html.json，jsonp（跨域访问）
				success:function(list){//成功获取响应
					alert(list)
					if(list==null){
						flag= true;
					}
					for(var i=0;i<list.length;i++){
						alert("书籍:"+list[i].bname+"的数量不能超过"+list[i].number);
					}
					flag=false
						},
				error:function(){
					alert("异常");
				}
			});
			return flag;
		}
		 function toOrder(){
			 alert(1111);
			if(!checkAddress()){
				alert("提交失败");
				return;
			} 
			var flag=checkBnumber();
			alert(flag)
			 if(flag){
			 $('form').submit();
			 }
		} 
		</script>
	</body>
</html>