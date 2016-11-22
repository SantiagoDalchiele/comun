package uy.com.uma.comun.util;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Funciones de utilería de datos numéricos
 *
 * Creado Aug 10, 2007
 * @author Santiago Dalchiele
 */
@SuppressWarnings("rawtypes")
public abstract class UtilNumerico {

	/**
	 * Retorna TRUE si la clase es un entero
	 */	
	public static boolean esEntero (Class clase) {
	    return ((clase.equals (BigInteger.class)) ||
	    		(clase.equals (Byte.class)) ||
	    		(clase.equals (Integer.class)) ||
	    		(clase.equals (Long.class)) ||
	    		(clase.equals (Short.class)));
	}
	
	

	/**
	 * Retorna TRUE si la clase es un real
	 */
	public static boolean esReal (Class clase) {
	    return ((clase.equals (BigDecimal.class)) ||
	    		(clase.equals (Double.class)) ||
	    		(clase.equals (Float.class)));
	}

	/**
	 * Retorna TRUE si la clase es numerica
	 */
	public static boolean esNumerico (Class clase) {
	    return esEntero(clase) || esReal(clase);
	}

	/**
	 * Retorna un String con el dato numerico, y si es menor a 10 le agrega un cero
	 */
	public static String getDosDigitos (int dato) {
		if (dato > 9)
			return "" + dato;
		else
			return "0" + dato;
	}

	/**
	 * Retorna la cantidad de digitos de un entero
	 */
	public static int getCantDigitos (long valor) {
		return (new Long (valor).toString()).length();
	}

	/**
	 * Retorna un String con el numero, completando con ceros a la izquierda
	 * para llegar al largo
	 */
	public static String getNumeroStr (long valor, int largo) {
		
		String ret = "";
		
		for (int i = 0; i < (largo - getCantDigitos (valor)); i++)
			ret += "0";
		
		return ret + (new Long (valor)).toString();
	}

	/**
	 * Retorna un String con el dato numerico, y si es menor a 100 le agrega un cero
	 */
	public static String getTresDigitos (int dato) {
		if (dato > 99)
			return "" + dato;
		else
			return "0" + getDosDigitos(dato);
	}

	
	
	/**
	 * Redondea el valor segun la precision indicada
	 */
	public static double redondear (double valor, int precision) {
	    BigDecimal imp = new BigDecimal (valor);
	    return imp.setScale (precision, BigDecimal.ROUND_HALF_EVEN).doubleValue();
	}

	
	
	/**
	 * Redondea el valor segun la precision indicada
	 */
	public static Object redondear (Object valor, int precision) {    	
		if (valor == null)
			return valor;    	
		else if (valor instanceof Number) {
			if (precision == 0)
				return new Long (Math.round (((Number) valor).doubleValue()));
			else
				return new Double (redondear (((Number) valor).doubleValue(), precision));
		} else
			return valor;
	}

	
	
	/**
	 * Retorna el factorial de un entero
	 */
	public static int factorial (int n) {
		if ((n == 0) || (n == 1))
			return 1;
		else
			return n * factorial(n-1);
	}
}
