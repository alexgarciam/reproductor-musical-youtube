package es.alex.futvre.form;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.LazyValidatorForm;
//import org.apache.log4j.Logger;

import es.alex.futvre.utils.Utils;

public class FutvreForm extends LazyValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8262799848508560407L;
	/**
	 * Log
	 */
//	private static org.apache.log4j.Logger log;
	
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	/**
     * Reset all properties to their default values.
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {
    	this.type=null;
    }

    /**
     * Reset all properties to their default values.
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
   * @return errors
     */
  public ActionErrors validate( 
      ActionMapping mapping, HttpServletRequest request ) {
      ActionErrors errors = new ActionErrors();
     
      return errors;
  }
  
  /**
	 * Devuelve un valor como String
	 *
	 * @param paramName Nombre del parámetro a obtener
	 * @return Devuelve un String con el valor del parámetro indicado
	 */
	public String getAsString( String paramName ) {
		String stringValue = null;

	    Object valueAsObject = get( paramName );

	    if( valueAsObject != null ) {
	    	stringValue = valueAsObject.toString().trim();
	    }

	    return stringValue;
	}

	/**
	 * Obtiene un número de tipo Short
	 *
	 * @param paramName Nombre del parámetro a obtener
	 * @return Devuelve un Short con el valor del parámetro indicado
	 */
	public Short getAsShort( String paramName ) {
	    Short shortValue = null;

	    String valueAsString = getAsString( paramName );

	    if( valueAsString != null ) {
	    	try {
		    	shortValue = new Short( valueAsString );
		    } catch( NumberFormatException e ) {
//		    	log.debug( Utils.concat( "Error al obtener el parametro ", paramName, " como un objeto de tipo Short desde el valor ", valueAsString ) );
		    }
	    }

	    return shortValue;
	}

	/**
	 * Obtiene un número de tipo Integer
	 *
	 * @param paramName Nombre del parámetro a obtener
	 * @return Devuelve un Integer con el valor del parámetro indicado
	 */
	public Integer getAsInteger( String paramName ) {
	    Integer integerValue = null;

	    String valueAsString = getAsString( paramName );

	    if( valueAsString != null ) {
	    	try {
	    		integerValue = new Integer( valueAsString );
  	    } catch( NumberFormatException e ) {
//  	    	log.debug( Utils.concat( "Error al obtener el parametro ", paramName, " como un objeto de tipo Integer desde el valor ", valueAsString ) );
  	  	}
	    }

	    return integerValue;
	}

	/**
	 * Obtiene un número de tipo Long
	 *
	 * @param paramName Nombre del parámetro a obtener
	 * @return Devuelve un Long con el valor del parámetro indicado
	 */
	public Long getAsLong( String paramName ) {
	    Long longValue = null;

	    String valueAsString = getAsString( paramName );

	    if( valueAsString != null ) {
	    	try {
	    		longValue = new Long( valueAsString );
  	    } catch( NumberFormatException e ) {
//  	    	log.debug( Utils.concat( "Error al obtener el parametro ", paramName, " como un objeto de tipo Long desde el valor ", valueAsString ) );
  	    }
	    }

	    return longValue;
	}

	/**
	 * Obtiene un número de tipo Date
	 *
	 * @param paramName Nombre del parámetro a obtener
	 * @param format Formato de entrada del valor a obtener
	 * @return Devuelve un Date con el valor del parámetro indicado
	 */
	public Date getAsDate( String paramName, String format ) {
		Date dateValue = null;

	    String valueAsString = getAsString( paramName );

	    if( valueAsString != null ) {
	    	try {
	    		dateValue = Utils.parseDate( valueAsString, format );
  	    } catch( NumberFormatException e ) {
//  	    	log.debug( Utils.concat( "Error al obtener el parametro ", paramName, " como un objeto de tipo Date desde el valor ", valueAsString ) );
  	    }
	    }


	    return dateValue;
	}

}
