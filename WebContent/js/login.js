$(document).ready(function() {
	$('.button').button();
	
/************************************************** to transport data with form START *********************************************************
	$('#loginForm').submit(function() {
		var options = {
				url:'../doLogin.do',
				type:'POST',
				dataType:'json',
				success: loginResponse
			};
		$(this).ajaxSubmit(options); 
		// return false to prevent normal browser submit and page navigation
		return false;
	});
*************************************************** to transport data with form END ********************************************************/

/************************************************** to transport data without form START*******************************************************/
	$('input[name=submit]').click(function() {
		if ($('input[name=userName]').val() == "" || $('input[name=password]').val() == "") {
			$("#errMsgDiv").css("visibility","visible");
			if ($("#errMsgDiv").val() != "请输入用户名和密码") {
				$("#errMsgDiv").text("请输入用户名和密码");
			}
			return false;
		}
		var userInfo = "userName=" + $('input[name=userName]').fieldValue() + "&password=" + $('input[name=password]').val();//.fieldValue() and .val() can both get the value
		var options = {
				url:'../doLogin.do',
				type:'POST',
				dataType:'json',
				data:userInfo,
				success: loginResponse
			};
		$.ajax(options); 
	});
/************************************************** to transport data without form END*******************************************************/

	$('input[name=reset]').click(function() {
		$('input[name=userName]').val("");
		$('input[name=password]').val("");
	});
	
	function loginResponse(result) { 
		// 'result' is the json object returned from the server 
		var loginRsl = eval(result);
		if (loginRsl.loginResult == "登陆成功") {
			window.location = "search.html";
		} else {
			$("#errMsgDiv").css("visibility","visible");
			$("#errMsgDiv").text(loginRsl.loginResult);
		}
	}
	
	// Enter Submit
	$("input[id='userName']").keydown(function(e) {
		var curKey = e.which;
		if (curKey == 13) {
			$("input[type='submit']").click();
			return false;
		}
	});
	
	// Enter Submit
	$("input[id='password']").keydown(function(e) {
		var curKey = e.which;
		if (curKey == 13) {
			$("input[type='submit']").click();
			return false;
		}
	});
});