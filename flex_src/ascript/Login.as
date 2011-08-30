// ActionScript file
import ascript.common.services.RO;

import mx.controls.Alert;
import mx.managers.CursorManager;
import mx.rpc.events.ResultEvent;
import mx.validators.Validator;

//declaration of global variable
private var validatorArr:Array;


/**init method
 * initialize of validatorArr
 */
private function init():void
{
	validatorArr = new Array();
	
	validatorArr.push(view.pwd_stringValidator);
	validatorArr.push(view.username_stringValidator);
	
	view.nameTxt.setFocus();
}

/**validateForm method
 * judge if the form is valid
 * 
 * @return Boolean true:valid; false:invalid
 */
private function validateForm():Boolean
{
	var validatorErrorArr:Array = Validator.validateAll(validatorArr);
	if(validatorErrorArr.length == 0){
		return true;
	} else {
		return false;
	}
}

/**reset method */
private function reset():void
{
	view.nameTxt.text = "";
	view.pwdTxt.text = "";
}

/**submit method */
private function submit():void
{
	if(validateForm()){
		var userInfo:String = view.nameTxt.text + " " + view.pwdTxt.text;
			RO.sendRequest("login","login",userInfo,loginResultHandler);
	} else {
		Alert.show("请确认必填选项已正确输入");
	}
}

/**loginResultHandler method
 * 
 * @param event resultevent of login
 */
private function loginResultHandler(event:ResultEvent):void
{
	CursorManager.removeBusyCursor();
	var result:String = event.result as String;
	if(result == "登陆成功"){
		navigateToURL(new URLRequest('http://localhost:8080/Passing/flex/Passing.html'),'_self');
	} else {
		Alert.show(result);
		if(result == "用户不存在"){
			view.nameTxt.setFocus();
			view.nameTxt.text = "";
		} else {
			view.pwdTxt.setFocus();
			view.pwdTxt.text = "";
		}
	}
}

/**findBackPwd() method
 * event when 找回密码 button is clicked
 */
private function findBackPwd():void
{
	navigateToURL(new URLRequest('http://localhost:8080/Passing/flex/FindBackPwd.html'),'_self');
}

/**register() method
 * event when 注册用户 button is clicked
 */
private function register():void
{
	navigateToURL(new URLRequest('http://localhost:8080/Passing/flex/Register.html'),'_self');
}

private function get view():LoginDiv
{
	return super.document as LoginDiv;
}