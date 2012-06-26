$(document).ready(function() {
	$('#date').datepicker();
	// bind 'loginForm' and provide a simple callback function
	$('#Tip').hide();//显示操作提示的元素不可见
	var options = {
				target:'#Tip', //后台将把传递过来的值赋给该元素 
				url:'../doLogin.do',
				type:'POST',
				success: function(){ alert($('#Tip').text());}
			};
	$('#loginForm').submit(function() {
		//alert("Thank you for your comment!");
		$('#loginForm').ajaxSubmit(options); 
		return false;//为了不刷新页面,返回false
	});
	
});