$(document).ready(function(){

	$(":submit").mouseover(function(){
		$(this).css({background:"#C61432"});
	});
	
	$(":submit").mouseout(function(){
		$(this).css({background:"#EC3A58"});
	});
	
	$(":reset").mouseover(function(){
		$(this).css({background:"#C61432"});
	});
	
	$(":reset").mouseout(function(){
		$(this).css({background:"#EC3A58"});
	});
	
});