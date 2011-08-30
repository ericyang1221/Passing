import ascript.common.util.Stack;

import mx.collections.ArrayCollection;
import mx.controls.Alert;


private var backStack:Stack = new Stack();
private var forwardStack:Stack = new Stack();

private function doPush(stack:Stack,flag:int):void{
	var tmpPushArr:ArrayCollection = new ArrayCollection();
	tmpPushArr.addItem(flag);
	tmpPushArr.addItem(searchResultPanel.getJpwordList());
	stack.push(tmpPushArr);
}

private function doBack():void{
	try{
		if(backStack.size() < 1){
			Alert.show(backStack.size().toString());
			return;
		}
		forwardStack.push(searchResultPanel.getJpwordList());
		var tmpPopArr:ArrayCollection = backStack.pop() as ArrayCollection;
		if(0 == tmpPopArr.getItemAt(0) as int){
			this.createSearchResultDivByJpToCn(tmpPopArr.getItemAt(1) as ArrayCollection,true);
		}else if(1 == tmpPopArr.getItemAt(0) as int){
			this.createNoResultDiv();
			this.createAssociateDiv(tmpPopArr.getItemAt(1) as ArrayCollection,true);
		}
	}catch(error:Error){
		navigateToURL(new URLRequest('http://localhost:8080/'),'_blank');
	}
}

private function doForward():void{
	try{
		if(forwardStack.size() < 1){
			Alert.show(forwardStack.size().toString());
			return;
		}
		backStack.push(searchResultPanel.getJpwordList());
		var tmpPopArr:ArrayCollection = forwardStack.pop() as ArrayCollection;
		if(0 == tmpPopArr.getItemAt(0) as int){
			this.createSearchResultDivByJpToCn(tmpPopArr.getItemAt(1) as ArrayCollection,true);
		}else if(1 == tmpPopArr.getItemAt(0) as int){
			this.createNoResultDiv();
			this.createAssociateDiv(tmpPopArr.getItemAt(1) as ArrayCollection,true);
		}
	}catch(error:Error){
		navigateToURL(new URLRequest('http://localhost:8080/'),'_blank');
	}
}

/**
 * @param	isForwardOrBack		Marks the flag if do the push action.
 * */
public function createSearchResultDivByJpToCn(jpwordList:ArrayCollection,isForwardOrBack:Boolean):void{
	if(!isForwardOrBack){
		backStack.push(searchResultPanel.getJpwordList());
	}
	searchResultPanel.createSearchResultDivByJpToCn(jpwordList);
}

public function createSearchResultDivByCnToJp(jpwordList:ArrayCollection):void{
	
}

public function createNoResultDiv():void{
	searchResultPanel.createNoResultDiv();
}

/**
 * @param	isForwardOrBack		Marks the flag if do the push action.
 * */
public function createAssociateDiv(jpwordList:ArrayCollection,isForwardOrBack:Boolean):void{
	if(!isForwardOrBack){
		backStack.push(searchResultPanel.getJpwordList());
	}
	searchResultPanel.createAssociateDiv(jpwordList);
}

private function updateButtonState():void{
	if(backStack.size()<1){
		backbtn.enabled = false;
		backbtn.buttonMode = false;
	}else{
		backbtn.enabled = true;
		backbtn.buttonMode = true;
	}
	if(forwardStack.size()<1){
		forwardbtn.enabled = false;
		forwardbtn.buttonMode = false;
	}else{
		forwardbtn.enabled = true;
		forwardbtn.buttonMode = true;
	}
}