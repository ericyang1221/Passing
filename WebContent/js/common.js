$(document).ready(function(){

//	$(":submit").mouseover(function(){
//		$(this).css({background:"#C61432"});
//	});
//	
//	$(":submit").mouseout(function(){
//		$(this).css({background:"#EC3A58"});
//	});
//	
//	$(":reset").mouseover(function(){
//		$(this).css({background:"#C61432"});
//	});
//	
//	$(":reset").mouseout(function(){
//		$(this).css({background:"#EC3A58"});
//	});
	
	
	//how to use the function below:
	//$.include('file/ajaxa.js');$.include('file/ajaxa.css');
	//or $.includePath  = 'file/';$.include(['ajaxa.js','ajaxa.css']);(only if .js and .css files are in the same directory)
	$.extend({
	    includePath: '',
	    include: function(file)
	    {
	        var files = typeof file == "string" ? [file] : file;
	        for (var i = 0; i < files.length; i++)
	        {
	            var name = files[i].replace(/^\s|\s$/g, "");
	            var att = name.split('.');
	            var ext = att[att.length - 1].toLowerCase();
	            var isCSS = ext == "css";
	            var tag = isCSS ? "link" : "script";
	            var attr = isCSS ? " type='text/css' rel='stylesheet' " : " type='text/javascript' ";
	            var link = (isCSS ? "href" : "src") + "='" + $.includePath + name + "'";
	            if ($(tag + "[" + link + "]").length == 0) $("head").prepend("<" + tag + attr + link + "></" + tag + ">");
	        }
	    }
	});
	$.include('../js/jquery-ui-1.8.21.custom.min.js');
	$.include('../css/black-tie/jquery-ui-1.8.21.custom.css');
	
});

