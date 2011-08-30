package ascript.hibernate.beans
{
	[Bindable]
    [RemoteClass(alias="com.passing.hibernate.beans.Jpwordexample")]
	public class Jpwordexample
	{
		public var id:Number;
		public var jpwordmeaning:Jpwordmeaning;
		public var example:String;
		public var examplemeaning:String;
	
		public function Jpwordexample()
		{
		}

	}
}