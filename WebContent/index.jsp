<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>在线书城首页</title>
<%-- <%
	String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<base href="<%=path%>"> --%>
	</head>
<body>
	<!--导航部分  begin-->
		<%@ include file="indexnav.jsp"%>
	<!--导航部分  end-->

		<!--图书内容部分 begin-->
		<div class="container" id="indexbook">
		</div>
		<!--首页底部信息 begin-->
			<div class="container">
				<div class="row">
					<div class="col-md-offset-2 col-md-8">
						<div class="row">
							<div class="col-md-offset-2 col-md-1"></div>
							<div class="col-md-6">
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
		<!--首页底部信息end->
		<!--图书内容部分end-->
		<script type="text/javascript" src="js/page.js"></script>
		<script>
		
		
		</script>
		
	
	</body>

</html>