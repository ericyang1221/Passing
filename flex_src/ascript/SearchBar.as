import ascript.common.effects.Effects;
import ascript.common.services.RO;
import mx.controls.Alert;
import mx.collections.ArrayCollection;
import mx.events.ItemClickEvent;
import mx.managers.CursorManager;
import mx.rpc.events.ResultEvent;

private function changeSearchType(event:ItemClickEvent):void{
	if(searchViewStack){
		var grammerArr:Array = new Array(searchViewStack);
		Effects.doFadeIn(grammerArr);
	}
}

private function processJpToCnResultFunc(event:ResultEvent):void{
	CursorManager.removeBusyCursor();
	var jpwordList:ArrayCollection = event.result as ArrayCollection;
	if(jpwordList.length > 0){
		try{
			this.parentApplication["searchResultDiv"].createSearchResultDivByJpToCn(jpwordList,false);
		}catch(error:Error){
			navigateToURL(new URLRequest('http://localhost:8080/'),'_blank');
		}
	}else{
		try{
			this.parentApplication["searchResultDiv"].createNoResultDiv();
		}catch(error:Error){
			navigateToURL(new URLRequest('http://localhost:8080/'),'_blank');
		}
		RO.sendRequest("SearchJpToCn","doJpToCn_like",searchWordStr.text,processAssociateResult);
	}
}

private function processAssociateResult(event:ResultEvent):void{
	CursorManager.removeBusyCursor();
	var jpwordList:ArrayCollection = event.result as ArrayCollection;
	if(jpwordList.length > 0){
		this.parentApplication["searchResultDiv"].createAssociateDiv(jpwordList,false);
	}
	
}

private function processCnToJpResultFunc(event:ResultEvent):void{
	CursorManager.removeBusyCursor();
	var jpwordMeaningList:ArrayCollection = event.result as ArrayCollection;
	Alert.show(jpwordMeaningList.length.toString());
}

///* Use the BrowserManager to control the application to back and forward */
//private var bm:IBrowserManager
//public function onCreationComplete():void{
//	bm = BrowserManager.getInstance();
//	bm.init();
//	bm.addEventListener(BrowserChangeEvent.URL_CHANGE,onURLChange);
//}
//
//public function onURLChange(event:BrowserChangeEvent):void{
//	var o:Object = URLUtil.stringToObject(bm.fragment);
//	if(o.searchWordStr == null){
//		this.parentApplication["searchResultDiv"].createSearchResultDivByJpToCn(new ArrayCollection());
//		searchWordStr.text = "";
//		return;
//	}
//	searchWordStr.text = o.searchWordStr;
//	RO.sendRequest('SearchJpToCn','doJpToCn',o.searchWordStr,processResultFunc);
//}
//
//private function updateURL():void{
//	bm.setFragment("searchWordStr="+searchWordStr.text);
//}
