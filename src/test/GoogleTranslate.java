package test;

import com.google.api.GoogleAPI;
import com.google.api.translate.Language;
import com.google.api.translate.Translate;

public class GoogleTranslate {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// Set the HTTP referrer to your website address.
	    GoogleAPI.setHttpReferrer("http://172.26.176.42:8180/passing/html/search.html");

	    // Set the Google Translate API key
	    // See: http://code.google.com/apis/language/translate/v2/getting_started.html
	    GoogleAPI.setKey("AIzaSyAPFtr4ZmXRvVe5XPWyqV3NnXfif0kLz8M");

	    String translatedText = Translate.DEFAULT.execute("Bonjour le monde", Language.FRENCH, Language.ENGLISH);

	    System.out.println(translatedText);

	}

}
