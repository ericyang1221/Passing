package ascript.common.consts
{
	/**consts of Login
	 * 
	 */
	public class LoginConsts
	{
		public static const VALIDMSG_NAME_REQUIRED:String = "用户名必填";
		public static const VALIDMSG_PWD_REQUIRED:String = "密码必填";
		public function LoginConsts()
		{
			super();
         	throw new Error("CANNOT CREATE INSTANCE");
		}

	}
}