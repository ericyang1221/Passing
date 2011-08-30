package ascript.common.validator
{
	import mx.validators.StringValidator;
	import mx.validators.ValidationResult;
	
	public class PwdCfmValidator extends StringValidator
	{
		//constructor
		public function PwdCfmValidator()
		{
			super();
		}
		
		//set password which is used to compare with
		private var _pwdUsedToCompare:String;
		public function set pwdUsedToCompare(str:String):void
		{
			this._pwdUsedToCompare = str;
		}
		public function get pwdUsedToCompare():String
		{
			return _pwdUsedToCompare;
		}
		//password different error message
		private var _differentError:String;
		public function set differentError(str:String):void
		{
			this._differentError = str;
		}
		public function get differentError():String
		{
			return _differentError;
		}		
		//override the doValidation() method
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
            if(inputValue != _pwdUsedToCompare){
            	results.push(new ValidationResult(true,null,"different",differentError));
            	return results;
            }
            return results;
		}
	}
}