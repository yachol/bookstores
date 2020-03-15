/**
 * 
 */
 $(function(){
	 showtype();
	  var typeid =window.location.search.substring(6);
	  var typeid_reg=/^i\d+$/;
	  if(typeid_reg.test(typeid)){
		  showbook("",1,typeid,8,2);
	  }else{
		  showbook("",1,"i1",8,2);
	  }
 })
 function search(){
	 var keyword=$('#search').val();
	showbook(keyword,1,"",8,2);
 }
 function showtype() {
		$.ajax({
			url : "type/bigtype.do",// 请求的路由地址
			type : "get",// 请求的方式
			async : false,// 异步请求控制开关，默认为true
			data : {},
			dataType : "json",// 接受服务器响应的类型,text,html.json，jsonp（跨域访问）
			success : function(resp) {// 成功获取响应
				var caidan = "";
				for (var i = 0; i < resp.length; i++) {
					caidan += '<li id="c' + resp[i].id
							+ '"><a href="javascript:showbook(\'\',1,\'i'+resp[i].id+'\',8)">' + resp[i].type + '</a></li>'
			//alert(caidan);
				}
				$('#caidan').html(caidan);
			},
			error : function() {
				alert("菜单类型异常");
			}
		});
	}
 function gotobook(keyword,totalPage,bigtypeid,pageSize,selected){
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
		showbook(keyword,num,bigtypeid,pageSize,selected);
	}
 function showbook(keyword,currentPage, bigtypeid,pageSize,selected) {
		$.ajax({
					url : "book/bookslist.do",// 请求的路由地址
					type : "post",// 请求的方式
					async : false,// 异步请求控制开关，默认为true
					data : {
						"bigtypeid" : bigtypeid,
						"currentPage" : currentPage,
						"keyword":keyword,
						"pageSize":pageSize
					},
					dataType : "json",// 接受服务器响应的类型,text,html.json，jsonp（跨域访问）
					success : function(inedxBookModel) {// 成功获取响应
						$('.cai').eq(0).attr("class","");
						$('#c'+bigtypeid.substring(1)).attr("class","cai");
						// 渲染书的展示
						var booklist = inedxBookModel.list
						var books = "";
						if (booklist == "") {
							books += "<h1>您查询的商品不存在</h1>";
							// alert($('#'+bigtypeid+'').text());
							$('#bookslist').html(books);
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
									+ '<a href="details.jsp?id='+booklist[i].id+'"><img src="images/'
									+ booklist[i].img + '" alt="通用的占位符缩略图"></a>'
									+ '<div class="caption"><h3>'
									+ booklist[i].bname + '</h3>' + '<p>价格:'
									+ booklist[i].currentPrice + '元&nbsp;&nbsp;作者:'
									+ booklist[i].author + '&nbsp;&nbsp;</p>'
									+ '<p><a href="details.jsp?id='+booklist[i].id+'">更多信息</a>'
									+ '</p></div></div></div>';
						}
						$('#bookslist').html(books);
						// 翻页渲染
						
						var current = inedxBookModel.currentPage;
						var totalPage = inedxBookModel.totalPage
						var pages="";
						//前一页
						if (current == 1) {
							pages+=' <li class="disabled"><a href="javascript:;">&laquo;</a></li>';
						}else{
							pages+='<li><a href="javascript:showbook(\''+keyword+'\','+(current-1)+',\''+bigtypeid+'\','+pageSize+','+selected+')">&laquo;</a></li>';
							
						}
						//总页数小于等于7时，正常的样式，有几页显示几页
						if(totalPage<=7){
							for(var i=1;i<=totalPage;i++){
								if(i==current){
									pages+='<li class="active"><a href="javascript:;">'+i+'</a></li>';
								}else{
									pages+='<li><a href="javascript:showbook(\''+keyword+'\','+i+',\''+bigtypeid+'\','+pageSize+','+selected+')">'+i+'</a></li>';
								}
							}
						}else{//总页数大于7时分两种情况，
							if(current<=5){//1.一种当前页数小于等于5，需要在后面增加.....即1234[5]67...
								for(var i=1;i<=7;i++){
									if(i==current){//判断是否被选中
										pages+='<li class="active"><a href="javascript:;">'+i+'</a></li>';
									}else{
										pages+='<li><a href="javascript:showbook(\''+keyword+'\','+i+',\''+bigtypeid+'\','+pageSize+','+selected+')">'+i+'</a></li>';
									}
								}//后面的...
								pages+='<li><a  style="border-top:none;border-bottom:none">...</a></li>'
							}else{//当当前页数大于5时，前面肯定是12...但是又分两种情况
								for(var i=1;i<=2;i++){
									pages+='<li><a href="javascript:showbook(\''+keyword+'\','+i+',\''+bigtypeid+'\','+pageSize+','+selected+')">'+i+'</a></li>';
								}
								pages+='<li><a  style="border-top:none;border-bottom:none">...</a></li>'
									//当前页数和总页数相差大于2 12..c-2,c-1,[c],c+1,c+2...
									if((totalPage-current)>2){
										for(var i=current-2;i<=current-1;i++){
											pages+='<li><a href="javascript:showbook(\''+keyword+'\','+i+',\''+bigtypeid+'\','+pageSize+','+selected+')">'+i+'</a></li>';
										}
										pages+='<li class="active"><a href="javascript:;">'+current+'</a></li>';
										for(var i=current+1;i<=current+2;i++){
											pages+='<li><a href="javascript:showbook(\''+keyword+'\','+i+',\''+bigtypeid+'\','+pageSize+','+selected+')">'+i+'</a></li>';
										}
										pages+='<li><a  style="border-top:none;border-bottom:none">...</a></li>'
									}else{
										//当前页数和总页数相差小于等于2，即已翻到最后， 12...t-4,t-3,t-2,t-2,t
										for(var i=totalPage-4;i<=totalPage;i++){
											if(i==current){
												pages+='<li class="active"><a href="javascript:;">'+i+'</a></li>';
											}else{
												pages+='<li><a href="javascript:showbook(\''+keyword+'\','+i+',\''+bigtypeid+'\','+pageSize+','+selected+')">'+i+'</a></li>';
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
							pages+='<li><a href="javascript:showbook(\''+keyword+'\','+(current+1)+',\''+bigtypeid+'\','+pageSize+','+selected+')">&raquo;</a></li>'
							pages+='<li><a href="javascript:showbook(\''+keyword+'\','+totalPage+',\''+bigtypeid+'\','+pageSize+','+selected+')" style="border: 1px solid #ddd;">尾页</a></li>';
						}
						
						var total=inedxBookModel.total
						pages+='<li><a style="border: 0px;margin-left: 0px;">当前页'+current+'/'+totalPage+'总页,共'+total+'数据</a></li>'
						
						//指定页
						pages+=' <li><div id="search" class="input-group" style="positon:relative;">'
						+'<input type="text" class="form-control" id="num" placeholder="跳转指定页" / >'
						+'<span class="input-group-btn"><a class="btn btn-info btn-search" href="javascript:gotobook(\''+keyword+'\','+totalPage+',\''+bigtypeid+'\','+pageSize+','+selected+')">GO</a>'
						+'</span></div></li>';
					pages+='<li><a style="padding-top: 0px;border: 0px;">'
				     	+'<label>每页显示:</label>'
				     	+'<select id="pageSize" class="form-control" style="width: 100px;display: inline;" onchange="showbook(\''+keyword+'\','+current+',\''+bigtypeid+'\',this.options[this.options.selectedIndex].value*4,this.options[this.options.selectedIndex].value)">'
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
					error : function() {
						alert("booklist展示异常");
					}
				});
	}   