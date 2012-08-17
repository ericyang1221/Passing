package test;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

public class MSTranslateTest {
	public static void main(String[] args) throws Exception{
		
		//message to be translated
		String str = "服务器硬盘软件数据库程序设计";
		
		
		String method = "Translate";
		Service service = new Service();
		Call call = (Call) service.createCall();
		call.setTargetEndpointAddress(new java.net.URL("http://api.microsofttranslator.com/V2/Soap.svc"));
		call.setOperationName(new QName("http://api.microsofttranslator.com/V2",method));
		
		call.addParameter(new QName("http://api.microsofttranslator.com/V2","appId"), org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN); 
		call.addParameter(new QName("http://api.microsofttranslator.com/V2","text"), org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN); 
		call.addParameter(new QName("http://api.microsofttranslator.com/V2","from"), org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN); 
		call.addParameter(new QName("http://api.microsofttranslator.com/V2","to"), org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN); 
		 
		 
		call.setUseSOAPAction(true);
		call.setReturnType(org.apache.axis.encoding.XMLType.SOAP_STRING);
		call.setSOAPActionURI("http://api.microsofttranslator.com/V2/LanguageService/Translate");
		
		String result = (String)call.invoke(new Object[]{"YOUR APPID HERE",str,"zh-CHS","zh-CHT"});
		
		//result
		System.out.println(result);
	}
}


