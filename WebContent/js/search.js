$(document).ready(function() {
	$('#searchTabs').tabs();
	$('input[type=submit]').button();
	
	$('input[id=searchChtoEn]').click(function() {
		if ($('input[name=searchStr]').val() == "") {
			if ($("#errMsgDiv").text() == "对不起，没有找到你想要查询的单词！") {
				$("#errMsgDiv").text("请输入你想要查询的单词！");
			// null search after one search action succeed
			} else if ($("#errMsgDiv").text() == "Research After Success") {
				var errMsg = "<center><div id='errMsgDiv' style='color:red;'>请输入你想要查询的单词！</div></center>";

				$('#allInfo').empty();
				$('#allInfo').append(errMsg);
				$('#allInfo').fadeIn("slow");
				$('#outer-line').fadeIn("slow");
				return ;
			}
			
			$("#errMsgDiv").css("display","block");
			$('#allInfo').fadeIn("slow");
			$('#outer-line').fadeIn("slow");
			return ;
		}
		
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
		$('#allInfo').empty();
		$('#allInfo').css("display","none");
		
		var enWordArr = result.enWordResponse.enWordInfo;
		if (enWordArr == null) {
			var errMsg = "<center><div id='errMsgDiv' style='color:red;'>对不起，没有找到你想要查询的单词！</div></center>";

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
			word[i] = htmlEscape($.trim(enWordArr[i].word));
			extdAttr[i] = htmlEscape($.trim(enWordArr[i].extdAttr));
			mean[i] = htmlEscape($.trim(enWordArr[i].mean) == "" ? "(无释义，点击直接查看例句)" : $.trim(enWordArr[i].mean));
			mean[i] = strFilterForMeaning(mean[i]);
			dictId[i] = htmlEscape($.trim(enWordArr[i].dictId));
			wordId[i] = htmlEscape($.trim(enWordArr[i].wordId));
			partOfSpeech[i] = htmlEscape($.trim(enWordArr[i].partOfSpeech));
			meaningNum[i] = htmlEscape($.trim(enWordArr[i].meaningNum));
			exampleNum[i] = htmlEscape($.trim(enWordArr[i].exampleNum));
			exampleExtdAttr[i] = htmlEscape($.trim(enWordArr[i].exampleExtdAttr));
			enExmp[i] = htmlEscape($.trim(enWordArr[i].enExmp));
			exmpMeaning[i] = htmlEscape($.trim(enWordArr[i].exmpMeaning));
		}

		var wrapStr = 
			"<div id='enWordInfoDiv-all'>";
		
		// to create wordInfo article by loop
		for (var q = 0; q < enWordArr.length; q ++) {
			
			if (q != 0) {
				if (wordId[q] == wordId[q - 1]) {
					continue;
				}
			}
			
			wrapStr += 
				   "<article id='enWordInfo'>"
				 + "	<header><h1>" + word[q] + "</h1></header>"
				 + "	<hr>";
			
			// to get the count of the same ptsp which is necessary in loop creation of ptsp div
			var wordPtspCnt = 1;
			for (var tmpQ = q; tmpQ < enWordArr.length; tmpQ ++) {
				if (wordId[tmpQ] != wordId[tmpQ + 1] || partOfSpeech[tmpQ] != partOfSpeech[tmpQ + 1]) {
					break;
				}
				wordPtspCnt ++;
			}
			
			// to create ptsp div by loop
			for ( var i = q; i < q + wordPtspCnt; i ++) {
				if (i != q) {
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
					for ( var j = i; j < i + wordPtspCnt; j ++) {
						if (j != i) {
							if (meaningNum[j] == meaningNum[j - 1]) {
								continue;
							}
						}
						wrapStr += 
						   "<p>"
						 + "	<span>" + k + "." + mean[j] + "&nbsp;"
//						 + "		<a href='javascript:void(0)' id='ptsp" + i + "-mean" + j + "' onclick='exampleToggle(" + i + ", " + j + ")'>"
						 + "		<a href='javascript:void(0)' id='ptsp" + i + "-mean" + j + "'>"
						 + "			<img src='../images/sent_on.gif' alt='例句' title='点击显示/隐藏例句'>"
						 + "		</a>"
						 + "	</span>"
						 + "</p>"
						 + "<div id='ptsp" + i + "-mean" + j + "-exmp-all' style='display:none;margin-left:1em;color:#646464;'>";
						
						// to get the count of the same meaningNum which is necessary in loop creation of ptsp-mean-exmp div
						var meanCnt = 1;
						for (var tmpJ = j; tmpJ < j + wordPtspCnt; tmpJ ++) {
							if (partOfSpeech[tmpJ] != partOfSpeech[tmpJ + 1] || meaningNum[tmpJ] != meaningNum[tmpJ + 1]) {
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
		}
		// <div id='enWordInfoDiv-all'> end
		wrapStr += 
			   "</div>";
		
		// to process enWordInfo END
			
		// to process enExtdWordInfo START
		var enExtdWordArr = result.enWordResponse.enExtdWordInfo;
		
		if (enExtdWordArr != null) {
			var extdWord = [enExtdWordArr.length];
			var extdWordExtdAttr = [enExtdWordArr.length];
			var extdWordMean = [enExtdWordArr.length];
			var dictId = [enExtdWordArr.length];
			var wordId = [enExtdWordArr.length];
			var extdWordId = [enExtdWordArr.length];
			var extdWordPtsp = [enExtdWordArr.length];
			var extdWordMeanNum = [enExtdWordArr.length];
			var extdWordExmpNum = [enExtdWordArr.length];
			var extdWordExmpExtdAttr = [enExtdWordArr.length];
			var extdWordExmp = [enExtdWordArr.length];
			var extdWordExmpMean = [enExtdWordArr.length];
			for (var i = 0; i < enExtdWordArr.length; i ++) {
				extdWord[i] = htmlEscape($.trim(enExtdWordArr[i].extdWord));
				extdWordExtdAttr[i] = htmlEscape($.trim(enExtdWordArr[i].extdWordExtdAttr));
				extdWordMean[i] = htmlEscape($.trim(enExtdWordArr[i].extdWordMean) == "" ? "(无释义，点击直接查看例句)" : $.trim(enExtdWordArr[i].extdWordMean));
				extdWordMean[i] = strFilterForMeaning(extdWordMean[i]);
				dictId[i] = htmlEscape($.trim(enExtdWordArr[i].dictId));
				wordId[i] = htmlEscape($.trim(enExtdWordArr[i].wordId));
				extdWordId[i] = htmlEscape($.trim(enExtdWordArr[i].extdWordId));
				extdWordPtsp[i] = htmlEscape($.trim(enExtdWordArr[i].extdWordPtsp));
				extdWordMeanNum[i] = htmlEscape($.trim(enExtdWordArr[i].extdWordMeanNum));
				extdWordExmpNum[i] = htmlEscape($.trim(enExtdWordArr[i].extdWordExmpNum));
				extdWordExmpExtdAttr[i] = htmlEscape($.trim(enExtdWordArr[i].extdWordExmpExtdAttr));
				extdWordExmp[i] = htmlEscape($.trim(enExtdWordArr[i].extdWordExmp));
				extdWordExmpMean[i] = htmlEscape($.trim(enExtdWordArr[i].extdWordExmpMean));
			}
			
			wrapStr += 
				   "<br><br><br>"
				 + "<div id='more'>"
				 + "	<center>"
				 + "		<a href='javascript:void(0)' id='moreInfo'>更多详细释义"
				 + "			<img src='../images/down.gif' alt='More'>"
				 + "		</a>"
				 + "	</center>"
				 + "</div>"
				 + "<div id='enExtdWordInfoDiv-all' style='display:none'>";
			
			// to create extdWord article by loop
			for (var s = 0; s<enExtdWordArr.length; s ++) {
				
				if (s != 0) {
					if (extdWordId[s] == extdWordId[s - 1]) {
						continue;
					}
				}
				
				wrapStr += 
					   "<article id='enExtdWordInfo" + s + "'>"
					 + "	<header><h4>" + extdWord[s] + "</h4></header>"
					 + "	<hr>";
				
				// to get the count of the same extdWordPtsp which is necessary in loop creation of enExtdWordPtsp div
				var extdWordPtspCnt = 1;
				for (var tmpS = s; tmpS < enExtdWordArr.length; tmpS ++) {
					if (extdWordId[tmpS] != extdWordId[tmpS + 1] || extdWordPtsp[tmpS] != extdWordPtsp[tmpS + 1]) {
						break;
					}
					extdWordPtspCnt ++;
				}
				
				// to create enExtdWordPtsp div by loop
				for ( var i = s; i < s + extdWordPtspCnt; i ++) {
					if (i != s) {
						if (extdWordPtsp[i] == extdWordPtsp[i - 1]) {
							continue;
						}
					}
					wrapStr += 
						   "<div name='extdword-ptsp'>"
						 + "	<h5>" + extdWordPtsp[i] + ".</h5>";
						if (extdWordExtdAttr[i] != null && extdWordExtdAttr[i] != "") {
						wrapStr += 
						   "	<p>" + extdWordExtdAttr[i] + "</p>";
						}
						
						// meaning number for page display
						var k = 1;
						// to create ptsp-mean div by loop
						for ( var j = i; j < i + extdWordPtspCnt; j ++) {
							if (j != i) {
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
							 + "<div id='extdword-ptsp" + i + "-mean" + j + "-exmp-all' style='display:none;margin-left:1em;color:#646464;'>";
							
							// to get the count of the same extdWordMean which is necessary in loop creation of extdword-ptsp-mean-exmp div
							var extdWordMeanCnt = 1;
							for (var tmpJ = j; tmpJ < j + extdWordPtspCnt; tmpJ ++) {
								if (extdWordPtsp[tmpJ] != extdWordPtsp[tmpJ + 1] || extdWordMeanNum[tmpJ] != extdWordMeanNum[tmpJ + 1]) {
									break;
								}
								extdWordMeanCnt++;
							}
							
							// to create extdword-ptsp-mean-exmp div by loop
							for ( var m = j; m < j + extdWordMeanCnt; m ++) {
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
					   "</article>";
			}
			
				wrapStr += 
					// <div id='enExtdWordInfoDiv-all'> end
				   "</div>"
				 + "<div id='hide' style='display:none'>"
				 + "	<center>"
				 + "		<a href='javascript:void(0)' id='hideExtdInfo'>收起"
				 + "			<img src='../images/up.gif' alt='Hide'>"
				 + "		</a>"
				 + "	</center>"
				 + "</div>"
				 + "<center><div id='errMsgDiv' style='color:red;display:none;'>Research After Success</div></center>";
		}
		// to process enExtdWordInfo END
			
		// to append the html string to the page
		$('#allInfo').prepend(wrapStr);
		$('#allInfo').fadeIn("slow");
		$('#outer-line').fadeIn("slow");
		
		// to add onclick event for enWordMeaning
		$("a[id^='ptsp']").click(function() {
			// format of id get here is like "'ptsp' + i + '-mean' + j"
			var id = $(this).attr("id");
			
			// the vary beginning 4 characters of id are "ptsp",this fact will not change,so variable "endIndexOfI" is initialized to 4
			var endIndexOfI = 4;
			// tmpIndex is initialized to 3 to make sure that "if" condition will be true when the first time matches a number,
			// because we know that the first time matches a number will be the time i=4, so i-1=3 should be the value of "tmpIndex"
			var tmpIndex = 3;
			// method $.each() traversing every character in "id"(i:index; n:value).
			$.each( id, function(i, n){
				  if (/\d/.test(n) && tmpIndex == i-1) {
					  endIndexOfI ++;
					  tmpIndex = i;
				  }
				});
			
			var i = id.substring(4,endIndexOfI);
			// "5" is length of '-mean'
			var j = id.substring(endIndexOfI + 5);
			
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
			// format of id get here is like "'extdword-ptsp' + i + '-mean' + j"
			var id = $(this).attr("id");
			
			// the vary beginning 13 characters of id are "extdword-ptsp",this fact will not change,so variable "endIndexOfI" is initialized to 13
			var endIndexOfI = 13;
			// tmpIndex is initialized to 12 to make sure that "if" condition will be true when the first time matches a number,
			// because we know that the first time matches a number will be the time i=13, so i-1=12 should be the value of "tmpIndex"
			var tmpIndex = 12;
			// method $.each() traversing every character in "id"(i:index; n:value).
			$.each( id, function(i, n){
				  if (/\d/.test(n) && tmpIndex == i-1) {
					  endIndexOfI ++;
					  tmpIndex = i;
				  }
				});
			
			var i = id.substring(13,endIndexOfI);
			// "5" is length of '-mean'
			var j = id.substring(endIndexOfI + 5);
			
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
			$("#enExtdWordInfoDiv-all").toggle("normal");
			
			$("#hide").css("display","block");
		});
		
		// to add onclick event for “收起” link
		$("a[id='hideExtdInfo']").click(function() {
			$("#hide").css("display","none");
			
			// to change display attribute of example div
			$("#enExtdWordInfoDiv-all").toggle("normal");
			
			$("#more").css("display","block");
		});
		
	}
	
	/** 
	 * to escape html key word "<" and ">"
	 */
	function htmlEscape(str) {
		var newStr = "";
		$.each(str,function(i,n){
			if (n == "<") {
				newStr += "&lt;";
			} else if (n == ">") {
				newStr += "&gt;";
			} else {
				newStr += n;
			}
		});
		return newStr;
	}
	
	/**
	 * special string filter for meaning
	 */
	function strFilterForMeaning(str) {
		// if the meaning match the condition which is "以 '数字+空格' 开头，后缀任意个字符", delete the number and space;
		/*只要子字符串满足表达式，test()方法就会返回true，所以这里为了限制"以'数字+空格'开头"这个条件，
		 * 要同时加上/\d/.test(str.substring(0,1))这个判断条件
		 */
		if (/\d/.test(str.substring(0,1)) && /\d .*/.test(str)) {
			str = str.substring(str.indexOf(" ") + 1);
		}
		return str;
	}
	
	// Enter Submit
	$("input[id='inputChToEn']").keydown(function(e) {
		var curKey = e.which;
		if (curKey == 13) {
			$("input[id='searchChtoEn']").click();
			return false;
		}
	});
//	function exampleToggle(i, j) {
//		alert("1");
//		$("#ptsp" + i +"-mean" + j + "-exmp-all").toggle();
//	}
});
