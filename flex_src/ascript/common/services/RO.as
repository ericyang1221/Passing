package ascript.common.services
{
	import ascript.common.util.StrTrim;
	
	import mx.controls.Alert;
	import mx.managers.CursorManager;
	import mx.messaging.ChannelSet;
	import mx.messaging.channels.AMFChannel;
	import mx.messaging.messages.ErrorMessage;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.Operation;
	import mx.rpc.remoting.RemoteObject;

	public class RO
	{
		private static var ro:RemoteObject = new RemoteObject();
		
		public function RO()
		{
			super();
		}
		
		private static function getRemoteObject(destination:String):RemoteObject{
			ro.destination = destination;
//			searchWord.endpoint = "/Passing/messagebroker/amf";
			var cs:ChannelSet = new ChannelSet();
			cs.addChannel(new AMFChannel("myAMF","http://localhost:8080/Passing/messagebroker/amf"));
			ro.channelSet = cs;
			return ro;
		}
		
		public static function sendRequest(destination:String,method:String,para:String,processResultFunc:Function):void{
//			Alert.show(StrTrim.rTrim(searchStr).length.toString());
			if(para == null){
				return;
			}
			if(StrTrim.rTrim(para).length < 1 || StrTrim.rTrim(method).length < 1){
				return;
			}
			var o:Operation = getRemoteObject(destination).getOperation(method) as Operation;
			o.addEventListener(ResultEvent.RESULT, processResultFunc);
			o.addEventListener(FaultEvent.FAULT,faultHandler);
			o.send(para);
			CursorManager.setBusyCursor();
		}

		private static function faultHandler(event:FaultEvent):void {
			throw new Error(event.fault.faultString);
//			Alert.show(event.fault.faultString);
		}
	}
}


//<mx:RemoteObject id="SearchWord" destination="SearchWord"
//		endpoint="/Passing/messagebroker/amf"
//		result="resultHandler(event);" fault="faultHandler(event);" />