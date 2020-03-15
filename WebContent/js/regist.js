/**
 * 
 */
//注册验证
		$('#register').on('shown.bs.modal', function () {
			//用户名失去焦点时
			var s=$('#uname').blur(isExist);
			var flag_uname = false;
			//检查用户名的方法
			function isExist(){
				const reg_name=/^[0-9a-zA-Z\u4E00-\u9FA5_]{1,16}$/;
				var uname=$('#uname').val();
				/* alert(uname); */
				 if(reg_name.test(uname)){
						$.ajax({
							url:"user/check_uname.do",
							type:"post",
							data:{"uname":uname},
							dataType:"text",
							success:function(resp){
								if(resp=="true"){
									 $('#uname').parent().next().children().eq(0).text("√");
									 $('#uname').parent().next().children().eq(0).css({"color":"green","background-color":"white"});
									flag_uname=true;
								}else{
									$('#uname').parent().next().children().eq(0).text("该用户名已注册");
									$('#uname').parent().next().children().eq(0).css({"color":"#a94442","background-color":"#f2dede"});
								}
							},
							error:function(){
								alert("user/check_uname异常");
							}
						})
		           }else {
		        	   $('#uname').parent().next().children().eq(0).text("用户名格式不正确");
		        	   $('#uname').parent().next().children().eq(0).css({"color":"#a94442","background-color":"#f2dede"});
		           }
			}
			
			 //2.检查密码，失去焦点时
			  $('#pwd').blur(function () {
				  var pwd =  $(this).val();
				  const reg_pwd=/^[0-9a-zA-Z_]{6,16}$/;
				  if(reg_pwd.test(pwd)){
					  $(this).parent().next().children().eq(0).text("√");
					  $(this).parent().next().children().eq(0).css({"color":"green","background-color":"white"});
				  }else {
					  $(this).parent().next().children().eq(0).text("密码格式不正确");
					  $(this).parent().next().children().eq(0).css({"color":"#a94442","background-color":"#f2dede"});
		           }
			  });
			 
			  //3. 验证密码两次是否一致
		        var flag_pwd= false;
		        $('#pwd2').blur(function () {
		            //1.获取两次密码（验证非空且相等）
		            var pwd = $('#pwd').val();
		            var pwd2 = $(this).val();
		            if(pwd!="" && pwd2!="" && pwd == pwd2){
		            	 $(this).parent().next().children().eq(0).text("√");
		            	 $(this).parent().next().children().eq(0).css({"color":"green","background-color":"white"});
		                flag_pwd= true;
		            }else{
		            	 $(this).parent().next().children().eq(0).html("&times;两次密码不一致");
		            	 $(this).parent().next().children().eq(0).css({"color":"#a94442","background-color":"#f2dede"});
		            }
		        });
		        //4.验证邮箱
		         var flag_email= false;
		        $('#email').blur(function () {
		            var email = $(this).val();
		            var regx_email = /^[a-zA-Z1-9][a-zA-Z0-9_]*@[a-zA-Z0-9]+\.[a-zA-Z]+$/;
		            if(regx_email.test(email)){
		            	$(this).parent().next().children().eq(0).html("<b>√</b>");
		            	 $(this).parent().next().children().eq(0).css({"color":"green","background-color":"white"});
		            	 flag_email= true;
		            }else{
		            	$(this).parent().next().children().eq(0).html("&times;邮箱格式不正确");
		            	 $(this).parent().next().children().eq(0).css({"color":"#a94442","background-color":"#f2dede"});
		            }
		        });
		      //5.验证手机号
		        var flag_mobile= false;
		        $('#mobile').blur(function () {
		            var mobile = $(this).val();
		            var regx_mobile = /^1[3-9]\d{9}$/;
		            if(regx_mobile.test(mobile)){
		            	$(this).parent().next().children().eq(0).html("<b>√</b>");
		            	 $(this).parent().next().children().eq(0).css({"color":"green","background-color":"white"});
		            	 flag_mobile= true;
		            }else{
		            	$(this).parent().next().children().eq(0).html("&times;手机格式不正确");
		            	 $(this).parent().next().children().eq(0).css({"color":"#a94442","background-color":"#f2dede"});
		            }
		        });
		        //6.验证公司不能过长
		        var flag_company= false;
		        $('#company').blur(function () {
		            var company = $(this).val();
		            if(company.length<=35&&company.length>=1){
		            	$(this).parent().next().children().eq(0).html("<b>√</b>");
		            	 $(this).parent().next().children().eq(0).css({"color":"green","background-color":"white"});
		            	
		            	 flag_company= true;
		        	}else{
		        		$(this).parent().next().children().eq(0).html("&times;公司名称过长");
		            	 $(this).parent().next().children().eq(0).css({"color":"#a94442","background-color":"#f2dede"});
		        	}
		        });
		        
		        
		 	   //提交按钮
			        $('#regist').click(function(){
			    		/* alert(flag_uname&&flag_pwd&&flag_phone); */
			    		if(flag_uname&&flag_pwd&&flag_email&&flag_mobile&&flag_company){
			    			$.ajax({
			    				url:"user/regist.do",
			    				type:"post",
			    				data:{"uname":$('#uname').val(),
			    					"pwd":$('#pwd').val(),
			    					"email":$('#email').val(),
			    					"mobile":$('#mobile').val(),
			    					"company":$('#company').val()
			    				},
			    				dataType:"json",
			    				success:function(data){
			    					 if(data.uname!=undefined){
			    						 if(confirm("注册成功，是否登录?")){
			    							 $('#register').modal('hide');
			    							 $('#login').modal('show');
			    							 //alert(data.uname+data.pwd);
			    							 $('#loginuname').val(data.uname);
			    							$('#loginpwd').val(data.pwd);
			    			                }else {
			    			                	 $('#register').modal('hide');
											}
			    			            }else{
			    			            	$('#regist').prev().prev().text("");
			    			            	$('#regist').prev().prev().text("注册失败,请重新注册");
			    			            }
			    				},
			    				error:function(){
			    					alert("异常");
			    				}
			    	})
			    		}
			    		else{
			    			$(this).prev().prev().text("");
			    			$(this).prev().prev().text("信息不完整");
			    		}
			    			
			        })
		        
		        
		});
		
			
			
	     