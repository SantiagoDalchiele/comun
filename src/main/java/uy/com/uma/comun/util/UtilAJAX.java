package uy.com.uma.comun.util;

import javax.servlet.http.HttpServletResponse;

/**
 * Clase de utilería para el manejo de 
 *
 * @author Santiago Dalchiele
 */
public abstract class UtilAJAX {

	
	/**
	 * Inicializa el response AJAX
	 */
	public static void initAjaxResponse (HttpServletResponse response) {
		response.setHeader ("Pragma","No-Cache");
		response.setDateHeader ("Expires",0);
		response.setHeader ("Cache-Control","no-Cache");
		response.setContentType ("application/json");
	}
}
