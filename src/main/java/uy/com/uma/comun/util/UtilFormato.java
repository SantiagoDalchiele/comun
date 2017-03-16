package uy.com.uma.comun.util;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Funciones de utileria para formato de datos
 *
 * Creado Aug 10, 2007
 * @author Santiago Dalchiele
 */
public abstract class UtilFormato {

	/** Fecha para sustituir por null */
	public static final GregorianCalendar FECHA_NULA = new GregorianCalendar (1900, 0, 1);
	
	
	

	/**
	 * Retorna true si un Object es nulo o es una instancia de String
	 * y es igual a la cadena vacía
	 */
	public static boolean esNulo (Object valor) {
	    
	    boolean esNulo = (valor == null) || 
				((valor instanceof String) && (valor.toString().trim().equals(""))) ||
				((valor instanceof GregorianCalendar) && (valor.equals(UtilFormato.FECHA_NULA)));
	    
	    if (esNulo)
	        return true;
	    else if (valor instanceof Date) {
	        GregorianCalendar aux = new GregorianCalendar();
	        aux.setTime ((Date) valor);
	        return esNulo (aux);
	    } else
	        return false;
	}

	/**
	 * Retorna el valor boolean de un objecto, si es un String se toma como 
	 * verdadero: "s", "true", "si", "v", ".v.", "verdadero", "t", ".t."
	 * si es un double, se toma como verdadero si es distinto de cero
	 */
	public static Boolean object2Boolean (Object valor) {
	    
	    if (valor == null)
	        return Boolean.FALSE;
	    
	    else if (valor instanceof Boolean)
	        return (Boolean) valor;
	    
	    else if (valor instanceof Number)
	        return new Boolean (Double.parseDouble(valor.toString()) != 0);
	    
	    else if (valor instanceof String) {
	        String c = valor.toString().trim().toLowerCase(Locale.ENGLISH);
	        boolean v = UtilString.esUnoDe (c, new String [] {"s", "true", 
	        		"si", "v", ".v.", "verdadero", "t", ".t.", "1", "on"});                
	        return Boolean.valueOf (v); 
	    } else
	        return Boolean.valueOf (valor.toString());
	}


	/**
	 * Dado un objeto retorna un entero largo
	 */
	public static Long object2Long (Object valor) {
		if (valor instanceof Long)
			return (Long) valor;
		else if (valor instanceof Integer)
			return new Long (((Integer) valor).intValue());
		else if (valor == null)
			return new Long (0);
		else if (valor instanceof Number)
			return new Long (((Number) valor).longValue());
		else {
			try {
				return new Long (Long.parseLong (valor.toString()));
			} catch (NumberFormatException nfe) {
				String v = valor.toString();
				
				if (v.indexOf('.') != -1)
					return new Long (Long.parseLong (v.substring(0, v.indexOf('.'))));
				else
					return new Long (0);
			}
		}
	}

	
	
	/**
	 * Retorna TRUE si ambos son nulos o son iguales
	 */
	public static boolean equals (Object a, Object b) {
		return ((esNulo (a)) && (esNulo (b))) ||
				(! esNulo (a)) && (a.equals (b));
	}
}
