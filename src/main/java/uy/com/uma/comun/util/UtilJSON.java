package uy.com.uma.comun.util;

/**
 * Clase de utilería para el manejo de JSON
 *
 * @author Santiago Dalchiele
 */
public abstract class UtilJSON {
	
	
	/**
	 * Retorna el identificador de la propiedad entre comillas dobles seguido de dos puntos (:)
	 */
	public static String getPropJSON (String prop) {
		return "\"" + prop + "\":";
	}
	
	

	/**
	 * Retorna un valor entre comillas dobles (") y a continuación una coma (,)
	 */
	public static String getValorJSON (String valor) {
		return "\"" + valor + "\",";
	}
	
	
	
	/**
	 * Retorna un valor entre comillas dobles (")
	 */
	public static String getComillasJSON (String valor) {
		return "\"" + valor + "\"";
	}
}
