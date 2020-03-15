<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="dist/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/index.css" />
<link rel="stylesheet" type="text/css" href="css/animate.css" />
<style type="text/css">
	#head{
    height: 50px;
    /*background-color: salmon;*/
    /*margin-bottom: 40px;*/
    text-align: center;
    position: relative;
    }
    #caidan{
    display: inline-block;
    /*background-color: #2aabd2;*/
    height: 50px;
    line-height: 50px;
}
#caidan>li{
	font-size:18px;
	width:114px;
    display: inline-block;
    /*  background-color: #2aabd2; */
    /*padding-right: 20px;*/
}
#caidan>li:hover>a{
    color: #f56600;
}
</style> 


</head>
<body>
<div style="text-align:center">
    <iframe name="weather_inc" src="http://i.tianqi.com/index.php?c=code&id=2" width="770" height="70" frameborder="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>    
 </div>
<!--导航部分  begin-->
		<div class="container" style="margin-top: 5px;">
			<nav class="navbar navbar-default well-sm" style="padding-left: 0px;" role="navigation">
				<div class="container-fluid">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#example-navbar-collapse">
				            <span class="sr-only">切换导航</span>
				            <span class="icon-bar"></span>
				            <span class="icon-bar"></span>
				            <span class="icon-bar"></span>
        				</button>
						<a class="navbar-brand" href="#">在线书城</a>
					</div>
					<div class="collapse navbar-collapse" id="example-navbar-collapse">
						<ul class="nav navbar-nav pull-left">
							<li class="nv">
								<a href="index.jsp">首页</a>
							</li>
							<li  class="nv">
								<a href="books_list.jsp">图书分类</a>
							</li>
							<c:if test="${not empty sessionScope.user}">
								<li  class="nv">
									<a href="" οnclick="return false">${sessionScope.user.uname}</a>
								</li>
								<li  class="nv">
									<a href="user/destroy.do">安全退出</a>
								</li>
							</c:if>
							<c:if test="${empty sessionScope.user}">
								<li  class="nv">
									<a href="" οnclick="return false" data-toggle="modal" data-target="#login" id="test">登录</a>
								</li>
								<li  class="nv">
								<a href="" οnclick="return false" data-toggle="modal" data-target="#register">注册</a>
							</li>
							</c:if>
							<li  class="nv">
								<a href="javascript:void(0)"onclick=check()><span class="glyphicon glyphicon-shopping-cart"> </span>我的购物车</a>
							</li>
							<li>
								<a href="order.jsp">我的订单</a>
							</li>
						</ul>
						<div class="input-group col-md-3 pull-right" style="positon:relative;padding: 7px;width: 23% ;display:none">
							<input id="search"type="text" class="form-control" placeholder="请输入图书名" />
							<span class="input-group-btn" onclick=search()>  
					            <button class="btn btn-info btn-search">
					            	<span class="glyphicon glyphicon-search"></span>
							</button>
							</span>
						</div>
					</div>
				</div>
			</nav>
		</div>
		<!--导航部分 end-->
		<!--最顶端轮播图片 begin-->
		<div id="gcarouse" class="container">
			<!--轮播-->
			<div id="myCarousel" class="carousel slide">
				<!-- 轮播（Carousel）指标 -->
				<ol class="carousel-indicators">
					<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
					<li data-target="#myCarousel" data-slide-to="1"></li>
					<li data-target="#myCarousel" data-slide-to="2"></li>
				</ol>
				<!-- 轮播（Carousel）项目 -->
				<div class="carousel-inner">
					<div class="item active">
						<a href="details.html"><img src="images/advert1.jpg" class="pull-left" alt="First slide"></a>
					
					</div>
					<div class="item">
						<a href="details.html"><img src="images/advert2.jpg" class="pull-left" alt="First slide"></a>
						
					</div>
					<div class="item">
						<a href="details.html"><img src="images/advert3.jpg" class="pull-left" alt="First slide"></a>
					
					</div>
				</div>

				<!-- 轮播（Carousel）导航 -->
				<a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;</a>
				<a class="carousel-control right" href="#myCarousel" data-slide="next">&rsaquo;</a>
			</div>
		</div>
		<!--最顶端轮播图片 end-->
<!-- 登录模态框（Modal）-->
		<div class="modal fade" id="login" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
						<h4 class="modal-title" id="myModalLabel">
					用户登录
				</h4>
					</div>
					<div class="modal-body">
						<!--登录的form表单-->
						<form class="form-horizontal" role="form" >
							<div class="form-group has-feedback">
								<label for="firstname" class="col-sm-2 control-label">用户名</label>
								<div class="col-sm-5">
									<input type="text" class="form-control" id="loginuname" placeholder="请输入用户名">
									<span class="glyphicon glyphicon-user form-control-feedback"></span>
								</div>
								<div class="col-sm-3" style="padding-top: 10px;">
									<label class="alert-danger"></label>
								</div>
							</div>
							<div class="form-group has-feedback">
								<label for="lastname" class="col-sm-2 control-label">密码</label>
								<div class="col-sm-5">
									<input type="password" class="form-control" id="loginpwd" placeholder="请输入密码">
									<span class="glyphicon glyphicon-lock form-control-feedback"></span>
								</div>
								<div class="col-sm-3" style="padding-top: 10px;">
									<label class="alert-danger"></label>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<div class="checkbox">
										<label>
          									<input type="checkbox" checked>请记住我
        								</label>
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<div class="col-sm-5 alert-danger center" >
								</div>
								<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
								<input type="button" class="btn btn-primary" id="btn_login"value="登录">
							</div>
						</form>
						<!--form结束-->
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
		</div>
		<!-- 注册modal end/.modal  -->
		<div class="modal fade" id="register" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
						<h4 class="modal-title" id="myModalLabel">
					用户注册
				</h4>
					</div>
					<div class="modal-body">
						<!--注册的form表单-->
						<form method="post" class="form-horizontal" role="form">
							<div class="form-group has-feedback">
								<label for="uname" class="col-sm-2 control-label">用户名</label>
								<div class="col-sm-5">
									<input type="text" class="form-control" name="uname" id="uname" required placeholder="中文字母数字下划线组成.">
									<span class="glyphicon glyphicon-user form-control-feedback"></span>
								</div>
								<div class="col-sm-3" style="padding-top: 10px;">
									<label class="alert-danger"></label>
								</div>
							</div>
							<div class="form-group has-feedback">
								<label for="pwd" class="col-sm-2 control-label">密码</label>
								<div class="col-sm-5">
									<input type="password" class="form-control" name="pwd"  id="pwd" placeholder="6-16位字母数字下划线" minlength="6" maxlength="8">
									<span class="glyphicon glyphicon-lock form-control-feedback"></span>
								</div>
								<div class="col-sm-3" style="padding-top: 10px;">
									<label class="alert-danger"></label>
								</div>
							</div>
							<div class="form-group has-feedback">
								<label class="col-sm-2 control-label">确认密码</label>
								<div class="col-sm-5">
									<input type="password" class="form-control" id="pwd2" minlength="6" maxlength="8" placeholder="和密码保持一致">
									<span class="glyphicon glyphicon-lock form-control-feedback"></span>
								</div>
								<div class="col-sm-3" style="padding-top: 10px;">
									<label class="alert-danger"></label>
								</div>
							</div>

							<div class="form-group has-feedback">
								<label class="col-sm-2 control-label">邮箱</label>
								<div class="col-sm-5">
									<input type="email" class="form-control" name="email"id="email" placeholder="合法邮箱格式">
									<span class="glyphicon glyphicon-envelope form-control-feedback"></span>
								</div>
								<div class="col-sm-3" style="padding-top: 10px;">
									<label class="alert-danger"></label>
								</div>
							</div>
							
							<div class="form-group has-feedback">
								<label class="col-sm-2 control-label">验证码</label>
								<div class="col-sm-5">
									<input type="button" value="发送验证码" name="yan"id="nimei">
									<input type="text" class="form-control" name="yanzhengma"id="yanzhengma" placeholder="请输入收到的验证码">
									<span class="glyphicon glyphicon-envelope form-control-feedback"></span>
								</div>
								<div class="col-sm-3" style="padding-top: 10px;">
									<label class="alert-danger"></label>
								</div>
							</div>
							<div class="form-group has-feedback">
								<label class="col-sm-2 control-label">电话</label>
								<div class="col-sm-5">
									<input type="tel" class="form-control" name="mobile" id="mobile" placeholder="合法手机格式">
									<span class="glyphicon glyphicon-earphone form-control-feedback"></span>
								</div>
								<div class="col-sm-3" style="padding-top: 10px;">
									<label class="alert-danger"></label>
								</div>
							</div>

							<div class="form-group has-feedback">
								<label class="col-sm-2 control-label">公司</label>
								<div class="col-sm-5">
									<input type="text" class="form-control" name="company" id="company" placeholder="请输入公司地址">
									<span class="glyphicon glyphicon-home form-control-feedback"></span>
								</div>
								<div class="col-sm-3" style="padding-top: 10px;">
									<label class="alert-danger"></label>
								</div>
							</div>
						
							<div class="modal-footer">
								<div class="col-sm-5 alert-danger center" >
								
								</div>
								<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
								<input type="button" class="btn btn-primary" id="regist"value="注册">
							</div>
						</form>
						<!--form结束-->
					</div>
				</div>
				<!-- 注册/.modal-content -->
			</div>
		</div>
	<script src="dist/js/jquery.min.js"></script>
	<script src="dist/js/bootstrap.min.js"></script>
	<script src="js/carousel.js" type="text/javascript" charset="utf-8"></script>
	<script src="js/wow.js"></script>
	<script src="js/regist.js"></script> 
	<script type="text/javascript">
	
	function check(){
	    $.post("cart/init.do",
	        {
	        },
	        function(data){
	            if(data=="请登录"){
	                if(confirm("未登录，请登录?")){
	                	 $('#login').modal('show');
	                }
	            }else{
	                location.href="cart.jsp";
	            }
	        }
	    )
	}
		$('#test').click(function(){
		$('#loginuname').val("${cookie.uname.value}");
		
		$('#loginpwd').val("${cookie.pwd.value}");
	})
	//登录的验证
	$('#login').on('shown.bs.modal', function () {
		/* $(function(){
			$('#loginuname').val("${cookie.uname.value}");
			
			$('#loginpwd').val("${cookie.pwd.value}");
		}); */
		var flag_uname=false;
		//用户名是否存在检查
		$('#loginuname').blur(function(){
			var uname=$(this).val();
			 if(uname!=""){
					$.ajax({
						url:"user/check_uname.do",
						type:"post",
						data:{"uname":uname},
						dataType:"text",
						success:function(resp){
							if(resp=="false"){//true是没有这个用户
								 $('#loginuname').parent().next().children().eq(0).text("√");
								 $('#loginuname').parent().next().children().eq(0).css({"color":"green","background-color":"white"});
								flag_uname=true;
							}else{
								$('#loginuname').parent().next().children().eq(0).text("用户名不存在");
								$('#loginuname').parent().next().children().eq(0).css({"color":"#a94442","background-color":"#f2dede"});
							}
						},
						error:function(){
							alert("user/check_uname异常");
						}
					})
	           }else {
	        	   $(this).parent().next().children().eq(0).text("用户名不能为空");
	        	   $(this).parent().next().children().eq(0).css({"color":"#a94442","background-color":"#f2dede"});
	           }
		});
		var flag_pwd=false;
		$('#loginpwd').blur(function(){
			var pwd=$(this).val();
			 if(pwd==""){
	        	   $(this).parent().next().children().eq(0).text("密码不能为空");
	        	   $(this).parent().next().children().eq(0).css({"color":"#a94442","background-color":"#f2dede"});
	           }else{
	        	   $(this).parent().next().children().eq(0).text("");
	        	   flag_pwd=true;
	           }
		});
	
		
		$('input[name="yan"]').click(function(){
			alert(111)
		/* 	var email=$('#email').val();
			if(email==null){
				alert("请输入邮箱");
				return;
			}
			alert(email)
			$(this).attr('disabled',true);
			 var num =60;
		     var timer=setInterval(function () {
		    	 $('#yan').val(num--+"s后再次发送");
		            if (num<0){
		            	$('#yan').attr('disabled',false);
		            	$('#yan').val("发送验证码");
		            }
		        },1000);//每隔一秒执行一次function
				$.ajax({
					url:"user/yanzheng.do",//请求的路由地址
					type:"get",//请求的方式
					async:true,//异步请求控制开关，默认为true
					data:{"email":email},
					dataType:"text",//接受服务器响应的类型,text,html.json，jsonp（跨域访问）
					success:function(resp){//成功获取响应
						
							},
					error:function(){
						//1.url匹配不到路由404
						//2.服务器端的任何异常都会进入error
						//3.服务器返回类型和期待类型不一样
					
						alert("异常");
					}
	});  */
		})
	 	   //提交按钮
		        $('#btn_login').click(function(){
		    		if((flag_uname&&flag_pwd)||($('#loginpwd').val()!=""&&$('#loginuname').val()!="")){
		    			$.ajax({
		    				url:"user/login.do",
		    				type:"post",
		    				data:{"uname":$('#loginuname').val(),
		    					"pwd":$('#loginpwd').val(),
		    					"remember":$('input[type="checkbox"]').prop('checked')
		    				},
		    				dataType:"text",
		    				success:function(data){
		    					 if(data=="true"){
		    							$('#login').modal('hide');
		    			                location.href="index.jsp";
		    			            }else{
		    			            	$('#loginpwd').parent().next().children().eq(0).text("密码错误");
		    			            }
		    				},
		    				error:function(){
		    					alert("登录异常");
		    				}
		    	})
		    		}else{
		    			$(this).prev().prev().text("");
		    			$(this).prev().prev().text("请检查用户信息");
		    		}
		        });
		
	});
	</script>	
		 
</body>
</html>