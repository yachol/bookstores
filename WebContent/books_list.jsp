<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书展示</title>
<%
	String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<base href="<%=path%>">
<style type="text/css">
.cai{
	background-color:#DDD;
}

</style>
	<link rel="stylesheet" type="text/css" href="css/order_list.css"/>
</head>
<body>
	<%@ include file="listnav.jsp"%>
	<!-- 类型选择 -->
	<div id="head">
        <ul id="caidan">
        </ul>
    </div>
   
    <!-- 主页 -->
        <div class="container"id="bookslist">
        	
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
 <script type="text/javascript" src="js/list.js">
   
 </script>
</body>
</html>