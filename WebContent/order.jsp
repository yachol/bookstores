<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单</title>
<%
	String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<base href="<%=path%>">
		<link rel="stylesheet" type="text/css" href="css/bootstrap-datetimepicker.min.css"/>
		<link rel="stylesheet" type="text/css" href="css/order_list.css"/>
	</head>

	<body>
		<%@ include file="indexnav.jsp"%>
		

		<!--订单begin-->
		<!--外层div-->
		<div class="container">
			<!--左边-->
			<div class="container pull-left">
				<ol class="breadcrumb">
					<li>
						<a href="#" class="text-success"><span class="glyphicon glyphicon-list"></span>&nbsp;&nbsp;我的订单</a>
					</li>
				</ol>

				<!--订单查询导航 begin-->
				<div id="key">
				
				</div>
				
				<!--订单查询导航 end-->
				<!--日期控件-->
				<div id="collapseTwo" class="panel-collapse collapse">
					<div class="panel-body">
						<div class="form-group">	
							<div>
								<form class="form-horizontal">
							  		<div class="form-group has-feedback">
							  			<div class="col-md-1" style="padding-top:5px ;">
							  				成交日期:
							  			</div>
										<div class="col-md-3">
											<input type="text" class="form-control" id="datetimepicker" placeholder="开始时间">
											<span class="glyphicon glyphicon-time form-control-feedback"></span>
										</div>
										<div class="col-md-1" style="padding-top:5px ;text-align:center;">
							  				至
							  			</div>
										<div class="col-md-3">
											<input type="text" class="form-control" id="datetimepicker2" placeholder="结束日期">
											<span class="glyphicon glyphicon-time form-control-feedback"></span>
										</div>
										<div class="col-md-3">
											<button type="button" class="btn btn-default" onclick=time()>查询&nbsp;&nbsp;<span class="glyphicon glyphicon-search"></span></button>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
				<!--日期控件-->
				
				<!--内容展示 begin-->
				<div class="table-responsive" id="oo">
				 
				  
				  
				</div>
				<!--内容展示end-->
			</div>
			<!--分页begin-->
			<div class="container">
				<div class="row text-center">
					<div class="col-md-12">
						<ul class="pagination" id="page">
							
					    </ul>
					</div>
				</div>
			</div>
			<!--分页end-->
		</div>
		<div class="container">
		<hr>
			<!--首页底部信息 begin-->
			<div class="container">
				<div class="row">
					<div class="col-md-offset-2 col-md-8">
						<div class="row">
							<div class="row">
									<div class="col-md-offset-2 col-md-10">
										<a href="http://www.sztzedu.com">蜗牛创想科技有限公司<sup>&copy;</sup>所有</a>
									</div>
								</div>
								<div class="gap"></div>
								<div class="row">
									<div class="col-md-offset-2 col-md-10">
										<span class="text-info">蜗牛学院上海中心<sup>&reg;</sup> |&nbsp;</span>
										<span class="text-info">图书管理平台 |&nbsp;</span>
										<span class="text-info">2019</span>
									</div>
								</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--end-->
		<script src="js/moment.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/bootstrap-datetimepicker.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			$('#datetimepicker').datetimepicker();
			$('#datetimepicker2').datetimepicker();
		</script>
		<script type="text/javascript">
		$(function(){
			
			xall("","DESC","","",1,2,2)
		})
		function time(){
			
		}
		function formatDateTime(inputTime) {
    var date = new Date(inputTime);
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    m = m < 10 ? ('0' + m) : m;
    var d = date.getDate();
    d = d < 10 ? ('0' + d) : d;
    var h = date.getHours();
    h = h < 10 ? ('0' + h) : h;
    var minute = date.getMinutes();
    var second = date.getSeconds();
    minute = minute < 10 ? ('0' + minute) : minute;
    second = second < 10 ? ('0' + second) : second;
    return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;
};
		$('#datetimepicker').blur(function(){
			alert($(this).val())
		})
		 function sea(orderby,start,end,currentPage,pageSize,selected){
	 			var keyword=$('#se').val();
	 			xall(keyword,orderby,start,end,currentPage,pageSize,selected);
 }
		function xall(keyword,orderby,start,end,currentPage,pageSize,selected){
			xorder(keyword,orderby,start,end,currentPage,pageSize,selected);
			$('tbody').each(function(index, element) {
				xorderBook($(this).attr("id"));
			})
			$('.ad').each(function(index, element) {
				xadress($(this).attr("id"));
			})
		}
		function xorder(keyword,orderby,start,end,currentPage,pageSize,selected){
			$.ajax({
				url:"order/showorder.do",//请求的路由地址
				type:"post",//请求的方式
				async:false,//异步请求控制开关，默认为true
				data:{
					"orderby" : orderby,
					"currentPage" : currentPage,
					"keyword":keyword,
					"pageSize":pageSize,
					"start":start,
					"end":end
				},
				dataType:"json",//接受服务器响应的类型,text,html.json，jsonp（跨域访问）
				success:function(resp){//成功获取响应
					if(resp==null){
						alert("数据为空");
						return;
					}
					//渲染条件
					var key="";
					key='<nav class="navbar navbar-default" role="navigation">'
					+'<div class="navbar-header"><a class="navbar-brand">所有订单</a>'
					+'</div><form class="navbar-form navbar-left" role="search">'
					+'<input type="text" class="form-control"  id="se" placeholder="输入订单号进行查询" value="'+keyword+'">'
					+'<button type="button" class="btn btn-search" onclick=sea(\'DESC\',\''+start+'\',\''+end+'\','+currentPage+','+pageSize+','+selected+')>'
					+'<span class="text-success" onclick=>Search</span>&nbsp;&nbsp;'
					+'<span class="glyphicon glyphicon-search"></span></button>'
					+'</form><ul class="nav navbar-nav navbar-left"><li class="dropdown">'
					+'<a href="#" class="dropdown-toggle" data-toggle="dropdown">订单排序'
					+'<b class="caret"></b></a><ul class="dropdown-menu"><li>'
					+'<a href="javascript:xall(\''+keyword+'\',\'DESC\',\''+start+'\',\''+end+'\','+currentPage+','+pageSize+','+selected+')">订单日期降序</a></li><li class="divider"></li><li>'
					+'<a href="javascript:xall(\''+keyword+'\',\'ASC\',\''+start+'\',\''+end+'\','+currentPage+','+pageSize+','+selected+')">订单日期升序</a>'
					+'</li><li class="divider"></li></ul></li></ul>'
					+'<ul class="nav navbar-nav navbar-right"><li style="padding-right: 15px;">'
					+'<a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">更多筛选条件</a>'
					+'</li></ul><ul class="nav navbar-nav"><li><a href="javascript:;"onclick=delcks(\''+keyword+'\',\''+orderby+'\',\''+start+'\',\''+end+'\','+current+','+pageSize+','+selected+')>删除订单</a>'
					+'</li></ul><ul class="nav navbar-nav"><li>';
					+'<a href="#">清空订单列表</a></li></ul></nav>';
					$('#key').html(key);
					//展示渲染
					var orders=resp.list;
					var o="";
					for(var i=0;i<orders.length;i++){
						o+='<table class="table table-hover table-striped or">'
						+' <caption class="text-info"><div class="col-md-10" style="padding-left: 0px;">'
						+'<input type="checkbox" name="cks" value="'+orders[i].orderNumber+'">&nbsp;'+orders[i].orderTime+'订单号:'+orders[i].orderNumber+'&nbsp;&nbsp;未付款</div>'
						+'<div class="col-md-2" style="text-align: right;">'
						+'<a data-toggle="collapse" data-parent="#accordion" href="#collapseThree'+orders[i].id+'">订单详情</a>'
						+'</div></caption><thead><tr id="collapseThree'+orders[i].id+'" class="panel-collapse collapse">'
						+'<td colspan="9"><div class="panel-body"><ul class="nav nav-tabs"><li class="active">'
						+'<a href="#home'+orders[i].id+'" data-toggle="tab">联系人信息</a></li><li><a href="#ios'+orders[i].id+'" data-toggle="tab">订单信息</a>'
						+'</li></ul><div class="tab-content"><div class="tab-pane fade in active" id="home'+orders[i].id+'">'
						+'<div class="row"><div><ul id="ad'+orders[i].sid+'" class="nav navbar-collapse ad" style="line-height: 3;">'
						+'</ul></div></div></div><div class="tab-pane fade" id="ios'+orders[i].id+'">'
						+'<div class="row"><div><ul class="nav navbar-collapse" style="line-height: 3;">'
						+'<li class="text-info"><span class="text-success">订单编号:</span><span class="text-info">'+orders[i].orderNumber+'</span></li>'
						+'<li class="text-info"><span class="text-success">交易时间:</span>'+formatDateTime(new Date(orders[i].prayTime))+'</li>'
						+'<li class="text-info"><span class="text-success">订单总金额:</span>'+orders[i].totalPrice+'元</li>'
						+'</ul></div></div></div></div></div></td></tr>'
						+'<tr class="active"><th>序号</th><th>图片</th><th>图片名称</th>'
						+'<th>单价(元)</th><th>数量</th><th>实付款(元)</th><th>付款日期</th><th>交易状态</th>'
						+'</tr></thead><tbody  id="ob'+orders[i].orderNumber+'"></tbody></table>';
					}
					$('#oo').html(o);
					//分页渲染
					var current = resp.currentPage;
					var totalPage = resp.totalPage
					var pages="";
					//前一页
					if (current == 1) {
						pages+=' <li class="disabled"><a href="javascript:;">&laquo;</a></li>';
					}else{
						pages+='<li><a href="javascript:xall(\''+keyword+'\',\''+orderby+'\',\''+start+'\',\''+end+'\','+(current-1)+','+pageSize+','+selected+')">&laquo;</a></li>';
						
					}
					//总页数小于等于7时，正常的样式，有几页显示几页
					if(totalPage<=7){
						for(var i=1;i<=totalPage;i++){
							if(i==current){
								pages+='<li class="active"><a href="javascript:;">'+i+'</a></li>';
							}else{
								pages+='<li><a href="javascript:xall(\''+keyword+'\',\''+orderby+'\',\''+start+'\',\''+end+'\','+i+','+pageSize+','+selected+')">'+i+'</a></li>';
							}
						}
					}else{//总页数大于7时分两种情况，
						if(current<=5){//1.一种当前页数小于等于5，需要在后面增加.....即1234[5]67...
							for(var i=1;i<=7;i++){
								if(i==current){//判断是否被选中
									pages+='<li class="active"><a href="javascript:;">'+i+'</a></li>';
								}else{
									pages+='<li><a href="javascript:xall(\''+keyword+'\',\''+orderby+'\',\''+start+'\',\''+end+'\','+i+','+pageSize+','+selected+')">'+i+'</a></li>';
								}
							}//后面的...
							pages+='<li><a  style="border-top:none;border-bottom:none">...</a></li>'
						}else{//当当前页数大于5时，前面肯定是12...但是又分两种情况
							for(var i=1;i<=2;i++){
								pages+='<li><a href="javascript:xall(\''+keyword+'\',\''+orderby+'\',\''+start+'\',\''+end+'\','+i+','+pageSize+','+selected+')">'+i+'</a></li>';
							}
							pages+='<li><a  style="border-top:none;border-bottom:none">...</a></li>'
								//当前页数和总页数相差大于2 12..c-2,c-1,[c],c+1,c+2...
								if((totalPage-current)>2){
									for(var i=current-2;i<=current-1;i++){
										pages+='<li><a href="javascript:xall(\''+keyword+'\',\''+orderby+'\',\''+start+'\',\''+end+'\','+i+','+pageSize+','+selected+')">'+i+'</a></li>';
									}
									pages+='<li class="active"><a href="javascript:;">'+current+'</a></li>';
									for(var i=current+1;i<=current+2;i++){
										pages+='<li><a href="javascript:xall(\''+keyword+'\',\''+orderby+'\',\''+start+'\',\''+end+'\','+i+','+pageSize+','+selected+')">'+i+'</a></li>';
									}
									pages+='<li><a  style="border-top:none;border-bottom:none">...</a></li>'
								}else{
									//当前页数和总页数相差小于等于2，即已翻到最后， 12...t-4,t-3,t-2,t-2,t
									for(var i=totalPage-4;i<=totalPage;i++){
										if(i==current){
											pages+='<li class="active"><a href="javascript:;">'+i+'</a></li>';
										}else{
											pages+='<li><a href="javascript:xall(\''+keyword+'\',\''+orderby+'\',\''+start+'\',\''+end+'\','+i+','+pageSize+','+selected+')">'+i+'</a></li>';
										}
									}
								}
						}
					}
					//后一页
					if(current==totalPage){
						pages+='<li><a href="javascript:;">&raquo;</a></li>';
						pages+='<li><a href="javascript:;" style="border: 1px solid #ddd;">尾页</a></li>';
					}else{
						pages+='<li><a href="javascript:xall(\''+keyword+'\',\''+orderby+'\',\''+start+'\',\''+end+'\','+(current+1)+','+pageSize+','+selected+')">&raquo;</a></li>'
						pages+='<li><a href="javascript:xall(\''+keyword+'\',\''+orderby+'\',\''+start+'\',\''+end+'\','+totalPage+','+pageSize+','+selected+')" style="border: 1px solid #ddd;">尾页</a></li>';
					}
					
					var total=resp.total
					pages+='<li><a style="border: 0px;margin-left: 0px;">当前页'+current+'/'+totalPage+'总页,共'+total+'数据</a></li>'
					
					//指定页
					pages+=' <li><div id="search" class="input-group" style="positon:relative;">'
					+'<input type="text" class="form-control" id="num" placeholder="跳转指定页" / >'
					+'<span class="input-group-btn"><a class="btn btn-info btn-search" href="javascript:gotoorder(\''+keyword+'\',\''+orderby+'\',\''+start+'\',\''+end+'\','+totalPage+','+pageSize+','+selected+')">GO</a>'
					+'</span></div></li>';
				pages+='<li><a style="padding-top: 0px;border: 0px;">'
			     	+'<label>每页显示:</label>'
			     	+'<select id="pageSize" class="form-control" style="width: 100px;display: inline;" onchange="xall(\''+keyword+'\',\''+orderby+'\',\''+start+'\',\''+end+'\','+current+',this.options[this.options.selectedIndex].value,this.options[this.options.selectedIndex].value)">'
			     for(var i=2;i<=5;i++){
			    	 if(selected==i){
			    		pages+= '<option value="'+i+'" selected>'+i+'</option>';
			    	 }else{
			    		 pages+='<option value="'+i+'">'+i+'</option>';
			    	 }
			     }
			     	pages+='</select><label>条</label></a></li>';
			     	$('#page').html(pages);
					
						},
				error:function(){
				
					alert("异常");
				}
}); 
			
		}
		function gotoorder(keyword,orderby,start,end,totalPage,pageSize,selected){
			var num = $('#num').val();
			var reg_num=/^\d+$/;
			if(!reg_num.test(num)){
				alert("页码必须是数字");
				return;
			}
			if(num<1 || num>totalPage){
				alert("页码不在范围");
				return;
			}
			xall(keyword,orderby,start,end,num,pageSize,selected);
		}
		function xorderBook(orderNumber){
			$.ajax({
				url:"order/showOrderBook.do",//请求的路由地址
				type:"post",//请求的方式
				async:true,//异步请求控制开关，默认为true
				data:{"orderNumber":orderNumber},
				dataType:"json",//接受服务器响应的类型,text,html.json，jsonp（跨域访问）
				success:function(resp){//成功获取响应
					if(resp==null){
						alert("这个订单没有书");
						return;
					}
					var ob="";
				for(var i=0;i<resp.length;i++){
					ob+='<tr><td>'+resp[i].id+'</td>'
					+' <td><img src="images/'+resp[i].bimg+'"></td>'
			      	+'<td>'+resp[i].bname+'</td><td>'+resp[i].price+'</td>'
			       +'<td>'+resp[i].bnumber+'</td><td>'+resp[i].payPrice+'</td><td>'+formatDateTime(new Date(resp[i].payTime))+'</td>'
			        +'<td>未付款</td></tr>'
				}
				$('#'+orderNumber).html(ob);
						},
				error:function(){
					//1.url匹配不到路由404
					//2.服务器端的任何异常都会进入error
					//3.服务器返回类型和期待类型不一样
				
					alert("异常");
				}
}); 
		}
		function xadress(sid){
			$.ajax({
				url:"shipadress/showad.do",//请求的路由地址
				type:"post",//请求的方式
				async:true,//异步请求控制开关，默认为true
				data:{"sid":sid},
				dataType:"json",//接受服务器响应的类型,text,html.json，jsonp（跨域访问）
				success:function(resp){//成功获取响应
					if(resp==null){
						alert("这个订单没有地址");
						return;
					}
					var ad="";
					ad+='<li class="text-info"><span class="text-success">姓名:</span><span class="text-info">'+resp.name+'</span></li>'
					+'<li class="text-info"><span class="text-success">联系方式:</span>'+resp.mobile+'</li>'
					+'<li class="text-info"><span class="text-success">收货地址:</span>'+resp.adress+'</li>'
				$('#'+sid).html(ad);
						},
				error:function(){
					//1.url匹配不到路由404
					//2.服务器端的任何异常都会进入error
					//3.服务器返回类型和期待类型不一样
				
					alert("异常");
				}
}); 
		}
		function delcks(keyword,orderby,start,end,currentPage,pageSize,selected){
			onumber=[];
			$('input[name="cks"]').each(function(index,element){
				if($(element).prop('checked')){
					onumber.push($(this).val())
				}
			})
			alert(onumber);
			if(onumber.length>0){
				if(confirm("确认删除？")){
					$.ajax({
						url:"order/delorder.do",//请求的路由地址
						type:"post",//请求的方式
						async:true,//异步请求控制开关，默认为true
						data:{"onumber":onumber},
						dataType:"text",//接受服务器响应的类型,text,html.json，jsonp（跨域访问）
						success:function(resp){//成功获取响应
							if(resp=="true"){
								alert("成功")
								xall(keyword,orderby,start,end,currentPage,pageSize,selected);
							}
								},
						error:function(){
							//1.url匹配不到路由404
							//2.服务器端的任何异常都会进入error
							//3.服务器返回类型和期待类型不一样
						
							alert("异常");
						}
		});
				}
				 
			}else{
				alert("请选择要删除的订单")
			}
			
			
			
		}
		
		</script>
	</body>
