import flash.events.MouseEvent;
import mx.collections.ArrayCollection;
import mx.events.IndexChangedEvent;
import mx.rpc.events.ResultEvent;

[bindable]
private var linkData:Array = ["Flash", "Director", "Dreamweaver", "ColdFusion"];

public function searchTabFade():void{
	searchNav.addEventListener(IndexChangedEvent.CHANGE,function(event:IndexChangedEvent):void{tabFade.play([event.target],false);});
}

public function glowButton(event:MouseEvent):void{
	if(buttonGlow.isPlaying && event.target == buttonGlow.target){
		buttonGlow.reverse();
	}else{
		buttonGlow.play([event.target],event.type == MouseEvent.ROLL_OUT ? true : false);
	}
}

private function processResultFunc(event:ResultEvent):void{
//	var jpwordList:ArrayCollection = event.result as ArrayCollection;
//	this.parentApplication["searchResultDiv"].createSearchResultDiv(jpwordList);
}