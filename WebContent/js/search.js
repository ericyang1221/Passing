$(document).ready(function() {
	$('#searchTabs').tabs();
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

//	$('input[type=submit]').click(function() {
//		$('#allInfo').empty();
//		if (true) {
//			var errMsg = "<center style='color:red'>对不起，没有找到你想要查询的单词！</center>";
//			$('#allInfo').append(errMsg);
//			$('#outer-line').slideDown("fast");
//			return ;
//		}
//		var wordRes = "<article id='enWordInfo'>"
//			 + "	<header><h2>word</h2></header>"
//			 + "	<hr>"
//			 + "	<div class='ptsp1'>"
//			 + "		<h4>partOfSpeech-1.</h4>"
//			 + "		<p>extdAttr</p>"
//			 + "		<div id='ptsp1-mean1'>1.mean +/-"
//			 + "			<div id='ptsp1-mean1-exmp1'>"
//			 + "				<p>"
//			 + "					<span>enExmp + exampleExtdAttr</span>"
//			 + "					<br>"
//			 + "					<span>exmpMeaning</span>"
//			 + "				</p>"
//			 + "				<hr>"
//			 + "			</div>"
//			 + "			<div id='ptsp1-mean1-exmp2'>"
//			 + "				<p>"
//			 + "					<span>enExmp2 + exampleExtdAttr2</span>"
//			 + "					<br>"
//			 + "					<span>exmpMeaning2</span>"
//			 + "				</p>"
//			 + "				<hr>"
//			 + "			</div>"
//			 + "		</div>"
//			 + "		<div id='ptsp1-mean2'>2.mean +/-"
//			 + "			<div id='ptsp1-mean2-exmp1'>"
//			 + "				<p>"
//			 + "					<span>enExmp + exampleExtdAttr</span>"
//			 + "					<br>"
//			 + "					<span>exmpMeaning</span>"
//			 + "				</p>"
//			 + "				<hr>"
//			 + "			</div>"
//			 + "			<div id='ptsp1-mean2-exmp2'>"
//			 + "				<p>"
//			 + "					<span id='test'>enExmp2 + exampleExtdAttr2</span>"
//			 + "					<br>"
//			 + "					<span>exmpMeaning2</span>"
//			 + "				</p>"
//			 + "				<hr>"
//			 + "			</div>"
//			 + "		</div>"
//			 + "	</div>"
//			 + "	<div class='ptsp2'>"
//			 + "		<h4>partOfSpeech-2.</h4>"
//			 + "		<p>以下省略</p>"
//			 + "	</div>"
//			 + "</article>"
//			 + "<center><a href=''>更多详细释义</a></center>";
//		$('#allInfo').prepend(wordRes);
//		$('#outer-line').slideDown(2000);
//	});
	
	function searchResponse(result) { 
		// 'result' is the json object returned from the server 
		var enWordArr = result.enWordResponse.enWordInfo;
		
		$('#allInfo').empty();
		if (enWordArr == null) {
			var errMsg = "<center style='color:red'>对不起，没有找到你想要查询的单词！</center>";
			$('#allInfo').append(errMsg);
			$('#outer-line').slideDown("fast");
			return ;
		}
		
		var word = [enWordArr.length];
		var extdAttr = [enWordArr.length];
		var mean = [enWordArr.length];
		var dictId = [enWordArr.length];
		var wordId = [enWordArr.length];
		var partOfSpeech = [enWordArr.length];
		var meaningNum = [enWordArr.length];
		var exampleNum = [enWordArr.length];
		var exampleExtdAttr = [enWordArr.length];
		var enExmp = [enWordArr.length];
		var exmpMeaning = [enWordArr.length];
		for (var i = 0; i < enWordArr.length; i ++) {
			word[i] = $.trim(enWordArr[i].word);
			extdAttr[i] = $.trim(enWordArr[i].extdAttr);
			mean[i] = $.trim(enWordArr[i].mean);
			dictId[i] = $.trim(enWordArr[i].dictId);
			wordId[i] = $.trim(enWordArr[i].wordId);
			partOfSpeech[i] = $.trim(enWordArr[i].partOfSpeech);
			meaningNum[i] = $.trim(enWordArr[i].meaningNum);
			exampleNum[i] = $.trim(enWordArr[i].exampleNum);
			exampleExtdAttr[i] = $.trim(enWordArr[i].exampleExtdAttr);
			enExmp[i] = $.trim(enWordArr[i].enExmp);
			exmpMeaning[i] = $.trim(enWordArr[i].exmpMeaning);
		}

		var wrapStr = 
			   "<article id='enWordInfo'>"
			 + "	<header><h2>" + word[0] + "</h2></header>"
			 + "	<hr>";
		for ( var i = 0; i < enWordArr.length; i ++) {
			if (i != 0) {
				if (partOfSpeech[i] == partOfSpeech[i - 1]) {
					continue;
				}
			}
			wrapStr += 
				   "<div name='ptsp'>"
				 + "	<h4>" + partOfSpeech[i] + ".</h4>";
				if (extdAttr[i] != null && extdAttr[i] != "") {
				wrapStr += 
				   "	<p>" + extdAttr[i] + "</p>";
				}
				var k = 1;
				for ( var j = 0; j < enWordArr.length; j ++) {
					if (j != 0) {
						if (meaningNum[j] == meaningNum[j - 1]) {
							continue;
						}
					}
					wrapStr += 
					   "<p>"
					 + "	<span>" + k + "." + mean[j] + "&nbsp;" + "<a href=''>+</a></span>"
					 + "</p>"
					 + "<div id='ptsp" + i + "-mean" + k + "'>";
					
					for ( var m = 0; m < enWordArr.length; m ++) {
						if (m != 0) {
							if (exampleNum[m] == exampleNum[m - 1]) {
								continue;
							}
						}
						wrapStr += 
							   "<div name='ptsp-mean-exmp'>"
							 + "	<p>"
							 + "		<span>" + enExmp[m] + "&nbsp;" + exampleExtdAttr[m] + "</span>"
							 + "		<br>"
							 + "		<span>" + exmpMeaning[m] + "</span>"
							 + "	</p>"
							 + "	<hr>"
							 + "</div>";
					}
						
					wrapStr += 
					   "</div>";
					k ++;
				}
				wrapStr += 
				   "</div>";
		}
			wrapStr += 
			   "</article>";
		$('#allInfo').append(wrapStr);
		$('#outer-line').slideDown(2000);
	}
});
