package ascript.hibernate.beans
{
	[Bindable]
    [RemoteClass(alias="com.passing.hibernate.beans.Jpwordremark")]
	public class Jpwordremark
	{
		public var id:Number;
		public var jpword:Jpword;
		public var remark:String;
	
		public function Jpwordremark()
		{
		}

	}
}