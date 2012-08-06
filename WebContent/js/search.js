$(document).ready(function() {
	$('#searchTabs').tabs();
	$('input[type=submit]').button();
	
//	$('input[type=submit]').click(function() {
//		var searchStr = "searchStr=" + $('input[name=searchStr]').val();
//		var options = {
//				url:'../doSearch.do',
//				type:'POST',
//				dataType:'json',
//				data:searchStr,
//				success: searchResponse
//			};
//		$.ajax(options); 
//	});

	$('input[type=submit]').click(function() {
		$('#allInfo').empty();
		if (true) {
			var errMsg = "<center style='color:red'>对不起，没有找到你想要查询的单词！</center>";
			$('#allInfo').append(errMsg);
			$('#outer-line').slideDown("fast");
			return ;
		}
		var wordRes = "<article id='enWordInfo'>"
			 + "	<header><h2>word</h2></header>"
			 + "	<hr>"
			 + "	<div class='ptsp1'>"
			 + "		<h4>partOfSpeech-1.</h4>"
			 + "		<p>extdAttr</p>"
			 + "		<div id='ptsp1-mean1'>1.mean +/-"
			 + "			<div id='ptsp1-mean1-exmp1'>"
			 + "				<p>"
			 + "					<span>enExmp + exampleExtdAttr</span>"
			 + "					<br>"
			 + "					<span>exmpMeaning</span>"
			 + "				</p>"
			 + "				<hr>"
			 + "			</div>"
			 + "			<div id='ptsp1-mean1-exmp2'>"
			 + "				<p>"
			 + "					<span>enExmp2 + exampleExtdAttr2</span>"
			 + "					<br>"
			 + "					<span>exmpMeaning2</span>"
			 + "				</p>"
			 + "				<hr>"
			 + "			</div>"
			 + "		</div>"
			 + "		<div id='ptsp1-mean2'>2.mean +/-"
			 + "			<div id='ptsp1-mean2-exmp1'>"
			 + "				<p>"
			 + "					<span>enExmp + exampleExtdAttr</span>"
			 + "					<br>"
			 + "					<span>exmpMeaning</span>"
			 + "				</p>"
			 + "				<hr>"
			 + "			</div>"
			 + "			<div id='ptsp1-mean2-exmp2'>"
			 + "				<p>"
			 + "					<span id='test'>enExmp2 + exampleExtdAttr2</span>"
			 + "					<br>"
			 + "					<span>exmpMeaning2</span>"
			 + "				</p>"
			 + "				<hr>"
			 + "			</div>"
			 + "		</div>"
			 + "	</div>"
			 + "	<div class='ptsp2'>"
			 + "		<h4>partOfSpeech-2.</h4>"
			 + "		<p>以下省略</p>"
			 + "	</div>"
			 + "</article>"
			 + "<center><a href=''>更多详细释义</a></center>";
		$('#allInfo').prepend(wordRes);
		$('#outer-line').slideDown(2000);
	});
	
	function searchResponse(result) { 
		// 'result' is the json object returned from the server 
//		alert(result.enWordResponse.enWordInfo[0].word + ":" + result.enWordResponse.enExtdWordInfo[0].extdWord);
		var enWordArr = result.enWordResponse.enWordInfo;
		
		$('#allInfo').empty();
		if (enWordArr == null) {
			var errMsg = "<center>对不起，没有找到你想要查询的单词！</center>";
			$('#allInfo').append(errMsg);
			$('#outer-line').slideDown(2000);
			return ;
		}
		
		var word;
		var extdAttr;
		var mean;
		var dictId;
		var wordId;
		var partOfSpeech;
		var meaningNum;
		var exampleNum;
		var exampleExtdAttr;
		var enExmp;
		var exmpMeaning;
//		for (var i == 0; i < enWordArr.length; i ++) {
//			word[i] = enWordArr[i].word;
//			extdAttr[i] = enWordArr[i].extdAttr;
//			mean[i] = enWordArr[i].mean;
//			dictId[i] = enWordArr[i].dictId;
//			wordId[i] = enWordArr[i].wordId;
//			partOfSpeech[i] = enWordArr[i].partOfSpeech;
//			meaningNum[i] = enWordArr[i].meaningNum;
//			exampleNum[i] = enWordArr[i].exampleNum;
//			exampleExtdAttr[i] = enWordArr[i].exampleExtdAttr;
//			enExmp[i] = enWordArr[i].enExmp;
//			exmpMeaning[i] = enWordArr[i].exmpMeaning;
//		}
	}
});
