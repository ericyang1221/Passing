package ascript.common.util
{
	import flash.errors.IllegalOperationError;
	import mx.controls.Alert;
	
	public class StrTrim
	{
		public static function rTrim(str:String):String
		{
			if(str == null){
				throw new IllegalOperationError("Illeagl arguement.");
			}
			while(str.charAt(str.length-1) == ' '){
				str = str.substring(0,str.length-1);
			}
			return str;
		}

	}
}