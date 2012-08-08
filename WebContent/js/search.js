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
//		$('#allInfo').css("display","none");
////		if (true) {
////			var errMsg = "<center style='color:red'>对不起，没有找到你想要查询的单词！</center>";
////			$('#allInfo').append(errMsg);
////			$('#outer-line').slideDown("fast");
////			return ;
////		}
//		var wordRes = "<article id='enWordInfo'>"
//			 + "	<header><h2>word</h2></header>"
//			 + "	<hr>"
//			 + "	<div class='ptsp1'>"
//			 + "		<h4>partOfSpeech-1.</h4>"
//			 + "		<p>extdAttr</p>"
//			 + "		<div id='ptsp1-mean1'>1.mean "
//			 + "			<a href='javascript:void(0)' id='tester1' class='tester'><img src='../images/sent_on.gif' alt='例句' title='点击显示/隐藏例句'></a>"
//			 + "			<div id='ptsp1-mean1-exmp1' style='display:none;margin-left:1em;'>"
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
//			 + "			<a href='javascript:void(0)' id='tester2' class='tester'><img src='../images/sent_on.gif' alt='例句' title='点击显示/隐藏例句'></a>"
//			 + "			<div id='ptsp1-mean2-exmp1' style='display:none;margin-left:1em;'>"
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
//		$('#allInfo').append(wordRes);
//		$('#allInfo').fadeIn("slow");
//		$('#outer-line').fadeIn("slow");
//		
//		var cnt = 0;
//		$('.tester').click(function() {
//			// change display attribute of example div
//			var idNum = $(this).attr("id").substring(6);
//			
//			var exmpId = "#ptsp1-mean" + idNum + "-exmp1";
//			$("#ptsp1-mean" + idNum + "-exmp1").toggle("normal");
//			
//			// change image behind meaning
//			if (cnt%2 == 0) {
//				
//				$("a[id=tester] > img").attr("src","../images/sent_off.gif");
//			} else if (cnt%2 == 1) {
//				$("a[id=tester] > img").attr("src","../images/sent_on.gif");
//			}
//			cnt++;
//		});
////		function exampleToggle() {
////			alert("1");
////			$('#ptsp1-mean1-exmp1').toggle();
////		}
//	});
	
	function searchResponse(result) {
		// 'result' is the json object returned from the server 
		$('#allInfo').empty();
		$('#allInfo').css("display","none");
		
		var enWordArr = result.enWordResponse.enWordInfo;
		if (enWordArr == null) {
			var errMsg = "<center style='color:red'>对不起，没有找到你想要查询的单词！</center>";
			$('#allInfo').append(errMsg);
			$('#allInfo').fadeIn("slow");
			$('#outer-line').fadeIn("slow");
			return ;
		}
		
		// to process enWordInfo START
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
		
		// to create ptsp div by loop
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
				// meaning number for page display
				var k = 1;
				// to create ptsp-mean div by loop
				for ( var j = 0; j < enWordArr.length; j ++) {
					if (j != 0) {
						if (meaningNum[j] == meaningNum[j - 1]) {
							continue;
						}
					}
					wrapStr += 
					   "<p>"
					 + "	<span>" + k + "." + mean[j] + "&nbsp;"
//					 + "		<a href='javascript:void(0)' id='ptsp" + i + "-mean" + j + "' onclick='exampleToggle(" + i + ", " + j + ")'>"
					 + "		<a href='javascript:void(0)' id='ptsp" + i + "-mean" + j + "'>"
					 + "			<img src='../images/sent_on.gif' alt='例句' title='点击显示/隐藏例句'>"
					 + "		</a>"
					 + "	</span>"
					 + "</p>"
					 + "<div id='ptsp" + i + "-mean" + j + "-exmp-all' style='display:none;margin-left:1em;'>";
					
					// to get the count of the same meaningNum which is necessary in loop creation of ptsp-mean-exmp div
					var meanCnt = 1;
					for (var tmpJ = j; tmpJ < enWordArr.length; tmpJ ++) {
						if (meaningNum[tmpJ] != meaningNum[tmpJ + 1]) {
							break;
						}
						meanCnt++;
					}
					
					// to create ptsp-mean-exmp div by loop
					for ( var m = j; m < j + meanCnt; m ++) {
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
		// to process enWordInfo END
			
		// to process enExtdWordInfo START
		var enExtdWordArr = result.enWordResponse.enExtdWordInfo;
		
		if (enExtdWordArr != null) {
			var extdWord = [enExtdWordArr.length];
			var extdWordExtdAttr = [enExtdWordArr.length];
			var extdWordMean = [enExtdWordArr.length];
			var dictId = [enExtdWordArr.length];
			var wordId = [enExtdWordArr.length];
			var extdWordPtsp = [enExtdWordArr.length];
			var extdWordMeanNum = [enExtdWordArr.length];
			var extdWordExmpNum = [enExtdWordArr.length];
			var extdWordExmpExtdAttr = [enExtdWordArr.length];
			var extdWordExmp = [enExtdWordArr.length];
			var extdWordExmpMean = [enExtdWordArr.length];
			for (var i = 0; i < enExtdWordArr.length; i ++) {
				extdWord[i] = $.trim(enExtdWordArr[i].extdWord);
				extdWordExtdAttr[i] = $.trim(enExtdWordArr[i].extdWordExtdAttr);
				extdWordMean[i] = $.trim(enExtdWordArr[i].extdWordMean);
				dictId[i] = $.trim(enExtdWordArr[i].dictId);
				wordId[i] = $.trim(enExtdWordArr[i].wordId);
				extdWordPtsp[i] = $.trim(enExtdWordArr[i].extdWordPtsp);
				extdWordMeanNum[i] = $.trim(enExtdWordArr[i].extdWordMeanNum);
				extdWordExmpNum[i] = $.trim(enExtdWordArr[i].extdWordExmpNum);
				extdWordExmpExtdAttr[i] = $.trim(enExtdWordArr[i].extdWordExmpExtdAttr);
				extdWordExmp[i] = $.trim(enExtdWordArr[i].extdWordExmp);
				extdWordExmpMean[i] = $.trim(enExtdWordArr[i].extdWordExmpMean);
			}
			

			wrapStr += 
				   "<br>"
				 + "<div id='more'>"
				 + "	<center>"
				 + "		<a href='javascript:void(0)' id='moreInfo'>更多详细释义"
				 + "			<img src='../images/down.gif' alt='More'>"
				 + "		</a>"
				 + "	</center>"
				 + "</div>"
				 + "<div id='enExtdWordInfoDiv' style='display:none'>"
				 + "<article id='enExtdWordInfo'>"
				 + "	<header><h4>" + extdWord[0] + "</h4></header>"
				 + "	<hr>";
			
			// to create ptsp div by loop
			for ( var i = 0; i < enExtdWordArr.length; i ++) {
				if (i != 0) {
					if (extdWordPtsp[i] == extdWordPtsp[i - 1]) {
						continue;
					}
				}
				wrapStr += 
					   "<div name='extdword-ptsp'>"
					 + "	<h4>" + extdWordPtsp[i] + ".</h4>";
					if (extdWordExtdAttr[i] != null && extdWordExtdAttr[i] != "") {
					wrapStr += 
					   "	<p>" + extdWordExtdAttr[i] + "</p>";
					}
					// meaning number for page display
					var k = 1;
					// to create ptsp-mean div by loop
					for ( var j = 0; j < enExtdWordArr.length; j ++) {
						if (j != 0) {
							if (extdWordMeanNum[j] == extdWordMeanNum[j - 1]) {
								continue;
							}
						}
						wrapStr += 
						   "<p>"
						 + "	<span>" + k + "." + extdWordMean[j] + "&nbsp;"
						 + "		<a href='javascript:void(0)' id='extdword-ptsp" + i + "-mean" + j + "'>"
						 + "			<img src='../images/sent_on.gif' alt='例句' title='点击显示/隐藏例句'>"
						 + "		</a>"
						 + "	</span>"
						 + "</p>"
						 + "<div id='extdword-ptsp" + i + "-mean" + j + "-exmp-all' style='display:none;margin-left:1em;'>";
						
						// to get the count of the same meaningNum which is necessary in loop creation of ptsp-mean-exmp div
						var meanCnt = 1;
						for (var tmpJ = j; tmpJ < enExtdWordArr.length; tmpJ ++) {
							if (extdWordMeanNum[tmpJ] != extdWordMeanNum[tmpJ + 1]) {
								break;
							}
							meanCnt++;
						}
						
						// to create ptsp-mean-exmp div by loop
						for ( var m = j; m < j + meanCnt; m ++) {
							wrapStr += 
								   "<div name='extdword-ptsp-mean-exmp'>"
								 + "	<p>"
								 + "		<span>" + extdWordExmp[m] + "&nbsp;" + extdWordExmpExtdAttr[m] + "</span>"
								 + "		<br>"
								 + "		<span>" + extdWordExmpMean[m] + "</span>"
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
				   "</article>"
				 + "</div>"
				 + "<div id='hide' style='display:none'>"
				 + "	<center>"
				 + "		<a href='javascript:void(0)' id='hideExtdInfo'>收起"
				 + "			<img src='../images/up.gif' alt='Hide'>"
				 + "		</a>"
				 + "	</center>"
				 + "</div>";
		}
		// to process enExtdWordInfo END
			
		// to append the html string to the page
		$('#allInfo').append(wrapStr);
		$('#allInfo').fadeIn("slow");
		$('#outer-line').fadeIn("slow");
		
		// to add onclick event for enWordMeaning
		$("a[id^='ptsp']").click(function() {
			
			var id = $(this).attr("id");
			var i = id.substring(4,5);
			var j = id.substring(10,11);
			
			var exmpDivId = "#ptsp" + i +"-mean" + j + "-exmp-all";
			
			// to change image of ptsp-mean href
			if ($(exmpDivId).css("display") == "none") {
				$("a[id=" + id + "] > img").attr("src","../images/sent_off.gif");
			} else if ($(exmpDivId).css("display") == "block") {
				$("a[id=" + id + "] > img").attr("src","../images/sent_on.gif");
			}
			
			// to change display attribute of example div
			$(exmpDivId).toggle("normal");
		});
		
		// to add onclick event for enExtdWordMeaning
		$("a[id^='extdword-ptsp']").click(function() {
			
			var id = $(this).attr("id");
			var i = id.substring(13,14);
			var j = id.substring(19,20);
			
			var exmpDivId = "#extdword-ptsp" + i +"-mean" + j + "-exmp-all";
			
			// to change image of ptsp-mean href
			if ($(exmpDivId).css("display") == "none") {
				$("a[id=" + id + "] > img").attr("src","../images/sent_off.gif");
			} else if ($(exmpDivId).css("display") == "block") {
				$("a[id=" + id + "] > img").attr("src","../images/sent_on.gif");
			}
			
			// to change display attribute of example div
			$(exmpDivId).toggle("normal");
		});
		
		// to add onclick event for “更多详细释义” link
		$("a[id='moreInfo']").click(function() {
			$("#more").css("display","none");
			
			// to change display attribute of example div
			$("#enExtdWordInfoDiv").toggle("normal");
			
			$("#hide").css("display","block");
		});
		
		// to add onclick event for “收起” link
		$("a[id='hideExtdInfo']").click(function() {
			$("#hide").css("display","none");
			
			// to change display attribute of example div
			$("#enExtdWordInfoDiv").toggle("normal");
			
			$("#more").css("display","block");
		});
		
	}
	
//	function exampleToggle(i, j) {
//		alert("1");
//		$("#ptsp" + i +"-mean" + j + "-exmp-all").toggle();
//	}
});
