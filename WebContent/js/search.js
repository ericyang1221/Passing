$(document).ready(function() {
	$('#searchTabs').tabs({ ajaxOptions: { async: false } });
	$('input[type=submit]').button();
	
	$('input[type=submit]').click(function() {
		var searchStr = "searchStr=" + $('input[name=searchStr]').val();
		var options = {
				url:'../doSearch.do',
				type:'POST',
				dataType:'json',
				data:searchStr,
				success: searchResponse
			};
		$.ajax(options); 
	});
	
	function searchResponse(result) { 
		// 'result' is the json object returned from the server 
		alert("ok");
	}
});