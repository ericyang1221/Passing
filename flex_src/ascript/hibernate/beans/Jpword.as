package ascript.hibernate.beans
{
	import mx.collections.ArrayCollection;
	
	[Bindable]
    [RemoteClass(alias="com.passing.hibernate.beans.Jpword")]
	public class Jpword
	{
		public var id:Number;
		public var kana:String;
		public var word:String;
		public var jpwordmeanings:ArrayCollection;
		public var jpwordremarks:ArrayCollection;
		
		public function Jpword()
		{
		}

	}
}