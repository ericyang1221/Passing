// ActionScript file
import flash.events.MouseEvent;

import mx.containers.ApplicationControlBar;
import mx.containers.Canvas;
import mx.containers.HBox;
import mx.containers.VBox;
import mx.containers.VDividedBox;
import mx.controls.Button;
import mx.controls.Label;
import mx.controls.Spacer;
import mx.controls.TextArea;
import mx.events.FlexEvent;

import ascript.common.effects.Effects;


private var body:Test = Test (this);
protected function creChatWindow(obj:Object):void
{
	var check:ApplicationControlBar = ApplicationControlBar(body.getChildByName("chatBox_"+obj.name));
	
	if(!check){
		var winBox:Canvas = new Canvas();
		var winCtlBar:ApplicationControlBar = new ApplicationControlBar();
		var midCtlBar:ApplicationControlBar = new ApplicationControlBar();
		var vBox:VBox = new VBox();
		var vBox2:VBox = new VBox();
		var headHBox:HBox = new HBox();
		var botHBox:HBox = new HBox();
		var titleLbl:Label = new Label();
		var vDividedBox:VDividedBox = new VDividedBox();
		var msgArea:TextArea = new TextArea();
		var inputArea:TextArea = new TextArea();
		var msgCanvas:Canvas = new Canvas();
		var inputCanvas:Canvas = new Canvas();
		var closeButton:Button = new Button();
		var sendButton:Button = new Button();
		var botHBoxSpacer:Spacer = new Spacer();

		winBox.addEventListener(MouseEvent.MOUSE_DOWN,function():void{Effects.toTopFloor(winBox);});
		winCtlBar.addEventListener(FlexEvent.ADD,function():void{Effects.doZoomIn(winCtlBar);});
		headHBox.addEventListener(FlexEvent.ADD,function():void{Effects.doDrag(headHBox,winBox);});
		
		body.addChild(winBox);
		winBox.addChild(winCtlBar);
		winCtlBar.addChild(vBox);
		vBox.addChild(headHBox);
		vBox.addChild(vDividedBox);
		vBox.addChild(botHBox);
		vDividedBox.addChild(msgCanvas);
		vDividedBox.addChild(inputCanvas);
		msgCanvas.addChild(vBox2);
		vBox2.addChild(msgArea);
		vBox2.addChild(midCtlBar);
		inputCanvas.addChild(inputArea);
		headHBox.addChild(titleLbl);
		botHBox.addChild(botHBoxSpacer);
		botHBox.addChild(closeButton);
		botHBox.addChild(sendButton);
		
		winBox.setStyle("left",100);
		winBox.setStyle("top",100);
		winCtlBar.setStyle("horizontalCenter",0);
		winCtlBar.setStyle("verticalCenter",0);
		winBox.width = 300;
		winBox.height = 400;
		headHBox.height = 30;
		headHBox.percentWidth = 100;
		winCtlBar.width = 300;
		winCtlBar.height = 400;
		vBox.percentHeight = 100;
		vBox.percentWidth = 100;
		vDividedBox.percentHeight = 100;
		vDividedBox.percentWidth = 100;
		msgCanvas.percentHeight = 70;
		msgCanvas.percentWidth = 100;
		inputCanvas.percentHeight = 30;
		inputCanvas.percentWidth = 100;
		vBox2.percentHeight = 100;
		vBox2.percentWidth = 100;
		msgArea.percentHeight = 100;
		msgArea.percentWidth = 100;
		midCtlBar.percentWidth = 100;
		midCtlBar.height = 25;
		inputArea.percentHeight = 100;
		inputArea.percentWidth = 100;
		botHBox.percentWidth = 100;
		botHBoxSpacer.percentWidth = 75;
		msgCanvas.minHeight = 120;
		inputCanvas.minHeight = 50;
		
//		winCtlBar.opaqueBackground = 0x000000;
		msgArea.editable = false;
		winCtlBar.name = "chatWin_"+obj.name;
		winBox.name = "chatBox_"+obj.name;
		titleLbl.setStyle("fontFamily","微软雅黑");
		titleLbl.text = obj.label;
		closeButton.label = "关闭";
		sendButton.label = "发送";
		 
		closeButton.addEventListener(MouseEvent.CLICK,function():void{doZoomOut(winCtlBar);});
		
		
	}
}
