package es.alex.futvre.tests;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class Encode {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			System.out.println(URLDecoder.decode("Pablo+AlborA!n","UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
