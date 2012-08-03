$(document).ready(function() {
	$('#searchTabs').tabs();
	$('input[type=submit]').button();
	
	$('input[type=submit]').click(function() {
		var searchStr = $('input[name=searchStr]').val();
		if (searchStr == "y") {
			window.location = "testrstl.html?r=" + searchStr;
		} else {
			alert(searchStr);
		}
	});
	
});