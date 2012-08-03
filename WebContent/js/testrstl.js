$(document).ready(function() {

	//window.location can get the whole url which contains the data from test page.
	var urlData = window.location.toString();
	var previousData = urlData.substring(urlData.lastIndexOf('?')+1);
	alert(previousData);
});