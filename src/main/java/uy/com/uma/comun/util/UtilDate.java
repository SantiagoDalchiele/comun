package uy.com.uma.comun.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Funciones de utilería para el manejo de fechas
 * @author Santiago Dalchiele
 */
public class UtilDate {
	
	
	/** 
	 * Fecha para sustituir por null 
	 */
	public static GregorianCalendar FECHA_NULA() {
		return UtilFormato.FECHA_NULA();
	}
	
	
	
	/**
	 * Retorna solo la fecha (sin hora)
	 */    
	public static Date getFecha (Date d) {	
	    GregorianCalendar gc = new GregorianCalendar();
	    
	    gc.setTimeInMillis (d.getTime());
	    gc.set (Calendar.HOUR_OF_DAY, 0);
	    gc.set (Calendar.MINUTE, 0);
	    gc.set (Calendar.SECOND, 0);
	    gc.set (Calendar.MILLISECOND, 0);
	    
	    return new Date (gc.getTimeInMillis());
	}

	
	
	/**
	 * Retorna una fecha con el día de ayer y la última hora 23:59:59.999
	 */
	public static Date ultimaHoraAyer() {
		GregorianCalendar gc = new GregorianCalendar();
		
		gc.setTimeInMillis(new Date().getTime());
		gc.add(Calendar.DAY_OF_YEAR, -1);
	    gc.set (Calendar.HOUR_OF_DAY, 23);
	    gc.set (Calendar.MINUTE, 59);
	    gc.set (Calendar.SECOND, 59);
	    gc.set (Calendar.MILLISECOND, 999);
	    
	    return new Date (gc.getTimeInMillis());
	}
}
