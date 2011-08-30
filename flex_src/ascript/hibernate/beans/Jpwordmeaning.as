package ascript.hibernate.beans
{
	import mx.collections.ArrayCollection;
	
	[Bindable]
    [RemoteClass(alias="com.passing.hibernate.beans.Jpwordmeaning")]
	public class Jpwordmeaning
	{
		public var id:Number;
		public var jpword:Jpword;
		public var meaning:String;
		public var partofspeech:String;
		public var jpwordexamples:ArrayCollection;
	
		public function Jpwordmeaning()
		{
		}

	}
}