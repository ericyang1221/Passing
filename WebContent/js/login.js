$(document).ready(function() {
	$('#date').datepicker();
	// bind 'loginForm' and provide a simple callback function
	$('#Tip').hide();//��ʾ������ʾ��Ԫ�ز��ɼ�
	var options = {
				target:'#Tip', //��̨���Ѵ��ݹ�����ֵ������Ԫ�� 
				url:'../doLogin.do',
				type:'POST',
				success: function(){ alert($('#Tip').text());}
			};
	$('#loginForm').submit(function() {
		//alert("Thank you for your comment!");
		$('#loginForm').ajaxSubmit(options); 
		return false;//Ϊ�˲�ˢ��ҳ��,����false
	});
	
});