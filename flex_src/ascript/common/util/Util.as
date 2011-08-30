package ascript.common.util
{
	import flash.utils.ByteArray;
	
	public class Util
	{
		public function Util()
		{
		}
		
		public static function clone(source:Object):*
		{
			var myBA:ByteArray = new ByteArray();
			myBA.writeObject(source);
			myBA.position = 0;
			return(myBA.readObject());
		}

	}
}