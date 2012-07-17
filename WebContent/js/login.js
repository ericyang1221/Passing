$(document).ready(function() {
	$('.button').button();
	
/************************************************** to transform data with form START *********************************************************
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
*************************************************** to transform data with form END ********************************************************/

/************************************************** to transform data without form START*******************************************************/
	$('input[name=submit]').click(function() {
		var userInfo = "userName=" + $('input[name=userName]').fieldValue() + "&password=" + $('input[name=password]').val();//.fieldValue() and .val() can both getthe value
		var options = {
				url:'../doLogin.do',
				type:'POST',
				dataType:'json',
				data:userInfo,
				success: loginResponse
			};
		$.ajax(options); 
	});
/************************************************** to transform data without form END*******************************************************/

	$('input[name=reset]').click(function() {
		$('input[name=userName]').val("");
		$('input[name=password]').val("");
	});
	
	function loginResponse(result) { 
		// 'result' is the json object returned from the server 
		alert(result.loginResult);
	}
});