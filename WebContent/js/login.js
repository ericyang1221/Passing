$(document).ready(function() {
	$('.button').button();
	// bind 'loginForm' and provide a simple callback function
	var options = {
				url:'../doLogin.do',
				type:'POST',
				dataType:'json',
				success: loginResponse
			};
	
	$('#loginForm').submit(function() {
		$(this).ajaxSubmit(options); 
		// return false to prevent normal browser submit and page navigation
		return false;
	});
	
	function loginResponse(result) { 
		// 'result' is the json object returned from the server 
		alert(result.loginResult);
	}
});