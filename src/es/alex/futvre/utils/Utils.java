package es.alex.futvre.utils;


import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.helpers.Loader;
import org.w3c.dom.Document;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class Utils {

	private static org.apache.log4j.Logger registro;
	
	public static org.apache.log4j.Logger configurarLog(Class nombreClase){
		
		registro = Logger.getLogger(nombreClase);
		URL url = Loader.getResource("log4j.properties");
		PropertyConfigurator.configure(url);
		return registro;
		
	}
	
	
	
	public static Date now() {
		return new Date();
	}
/*
	public static User getCurrentSessionUser() {
		return (User) ((SecurityContext) SecurityContextHolder.getContext())
				.getAuthentication().getPrincipal();
	}
*/
	
	public static String suprimirCaracteresEspeciales(String input) {
	    // Cadena de caracteres original a sustituir.
	    String original = "·‡‰ÈËÎÌÏÔÛÚˆ˙˘uÒ¡¿ƒ…»ÀÕÃœ”“÷⁄Ÿ‹—Á«";
	    // Cadena de caracteres ASCII que reemplazar·n los originales.
	    String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
	    String output = input;
	    for (int i=0; i<original.length(); i++) {
	        // Reemplazamos los caracteres especiales.
	        output = output.replace(original.charAt(i), ascii.charAt(i));
	    }//for i
	   
	    //output=output.replaceAll("&", "y");
	    
	    return output;
	}//remove1
	
	
	
	public static String formatDate(Date date, String format) {
		String result = "";

		if (date != null && format != null) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(format);

				result = sdf.format(date);
			} catch (IllegalArgumentException iae) {
			}
		}

		return result;
	}

	public static Date parseDate(String dateAsString, String format) {
		if (dateAsString == null) {
			return null;
		}
		SimpleDateFormat df = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = df.parse(dateAsString);
		} catch (ParseException e) {
			// log.error("No se pudo obtener una fecha a partir de la cadena " +
			// dateAsString);
		}
		return date;
	}

	public static boolean containsAnyString(String str, String[] srch) {
		boolean result = false;

		for (int i = 0; !result && i < srch.length; i++) {
			result |= str.contains(srch[i]);
		}

		return result;
	}

	public static String concat(String... strs) {
		StringBuilder sb = new StringBuilder();

		for (String str : strs) {
			sb.append(str);
		}

		return sb.toString();
	}

	/**
	 * Devuelve una fecha (date) incrementada en el n y campo indicado<br/>
	 * Para el campo se utiliza: <br/>
	 * - Calendar.MONTH <br/>
	 * - Calendar.YEAR <br/>
	 * - ...
	 * 
	 * @param date
	 *            Fecha a incrementar
	 * @param field
	 *            Campo a incrementar
	 * @param amount
	 *            Cantidad a incrementar
	 * @return
	 */
	public static Date increaseDate(Date date, int field, int amount) {
		if (date == null || field == 0) {
			return date;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(field, amount);

		return cal.getTime();
	}

	/**
	 * Devuelve la posicion en la que se encuentra la subcadena que se recibe
	 * como parametro, dentro de la cadena, ignorando las comas. El formato de
	 * la cadena debe ser valor,valor,valor... Se devolvera -1 si la subcadena
	 * no se encuentra en la cadena.
	 * 
	 * @param cad
	 *            La cadena en la que se buscara la subcadena.
	 * @param subCad
	 *            La subcadena que estamos buscando.
	 * @return Integer La posicion en la que se encuentra la subcadena dentro de
	 *         la cadena.
	 */
	public static Integer indexOf(String cad, String subCad) {
		Integer index = -1;
		boolean encontrado = false;
		if (cad != null && subCad != null && !"".equals(cad)) {
			String[] subCadenas = cad.split(",");
			for (int i = 0; i < subCadenas.length && !encontrado; i++) {
				if (subCadenas[i].equals(subCad)) {
					encontrado = true;
					index = i;
				}
			}
		}

		return index;
	}


	/**
	 * Devuelve el valor de una propiedad del fichero de constantes de la BD o
	 * nulo en caso de que no exista dicha propiedad
	 * 
	 * @param property
	 * @return String el valor de la propiedad recibida como parametro
	 */
	public static String getConstante(String property) {
		if (property != null) {
			ResourceBundle res;
			try {
				res = ResourceBundle.getBundle("constants_db");
				return res.getString(property);
			} catch (Exception e) {
				return null;
			}

		}
		return null;
	}

	/**
	 * Metodo que genera una contrasenya hexadecimal de la longitud indicada
	 * 
	 * @param longitud
	 * @return
	 */
	public static String generaContrasenya(int longitud) {
		Random rng = new Random();
		String resultado = "";
		int caracterValue;
		for (int i = 0; i < longitud; i++) {
			caracterValue = rng.nextInt(16);
			resultado += Integer.toHexString(caracterValue).toString()
					.toUpperCase();
		}
		return resultado;
	}
	
	
	public static String getStringResource (String key){
		ResourceBundle rb = ResourceBundle.getBundle("MessageResource");
		String valor = rb.getString(key);
		return valor;
	}

	
	/**
	   * Read XML as DOM.
	   */
	  public static Document readXml(InputStream is) throws SAXException, IOException,
	      ParserConfigurationException {
	      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

	      dbf.setValidating(false);
	      dbf.setIgnoringComments(false);
	      dbf.setIgnoringElementContentWhitespace(true);
	      dbf.setNamespaceAware(true);
	      // dbf.setCoalescing(true);
	      // dbf.setExpandEntityReferences(true);

	      DocumentBuilder db = null;
	      db = dbf.newDocumentBuilder();
	      db.setEntityResolver(new NullResolver());

	      // db.setErrorHandler( new MyErrorHandler());

	      return db.parse(is);
	  }
	  
	  public static int getNumeroAleatorioEntre1YParam(int max){
		  int valorDado = (int) Math.floor(Math.random()*max+1);
		  return valorDado;
	  }
	  
}



class NullResolver implements EntityResolver {
  public InputSource resolveEntity(String publicId, String systemId) throws SAXException,
      IOException {
    return new InputSource(new StringReader(""));
  }
}
