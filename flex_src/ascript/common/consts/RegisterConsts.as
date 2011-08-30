package ascript.common.consts
{
	/**consts of register
	 * 
	 */
	public class RegisterConsts
	{
		public static const REG_VALIDMSG_NAME_TOLONGERROR:String = "用户名最多20位";
		public static const REG_VALIDMSG_NAME_REQUIREDFIELDERROR:String = "用户名必填";
		public static const REG_VALIDMSG_NAME_NETWORKERROR:String = "网络异常，请重新注册";
		public static const REG_VALIDMSG_NAME_USEREXISTERROR:String = "该用户已存在，请更改注册用户名";
		public static const REG_VALIDMSG_PWD_LENGTHERROR:String = "密码必须是6-20位英数字";
		public static const REG_VALIDMSG_PWD_REQUIREDFIELDERROR:String = "密码必填";
		public static const REG_VALIDMSG_PWDCFM_REQUIREDFEILDERROR:String = "密码确认必填";
		public static const REG_VALIDMSG_PWDCFM_DIFFERENT:String = "两次输入的密码不一致";
		public static const REG_VALIDMSG_EMAIL_REQUIREDFEILDERROR:String = "邮箱必填";
		public static const REG_VALIDMSG_EMAIL_INVALIDCHARERROR:String = "邮件地址中存在无效字符";
		public static const REG_VALIDMSG_EMAIL_INVALIDDOMAINERROR:String = "后缀的长度必须是 2、3、4 或 6 个字符";
		public static const REG_VALIDMSG_EMAIL_INVALIDIPDOMAINERROR:String = "IP 域无效";
		public static const REG_VALIDMSG_EMAIL_INVALIDPERIODSINDOMAINERROR:String = "地址中不能含连续的 . 符号";
		public static const REG_VALIDMSG_EMAIL_MISSINGATSIGNERROR:String = "邮件地址中需含有@ 符号";
		public static const REG_VALIDMSG_EMAIL_MISSINGPERIODINDOMAINERROR:String = "地址中需含有 . 符号";
		public static const REG_VALIDMSG_EMAIL_TOOMANYATSIGNERROR:String = "地址中只能含有1个 @ 符号";
		public static const REG_VALIDMSG_MYPROBLEM_EMPTY:String = "请填写答案所对应的密码提示问题";
		public static const REG_VALIDMSG_ANSWER_EMPTY:String = "请填写密码提示问题的答案";
		
		public function RegisterConsts()
		{
			super();
         	throw new Error("CANNOT CREATE INSTANCE");
		}
	}
}