package ascript.common.validator
{
	import mx.messaging.ChannelSet;
	import mx.messaging.channels.AMFChannel;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.Operation;
	import mx.rpc.remoting.RemoteObject;
	import mx.validators.StringValidator;
	import mx.validators.ValidationResult;
	import mx.controls.Alert;
	
	public class UserNameValidator extends StringValidator
	{
		public function UserNameValidator()
		{
			super();
		}
		
		private var _userExistError:String;
		public function set userExistError(str:String):void
		{
			this._userExistError = str;
		}
		public function get userExistError():String
		{
			return _userExistError;
		}
		
		private var _netWorkError:String;
		public function set netWorkError(str:String):void
		{
			this._netWorkError = str;
		}
		public function get netWorkError():String
		{
			return _netWorkError;
		}
		
		private var results:Array;
		override protected function doValidation(value:Object):Array
		{
			// Convert value to a Number.
            var inputValue:String = String(value);
			// Clear results Array.
            results = [];
            // Call base class doValidation().
            results = super.doValidation(value);
            //return if there are errors
            if(results.length > 0){
            	return results;
            }
            isExistValidate(inputValue);

            return results;
		}
		private function isExistValidate(username:String):void
		{
			
			var ope:Operation = getRemoteObject().getOperation("isUserExist") as Operation;
			ope.addEventListener(ResultEvent.RESULT,validateResultHandler);
			ope.addEventListener(FaultEvent.FAULT,faultHandler);
			ope.send(username);
		}
		private var isResponsed:Boolean = false;
		private function validateResultHandler(event:ResultEvent):void
		{
			var rst:Boolean = event.result as Boolean;
			Alert.show("rst="+rst.toString());
			if(rst){
				results.push(new ValidationResult(true,null,"userExist",userExistError));
				isResponsed = true;
				Alert.show("isResponsed_in="+isResponsed.toString());
			}
			isResponsed = true;
		}
		private function faultHandler(event:FaultEvent):void
		{
			results.push(new ValidationResult(true,null,"netWorkError",netWorkError));
			isResponsed = true;
		}
		
		private static var remoteObj:RemoteObject = new RemoteObject();
		private static function getRemoteObject():RemoteObject
		{
			remoteObj.destination = "register";
			var cs:ChannelSet = new ChannelSet();
			cs.addChannel(new AMFChannel("my-amf","http://localhost:8080/Passing/messagebroker/amf"));
			remoteObj.channelSet = cs;
			return remoteObj;
		}
	}
}