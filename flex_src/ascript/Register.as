// ActionScript file
import ascript.common.consts.RegisterConsts;
import ascript.common.services.RO;
import user_components.RegisterDiv;

import flash.events.Event;

import mx.controls.Alert;
import mx.managers.CursorManager;
import mx.rpc.events.ResultEvent;
import mx.validators.Validator;

private var validatorArr:Array;
private var isNotExist:Boolean = true;
private var isEnable:Boolean;
private var previousName:String;

protected var problems:Array = [ {label:"备选问题"},{label:"我的小学"}, {label:"我的恩师"}, 
	{label:"身份证后四位数字"}, {label:"喜欢的人"}, {label:"母亲的生日"}, 
	{label:"父亲的生日"} ];
protected function clear():void
{
	if(view.myProblem.text == "自定义问题"){
		myProblem.text = "";
	}
}
protected function selected(event:Event):void
{
	if(ComboBox(event.target).selectedIndex != 0){
		myProblem.text = ComboBox(event.target).selectedItem.label;
	}
}
protected function reset(event:Event):void
{
	view.txtRegName.text = "";
	view.txtRegPwd.text = "";
	view.txtCfmPwd.text = "";
	view.txtRegNick.text = "";
	view.txtRegEmail.text = "";
	view.sex.selectedValue = 1;
	view.problem.selectedIndex = 0;
	view.myProblem.text = "自定义问题";
	view.answer.text = "";
	
}
private function init():void
{
	validatorArr = new Array();
	
	validatorArr.push(view.regName_StringValidator);
	validatorArr.push(view.regPwd_StringValidator);
	validatorArr.push(view.pwdCfm_StringValidator);
	validatorArr.push(view.regNick_StringValidator);
	validatorArr.push(view.regEmail_EmailValidator);
	validatorArr.push(view.myProblem_StringValidator);
	validatorArr.push(view.answer_StringValidator);
}
private function validateForm():Boolean
{
	var validatorErrorArr:Array = Validator.validateAll(validatorArr);
	if(validatorErrorArr.length == 0){
		return true;
	} else {
		return false;
	}
}
private function registerUser():void
{
	if(view.myProblem.text != "自定义问题" && view.myProblem.text != "" && view.answer.text == ""){
		Alert.show(RegisterConsts.REG_VALIDMSG_ANSWER_EMPTY);
	} else if ((view.myProblem.text == "自定义问题" || view.myProblem.text == "") && view.answer.text != ""){
		Alert.show(RegisterConsts.REG_VALIDMSG_MYPROBLEM_EMPTY);
	} else {
		if(validateForm() && isNotExist){
			
			var userInfo:String = view.txtRegName.text + " " + view.txtRegPwd.text + " " 
									+ view.txtRegNick.text + " " + view.txtRegEmail.text + " " 
									+ view.sex.selectedValue;
			RO.sendRequest("register","register",userInfo,registerResultHandler);
		}
		else if(validateForm() && !isNotExist){
			Alert.show("该用户已存在");
			isNotExist = true;
		}
		else{
			Alert.show("请确认必填选项已正确输入");
		}
	}
}
private function recordUserName():void
{
	previousName = view.txtRegName.text;
}
private function validateUser():void
{
	if(view.txtRegName.text == "" && view.isUsed.text != ""){
		view.isUsed.text = "";
	}
	if(view.txtRegName.text != "" && view.txtRegName.text != previousName){
		view.isUsed.text = "";
		RO.sendRequest("register","isUserExist",view.txtRegName.text,validateResultHandler);
	}
}
private function validateResultHandler(event:ResultEvent):void
{
	CursorManager.removeBusyCursor();
	var isExist:Boolean = event.result as Boolean;
	if(isExist){
		isNotExist = false;
		view.isUsed.text = "用户名已被注册";
		view.isUsed.setStyle("color","red");
	} else {
		isNotExist = true;
		view.isUsed.text = "用户名可用";
		view.isUsed.setStyle("color","green");
	}
}
private function registerResultHandler(event:ResultEvent):void
{
	if(event.result.toString() == "注册成功"){
		navigateToURL(new URLRequest('http://localhost:8080/Passing/flex/Passing.html'),'_self');
	} else {
		Alert.show(event.result.toString());
	}
}

private function get view():RegisterDiv
{
	return super.document as RegisterDiv;
}



