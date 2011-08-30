import mx.containers.Panel;
import mx.events.EffectEvent;
import mx.events.FlexEvent;

public function myApp():void{
	createMyAppDiv("left",true,"我的应用程序",400);
}

public function myFriends():void{
	createMyAppDiv("right",false,"我的好友",250);
}

public function createMyAppDiv(name:String,posLorR:Boolean,title:String,width:int):void{
	var body:Object = Object(this.parent);
	var tmpDiv:Panel = Panel(body.getChildByName(name));
	if(!tmpDiv){
		var myAppDiv:Panel = new Panel();
		myAppDiv.name = name;
		myAppDiv.title = title;
		myAppDiv.width = width;
		myAppDiv.minHeight = 100;
		myAppDiv.maxHeight = 500;
		myAppDiv.setStyle("bottom",40);
		if(posLorR == true){
			myAppDiv.setStyle("left",40);
		}else{
			myAppDiv.setStyle("right",40);
		}
		myAppDiv.setStyle("titleStyleName","myAppTitle");
		myAppDiv.addEventListener(FlexEvent.ADD,function():void{myAppZoom.play([myAppDiv],false)});
		body.addChild(myAppDiv);
	}else{
		myAppZoom.play([tmpDiv],true);
		myAppZoom.addEventListener(EffectEvent.EFFECT_END,function():void{
			if(body.contains(tmpDiv)){
				body.removeChild(tmpDiv);
			}
		});
	}
}

public function buttonZoom(event:MouseEvent):void{
	if(zoomButton.isPlaying && event.target == zoomButton.target){
		zoomButton.reverse();
	}else{
		zoomButton.play([event.target],event.type == MouseEvent.ROLL_OUT ? true : false);
	}
}
